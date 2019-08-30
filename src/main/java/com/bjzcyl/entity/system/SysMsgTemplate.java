package com.bjzcyl.entity.system;

import com.bjzcyl.entity.Page;

public class SysMsgTemplate {
	
	private String ID;
	private String NAME;
	private String VAR;
	private String KIND;
	private String CONTENT;
	private String TEMP;
	private String STATE;
	private String REG_MAN;
	private String REG_TIME;
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
	public String getVAR() {
		return VAR;
	}
	public void setVAR(String vAR) {
		VAR = vAR;
	}
	public String getKIND() {
		return KIND;
	}
	public void setKIND(String kIND) {
		KIND = kIND;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getTEMP() {
		return TEMP;
	}
	public void setTEMP(String tEMP) {
		TEMP = tEMP;
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
	public String getREG_MAN() {
		return REG_MAN;
	}
	public void setREG_MAN(String rEG_MAN) {
		REG_MAN = rEG_MAN;
	}
	public String getREG_TIME() {
		return REG_TIME;
	}
	public void setREG_TIME(String rEG_TIME) {
		REG_TIME = rEG_TIME;
	}
	
	
	
}
