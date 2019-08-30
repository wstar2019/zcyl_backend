package com.bjzcyl.controller.system.art;

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
import com.bjzcyl.service.system.art.SAOptionManager;
import com.bjzcyl.service.system.art.SAOptionValueManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/sa")
public class SAOptionController extends BaseController {
	
	String menuUrl = "sa/listOption";
	
	@Resource(name="saOptionService")
	private SAOptionManager saOptionService;
	
	@Resource(name="saOptionValueService")
	private SAOptionValueManager saOptionValueService;
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listOption")
	public ModelAndView listOption(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		
		page.setPd(pd);
		
		List<PageData>	optionList = new ArrayList<PageData>();
		try{
			optionList = saOptionService.listOption(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/art/option_list");
		mv.addObject("optionList", optionList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/saveOpt")
	@ResponseBody
	public Object saveOpt() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();

		try{
			pd = this.getPageData();
			if(saOptionService.findByName(pd) != null){
				errInfo = "error";
			}else{
				saOptionService.saveOpt(pd);
				LogService.insertLog(this.get32UUID(), this.getUserLGID(), menuUrl,
						Const.OPERATION_ADD, pd.getString("NAME"), "", "");
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);

	}
	
	@RequestMapping(value="/hasOpt")
	@ResponseBody
	public Object hasOpt(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(saOptionService.findByName(pd) != null){
				errInfo = "success";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/deleteOpt")
	@ResponseBody
	public Object deleteOpt() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"delete__option");

		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			saOptionService.deleteOpt(pd);
			pd.put("MID", pd.get("ID"));
			pd.remove("ID");
			saOptionValueService.delOptValWithMID(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/editOpt")
	@ResponseBody
	public Object editOpt() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"edit__option");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			PageData opt = saOptionService.findByName(pd);
			if(opt != null){
				if(opt.getString("ID").equals(pd.getString("ID"))){
					saOptionService.editOpt(pd);
				}
				else{
					errInfo = "error";
				}
			} else {
				saOptionService.editOpt(pd);
				LogService.insertLog(this.get32UUID(), this.getUserLGID(), menuUrl,
						Const.OPERATION_ADD, pd.getString("OPTION"), "", "");
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
	
	@RequestMapping(value="/changeStateOpt")
	@ResponseBody
	public Object changeStateOpt() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"change__option");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(saOptionService.findByName(pd) != null){
				errInfo = "error";
			} else {
				saOptionService.changeStateOpt(pd);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
	
	@RequestMapping(value="/deleteAllOpt")
	@ResponseBody
	public Object deleteAllOpt() throws Exception {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;}
		logBefore(logger, Jurisdiction.getUsername()+"delete_all__option");
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String IDS = pd.getString("IDS");
		if(null != IDS && !"".equals(IDS)){
			String ArrayIDS[] = IDS.split(",");
			try{
				saOptionService.deleteAllOpt(ArrayIDS);
				pd.put("msg", "ok");
			}catch(Exception e){
				pd.put("msg", "no");
				logger.error(e.toString(), e);
			}
			
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

}
