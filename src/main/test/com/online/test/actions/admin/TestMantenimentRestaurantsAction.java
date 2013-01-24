package com.online.test.actions.admin;

import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.mockito.Mockito;

import com.online.action.admin.MantenimentRestaurantsAction;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.BOException;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.opensymphony.xwork2.ActionProxy;

public class TestMantenimentRestaurantsAction extends StrutsSpringTestCase{

	private final Restaurant restaurant = new Restaurant(1,"nom_prova");
	
	@Test
	public void testsaveRestaurantNullId() throws Exception{

		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		Mockito.when(mockRestaurantsBo.load(null,true,false,false)).thenReturn(null);

		ActionProxy proxy = getActionProxy("/admin/saveRestaurant.action");
		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);
		action.setRestaurant(new Restaurant());

		String result = proxy.execute();
		assertEquals("error", result);

	}
	
	@Test
	public void testsaveRestaurantNull() throws Exception{

		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		Mockito.when(mockRestaurantsBo.load(null,true,false,false)).thenReturn(null);

		ActionProxy proxy = getActionProxy("/admin/saveRestaurant.action");
		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);
		action.setRestaurant(null);

		String result = proxy.execute();
		assertEquals("error", result);

	}

	@Test
	public void testsaveRestaurant() throws Exception{

		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		Mockito.when(mockRestaurantsBo.load(null,true,false,false)).thenReturn(null);
		Mockito.doThrow(new BOException()).when(mockRestaurantsBo).update(null);
		Mockito.doNothing().when(mockRestaurantsBo).update(Mockito.any(Restaurant.class));
		
		Restaurant restaurant = new Restaurant(1,"nom_prova");
		restaurant.setDescripcio("decripcio");
		Mockito.when(mockRestaurantsBo.load(Mockito.anyInt(),Mockito.anyBoolean(),Mockito.anyBoolean(),Mockito.anyBoolean())).thenReturn(restaurant);

		ActionProxy proxy = getActionProxy("/admin/saveRestaurant.action");
		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);
		action.setRestaurant(restaurant);

		String result = proxy.execute();
		assertEquals("success", result);

	}
	
	@Test
	public void testsaveNewRestaurant() throws Exception{

		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		Mockito.doThrow(new BOException()).when(mockRestaurantsBo).save(null);
		Mockito.doNothing().when(mockRestaurantsBo).save(Mockito.any(Restaurant.class));
		
		Restaurant restaurant = new Restaurant(1,"nom_prova");
		restaurant.setDescripcio("decripcio");

		ActionProxy proxy = getActionProxy("/admin/saveNewRestaurant.action");
		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);
		action.setRestaurant(restaurant);

		String result = proxy.execute();
		assertEquals("success", result);

	}
	
	@Test
	public void testsaveNewNullRestaurant() throws Exception{

		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		Mockito.doThrow(new BOException()).when(mockRestaurantsBo).save(Mockito.any(Restaurant.class));

		ActionProxy proxy = getActionProxy("/admin/saveNewRestaurant.action");
		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);
		action.setRestaurant(null);

		String result = proxy.execute();
		assertEquals("error", result);

	}
	
	@Test
	public void testAjaxLoadRestaurantAction() throws Exception{

		Restaurant restaurant = new Restaurant(1,"nom_prova");
		restaurant.setDescripcio("decripcio");
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		Mockito.when(mockRestaurantsBo.load(null,true,false,false)).thenReturn(null);
		Mockito.when(mockRestaurantsBo.load(Mockito.anyInt(),Mockito.anyBoolean(),Mockito.anyBoolean(),Mockito.anyBoolean())).thenReturn(restaurant);
		request.setParameter("id", "1");

		
		ActionProxy proxy = getActionProxy("/admin/ajaxLoadRestaurantAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals(null, result);

	}
	
	@Test
	public void testAjaxLoadRestaurantActionNullParam() throws Exception{

		Restaurant restaurant = new Restaurant(1,"nom_prova");
		restaurant.setDescripcio("decripcio");
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		Mockito.when(mockRestaurantsBo.load(null,true,false,false)).thenReturn(null);
		Mockito.when(mockRestaurantsBo.load(Mockito.anyInt(),Mockito.anyBoolean(),Mockito.anyBoolean(),Mockito.anyBoolean())).thenReturn(restaurant);

		
		ActionProxy proxy = getActionProxy("/admin/ajaxLoadRestaurantAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals(null, result);

	}

	@Test
	public void testAjaxTableRestaurants() throws Exception{
		request.setParameter("sEcho", "1");
		request.setParameter("iDisplayLength", "5");
		request.setParameter("iDisplayStart", "0");
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		List<Restaurant> restaurantList = new LinkedList<Restaurant>();
		for (int i = 0; i < 10; i++) {
			Restaurant restaurant = new Restaurant(i, "nom_" + i);
			restaurantList.add(restaurant);
		}

		Mockito.when(mockRestaurantsBo.getAll(true,true,true)).thenReturn(restaurantList);

		ActionProxy proxy = getActionProxy("/admin/ajaxTableRestaurantsAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	@Test
	public void testAjaxTableRestaurantsNullParams() throws Exception{
	
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		List<Restaurant> restaurantList = new LinkedList<Restaurant>();
		for (int i = 0; i < 10; i++) {
			Restaurant restaurant = new Restaurant(i, "nom_" + i);
			restaurantList.add(restaurant);
		}

		Mockito.when(mockRestaurantsBo.getAll(true,true,true)).thenReturn(restaurantList);

		ActionProxy proxy = getActionProxy("/admin/ajaxTableRestaurantsAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals(null, result);
	
	}
	
	@Test
	public void testAjaxTableRestaurantsWrongParams() throws Exception{
		request.setParameter("sEcho", "1");
		request.setParameter("iDisplayLength", "sdf5");
		request.setParameter("iDisplayStart", "dff0");
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		List<Restaurant> restaurantList = new LinkedList<Restaurant>();
		for (int i = 0; i < 10; i++) {
			Restaurant restaurant = new Restaurant(i, "nom_" + i);
			restaurantList.add(restaurant);
		}

		Mockito.when(mockRestaurantsBo.getAll(true,true,true)).thenReturn(restaurantList);

		ActionProxy proxy = getActionProxy("/admin/ajaxTableRestaurantsAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals(null, result);
	
	}
	
	@Test
	public void testAjaxTablePlatsAction() throws Exception{
		request.setParameter("sEcho", "1");
		request.setParameter("iDisplayLength", "5");
		request.setParameter("iDisplayStart", "0");
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		List<Restaurant> restaurantList = new LinkedList<Restaurant>();
		for (int i = 0; i < 10; i++) {
			Restaurant restaurant = new Restaurant(i, "nom_" + i);
			Set<Plat> plats = new HashSet<Plat>();
			for(int j=0; j<5; j++){
				Plat plat= new Plat(Long.parseLong(String.valueOf(j)),"nom_"+j,12.98);
				plats.add(plat);
			}
			restaurant.setPlats(plats);
			restaurantList.add(restaurant);
		}

		Mockito.when(mockRestaurantsBo.getAll(true,true,true)).thenReturn(restaurantList);

		ActionProxy proxy = getActionProxy("/admin/ajaxTablePlatsAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals(null, result);
	
	}
	
	@Test
	public void testAjaxTablePlatsActionNullParams() throws Exception{
	
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		List<Restaurant> restaurantList = new LinkedList<Restaurant>();
		for (int i = 0; i < 10; i++) {
			Restaurant restaurant = new Restaurant(i, "nom_" + i);
			Set<Plat> plats = new HashSet<Plat>();
			for(int j=0; j<5; j++){
				Plat plat= new Plat(Long.parseLong(String.valueOf(j)),"nom_"+j,12.98);
				plats.add(plat);
			}
			restaurant.setPlats(plats);
			restaurantList.add(restaurant);
		}

		Mockito.when(mockRestaurantsBo.getAll(true,true,true)).thenReturn(restaurantList);

		ActionProxy proxy = getActionProxy("/admin/ajaxTablePlatsAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals(null, result);
	
	}
	
	@Test
	public void testAjaxTablePlatsActionWrongParams() throws Exception{

		request.setParameter("sEcho", "1");
		request.setParameter("iDisplayLength", "5dssds");
		request.setParameter("iDisplayStart", "0sdd");
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		List<Restaurant> restaurantList = new LinkedList<Restaurant>();
		for (int i = 0; i < 10; i++) {
			Restaurant restaurant = new Restaurant(i, "nom_" + i);
			Set<Plat> plats = new HashSet<Plat>();
			for(int j=0; j<5; j++){
				Plat plat= new Plat(Long.parseLong(String.valueOf(j)),"nom_"+j,12.98);
				plats.add(plat);
			}
			restaurant.setPlats(plats);
			restaurantList.add(restaurant);
		}

		Mockito.when(mockRestaurantsBo.getAll(true,true,true)).thenReturn(restaurantList);

		ActionProxy proxy = getActionProxy("/admin/ajaxTablePlatsAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	
	@Test
	public void testAjaxDeleteRestaurantAction() throws Exception{

		request.setParameter("idRestaurant", "1");
		
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		Mockito.when(mockRestaurantsBo.load(Mockito.anyInt(),Mockito.anyBoolean(),Mockito.anyBoolean(),Mockito.anyBoolean())).thenReturn(this.restaurant);
		Mockito.doNothing().when(mockRestaurantsBo).delete(this.restaurant);

		ActionProxy proxy = getActionProxy("/admin/ajaxDeleteRestaurantAction.action");

		MantenimentRestaurantsAction action = (MantenimentRestaurantsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);
		
		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	@Override
	public String getContextLocations(){
		return "/spring/config/BeanLocations.xml";

	}

}
