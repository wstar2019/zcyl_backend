package com.bjzcyl.service.system.art;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface SABookingManager {
	public List<PageData> listBooking(Page page)throws Exception;
	public PageData findById(PageData pd)throws Exception;
	public void updateBookingState(PageData pd)throws Exception;
}
