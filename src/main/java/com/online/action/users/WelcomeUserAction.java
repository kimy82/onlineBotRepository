package com.online.action.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.online.bo.ComandaBo;
import com.online.bo.UsersBo;
import com.online.model.Comandes;
import com.online.model.Users;
import com.opensymphony.xwork2.ActionSupport;

public class WelcomeUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ComandaBo 	comandaBo;
	private UsersBo 	usersBo;
	
	List<Comandes> 		ComandaList = new ArrayList<Comandes>();

	public String execute() {

		return SUCCESS;

	}

	public String comandesPasades() {

		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users user = this.usersBo.findByUsername(name);
		this.ComandaList = this.comandaBo.getAllByUser(user.getId());
		return SUCCESS;

	}

	//GETERS I SETTERS
	public List<Comandes> getComandaList() {
		return ComandaList;
	}

	public void setComandaList(List<Comandes> comandaList) {
		ComandaList = comandaList;
	}

	public void setComandaBo(ComandaBo comandaBo) {
		this.comandaBo = comandaBo;
	}
	

	
}