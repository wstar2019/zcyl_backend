package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class TourOption {
	private String ID;
	private String NAME;
	private String COMMENT;
	private String STATE;
	private Page page;	
	
	public String getID() {
		return ID;
	}
	public void setID(String _ID) {
		ID = _ID;
	}
	
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String _NAME) {
		NAME = _NAME;
	}
	
	public String getCOMMENT() {
		return COMMENT;
	}
	public void setCOMMENT(String _COMMENT) {
		COMMENT = _COMMENT;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String _STATE) {
		STATE = _STATE;
	}
	public Page getPage() {
		if(page==null)
			page = new Page();
		
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
