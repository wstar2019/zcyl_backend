package com.bjzcyl.service.system.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjzcyl.dao.DaoSupport;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.CustomerManager;
import com.bjzcyl.util.PageData;

@Service("CustomerService")
public class CustomerService implements CustomerManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<PageData> customerlistPage(Page page)throws Exception{
		return (List<PageData>) dao.findForList("SysCustomerMapper.customerlistPage", page);
	}
	@Override
	public void deleteCustomer(PageData pd) throws Exception {
		dao.delete("SysCustomerMapper.deleteCustomer", pd);
	}
}
