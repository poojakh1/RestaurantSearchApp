package com.pooja.restaurant.search.csvreader;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pooja.restaurant.search.restaurantsprovider.Restaurant;

public class BackgroundCSVReader implements Runnable {

	private volatile boolean running = true;
	private Timer timer;

	public void terminate() {
		running = false;
		stopTimer();
	}

	BackgroundCSVReader() {
		timer = new Timer();
	}

	@Override
	public void run() {
		while (running) {
			scheduleTimer();
		}
	}

	private void scheduleTimer() {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				File folder = new File(new File("").getAbsolutePath(), "resource");
				File newFileToScan = null;
				File[] listOfFiles = folder.listFiles();
				CSVReaderManager cSVReaderManager = CSVReaderManager.getCSVReadManagerInstance();
				CopyOnWriteArrayList<File> scannedFiles = cSVReaderManager.getScannedFiles();
				for (File file : listOfFiles) {
					boolean fileIsAvailable = false;
					if (!file.isHidden() && file.isFile()) {
						Iterator<File> iterator = scannedFiles.iterator();
						while (iterator.hasNext()) {
							File scannedFile = iterator.next();
							try {
								fileIsAvailable = fileIsAvailable || file.getCanonicalFile().equals(scannedFile.getCanonicalFile());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}, 0, 3000);
	}

	private void stopTimer() {
		timer.cancel();
		timer.purge();
	}
}
