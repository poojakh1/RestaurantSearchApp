package com.pooja.restaurant.search.csvreader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.pooja.restaurant.search.restaurantsprovider.Restaurant;
import com.pooja.restaurant.search.restaurantsprovider.RestaurantsDataProvider;

public class ReataurantCSVReader {

	public void readRestaurantsCSV(File restaurantFile) {
		Workbook w = null;
		try {
			try {
				w = Workbook.getWorkbook(restaurantFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();
			for (int i = 0; i < sheet.getRows(); i++) {
				String[] restaurantAttributes = new String[sheet.getColumns()];
				int k = 0;
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j, i);
					if (cell == null || cell.getContents() == null) {
						// if some cell's content is not present assuming it to blank string
						restaurantAttributes[k] = "";
					} else {
						restaurantAttributes[k] = cell.getContents();
					}
					k++;
				}
				Restaurant restaurant = new Restaurant(restaurantAttributes);
				restaurantsList.add(restaurant);
			}
			saveRestaurants(restaurantsList);

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	private void saveRestaurants(ArrayList<Restaurant> restaurantsList) {
		RestaurantsDataProvider restaurantsDataProvider = RestaurantsDataProvider.getRestaurantsDataProviderrInstance();
		restaurantsDataProvider.addRestaurants(restaurantsList);
	}

}
