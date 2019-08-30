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
import com.bjzcyl.service.system.tour.TourTouristManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/tour")
public class TouristController extends BaseController {
	
	String menuUrl = "tour/listTourTourist";
	
	@Resource(name="tourTouristService")
	private TourTouristManager tourTouristService;	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listTourTourist")
	public ModelAndView listTourOption(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	tourTouristList = tourTouristService.listTourTourist(page);		
		
		mv.setViewName("system/tour/tour_tourist_list");
		mv.addObject("tourTouristList", tourTouristList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/saveTourist")
	@ResponseBody
	public Object saveTourist() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();

		try{
			pd = this.getPageData();
			pd.put("ID", this.get32UUID());
			if(tourTouristService.findByName(pd) != null){
				errInfo = "error";
			}else{
				tourTouristService.saveTourist(pd); 	
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);

	}
	
	@RequestMapping(value="/hasTourist")
	@ResponseBody
	public Object hasTourist(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(tourTouristService.findByName(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/deleteTourist")
	@ResponseBody
	public Object deleteOpt() throws Exception{
		
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		try{
			tourTouristService.deleteTourist(pd);
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}

		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/editTourist")
	@ResponseBody
	public Object editTourist() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(tourTouristService.findByName(pd) != null){
				errInfo = "error";
			} else {
				tourTouristService.editTourist(pd);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
}
