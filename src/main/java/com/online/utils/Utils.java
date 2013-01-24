package com.online.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.online.pojos.Basic;

public class Utils{

	public static String createSHA( String password ) throws NoSuchAlgorithmException{

		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	public static boolean isValidJSON(final String json) {
		   boolean valid = true;
		   try {
		      final JsonParser parser = new JsonParser();
		     JsonElement jsonElement =  parser.parse(json);
		     valid = jsonElement.isJsonObject();

		     
		   } catch (JsonParseException jpe) {
		      valid=false;
		   } 

		   return valid;
	}
	
	public static List<Basic> getTipusDescompte(){
		
		List<Basic> tipusDescompte = new ArrayList<Basic>();
		tipusDescompte.add(new Basic(1,"tant per cent %"));
		tipusDescompte.add(new Basic(2,"quantitat en euros"));
		return tipusDescompte;
	}
	
	public static List<Basic> inizializeListTipusBeguda(){
		List<Basic> tipusBegudaList = new ArrayList<Basic>(); 
		tipusBegudaList.add(new Basic(1,Constants.TIPUS_BEGUDA_REFRESC));
		tipusBegudaList.add(new Basic(2,Constants.TIPUS_BEGUDA_VI));
		tipusBegudaList.add(new Basic(3,Constants.TIPUS_BEGUDA_CAVA));
		return tipusBegudaList;
	}
	
	public static String formatDate(Date data){
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(data);
		
	}
	
	public static String formatDate2(Date data){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(data);
		
	}
	
	public static Date getDate(String date){
		String[] dateString = date.split("-");
		if(dateString.length==3){
			String dia =dateString[0];
			String mes = dateString[1];
			String any = dateString[2];
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dia));
			cal.set(Calendar.MONTH, Integer.parseInt(mes)-1);
			cal.set(Calendar.YEAR, Integer.parseInt(any));
			return cal.getTime();
		}
		return null;
		
	}
	
	public static Date getDate2(String date){
		String[] dateString = date.split("-");
		if(dateString.length==3){
			String any =dateString[0];
			String mes = dateString[1];
			String dia = dateString[2];
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dia));
			cal.set(Calendar.MONTH, Integer.parseInt(mes)-1);
			cal.set(Calendar.YEAR, Integer.parseInt(any));
			return cal.getTime();
		}
		return null;
		
	}
	
	public static Date getData(String dia, String mes, String any){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dia));
		cal.set(Calendar.MONTH, (Integer.parseInt(mes)-1));
		cal.set(Calendar.YEAR, Integer.parseInt(any));
		return cal.getTime();
	}
	
	public static List<Basic> getHoraList(){
		
		List<Basic> horaList = new ArrayList<Basic>();
		
		horaList.add(new Basic(1,Constants.H0800));
		horaList.add(new Basic(1,Constants.H0900));
		horaList.add(new Basic(1,Constants.H1000));
		horaList.add(new Basic(1,Constants.H1100));
		horaList.add(new Basic(1,Constants.H1200));
		horaList.add(new Basic(1,Constants.H1300));
		horaList.add(new Basic(1,Constants.H1400));
		horaList.add(new Basic(1,Constants.H1500));
		horaList.add(new Basic(1,Constants.H1600));
		horaList.add(new Basic(1,Constants.H1700));
		horaList.add(new Basic(1,Constants.H1800));
		horaList.add(new Basic(1,Constants.H1900));
		horaList.add(new Basic(1,Constants.H2000));
		horaList.add(new Basic(1,Constants.H2100));
		horaList.add(new Basic(1,Constants.H2200));
		horaList.add(new Basic(1,Constants.H2300));
		horaList.add(new Basic(1,Constants.H2400));
				
		return horaList;
	}
	
	public static String getHora(String hora){
		String[] horaVec = hora.split(":");
		if(horaVec.length==2){
			return horaVec[0]+horaVec[1];
		}
		return hora;
	}
}
