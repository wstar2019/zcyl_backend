package com.bjzcyl.controller.system.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.CustomerManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/sys")
public class CustomerController extends BaseController {
	
	String menuUrl = "sys/listCustomer";
	
	@Resource(name="CustomerService")
	private CustomerManager CustomerService;	

	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listCustomer")
	public ModelAndView listCustomer(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	customerList = new ArrayList<PageData>();
		try{
			customerList = CustomerService.customerlistPage(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}	
		
		mv.setViewName("system/sys/customer_list");
		mv.addObject("customerList", customerList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/deleteCustomer")
	@ResponseBody
	public Object deleteCustomer() throws Exception{
		
		logBefore(logger, Jurisdiction.getUsername()+"delete_customer");
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		try{
			CustomerService.deleteCustomer(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
}
