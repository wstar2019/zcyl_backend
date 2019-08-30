package com.bjzcyl.controller.system.tour;

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
import com.bjzcyl.service.system.tour.TourFeedbackManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/tour")
public class FeedbackController extends BaseController {
	
	String menuUrl = "tour/listTourFeedback";
	@Resource(name="tourFeedbackService")
	private TourFeedbackManager tourFeedbackService;
	@Resource(name="LogService")
	private LogManager LogService;

	@RequestMapping(value="/listTourFeedback")
	public ModelAndView listTourFeedback(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	tourFeedbackList = null;
		try{
			tourFeedbackList = tourFeedbackService.listTourFeedback(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/tour/tour_feedback_list");
		mv.addObject("tourFeedbackList", tourFeedbackList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/deleteFeedback")
	@ResponseBody
	public Object deleteFeedback() throws Exception{
		
		logBefore(logger, Jurisdiction.getUsername()+"delete_tour_feedback");
		
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		try{
			tourFeedbackService.deleteFeedback(pd);
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}

		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/viewFeedback")
	public ModelAndView viewSN()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData feedback = new PageData();		
		
		try{
			feedback = tourFeedbackService.findById(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.addObject("feedback", feedback);
		mv.setViewName("system/tour/tour_feedback_detail");
		
		mv.addObject("pd", pd);
		return mv;		
	}
}
