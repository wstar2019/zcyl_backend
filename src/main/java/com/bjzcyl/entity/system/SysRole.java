package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class SysRole {
	
	private String ID;	
	private String ROLE_NAME;
	private String ROLE_ENG_NAME;
	private String ROLE_MENU_PERMISSION;
	private String ROLE_STATE;	
	private String ROLE_MENU_IDS;
	private String ROLE_DT;
	private String ROLE_COMMENT;
	private Page page;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getROLE_NAME() {
		return ROLE_NAME;
	}
	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}
	public String getROLE_ENG_NAME() {
		return ROLE_ENG_NAME;
	}
	public void setROLE_ENG_NAME(String rOLE_ENG_NAME) {
		ROLE_ENG_NAME = rOLE_ENG_NAME;
	}
	public String getROLE_MENU_PERMISSION() {
		return ROLE_MENU_PERMISSION;
	}
	public void setROLE_MENU_PERMISSION(String rOLE_MENU_PERMISSION) {
		ROLE_MENU_PERMISSION = rOLE_MENU_PERMISSION;
	}
	public String getROLE_STATE() {
		return ROLE_STATE;
	}
	public void setROLE_STATE(String rOLE_STATE) {
		ROLE_STATE = rOLE_STATE;
	}
	public String getROLE_MENU_IDS() {
		return ROLE_MENU_IDS;
	}
	public void setROLE_MENU_IDS(String rOLE_MENU_IDS) {
		ROLE_MENU_IDS = rOLE_MENU_IDS;
	}
	public String getROLE_DT() {
		return ROLE_DT;
	}
	public void setROLE_DT(String rOLE_DT) {
		ROLE_DT = rOLE_DT;
	}
	public String getROLE_COMMENT() {
		return ROLE_COMMENT;
	}
	public void setROLE_COMMENT(String rOLE_COMMENT) {
		ROLE_COMMENT = rOLE_COMMENT;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
