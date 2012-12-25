package com.online.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.MotersBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Moters;
import com.online.pojos.Basic;
import com.online.pojos.ConfigMotersTable;
import com.online.pojos.MotersRang;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class MantenimentConfigMotersAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private MotersBo			motersBo;

	private String				dia;
	private Integer				numMoters			= null;
	private Moters				moter				= new Moters();
	private MotersRang			motersRang			= new MotersRang();
	private Date				date;
	private String				hora;

	private List<Basic>			horaList			= new ArrayList<Basic>();

	private String				sEcho;
	private int					lenght				= 0;
	private int					inici				= 0;
	private String				sortDireccio		= null;

	HttpServletResponse			response;
	HttpServletRequest			request;

	public String execute(){

		this.horaList = Utils.getHoraList();
		return SUCCESS;

	}

	public String saveConfigMotersForRang(){

		this.horaList = Utils.getHoraList();
		try {

			if (this.motersRang == null) {
				return ERROR;
			}

			processMoterRang();

		} catch (BOException boe) {
			throw boe;
		} catch (Exception e) {
			throw new GeneralException(e, "Error ");
		}
		return SUCCESS;
	}

	public String saveMoters(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeMotersParams();
			Moters moter = this.motersBo.load(this.hora, this.date);
			if (moter == null) {
				moter = new Moters();
				moter.setData(this.date);
				moter.setHora(this.hora);
				moter.setNumeroMoters(this.numMoters);
				this.motersBo.save(moter);
			} else {
				moter.setNumeroMoters(this.numMoters);
				this.motersBo.update(moter);
			}
			json = "{\"ok\" : \"ok\"}";
		} catch (WrongParamException e) {
			json = createErrorJSON("error in ajax action: wrong params");
		} catch (Exception e) {
			json = createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	public String ajaxTableMotersAction(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableMotersParams();
			json = searchInfoANDcreateJSONForMoters();
		} catch (WrongParamException e) {
			json = createErrorJSON("error in ajax action: wrong params");
		} catch (Exception e) {
			json = createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	// private methods

	private void processMoterRang() throws BOException, NumberFormatException{

		if (this.motersRang.getDiaIni() != null && !this.motersRang.getDiaIni().equals("") && this.motersRang.getDiaFi() != null
				&& !this.motersRang.getDiaFi().equals("") && this.motersRang.getHoraIni() != null && !this.motersRang.getHoraIni().equals("")
				&& this.motersRang.getHoraFi() != null && !this.motersRang.getHoraFi().equals("")) {

			String[] diaIniVec = this.motersRang.getDiaIni().split("-");
			String[] diaFiVec = this.motersRang.getDiaFi().split("-");
			int mesIni = Integer.parseInt(diaIniVec[1]);
			int mesFi = Integer.parseInt(diaFiVec[1]);
			int mesIterator = 0;
			for (mesIterator = mesIni; mesIterator <= mesFi; mesIterator++) {

				int diaIterator = 1;
				int diaIni = Integer.parseInt(diaIniVec[0]);
				int diaFi = Integer.parseInt(diaFiVec[0]);

				for (diaIterator = diaIni; diaIterator <= diaFi; diaIterator++) {

					int horaIni = getWipedHora(this.motersRang.getHoraIni());
					int horaFi = getWipedHora(this.motersRang.getHoraFi());
					int horaIterator = 0;

					for (horaIterator = horaIni; horaIterator <= horaFi; horaIterator++) {

						Date data = Utils.getData(String.valueOf(diaIterator), String.valueOf(mesIterator), diaIniVec[2]);
						Moters moterEnPunt = new Moters();
						moterEnPunt.setData(data);
						String horaToSave = getHoraEnPunt(horaIterator);
						moterEnPunt.setHora(horaToSave);
						moterEnPunt.setNumeroMoters(this.motersRang.getNumMoters());
						saveMoter(moterEnPunt);
						Moters moterIMitja = new Moters();
						moterIMitja.setData(data);
						String horaToSaveIMitja = getHoraIMitja(horaIterator);
						moterIMitja.setHora(horaToSaveIMitja);
						moterIMitja.setNumeroMoters(this.motersRang.getNumMoters());
						saveMoter(moterIMitja);

					}

				}
			}
		}

	}

	private String getHoraEnPunt( int horaIterator ){

		String horaIteraString = String.valueOf(horaIterator);
		if (horaIteraString.length() == 1) {
			horaIteraString = "0" + horaIteraString + "00";
		} else {
			horaIteraString = horaIteraString + "00";
		}
		return horaIteraString;
	}

	private String getHoraIMitja( int horaIterator ){

		String horaIteraString = String.valueOf(horaIterator);
		if (horaIteraString.length() == 1) {
			horaIteraString = "0" + horaIteraString + "30";
		} else {
			horaIteraString = horaIteraString + "30";
		}
		return horaIteraString;
	}

	private void saveMoter( Moters moterTosave ) throws BOException{

		try {
			Moters moter = this.motersBo.load(moterTosave.getHora(), moterTosave.getData());
			if (moter == null) {
				this.motersBo.save(moterTosave);
			} else {
				moter.setNumeroMoters(numMoters);
				this.motersBo.update(moter);
			}

		} catch (BOException e) {
			throw e;
		}

	}

	private Integer getWipedHora( String hora ){

		String wipeHora = "0";
		String[] horaVec = hora.split(":");
		if (horaVec.length == 2) {
			String horaWithZero = horaVec[0];
			if (horaWithZero.startsWith("0")) {
				wipeHora = horaWithZero.substring(1, 2);
			} else {
				wipeHora = horaWithZero;
			}
		}
		return Integer.parseInt(wipeHora);
	}

	private void inizializeMotersParams() throws NumberFormatException, WrongParamException{

		this.numMoters = request.getParameter("num") != null && !request.getParameter("num").equals("") ? Integer.parseInt(request
				.getParameter("num")) : null;
		String horaDia = request.getParameter("id");
		String[] horaDiaVec = horaDia.split("_");
		if (horaDiaVec.length != 2) {
			throw new WrongParamException("Hora o dia mal informats");
		} else {
			this.date = getDate(horaDiaVec[1]);
			this.hora = horaDiaVec[0];
		}
	}

	private String searchInfoANDcreateJSONForMoters(){

		List<Moters> motersForDate = this.motersBo.load(getDate(this.dia));
		ConfigMotersTable configMotersTable = new ConfigMotersTable(this.dia);
		configMotersTable.setDia(this.dia);
		if (motersForDate == null || motersForDate.isEmpty()) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			return createJSONForTable(gson.toJson(configMotersTable));
		}
		for (Moters moter : motersForDate) {
			if (moter.getHora().equals("0800")) {
				configMotersTable.setH0800("<input type=\"text\" id=\"0800_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('0800_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("0830")) {
				configMotersTable.setH0830("<input type=\"text\" id=\"0830_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('0830_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("0900")) {
				configMotersTable.setH0900("<input type=\"text\" id=\"0900_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('0900_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("0930")) {
				configMotersTable.setH0930("<input type=\"text\" id=\"0930_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('0930_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1000")) {
				configMotersTable.setH1000("<input type=\"text\" id=\"1000_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1000_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1030")) {
				configMotersTable.setH1030("<input type=\"text\" id=\"1030_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1030_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1100")) {
				configMotersTable.setH1100("<input type=\"text\" id=\"1100_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1100_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1130")) {
				configMotersTable.setH1130("<input type=\"text\" id=\"1130_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('1130_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1200")) {
				configMotersTable.setH1200("<input type=\"text\" id=\"1200_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"/> <a href=\"#\" onclick=\"saveMoters('1200_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1230")) {
				configMotersTable.setH1230("<input type=\"text\" id=\"1230_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1230_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1300")) {
				configMotersTable.setH1300("<input type=\"text\" id=\"1300_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"/><a href=\"#\" onclick=\"saveMoters('1300_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1330")) {
				configMotersTable.setH1330("<input type=\"text\" id=\"1330_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1330_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1400")) {
				configMotersTable.setH1400("<input type=\"text\" id=\"1400_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1400_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1430")) {
				configMotersTable.setH1430("<input type=\"text\" id=\"1430_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('1430_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1500")) {
				configMotersTable.setH1500("<input type=\"text\" id=\"1500_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1500_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1530")) {
				configMotersTable.setH1530("<input type=\"text\" id=\"1530_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1530_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1600")) {
				configMotersTable.setH1600("<input type=\"text\" id=\"1600_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /> <a href=\"#\" onclick=\"saveMoters('1600_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1630")) {
				configMotersTable.setH1630("<input type=\"text\" id=\"1630_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"/> <a href=\"#\" onclick=\"saveMoters('1630_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/> ");
			} else if (moter.getHora().equals("1700")) {
				configMotersTable.setH1700("<input type=\"text\" id=\"1700_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1700_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1730")) {
				configMotersTable.setH1730("<input type=\"text\" id=\"1730_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('1730_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1800")) {
				configMotersTable.setH1800("<input type=\"text\" id=\"1800_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('1800_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1830")) {
				configMotersTable.setH1830("<input type=\"text\" id=\"1830_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('1830_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1900")) {
				configMotersTable.setH1900("<input type=\"text\" id=\"1900_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('1900_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("1930")) {
				configMotersTable.setH1930("<input type=\"text\" id=\"1930_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('1930_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2000")) {
				configMotersTable.setH2000("<input type=\"text\" id=\"2000_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('2000_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2030")) {
				configMotersTable.setH2030("<input type=\"text\" id=\"2030_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('2030_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2100")) {
				configMotersTable.setH2100("<input type=\"text\" id=\"2100_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('2100_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2130")) {
				configMotersTable.setH2130("<input type=\"text\" id=\"2130_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"/><a href=\"#\" onclick=\"saveMoters('2130_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2200")) {
				configMotersTable.setH2200("<input type=\"text\" id=\"2200_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('2200_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2230")) {
				configMotersTable.setH2230("<input type=\"text\" id=\"2230_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('2230_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2300")) {
				configMotersTable.setH2300("<input type=\"text\" id=\"2300_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\"  /><a href=\"#\" onclick=\"saveMoters('2300_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2330")) {
				configMotersTable.setH2330("<input type=\"text\" id=\"2330_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('2330_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			} else if (moter.getHora().equals("2400")) {
				configMotersTable.setH2400("<input type=\"text\" id=\"2400_" + this.dia + "\" value=\"" + moter.getNumeroMoters()
						+ "\" style=\"width: 20px;\" /><a href=\"#\" onclick=\"saveMoters('2400_" + this.dia
						+ "');\"><img src=\"../admin/images/save.png\" /><a/>");
			}
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return createJSONForTable(gson.toJson(configMotersTable));

	}

	private String createJSONForTable( String json ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"1\", \"iTotalDisplayRecords\":\"1\", \"aaData\": [");
		jsonSB.append(json);
		jsonSB.append("]}");
		return jsonSB.toString();
	}

	private void inizializeTableMotersParams() throws WrongParamException{

		this.dia = this.request.getParameter("dia") == null || this.request.getParameter("dia").equals("") ? null : this.request
				.getParameter("dia");
		this.sEcho = request.getParameter("sEcho");
		this.lenght = (request.getParameter("iDisplayLength") == null) ? 10 : Integer.parseInt(request.getParameter("iDisplayLength"));
		this.inici = (request.getParameter("iDisplayStart") == null) ? 0 : Integer.parseInt(request.getParameter("iDisplayStart"));
		this.sortDireccio = request.getParameter("sSortDir_0");
		if (this.sortDireccio == null)
			this.sortDireccio = "ASC";

		if (this.dia == null) {
			throw new WrongParamException("No hi ha dia");
		}
	}

	private String createErrorJSON( String error ){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"error\":\"" + error + "\"");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	private Date getDate( String dia ) throws RuntimeException{

		String[] diaS = dia.split("-");
		if (diaS.length != 3) {
			throw new RuntimeException("wrong dia");
		}

		String numDia = diaS[0];
		String month = diaS[1];
		String year = diaS[2];

		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(year), (Integer.parseInt(month)-1), Integer.parseInt(numDia));
		return calendar.getTime();

	}

	private String createEmptyJSON(){

		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("}");
		return jsonSB.toString();
	}

	// Getters i setters

	public void setMotersBo( MotersBo motersBo ){

		this.motersBo = motersBo;
	}

	public String getDia(){

		return dia;
	}

	public void setDia( String dia ){

		this.dia = dia;
	}

	public void setMoter( Moters moter ){

		this.moter = moter;
	}

	public void setServletResponse( HttpServletResponse response ){

		this.response = response;
	}

	public HttpServletResponse getServletResponse(){

		return this.response;
	}

	public void setServletRequest( HttpServletRequest request ){

		this.request = request;
	}

	public HttpServletRequest getServletRequest(){

		return this.request;
	}

	public MotersRang getMotersRang(){

		return motersRang;
	}

	public void setMotersRang( MotersRang motersRang ){

		this.motersRang = motersRang;
	}

	public List<Basic> getHoraList(){

		return horaList;
	}

	public void setHoraList( List<Basic> horaList ){

		this.horaList = horaList;
	}

}