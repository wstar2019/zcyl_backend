package com.bjzcyl.controller.system.special;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.service.system.special.SPBookingLogManager;
import com.bjzcyl.service.system.sys.LogManager;

@Controller
@RequestMapping(value="/sp")
public class SPBookingLogController extends BaseController {
	
	String menuUrl = "sp/bookingLog.do";
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@Resource(name="spBookingLogService")
	private SPBookingLogManager spBookingLogService;
	
}
