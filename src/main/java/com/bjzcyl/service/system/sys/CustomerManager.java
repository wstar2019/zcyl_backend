package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface CustomerManager {
	public List<PageData> customerlistPage(Page page)throws Exception;
	public void deleteCustomer(PageData pd)throws Exception;
}
