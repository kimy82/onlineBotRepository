package com.online.test.bo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.online.bo.impl.PlatsBoImpl;
import com.online.dao.PlatsDao;
import com.online.exceptions.BOException;
import com.online.model.Plat;

@RunWith(MockitoJUnitRunner.class)
public class TestPlatsBO{

	@SuppressWarnings("unused")
	@Mock
	private PlatsDao	mockPlatsDao;
	@InjectMocks
	private PlatsBoImpl	platsBo;

	private final Plat	plattest	= new Plat(null, "", null);

	@Before
	public void init(){

	}

	@Test(expected = BOException.class)
	public void testSaveNullPlat(){

		this.platsBo.save(null);
	}

	@Test(expected = BOException.class)
	public void testSaveEmptyPlat(){

		this.platsBo.save(plattest);
	}

	@Test(expected = BOException.class)
	public void testUpdateNullPlat(){

		this.platsBo.update(null);
	}

	@Test(expected = BOException.class)
	public void testUpdateEmptyPlat(){

		this.platsBo.update(plattest);
	}

	@Test(expected = BOException.class)
	public void testDeleteNullPlat(){

		this.platsBo.delete(null);
	}

	@Test(expected = BOException.class)
	public void testDeleteEmptyPlat(){

		this.platsBo.delete(plattest);
	}

	@Test(expected = BOException.class)
	public void testLoadNullId(){

		this.platsBo.load(null,true);
	}

}
