package com.bjzcyl.service.system.landingpage;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface FPArtManager {
	public List<PageData> listFPArt(Page page)throws Exception;
	public List<PageData> fpArtAll() throws Exception;
	public List<PageData> getFPArt() throws Exception;
	public void saveFPArt(PageData pd)throws Exception;
	public void updateFPArt(PageData pd)throws Exception;
	public void updateFPArtViewNum(PageData pd)throws Exception;
	public void updateFPArtViewState(PageData pd)throws Exception;
	public void deleteFPArt(PageData pd)throws Exception;
	public List<PageData> hasFPArt(PageData pd ) throws Exception;
}
