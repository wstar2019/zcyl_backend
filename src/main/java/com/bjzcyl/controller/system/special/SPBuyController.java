package com.bjzcyl.controller.system.special;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/sp")
public class SPBuyController extends BaseController {
	
	String menuUrl = "sp/listBuy";
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listBuy")
	public ModelAndView listArticle(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		mv.setViewName("system/special/buy_list");
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
}
