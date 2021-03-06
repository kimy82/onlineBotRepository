package com.online.test.actions.admin;

import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.mockito.Mockito;

import com.online.action.admin.MantenimentPlatsAction;
import com.online.action.admin.MantenimentRestaurantsAction;
import com.online.bo.PlatsBo;
import com.online.bo.RestaurantsBo;
import com.online.exceptions.BOException;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.opensymphony.xwork2.ActionProxy;

public class TestMantenimentPlatAction extends StrutsSpringTestCase{

	private final Plat plat = new Plat(new Long(1),"nom_prova",2.3);
	private final Restaurant restaurant = new Restaurant(1,"Rest_1");
	
	
	@Test
	public void testconsultaPlatsEmptyList() throws Exception{

		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);

		Mockito.when(mockRestaurantsBo.getAll(true,true,true)).thenReturn(null);

		ActionProxy proxy = getActionProxy("/admin/plats.action");
		MantenimentPlatsAction action = (MantenimentPlatsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals("success", result);

	}

	
	@Test
	public void testconsultaPlatsNotEmptyList() throws Exception{

		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);

		List<Restaurant> restaurantList = new LinkedList<Restaurant>();
		for (int i = 0; i < 10; i++) {
			Restaurant restaurant = new Restaurant(i, "nom_" + i);
			restaurantList.add(restaurant);
		}

		Mockito.when(mockRestaurantsBo.getAll(true,true,true)).thenReturn(restaurantList);

		ActionProxy proxy = getActionProxy("/admin/plats.action");
		MantenimentPlatsAction action = (MantenimentPlatsAction) proxy.getAction();

		action.setRestaurantsBo(mockRestaurantsBo);

		String result = proxy.execute();
		assertEquals("success", result);
	}
	
	@Test
	public void testSaveNewPlatNull() throws Exception{
		
		PlatsBo mockPlatsBo = mock(PlatsBo.class);		
		Mockito.doThrow(new BOException("")).when(mockPlatsBo).save(Mockito.any(Plat.class));
		
		ActionProxy proxy = getActionProxy("/admin/saveNewPlat.action");
		MantenimentPlatsAction action = (MantenimentPlatsAction) proxy.getAction();
		action.setPlatsBo(mockPlatsBo);
		action.setPlat(null);
		
		String result = proxy.execute();
		assertEquals("success", result);
		
	}
	
	@Test
	public void testSaveNewPlatNotNull() throws Exception{
		
		PlatsBo mockPlatsBo = mock(PlatsBo.class);		
		RestaurantsBo mockRestaurantsBo = mock(RestaurantsBo.class);
		
		
		Mockito.when(mockRestaurantsBo.load(1,true,false,false)).thenReturn(this.restaurant);
		Mockito.when(mockRestaurantsBo.load(2,true,false,false)).thenReturn(this.restaurant);

		Mockito.doNothing().when(mockRestaurantsBo).update(Mockito.any(Restaurant.class));
		Mockito.doNothing().when(mockPlatsBo).save(Mockito.any(Plat.class));
		
		ActionProxy proxy = getActionProxy("/admin/saveNewPlat.action");
		MantenimentPlatsAction action = (MantenimentPlatsAction) proxy.getAction();		
		action.setPlatsBo(mockPlatsBo);
		action.setRestaurantsBo(mockRestaurantsBo);
		Plat plat = new Plat(null,"nom_plat",10.0);
		plat.setDescripcio("descripcio_plat");
		plat.setTipus("tipus");
		action.setPlat(plat);
		action.setIdRestaurants("1,2");
		String result = proxy.execute();
		assertEquals("success", result);
		
	}
	
	
	@Test
	public void testAjaxDeletePlatAction() throws Exception{

		request.setParameter("idRestaurant", "1");
		request.setParameter("idPlat", "1");
		
		PlatsBo mockPlatsBo = mock(PlatsBo.class);		
		 
		Mockito.when(mockPlatsBo.load(Mockito.anyLong(), Mockito.anyBoolean())).thenReturn(this.plat);
		Mockito.doNothing().when(mockPlatsBo).delete(this.plat);

		ActionProxy proxy = getActionProxy("/admin/ajaxDeletePlatAction.action");

		MantenimentPlatsAction action = (MantenimentPlatsAction) proxy.getAction();

		action.setPlatsBo(mockPlatsBo); 

		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	@Test
	public void testAjaxDeletePlatActionWrongParams() throws Exception{

		request.setParameter("idRestaurant", "1");
		request.setParameter("idPlat", "1");
		
		PlatsBo mockPlatsBo = mock(PlatsBo.class);				

		ActionProxy proxy = getActionProxy("/admin/ajaxDeletePlatAction.action");

		MantenimentPlatsAction action = (MantenimentPlatsAction) proxy.getAction();

		action.setPlatsBo(mockPlatsBo);

		String result = proxy.execute();
		
		assertEquals(null, result);
		
	}
	
	@Test
	public void testAjaxDeletePlatActionNullParams() throws Exception{

		
		PlatsBo mockPlatsBo = mock(PlatsBo.class);				

		ActionProxy proxy = getActionProxy("/admin/ajaxDeletePlatAction.action");

		MantenimentPlatsAction action = (MantenimentPlatsAction) proxy.getAction();

		action.setPlatsBo(mockPlatsBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}


	@Override
	public String getContextLocations(){
		return "/spring/config/BeanLocations.xml";

	}

}
