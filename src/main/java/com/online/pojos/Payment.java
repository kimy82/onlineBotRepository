package com.online.pojos;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.online.model.Plat;

public class Payment implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String Ds_Merchant_Amount;
	private String Ds_Merchant_Currency="978";
	private String Ds_Merchant_Order;
	private String Ds_Merchant_ProductDescription;
	private String Ds_Merchant_Titular;
	private String Ds_Merchant_MerchantCode="327318309";
	private String Ds_Merchant_UrlOK;
	private String Ds_Merchant_UrlKO;
	private String Ds_Merchant_ConsumerLanguage="0";
	private String Ds_Merchant_MerchantName="PORTAMU";
	private String Ds_Merchant_Terminal="1";
	private String Ds_Merchant_TransactionType="0";
	private String Ds_Merchant_MerchantSignature;
	private String url;
	// CONSTRUCTORS

	public Payment() {

		super();

	}

	// GETTERS i SETTERS
	public String getDs_Merchant_Amount() {
		return Ds_Merchant_Amount;
	}

	public void setDs_Merchant_Amount(String ds_Merchant_Amount) {
		Ds_Merchant_Amount = ds_Merchant_Amount;
	}

	public String getDs_Merchant_Currency() {
		return Ds_Merchant_Currency;
	}

	public void setDs_Merchant_Currency(String ds_Merchant_Currency) {
		Ds_Merchant_Currency = ds_Merchant_Currency;
	}

	public String getDs_Merchant_Order() {
		return Ds_Merchant_Order;
	}

	public void setDs_Merchant_Order(String ds_Merchant_Order) {
		Ds_Merchant_Order = ds_Merchant_Order;
	}

	public String getDs_Merchant_ProductDescription() {
		return Ds_Merchant_ProductDescription;
	}

	public void setDs_Merchant_ProductDescription(
			String ds_Merchant_ProductDescription) {
		Ds_Merchant_ProductDescription = ds_Merchant_ProductDescription;
	}

	public String getDs_Merchant_Titular() {
		return Ds_Merchant_Titular;
	}

	public void setDs_Merchant_Titular(String ds_Merchant_Titular) {
		Ds_Merchant_Titular = ds_Merchant_Titular;
	}

	public String getDs_Merchant_MerchantCode() {
		return Ds_Merchant_MerchantCode;
	}

	public void setDs_Merchant_MerchantCode(String ds_Merchant_MerchantCode) {
		Ds_Merchant_MerchantCode = ds_Merchant_MerchantCode;
	}

	public String getDs_Merchant_UrlOK() {
		return Ds_Merchant_UrlOK;
	}

	public void setDs_Merchant_UrlOK(String ds_Merchant_UrlOK) {
		Ds_Merchant_UrlOK = ds_Merchant_UrlOK;
	}

	public String getDs_Merchant_UrlKO() {
		return Ds_Merchant_UrlKO;
	}

	public void setDs_Merchant_UrlKO(String ds_Merchant_UrlKO) {
		Ds_Merchant_UrlKO = ds_Merchant_UrlKO;
	}

	public String getDs_Merchant_ConsumerLanguage() {
		return Ds_Merchant_ConsumerLanguage;
	}

	public void setDs_Merchant_ConsumerLanguage(String ds_Merchant_ConsumerLanguage) {
		Ds_Merchant_ConsumerLanguage = ds_Merchant_ConsumerLanguage;
	}

	public String getDs_Merchant_MerchantName() {
		return Ds_Merchant_MerchantName;
	}

	public void setDs_Merchant_MerchantName(String ds_Merchant_MerchantName) {
		Ds_Merchant_MerchantName = ds_Merchant_MerchantName;
	}

	public String getDs_Merchant_Terminal() {
		return Ds_Merchant_Terminal;
	}

	public void setDs_Merchant_Terminal(String ds_Merchant_Terminal) {
		Ds_Merchant_Terminal = ds_Merchant_Terminal;
	}

	public String getDs_Merchant_TransactionType() {
		return Ds_Merchant_TransactionType;
	}

	public void setDs_Merchant_TransactionType(String ds_Merchant_TransactionType) {
		Ds_Merchant_TransactionType = ds_Merchant_TransactionType;
	}

	public String getDs_Merchant_MerchantSignature() {
		return Ds_Merchant_MerchantSignature;
	}

	public void setDs_Merchant_MerchantSignature(
			String ds_Merchant_MerchantSignature) {
		Ds_Merchant_MerchantSignature = ds_Merchant_MerchantSignature;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
