package com.bjzcyl.controller.system.special;

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
@RequestMapping(value="/sp")
public class SPArticleLogController extends BaseController {
	
	String menuUrl = "sp/listArticleUpdate";
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listArticleUpdate")
	public ModelAndView listArticle(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("AT_SORT", "sp_article");
		
		page.setPd(pd);
		
		List<PageData> logList = new ArrayList<PageData>();
		try{
			logList = LogService.loglistBySPArticlePage(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/special/article_change_list");
		mv.addObject("logList", logList);	
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
}
