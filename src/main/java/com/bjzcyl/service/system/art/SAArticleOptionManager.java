package com.bjzcyl.service.system.art;

import java.util.List;

import com.bjzcyl.util.PageData;


public interface SAArticleOptionManager {
	
	public List<PageData> listArticleOption(PageData pd)throws Exception;
	public PageData checkItem(PageData pd)throws Exception;
	public void saveSAOption(PageData pd)throws Exception;
	public void deleSAOption(PageData pd)throws Exception;
	
}
