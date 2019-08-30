package com.bjzcyl.service.system.art;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface SASlidingManager {
	public List<PageData> listSASlide(Page page)throws Exception;
	public List<PageData> saSlideAll() throws Exception;
	public void saveSASlide(PageData pd)throws Exception;
	public void updateSASlide(PageData pd)throws Exception;
	public void deleteSASlide(PageData pd)throws Exception;
	public void changeStateSlide(PageData pd)throws Exception;
}
