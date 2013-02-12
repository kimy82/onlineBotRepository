package com.online.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import com.online.exceptions.PaymentException;
import com.online.model.Comandes;
import com.online.model.PlatComanda;
import com.online.services.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	private Comandes comanda;
	private final String CODI_MAQUINA_ADMIN="AC001";
	
	public void  sendOrder(boolean toAdmins, List<String> orders) throws PaymentException {
		
		for(String order : orders){
			RestClient client = new RestClient();
			Resource resource = client.resource("http://localhost/ComandaRest/jaxrs/comandes/file");
			String[] orderVec = order.split("&");
			int iterador=0;
			for(String param : orderVec){
				if(toAdmins && iterador==0){
					String[] params = param.split("=");
					resource.queryParam(params[0],CODI_MAQUINA_ADMIN);
				}else{
					String[] params = param.split("=");
					resource.queryParam(params[0],params[1]);
				}
				iterador=iterador+1;
				
			}					
			String response = resource.accept("text/plain").get(String.class);
		}
		
	}
	
	public List<String> getComandaOrders(Comandes comanda)
			throws PaymentException {

		this.comanda = comanda;
		List<String> orders = new ArrayList<String>();

		try {

			Map<String, String> comandes = new HashMap<String, String>();
			Set<String> restaurants = new HashSet<String>();

			List<PlatComanda> listOfPlats = this.comanda.getPlats();

			// resId=codiMaquina ; orderNum = numComanda ; deliveryCharge = si
			// va o no la moto ; total= preu total ;
			// nom = nom de l'user ; address = adreça de l'usu ; diahora =
			// diahora de la comanda ; telnumber= tel de l'usu ;
			// comanda = comanda de plats
			for (PlatComanda platComanda : listOfPlats) {
				// Ordenem dades per filtrar per restaurants
				String codi = platComanda.getPlat().getRestaurants().iterator()
						.next().getCodiMaquina();
				restaurants.add(codi);

				int nPlats = platComanda.getNumPlats();
				String nomPlat = platComanda.getPlat().getNom();
				Double preuPlat = platComanda.getPlat().getPreu() * nPlats;
				String comandaSinglePlat = nPlats + ";" + nomPlat + ";"
						+ preuPlat;
				if (comandes.containsKey(codi)) {
					String numIdsPlats = comandes.get(codi);
					numIdsPlats = numIdsPlats + ";"
							+ platComanda.getPlat().getId();
					comandes.remove(codi);
					comandes.put(codi, numIdsPlats);
				} else {
					comandes.put(codi, platComanda.getPlat().getId().toString());
				}
				comandes.put(codi + "_" + platComanda.getPlat().getId(),
						comandaSinglePlat);
			}
			for (String cod : restaurants) {
				// creem order per cada restaurant
				String[] platsId = comandes.get(cod).split(";");
				StringBuffer comandaSB = new StringBuffer("");
				StringBuffer comandaOrderSB = new StringBuffer("resid=" + cod
						+ "&comanda=");

				for (String idPlat : platsId) {
					comandaSB.append(comandes.get(cod + "_" + idPlat) + ";");
				}

				comandaOrderSB.append(comandaSB.toString());
				if (this.comanda.getaDomicili()) {
					comandaOrderSB.append("&deliveryCharge=3.0");
				} else {
					comandaOrderSB.append("&deliveryCharge=0.0");
				}

				comandaOrderSB.append("&orderNum=" + this.comanda.getId());

				comandaOrderSB.append("&total=" + this.comanda.getPreu());

				comandaOrderSB.append("&nom="
						+ this.comanda.getUser().getUsername());

				comandaOrderSB.append("&address="
						+ this.comanda.getUser().getAddress());

				comandaOrderSB.append("&diahora=" + this.comanda.getHora()
						+ " " + this.comanda.getDia());

				comandaOrderSB.append("&telnumber="
						+ this.comanda.getUser().getTelNumber());

				orders.add(comandaOrderSB.toString());
			}
		} catch (Exception e) {
			throw new PaymentException(e, "Error creating orders");
		}
		return orders;
	}

	// PRIVATE
}
