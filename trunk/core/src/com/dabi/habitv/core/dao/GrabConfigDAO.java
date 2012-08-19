package com.dabi.habitv.core.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.dabi.habitv.core.config.HabitTvConf;
import com.dabi.habitv.framework.plugin.api.dto.CategoryDTO;
import com.dabi.habitv.framework.plugin.exception.InvalidCategoryException;
import com.dabi.habitv.framework.plugin.exception.TechnicalException;
import com.dabi.habitv.grabconfig.entities.Category;
import com.dabi.habitv.grabconfig.entities.Channel;
import com.dabi.habitv.grabconfig.entities.GrabConfig;
import com.dabi.habitv.utils.FileUtils;

public class GrabConfigDAO {

	private static final Logger LOGGER = Logger.getLogger(GrabConfigDAO.class);
	private final String grabConfigFile;

	public GrabConfigDAO(final String grabConfigFile) {
		super();
		this.grabConfigFile = grabConfigFile;
	}

	public void saveGrabConfig(final Map<String, Set<CategoryDTO>> channel2Categories) {
		final GrabConfig config = new GrabConfig();
		for (final Entry<String, Set<CategoryDTO>> entry : channel2Categories.entrySet()) {
			final Channel channel = new Channel();
			channel.setName(entry.getKey());
			for (final CategoryDTO categoryDTO : entry.getValue()) {
				try {
					categoryDTO.check();
				} catch (final InvalidCategoryException e) {
					LOGGER.error("Invalid Category" + categoryDTO, e);
				}
				channel.getCategory().add(buildCategory(categoryDTO));
			}
			config.getChannel().add(channel);
		}
		marshal(config);
	}

	private Category buildCategory(final CategoryDTO categoryDTO) {
		final Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setExtension(categoryDTO.getExtension());
		category.setToDownload(false);
		for (final String exclude : categoryDTO.getExclude()) {
			category.getExclude().add(exclude);
		}
		for (final String include : categoryDTO.getInclude()) {
			category.getInclude().add(include);
		}
		for (final CategoryDTO subCategoryDTO : categoryDTO.getSubCategories()) {
			category.getCategory().add(buildCategory(subCategoryDTO));
		}
		return category;
	}

	private void marshal(final GrabConfig config) {
		final JAXBContext jaxbContext;
		FileOutputStream inputFile = null;
		try {
			jaxbContext = JAXBContext.newInstance(HabitTvConf.GRAB_CONF_PACKAGE_NAME);
			final Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, HabitTvConf.ENCODING);
			inputFile = new FileOutputStream(grabConfigFile);
			marshaller.marshal(config, inputFile);
		} catch (JAXBException | FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			if (inputFile != null) {
				try {
					inputFile.close();
				} catch (final IOException e) {
					throw new TechnicalException(e);
				}
			}
		}
	}

	private static Set<CategoryDTO> buildCategoryListDTO(final String channelName, final List<Category> categories) {
		final Set<CategoryDTO> categoryDTOs = new HashSet<>(categories.size());
		CategoryDTO categoryDTO;
		for (final Category category : categories) {
			if (category.getToDownload() == null || category.getToDownload()) {
				categoryDTO = new CategoryDTO(channelName, category.getName(), category.getId(), category.getInclude(), category.getExclude(),
						category.getExtension());
				categoryDTO.addSubCategories(buildCategoryListDTO(channelName, category.getCategory()));
				categoryDTOs.add(categoryDTO);
			}
		}
		return categoryDTOs;
	}

	public GrabConfig unmarshal() {
		GrabConfig grabConfig = null;
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(HabitTvConf.GRAB_CONF_PACKAGE_NAME);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			FileUtils.setValidation(unmarshaller, HabitTvConf.GRAB_CONF_XSD);
			grabConfig = ((GrabConfig) unmarshaller.unmarshal(new InputStreamReader(new FileInputStream(grabConfigFile), HabitTvConf.ENCODING)));
		} catch (final JAXBException e) {
			throw new TechnicalException(e);
		} catch (final UnsupportedEncodingException e) {
			throw new TechnicalException(e);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		}
		return grabConfig;
	}

	private Map<String, Set<CategoryDTO>> buildCategoryDTO(final GrabConfig grabConfig) {
		final Map<String, Set<CategoryDTO>> channel2Category = new HashMap<>();
		for (final Channel channel : grabConfig.getChannel()) {
			channel2Category.put(channel.getName(), buildCategoryListDTO(channel.getName(), channel.getCategory()));
		}
		return channel2Category;
	}

	public Map<String, Set<CategoryDTO>> load() {
		return buildCategoryDTO(unmarshal());
	}

	public boolean exist() {
		return (new File(grabConfigFile)).exists();
	}
}