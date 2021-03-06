package com.online.action.users;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.online.bo.BegudaBo;
import com.online.bo.ComandaBo;
import com.online.bo.PromocionsBo;
import com.online.bo.RestaurantsBo;
import com.online.bo.UsersBo;
import com.online.bo.VotacionsBo;
import com.online.exceptions.BOException;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Beguda;
import com.online.model.Comandes;
import com.online.model.HoresDTO;
import com.online.model.Plat;
import com.online.model.PlatComanda;
import com.online.model.PromocioAPartirDe;
import com.online.model.PromocioAssociada;
import com.online.model.PromocioNumComandes;
import com.online.model.Restaurant;
import com.online.model.Users;
import com.online.model.VotacioTMP;
import com.online.pojos.Basic;
import com.online.pojos.BasicSub;
import com.online.pojos.ComandesUserTable;
import com.online.services.impl.ComandaServiceImpl;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;

public class WelcomeUserAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private ComandaBo			comandaBo;
	private UsersBo				usersBo;
	private PromocionsBo		promocionsBo;
	private BegudaBo			begudaBo;
	private VotacionsBo			votacionsBo;
	private RestaurantsBo		restaurantsBo;
	private List<Restaurant>	restaurantList;
	private ComandaServiceImpl	comandaService;
	private Users				user;
	private Long				idComanda			= null;
	private Comandes			comanda				= null;
	private HoresDTO			horesDTO;
	private String				nameUser;

	private int					numComandes			= 0;
	private String				data;
	private String				recoveredComanda	= "true";
	private int					numPlats			= 0;
	private int					numBegudes			= 0;

	private List<Basic>			horaList			= new ArrayList<Basic>();
	List<Comandes>				ComandaList			= new ArrayList<Comandes>();
	List<PromocioAPartirDe>		promoListAPartirDe	= new ArrayList<PromocioAPartirDe>();
	List<PromocioNumComandes>	promocioNumComandes	= new ArrayList<PromocioNumComandes>();
	PromocioAssociada	    	promocioAssociada	= new PromocioAssociada();
	private List<BasicSub>		refrescList			= new ArrayList<BasicSub>();
	List<PlatComanda>			platComandaList		= new ArrayList<PlatComanda>();
	List<Plat>					platListToVote		= new ArrayList<Plat>();

	public String execute(){
		
		setAuthenticationUser();
		
		setUserName();
		
		return SUCCESS;

	}

	public String comandesPasades(){
		
		
		this.user = getUserFromContext();		
		setUserName();
		this.promoListAPartirDe = this.promocionsBo.getAllAPartirDe();
		this.promocioNumComandes = this.promocionsBo.getAllNumComandes();
		this.restaurantList = this.restaurantsBo.getAll(true, false, false);
		
		if(this.user.getCodePromo()!=null && !this.user.getCodePromo().equals("")){
			try{
				this.promocioAssociada = this.promocionsBo.loadAssociadaByCode(this.user.getCodePromo()).get(0);
			}catch(Exception e){
				this.promocioAssociada = null;
			}
		}
		return SUCCESS;

	}

	public String saveUserDetails(){

		try {
			Users userFromDB = this.usersBo.load(this.user.getId());
			if (this.user.getPassword() != null && !this.user.getPassword().equals("")){
				String hashNewPassword = Utils.createSHA(this.user.getPassword());
				if(!hashNewPassword.equals(userFromDB.getPassword()))
					userFromDB.setPassword(hashNewPassword);
			}
				
			userFromDB.setAddress(this.user.getAddress());
			userFromDB.setNom(this.user.getNom());
			userFromDB.setIndicacions(this.user.getIndicacions());
			userFromDB.setTelNumber(this.user.getTelNumber());
			this.usersBo.update(userFromDB);

		} catch (BOException e) {
			return ERROR;
		} catch (NoSuchAlgorithmException e) {
			return ERROR;
		}
		return SUCCESS;

	}

	public String repeatComanda(){

		try {

			inicializeIdComanda();
			inizializeData();
			getUserAllInfoFromContext();

			Comandes comandaDone = this.comandaBo.load(this.idComanda);

			this.comanda = this.comandaService.getComandaToRepeat(comandaDone);

			this.comandaBo.save(comanda);

			horesDTO = new HoresDTO();
			horesDTO.setData(data);
			horesDTO = this.comandaService.setHoresFeature(horesDTO, this.data, this.comanda, false,0);

			setAuthenticationUser();

			List<Beguda> begudaList = this.begudaBo.getAll();
			for (Beguda beguda : begudaList) {

				BasicSub basic = new BasicSub(((beguda.getFoto() != null) ? beguda.getFoto().getId() : 0), beguda.getNom());
				basic.setIdSub(beguda.getId());
				basic.setTipus(beguda.getTipus());
				this.refrescList.add(basic);

			}

			this.platComandaList = comanda.getPlats();

			this.numPlats = this.comandaService.getNumPlats(this.platComandaList);

			return SUCCESS;

		} catch (Exception e) {
			return ERROR;
		}

	}

	public String votaUserPlat(){

		try {

			inicializeIdComanda();
			getUserAllInfoFromContext();

			this.platListToVote.clear();

			Comandes comanda = this.comandaBo.load(this.idComanda);
			this.platComandaList = comanda.getPlats();

			for (PlatComanda platComanda : this.platComandaList) {

				VotacioTMP votTMP = this.votacionsBo.getLast(platComanda.getPlat().getId(), this.user.getId());
				if (votTMP.getDia().before(comanda.getDia())) {
					addAsPlatToVote(platComanda);
				}
			}

			return SUCCESS;

		} catch (Exception e) {
			return ERROR;
		}

	}

	public String checkComanda(){

		try {

		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;

	}

	public String ajaxTableComandesUser(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeTableParams();
			json = getInfoAndCreateJSONForComandesUser();
		} catch (BOException boe) {
			json = Utils.createErrorJSONForDataTable("error in ajax action: Error in BO", this.sEcho);
		} catch (NumberFormatException e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action: wrong params", this.sEcho);
		} catch (Exception e) {
			json = Utils.createErrorJSONForDataTable("error in ajax action", this.sEcho);
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	// private methods
	private void setUserName(){
		
		try{
			
			this.nameUser = Utils.getNameUser(nameAuth, usersBo);
			
		}catch(Exception e){
			this.nameUser="";
		}
		
	}
	private void getUserAllInfoFromContext(){

		setAuthenticationUser();
		if (!this.nameAuth.equals("anonymousUser")) {
			this.user = this.usersBo.findByUsername(this.nameAuth);
		} else {
			this.user = null;
		}
	}

	private void addAsPlatToVote( PlatComanda platComanda ){

		this.platListToVote.add(platComanda.getPlat());

	}

	private void inizializeData() throws WrongParamException{

		this.data = Utils.formatDate2(new Date());

	}

	private void inicializeIdComanda(){

		try {
			this.idComanda = (request.getParameter("idComanda") != null && !request.getParameter("idComanda").equals("")) ? Long
					.parseLong(request.getParameter("idComanda")) : null;
		} catch (NumberFormatException nfe) {
			throw new WrongParamException("Id comanda wrong");
		}
	}

	public String getInfoAndCreateJSONForComandesUser(){

		List<Comandes> ComandaList = getComandesUserListAndSetNum();
		List<ComandesUserTable> comandaTableList = new ArrayList<ComandesUserTable>();
		for (Comandes comanda : ComandaList) {
			ComandesUserTable cmdUserTable = new ComandesUserTable();
			BeanUtils.copyProperties(comanda, cmdUserTable);
			if (cmdUserTable.getObservacions() == null)
				cmdUserTable.setObservacions("");
			cmdUserTable.setPlatsString(Utils.escapeUTF(getNomPLats(comanda)));
			cmdUserTable.setLinks(getLinksVotacions(comanda));
			cmdUserTable.setAccio("<a href=\"#\" onclick=\"repeatComanda(" + comanda.getId()
					+ ")\" ><img src=\"../images/shopping_cart.png\"></a>");
			comandaTableList.add(cmdUserTable);
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(comandaTableList);
		StringBuffer jsonSB = new StringBuffer("{");
		jsonSB.append("\"sEcho\": " + sEcho + ", \"iTotalRecords\":\"" + this.numComandes + "\", \"iTotalDisplayRecords\":\""
				+ this.numComandes + "\", \"aaData\":  ");
		jsonSB.append(json);
		jsonSB.append("}");

		return jsonSB.toString();

	}

	private String getNomPLats( Comandes comanda ){

		StringBuffer nomPlats = new StringBuffer("");
		for (PlatComanda platComanda : comanda.getPlats()) {
			nomPlats.append(platComanda.getPlat().getNom() + "<br>");
		}
		if (nomPlats.length() > 4)
			nomPlats.setLength(nomPlats.length() - 4);

		return nomPlats.toString();
	}

	private String getLinksVotacions( Comandes comanda ){

		StringBuffer nomPlats = new StringBuffer("");
		String app =this.request.getSession().getServletContext().getInitParameter("app");
		for (PlatComanda platComanda : comanda.getPlats()) {
			nomPlats.append("<a href='#' onclick='goToVotarPlat(" + platComanda.getPlat().getId()
					+ ")' ><img  src='/"+app+"/images/vota.png' /></a><br>");
		}
		if (nomPlats.length() != 0)
			nomPlats.setLength(nomPlats.length() - 4);

		return nomPlats.toString();
	}

	private List<Comandes> getComandesUserListAndSetNum(){

		Users user = getUserFromContext();
		List<Comandes> comandeslist = this.comandaBo.getAllByUser(user.getId(), true);
		List<Comandes> subComandaList = new ArrayList<Comandes>();
		if (!comandeslist.isEmpty()) {
			this.numComandes = comandeslist.size();
			subComandaList = comandeslist.subList(inici, ((inici + lenght) < comandeslist.size()) ? (inici + lenght) : comandeslist.size());
		}
		return subComandaList;
	}

	private Users getUserFromContext(){

		setAuthenticationUser();
		return this.usersBo.findByUsername(this.nameAuth);

	}

	// GETERS I SETTERS
	public List<Comandes> getComandaList(){

		return ComandaList;
	}

	public void setComandaList( List<Comandes> comandaList ){

		ComandaList = comandaList;
	}

	public void setComandaBo( ComandaBo comandaBo ){

		this.comandaBo = comandaBo;
	}

	public Comandes getComanda(){

		return comanda;
	}

	public void setComanda( Comandes comanda ){

		this.comanda = comanda;
	}

	public Users getUser(){

		return user;
	}

	public void setUser( Users user ){

		this.user = user;
	}

	public List<Basic> getHoraList(){

		return horaList;
	}

	public void setHoraList( List<Basic> horaList ){

		this.horaList = horaList;
	}

	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

	public void setPromocionsBo( PromocionsBo promocionsBo ){

		this.promocionsBo = promocionsBo;
	}

	public List<PromocioAPartirDe> getPromoListAPartirDe(){

		return promoListAPartirDe;
	}

	public void setPromoListAPartirDe( List<PromocioAPartirDe> promoListAPartirDe ){

		this.promoListAPartirDe = promoListAPartirDe;
	}

	public List<PromocioNumComandes> getPromocioNumComandes(){

		return promocioNumComandes;
	}

	public void setPromocioNumComandes( List<PromocioNumComandes> promocioNumComandes ){

		this.promocioNumComandes = promocioNumComandes;
	}

	public void setComandaService( ComandaServiceImpl comandaService ){

		this.comandaService = comandaService;
	}

	public void setBegudaBo( BegudaBo begudaBo ){

		this.begudaBo = begudaBo;
	}

	public HoresDTO getHoresDTO(){

		return horesDTO;
	}

	public void setHoresDTO( HoresDTO horesDTO ){

		this.horesDTO = horesDTO;
	}

	public List<BasicSub> getRefrescList(){

		return refrescList;
	}

	public void setRefrescList( List<BasicSub> refrescList ){

		this.refrescList = refrescList;
	}

	public List<PlatComanda> getPlatComandaList(){

		return platComandaList;
	}

	public void setPlatComandaList( List<PlatComanda> platComandaList ){

		this.platComandaList = platComandaList;
	}

	public String getRecoveredComanda(){

		return recoveredComanda;
	}

	public void setRecoveredComanda( String recoveredComanda ){

		this.recoveredComanda = recoveredComanda;
	}

	public Long getIdComanda(){

		return idComanda;
	}

	public void setIdComanda( Long idComanda ){

		this.idComanda = idComanda;
	}

	public int getNumPlats(){

		return numPlats;
	}

	public void setNumPlats( int numPlats ){

		this.numPlats = numPlats;
	}

	public int getNumBegudes(){

		return numBegudes;
	}

	public void setNumBegudes( int numBegudes ){

		this.numBegudes = numBegudes;
	}

	public List<Plat> getPlatListToVote(){

		return platListToVote;
	}

	public void setPlatListToVote( List<Plat> platListToVote ){

		this.platListToVote = platListToVote;
	}

	public void setVotacionsBo( VotacionsBo votacionsBo ){

		this.votacionsBo = votacionsBo;
	}

	public PromocioAssociada getPromocioAssociada() {
		return promocioAssociada;
	}

	public void setPromocioAssociada(PromocioAssociada promocioAssociada) {
		this.promocioAssociada = promocioAssociada;
	}

	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}

	public void setRestaurantsBo(RestaurantsBo restaurantsBo) {
		this.restaurantsBo = restaurantsBo;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}		
	
}