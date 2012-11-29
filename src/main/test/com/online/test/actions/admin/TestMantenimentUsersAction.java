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
import com.online.action.admin.MantenimentUsuarisAction;
import com.online.bo.RestaurantsBo;
import com.online.bo.UsersBo;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.online.model.Users;
import com.opensymphony.xwork2.ActionProxy;

public class TestMantenimentUsersAction extends StrutsSpringTestCase{

	
	
	@Test
	public void testAjaxTableUsuarisAction() throws Exception{
		request.setParameter("sEcho", "1");
		request.setParameter("iDisplayLength", "5");
		request.setParameter("iDisplayStart", "0");
		UsersBo mockusersBo = mock(UsersBo.class);
		
		List<Users> userList = new LinkedList<Users>();
		for (int i = 0; i < 10; i++) {
			Users user = new Users("nom"+i);
			user.setId(new Long(i));
			user.setEnabled(1);
			userList.add(user);
		}

		Mockito.when(mockusersBo.getAll()).thenReturn(userList);

		ActionProxy proxy = getActionProxy("/admin/ajaxTableUsuarisAction.action");

		MantenimentUsuarisAction action = (MantenimentUsuarisAction) proxy.getAction();

		action.setUsersBo(mockusersBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	@Test
	public void testAjaxTableUsuarisActionNullParams() throws Exception{

		UsersBo mockusersBo = mock(UsersBo.class);

		ActionProxy proxy = getActionProxy("/admin/ajaxTableUsuarisAction.action");

		MantenimentUsuarisAction action = (MantenimentUsuarisAction) proxy.getAction();

		action.setUsersBo(mockusersBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	@Test
	public void testAjaxTableUsuarisActionWrongParams() throws Exception{

		UsersBo mockusersBo = mock(UsersBo.class);

		ActionProxy proxy = getActionProxy("/admin/ajaxTableUsuarisAction.action");

		MantenimentUsuarisAction action = (MantenimentUsuarisAction) proxy.getAction();

		action.setUsersBo(mockusersBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}	
	
	@Test
	public void testAjaxDeleteUserActionWrongParams() throws Exception{

		request.setParameter("id", "1as");
		
		UsersBo mockusersBo = mock(UsersBo.class);
		
		
		ActionProxy proxy = getActionProxy("/admin/ajaxDeleteUserAction.action");

		MantenimentUsuarisAction action = (MantenimentUsuarisAction) proxy.getAction();

		action.setUsersBo(mockusersBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	@Test
	public void testAjaxDeleteUserActionNullParam() throws Exception{
		
		
		UsersBo mockusersBo = mock(UsersBo.class);
		
		
		ActionProxy proxy = getActionProxy("/admin/ajaxDeleteUserAction.action");

		MantenimentUsuarisAction action = (MantenimentUsuarisAction) proxy.getAction();

		action.setUsersBo(mockusersBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	@Test
	public void testAjaxDeleteUserAction() throws Exception{
		
		request.setParameter("id", "1");
		
		UsersBo mockusersBo = mock(UsersBo.class);
		
		Mockito.doNothing().when(mockusersBo).delete(Mockito.any(Users.class));
		
		ActionProxy proxy = getActionProxy("/admin/ajaxDeleteUserAction.action");

		MantenimentUsuarisAction action = (MantenimentUsuarisAction) proxy.getAction();

		action.setUsersBo(mockusersBo);

		String result = proxy.execute();
		assertEquals(null, result);
	}
	
	@Override
	public String getContextLocations(){
		return "/spring/config/BeanLocations.xml";

	}

}
