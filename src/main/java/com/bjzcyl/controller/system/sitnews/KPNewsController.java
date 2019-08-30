	package com.bjzcyl.controller.system.sitnews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sitnews.KPNewsManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.thumb.ThumbService;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.FileUpload;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.PathUtil;

import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/sn")
public class KPNewsController extends BaseController {
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@Resource(name="KPNewsService")
	private KPNewsManager KPNewsService;	
	
	@RequestMapping(value="/listNews")
	public ModelAndView listSituation(Page page)throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData> newsList = new ArrayList<PageData>();
		try{
			newsList = KPNewsService.kpNewslistPage(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/sitnews/news_list");
		mv.addObject("newsList", newsList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/viewNews")
	public ModelAndView viewSN()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData news = new PageData();		
		
		try{
			news = KPNewsService.findById(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.addObject("news", news);
		mv.setViewName("system/sitnews/news_detail");
		
		mv.addObject("pd", pd);
		return mv;		
	}
	
	
	@RequestMapping(value="/deleteNews")
	@ResponseBody
	public Object deleteSN() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"delete_sn");
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			KPNewsService.deleteSN(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/editNews")
	public ModelAndView editSN()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData news = new PageData();		
		
		try{
			news = KPNewsService.findById(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.addObject("news", news);
		mv.setViewName("system/sitnews/news");
		
		mv.addObject("pd", pd);
		return mv;		
	}
	
	@RequestMapping(value="/saveNews")
	@ResponseBody
	public Object saveSN(@RequestParam(value="IMG_S",required=false) MultipartFile mImg,
			DefaultMultipartHttpServletRequest serv) throws Exception{
		
		CurrentDateTime dt = new CurrentDateTime();
		String reg_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String _filename = serv.getParameter("SN_IMAGE").toString();
		
		try{
			if (null != mImg && !mImg.isEmpty()) {
				CurrentDateTime ms = new CurrentDateTime();
				_filename = dt.getTotalDate("") + dt.getTotalTime("") + ms.getMilliSecond();
				String filePath = PathUtil.getClasspath() + Const.SN_IMAGE_FILE_PATH;
				_filename =  FileUpload.fileUp(mImg, filePath, _filename);
				new ThumbService().thumb(filePath + _filename, 100*100);
			}
		
			pd.put("SN_TITLE", serv.getParameter("SN_TITLE"));
			pd.put("SN_CONTENT", serv.getParameter("SN_CONTENT"));
			pd.put("SN_IMAGE", _filename);
			pd.put("SN_MAN", this.getUserLGID());
			pd.put("SN_DATETIME", reg_time);
			
			if(serv.getParameter("ID").equals(""))
			{
				pd.put("ID", this.get32UUID());
				pd.put("VIEW_NUM", 0);
				pd.put("TRAVELER_FP_VIEW", 0);
				
				KPNewsService.saveSN(pd);
			}
			else{
				pd.put("ID", serv.getParameter("ID"));
				KPNewsService.updateSN(pd);
			}
			
		}
		catch(Exception e){ 
			errInfo = "error";
			logger.error(e.toString(), e);
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
}
