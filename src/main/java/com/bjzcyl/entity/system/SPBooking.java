package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class SPBooking {
	private String ID;
	private String USER_ID;
	private String ARTICLE_ID;
	private String AMOUNT;
	private String TOTAL_PRICE;
	private String REQUEST_DATE;
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
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getTOTAL_PRICE() {
		return TOTAL_PRICE;
	}
	public void setTOTAL_PRICE(String tOTAL_PRICE) {
		TOTAL_PRICE = tOTAL_PRICE;
	}
	public String getREQUEST_DATE() {
		return REQUEST_DATE;
	}
	public void setREQUEST_DATE(String rEQUEST_DATE) {
		REQUEST_DATE = rEQUEST_DATE;
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
