package com.pooja.restaurant.search.restaurantsprovider;

import java.util.Comparator;

public class NameCompare implements Comparator<Restaurant> {
	
	@Override
	public int compare(Restaurant r1, Restaurant r2) {
		// TODO Auto-generated method stub
		int result = r1.getName().compareTo(r2.getName());
		return result;
	}
}
