package com.online.services.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import com.online.bo.ClauBo;
import com.online.bo.ComandaBo;
import com.online.exceptions.ComandaException;
import com.online.exceptions.PaymentException;
import com.online.model.BegudaComanda;
import com.online.model.Clau;
import com.online.model.Comandes;
import com.online.model.PlatComanda;
import com.online.services.PaymentService;
import com.online.utils.Constants;

public class PaymentServiceImpl implements PaymentService {

	private Comandes 		comanda;
	private final String	CODI_MAQUINA_ADMIN="AC001";
	private ClauBo			clauBo;
	private ComandaBo	    comandaBo;
	final int SHA1_DIGEST_LENGTH = 20;
	
	public boolean CheckOrderOK(String order, String entorn,String orderID) throws PaymentException, NoSuchAlgorithmException{
		boolean check=false;
		String recoveredOrder = SHAOrder(orderID, entorn);
		if(order.equals(recoveredOrder)){
			check=true;
		}
		return check;
	}
	
	public String  SHA(String ds_Merchant_Amount, String ds_Merchant_Order,String ds_Merchant_MerchantCode, String ds_Merchant_Currency, String ds_Merchant_TransactionType,String ds_Merchant_MerchantURL,String entorn) throws PaymentException, NoSuchAlgorithmException {
		try{
		   Clau clau = this.clauBo.getClau(entorn);
//		   byte bAmount[]   = new byte[ds_Merchant_Amount.length()];  
//	       byte bOrder[]    = new byte[ds_Merchant_Order.length()];  
//	       byte bCode[]     = new byte[ds_Merchant_MerchantCode.length()];  
//	       byte bCurrency[] = new byte[ds_Merchant_Currency.length()];
//	       byte bTransactionType[] = new byte[ds_Merchant_TransactionType.length()];  
//	       byte bMerchantURL[] = new byte[ds_Merchant_MerchantURL.length()];  
//	       byte bPassword[] = new byte[clau.getCode().length()];   
//	       
//	       MessageDigest sha = MessageDigest.getInstance("SHA-1");
//	       sha.update(bAmount);
//	       sha.update(bOrder);
//	       sha.update(bCode); 
//	       sha.update(bCurrency);
//	       sha.update(bTransactionType);
//	       sha.update(bMerchantURL);
//	       byte[] hash = sha.digest(bPassword);
//	 
//	       String merchant_Signature = new String();
//	 
//	       int h = 0;
//	       String s = new String();
//	              
//	       for(int i = 0; i < SHA1_DIGEST_LENGTH; i++)
//	        {         
//	         h = (int) hash[i];          // Convertir de byte a int
//	         if(h < 0) h += 256;  // Si son valores negativos, pueden haber problemas de conversi¢n.
//	         s = Integer.toHexString(h); // Devuelve el valor hexadecimal como un String        
//	         if (s.length() < 2) merchant_Signature = merchant_Signature.concat("0"); // A¤ade un 0 si es necesario
//	         merchant_Signature = merchant_Signature.concat(s); // A¤ade la conversi¢n a la cadena ya existente
//	        }
//
//			merchant_Signature = merchant_Signature.toUpperCase();
//			
//			return merchant_Signature;
		
		String cadena = ds_Merchant_Amount+ds_Merchant_Order+ds_Merchant_MerchantCode+ds_Merchant_Currency+ds_Merchant_TransactionType+ds_Merchant_MerchantURL+clau.getCode();
		MessageDigest md;
		byte[] buffer, digest;
		String hash = "";

		
		  buffer = cadena.getBytes();
	        md = MessageDigest.getInstance("SHA1");
	        md.update(buffer);
	        digest = md.digest();

	        for(byte aux : digest) {
	            int b = aux & 0xff;
	            if (Integer.toHexString(b).length() == 1) hash += "0";
	            hash += Integer.toHexString(b);
	        }
	     return hash;
		}catch(Exception e){
			e.printStackTrace();
			throw new PaymentException(e,"payment");
		}
	}
	
	public String  SHAOrder(String order,String entorn) throws PaymentException, NoSuchAlgorithmException {
		try{
		Clau clau = this.clauBo.getClau(entorn);
		
		String cadena = order+clau.getCode();
		MessageDigest md;
		byte[] buffer, digest;
		String hash = "";

		
		  buffer = cadena.getBytes();
	        md = MessageDigest.getInstance("SHA1");
	        md.update(buffer);
	        digest = md.digest();

	        for(byte aux : digest) {
	            int b = aux & 0xff;
	            if (Integer.toHexString(b).length() == 1) hash += "0";
	            hash += Integer.toHexString(b);
	        }
	     return hash;
		}catch(Exception e){
			e.printStackTrace();
			throw new PaymentException(e,"payment");
		}
	}
	

