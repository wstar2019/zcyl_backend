package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class TourBooking {
	private String ID;
	private String USER_ID;
	private String ARTICLE_ID;
	private String ADULT_NUM;
	private String CHILD_NUM;
	private String TOTAL_EXPENSE;
	private String REQUEST_DATE;
	private String START_DATE;
	private String BOOKING_STATE;
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
	public String getADULT_NUM() {
		return ADULT_NUM;
	}
	public void setADULT_NUM(String aDULT_NUM) {
		ADULT_NUM = aDULT_NUM;
	}
	public String getCHILD_NUM() {
		return CHILD_NUM;
	}
	public void setCHILD_NUM(String cHILD_NUM) {
		CHILD_NUM = cHILD_NUM;
	}
	public String getTOTAL_EXPENSE() {
		return TOTAL_EXPENSE;
	}
	public void setTOTAL_EXPENSE(String tOTAL_EXPENSE) {
		TOTAL_EXPENSE = tOTAL_EXPENSE;
	}
	public String getREQUEST_DATE() {
		return REQUEST_DATE;
	}
	public void setREQUEST_DATE(String rEQUEST_DATE) {
		REQUEST_DATE = rEQUEST_DATE;
	}
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String sTART_DATE) {
		START_DATE = sTART_DATE;
	}
	public String getBOOKING_STATE() {
		return BOOKING_STATE;
	}
	public void setBOOKING_STATE(String bOOKING_STATE) {
		BOOKING_STATE = bOOKING_STATE;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
