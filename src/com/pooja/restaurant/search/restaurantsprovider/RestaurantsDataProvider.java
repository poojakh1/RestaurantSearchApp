package com.pooja.restaurant.search.restaurantsprovider;

import com.pooja.restaurant.search.Constants.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.*;

public class RestaurantsDataProvider {

	private static RestaurantsDataProvider restaurantsDataProvider;
	private SEARCH_CRITERIA searchCriteria;
	private SORT_CRITERIA sortCriteria;
	private CopyOnWriteArrayList<Restaurant> restaurants;
	
	public static RestaurantsDataProvider getRestaurantsDataProviderrInstance() {
		if (null == restaurantsDataProvider) {
			synchronized (RestaurantsDataProvider.class) {
				if (null == restaurantsDataProvider) {
					restaurantsDataProvider = new RestaurantsDataProvider();
				}
			}
		}
		return restaurantsDataProvider;
	}

	private RestaurantsDataProvider() {
		// these are default search criteria and sort criteria
		searchCriteria = SEARCH_CRITERIA.SEARCH_BY_CATEGORY;
		sortCriteria = SORT_CRITERIA.SORT_BY_RATING;
		restaurants = new CopyOnWriteArrayList<Restaurant>();
	}

	public CopyOnWriteArrayList<Restaurant> getRestaurantsList() {
		return restaurants;
	}

	public ArrayList<Restaurant> searchAndSortRestaurants(String searchQuery) {
		return searchAndSortRestaurants(searchQuery, this.searchCriteria, this.sortCriteria);
	}

	public ArrayList<Restaurant> searchAndSortRestaurants(String searchQuery, SEARCH_CRITERIA searchCriteria) {
		return searchAndSortRestaurants(searchQuery, searchCriteria, this.sortCriteria);
	}

	public ArrayList<Restaurant> searchAndSortRestaurants(String searchQuery, SORT_CRITERIA sortCriteria) {
		return searchAndSortRestaurants(searchQuery, this.searchCriteria, sortCriteria);
	}

	public ArrayList<Restaurant> searchAndSortRestaurants(String searchQuery, SEARCH_CRITERIA searchCriteria,
			SORT_CRITERIA sortCriteria) {
		ArrayList<Restaurant> filteredRestaurants = searchRestaurants(searchQuery, searchCriteria);
		if (sortCriteria == SORT_CRITERIA.SORT_BY_RATING) {
			RatingCompare ratingCompare = new RatingCompare();
			Collections.sort(filteredRestaurants, ratingCompare);
		} else if (sortCriteria == SORT_CRITERIA.SORT_BY_NAME) {
			NameCompare nameCompare = new NameCompare();
			Collections.sort(filteredRestaurants, nameCompare);
		} else if (sortCriteria == SORT_CRITERIA.SORT_BY_CATEGORY) {
			CategoryCompare categoryCompare = new CategoryCompare();
			Collections.sort(filteredRestaurants, categoryCompare);
		}
		displaySortedRestaurants(filteredRestaurants);
		return filteredRestaurants;
	}

	private ArrayList<Restaurant> searchRestaurants(String searchQuery, SEARCH_CRITERIA searchCriteria) {
		ArrayList<Restaurant> filteredRestaurants = new ArrayList<Restaurant>();
		Iterator<Restaurant> iterator = restaurants.iterator();
		// handle cases for different search criteria
		if (searchCriteria == SEARCH_CRITERIA.SEARCH_BY_CATEGORY) {
			while (iterator.hasNext()) {
				Restaurant restaurant = iterator.next();
				if ((restaurant.getCategory().toLowerCase().contains(searchQuery.toLowerCase())) || (searchQuery.equalsIgnoreCase(restaurant.getCategory()))) {
					filteredRestaurants.add(restaurant);
				}
			}
		} else if (searchCriteria == SEARCH_CRITERIA.SEARCH_BY_NAME) {
			while (iterator.hasNext()) {
				Restaurant restaurant = iterator.next();
				if ((restaurant.getName().toLowerCase().contains(searchQuery.toLowerCase())) || (searchQuery.equalsIgnoreCase(restaurant.getName()))) {
					filteredRestaurants.add(restaurant);
				}
			}
		}
		return filteredRestaurants;
	}

	public void addRestaurants(ArrayList<Restaurant> restaurantsList) {
		restaurants.addAll(restaurantsList);
	}
	
	private void displaySortedRestaurants(ArrayList<Restaurant> restaurantsList) {
		System.out.println("Sorted Restaurants:-");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Name                              | Category               | Rating |");
		for (Restaurant restaurant : restaurantsList) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println(restaurant.getName() + "\t| " + restaurant.getCategory()
					+ "\t| " + restaurant.getRatingValue() + "\t| ");
		}
		System.out.println("--------------------------------------------------------------------------------");
	}
}
