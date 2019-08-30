package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface NotiTemplateManager {
	public List<PageData> notiTemplatelistPage(Page page)throws Exception;
	public void deleteNotiTemplate(PageData pd)throws Exception;
	public void saveNotiTemplate(PageData pd)throws Exception;
	public PageData getNotiTemplateInfo(PageData pd) throws Exception;
	public void sendNotify(PageData pd)throws Exception;
}
