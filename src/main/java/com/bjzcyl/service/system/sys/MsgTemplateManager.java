package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface MsgTemplateManager {
	public List<PageData> msgTemplatelistPage(Page page)throws Exception;
	public void deleteMsgTemplate(PageData pd)throws Exception;
	public void saveMsgTemplate(PageData pd)throws Exception;
	public PageData getMsgTemplateInfo(PageData pd) throws Exception;
	public void changeMsgTemplateState(PageData pd)throws Exception;
}
