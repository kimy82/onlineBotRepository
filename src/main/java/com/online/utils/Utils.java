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
import com.online.bo.UsersBo;
import com.online.model.Users;
import com.online.pojos.Basic;

public class Utils{
	
	public static String escapeUTF(String desc){
		desc = desc.replaceAll("À", "&#192;");
		desc = desc.replaceAll("Á", "&#193;");
		desc = desc.replaceAll("È", "&#200;");
		desc = desc.replaceAll("É", "&#201;");		
		desc = desc.replaceAll("Ì", "&#204;");
		desc = desc.replaceAll("Í", "&#205;");
		desc = desc.replaceAll("Ñ", "&#209;");
		desc = desc.replaceAll("Ò", "&#210;");
		desc = desc.replaceAll("Ó", "&#211;");
		desc = desc.replaceAll("Ù", "&#217;");
		desc = desc.replaceAll("Ú", "&#218;");
		desc = desc.replaceAll("Ü", "&#220;");		
		desc = desc.replaceAll("à", "&#224;");
		desc = desc.replaceAll("á", "&#225;");
		desc = desc.replaceAll("ç", "&#231;");
		desc = desc.replaceAll("è", "&#232;");
		desc = desc.replaceAll("é", "&#233;");
		desc = desc.replaceAll("ì", "&#236;");
		desc = desc.replaceAll("í", "&#236;");		
		desc = desc.replaceAll("ñ", "&#241;");
		desc = desc.replaceAll("ó", "&#243;");
		desc = desc.replaceAll("ò", "&#242;");
		desc = desc.replaceAll("ù", "&#249;");
		desc = desc.replaceAll("ú", "&#250;");
		desc = desc.replaceAll("ü", "&#252;");
		desc = desc.replaceAll("í", "&#236;");
		
	 	
		return desc;

	}
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
	
	public static String getNameUser( String username, UsersBo usersBo ) throws NoSuchAlgorithmException{

		if(username==null || username.equals("anonymousUser")){
			return "";
		}else{
			Users user = usersBo.findByUsername(username);
			return (user==null || user.getNom()==null)? "": user.getNom();
		}	
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
	
	public static Integer getMonth(String data){
		String[] array = data.split("-");
		if(array.length==3){
			String month= array[1];
			if(month.startsWith("0")){
				return Integer.parseInt(month.substring(1));
			}else{
				return Integer.parseInt(month);
			}
		}
		return 1;
		
	}
	
	public static Integer getDay(String data){
		String[] array = data.split("-");
		if(array.length==3){
			String day= array[2];
			if(day.startsWith("0")){
				return Integer.parseInt(day.substring(1));
			}else{
				return Integer.parseInt(day);
			}
		}
		return 1;
		
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
	
	public static String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\":\"" + error);
		jsonSB.append("\"}");
		return jsonSB.toString();
	}
	
	public static String createNotLogedJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"alertLoged\":\"" + error + "\"");
		jsonSB.append("}");
		return jsonSB.toString();
	}
	
	
	public static String createAlertJSON( String alert ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"alerta\":\"" + alert + "\"");
		jsonSB.append("}");
		return jsonSB.toString();
	}
	
	public static String createErrorJSONForDataTable( String error, String sEcho ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ",\"error\":\"" + error
				+ "\" ,\"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\":  []");
		jsonSB.append("}");
		return jsonSB.toString();
	}
	
	public static String createEmptyJSONForDataTable(String sEcho){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ",\"iTotalRecords\":\"0\", \"iTotalDisplayRecords\":\"0\", \"aaData\":  []");
		jsonSB.append("}");
		return jsonSB.toString();
	}
	
	public static  String createEmptyJSON(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("}");
		return jsonSB.toString();
	}
	
	public static String createJSONForTable( String json, String sEcho ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"1\", \"iTotalDisplayRecords\":\"1\", \"aaData\": [");
		jsonSB.append(json);
		jsonSB.append("]}");
		return jsonSB.toString();
	}
}
