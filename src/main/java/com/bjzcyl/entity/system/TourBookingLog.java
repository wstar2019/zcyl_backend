package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class TourBookingLog {
	private String ID;
	private String BOOKING_ID;
	private String PRE_STATE;
	private String CUR_STATE;
	private String UPDATE_TIME;
	private String COMMENT;
	private Page page;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBOOKING_ID() {
		return BOOKING_ID;
	}
	public void setBOOKING_ID(String bOOKING_ID) {
		BOOKING_ID = bOOKING_ID;
	}
	public String getPRE_STATE() {
		return PRE_STATE;
	}
	public void setPRE_STATE(String pRE_STATE) {
		PRE_STATE = pRE_STATE;
	}
	public String getCUR_STATE() {
		return CUR_STATE;
	}
	public void setCUR_STATE(String cUR_STATE) {
		CUR_STATE = cUR_STATE;
	}
	public String getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(String uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	public String getCOMMENT() {
		return COMMENT;
	}
	public void setCOMMENT(String cOMMENT) {
		COMMENT = cOMMENT;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}
