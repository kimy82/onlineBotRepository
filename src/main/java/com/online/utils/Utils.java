package com.online.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
}
