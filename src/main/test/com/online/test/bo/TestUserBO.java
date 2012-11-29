package com.online.test.bo;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.online.bo.impl.UsersBoImpl;
import com.online.dao.UsersDao;
import com.online.exceptions.BOException;
import com.online.model.Users;
import com.online.services.SendingEmailService;

@RunWith(MockitoJUnitRunner.class)
public class TestUserBO{

	@SuppressWarnings("unused")
	@Mock
	private SendingEmailService	mockSendingEmailService;
	@Mock
	private UsersDao			mockUsersDao;
	@InjectMocks
	private UsersBoImpl			usersBo;

	private final Users			usertest	= new Users("provaUsuari");

	@Before
	public void init(){

		when(this.mockUsersDao.findByUsername("provaUsuari")).thenReturn(usertest);
	}

	@Test(expected = BOException.class)
	public void testfindByUsernameEmptyParams(){

		Users user = this.usersBo.findByUsername("");
		Assert.assertNull(user);
	}

	@Test(expected = BOException.class)
	public void testfindByUsernameNullParams(){

		this.usersBo.findByUsername(null);

	}

	@Test
	public void testfindByUsername(){

		Users user = this.usersBo.findByUsername("provaUsuari");
		Assert.assertNotNull(user);
	}

	@Test(expected = BOException.class)
	public void testSaveNullUser() throws BOException, Exception{

		this.usersBo.save(null);
	}

	@Test(expected = BOException.class)
	public void testSaveEmptyUser() throws BOException, Exception{

		Users user = new Users();
		this.usersBo.save(user);
	}

	@Test(expected = BOException.class)
	public void testUpdateNullUser(){

		this.usersBo.update(null);
	}

	@Test(expected = BOException.class)
	public void testUpdateEmptyUser(){

		Users user = new Users();
		this.usersBo.update(user);
	}

	@Test(expected = BOException.class)
	public void testDeleteNullUser(){

		this.usersBo.delete(null);
	}

	@Test(expected = BOException.class)
	public void testDeleteEmptyUser(){

		Users user = new Users();
		this.usersBo.delete(user);
	}
}
