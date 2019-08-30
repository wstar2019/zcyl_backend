package com.bjzcyl.entity.system;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class TreeMenus implements Serializable{
	private String MENU_ID;	
	private String MENU_NAME;	
	private String PARENT_ID;
	private String ICON;
	private boolean hasMenu = false;
	private List<TreeMenus> subMenu;
	private JSONObject state;
	
	public String getMENU_ID() {
		return MENU_ID;
	}
	public void setMENU_ID(String mENU_ID) {
		MENU_ID = mENU_ID;
	}
	public String getMENU_NAME() {
		return MENU_NAME;
	}
	public void setMENU_NAME(String mENU_NAME) {
		MENU_NAME = mENU_NAME;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public String getICON() {
		return ICON;
	}
	public void setICON(String iCON) {
		ICON = iCON;
	}
	public boolean isHasMenu() {
		return hasMenu;
	}
	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}
	public List<TreeMenus> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<TreeMenus> subMenu) {
		this.subMenu = subMenu;
		if(subMenu.size() == 0)
			setICON("fa fa-file icon-state-warning icon-lg m_file_icon");
		else
		{
			setICON("fa fa-folder icon-state-warning icon-lg");
			JSONObject obj = new JSONObject();
			obj.put("opended", true);
			this.setState(obj);
		}
	}
	public JSONObject getState() {
		return state;
	}
	public void setState(JSONObject obj) {
		state = obj;
	}
	
	
}
