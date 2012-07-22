package com.dabi.habitv.tray.model;

import java.util.Observable;

import com.dabi.habitv.config.entities.Config;
import com.dabi.habitv.core.config.ConfigAccess;
import com.dabi.habitv.core.mgr.CoreManager;
import com.dabi.habitv.grabconfig.entities.GrabConfig;
import com.dabi.habitv.tray.controller.CoreSubscriber;
import com.dabi.habitv.tray.controller.SubscriberAdapter;

public class HabitTvTrayModel extends Observable {

	private final CoreManager coreManager;

	private final Config config = ConfigAccess.initConfig();

	private GrabConfig grabConfig = ConfigAccess.initGrabConfig();

	private final ProgressionModel progressionModel;

	private Thread demonThread;

	public HabitTvTrayModel() {
		super();
		coreManager = new CoreManager(config, grabConfig);
		progressionModel = new ProgressionModel();
	}

	public void attach(final CoreSubscriber coreSubscriber) {
		final SubscriberAdapter subscriberAdapter = new SubscriberAdapter(coreSubscriber);
		coreManager.getCategoryManager().getSearchCategoryPublisher().attach(subscriberAdapter.getSearchCategorySubscriber());
		coreManager.getEpisodeManager().getRetreivePublisher().attach(subscriberAdapter.getRetreiveSubscriber());
		coreManager.getEpisodeManager().getSearchPublisher().attach(subscriberAdapter.getSearchSubscriber());
	}

	public ProgressionModel getProgressionModel() {
		return progressionModel;
	}

	public void startDownloadCheckDemon() {

		demonThread = new Thread() {
			@Override
			public void run() {
				boolean interrupted = false;
				final long confDemonTime;
				if (config.getDemonTime() == null) {
					confDemonTime = 1800;
				} else {
					confDemonTime = config.getDemonTime();
				}
				final long demonTime = confDemonTime * 1000L;
				// demon mode
				while (true) {
					if (interrupted) {
						interrupted = false;
					} else {
						if (grabConfig == null) {
							coreManager.findAndSaveCategory();
							grabConfig = ConfigAccess.initGrabConfig();
						} else {
							coreManager.retreiveEpisode();
						}
					}
					try {
						Thread.sleep(demonTime);
					} catch (final InterruptedException e) {
						// may have been interrupted by a manually start
						interrupted = true;
					}
				}
			}

		};

		demonThread.start();
	}

	public void startDownloadCheck() {

		demonThread.interrupt();
		(new Thread() {
			@Override
			public void run() {
				coreManager.retreiveEpisode();
			}

		}).start();
	}

	public void forceEnd() {
		coreManager.forceEnd();
	}

	public void clear() {
		progressionModel.clear();
	}

	public Config getConfig() {
		return config;
	}

	public void reloadGrabConfig() {
		grabConfig = ConfigAccess.initGrabConfig();
		coreManager.reloadGrabConfig(grabConfig);
	}

}