package com.online.test.actions.admin;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.Test;
import org.mockito.Mockito;

import com.online.action.admin.MantenimentBegudaAction;
import com.online.bo.BegudaBo;
import com.online.exceptions.BOException;
import com.online.model.Beguda;
import com.opensymphony.xwork2.ActionProxy;

public class TestMantenimentBegudaAction extends StrutsSpringTestCase{

	private    Beguda				beguda				= new Beguda(new Long(1),"Nom",12.3);

	private List<Beguda>		begudaList				= new ArrayList<Beguda>();
	
	@Test
	public void testajaxLoadBegudaAction() throws Exception{
		request.setParameter("idBeguda", "1");
		BegudaBo mockBegudaBo = mock(BegudaBo.class);

		Mockito.when(mockBegudaBo.load(Mockito.anyLong())).thenReturn(this.beguda);

		ActionProxy proxy = getActionProxy("/admin/ajaxLoadBegudaAction.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();

		action.setBegudaBo(mockBegudaBo);

		String result = proxy.execute();
		assertEquals(null, result);

	}

	@Test
	public void testajaxLoadBegudaActionNullParama() throws Exception{
		
		
		BegudaBo mockBegudaBo = mock(BegudaBo.class);

		Mockito.when(mockBegudaBo.load(Mockito.anyLong())).thenReturn(this.beguda);

		ActionProxy proxy = getActionProxy("/admin/ajaxLoadBegudaAction.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();

		action.setBegudaBo(mockBegudaBo);
		String result = proxy.execute();
		assertEquals(null, result);

	}

	@Test
	public void testajaxTableBegudes()throws Exception{
		
		request.setParameter("sEcho", "1");
		request.setParameter("iDisplayLength", "10");
		request.setParameter("iDisplayStart", "0");
		
		for(int i=0; i<15;i++){
			this.begudaList.add(this.beguda);
		}
		BegudaBo mockBegudaBo = mock(BegudaBo.class);
		Mockito.when(mockBegudaBo.getAll()).thenReturn(this.begudaList);
		
		ActionProxy proxy = getActionProxy("/admin/ajaxTableBegudes.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();

		action.setBegudaBo(mockBegudaBo);
		String result = proxy.execute();
		assertEquals(null, result);	
		
	}
	

	
	@Test
	public void testsaveBegudaUpdate()throws Exception{
		
		BegudaBo mockBegudaBo = mock(BegudaBo.class);
		
		Mockito.doNothing().when(mockBegudaBo).update(Mockito.any(Beguda.class));
		Mockito.doThrow(new BOException()).when(mockBegudaBo).save(Mockito.any(Beguda.class));
		
		ActionProxy proxy = getActionProxy("/admin/saveBeguda.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();
		
		Beguda beguda = new Beguda(new Long(1),"nom",12.4);

		action.setBeguda(beguda);
		action.setBegudaBo(mockBegudaBo);
		String result = proxy.execute();
		assertEquals("success", result);	
		
	}
	
	@Test
	public void testsaveBegudaSave()throws Exception{
		
		BegudaBo mockBegudaBo = mock(BegudaBo.class);
	
		Mockito.doNothing().when(mockBegudaBo).save(Mockito.any(Beguda.class));
		Mockito.doThrow(new BOException()).when(mockBegudaBo).update(Mockito.any(Beguda.class));
		
		
		ActionProxy proxy = getActionProxy("/admin/saveBeguda.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();
		
		Beguda beguda = new Beguda(null,"nom",12.4);

		action.setBeguda(beguda);
		action.setBegudaBo(mockBegudaBo);
		String result = proxy.execute();
		assertEquals("success", result);		
		
	}
	
	@Test
	public void testsaveBegudaSaveNull()throws Exception{
		
		BegudaBo mockBegudaBo = mock(BegudaBo.class);
		Mockito.doThrow(new BOException()).when(mockBegudaBo).update(Mockito.any(Beguda.class));
		Mockito.doThrow(new BOException()).when(mockBegudaBo).save(Mockito.any(Beguda.class));
		
		ActionProxy proxy = getActionProxy("/admin/saveBeguda.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();

		action.setBeguda(null);
		action.setBegudaBo(mockBegudaBo);
		String result = proxy.execute();
		assertEquals("error", result);
		
	}
	
	@Test
	public void testajaxTableBegudesNullParams()throws Exception{
		

		
		for(int i=0; i<15;i++){
			this.begudaList.add(this.beguda);
		}
		BegudaBo mockBegudaBo = mock(BegudaBo.class);
		Mockito.when(mockBegudaBo.getAll()).thenReturn(this.begudaList);
		
		ActionProxy proxy = getActionProxy("/admin/ajaxTableBegudes.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();

		action.setBegudaBo(mockBegudaBo);
		String result = proxy.execute();
		assertEquals(null, result);
				
	}

	@Test
	public void testajaxDeleteBegudaNotNull()throws Exception{
		
		BegudaBo mockBegudaBo = mock(BegudaBo.class);
		
		Mockito.doNothing().when(mockBegudaBo).delete(Mockito.any(Beguda.class));		
		
		ActionProxy proxy = getActionProxy("/admin/ajaxDeleteBeguda.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();
		Beguda beguda = new Beguda(null,"nom",12.4);
		action.setBeguda(beguda);
		action.setBegudaBo(mockBegudaBo);
		String result = proxy.execute();
		assertEquals(null, result);
		
	}
	
	@Test
	public void testajaxDeleteBegudaNull()throws Exception{
		
		BegudaBo mockBegudaBo = mock(BegudaBo.class);
		
		Mockito.doThrow(new BOException()).when(mockBegudaBo).delete(null);
		
		ActionProxy proxy = getActionProxy("/admin/ajaxDeleteBeguda.action");
		MantenimentBegudaAction action = (MantenimentBegudaAction) proxy.getAction();

		action.setBeguda(null);
		action.setBegudaBo(mockBegudaBo);
		String result = proxy.execute();
		assertEquals(null, result);
		
	}
	
	
	@Override
	public String getContextLocations(){
		return "/spring/config/BeanLocations.xml";

	}

}
