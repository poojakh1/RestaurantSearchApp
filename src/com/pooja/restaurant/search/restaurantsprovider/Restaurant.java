package com.pooja.restaurant.search.restaurantsprovider;

import com.pooja.restaurant.search.Utils;

public class Restaurant implements Comparable<Restaurant> {

	private String name;
	private String category;
	private Double rating;

	public Restaurant(String[] restaurantAttributes) {
		name = restaurantAttributes[0];
		category = restaurantAttributes[1];
		rating = Utils.parseRatingValue(restaurantAttributes[2]);
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public Double getRatingValue() {
		return rating;
	}

	@Override
	public String toString() {
		return "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
				+ "\nRestaurant" + "\t| name=" + name + "\t| category=" + category + "\t| rating=" + rating + "]";
	}

	// Compare based on Rating By default
	@Override
	public int compareTo(Restaurant restaurant) {
		int result;
		if (this.rating < restaurant.rating) {
			result = 1;
		} else if (this.rating > restaurant.rating) {
			result = -1;
		} else {
			result = 0;
		}
		return result;
	}
}
