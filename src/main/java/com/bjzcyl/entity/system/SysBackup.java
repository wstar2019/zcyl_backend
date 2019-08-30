package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class SysBackup {
	
	private String ID;
	private String FILE;
	private String CREATE_TIME;
	private String OPERATOR;
	private Page page;
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFILE() {
		return FILE;
	}
	public void setFILE(String fILE) {
		FILE = fILE;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public String getOPERATOR() {
		return OPERATOR;
	}
	public void setOPERATOR(String oPERATOR) {
		OPERATOR = oPERATOR;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
