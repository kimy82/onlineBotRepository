package com.online.action.admin;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import com.online.bo.BegudaBo;
import com.online.bo.PlatsBo;
import com.online.dao.HtmlDao;
import com.online.exceptions.GeneralException;
import com.online.exceptions.WrongParamException;
import com.online.model.Beguda;
import com.online.model.Foro;
import com.online.model.ForoBeguda;
import com.online.model.Html;
import com.online.model.Plat;
import com.online.model.Restaurant;
import com.online.pojos.ForoDTO;
import com.online.supplier.extend.ActionSuportOnline;
import com.online.utils.Utils;

public class MantenimentHtmlAction extends ActionSuportOnline{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private HtmlDao				htmlDao;
	private String				innerHTML;

	

	public String execute(){

		Html html = this.htmlDao.load(1L);
		this.innerHTML=html.getHtml();

		return SUCCESS;
	}

	public String saveHtml(){

		ServletOutputStream out = null;

		this.innerHTML = Utils.decodeUTFONlyWords(request.getParameter("txt"));
		
		String json = "{\"ok\":\"ok\"}";
		try {

			out = this.response.getOutputStream();
			
			Html html = this.htmlDao.load(1L);
			if(html==null){
				html= new Html();
				html.setHtml(innerHTML);
				this.htmlDao.save(html);
			}else{
				html.setHtml(innerHTML);
				this.htmlDao.update(html);
			}

		} catch (Exception e) {
			json = Utils.createErrorJSON("error in ajax action");
		}

		try {
			out.print(json);
		} catch (IOException e) {
			throw new GeneralException(e, "possibly ServletOutputStream null");
		}
		return null;
	}

	


	public String getInnerHTML(){
	
		return innerHTML;
	}



	public void setInnerHTML( String innerHTML ){
	
		this.innerHTML = innerHTML;
	}



	public void setHtmlDao( HtmlDao htmlDao ){
	
		this.htmlDao = htmlDao;
	}
	
	

	
}