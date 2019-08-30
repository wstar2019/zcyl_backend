package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class TourFeedback {
	private String ID;
	private String USER_ID;
	private String ARTICLE_ID;
	private String RATE;
	private String CONTENT;
	private String CREATE_TIME;	
	private Page page;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getARTICLE_ID() {
		return ARTICLE_ID;
	}
	public void setARTICLE_ID(String aRTICLE_ID) {
		ARTICLE_ID = aRTICLE_ID;
	}
	public String getRATE() {
		return RATE;
	}
	public void setRATE(String rATE) {
		RATE = rATE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
