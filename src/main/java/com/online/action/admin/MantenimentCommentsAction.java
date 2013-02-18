package com.online.action.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.online.bo.BegudaBo;
import com.online.bo.PlatsBo;
import com.online.exceptions.WrongParamException;
import com.online.model.Beguda;
import com.online.model.Foro;
import com.online.model.ForoBeguda;
import com.online.model.Plat;
import com.online.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;

public class MantenimentCommentsAction extends ActionSupport implements ServletResponseAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private PlatsBo				platsBo;
	private BegudaBo			begudaBo;

	private Long				idPlat;
	private Long				idBeguda;
	private Long				idComment;

	private Plat				plat;
	private Beguda				beguda;
	private List<Plat>			listPlats;
	private List<Beguda>		listBegudes;
	HttpServletResponse			response;
	HttpServletRequest			request;

	public String execute(){

		this.listPlats = this.platsBo.getAll();
		this.listBegudes= this.begudaBo.getAll("vi", false);
		
		return SUCCESS;
	}

	public String loadComments(){

		inizializePlatId();
		this.listPlats = this.platsBo.getAll();
		this.listBegudes= this.begudaBo.getAll("vi", false);
		this.plat = this.platsBo.loadPLatAndForos(this.idPlat);
		return SUCCESS;

	}
	
	public String loadCommentsBeguda(){

		inizializeBegudaId();
		this.listPlats = this.platsBo.getAll();
		this.listBegudes= this.begudaBo.getAll("vi", false);
		this.beguda = this.begudaBo.loadBegudaAndForos(this.idBeguda);
		return SUCCESS;

	}

	public String ajaxDeleteCommentAction(){

		try {
			inizializePlatId();
			inizializeCommentId();
			this.plat = this.platsBo.loadPLatAndForos(this.idPlat);
			Set<Foro> newForoList = new HashSet<Foro>();
			if (this.plat != null) {
				Set<Foro> foroList = this.plat.getComments();
				for (Foro foro : foroList) {
					if (!foro.getId().equals(this.idComment)) {
						newForoList.add(foro);
					}
				}
				this.plat.setComments(newForoList);
				this.platsBo.update(plat);
			}
			return null;
		} catch (Exception e) {
			return Utils.createErrorJSON("Error deleting comment" + e);
		}
	}
	
	public String ajaxDeleteCommentBegudaAction(){

		try {
			inizializeBegudaId();
			inizializeCommentId();
			this.beguda = this.begudaBo.loadBegudaAndForos(this.idBeguda);
			Set<ForoBeguda> newForoList = new HashSet<ForoBeguda>();
			if (this.beguda != null) {
				Set<ForoBeguda> foroList = this.beguda.getComments();
				for (ForoBeguda foro : foroList) {
					if (!foro.getId().equals(this.idComment)) {
						newForoList.add(foro);
					}
				}
				this.beguda.setComments(newForoList);
				this.begudaBo.update(beguda);
			}
			return null;
		} catch (Exception e) {
			return Utils.createErrorJSON("Error deleting comment" + e);
		}
	}

	// private methods

	private void inizializePlatId() throws WrongParamException{

		this.idPlat = (request.getParameter("idPlat") == null || request.getParameter("idPlat").equals("")) ? null : Long.parseLong(request
				.getParameter("idPlat"));
		if (this.idPlat == null) {
			throw new WrongParamException("null plat id");
		}

	}
	
	private void inizializeBegudaId() throws WrongParamException{

		this.idBeguda = (request.getParameter("idBeguda") == null || request.getParameter("idBeguda").equals("")) ? null : Long.parseLong(request
				.getParameter("idBeguda"));
		if (this.idBeguda == null) {
			throw new WrongParamException("null beguda id");
		}

	}

	private void inizializeCommentId() throws WrongParamException{

		this.idComment = (request.getParameter("idComment") == null || request.getParameter("idComment").equals("")) ? null : Long
				.parseLong(request.getParameter("idComment"));
		if (this.idComment == null) {
			throw new WrongParamException("null comment id");
		}
	}

	// SETTERS i GETTERS
	public void setPlatsBo( PlatsBo platsBo ){

		this.platsBo = platsBo;
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

	public void setServletResponse( HttpServletResponse response ){

		this.response = response;
	}

	public Plat getPlat(){

		return plat;
	}

	public void setPlat( Plat plat ){

		this.plat = plat;
	}

	public List<Plat> getListPlats(){

		return listPlats;
	}

	public void setListPlats( List<Plat> listPlats ){

		this.listPlats = listPlats;
	}

	public List<Beguda> getListBegudes(){
	
		return listBegudes;
	}

	public void setListBegudes( List<Beguda> listBegudes ){
	
		this.listBegudes = listBegudes;
	}

	public void setBegudaBo( BegudaBo begudaBo ){
	
		this.begudaBo = begudaBo;
	}

	public Beguda getBeguda(){
	
		return beguda;
	}

	public void setBeguda( Beguda beguda ){
	
		this.beguda = beguda;
	}
	
	
}