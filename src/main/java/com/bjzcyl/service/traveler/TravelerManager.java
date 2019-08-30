package com.bjzcyl.service.traveler;

import java.util.List;

import com.bjzcyl.util.PageData;

public interface TravelerManager {
	///////////////////// Traveler Feature	/////////////////////
	public void updatePassword(PageData pd)throws Exception;
	public void updateTraveler(PageData pd)throws Exception;
	public void registTraveler(PageData pd)throws Exception;	
	public List<PageData> findByLGID(PageData pd) throws Exception;
	public PageData loginTraveler(PageData pd) throws Exception;
}
