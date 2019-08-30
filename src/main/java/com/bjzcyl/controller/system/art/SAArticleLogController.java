package com.bjzcyl.controller.system.art;

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
@RequestMapping(value="/sa")
public class SAArticleLogController extends BaseController {
	
	String menuUrl = "sa/listArticleUpdate";
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listArticleUpdate")
	public ModelAndView listArticle(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("AT_SORT", "sa_article");
				
		page.setPd(pd);
		
		List<PageData> logList = new ArrayList<PageData>();
		try{
			logList = LogService.loglistBySAArticlePage(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
				
		mv.setViewName("system/art/article_change_list");
		mv.addObject("logList", logList);	
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
}
