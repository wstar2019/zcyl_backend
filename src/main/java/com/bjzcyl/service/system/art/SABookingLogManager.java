package com.bjzcyl.service.system.art;

import java.util.List;

import com.bjzcyl.util.PageData;


public interface SABookingLogManager {
	public List<PageData> findByBookingId(PageData pd)throws Exception;
	public void saveBookingLog(PageData pd)throws Exception;
}
