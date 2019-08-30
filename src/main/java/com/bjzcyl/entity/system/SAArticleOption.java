package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class SAArticleOption {
	private String ID;
	private String ARTICLE_ID;
	private String OPTION;
	private String VAL;
	private Page page;
	
	public String getID() {
		return ID;
	}
	public void setID(String _ID) {
		ID = _ID;
	}	
	public String getARTICLE_ID() {
		return ARTICLE_ID;
	}
	public void setARTICLE_ID(String aRTICLE_ID) {
		ARTICLE_ID = aRTICLE_ID;
	}
	public String getOPTION() {
		return OPTION;
	}
	public void setOPTION(String oPTION) {
		OPTION = oPTION;
	}
	public String getVAL() {
		return VAL;
	}
	public void setVAL(String vAL) {
		VAL = vAL;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
		
}
