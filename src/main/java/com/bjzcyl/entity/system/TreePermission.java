package com.bjzcyl.entity.system;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;


@SuppressWarnings("serial")
public class TreePermission implements Serializable{
	private String Permission_ID;	
	private String Permission_NAME;	
	private String PARENT_ID;
	private String ICON;
	private boolean hasPermission = false;
	private List<TreePermission> subPermission;
	private JSONObject state;
	
	public String getPermission_ID() {
		return Permission_ID;
	}
	public void setPermission_ID(String permission_ID) {
		Permission_ID = permission_ID;
	}
	public String getPermission_NAME() {
		return Permission_NAME;
	}
	public void setPermission_NAME(String permission_NAME) {
		Permission_NAME = permission_NAME;
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
	public boolean isHasPermission() {
		return hasPermission;
	}
	public void setHasPermission(boolean hasPermission) {
		this.hasPermission = hasPermission;
	}
	public List<TreePermission> getSubPermission() {
		return subPermission;
	}
	public void setSubPermission(List<TreePermission> subPermission) {
		this.subPermission = subPermission;
		if(subPermission.size() == 0)
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
