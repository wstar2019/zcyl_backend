package com.bjzcyl.service.system.landingpage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.landingpage.FPSpecialManager;
import com.bjzcyl.util.PageData;

@Service("FPSpecialService")
public class FPSpecialService implements FPSpecialManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> listFPSpecial(Page page) throws Exception {
		return (List<PageData>) dao.findForList("FPSpecialMapper.fpspeciallistPage", page);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> fpSpecialAll() throws Exception {
		return (List<PageData>) dao.findForList("FPSpecialMapper.fpSpecialAll", null);
	}
	@SuppressWarnings("unchecked")
	public List<PageData> getFPSpecial() throws Exception {
		return (List<PageData>) dao.findForList("FPSpecialMapper.getFPSpecial", null);
	}
	@Override
	public void saveFPSpecial(PageData pd) throws Exception {
		dao.save("FPSpecialMapper.saveFPSpecial", pd);
	}

	@Override
	public void updateFPSpecial(PageData pd) throws Exception {
		dao.save("FPSpecialMapper.updateFPSpecial", pd);
	}
	@Override
	public void updateFPSpecialViewNum(PageData pd) throws Exception {
		dao.save("FPSpecialMapper.updateFPSpecialViewNum", pd);
	}
	@Override
	public void updateFPSpecialViewState(PageData pd) throws Exception {
		dao.save("FPSpecialMapper.updateFPSpecialViewState", pd);
	}
	@Override
	public void deleteFPSpecial(PageData pd) throws Exception {
		dao.delete("FPSpecialMapper.deleteFPSpecial", pd);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> hasFPSpecial(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("FPSpecialMapper.hasFPSpecial", pd);
	}

}
