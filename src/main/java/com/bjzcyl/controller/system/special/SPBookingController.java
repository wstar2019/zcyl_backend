package com.bjzcyl.controller.system.special;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.special.SPBookingLogManager;
import com.bjzcyl.service.system.special.SPBookingManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/sp")
public class SPBookingController extends BaseController {
	
	String menuUrl = "sp/listBooking";
	
	@Resource(name="spBookingService")
	private SPBookingManager spBookingService;
	
	@Resource(name="spBookingLogService")
	private SPBookingLogManager spBookingLogService;
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listBooking")
	public ModelAndView listBooking(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	bookingList = null;
		List<PageData> booking_state = new ArrayList<PageData>();
		
		try{
			bookingList = spBookingService.listBooking(page);	
			pd.put("CONST_TYPE", "booking_state");
//			booking_state = constService.listAllConstByType(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/special/booking_list");
		mv.addObject("bookingList", bookingList);		
		mv.addObject("booking_state", booking_state);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/booking")
	public ModelAndView editBooking()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData booking = new PageData();
		List<PageData> logs = new ArrayList<PageData>();
		
		try{
			booking = spBookingService.findById(pd);
			logs = spBookingLogService.findByBookingId(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/special/booking");
		mv.addObject("booking", booking);
		mv.addObject("logs", logs);
		mv.addObject("pd", pd);
		
		return mv;
		
	}
}
