package com.bjzcyl.service.system.special;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface SPSlidingManager {
	public List<PageData> listSPSlide(Page page)throws Exception;
	public List<PageData> spSlideAll() throws Exception;
	public void saveSPSlide(PageData pd)throws Exception;
	public void updateSPSlide(PageData pd)throws Exception;
	public void deleteSPSlide(PageData pd)throws Exception;
	public void changeStateSlide(PageData pd)throws Exception;
}
