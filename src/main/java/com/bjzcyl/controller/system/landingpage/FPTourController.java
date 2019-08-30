package com.bjzcyl.controller.system.landingpage;

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
import com.bjzcyl.service.system.landingpage.FPTourManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/fp")
public class FPTourController extends BaseController {
	
	String menuUrl = "fp/listFPTour";
	
	@Resource(name="FPTourService")
	private FPTourManager FPTourService;	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listDPRKTour")
	public ModelAndView listTour(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData> tourList = new ArrayList<PageData>();
		try{
			tourList = FPTourService.listFPTour(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/landingpage/tour_list");
		mv.addObject("tourList", tourList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/update_tour_vs")
	@ResponseBody
	public Object updateFPTourViewState() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try{
			FPTourService.updateFPTourViewState(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}	
}
