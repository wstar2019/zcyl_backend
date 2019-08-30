package com.bjzcyl.controller.system.tour;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping(value="/tour")
public class ArticleChangeLogController extends BaseController {
	
	String menuUrl = "tour/articleChangeLog";
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/articleChangeLog")
	public ModelAndView listTourArticle(Page page)throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		pd.put("AT_SORT", "tu_article");		
		page.setPd(pd);
		
		List<PageData> logList = new ArrayList<PageData>();
		try{
			logList = LogService.loglistByTourArticlePage(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/tour/tour_article_change_list");	
		mv.addObject("logList", logList);
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
	}
}
