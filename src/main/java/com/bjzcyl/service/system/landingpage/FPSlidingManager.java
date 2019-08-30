package com.bjzcyl.service.system.landingpage;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface FPSlidingManager {
	public List<PageData> listFPSlide(Page page)throws Exception;
	public List<PageData> fpSlideAll() throws Exception;
	public void saveFPSlide(PageData pd)throws Exception;
	public void updateFPSlide(PageData pd)throws Exception;
	public void deleteFPSlide(PageData pd)throws Exception;
}
