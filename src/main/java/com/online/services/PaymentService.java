package com.online.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.online.exceptions.PaymentException;
import com.online.model.Comandes;


public interface PaymentService{
	
	public List<String> getComandaOrders(Comandes comanda, boolean moreThanOneRestaurant, String transport, String transportDouble) throws PaymentException;	
	public void  sendOrder(boolean toAdmins, List<String> orders) throws PaymentException;
	public String  SHA(String Ds_Merchant_Amount, String Ds_Merchant_Order,String Ds_Merchant_MerchantCode, String DS_Merchant_Currency, String Ds_Merchant_TransactionType,String Ds_Merchant_MerchantURL,String entorn) throws PaymentException, NoSuchAlgorithmException;
	public String  SHAOrder(String order,String entorn) throws PaymentException, NoSuchAlgorithmException;
	public boolean CheckOrderOK(String order, String entorn,String orderID) throws PaymentException, NoSuchAlgorithmException;
	
}
 