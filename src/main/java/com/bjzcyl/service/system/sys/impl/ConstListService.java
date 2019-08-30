package com.bjzcyl.service.system.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.ConstListManager;
import com.bjzcyl.util.PageData;

@Service("ConstListService")
public class ConstListService implements ConstListManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> constListlistPage(Page page) throws Exception {
		return (List<PageData>)dao.findForList("ConstMapper.constListlistPage", page);
	}

	@Override
	public void deleteConstList(PageData pd) throws Exception {
		dao.delete("ConstMapper.deleteConstList", pd);
	}

	@Override
	public void saveConstList(PageData pd) throws Exception {
		dao.save("ConstMapper.saveConstList", pd);
	}

	@Override
	public void updateConstList(PageData pd) throws Exception {
		dao.update("ConstMapper.updateConstList", pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> allConstByType(PageData pd) throws Exception {
		return (List<PageData>)dao.findForList("ConstMapper.allConstByType", pd);
	}

	@Override
	public PageData find(PageData pd) throws Exception {
		return (PageData)dao.findForObject("ConstMapper.find", pd);
	}
	
}