	public void  sendOrder(boolean toAdmins,boolean toAdminRestaurant, List<String> orders, String entorncomandarest) throws PaymentException {
		
		String resourceComanda =getResourceComanda(entorncomandarest);
		
		if(toAdminRestaurant && toAdmins){
			//Tiquets dels moters als restaurants
			for(String order : orders){
				RestClient client = new RestClient();
				Resource resource = client.resource(resourceComanda);
				String[] orderVec = order.split("&");
				int iterador=0;
				String begudes="";
				for(String param : orderVec){
					String[] params = param.split("=");
					
					if(!toAdmins && params[0].equals("telnumber")){
						
						resource.queryParam(params[0],transformTel(params[1]));
						
					}else if(params[0].equals("orderNum")){
						
						resource.queryParam(params[0],"M_TO_R"+params[1]);
						
					}else if (toAdmins && params[0].equals("begudes")){
						
						begudes = (params.length==1)?"": (";"+params[1]);
						
					}else if (!toAdmins && params[0].equals("begudes")){
						
						begudes = "";
						
					}else if (toAdmins && params[0].equals("comanda")){
						if(params.length==1){
							resource.queryParam(params[0],begudes);
						}else{
							resource.queryParam(params[0],params[1]+""+begudes);
						}
						
					}else{
						
						if(params.length==1){
							resource.queryParam(params[0],"sense comentaris");
						}else{
						resource.queryParam(params[0],params[1]);
						}
					}
					iterador=iterador+1;
					
				}		
				resource.queryParam("admin",String.valueOf(toAdmins));
				String response = resource.accept("text/plain").get(String.class);
			 }
		}
		if(toAdmins && !toAdminRestaurant){
			for(String order : orders){
				RestClient client = new RestClient();
				Resource resource = client.resource(resourceComanda);
				String[] orderVec = order.split("&");
				int iterador=0;
				String begudes="";
				//Tiquets dels moters a portamu
				for(String param : orderVec){
					String[] params = param.split("=");
					
					if(toAdmins && iterador==0){
						
						resource.queryParam(params[0],CODI_MAQUINA_ADMIN);
						
					}else if(!toAdmins && params[0].equals("telnumber")){
						
						resource.queryParam(params[0],transformTel(params[1]));
						
					}else if(params[0].equals("orderNum")){
						
						resource.queryParam(params[0],"M_"+params[1]);
						
					}else if (toAdmins && params[0].equals("begudes")){
						
						begudes = (params.length==1)?"": (";"+params[1]);
						
					}else if (!toAdmins && params[0].equals("begudes")){
						
						begudes = "";
						
					}else if (toAdmins && params[0].equals("comanda")){
						if(params.length==1){
							resource.queryParam(params[0],begudes);
						}else{						
							resource.queryParam(params[0],params[1]+""+begudes);
						}
					}else{
						if(params.length==1){
							resource.queryParam(params[0],"sense comentaris");
						}else{
						    resource.queryParam(params[0],params[1]);
						}
					}
					iterador=iterador+1;
					
				}		
				resource.queryParam("admin",String.valueOf(toAdmins));
				String response = resource.accept("text/plain").get(String.class);
			 }
			
		}else if(!toAdmins && !toAdminRestaurant){

			//Tiquet del restaurant a portamu
			for(String order : orders){
				RestClient client = new RestClient();
				Resource resource = client.resource(resourceComanda);
				String[] orderVec = order.split("&");
				int iterador=0;
				String begudes="";
				for(String param : orderVec){
					String[] params = param.split("=");
					
					if(iterador==0){
						
						resource.queryParam(params[0],CODI_MAQUINA_ADMIN);
						
					}else if(!toAdmins && params[0].equals("telnumber")){
						
						resource.queryParam(params[0],transformTel(params[1]));
						
					}else if (params[0].equals("begudes")){
						
						begudes = "";
						
					}else if( params[0].equals("orderNum")){
						
						resource.queryParam(params[0],"R_TO_M"+params[1]);
						
					}else if (toAdmins && params[0].equals("comanda")){
						if(params.length==1){
							resource.queryParam(params[0],"no ID");
						}else{
							resource.queryParam(params[0],params[1]);
						}
					}else{
						
						if(params.length==1){
							resource.queryParam(params[0],"sense comentaris");
						}else{
							resource.queryParam(params[0],params[1]);
						}
					}
					iterador=iterador+1;
					
				}		
				resource.queryParam("admin",String.valueOf(toAdmins));
				String response = resource.accept("text/plain").get(String.class);
			 }
			//Tiquet del restaurant al restaurant 1
			for(String order : orders){
				RestClient client = new RestClient();
				Resource resource = client.resource(resourceComanda);
				String[] orderVec = order.split("&");
				int iterador=0;
				String begudes="";
				for(String param : orderVec){
					String[] params = param.split("=");
					
					 if(!toAdmins && params[0].equals("telnumber")){
						
						resource.queryParam(params[0],transformTel(params[1]));
						
					}else if(params[0].equals("orderNum")){
						
						resource.queryParam(params[0],"R_"+params[1]);
						
					}else if (params[0].equals("begudes")){
						
						begudes = "";
						
					}else if (toAdmins && params[0].equals("comanda")){
						if(params.length==1){
							resource.queryParam(params[0],"no ID ");
						}else{
							resource.queryParam(params[0],params[1]);
						}
					}else{
						
						if(params.length==1){
							resource.queryParam(params[0],"sense comentaris");
						}else{
							resource.queryParam(params[0],params[1]);
						}
					}
					iterador=iterador+1;
					
				}		
				resource.queryParam("admin",String.valueOf(toAdmins));
				String response = resource.accept("text/plain").get(String.class);
			 }			
		}
	}
	
