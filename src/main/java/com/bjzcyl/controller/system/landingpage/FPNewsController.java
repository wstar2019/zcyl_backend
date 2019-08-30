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
import com.bjzcyl.service.system.sitnews.KPNewsManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/fp")
public class FPNewsController extends BaseController {
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@Resource(name="KPNewsService")
	private KPNewsManager KPNewsService;
	
	@RequestMapping(value="/listDPRKNews")
	public ModelAndView listTour(Page page)throws Exception{				
			
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData> newsList = new ArrayList<PageData>();
		try{
			newsList = KPNewsService.kpNewslistPage(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/landingpage/news_list");
		mv.addObject("newsList", newsList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/update_news_vs")
	@ResponseBody
	public Object updateFPArtViewState() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			KPNewsService.updateTravelerFPViewState(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
		
}
