package com.bjzcyl.service.system.landingpage;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface FPTourManager {
	public List<PageData> listFPTour(Page page)throws Exception;
	public List<PageData> fpTourAll() throws Exception;
	public List<PageData> getFPTour() throws Exception;
	public void saveFPTour(PageData pd)throws Exception;
	public void updateFPTour(PageData pd)throws Exception;
	public void updateFPTourViewNum(PageData pd)throws Exception;
	public void updateFPTourViewState(PageData pd)throws Exception;
	public void deleteFPTour(PageData pd)throws Exception;
	public List<PageData> hasFPTour(PageData pd ) throws Exception;
}
