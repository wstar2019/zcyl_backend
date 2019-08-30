package com.bjzcyl.service.system.landingpage;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface FPSpecialManager {
	public List<PageData> listFPSpecial(Page page)throws Exception;
	public List<PageData> fpSpecialAll() throws Exception;
	public List<PageData> getFPSpecial() throws Exception;
	public void saveFPSpecial(PageData pd)throws Exception;
	public void updateFPSpecial(PageData pd)throws Exception;
	public void updateFPSpecialViewNum(PageData pd)throws Exception;
	public void updateFPSpecialViewState(PageData pd)throws Exception;
	public void deleteFPSpecial(PageData pd)throws Exception;
	public List<PageData> hasFPSpecial(PageData pd ) throws Exception;
}
