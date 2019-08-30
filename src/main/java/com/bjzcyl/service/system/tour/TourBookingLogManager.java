package com.bjzcyl.service.system.tour;

import java.util.List;

import com.bjzcyl.util.PageData;


public interface TourBookingLogManager {
	public List<PageData> findByBookingId(PageData pd)throws Exception;
	public void insertBookingLog(PageData pd)throws Exception;
}
