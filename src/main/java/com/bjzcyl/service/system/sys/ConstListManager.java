package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface ConstListManager {
	public List<PageData> constListlistPage(Page page)throws Exception;
	public void deleteConstList(PageData pd)throws Exception;
	public void saveConstList(PageData pd)throws Exception;
	public void updateConstList(PageData pd) throws Exception;
	public List<PageData> allConstByType(PageData pd)throws Exception;
	public PageData find(PageData pd) throws Exception;
}
