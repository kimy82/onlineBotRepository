package com.online.utils;

import java.util.Comparator;

import com.online.model.Plat;



@SuppressWarnings("rawtypes")
public class ComparatorPlats implements Comparator{

	
	public int compare( Object o1, Object o2 ){

		Plat plat1 = (Plat) o1;
		Plat plat2 = (Plat) o2;
		
		return  plat1.getPrioritat().compareTo(plat2.getPrioritat());
	}

}