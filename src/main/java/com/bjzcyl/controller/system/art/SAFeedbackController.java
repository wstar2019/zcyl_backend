package com.bjzcyl.controller.system.art;

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
import com.bjzcyl.service.system.art.SAFeedbackManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/sa")
public class SAFeedbackController extends BaseController {
	
	String menuUrl = "sa/listFeedback";
	
	@Resource(name="saFeedbackService")
	private SAFeedbackManager saFeedbackService;

	@Resource(name="LogService")
	private LogManager LogService;
	

	@RequestMapping(value="/listFeedback")
	public ModelAndView listFeedback(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	feedbackList = null;
		try{
			feedbackList = saFeedbackService.listFeedback(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/art/feedback_list");
		mv.addObject("feedbackList", feedbackList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/deleteFeedback")
	@ResponseBody
	public Object deleteFeedback() throws Exception{
		
		logBefore(logger, Jurisdiction.getUsername()+"delete__feedback");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try{
			saFeedbackService.deleteFeedback(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
}
