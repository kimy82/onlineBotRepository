package com.online.supplier.extend;

import java.util.Locale;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class ActionSuportOnlineSession extends ActionSuportOnline implements SessionAware{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	protected Map<String, Object>	session;

	protected void setLocaleIfNull( String locale ) throws NumberFormatException{

		if (session.get("WW_TRANS_I18N_LOCALE") == null || session.get("WW_TRANS_I18N_LOCALE").equals(""))
			session.put("WW_TRANS_I18N_LOCALE", new Locale("ca"));
	}

	protected void setLocale( String locale ) throws NumberFormatException{

		session.put("WW_TRANS_I18N_LOCALE", new Locale("ca"));
	}

	// SETTERS
	public void setSession( Map<String, Object> session ){

		this.session = session;
	}

}