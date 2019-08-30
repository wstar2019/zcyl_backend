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
import com.bjzcyl.service.system.sys.MsgTemplateManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;
import net.sf.json.JSONObject;
import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/sys")
public class MsgTemplateController extends BaseController {
	
	String menuUrl = "sys/listMsgTemplate";
	
	@Resource(name="LogService")
	private LogManager LogService;
	@Resource(name="MsgTemplateService")
	private MsgTemplateManager MsgTemplateService;
	
	@RequestMapping(value="/deleteMsgTemplate")
	@ResponseBody
	public Object deleteMsgTemplate() throws Exception{
		
		logBefore(logger, Jurisdiction.getUsername()+"delete_msg_template");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		try{
			MsgTemplateService.deleteMsgTemplate(pd);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	@RequestMapping(value="/getMsgTemplateInfo")
	@ResponseBody
	public Object getMsgTemplateInfo() throws Exception{
		
		JSONObject res = new JSONObject();
		JSONObject obj = new JSONObject();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();		
		try{
			pd = MsgTemplateService.getMsgTemplateInfo(pd);
			obj.put("ID", pd.get("ID"));
			obj.put("NAME", pd.get("NAME"));
			obj.put("KIND", pd.get("KIND"));
			obj.put("VAR", pd.get("VAR"));
			obj.put("CONTENT", pd.get("CONTENT"));
			obj.put("TEMP", pd.get("TEMP"));
			obj.put("STATE", pd.get("STATE"));
			obj.put("REG_TIME", pd.get("REG_TIME"));
			obj.put("REG_MAN", pd.get("REG_MAN"));
		}
		catch(Exception e){ 
			errInfo = "error";
			logger.error(e.toString(), e);
		}
		
		res.put("result", errInfo);
		res.put("data", obj);
		return res;
	}
	@RequestMapping(value="/changeMsgTemplateState")
	@ResponseBody
	public Object changeMsgTemplateState() throws Exception{
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			MsgTemplateService.changeMsgTemplateState(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
	
	@RequestMapping(value="/listMsgTemplate")
	public ModelAndView listMsgTemplate(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData> tempList = new ArrayList<PageData>();
		try{
			tempList = MsgTemplateService.msgTemplatelistPage(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/sys/msg_template_list");
		mv.addObject("tempList", tempList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
	}
	
	@RequestMapping(value="/editMsgTemplate")
	public ModelAndView editMsgTemplate()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData template = new PageData();		
		
		try{
			template = MsgTemplateService.getMsgTemplateInfo(pd);
			
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/sys/msg_template");
		mv.addObject("template", template);
		mv.addObject("pd", pd);
		return mv;
		
	}
	
	@RequestMapping(value="/saveMsgTemplate")
	@ResponseBody
	public Object saveMsgTemplate() throws Exception{
		CurrentDateTime dt = new CurrentDateTime();
		String reg_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();		
		pd.put("REG_TIME", reg_time);
		pd.put("REG_MAN", this.getUserLGID());
		
		try{
			if(pd.getString("ID").equals("")){
				pd.put("ID", this.get32UUID());
			}
			MsgTemplateService.saveMsgTemplate(pd);
		}
		catch(Exception e){ 
			errInfo = "error";
			logger.error(e.toString(), e);
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
}
