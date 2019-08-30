package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class FPSliding {
	private String ID;
	private String NAME;
	private String COMMENT;
	private String VIEW_TYPE;
	private String STATE;
	private Page page;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getCOMMENT() {
		return COMMENT;
	}
	public void setCOMMENT(String cOMMENT) {
		COMMENT = cOMMENT;
	}
	public String getVIEW_TYPE() {
		return VIEW_TYPE;
	}
	public void setVIEW_TYPE(String vIEW_TYPE) {
		VIEW_TYPE = vIEW_TYPE;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
