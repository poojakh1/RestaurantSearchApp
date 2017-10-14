import java.io.File;
import java.util.Scanner;

import com.pooja.restaurant.search.Constants.*;
import com.pooja.restaurant.search.Utils;
import com.pooja.restaurant.search.csvreader.CSVReaderManager;
import com.pooja.restaurant.search.restaurantsprovider.RestaurantsDataProvider;

public class RestaurantSearchApp {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		File restaurantsfile = new File(new File("").getAbsolutePath(), "resource/sampleCSV.xls");
		CSVReaderManager csvReaderManager = CSVReaderManager.getCSVReadManagerInstance();
		System.out.println("Hold On! fetching restaurants data...");
		csvReaderManager.readRestaurantsCSV(restaurantsfile);
		showRestaurants();
		//csvReaderManager.startScanningCSVsInBackground();
	}

	public static void checkYesNoValidityAndTakeAction(String yesOrNo) {
		if (Utils.isValidString(yesOrNo)) {
			if (yesOrNo.equalsIgnoreCase("y")) {
				showRestaurants();
			} else if (yesOrNo.equalsIgnoreCase("n")) {
				System.out.println("Please restart app If you want to search again");
				//stopScanningCSVs();
			} else {
				System.out.println("Invalid Input. Please restart app.");
				//stopScanningCSVs();
			}
		} else {
			System.out.println("Invalid Input. Please restart app.");
			//stopScanningCSVs();
		}
	}

	public static void showRestaurants() {
		System.out.println("Please enter which category of resturants you would like to see ?");
		String searchQuery = scanner.nextLine();
		if (Utils.isValidString(searchQuery)) {
			RestaurantsDataProvider restaurantsDataProvider = RestaurantsDataProvider
					.getRestaurantsDataProviderrInstance();
			restaurantsDataProvider.searchAndSortRestaurants(searchQuery, SEARCH_CRITERIA.SEARCH_BY_CATEGORY,
					SORT_CRITERIA.SORT_BY_RATING);
			System.out.println("Do you want to continue Y/N ?");
			String yesOrNo = scanner.nextLine();
			checkYesNoValidityAndTakeAction(yesOrNo);
		} else {
			System.out.println("Invalid Input. Please restart app.");
			//stopScanningCSVs();
		}
	}

	public static void stopScanningCSVs() {
		CSVReaderManager csvReaderManager = CSVReaderManager.getCSVReadManagerInstance();
		csvReaderManager.stopScanningCSVsInBackground();
	}
}
