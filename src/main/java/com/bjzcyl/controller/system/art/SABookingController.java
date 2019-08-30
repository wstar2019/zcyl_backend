package com.bjzcyl.controller.system.art;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.art.SABookingLogManager;
import com.bjzcyl.service.system.art.SABookingManager;
import com.bjzcyl.service.system.sys.ConstListManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/sa")
public class SABookingController extends BaseController {
	
	String menuUrl = "sa/listBooking";
	
	@Resource(name="saBookingService")
	private SABookingManager saBookingService;	
	@Resource(name="saBookingLogService")
	private SABookingLogManager saBookingLogService;	
	@Resource(name="LogService")
	private LogManager LogService;
	@Resource(name="ConstListService")
	ConstListManager ConstListService;
	
	@RequestMapping(value="/listBooking")
	public ModelAndView listBooking(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	bookingList = null;
		List<PageData> booking_state = new ArrayList<PageData>();
		
		try{
			bookingList = saBookingService.listBooking(page);	
			pd.put("CONST_TYPE", "booking_state");
			booking_state = ConstListService.allConstByType(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/art/booking_list");
		mv.addObject("bookingList", bookingList);		
		mv.addObject("booking_state", booking_state);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/editBooking")
	public ModelAndView editBooking()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData booking = new PageData();
		List<PageData> logs = new ArrayList<PageData>();
		
		try{
			booking = saBookingService.findById(pd);
			logs = saBookingLogService.findByBookingId(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/art/booking");
		mv.addObject("booking", booking);
		mv.addObject("logs", logs);
		mv.addObject("pd", pd);
		
		return mv;
		
	}
}
