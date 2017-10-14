package com.pooja.restaurant.search.restaurantsprovider;

import java.util.Comparator;

public class RatingCompare implements Comparator<Restaurant> {

	@Override
	public int compare(Restaurant r1, Restaurant r2) {
		// TODO Auto-generated method stub
		int result;
		if (r1.getRatingValue() < r2.getRatingValue()) {
			result = -1;
		}	
		if (r1.getRatingValue() > r2.getRatingValue()) {
			result = 1;
		}
		else {
			// result is zero means ratings are equal, so sorting based on name
			result = r1.getName().compareTo(r2.getName());
		}
		return result;
	}

}
