package com.bjzcyl.controller.system.special;

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
import com.bjzcyl.service.system.special.SPSlidingManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.thumb.ThumbService;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.FileUpload;
import com.bjzcyl.util.FileUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.PathUtil;

import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/sp")
public class SPSlidingController extends BaseController {
	
	String menuUrl = "sp/listSlide";
	
	@Resource(name="SPSlidingService")
	private SPSlidingManager SPSlidingService;	
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listSlide")
	public ModelAndView listSlide(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData> slideList = new ArrayList<PageData>();
		try{
			slideList = SPSlidingService.listSPSlide(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/special/slide_list");
		mv.addObject("slideList", slideList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/saveSlide")
	@ResponseBody
	public Object saveSlide(@RequestParam(value="IMG",required=false) MultipartFile mImg,
			DefaultMultipartHttpServletRequest serv) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();

		String filename = "";
		
		if (null != mImg && !mImg.isEmpty()) {
			CurrentDateTime dt = new CurrentDateTime();
			filename = dt.getTotalDate("") + dt.getTotalTime("") + dt.getMilliSecond();
			String filePath = PathUtil.getClasspath() + Const.SP_IMAGE_FILE_PATH;
			filename =  FileUpload.fileUp(mImg, filePath, filename);
			new ThumbService().thumb(filePath + filename, 100*100);
		}
		else
		{
			filename = serv.getParameter("IMG_NAME");
		}
		
		pd.put("NAME", filename);
		pd.put("COMMENT", serv.getParameter("COMMENT"));
		pd.put("VIEW_TYPE", serv.getParameter("VIEW_TYPE"));
		pd.put("STATE", serv.getParameter("VIEW_STATE"));
		
		String SLIDE_ID = "";
		if(serv.getParameter("ID").equals("")){
			SLIDE_ID = this.get32UUID();
			pd.put("ID", SLIDE_ID);
			
			try{
				SPSlidingService.saveSPSlide(pd);
			}
			catch(Exception e){ 
				errInfo = "error";
				logger.error(e.toString(), e);
			}
		}
		else{
			SLIDE_ID = serv.getParameter("ID");
			pd.put("ID", SLIDE_ID);
			try{
				SPSlidingService.updateSPSlide(pd);
			}
			catch(Exception e){ 
				logger.error(e.toString(), e);
				errInfo = "error";
			}
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}	
	
	@RequestMapping(value="/deleteSlide")
	@ResponseBody
	public Object deleteSlide() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		try{
			SPSlidingService.deleteSPSlide(pd);
			String filePath = PathUtil.getClasspath() + Const.SP_IMAGE_FILE_PATH;
			FileUtil.delFile(filePath + "/" + pd.getString("NAME"));
		}catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/changeStateSlide")
	@ResponseBody
	public Object changeStateSlide() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		try{
			SPSlidingService.changeStateSlide(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
}
