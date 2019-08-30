package com.bjzcyl.controller.system.art;

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
@RequestMapping(value="/sa")
public class SABuyController extends BaseController {
	
	String menuUrl = "sa/listBuy";
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listBuy")
	public ModelAndView listArticle(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		mv.setViewName("system/art/buy_list");
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
}
