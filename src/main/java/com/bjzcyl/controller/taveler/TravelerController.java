package com.bjzcyl.controller.taveler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.client.Traveler;
import com.bjzcyl.service.system.art.SAArticleManager;
import com.bjzcyl.service.system.art.SASlidingManager;
import com.bjzcyl.service.system.landingpage.FPArtManager;
import com.bjzcyl.service.system.landingpage.FPSlidingManager;
import com.bjzcyl.service.system.landingpage.FPSpecialManager;
import com.bjzcyl.service.system.landingpage.FPTourManager;
import com.bjzcyl.service.system.sitnews.KPNewsManager;
import com.bjzcyl.service.system.sitnews.KPSituationManager;
import com.bjzcyl.service.system.special.SPArticleManager;
import com.bjzcyl.service.system.special.SPSlidingManager;
import com.bjzcyl.service.system.tour.TourArticleManager;
import com.bjzcyl.service.system.tour.TourArticleOptionManager;
import com.bjzcyl.service.system.tour.TourBookingLogManager;
import com.bjzcyl.service.system.tour.TourBookingManager;
import com.bjzcyl.service.system.tour.TourSlidingManager;
import com.bjzcyl.service.thumb.ThumbService;
import com.bjzcyl.service.traveler.TravelerManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.DateUtil;
import com.bjzcyl.util.FileUpload;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.PathUtil;
import com.bjzcyl.util.Tools;
import com.bjzcyl.util.TravelerConst;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/traveler")
public class TravelerController extends BaseController {
	
	@Resource(name="TravelerService")
	private TravelerManager TravelerService;
	
	@Resource(name="FPSlidingService")
	private FPSlidingManager FPSlidingService;	
	@Resource(name="FPSpecialService")
	private FPSpecialManager FPSpecialService;	
	@Resource(name="FPArtService")
	private FPArtManager FPArtService;	
	@Resource(name="FPTourService")
	private FPTourManager FPTourService;
	
	@Resource(name="SPSlidingService")
	private SPSlidingManager SPSlidingService;
	@Resource(name="spArticleService")
	private SPArticleManager spArticleService;
	/*@Resource(name="spArticleOptionService")
	private SPArticleOptionManager spArticleOptionService;*/
	
	@Resource(name="SASlidingService")
	private SASlidingManager SASlidingService;
	@Resource(name="saArticleService")
	private SAArticleManager saArticleService;
	
	@Resource(name="KPNewsService")
	private KPNewsManager KPNewsService;
	@Resource(name="KPSituationService")
	private KPSituationManager KPSituationService;
	
	@Resource(name="TourSlidingService")
	private TourSlidingManager TourSlidingService;
	@Resource(name="tourArticleService")
	private TourArticleManager tourArticleService;
	@Resource(name="tAOptionService")
	private TourArticleOptionManager tAOptionService;
	@Resource(name="tourBookingService")
	private TourBookingManager tourBookingService;
	@Resource(name="tourBookingLogService")
	private TourBookingLogManager tourBookingLogService;
	
