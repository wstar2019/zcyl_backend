package com.bjzcyl.service.system.special.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.system.SPClass;
import com.bjzcyl.service.system.special.SPClassManager;
import com.bjzcyl.util.PageData;


@Service("spClassService")
public class SPClassService implements SPClassManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> getClasslistPage(Page page)throws Exception{
		return ((List<PageData>) dao.findForList("SPClassMapper.classlistPage", page));
	}	
	@Override
	public void saveClass(PageData pd) throws Exception {
		dao.save("SPClassMapper.saveClass", pd);		
	}
	@Override
	public void updateClass(PageData pd) throws Exception {
		dao.update("SPClassMapper.updateClass", pd);
	}
	@Override
	public void deleteClass(PageData pd) throws Exception {
		dao.update("SPClassMapper.deleteClass", pd);
	}		
	@Override
	public PageData findClassById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SPClassMapper.getClassById", pd);
	}	
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listClassByUpperId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SPClassMapper.getClassByUpperId", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<SPClass> listSubClassByParentId(String parentId) throws Exception {
		return (List<SPClass>) dao.findForList("SPClassMapper.listSubClassByParentId", parentId);
	}
	
	@Override
	public List<SPClass> listAllClass(String parentId) throws Exception {
		List<SPClass> classList = this.listSubClassByParentId(parentId);			
		for(SPClass cl : classList){
			cl.setSubClass(this.listAllClass(cl.getCLASS_ID()));			
		}
		return classList;
	}

}
