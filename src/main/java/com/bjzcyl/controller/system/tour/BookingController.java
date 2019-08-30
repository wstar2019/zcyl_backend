package com.bjzcyl.controller.system.tour;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.ConstListManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.system.tour.TourBookingLogManager;
import com.bjzcyl.service.system.tour.TourBookingManager;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/tour")
public class BookingController extends BaseController {
	
	String menuUrl = "tour/listTourBooking";
	
	@Resource(name="tourBookingService")
	private TourBookingManager tourBookingService;	
	@Resource(name="tourBookingLogService")
	private TourBookingLogManager tourBookingLogService;
	@Resource(name="ConstListService")
	private ConstListManager ConstListService;
	@Resource(name="LogService")
	private LogManager LogService;

	@RequestMapping(value="/listTourBooking")
	public ModelAndView listTourBooking(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	tourBookingList = null;
		List<PageData> booking_state = new ArrayList<PageData>();
		
		try{
			tourBookingList = tourBookingService.listTourBooking(page);	
			pd.put("CONST_TYPE", "booking_state");
			booking_state = ConstListService.allConstByType(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/tour/tour_booking_list");
		mv.addObject("tourBookingList", tourBookingList);		
		mv.addObject("booking_state", booking_state);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/editTourBooking")
	public ModelAndView editTourBooking()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData booking = new PageData();
		List<PageData> logs = new ArrayList<PageData>();
		List<PageData> cList = new ArrayList<PageData>();
		JSONArray cArray = new JSONArray();
		try{
			booking = tourBookingService.findById(pd);
			logs = tourBookingLogService.findByBookingId(pd);
			PageData cType = new PageData();
			cType.put("CONST_TYPE", "booking_state");
			cList = ConstListService.allConstByType(cType);
			
			for (PageData c : cList) {
				JSONObject co = new JSONObject();
				co.put("value", c.get("CONST_VALUE"));
				co.put("name", c.get("CONST_NAME"));
				cArray.add(co);
			}
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/tour/tour_booking");
		mv.addObject("booking", booking);
		mv.addObject("logs", logs);
		mv.addObject("cList", cArray);
		return mv;
		
	}
}
