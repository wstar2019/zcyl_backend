package com.bjzcyl.service.system.special;

import java.util.List;

import com.bjzcyl.util.PageData;

public interface SPArticleOptionManager {
	
	public List<PageData> listArticleOption(PageData pd)throws Exception;
	public PageData checkItem(PageData pd)throws Exception;
	public void saveSPOption(PageData pd)throws Exception;
	public void deleSPOption(PageData pd)throws Exception;
	
}