    ///////////////////////   Traveler Feature	///////////////////////
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login() throws Exception {

		JSONObject map = new JSONObject();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		Traveler traveler = new Traveler();
		
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("RC03030514", "").replaceAll("19881994NH", "").split(",R3C5,");
		if (null != KEYDATA && KEYDATA.length == 3) {
			
			Session session = Jurisdiction.getSession();
			String sessionCode = (String) session.getAttribute(TravelerConst.SESSION_SECURITY_CODE);

			String code = KEYDATA[2];
			if (null == code || "".equals(code)) {
				errInfo = "nullcode";
			} else {
				String USERNAME = KEYDATA[0];				
				String PASSWORD = KEYDATA[1];				
				pd.put("USERNAME", USERNAME);
				if (Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)) {
					
					String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();
					pd.put("PASSWORD", passwd);
					
					try{
						pd = TravelerService.loginTraveler(pd);
					}catch(Exception e){
						errInfo = "usererror";
					}
					
					if (pd != null) {
						pd.put("LAST_LOGIN", DateUtil.getTime().toString());
						
						traveler = new Traveler();
						traveler.setID(pd.getString("ID"));
						traveler.setNAME(pd.getString("NAME"));
						traveler.setPASS(pd.getString("PASS"));
						traveler.setLG_ID(pd.getString("LG_ID"));
						traveler.setADDRESS(pd.getString("ADDRESS"));
						traveler.setTEL(pd.getString("TEL"));
						traveler.setAVATAR(pd.getString("AVATAR"));
						traveler.setSEX(pd.getString("SEX"));
						
						session.setAttribute(TravelerConst.SESSION_TRAVELER, traveler);
						session.removeAttribute(TravelerConst.SESSION_SECURITY_CODE);
						
						Subject subject = SecurityUtils.getSubject();
						UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD);
						try {
							subject.login(token);
						} catch (AuthenticationException e) {
							errInfo = "身份验证失败！";
						}
					} else {
						errInfo = "usererror";
						logBefore(logger, USERNAME + "登录系统密码或用户名错误");
					}
				} else {
					errInfo = "codeerror";
				}
				if (Tools.isEmpty(errInfo)) {
					errInfo = "success";
					logBefore(logger, USERNAME + "登录系统");
				}
			}
		} else {
			errInfo = "error";
		}
		map.put("result", errInfo);
		map.put("user", traveler);
		
		return map;
	}
	@RequestMapping(value="/regist")
	@ResponseBody
	public Object registTraveler() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		String errInfo = "success";
		PageData pd = new PageData();

		try{
			pd = this.getPageData();
			String passwd = new SimpleHash("SHA-1", pd.getString("LG_ID"), pd.getString("PASS")).toString();
			
			pd.put("ID", this.get32UUID());
			pd.put("PASS", passwd);
			pd.put("NAME", pd.getString("LG_ID"));
			pd.put("TEL", "");
			pd.put("ADDRESS", "");
			pd.put("SEX", "");
			pd.put("BIRTHDAY", "");
			pd.put("AVATAR", "");
			pd.put("STATE", "1");
			
			List<PageData> exists = new ArrayList<PageData>();

			try{
				exists = TravelerService.findByLGID(pd);
			}
			catch(Exception e){
				logger.error(e.toString(), e);
				errInfo = "error";
			}
			
			if( exists.size() != 0){
				errInfo = "error";
			}else{
				TravelerService.registTraveler(pd); 	
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}	
	@RequestMapping(value="/updateTraveler")
	@ResponseBody
	public Object updateTraveler(@RequestParam(value="AVATAR",required=false) MultipartFile avatar,
			DefaultMultipartHttpServletRequest serv) throws Exception{
		CurrentDateTime dt = new CurrentDateTime();

		JSONObject res = new JSONObject();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String filename = "";
		
		if (null != avatar && !avatar.isEmpty()) {
			CurrentDateTime ms = new CurrentDateTime();
			filename = dt.getTotalDate("") + dt.getTotalTime("") + ms.getMilliSecond();
			String filePath = PathUtil.getClasspath() + Const.CS_IMAGE_FILE_PATH;
			filename =  FileUpload.fileUp(avatar, filePath, filename);
			new ThumbService().thumb(filePath + filename, 100*100);
		}
		else
		{
			filename = serv.getParameter("AVATAR_NAME");
		}
		
		pd.put("ID", serv.getParameter("ID"));
		pd.put("NAME", serv.getParameter("NAME"));
		pd.put("TEL", serv.getParameter("TEL"));
		pd.put("ADDRESS", serv.getParameter("ADDRESS"));
		pd.put("SEX", serv.getParameter("SEX"));
		pd.put("BIRTHDAY", serv.getParameter("BIRTHDAY"));
		pd.put("AVATAR", filename);
		
		try{
			TravelerService.updateTraveler(pd);
		}
		catch(Exception ex){
			logger.error(ex.toString(), ex);
			errInfo = "error";
		}
		
		res.put("result", errInfo);
		return res;
	}
	@RequestMapping(value="/updatePassword")
	@ResponseBody
	public Object updatePassword() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"update_traveler_password");		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String passwd = new SimpleHash("SHA-1", pd.getString("LG_ID"), pd.getString("PASS")).toString();
			pd.put("PASS", passwd);
			TravelerService.updatePassword(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);		
	}
	@RequestMapping(value="/update_traveler")
	@ResponseBody
	public Object updateTraveler() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"update_traveler");		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String passwd = new SimpleHash("SHA-1", pd.getString("LG_ID"), pd.getString("PASS")).toString();
			pd.put("PASS", passwd);
			TravelerService.updateTraveler(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);		
	}
    ///////////////////////   Traveler Feature	///////////////////////
	
	///////////////////////  Landing Page- First Tab///////////////////////
	@RequestMapping(value = "/fp_slide")
	@ResponseBody
	public Object FPSlideImages() throws Exception {
		JSONArray _arr = new JSONArray();

		List<PageData> slides = new ArrayList<PageData>();

		try{
			slides = FPSlidingService.fpSlideAll();
			for(int i = 0; i < slides.size(); i++)
			{
				PageData _pd = slides.get(i);
				JSONObject obj = new JSONObject();
				obj.put("NAME", _pd.getString("name"));
				obj.put("ID", _pd.getString("id"));
				obj.put("VT", _pd.getString("view_type"));
				_arr.add(obj);
			}
			return _arr;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
		}

		return _arr;
	}
	@RequestMapping(value = "/fp_special")
	@ResponseBody
	public Object FPSpecial() throws Exception {
		JSONArray _arr = new JSONArray();
		
		List<PageData> spList = new ArrayList<PageData>();

		try{
			spList = FPSpecialService.getFPSpecial();
			for(int i = 0; i < spList.size(); i++)
			{
				PageData _pd = spList.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", _pd.getString("id"));
				obj.put("name", _pd.getString("name"));
				obj.put("desc", _pd.getString("comment"));
				obj.put("price", _pd.get("price"));
				obj.put("image", _pd.getString("main_image"));
				
				_arr.add(obj);
			}
			return _arr;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
		}
		return _arr;
	}
	@RequestMapping(value = "/fp_art")
	@ResponseBody
	public Object FPArt() throws Exception {
		JSONArray _arr = new JSONArray();

		List<PageData> artList = new ArrayList<PageData>();

		try{
			artList = FPArtService.getFPArt();
			for(int i = 0; i < artList.size(); i++)
			{
				PageData _pd = artList.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", _pd.getString("id"));
				obj.put("name", _pd.getString("name"));
				obj.put("desc", _pd.getString("comment"));
				obj.put("price", _pd.get("price"));
				obj.put("image", _pd.getString("main_image"));
				
				_arr.add(obj);
			}
			return _arr;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
		}
		return _arr;
	}
	@RequestMapping(value = "/fp_tour")
	@ResponseBody
	public Object FPTour() throws Exception {
		JSONArray _arr = new JSONArray();
		
		List<PageData> tourList = new ArrayList<PageData>();

		try{
			tourList = FPTourService.getFPTour();
			for(int i = 0; i < tourList.size(); i++)
			{
				PageData _pd = tourList.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", _pd.getString("id"));
				obj.put("name", _pd.getString("name"));
				obj.put("desc", _pd.getString("description"));
				obj.put("price", Integer.parseInt(_pd.get("adult_expense").toString()) + 
						Integer.parseInt(_pd.get("child_expense").toString()));
				obj.put("image", _pd.getString("main_image"));
				
				_arr.add(obj);
			}
			return _arr;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
		}

		return _arr;
	}
	@RequestMapping(value = "/fp_news")
	@ResponseBody
	public Object FPNews() throws Exception {
		JSONArray _arr = new JSONArray();
		
		List<PageData> newsList = new ArrayList<PageData>();

		try{
			newsList = KPNewsService.getFPNews();
			for(int i = 0; i < newsList.size(); i++)
			{
				PageData _pd = newsList.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", _pd.getString("id"));
				obj.put("title", _pd.getString("sn_title"));
				obj.put("content", _pd.getString("sn_content"));
				obj.put("image", _pd.getString("sn_image"));
				
				_arr.add(obj);
			}
			return _arr;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
		}

		return _arr;
	}
	///////////////////////   Landing Page- First Tab///////////////////////
	///////////////////////   Landing Page- Second Tab///////////////////////
	@RequestMapping(value = "/kp_tour_slide")
	@ResponseBody
	public Object KPTourSlideImages() throws Exception {
		JSONArray _arr = new JSONArray();
		List<PageData> slides = new ArrayList<PageData>();

		try{
			slides = TourSlidingService.tourSlideAll();
			for(int i = 0; i < slides.size(); i++)
			{
				PageData _pd = slides.get(i);
				JSONObject obj = new JSONObject();
				obj.put("NAME", _pd.getString("name"));
				obj.put("ID", _pd.getString("id"));
				_arr.add(obj);
			}
			return _arr;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		return _arr;
	}
	@RequestMapping(value = "/kp_tour_list")
	@ResponseBody
	public Object KPTourPageList() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("S_NUM", Integer.parseInt(pd.getString("S_NUM")));
		JSONObject result = new JSONObject();
		JSONArray _arr = new JSONArray();
		try{
			List<PageData> tour = new ArrayList<PageData>();
			PageData totalPd = new PageData();
			try{
				totalPd = tourArticleService.getTourListCountForTraveler(pd);
				int total = Integer.parseInt(totalPd.get("total").toString());
				if(total > 0){
					if(total < 6)
						pd.put("LEN", total);
					else
						pd.put("LEN", 6);
					
					tour = tourArticleService.getTourListForTraveler(pd);
					
					for(int i = 0; i < tour.size(); i++)
					{
						PageData _pd = tour.get(i);
						JSONObject obj = new JSONObject();
						obj.put("id", _pd.getString("id"));
						obj.put("name", _pd.getString("name"));
//						obj.put("desc", _pd.getString("description"));
						obj.put("a_exp", _pd.get("adult_expense"));
						obj.put("c_exp", _pd.get("child_expense"));
						obj.put("img", _pd.getString("main_image"));
						_arr.add(obj);
					}
				}
				
				result.put("total", total);
				result.put("data", _arr);
				return result;
			}
			catch(Exception e){
				logger.error(e.toString(), e);
				result.put("total", 0);
				result.put("data", new JSONArray() );
			}
		
		} catch(Exception e){
			logger.error(e.toString(), e);
			result.put("total", 0);
			result.put("data", new JSONArray() );
		}
		return result;
	}
	@RequestMapping(value = "/kp_tour_detail")
	@ResponseBody
	public Object KPTourDetail() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		JSONObject res = new JSONObject();
		JSONObject art = new JSONObject();
		String state = "error";
		
		try{
			PageData artPd = new PageData();
			List<PageData> artOpts = new ArrayList<PageData>();
			
			artPd = tourArticleService.findById(pd);
			artOpts = tAOptionService.listTAOption(pd);
			
			if(!artPd.isEmpty()){
				art.put("id", artPd.get("ID"));
				art.put("name", artPd.get("NAME"));
				art.put("desc", artPd.get("DESCRIPTION"));
				art.put("a_exp", artPd.get("ADULT_EXPENSE"));
				art.put("c_exp", artPd.get("CHILD_EXPENSE"));
				art.put("mImage", artPd.get("MAIN_IMAGE"));
				art.put("dImage", artPd.get("DETAIL_IMAGE"));
				art.put("s_feat", artPd.get("SERVICE_FEATURE"));
				art.put("t_info", artPd.get("TREATMENT_INFORMATION"));
				art.put("r_intr", artPd.get("ROUTE_INTRODUCTION"));
				art.put("e_incl", artPd.get("EXPENSE_INCLUDED"));
				art.put("b_note", artPd.get("BOOKING_NOTE"));
				art.put("p_url", artPd.get("PURCHASE_URL"));
				art.put("v_state", artPd.get("VIEW_STATE"));
				art.put("s_state", artPd.get("SALE_STATE"));
				art.put("register", artPd.get("REGISTER"));
				art.put("r_time", artPd.get("REGIST_TIME"));
				
				JSONArray taOptArr = new JSONArray();
				if(artOpts.size() > 0){
					for (PageData tAOption : artOpts) {
						JSONObject taOpt = new JSONObject();
						taOpt.put("option", tAOption.get("OPTION"));
						taOpt.put("value", tAOption.get("VAL"));
						taOptArr.add(taOpt);
					}
				}
				
				art.put("option", taOptArr);
				state = "success";
			}
			else{
				state = "no result";
			}
			
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			state = "error";
		}
		
		res.put("state", state);
		res.put("data", art );
		
		return res;
	}
	@RequestMapping(value = "/kp_tour_booking")
	@ResponseBody
	public Object KPTourBooking() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		JSONObject res = new JSONObject();
		CurrentDateTime dt = new CurrentDateTime();
		String bId = this.getTravlerName() + dt.getTotalDate("") + dt.getTotalTime("");
		String reg_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
		String state = "ok";
		
		try{
			PageData booking = new PageData();
			
			booking.put("ID", bId);
			booking.put("USER_ID", pd.get("user_id"));
			booking.put("ARTICLE_ID", pd.get("article_id"));
			booking.put("ADULT_NUM", pd.get("adult_num"));
			booking.put("CHILD_NUM", pd.get("child_num"));
			booking.put("TOTAL_EXPENSE", pd.get("total_expense"));
			booking.put("REUEST_DATE", reg_time);
			booking.put("START_DATE", pd.get("start_date"));
			booking.put("BOOKING_STATE", "0");
			
			tourBookingService.insertBooking(booking);
			
			PageData bookingLog = new PageData();
			bookingLog.put("ID", this.get32UUID());
			bookingLog.put("BOOKING_ID", bId);
			bookingLog.put("PRE_STATE", 0);
			bookingLog.put("CUR_STATE", 0);
			bookingLog.put("UPDATE_TIME", reg_time);
			bookingLog.put("COMMENT", "");
			
			tourBookingLogService.insertBookingLog(bookingLog);
			
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			state = "error";
		}
		
		res.put("success", state);
		res.put("booking_id", bId);
		return res;
	}
	
	///////////////////////   Landing Page- Second Tab///////////////////////
	///////////////////////   Landing Page- Third Tab///////////////////////
	@RequestMapping(value = "/kp_sp_slide")
	@ResponseBody
	public Object KPSPSlideImages() throws Exception {
		
		JSONArray _arr = new JSONArray();
		List<PageData> slides = new ArrayList<PageData>();
		
		try{
			slides = SPSlidingService.spSlideAll();
			for(int i = 0; i < slides.size(); i++)
			{
				PageData _pd = slides.get(i);
				JSONObject obj = new JSONObject();
				obj.put("NAME", _pd.getString("name"));
				obj.put("ID", _pd.getString("id"));
				_arr.add(obj);
			}
			return _arr;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		return _arr;
	}
	@RequestMapping(value = "/kp_sp_list")
	@ResponseBody
	public Object KPSPPageList() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("S_NUM", Integer.parseInt(pd.getString("S_NUM")));
		JSONObject result = new JSONObject();
		JSONArray _arr = new JSONArray();
		
		List<PageData> sp = new ArrayList<PageData>();
		PageData totalPd = new PageData();
		try{
			totalPd = spArticleService.getSPListCountForTraveler(pd);
			int total = Integer.parseInt(totalPd.get("total").toString());
			if(total > 0){
				if(total < 6)
					pd.put("LEN", total);
				else
					pd.put("LEN", 6);
				
				sp = spArticleService.getSPListForTraveler(pd);
				
				for(int i = 0; i < sp.size(); i++)
				{
					PageData _pd = sp.get(i);
					JSONObject obj = new JSONObject();
					obj.put("id", _pd.getString("id"));
					obj.put("name", _pd.getString("name"));
					obj.put("desc", _pd.getString("comment"));
					obj.put("price", _pd.get("price"));
					obj.put("img", _pd.getString("main_image"));
					_arr.add(obj);
				}
			}
			result.put("total", total);
			result.put("data", _arr);
			return result;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			result.put("total", 0);
			result.put("data", new JSONArray() );
		}
		return result;
	}
	
	@RequestMapping(value = "/kp_special_detail")
	@ResponseBody
	public Object KPSpecialDetail() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		JSONObject res = new JSONObject();
		JSONObject art = new JSONObject();
		String state = "error";
		
		try{
			PageData artPd = new PageData();
			artPd = spArticleService.findById(pd);
			if(!artPd.isEmpty()){
				art.put("id", artPd.get("ID"));
				art.put("name", artPd.get("NAME"));
				art.put("comment", artPd.get("COMMENT"));
				art.put("price", artPd.get("PRICE"));
				art.put("mImage", artPd.get("MAIN_IMAGE"));
				art.put("dImage", artPd.get("DETAIL_IMAGE"));
				art.put("service_feature", artPd.get("SERVICE_FEATURE"));
				art.put("description", artPd.get("DESCRIPTION"));
				art.put("size_pakcing", artPd.get("SIZE_PACKING"));
				art.put("purchase_url", artPd.get("PURCHASE_URL"));
				art.put("view_state", artPd.get("VIEW_STATE"));
				art.put("sale_state", artPd.get("SALE_STATE"));
				art.put("register", artPd.get("REGISTER"));
				art.put("regist_time", artPd.get("REGIST_TIME"));
				
				state = "success";
			}
			else{
				state = "no result";
			}
			
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			state = "error";
		}
		
		res.put("state", state);
		res.put("data", art );
		
		return res;
	}
	
	///////////////////////   Landing Page- Third Tab///////////////////////
	///////////////////////   Landing Page- Fourth Tab///////////////////////
	@RequestMapping(value = "/kp_sa_slide")
	@ResponseBody
	public Object KPSASlideImages() throws Exception {
		JSONArray _arr = new JSONArray();
		List<PageData> slides = new ArrayList<PageData>();
		
		try{
			slides = SASlidingService.saSlideAll();
			for(int i = 0; i < slides.size(); i++)
			{
				PageData _pd = slides.get(i);
				JSONObject obj = new JSONObject();
				obj.put("NAME", _pd.getString("name"));
				obj.put("ID", _pd.getString("id"));
				_arr.add(obj);
			}
			return _arr;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
		}
		return _arr;
	}
	
	@RequestMapping(value = "/kp_sa_list")
	@ResponseBody
	public Object KPSAPageList() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("S_NUM", Integer.parseInt(pd.getString("S_NUM")));
		JSONObject result = new JSONObject();
		JSONArray _arr = new JSONArray();
		
		List<PageData> sa = new ArrayList<PageData>();
		PageData totalPd = new PageData();
		try{
			totalPd = saArticleService.getSAListCountForTraveler(pd);
			int total = Integer.parseInt(totalPd.get("total").toString());
			if(total > 0){
				if(total < 6)
					pd.put("LEN", total);
				else
					pd.put("LEN", 6);
				
				sa = saArticleService.getSAListForTraveler(pd);
				
				for(int i = 0; i < sa.size(); i++)
				{
					PageData _pd = sa.get(i);
					JSONObject obj = new JSONObject();
					obj.put("id", _pd.getString("id"));
					obj.put("desc", _pd.getString("comment"));
					obj.put("name", _pd.getString("name"));
					obj.put("price", _pd.get("price"));
					obj.put("img", _pd.getString("main_image"));
					_arr.add(obj);
				}
			}
			result.put("total", total);
			result.put("data", _arr);
			return result;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			result.put("total", 0);
			result.put("data", new JSONArray() );
		}
		return result;
	}
	@RequestMapping(value = "/kp_art_detail")
	@ResponseBody
	public Object KPSADetail() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		JSONObject res = new JSONObject();
		JSONObject art = new JSONObject();
		String state = "error";
		
		try{
			PageData artPd = new PageData();
			artPd = saArticleService.findById(pd);
			if(!artPd.isEmpty()){
				art.put("id", artPd.get("ID"));
				art.put("name", artPd.get("NAME"));
				art.put("comment", artPd.get("COMMENT"));
				art.put("price", artPd.get("PRICE"));
				art.put("mImage", artPd.get("MAIN_IMAGE"));
				art.put("dImage", artPd.get("DETAIL_IMAGE"));
				art.put("service_feature", artPd.get("SERVICE_FEATURE"));
				art.put("description", artPd.get("DESCRIPTION"));
				art.put("size_pakcing", artPd.get("SIZE_PACKING"));
				art.put("purchase_url", artPd.get("PURCHASE_URL"));
				art.put("view_state", artPd.get("VIEW_STATE"));
				art.put("sale_state", artPd.get("SALE_STATE"));
				art.put("register", artPd.get("REGISTER"));
				art.put("regist_time", artPd.get("REGIST_TIME"));
				
				state = "success";
			}
			else{
				state = "no result";
			}
			
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			state = "error";
		}
		
		res.put("state", state);
		res.put("data", art );
		
		return res;
	}
	
	///////////////////////   Landing Page- Fourth Tab///////////////////////
	///////////////////////   Landing Page- Fifth Tab///////////////////////
	@RequestMapping(value = "/kp_sits_list")
	@ResponseBody
	public Object KPSitsList() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
