package com.online.action.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.online.bo.impl.RestaurantsBoImpl;
import com.online.dao.RestaurantsDao;
import com.online.exceptions.BOException;
import com.online.model.Restaurant;

@RunWith(MockitoJUnitRunner.class)
public class TestRestaurantsBO{

	
	@Mock
	private RestaurantsDao			mockRestaurantsDao;
	@InjectMocks
	private RestaurantsBoImpl				restaurantsBo;
	
	private final Restaurant restauranttest = new Restaurant(null,"");

	@Before
	public void init(){
	
	}

	@Test(expected = BOException.class)
	public void testSaveNullRestaurant(){

		this.restaurantsBo.save(null);
	}

	@Test(expected = BOException.class)
	public void testSaveEmptyRestaurant(){

		this.restaurantsBo.save(restauranttest);
	}

	@Test(expected = BOException.class)
	public void testUpdateNullRestaurant(){

		this.restaurantsBo.update(null);
	}

	@Test(expected = BOException.class)
	public void testUpdateEmptyRestaurant(){

		this.restaurantsBo.update(restauranttest);
	}

	@Test(expected = BOException.class)
	public void testDeleteNullRestaurant(){

		this.restaurantsBo.delete(null);
	}

	@Test(expected = BOException.class)
	public void testDeleteEmptyRestaurant(){

		this.restaurantsBo.delete(restauranttest);
	}

	@Test
	public void testLoadNullId(){

		Restaurant restaurant = this.restaurantsBo.load(null);
		Assert.assertNull(restaurant);
	}

}
