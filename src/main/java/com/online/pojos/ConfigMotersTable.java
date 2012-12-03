package com.online.pojos;

import com.google.gson.annotations.Expose;

public class ConfigMotersTable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Expose
	private String				dia					= "";

	@Expose
	private String				h0800				= "0";

	@Expose
	private String				h0830				= "0";

	@Expose
	private String				h0900				= "0";

	@Expose
	private String				h0930				= "0";

	@Expose
	private String				h1000				= "0";

	@Expose
	private String				h1030				= "0";

	@Expose
	private String				h1100				= "0";

	@Expose
	private String				h1130				= "0";

	@Expose
	private String				h1200				= "0";

	@Expose
	private String				h1230				= "0";

	@Expose
	private String				h1300				= "0";

	@Expose
	private String				h1330				= "0";

	@Expose
	private String				h1400				= "0";

	@Expose
	private String				h1430				= "0";

	@Expose
	private String				h1500				= "0";

	@Expose
	private String				h1530				= "0";

	@Expose
	private String				h1600				= "0";

	@Expose
	private String				h1630				= "0";

	@Expose
	private String				h1700				= "0";

	@Expose
	private String				h1730				= "0";

	@Expose
	private String				h1800				= "0";

	@Expose
	private String				h1830				= "0";

	@Expose
	private String				h1900				= "0";

	@Expose
	private String				h1930				= "0";

	@Expose
	private String				h2000				= "0";

	@Expose
	private String				h2030				= "0";

	@Expose
	private String				h2100				= "0";

	@Expose
	private String				h2130				= "0";

	@Expose
	private String				h2200				= "0";

	@Expose
	private String				h2230				= "0";

	@Expose
	private String				h2300				= "0";

	@Expose
	private String				h2330				= "0";

	@Expose
	private String				h2400				= "0";

	// CONSTRUCTORS
	public ConfigMotersTable(String dia) {
		this.dia=dia;
		this.h0800="<input type=\"text\" style=\"width: 20px;\" id=\"0800_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('0800_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h0830="<input type=\"text\" style=\"width: 20px;\" id=\"0830_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('0830_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h0900="<input type=\"text\" style=\"width: 20px;\" id=\"0900_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('0900_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h0930="<input type=\"text\" style=\"width: 20px;\" id=\"0930_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('0930_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1000="<input type=\"text\" style=\"width: 20px;\" id=\"1000_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1000_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1030="<input type=\"text\" style=\"width: 20px;\" id=\"1030_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1030_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1100="<input type=\"text\" style=\"width: 20px;\" id=\"1100_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1100_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1130="<input type=\"text\" style=\"width: 20px;\" id=\"1130_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1130_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1200="<input type=\"text\" style=\"width: 20px;\" id=\"1200_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1200_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1230="<input type=\"text\" style=\"width: 20px;\" id=\"1230_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1230_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1300="<input type=\"text\" style=\"width: 20px;\" id=\"1300_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1300_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1330="<input type=\"text\" style=\"width: 20px;\" id=\"1330_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1330_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1400="<input type=\"text\" style=\"width: 20px;\" id=\"1400_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1400_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1430="<input type=\"text\" style=\"width: 20px;\" id=\"1430_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1430_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1500="<input type=\"text\" style=\"width: 20px;\" id=\"1500_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1500_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1530="<input type=\"text\" style=\"width: 20px;\" id=\"1530_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1530_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1600="<input type=\"text\" style=\"width: 20px;\" id=\"1600_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1600_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1630="<input type=\"text\" style=\"width: 20px;\" id=\"1630_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1630_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1700="<input type=\"text\" style=\"width: 20px;\" id=\"1700_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1700_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1730="<input type=\"text\" style=\"width: 20px;\" id=\"1730_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1730_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1800="<input type=\"text\" style=\"width: 20px;\" id=\"1800_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1800_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1830="<input type=\"text\" style=\"width: 20px;\" id=\"1830_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1830_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1900="<input type=\"text\" style=\"width: 20px;\" id=\"1900_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('1900_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h1930="<input type=\"text\" style=\"width: 20px;\" id=\"1930_"+this.dia+"\" value=\"0\"  /><a href=\"#\" onclick=\"saveMoters('1930_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2000="<input type=\"text\" style=\"width: 20px;\" id=\"2000_"+this.dia+"\" value=\"0\"  /><a href=\"#\" onclick=\"saveMoters('2000_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2030="<input type=\"text\" style=\"width: 20px;\" id=\"2030_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('2030_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2100="<input type=\"text\" style=\"width: 20px;\" id=\"2100_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('2100_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2130="<input type=\"text\" style=\"width: 20px;\" id=\"2130_"+this.dia+"\" value=\"0\"  /><a href=\"#\" onclick=\"saveMoters('2130_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2200="<input type=\"text\" style=\"width: 20px;\" id=\"2200_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('2200_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2230="<input type=\"text\" style=\"width: 20px;\" id=\"2230_"+this.dia+"\" value=\"0\" /><a href=\"#\" onclick=\"saveMoters('2230_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2300="<input type=\"text\" style=\"width: 20px;\" id=\"2300_"+this.dia+"\" value=\"0\"  /><a href=\"#\" onclick=\"saveMoters('2300_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2330="<input type=\"text\" style=\"width: 20px;\" id=\"2330_"+this.dia+"\" value=\"0\"  /><a href=\"#\" onclick=\"saveMoters('2330_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
		this.h2400="<input type=\"text\" style=\"width: 20px;\" id=\"2400_"+this.dia+"\" value=\"0\"  /><a href=\"#\" onclick=\"saveMoters('2400_"+this.dia+"');\"><img src=\"../admin/images/save.png\" /><a/>";
	}

	// GETTERS i SETTERS

	public String getDia(){

		return dia;
	}

	public void setDia( String dia ){

		this.dia = dia;
	}
	
	public String getH0800(){
	
		return h0800;
	}

	public void setH0800( String h0800 ){
	
		this.h0800 = h0800;
	}

	public String getH0830(){
	
		return h0830;
	}

	public void setH0830( String h0830 ){
	
		this.h0830 = h0830;
	}

	public String getH0900(){
	
		return h0900;
	}

	public void setH0900( String h0900 ){
	
		this.h0900 = h0900;
	}

	public String getH0930(){
	
		return h0930;
	}

	public void setH0930( String h0930 ){
	
		this.h0930 = h0930;
	}

	public String getH1000(){

		return h1000;
	}

	public void setH1000( String h1000 ){

		this.h1000 = h1000;
	}

	public String getH1030(){

		return h1030;
	}

	public void setH1030( String h1030 ){

		this.h1030 = h1030;
	}

	public String getH1100(){

		return h1100;
	}

	public void setH1100( String h1100 ){

		this.h1100 = h1100;
	}

	public String getH1130(){

		return h1130;
	}

	public void setH1130( String h1130 ){

		this.h1130 = h1130;
	}

	public String getH1200(){

		return h1200;
	}

	public void setH1200( String h1200 ){

		this.h1200 = h1200;
	}

	public String getH1230(){

		return h1230;
	}

	public void setH1230( String h1230 ){

		this.h1230 = h1230;
	}

	public String getH1300(){

		return h1300;
	}

	public void setH1300( String h1300 ){

		this.h1300 = h1300;
	}

	public String getH1330(){

		return h1330;
	}

	public void setH1330( String h1330 ){

		this.h1330 = h1330;
	}

	public String getH1400(){

		return h1400;
	}

	public void setH1400( String h1400 ){

		this.h1400 = h1400;
	}

	public String getH1430(){

		return h1430;
	}

	public void setH1430( String h1430 ){

		this.h1430 = h1430;
	}

	public String getH1500(){

		return h1500;
	}

	public void setH1500( String h1500 ){

		this.h1500 = h1500;
	}

	public String getH1530(){

		return h1530;
	}

	public void setH1530( String h1530 ){

		this.h1530 = h1530;
	}

	public String getH1600(){

		return h1600;
	}

	public void setH1600( String h1600 ){

		this.h1600 = h1600;
	}

	public String getH1630(){

		return h1630;
	}

	public void setH1630( String h1630 ){

		this.h1630 = h1630;
	}

	public String getH1700(){

		return h1700;
	}

	public void setH1700( String h1700 ){

		this.h1700 = h1700;
	}

	public String getH1730(){

		return h1730;
	}

	public void setH1730( String h1730 ){

		this.h1730 = h1730;
	}

	public String getH1800(){

		return h1800;
	}

	public void setH1800( String h1800 ){

		this.h1800 = h1800;
	}

	public String getH1830(){

		return h1830;
	}

	public void setH1830( String h1830 ){

		this.h1830 = h1830;
	}

	public String getH1900(){

		return h1900;
	}

	public void setH1900( String h1900 ){

		this.h1900 = h1900;
	}

	public String getH1930(){

		return h1930;
	}

	public void setH1930( String h1930 ){

		this.h1930 = h1930;
	}

	public String getH2000(){

		return h2000;
	}

	public void setH2000( String h2000 ){

		this.h2000 = h2000;
	}

	public String getH2030(){

		return h2030;
	}

	public void setH2030( String h2030 ){

		this.h2030 = h2030;
	}

	public String getH2100(){

		return h2100;
	}

	public void setH2100( String h2100 ){

		this.h2100 = h2100;
	}

	public String getH2130(){

		return h2130;
	}

	public void setH2130( String h2130 ){

		this.h2130 = h2130;
	}

	public String getH2200(){

		return h2200;
	}

	public void setH2200( String h2200 ){

		this.h2200 = h2200;
	}

	public String getH2230(){

		return h2230;
	}

	public void setH2230( String h2230 ){

		this.h2230 = h2230;
	}

	public String getH2300(){

		return h2300;
	}

	public void setH2300( String h2300 ){

		this.h2300 = h2300;
	}

	public String getH2330(){

		return h2330;
	}

	public void setH2330( String h2330 ){

		this.h2330 = h2330;
	}

	public String getH2400(){

		return h2400;
	}

	public void setH2400( String h2400 ){

		this.h2400 = h2400;
	}

}
