package com.online.services;

import java.util.List;

import com.online.exceptions.PaymentException;
import com.online.model.Comandes;


public interface PaymentService{
	
	public List<String> getComandaOrders(Comandes comanda) throws PaymentException;	 	
	
}
 