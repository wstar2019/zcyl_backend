package com.bjzcyl.controller.system.tour;

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
import com.bjzcyl.service.system.tour.TourOptionManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/tour")
public class OptionController extends BaseController {
	
	String menuUrl = "tour/listTourOption";
	
	@Resource(name="tourOptionService")
	private TourOptionManager tourOptionService;
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	
	@RequestMapping(value="/listTourOption")
	public ModelAndView listTourOption(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String keywords = pd.getString("keywords");
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		
		page.setPd(pd);
		List<PageData>	tourOptionList = tourOptionService.listTourOption(page);		
		
		mv.setViewName("system/tour/tour_option_list");
		mv.addObject("tourOptionIndexList", tourOptionList);
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
			pd.put("ID", this.get32UUID());
			if(tourOptionService.findByName(pd) != null){
				errInfo = "error";
			}else{
				tourOptionService.saveOpt(pd);
				LogService.insertLog(this.get32UUID(), this.getUserLGID(), menuUrl, Const.OPERATION_ADD, "content", "", "");
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
			if(tourOptionService.findByName(pd) != null){
				errInfo = "error";
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
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		try{
			tourOptionService.deleteOpt(pd);
		}
		 catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}

		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/editOpt")
	@ResponseBody
	public Object editOpt() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改tour_option");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		PageData temp = new PageData();
		try{
			pd = this.getPageData();
			temp = tourOptionService.findByName(pd);
			if(temp != null){
				if(temp.get("ID") == pd.get("ID")){
					tourOptionService.editOpt(pd);
				}else{
					errInfo = "error";
				}
			} else {
				tourOptionService.editOpt(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"修改tour_option");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(tourOptionService.findByName(pd) != null){
				errInfo = "error";
			} else {
				tourOptionService.changeStateOpt(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"批量删除tour_option");
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String IDS = pd.getString("IDS");
		if(null != IDS && !"".equals(IDS)){
			String ArrayIDS[] = IDS.split(",");
			tourOptionService.deleteAllOpt(ArrayIDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

}
