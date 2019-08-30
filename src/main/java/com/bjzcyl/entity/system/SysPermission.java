package com.bjzcyl.entity.system;

import java.io.Serializable;
import java.util.List;

import com.bjzcyl.entity.Page;

@SuppressWarnings("serial")
public class SysPermission implements Serializable{
	private String ID;	
	private String NAME;	
	private String ENG_NAME;
	private String M_ID;
	private String PARENT_ID;	
	private String STATE;	
	private String COMMENT;
	private boolean hasPermission = false;
	private List<Menus> subMenu;
	
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
	public String getENG_NAME() {
		return ENG_NAME;
	}
	public void setENG_NAME(String eNG_NAME) {
		ENG_NAME = eNG_NAME;
	}
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getCOMMENT() {
		return COMMENT;
	}
	public void setCOMMENT(String cOMMENT) {
		COMMENT = cOMMENT;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public boolean isHasPermission() {
		return hasPermission;
	}
	public void setHasPermission(boolean hasPermission) {
		this.hasPermission = hasPermission;
	}
	public List<Menus> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Menus> subMenu) {
		this.subMenu = subMenu;
	}
	
}
