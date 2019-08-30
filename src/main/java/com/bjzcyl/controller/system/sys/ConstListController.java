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
import com.bjzcyl.service.system.sys.ConstListManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/sys")
public class ConstListController extends BaseController {
	
	String menuUrl = "sys/listConst";
	
	@Resource(name="ConstListService")
	private ConstListManager ConstListService;	

	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listConst")
	public ModelAndView constListlistPage(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	constList = new ArrayList<PageData>();
		try{
			constList = ConstListService.constListlistPage(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/sys/const_list");
		mv.addObject("constList", constList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/saveConstList")
	@ResponseBody
	public Object saveConstList() throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;}
		logBefore(logger, Jurisdiction.getUsername()+"save_backup");
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();

		try{
			pd = this.getPageData();
			ConstListService.saveConstList(pd);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
		
	}
	
	@RequestMapping(value="/deleteConstList")
	@ResponseBody
	public Object deleteConstList() throws Exception{
		
		logBefore(logger, Jurisdiction.getUsername()+"delete_backup");
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			ConstListService.deleteConstList(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/allConstByType")
	@ResponseBody
	public Object allConstByType() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> consts = new ArrayList<PageData>();
		try{			
			consts = ConstListService.allConstByType(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		//map.put("data", consts);
		return AppUtil.returnObject(new PageData(), map);
	}
	
}
