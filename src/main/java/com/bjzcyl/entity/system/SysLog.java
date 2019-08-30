package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class SysLog {
	
	private String ID;
	private String OP_DT;
	private String OP_MAN;
	private String OP_MENU;
	private String OP_KIND;
	private String OP_CONTENT;
	private String AT_SORT;
	private String AT_ID;
	
	private Page page;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getOP_DT() {
		return OP_DT;
	}
	public void setOP_DT(String oP_DT) {
		OP_DT = oP_DT;
	}
	public String getOP_MAN() {
		return OP_MAN;
	}
	public void setOP_MAN(String oP_MAN) {
		OP_MAN = oP_MAN;
	}
	public String getOP_MENU() {
		return OP_MENU;
	}
	public void setOP_MENU(String oP_MENU) {
		OP_MENU = oP_MENU;
	}
	public String getOP_KIND() {
		return OP_KIND;
	}
	public void setOP_KIND(String oP_KIND) {
		OP_KIND = oP_KIND;
	}
	public String getOP_CONTENT() {
		return OP_CONTENT;
	}
	public void setOP_CONTENT(String oP_CONTENT) {
		OP_CONTENT = oP_CONTENT;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getAT_SORT() {
		return AT_SORT;
	}
	public void setAT_SORT(String aT_SORT) {
		AT_SORT = aT_SORT;
	}
	public String getAT_ID() {
		return AT_ID;
	}
	public void setAT_ID(String aT_ID) {
		AT_ID = aT_ID;
	}
		
}
