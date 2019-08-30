package com.bjzcyl.service.system.special;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;


public interface SPBookingManager {
	public List<PageData> listBooking(Page page)throws Exception;
	public PageData findById(PageData pd)throws Exception;
}