	public List<String> getComandaOrders(Comandes comanda, boolean moreThanOneRestaurant,String transport, String transportDouble,String moterTime,int tempsPreparacio)
			throws PaymentException {

		this.comanda = comanda;
		
		Double preuTotalComanda = getPreuOfComanda(comanda);
		
		if(this.comanda.getImportDescomte()!=null && this.comanda.getTipuDescomte()!=null){
			if(this.comanda.getTipuDescomte().equals(Constants.TIPUS_DESCOMPTE_CENT_1)){
				preuTotalComanda = preuTotalComanda-((this.comanda.getImportDescomte()*preuTotalComanda)/100);
			}else if(this.comanda.getTipuDescomte().equals(Constants.TIPUS_DESCOMPTE_AMOUNT_2)){
				preuTotalComanda = preuTotalComanda-this.comanda.getImportDescomte();
			}else{
				
			}
		}
		
		List<String> orders = new ArrayList<String>();

		try {

			Map<String, String> comandes = new HashMap<String, String>();
			Set<String> restaurants = new HashSet<String>();

			List<PlatComanda> listOfPlats = this.comanda.getPlats();
			
			List<BegudaComanda> listOfBegudes = this.comanda.getBegudes();

			// resId=codiMaquina ; orderNum = numComanda ; deliveryCharge = si
			// va o no la moto ; total= preu total ;
			// nom = nom de l'user ; address = adreça de l'usu ; diahora =
			// diahora de la comanda ; telnumber= tel de l'usu ;
			// comanda = comanda de plats
			StringBuffer comandaBeguda = new StringBuffer();
			
			for(BegudaComanda begudaComanda : listOfBegudes){
				
				
				
				String nom = begudaComanda.getBeguda().getNom();
				Double preu = begudaComanda.getBeguda().getPreu();
				int nbegudes = begudaComanda.getNumBegudes()+ begudaComanda.getNumBegudesPromo();
				
				comandaBeguda.append(nbegudes+";"+nom+";"+preu+";");
				
			}
			
			if(comandaBeguda.length()>0){
				
				comandaBeguda.setLength(comandaBeguda.length()-1);				
				
			}
			
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
			int numRest=1;
			for (String cod : restaurants) {
				// creem order per cada restaurant
				String[] infoRestaurant = cod.split("_");
				String[] platsId = comandes.get(infoRestaurant[0]).split(";");
				StringBuffer comandaSB = new StringBuffer("");
				
				StringBuffer comandaOrderSB = new StringBuffer("resid=" + infoRestaurant[0]);
				comandaOrderSB.append("&begudes="+comandaBeguda.toString());
				
				for (String idPlat : platsId) {
					comandaSB.append(comandes.get(infoRestaurant[0] + "_" + idPlat) + ";");
				}
				comandaSB.setLength(comandaSB.length()-1);
				
				int dayAvui = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				Calendar cal = Calendar.getInstance();
				cal.setTime(this.comanda.getDia());
				int dayComanda = cal.get(Calendar.DAY_OF_MONTH);
				if(dayComanda>dayAvui)			
					comandaOrderSB.append("&comanda= AL LORO; AL LORO; AL LORO; "+comandaSB.toString());
				else
					comandaOrderSB.append("&comanda="+comandaSB.toString());
					
				
				if (this.comanda.getaDomicili()) {		
					if(moreThanOneRestaurant)
						comandaOrderSB.append("&deliveryCharge="+transportDouble);
					else
						comandaOrderSB.append("&deliveryCharge="+transport);
				} else {
					comandaOrderSB.append("&deliveryCharge=0.0");
				}

				comandaOrderSB.append("&orderNum=" + this.comanda.getId()+numRest);

				comandaOrderSB.append("&total=" + preuTotalComanda);				
			
				comandaOrderSB.append("&nom="
						+ this.comanda.getUser().getNom());
				
				if(this.comanda.getAddress()!=null && !this.comanda.getAddress().equals("") ){
					comandaOrderSB.append("&address="+ this.comanda.getAddress());
				}else{
					comandaOrderSB.append("&address="+ this.comanda.getUser().getAddress());
				}

				comandaOrderSB.append("&diahora=D.Entrega:" + this.comanda.getDia());

				comandaOrderSB.append("&telnumber="
						+ this.comanda.getUser().getTelNumber());
				
				comandaOrderSB.append("&comandaName=Comanda:"+this.comanda.getId());								
				
				comandaOrderSB.append("&comandaHora=H.Comanda:"+calculHorafromMinuts((Calendar.getInstance().get(Calendar.HOUR_OF_DAY)*60)+Calendar.getInstance().get(Calendar.MINUTE)));
				
				comandaOrderSB.append("&comandaEntregaRang=H.Entrega:"+prepareHoraFormat(this.comanda.getHora())+" "+rangHora(this.comanda.getHora()));
				
				if(dayComanda>dayAvui){
					this.comanda.setHoraEntrega(this.prepareHoraFormat(this.comanda.getHora()));
					comanda.setHoraEntrega(this.prepareHoraFormat(this.comanda.getHora()));
					comandaOrderSB.append("&comandaEntregaRest=H.Entrega:"+prepareHoraFormat(this.comanda.getHora()));
				}else{
					String hEntrega = getHEntregraPlats(this.comanda.getHora(), tempsPreparacio, moterTime);
					this.comanda.setHoraEntrega(hEntrega);
					comanda.setHoraEntrega(hEntrega);
					comandaOrderSB.append("&comandaEntregaRest=H.Entrega:"+hEntrega);
				}
				this.comandaBo.update(this.comanda);
				comandaOrderSB.append("&comandaLimit=H.Limit:"+rangHora(this.comanda.getHora()));
				
				if(this.comanda.getPagada()!=null && this.comanda.getPagada()==true){
					comandaOrderSB.append("&pagada=AMB TARGETA");
				}else{
					comandaOrderSB.append("&pagada=A CONTRAREMBOLS");
				}
				
				if(this.comanda.getObservacions()!=null && !this.comanda.getObservacions().equals("")){
					comandaOrderSB.append("&comment="+this.comanda.getObservacions());
				}else{
					comandaOrderSB.append("&comment=Sense comentaris");
				}
				comandaOrderSB.append("&nomRest=Restaurant:"+infoRestaurant[1]);
				
				
				String order  = Normalizer.normalize(comandaOrderSB.toString(), Normalizer.Form.NFD);
				order = order.replaceAll("[^\\p{ASCII}]", "");
				orders.add(order);
				
				numRest++;
			}
		} catch (Exception e) {
			throw new PaymentException(e, "Error creating orders");
		}
		return orders;
	}

