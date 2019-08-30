package com.bjzcyl.service.system.special;

import java.util.List;

import com.bjzcyl.util.PageData;


public interface SPBookingLogManager {
	public List<PageData> findByBookingId(PageData pd)throws Exception;
}
