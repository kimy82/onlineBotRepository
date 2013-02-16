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
				String[] params = param.split("=");
				
				if(toAdmins && iterador==0){
					
					resource.queryParam(params[0],CODI_MAQUINA_ADMIN);
					
				}else if(!toAdmins && params[0].equals("telnumber")){
					
					resource.queryParam(params[0],transformTel(params[1]));
					
				}else if(toAdmins && params[0].equals("orderNum")){
					
					resource.queryParam(params[0],"11_"+params[1]);
					
				}else{
					
					resource.queryParam(params[0],params[1]);
				}
				iterador=iterador+1;
				
			}		
			resource.queryParam("admin",String.valueOf(toAdmins));
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
				String nom = platComanda.getPlat().getRestaurants().iterator()
						.next().getNom();
				restaurants.add(codi+"_"+((nom==null)?"":nom));

				int nPlats = platComanda.getNumPlats();
				String nomPlat = platComanda.getPlat().getNom();
				String codiPlat = platComanda.getPlat().getCodi();
				Double preuPlat = platComanda.getPlat().getPreu() * nPlats;
				String comandaSinglePlat = nPlats + ";" +"("+codiPlat+")"+ nomPlat + ";"
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
				String[] infoRestaurant = cod.split("_");
				String[] platsId = comandes.get(infoRestaurant[0]).split(";");
				StringBuffer comandaSB = new StringBuffer("");
				StringBuffer comandaOrderSB = new StringBuffer("resid=" + infoRestaurant[0]
						+ "&comanda=");

				for (String idPlat : platsId) {
					comandaSB.append(comandes.get(infoRestaurant[0] + "_" + idPlat) + ";");
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
						+ this.comanda.getUser().getUsername()+"/r");

				comandaOrderSB.append("&address="
						+ this.comanda.getUser().getAddress()+"/r");

				comandaOrderSB.append("&diahora=" + this.comanda.getHora()
						+ " " + this.comanda.getDia());

				comandaOrderSB.append("&telnumber="
						+ this.comanda.getUser().getTelNumber());
				
				comandaOrderSB.append("&comandaName=Comanda:"+this.comanda.getId()+"/r");
				
				comandaOrderSB.append("&comandaHora=H.Com.:"+this.comanda.getFentrada()+"/r");
				
				comandaOrderSB.append("&comandaEntrega=H.Entrega:"+this.comanda.getHora()+"/r");
				
				comandaOrderSB.append("&comandaLimit=H.Limit:"+this.comanda.getHora()+"/r");
				
				if(this.comanda.getPagada()!=null && this.comanda.getPagada()==true){
					comandaOrderSB.append("&pagada=PAGAT/r");
				}else{
					comandaOrderSB.append("&pagada=NO PAGAT/r");
				}
				
				if(this.comanda.getObservacions()!=null){
					comandaOrderSB.append("&comment="+this.comanda.getObservacions()+"/r");
				}else{
					comandaOrderSB.append("&comment=Sense comentaris/r");
				}
				comandaOrderSB.append("&nomRest=Rest.:"+infoRestaurant[1]+"/r");
				
				orders.add(comandaOrderSB.toString());
			}
		} catch (Exception e) {
			throw new PaymentException(e, "Error creating orders");
		}
		return orders;
	}

	// PRIVATE
	private String transformTel(String tel){
		StringBuffer toSentTel= new StringBuffer("");
		String[] vecTel= tel.split("");
		for(String t : vecTel){
			toSentTel.append(t+"p1");
		}
		return toSentTel.toString();
	}
}
