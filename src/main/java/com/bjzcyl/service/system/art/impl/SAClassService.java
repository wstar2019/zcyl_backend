package com.bjzcyl.service.system.art.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.entity.system.SAClass;
import com.bjzcyl.service.system.art.SAClassManager;
import com.bjzcyl.util.PageData;


@Service("saClassService")
public class SAClassService implements SAClassManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> getClasslistPage(Page page)throws Exception{
		return ((List<PageData>) dao.findForList("SAClassMapper.classlistPage", page));
	}	
	@Override
	public void saveClass(PageData pd) throws Exception {
		dao.save("SAClassMapper.saveClass", pd);		
	}
	@Override
	public void updateClass(PageData pd) throws Exception {
		dao.update("SAClassMapper.updateClass", pd);
	}
	@Override
	public void deleteClass(PageData pd) throws Exception {
		dao.update("SAClassMapper.deleteClass", pd);
	}		
	@Override
	public PageData findClassById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("SAClassMapper.getClassById", pd);
	}	
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> listClassByUpperId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("SAClassMapper.getClassByUpperId", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<SAClass> listSubClassByParentId(String parentId) throws Exception {
		return (List<SAClass>) dao.findForList("SAClassMapper.listSubClassByParentId", parentId);
	}
	
	@Override
	public List<SAClass> listAllClass(String parentId) throws Exception {
		List<SAClass> classList = this.listSubClassByParentId(parentId);			
		for(SAClass cl : classList){
			cl.setSubClass(this.listAllClass(cl.getCLASS_ID()));			
		}
		return classList;
	}

}
