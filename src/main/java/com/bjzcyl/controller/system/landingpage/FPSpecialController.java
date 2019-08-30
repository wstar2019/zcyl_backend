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
import com.bjzcyl.service.system.landingpage.FPSpecialManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/fp")
public class FPSpecialController extends BaseController {
	
	String menuUrl = "fp/listFPSpecial";
	
	@Resource(name="FPSpecialService")
	private FPSpecialManager FPSpecialService;	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listDPRKSpecial")
	public ModelAndView listSpecial(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData> specialList = new ArrayList<PageData>();
		try{
			specialList = FPSpecialService.listFPSpecial(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/landingpage/special_list");
		mv.addObject("specialList", specialList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/update_sp_vs")
	@ResponseBody
	public Object updateFPSpecialViewState() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			FPSpecialService.updateFPSpecialViewState(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
}
