package com.online.action.foro;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.BegudaBo;
import com.online.bo.ForoBo;
import com.online.bo.PlatsBo;
import com.online.bo.UsersBo;
import com.online.bo.VotacionsBo;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Beguda;
import com.online.model.Foro;
import com.online.model.ForoBeguda;
import com.online.model.Plat;
import com.online.model.Users;
import com.online.model.VotacioTMP;
import com.online.model.VotacioTMPBeguda;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;

public class ForoAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private PlatsBo				platsBo;
	private BegudaBo			begudaBo;

	private UsersBo				usersBo;
	private ForoBo				foroBo;
	private VotacionsBo			votacionsBo;

	private Long				idPlat;
	private Long				idBeguda;
	private Long				idComment;
	private String				nameAuth;

	private Users				user;
	private Plat				plat;
	private Beguda				beguda;
	private String				comment;
	private int					punctuation;


	public String execute(){

		inizializePlatId();
		getUserFromContext();
		boolean allowComments = false;
		if (this.user != null)
			allowComments = this.usersBo.checkUserPlat(this.user.getId(), idPlat);
		if (allowComments) {
			VotacioTMP votTMP = this.votacionsBo.get(this.idPlat, this.user.getId());
			if (votTMP != null && votTMP.getDia().compareTo(new Date()) == 0) {
				allowComments = false;
			}
		}

		if (!allowComments) {
			this.nameAuth = "anonymousUser";
		} else {
			this.nameAuth = "ROLE_USER";
		}

		if (this.user != null && this.user.getUserRole().getRole().equals("ROLE_ADMIN")) {
			this.nameAuth = "ROLE_ADMIN";
		}

		this.plat = this.platsBo.loadPLatAndForos(this.idPlat);
		return SUCCESS;

	}

	public String foroBeguda(){

		inizializeBegudaId();
		getUserFromContext();
		boolean allowComments = false;
		if (this.user != null)
			allowComments = this.usersBo.checkUserBeguda(this.user.getId(), idBeguda);
		if (allowComments) {
			VotacioTMPBeguda votTMP = this.votacionsBo.getBeguda(this.idBeguda, this.user.getId());
			if (votTMP != null && votTMP.getDia().compareTo(new Date()) == 0) {
				allowComments = false;
			}
		}

		if (!allowComments) {
			this.nameAuth = "anonymousUser";
		} else {
			this.nameAuth = "ROLE_USER";
		}

		if (this.user != null && this.user.getUserRole().getRole().equals("ROLE_ADMIN")) {
			this.nameAuth = "ROLE_ADMIN";
		}

		this.beguda = this.begudaBo.loadBegudaAndForos(this.idBeguda);
		return SUCCESS;

	}

	public String ajaxSaveCommentForPlat(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializePlatId();
			inizializeComment();
			getUserFromContext();
			this.plat = this.platsBo.loadPLatAndForos(this.idPlat);
			if (this.plat != null) {
				Set<Foro> foroList = this.plat.getComments();
				Foro foro = new Foro();
				foro.setComment(comment);
				foro.setPlat(plat);
				this.foroBo.save(foro);
				foroList.add(foro);
				this.platsBo.update(plat);

				json = "{ \"idComment\": \"" + foro.getId() + "\"}";

			}

		} catch (Exception e) {
			json = Utils.createErrorJSON("Error saving comment" + e);
		}

		try {
			if (!json.equals(""))
				out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	public String ajaxDeleteCommentForPlat(){

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

	public String ajaxDeleteCommentForBeguda(){

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

	public String ajaxSaveCommentForBeguda(){

		ServletOutputStream out = null;
		String json = "";

		try {
			out = this.response.getOutputStream();
			inizializeBegudaId();
			inizializeComment();
			getUserFromContext();
			this.beguda = this.begudaBo.loadBegudaAndForos(this.idBeguda);
			if (this.beguda != null) {
				Set<ForoBeguda> foroList = this.beguda.getComments();
				ForoBeguda foro = new ForoBeguda();
				foro.setComment(comment);
				foro.setBeguda(beguda);
				this.foroBo.saveBeguda(foro);
				foroList.add(foro);
				this.begudaBo.update(beguda);

				json = "{ \"idComment\": \"" + foro.getId() + "\"}";

			}

		} catch (Exception e) {
			json = Utils.createErrorJSON("Error saving comment" + e);
		}

		try {
			if (!json.equals(""))
				out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	public String ajaxSavePunctuacioForPlat(){

		try {
			inizializePlatId();
			inizializePuntuacio();
			getUserFromContext();

			VotacioTMP votacioTMP = new VotacioTMP();
			votacioTMP.setPlatId(idPlat);
			votacioTMP.setPunctuacio(punctuation);
			votacioTMP.setUserId(this.user.getId());
			votacioTMP.setDia(new Date());
			this.votacionsBo.saveTMP(votacioTMP);

			return null;
		} catch (Exception e) {
			return Utils.createErrorJSON("Error saving votacio" + e);
		}
	}

	public String ajaxSavePunctuacioForBeguda(){

		try {
			inizializeBegudaId();
			inizializePuntuacio();
			getUserFromContext();

			VotacioTMPBeguda votacioTMP = new VotacioTMPBeguda();
			votacioTMP.setBegudaId(idBeguda);
			votacioTMP.setPunctuacio(punctuation);
			votacioTMP.setUserId(this.user.getId());
			votacioTMP.setDia(new Date());
			this.votacionsBo.saveTMPBeguda(votacioTMP);

			return null;
		} catch (Exception e) {
			return Utils.createErrorJSON("Error saving votacio" + e);
		}
	}

	// private methods

	private void inizializePuntuacio() throws WrongParamException{

		this.punctuation = (request.getParameter("punctuacio") == null || request.getParameter("punctuacio").equals("")) ? 0 : Integer
				.parseInt(request.getParameter("punctuacio"));
		if (request.getParameter("punctuacio") == null) {
			throw new WrongParamException("null puntuacio id");
		}
	}

	private void inizializePlatId() throws WrongParamException{

		this.idPlat = (request.getParameter("idPlat") == null || request.getParameter("idPlat").equals("")) ? null : Long.parseLong(request
				.getParameter("idPlat"));
		if (this.idPlat == null) {
			throw new WrongParamException("null plat id");
		}

	}

	private void inizializeBegudaId() throws WrongParamException{

		this.idBeguda = (request.getParameter("idBeguda") == null || request.getParameter("idBeguda").equals("")) ? null : Long
				.parseLong(request.getParameter("idBeguda"));
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

	private void inizializeComment() throws WrongParamException{

		this.comment = (request.getParameter("comment") == null) ? null : request.getParameter("comment");

	}

	private void getUserFromContext(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		this.nameAuth = auth.getName();
		if (!this.nameAuth.equals("anonymousUser")) {
			this.user = this.usersBo.findByUsername(this.nameAuth);
		} else {
			this.user = null;
		}
	}

	// SETTERS i GETTERS
	public void setPlatsBo( PlatsBo platsBo ){

		this.platsBo = platsBo;
	}

	public void setUsersBo( UsersBo usersBo ){

		this.usersBo = usersBo;
	}

	public Users getUser(){

		return user;
	}

	public void setUser( Users user ){

		this.user = user;
	}

	public Plat getPlat(){

		return plat;
	}

	public void setPlat( Plat plat ){

		this.plat = plat;
	}

	public String getNameAuth(){

		return nameAuth;
	}

	public void setNameAuth( String nameAuth ){

		this.nameAuth = nameAuth;
	}

	public void setForoBo( ForoBo foroBo ){

		this.foroBo = foroBo;
	}

	public void setVotacionsBo( VotacionsBo votacionsBo ){

		this.votacionsBo = votacionsBo;
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