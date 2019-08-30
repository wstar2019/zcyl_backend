package com.bjzcyl.entity.system;

import java.util.List;

import com.bjzcyl.entity.Page;

import net.sf.json.JSONObject;

public class SPClass {
	private String  CLASS_ID;
	private String  CLASS_PARENT;
	private String 	CLASS_NAME;	
	private String 	CLASS_CONTENT; 
	private String 	CLASS_VISIBILITY;
	private String  CLASS_LAYER;
	private String 	CLASS_SHOW_TYPE;
	private String 	CLASS_IMG_URL;
	private List<SPClass> subClass;
	private JSONObject state;
	
	private Page page;
	public List<SPClass> getSubClass() {
		return subClass;
	}
	public void setSubClass(List<SPClass> subClass) {
		this.subClass = subClass;
		if(subClass.size() != 0){
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
	public String getCLASS_ID() {
		return CLASS_ID;
	}
	public void setCLASS_ID(String cLASS_ID) {
		CLASS_ID = cLASS_ID;
	}
	public String getCLASS_PARENT() {
		return CLASS_PARENT;
	}
	public void setCLASS_PARENT(String cLASS_PARENT) {
		CLASS_PARENT = cLASS_PARENT;
	}
	public String getCLASS_NAME() {
		return CLASS_NAME;
	}
	public void setCLASS_NAME(String cLASS_NAME) {
		CLASS_NAME = cLASS_NAME;
	}
	public String getCLASS_CONTENT() {
		return CLASS_CONTENT;
	}
	public void setCLASS_CONTENT(String cLASS_CONTENT) {
		CLASS_CONTENT = cLASS_CONTENT;
	}
	public String getCLASS_VISIBILITY() {
		return CLASS_VISIBILITY;
	}
	public void setCLASS_VISIBILITY(String cLASS_VISIBILITY) {
		CLASS_VISIBILITY = cLASS_VISIBILITY;
	}
	public String getCLASS_LAYER() {
		return CLASS_LAYER;
	}
	public void setCLASS_LAYER(String cLASS_LAYER) {
		CLASS_LAYER = cLASS_LAYER;
	}
	public String getCLASS_SHOW_TYPE() {
		return CLASS_SHOW_TYPE;
	}
	public void setCLASS_SHOW_TYPE(String cLASS_SHOW_TYPE) {
		CLASS_SHOW_TYPE = cLASS_SHOW_TYPE;
	}
	public String getCLASS_IMG_URL() {
		return CLASS_IMG_URL;
	}
	public void setCLASS_IMG_URL(String cLASS_IMG_URL) {
		CLASS_IMG_URL = cLASS_IMG_URL;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
