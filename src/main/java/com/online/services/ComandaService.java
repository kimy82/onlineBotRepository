package com.online.services;

import java.util.List;
import java.util.ResourceBundle;

import com.online.exceptions.ComandaException;
import com.online.model.Beguda;
import com.online.model.BegudaComanda;
import com.online.model.Comandes;
import com.online.model.HoresDTO;
import com.online.model.Plat;
import com.online.model.PlatComanda;
import com.online.pojos.Basic;


public interface ComandaService{

	public boolean checkPlatForMoreThanTwoRestaurants(List<PlatComanda> platList, Plat plat) throws ComandaException;
	
	public List<BegudaComanda> addBegudaInList(List<BegudaComanda> begudaList, Beguda beguda,boolean promo) throws ComandaException;
	
	public List<BegudaComanda> removeBegudaInList(List<BegudaComanda> begudaList, Beguda beguda,boolean promo) throws ComandaException;
	 
	public String createJSONForShoppingCart(List<PlatComanda> platList, Long id, ResourceBundle resource) throws ComandaException;
	
	public String createJSONForBegudaList(List<BegudaComanda> listBeguda) throws ComandaException;
	
	public Double getPreuOfComanda(Comandes comanda) throws ComandaException;

	public boolean checkPlatInList(List<PlatComanda> platList, Plat plat) throws ComandaException;
	
	public String checkComandaProblems(Comandes comanda, ResourceBundle resource) throws ComandaException;
	
	public String checkComandaPromocions(Comandes comanda, ResourceBundle resource) throws ComandaException;
	
	public void deleteBegudesPromo(Comandes comanda) throws ComandaException;
	
	public HoresDTO  setHoresFeature(HoresDTO horesDTO, String data, Comandes comanda,boolean aDomicili) throws ComandaException;
	
	public int getNumPlats(List<PlatComanda> platList) throws ComandaException;
	
	public int getNumBegudes(List<BegudaComanda> listBeguda) throws ComandaException;
	
	public Comandes getComandaToRepeat(Comandes comanda) throws ComandaException;
	
	public String getHora(Integer idRestaurant, String data)throws ComandaException;
	
	public boolean checkMoreThanOneRestaurant (Comandes comanda) throws ComandaException;
	
	public String getAddressOfRestaurant (Comandes comanda) throws ComandaException;
	
}
 