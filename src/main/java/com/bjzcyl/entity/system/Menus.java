package com.bjzcyl.entity.system;

import java.io.Serializable;
import java.util.List;

import com.bjzcyl.entity.Page;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class Menus implements Serializable{
	private String MENU_ID;	
	private String MENU_NAME;	
	private String MENU_URL;	
	private String PARENT_ID;	
	private String MENU_ORDER;	
	private String MENU_ICON;	
	private String MENU_STATE;	
	private String MENU_TARGET;	
	private String MENU_PERMISION;	
	private String MENU_COMMENT;
	private String ICON;
	private Menus dict;
	private List<Menus> subMenu;
	private List<SysPermission> permission;
	private boolean hasMenu = false;
	private String treeurl;
	private Page page;	
	private JSONObject state;
		
	public String getICON() {
		return ICON;
	}
	public void setICON(String iCON) {
		ICON = iCON;
	}
	public JSONObject getState() {
		return state;
	}
	public void setState(JSONObject obj) {
		state = obj;
	}
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
	public String getMENU_URL() {
		return MENU_URL;
	}
	public void setMENU_URL(String mENU_URL) {
		MENU_URL = mENU_URL;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public String getMENU_ORDER() {
		return MENU_ORDER;
	}
	public void setMENU_ORDER(String mENU_ORDER) {
		MENU_ORDER = mENU_ORDER;
	}
	public String getMENU_ICON() {
		return MENU_ICON;
	}
	public void setMENU_ICON(String mENU_ICON) {
		MENU_ICON = mENU_ICON;
	}
	public String getMENU_STATE() {
		return MENU_STATE;
	}
	public void setMENU_STATE(String mENU_STATE) {
		MENU_STATE = mENU_STATE;
	}
	public String getMENU_TARGET() {
		return MENU_TARGET;
	}
	public void setMENU_TARGET(String mENU_TARGET) {
		MENU_TARGET = mENU_TARGET;
	}
	public String getMENU_PERMISION() {
		return MENU_PERMISION;
	}
	public void setMENU_PERMISION(String mENU_PERMISION) {
		MENU_PERMISION = mENU_PERMISION;
	}
	public String getMENU_COMMENT() {
		return MENU_COMMENT;
	}
	public void setMENU_COMMENT(String mENU_COMMENT) {
		MENU_COMMENT = mENU_COMMENT;
	}
	public Menus getDict() {
		return dict;
	}
	public void setDict(Menus dict) {
		this.dict = dict;
	}
	public List<Menus> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<Menus> subMenu) {
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
	public void setPermission(List<SysPermission> permission) {
		this.permission = permission;
		if(permission.size() == 0)
			setICON("fa fa-file icon-state-warning icon-lg m_file_icon");
		else
		{
			setICON("fa fa-folder icon-state-warning icon-lg");
			JSONObject obj = new JSONObject();
			obj.put("opended", true);
			this.setState(obj);
		}
		
	}
	public List<SysPermission> getPermission() {
		return permission;
	}
	public boolean isHasMenu() {
		return hasMenu;
	}
	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}
	public String getTreeurl() {
		return treeurl;
	}
	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
