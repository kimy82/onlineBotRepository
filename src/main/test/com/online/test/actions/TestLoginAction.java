package com.online.test.actions;

import static org.mockito.Mockito.mock;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.mockito.Mockito;

import com.online.action.LoginAction;
import com.online.bo.UsersBo;
import com.online.exceptions.BOException;
import com.online.model.Users;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionProxy;


public class TestLoginAction extends StrutsSpringTestCase {
	
	
	@Test
    public void testlogin() throws Exception {
		
    	ActionProxy proxy = getActionProxy("/login.action");
    	LoginAction action = (LoginAction) proxy.getAction();
    	String result = proxy.execute();              
    	assertEquals("success", result);
    	
    }
	
	@Test
    public void testregisterUserBOException() throws Exception {
		
		UsersBo mockUsersBo = mock(UsersBo.class);
		
    	ActionProxy proxy = getActionProxy("/registerUser.action");
    	LoginAction action = (LoginAction) proxy.getAction();
    	
    	action.setUsername("usernameProva");
    	action.setPassword("123456");
		
    	Mockito.doThrow(new BOException()).when(mockUsersBo).save(Mockito.any(Users.class));  
    	action.setUsersBo(mockUsersBo);
    	
    	String result = proxy.execute();
    	    	
    	assertEquals("error", result);    	
    	assertTrue(!action.getActionErrors().isEmpty());
    	
	}
	
	@Test
    public void testregisterUserException() throws Exception {
		
		UsersBo mockUsersBo = mock(UsersBo.class);
		
    	ActionProxy proxy = getActionProxy("/registerUser.action");
    	LoginAction action = (LoginAction) proxy.getAction();
    	
    	action.setUsername("usernameProva");
    	action.setPassword("123456");
		
    	Mockito.doThrow(new Exception("")).when(mockUsersBo).save(Mockito.any(Users.class));  
    	action.setUsersBo(mockUsersBo);
    	
    	String result = proxy.execute();
    	    	
    	assertEquals("error", result);    	
    	assertTrue(!action.getActionErrors().isEmpty());
    	
	}
	
	@Test
    public void testrecoverAccountWhenUsernameEmpty() throws Exception {
		
		UsersBo mockUsersBo = mock(UsersBo.class);
		Mockito.when(mockUsersBo.findByUsername("")).thenReturn(null);
		
		ActionProxy proxy = getActionProxy("/recoverAccount.action");
    	LoginAction action = (LoginAction) proxy.getAction();
    	action.setUsersBo(mockUsersBo);
    	
    	action.setUsername("");
    	
    	String result = proxy.execute();
    	assertEquals("notfound", result);    	
    	assertTrue(action.getActionErrors().isEmpty());
	}
	
	@Test
    public void testrecoverAccountWhenUsernameNull() throws Exception {
	
		UsersBo mockUsersBo = mock(UsersBo.class);
		Mockito.when(mockUsersBo.findByUsername(null)).thenReturn(null);
		
		ActionProxy proxy = getActionProxy("/recoverAccount.action");
    	LoginAction action = (LoginAction) proxy.getAction();
    	action.setUsersBo(mockUsersBo);
    	
    	action.setUsername("");
    	
    	String result = proxy.execute();
    	assertEquals("notfound", result);    	
    	assertTrue(action.getActionErrors().isEmpty());
	}
	
	@Test
    public void testrecoverAccountWhenUsernameExist() throws Exception {
				
		Users userfound= new Users("usernameProva");
		UsersBo mockUsersBo = mock(UsersBo.class);
		Mockito.when(mockUsersBo.findByUsername("usernameProva")).thenReturn(userfound);
		Mockito.when(mockUsersBo.changeUserPasswordRandomly(Mockito.any(Users.class))).thenReturn("password");		
		Mockito.doNothing().when(mockUsersBo).sendEmail(Mockito.anyString());
		
		
		ActionProxy proxy = getActionProxy("/recoverAccount.action");
    	LoginAction action = (LoginAction) proxy.getAction();
    	action.setUsersBo(mockUsersBo);
    	
    	action.setUsername("usernameProva");
    	
    	String result = proxy.execute();
    	assertEquals("success", result);    	
    	assertTrue(action.getActionErrors().isEmpty());
    	
	}
	
	@Test
    public void testloginfailed() throws Exception {
		
	}
	
	@Test
    public void testlogout() throws Exception {
		
	}
	
	
	@Override
	public String getContextLocations() {
			
	  return "/spring/config/BeanLocations.xml";

	}
	
	


	
	
}
    
