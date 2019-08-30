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
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.system.sys.NotiTemplateManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

import net.sf.json.JSONObject;
import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/sys")
public class NotiTemplateController extends BaseController {
	
	String menuUrl = "sys/listNotiTemplate";
	
	@Resource(name="LogService")
	private LogManager LogService;
	@Resource(name="NotiTemplateService")
	private NotiTemplateManager NotiTemplateService;
	
	
	@RequestMapping(value="/deleteNotiTemplate")
	@ResponseBody
	public Object deleteNotiTemplate() throws Exception{
		
		logBefore(logger, Jurisdiction.getUsername()+"delete_noti_template");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		try{
			NotiTemplateService.deleteNotiTemplate(pd);			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	@RequestMapping(value="/getNotiTemplateInfo")
	@ResponseBody
	public Object getNotiTemplateInfo() throws Exception{
		
		JSONObject res = new JSONObject();
		JSONObject obj = new JSONObject();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();		
		try{
			pd = NotiTemplateService.getNotiTemplateInfo(pd);
			obj.put("ID", pd.get("ID"));
			obj.put("NAME", pd.get("NAME"));
			obj.put("KIND", pd.get("KIND"));
			obj.put("AUTO_SEND", pd.get("AUTO_SEND"));
			obj.put("CONTENT", pd.get("CONTENT"));
			obj.put("TEMP", pd.get("TEMP"));
			obj.put("STATE", pd.get("STATE"));
			obj.put("REG_TIME", pd.get("REG_TIME"));
			obj.put("REG_MAN", pd.get("REG_MAN"));
			obj.put("SEND_TIME", pd.get("SEND_TIME"));
		}
		catch(Exception ex){ 
			errInfo = "error";
		}
		
		res.put("result", errInfo);
		res.put("data", obj);
		return res;
	}
	
	@RequestMapping(value="/sendNotify")
	@ResponseBody
	public Object sendNotify() throws Exception{
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			CurrentDateTime dt = new CurrentDateTime();
			String send_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
			pd = this.getPageData();
			pd.put("SEND_TIME", send_time);
			NotiTemplateService.sendNotify(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
	
	@RequestMapping(value="/listNotiTemplate")
	public ModelAndView listDict(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData> tempList = new ArrayList<PageData>();
		try{
			tempList = NotiTemplateService.notiTemplatelistPage(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/sys/noti_template_list");
		mv.addObject("tempList", tempList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/editNotiTemplate")
	public ModelAndView editNotiTemplate()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData template = new PageData();		
		
		try{
			template = NotiTemplateService.getNotiTemplateInfo(pd);
			
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/sys/noti_template");
		mv.addObject("template", template);
		mv.addObject("pd", pd);
		return mv;
		
	}
	
	@RequestMapping(value="/saveNotiTemplate")
	@ResponseBody
	public Object saveNotiTemplate() throws Exception{
		CurrentDateTime dt = new CurrentDateTime();
		String reg_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();		
		pd.put("REG_TIME", reg_time);
		pd.put("REG_MAN", this.getUserLGID());
		pd.put("SEND_TIME", "");
		
		try{
			if(pd.getString("ID").equals("")){
				pd.put("ID", this.get32UUID());
			}
			NotiTemplateService.saveNotiTemplate(pd);
		}
		catch(Exception ex){ 
			logger.error(ex.toString(), ex);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
}
