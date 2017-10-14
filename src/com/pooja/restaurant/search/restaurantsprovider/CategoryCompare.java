package com.pooja.restaurant.search.restaurantsprovider;

import java.util.Comparator;

public class CategoryCompare implements Comparator<Restaurant> {
	@Override
	public int compare(Restaurant r1, Restaurant r2) {
		// TODO Auto-generated method stub
		int result = r1.getCategory().compareTo(r2.getCategory());
		return result;
	}
}
