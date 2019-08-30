package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class FPArt {
	private String ID;
	private String ART_ID;
	private String SALE_QUANTITY;
	private String SALE_AMOUNT;
	private String VIEW_NUM;
	private String VIEW_STATE;
	private Page page;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getART_ID() {
		return ART_ID;
	}
	public void setART_ID(String aRT_ID) {
		ART_ID = aRT_ID;
	}
	public String getSALE_QUANTITY() {
		return SALE_QUANTITY;
	}
	public void setSALE_QUANTITY(String sALE_QUANTITY) {
		SALE_QUANTITY = sALE_QUANTITY;
	}
	public String getSALE_AMOUNT() {
		return SALE_AMOUNT;
	}
	public void setSALE_AMOUNT(String sALE_AMOUNT) {
		SALE_AMOUNT = sALE_AMOUNT;
	}
	public String getVIEW_NUM() {
		return VIEW_NUM;
	}
	public void setVIEW_NUM(String vIEW_NUM) {
		VIEW_NUM = vIEW_NUM;
	}
	public String getVIEW_STATE() {
		return VIEW_STATE;
	}
	public void setVIEW_STATE(String vIEW_STATE) {
		VIEW_STATE = vIEW_STATE;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
