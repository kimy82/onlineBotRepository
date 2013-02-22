package com.online.pojos;

import java.util.Comparator;

import com.google.gson.annotations.Expose;


public class AllComandesTable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String nom;
	
	@Expose
	private String telefon;
	
	@Expose
	private String address;
	
	@Expose
	private String hora;
	
	@Expose
	private String preu;
	
	@Expose
	private String plats;
	
	@Expose
	private String restaurant;
	
	@Expose
	private String metodePagament;
	
	
	
	public AllComandesTable() {	
	}
	
	public AllComandesTable( String nom, String telefon, String address, String hora, String preu, String restaurant, String metodePagament, String plats) {

		this.nom = nom;
		this.telefon = telefon;
		this.address = address;
		this.hora = hora;
		this.preu = preu;
		this.restaurant = restaurant;	
		this.metodePagament= metodePagament;
		this.plats=plats;
		
	}
	
	public String getNom(){
	
		return nom;
	}
	public void setNom( String nom ){
	
		this.nom = nom;
	}
	public String getTelefon(){
	
		return telefon;
	}
	public void setTelefon( String telefon ){
	
		this.telefon = telefon;
	}
	public String getAddress(){
	
		return address;
	}
	public void setAddress( String address ){
	
		this.address = address;
	}
	public String getHora(){
	
		return hora;
	}
	public void setHora( String hora ){
	
		this.hora = hora;
	}
	public String getPreu(){
	
		return preu;
	}
	public void setPreu( String preu ){
	
		this.preu = preu;
	}

	public String getRestaurant(){
	
		return restaurant;
	}

	public void setRestaurant( String restaurant ){
	
		this.restaurant = restaurant;
	}

	public String getMetodePagament(){
	
		return metodePagament;
	}

	public void setMetodePagament( String metodePagament ){
	
		this.metodePagament = metodePagament;
	}		
	
	public String getPlats(){
	
		return plats;
	}

	public void setPlats( String plats ){
	
		this.plats = plats;
	}



	public static Comparator<AllComandesTable> nameComparator  = new Comparator<AllComandesTable>() {

		public int compare(AllComandesTable cmd1, AllComandesTable cmd2) {
		
			String name1 = cmd1.getNom().toUpperCase();
			String name2 = cmd2.getNom().toUpperCase();
			
			//ascending order
			return name1.compareTo(name2);
			
			//descending order
			//return fruitName2.compareTo(fruitName1);
		}

	};
	
	public static Comparator<AllComandesTable> telComparator  = new Comparator<AllComandesTable>() {

		public int compare(AllComandesTable cmd1, AllComandesTable cmd2) {
		
			String name1 = cmd1.getTelefon();
			String name2 = cmd2.getTelefon();
			if(name1==null)name1="-";
			if(name2==null)name2="-";
			//ascending order
			return name1.compareTo(name2);			
		}

	};
	
	public static Comparator<AllComandesTable> horaComparator  = new Comparator<AllComandesTable>() {

		public int compare(AllComandesTable cmd1, AllComandesTable cmd2) {
		
			String name1 = cmd1.getHora();
			String name2 = cmd2.getHora();
			
			//ascending order
			return name1.compareTo(name2);			
		}
	};
	
	public static Comparator<AllComandesTable> preuComparator  = new Comparator<AllComandesTable>() {

		public int compare(AllComandesTable cmd1, AllComandesTable cmd2) {
		
			String name1 = cmd1.getPreu();
			String name2 = cmd2.getPreu();
			
			//ascending order
			return name1.compareTo(name2);			
		}
	};

	public static Comparator<AllComandesTable> restaurantComparator  = new Comparator<AllComandesTable>() {

		public int compare(AllComandesTable cmd1, AllComandesTable cmd2) {
		
			String name1 = cmd1.getRestaurant();
			String name2 = cmd2.getRestaurant();
			
			//ascending order
			return name1.compareTo(name2);			
		}
	};
	
	public static Comparator<AllComandesTable> metodeComparator  = new Comparator<AllComandesTable>() {

		public int compare(AllComandesTable cmd1, AllComandesTable cmd2) {
		
			String name1 = cmd1.getMetodePagament();
			String name2 = cmd2.getMetodePagament();
			
			//ascending order
			return name1.compareTo(name2);			
		}
	};
}
