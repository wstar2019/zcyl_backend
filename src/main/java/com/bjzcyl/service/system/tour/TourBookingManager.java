package com.bjzcyl.service.system.tour;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface TourBookingManager {
	public List<PageData> listTourBooking(Page page)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public void insertBooking(PageData pd)throws Exception;
	public void updateBookingState(PageData pd)throws Exception;
}
