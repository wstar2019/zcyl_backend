package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class KPNews {
	
	private String ID;
	private String SN_TITLE;
	private String SN_MAN;
	private String SN_DATETIME;
	private String SN_IMAGE;
	private String SN_CONTENT;
	private int VIEW_NUM;
	private int TRAVELER_FP_VIEW;
	
	private Page page;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getSN_TITLE() {
		return SN_TITLE;
	}
	public void setSN_TITLE(String sN_TITLE) {
		SN_TITLE = sN_TITLE;
	}
	public String getSN_MAN() {
		return SN_MAN;
	}
	public void setSN_MAN(String sN_MAN) {
		SN_MAN = sN_MAN;
	}
	public String getSN_DATETIME() {
		return SN_DATETIME;
	}
	public void setSN_DATETIME(String sN_DATETIME) {
		SN_DATETIME = sN_DATETIME;
	}
	public String getSN_IMAGE() {
		return SN_IMAGE;
	}
	public void setSN_IMAGE(String sN_IMAGE) {
		SN_IMAGE = sN_IMAGE;
	}
	public String getSN_CONTENT() {
		return SN_CONTENT;
	}
	public void setSN_CONTENT(String sN_CONTENT) {
		SN_CONTENT = sN_CONTENT;
	}	
	public int getVIEW_NUM() {
		return VIEW_NUM;
	}
	public void setVIEW_NUM(int vIEW_NUM) {
		VIEW_NUM = vIEW_NUM;
	}
	public int getTRAVELER_FP_VIEW() {
		return TRAVELER_FP_VIEW;
	}
	public void setTRAVELER_FP_VIEW(int tRAVELER_FP_VIEW) {
		TRAVELER_FP_VIEW = tRAVELER_FP_VIEW;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