	// PRIVATE
	private String getResourceComanda(String entorncomandarest){
		String resourceComanda ="http://www.portamu.com/ComandaRest/jaxrs/comandes/file";
		if(entorncomandarest!=null && entorncomandarest.equals(Constants.ENTORN_LOCAL)){
			resourceComanda="http://localhost/ComandaRest/jaxrs/comandes/file";
		}
		return resourceComanda;
	}
	private Double getPreuOfComanda( Comandes comanda ) throws ComandaException{

		try {
			if (comanda == null)
				return 0.0;

			Double preuComanda = 0.0;
			List<BegudaComanda> listBeguda = comanda.getBegudes();
			List<PlatComanda> platList = comanda.getPlats();
			if (!listBeguda.isEmpty()) {
				for (BegudaComanda begudaComanda : listBeguda) {
					preuComanda = preuComanda + (begudaComanda.getNumBegudes() * begudaComanda.getBeguda().getPreu());
				}
			}

			if (!platList.isEmpty()) {
				for (PlatComanda platComanda : platList) {

					preuComanda = preuComanda + (platComanda.getNumPlats() * platComanda.getPlat().getPreu());
				}
			}

			return preuComanda;
		} catch (Exception e) {
			throw new ComandaException(e, "Error getting preu");
		}
	}
	