//		pd.put("S_NUM", Integer.parseInt(pd.getString("S_NUM")));
		JSONObject result = new JSONObject();
		JSONArray _arr = new JSONArray();
		
		List<PageData> situations = new ArrayList<PageData>();
		
		try{
			situations = KPSituationService.getSitsListForTraveler(pd);
			
			for(int i = 0; i < situations.size(); i++)
			{
				PageData _pd = situations.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", _pd.getString("id"));
				obj.put("title", _pd.getString("sn_title"));
				_arr.add(obj);
			}
			result.put("data", _arr);
			return result;
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			result.put("data", new JSONArray() );
		}
		return result;
	}
	@RequestMapping(value = "/kp_sn_detail")
	@ResponseBody
	public Object KPSNDetail() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		JSONObject res = new JSONObject();
		JSONObject news = new JSONObject();
		String state = "error";
		
		
		PageData newsPd = new PageData();
		PageData prev = new PageData();
		PageData next = new PageData();
		
		if(pd.getString("TYPE").equals("1")){
			try{
				newsPd = KPSituationService.findById(pd);
				news.put("id", newsPd.get("ID"));
				news.put("content", newsPd.get("SN_CONTENT"));
				PageData m = new PageData();
				m.put("dt", newsPd.get("SN_DATETIME"));
				try{
					
					prev = KPSituationService.prevSituation(m);
					news.put("pid", prev.get("id"));
					news.put("pti", prev.get("sn_title"));
				}catch(Exception e){
					logger.error(e.toString(), e);
					news.put("pid", "");
					news.put("pti", "");
				}
				try{
					next = KPSituationService.nextSituation(m);
					news.put("nid", next.get("id"));
					news.put("nti", next.get("sn_title"));
				}catch(Exception e){
					logger.error(e.toString(), e);
					news.put("nid", "");
					news.put("nti", "");
				}
				state = "success";
			}catch(Exception e){
				logger.error(e.toString(), e);
				state = "error";
			}
				
		}else{
			try{
				newsPd = KPNewsService.findById(pd);
				news.put("id", newsPd.get("ID"));
				news.put("content", newsPd.get("SN_CONTENT"));
				
				PageData m = new PageData();
				m.put("dt", newsPd.get("SN_DATETIME"));
				
				try{
					prev = KPNewsService.prevNews(m);
					news.put("pid", prev.get("id"));
					news.put("pti", prev.get("sn_title"));
				}catch(Exception e){
					logger.error(e.toString(), e);
					news.put("pid", "");
					news.put("pti", "");
				}
				try{
					next = KPNewsService.nextNews(m);
					news.put("nid", next.get("id"));
					news.put("nti", next.get("sn_title"));
				}catch(Exception e){
					logger.error(e.toString(), e);
					news.put("nid", "");
					news.put("nti", "");
				}
				state = "success";
			}catch(Exception e){
				logger.error(e.toString(), e);
				state = "error";
			}
		}
		
		res.put("state", state);
		res.put("data", news );
		
		return res;
	}
	@RequestMapping(value = "/kp_news_list")
	@ResponseBody
	public Object KPNewsList() throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("S_NUM", Integer.parseInt(pd.getString("S_NUM")));
		JSONObject result = new JSONObject();
		JSONArray _arr = new JSONArray();
		
		List<PageData> news = new ArrayList<PageData>();
		PageData totalPd = new PageData();
		try{
			totalPd = KPNewsService.getFPListCount(pd);
			int total = Integer.parseInt(totalPd.get("total").toString());
			if(total > 0){
				if(total < 6)
					pd.put("LEN", total);
				else
					pd.put("LEN", 6);
				
				news = KPNewsService.getFPNewsList(pd);
				
				for(int i = 0; i < news.size(); i++)
				{
					PageData _pd = news.get(i);
					JSONObject obj = new JSONObject();
					obj.put("id", _pd.getString("id"));
					obj.put("title", _pd.getString("sn_title"));
					obj.put("sn_date", _pd.getString("sn_datetime"));
					obj.put("sn_image", _pd.getString("sn_image"));
					obj.put("sn_content", _pd.getString("sn_content"));
					
					_arr.add(obj);
				}
			}
			result.put("total", total);
			result.put("data", _arr);
			return result;			
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			result.put("data", new JSONArray() );
		}
		return result;
	}
	
	///////////////////////   Landing Page- Fifth Tab///////////////////////
}
