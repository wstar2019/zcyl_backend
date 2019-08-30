package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

/**
* @author R3
* 2018-10-30
* @version 1.0
 */
public class Const {
	private String PKID;
	private String CONST_VALUE;
	private String CONST_NAME;
	private String CONST_TYPE;	
	private String CONST_DESCRIPTION;
	private String CONST_ORDER;
	private String CONST_COMMENT;
	private Page page;
	
	public String getPKID() {
		return PKID;
	}
	public void setPKID(String pKID) {
		PKID = pKID;
	}
	public String getCONST_VALUE() {
		return CONST_VALUE;
	}
	public void setCONST_VALUE(String cONST_VALUE) {
		CONST_VALUE = cONST_VALUE;
	}
	public String getCONST_NAME() {
		return CONST_NAME;
	}
	public void setCONST_NAME(String cONST_NAME) {
		CONST_NAME = cONST_NAME;
	}
	public String getCONST_TYPE() {
		return CONST_TYPE;
	}
	public void setCONST_TYPE(String cONST_TYPE) {
		CONST_TYPE = cONST_TYPE;
	}
	public String getCONST_DESCRIPTION() {
		return CONST_DESCRIPTION;
	}
	public void setCONST_DESCRIPTION(String cONST_DESCRIPTION) {
		CONST_DESCRIPTION = cONST_DESCRIPTION;
	}
	public String getCONST_ORDER() {
		return CONST_ORDER;
	}
	public void setCONST_ORDER(String cONST_ORDER) {
		CONST_ORDER = cONST_ORDER;
	}
	public String getCONST_COMMENT() {
		return CONST_COMMENT;
	}
	public void setCONST_COMMENT(String cONST_COMMENT) {
		CONST_COMMENT = cONST_COMMENT;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	
}