	private  String getHEntregraPlats(String iniRang, int tempsPlat,String tempsMoter){
	
		if(iniRang==null || iniRang.equals("")){
			throw new PaymentException( "No hi ha hora limit");
		}
		
		String hEntrega=iniRang;
	
		try{
			if(tempsMoter==null || tempsMoter.equals("")){
				tempsMoter="7";
			}
			
			Integer hlimit= calculMinutsHora(iniRang)+30;
			Integer tmoter =  Integer.parseInt(tempsMoter);		
			String horaOfDay = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			String minOfDay = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
			if(horaOfDay.length()==1)horaOfDay="0"+horaOfDay;
			if(minOfDay.length()==1)minOfDay="0"+minOfDay;
			Integer horaComanda =calculMinutsHora(horaOfDay+minOfDay);
			
			if(0<=(hlimit-horaComanda-tempsPlat-tmoter) && (hlimit-horaComanda-tempsPlat-tmoter)<30){
				if(horaComanda+tempsPlat-tmoter>hlimit){
					hEntrega=calculHorafromMinuts(hlimit-5);
				}else{
					hEntrega=calculHorafromMinuts(horaComanda+tempsPlat);
				}
			}else if(30<=(hlimit-horaComanda-tempsPlat-tmoter)){
				hEntrega=calculHorafromMinuts(hlimit-30);
			}
			return hEntrega;
		}catch(Exception e){
			return rangHora(iniRang);
		}
	}
	private String calculHorafromMinuts(int minuts){
		
		String hora="";
		int horaInt=minuts/60;
		int minutsInt= minuts%60;	
		String horaS=String.valueOf(horaInt);
		String minutS=String.valueOf(minutsInt);
		if(horaS.length()==1){
			horaS="0"+horaS;
		}
		if(minutS.length()==1){
			minutS="0"+minutS;
		}
		hora=horaS+":"+minutS;
		return hora;
		
	}
	
	private int calculMinutsHora(String hora){
		if(hora==null){
			throw new PaymentException( "No hi ha hora");
		}
		String[] horaVec = hora.split("(?<!^)"); 
		
		int minuts=0;
		
		if(horaVec.length==4){
			String horaP = (horaVec[0].equals("0")?"":horaVec[0])+horaVec[1];
			String horaF = (horaVec[2].equals("0")?"":horaVec[2])+horaVec[3];
			minuts= (Integer.parseInt(horaP)*60)+Integer.parseInt(horaF);
			
		}
		if(minuts==0){
			throw new PaymentException();
		}
		return minuts;
		
	}
	
	
	private String prepareHoraFormat(String hora){
		if(hora==null)return "";
		String[] horaVec = hora.split("(?<!^)"); 
		if(horaVec.length==4){
			String horaP = horaVec[0]+horaVec[1];
			String horaF = horaVec[2]+horaVec[3];
			return horaP+":"+horaF;
		}
		return hora;
	}
	
	/**
	 * Torna la hora formatada mes 30 minuts
	 * @param hora
	 * @return
	 */
	private String rangHora(String hora){
		if(hora==null)return "";
		String[] horaVec = hora.split("(?<!^)");
		
		if(horaVec.length==4){
			String horaP = (horaVec[0].equals("0")?"":horaVec[0])+horaVec[1];
			String horaF = horaVec[2]+horaVec[3];
			if(horaF.equals("00")){
				if(horaP!=null){					
					horaF="30";
					hora=horaP+":"+horaF;
				}
			}else{
				int horaPInt = Integer.parseInt(horaP);
				horaP=String.valueOf(horaPInt+1);
				horaF="00";
				hora=horaP+":"+horaF;
			}
		}
		return hora;
		
	}
	private String transformTel(String tel){
		if(tel==null)return "no Tel";
		StringBuffer toSentTel= new StringBuffer("");
		String[] vecTel= tel.split("");
		for(String t : vecTel){
			toSentTel.append(t+"p1");
		}
		return toSentTel.toString();
	}

	public void setClauBo(ClauBo clauBo) {
		this.clauBo = clauBo;
	}

	public void setComandaBo( ComandaBo comandaBo ){
	
		this.comandaBo = comandaBo;
	}	
	
	
}

