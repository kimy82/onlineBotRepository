package com.online.test.bo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.online.bo.impl.BegudaBoImpl;
import com.online.dao.BegudaDao;
import com.online.exceptions.BOException;
import com.online.model.Beguda;

@RunWith(MockitoJUnitRunner.class)
public class TestBegudaBO{

	@SuppressWarnings("unused")
	@Mock
	private BegudaDao	mockBegudaDao;
	@InjectMocks
	private BegudaBoImpl	begudaBo;

	private final Beguda	begudatest	= new Beguda(null, "", null);

	@Before
	public void init(){

	}

	@Test(expected = BOException.class)
	public void testSaveNullBeguda(){

		this.begudaBo.save(null);
	}

	@Test(expected = BOException.class)
	public void testSaveEmptyBeguda(){

		this.begudaBo.save(begudatest);
	}

	@Test(expected = BOException.class)
	public void testUpdateNullBeguda(){

		this.begudaBo.update(null);
	}

	@Test(expected = BOException.class)
	public void testUpdateEmptyBeguda(){

		this.begudaBo.update(begudatest);
	}

	@Test(expected = BOException.class)
	public void testDeleteNullBeguda(){

		this.begudaBo.delete(null);
	}

	@Test(expected = BOException.class)
	public void testDeleteEmptyBeguda(){

		this.begudaBo.delete(begudatest);
	}

	@Test(expected = BOException.class)
	public void testLoadNullId(){

		this.begudaBo.load(null);
	}

}
