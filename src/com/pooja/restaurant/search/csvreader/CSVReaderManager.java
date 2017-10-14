package com.pooja.restaurant.search.csvreader;

import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pooja.restaurant.search.restaurantsprovider.Restaurant;

public class CSVReaderManager {

	private static CSVReaderManager csvReaderManager;
	private ReataurantCSVReader reataurantCSVReader;
    private BackgroundCSVReader backgroundCSVReader;
	private CopyOnWriteArrayList<File> scannedFiles;

	public static CSVReaderManager getCSVReadManagerInstance() {
		if (null == csvReaderManager) {
			synchronized (CSVReaderManager.class) {
				if (null == csvReaderManager) {
					csvReaderManager = new CSVReaderManager();
				}
			}
		}
		return csvReaderManager;
	}

	private CSVReaderManager() {
		reataurantCSVReader = new ReataurantCSVReader();
		scannedFiles = new CopyOnWriteArrayList<File>();
	}

	public CopyOnWriteArrayList<File> getScannedFiles() {
		return scannedFiles;
	}
	
	public void readRestaurantsCSV(File restaurantfile) {
		reataurantCSVReader.readRestaurantsCSV(restaurantfile);
	}

	public void startScanningCSVsInBackground() {
		backgroundCSVReader = new BackgroundCSVReader();
		Thread thread = new Thread(backgroundCSVReader, "backgroundCSVReader1");
		thread.start();
	}
	
	public void stopScanningCSVsInBackground() {
		if (backgroundCSVReader != null) {
			backgroundCSVReader.terminate();
		}
	}
	
	public void addToScannedFiles(File file) {
		scannedFiles.add(file);
	}

}
