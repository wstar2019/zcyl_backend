package com.bjzcyl.controller.system.art;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.service.system.art.SABookingLogManager;
import com.bjzcyl.service.system.art.SABookingManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.PageData;
import net.sf.json.JSONObject;
import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/sa")
public class SABookingLogController extends BaseController {
	
	String menuUrl = "sa/bookingLog";
	
	@Resource(name="LogService")
	private LogManager LogService;	
	@Resource(name="saBookingLogService")
	private SABookingLogManager saBookingLogService;
	@Resource(name="saBookingService")
	private SABookingManager saBookingService;	
	
	@RequestMapping(value="/saveBookingLog")
	@ResponseBody
	public Object saveBookinLog() throws Exception{
		JSONObject res = new JSONObject();
		String state = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		CurrentDateTime dt = new CurrentDateTime();
		String reg_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
		pd.put("UPDATE_TIME", reg_time);
		pd.put("ID", this.get32UUID());
		
		try{
			saBookingService.updateBookingState(pd);
			saBookingLogService.saveBookingLog(pd);
		}catch(Exception e){
			state = "error";
			logger.error(e.toString(), e);
		}
		
		res.put("result", state);		
		return res;
	}
}
