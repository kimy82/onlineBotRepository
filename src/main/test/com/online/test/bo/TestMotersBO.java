package com.online.test.bo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.online.bo.impl.MotersBoImpl;
import com.online.dao.MotersDao;
import com.online.exceptions.BOException;
import com.online.model.Moters;

@RunWith(MockitoJUnitRunner.class)
public class TestMotersBO{

	@SuppressWarnings("unused")
	@Mock
	private MotersDao	mockMotersDao;
	@InjectMocks
	private MotersBoImpl	motersBo;

	private final Moters	emptyMoter	= new Moters();

	@Before
	public void init(){

	}

	@Test(expected = BOException.class)
	public void testSaveNullMoter(){

		this.motersBo.save(null);
	}

	@Test(expected = BOException.class)
	public void testSaveEmptyMoter(){

		this.motersBo.save(this.emptyMoter);
	}

	@Test(expected = BOException.class)
	public void testUpdateNullMoter(){

		this.motersBo.update(null);
	}

	@Test(expected = BOException.class)
	public void testUpdateEmptyMoter(){

		this.motersBo.update(this.emptyMoter);
	}

	@Test
	public void testLoadMoterNullParams(){

		Moters moter = this.motersBo.load(null,null);
		Assert.assertNull(moter);
	}
	
	@Test(expected = BOException.class)
	public void testLoadIdNullMoter(){

		this.motersBo.load(null);
	}

	

}
