package com.online.pojos;

import java.io.Serializable;

public class Payment implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String ds_Merchant_Amount;
	private String ds_Merchant_Currency="978";
	private String ds_Merchant_Order;
	private String ds_Merchant_ProductDescription;
	private String ds_Merchant_Titular;
	private String ds_Merchant_MerchantCode="327318309";
	private String ds_Merchant_UrlOK;
	private String ds_Merchant_UrlKO;
	private String ds_Merchant_Url;
	private String ds_Merchant_ConsumerLanguage="0";
	private String ds_Merchant_MerchantName="PORTAMU";
	private String ds_Merchant_Terminal="1";
	private String ds_Merchant_TransactionType="0";
	private String ds_Merchant_MerchantSignature;
	private String url;
	// CONSTRUCTORS

	public Payment() {

		super();

	}

	// GETTERS i SETTERS
	public String getUrl() {
		return url;
	}

	public String getDs_Merchant_Amount() {
		return ds_Merchant_Amount;
	}

	public void setDs_Merchant_Amount(String ds_Merchant_Amount) {
		this.ds_Merchant_Amount = ds_Merchant_Amount;
	}

	public String getDs_Merchant_Currency() {
		return ds_Merchant_Currency;
	}

	public void setDs_Merchant_Currency(String ds_Merchant_Currency) {
		this.ds_Merchant_Currency = ds_Merchant_Currency;
	}

	public String getDs_Merchant_Order() {
		return ds_Merchant_Order;
	}

	public void setDs_Merchant_Order(String ds_Merchant_Order) {
		this.ds_Merchant_Order = ds_Merchant_Order;
	}

	public String getDs_Merchant_ProductDescription() {
		return ds_Merchant_ProductDescription;
	}

	public void setDs_Merchant_ProductDescription(
			String ds_Merchant_ProductDescription) {
		this.ds_Merchant_ProductDescription = ds_Merchant_ProductDescription;
	}

	public String getDs_Merchant_Titular() {
		return ds_Merchant_Titular;
	}

	public void setDs_Merchant_Titular(String ds_Merchant_Titular) {
		this.ds_Merchant_Titular = ds_Merchant_Titular;
	}

	public String getDs_Merchant_MerchantCode() {
		return ds_Merchant_MerchantCode;
	}

	public void setDs_Merchant_MerchantCode(String ds_Merchant_MerchantCode) {
		this.ds_Merchant_MerchantCode = ds_Merchant_MerchantCode;
	}

	public String getDs_Merchant_UrlOK() {
		return ds_Merchant_UrlOK;
	}

	public void setDs_Merchant_UrlOK(String ds_Merchant_UrlOK) {
		this.ds_Merchant_UrlOK = ds_Merchant_UrlOK;
	}

	public String getDs_Merchant_UrlKO() {
		return ds_Merchant_UrlKO;
	}

	public void setDs_Merchant_UrlKO(String ds_Merchant_UrlKO) {
		this.ds_Merchant_UrlKO = ds_Merchant_UrlKO;
	}

	public String getDs_Merchant_ConsumerLanguage() {
		return ds_Merchant_ConsumerLanguage;
	}

	public void setDs_Merchant_ConsumerLanguage(String ds_Merchant_ConsumerLanguage) {
		this.ds_Merchant_ConsumerLanguage = ds_Merchant_ConsumerLanguage;
	}

	public String getDs_Merchant_MerchantName() {
		return ds_Merchant_MerchantName;
	}

	public void setDs_Merchant_MerchantName(String ds_Merchant_MerchantName) {
		this.ds_Merchant_MerchantName = ds_Merchant_MerchantName;
	}

	public String getDs_Merchant_Terminal() {
		return ds_Merchant_Terminal;
	}

	public void setDs_Merchant_Terminal(String ds_Merchant_Terminal) {
		this.ds_Merchant_Terminal = ds_Merchant_Terminal;
	}

	public String getDs_Merchant_TransactionType() {
		return ds_Merchant_TransactionType;
	}

	public void setDs_Merchant_TransactionType(String ds_Merchant_TransactionType) {
		this.ds_Merchant_TransactionType = ds_Merchant_TransactionType;
	}

	public String getDs_Merchant_MerchantSignature() {
		return ds_Merchant_MerchantSignature;
	}

	public void setDs_Merchant_MerchantSignature(
			String ds_Merchant_MerchantSignature) {
		this.ds_Merchant_MerchantSignature = ds_Merchant_MerchantSignature;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDs_Merchant_Url(){
	
		return ds_Merchant_Url;
	}

	public void setDs_Merchant_Url( String ds_Merchant_Url ){
	
		this.ds_Merchant_Url = ds_Merchant_Url;
	}

	
}
