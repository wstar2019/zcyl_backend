package com.bjzcyl.controller.system.tour;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.system.tour.TourBookingLogManager;
import com.bjzcyl.service.system.tour.TourBookingManager;
import com.bjzcyl.util.PageData;

import net.sf.json.JSONObject;
import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/tour")
public class BookingLogController extends BaseController {
	
	String menuUrl = "tour/listTourBooking";
	@Resource(name="tourBookingLogService")
	private TourBookingLogManager tourBookingLogService;
	@Resource(name="tourBookingService")
	private TourBookingManager tourBookingService;
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value = "/insert_booking_log")
	@ResponseBody
	public Object InsertBookingLog() throws Exception{
		JSONObject res = new JSONObject();
		String state = "ok";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			pd.put("ID", this.get32UUID());
			CurrentDateTime dt = new CurrentDateTime();
			String reg_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
			pd.put("UPDATE_TIME", reg_time);
			tourBookingLogService.insertBookingLog(pd);
			
			PageData booking = new PageData();
			booking.put("ID", pd.get("BOOKING_ID"));
			booking.put("BOOKING_STATE", pd.get("CUR_STATE"));
			tourBookingService.updateBookingState(booking);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			state = "failed";
		}
		
		res.put("success", state);
		return res;
	}
	
}
