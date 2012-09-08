package com.dabi.habitv.framework.plugin.api.dto;

import java.util.List;
import java.util.Map;

import com.dabi.habitv.framework.plugin.api.exporter.PluginExporterInterface;
import com.dabi.habitv.framework.plugin.exception.TechnicalException;

public final class ExporterDTO {
	private final Map<String, PluginExporterInterface> exporterName2exporter;

	private final List<ExportDTO> exporterList;

	public ExporterDTO(final Map<String, PluginExporterInterface> exporterName2exporter, final List<ExportDTO> exporterList) {
		super();
		this.exporterName2exporter = exporterName2exporter;
		this.exporterList = exporterList;
	}

	public PluginExporterInterface getExporter(final String exporterName, final String defaultExporter) {
		PluginExporterInterface exporter = exporterName2exporter.get(exporterName);
		if (exporter == null) {
			exporter = exporterName2exporter.get(defaultExporter);
		}
		if (exporter == null) {
			throw new TechnicalException("No exporter found for " + exporterName + "and no default exporter " + defaultExporter);
		}
		return exporter;
	}

	public List<ExportDTO> getExporterList() {
		return exporterList;
	}

}