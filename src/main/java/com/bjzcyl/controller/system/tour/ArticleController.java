package com.bjzcyl.controller.system.tour;

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
import com.bjzcyl.service.system.landingpage.FPTourManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.system.tour.TourArticleManager;
import com.bjzcyl.service.system.tour.TourArticleOptionManager;
import com.bjzcyl.service.system.tour.TourOptionManager;
import com.bjzcyl.service.thumb.ThumbService;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.FileUpload;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.PathUtil;

import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/tour")
public class ArticleController extends BaseController {
	
	String menuUrl = "tour/listTourArticle";
	
	@Resource(name="tourArticleService")
	private TourArticleManager tourArticleService;	
	
	@Resource(name="tourOptionService")
	private TourOptionManager tourOptionService;
	
	@Resource(name="tAOptionService")
	private TourArticleOptionManager tAOptionService;
	
	@Resource(name="FPTourService")
	private FPTourManager FPTourService;
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	private String AT_SORT = "tu_article";
	
	@RequestMapping(value="/listTourArticle")
	public ModelAndView listTourArticle(Page page)throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	tourArticleList = new ArrayList<PageData>();
		try{
			tourArticleList = tourArticleService.listTourArticle(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/tour/tour_article_list");
		mv.addObject("tourArticleList", tourArticleList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/editTourArticle")
	public ModelAndView editTourArticle()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData art = new PageData();		
		List<PageData> options = new ArrayList<PageData>();
		List<PageData> artOpts = new ArrayList<PageData>();
		
		try{
			tourOptionService.listAllTourOption(pd);
			art = tourArticleService.findById(pd);
			options = tourOptionService.listAllTourOption(pd);
			artOpts = tAOptionService.listTAOption(pd);
			
			for (PageData artOpt : artOpts) {
				for (PageData option : options) {
					if(option.getString("NAME").equals(artOpt.getString("OPTION"))){
						options.remove(option);
						break;
					}
				}
			}
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/tour/tour_article");
		mv.addObject("art", art);
		mv.addObject("artOpts", artOpts);
		mv.addObject("opts", options);
		mv.addObject("pd", pd);
		return mv;
		
	}

	@RequestMapping(value="/saveArt")
	@ResponseBody
	public Object saveArt(@RequestParam(value="M_IMG_S",required=false) MultipartFile mImg,
			@RequestParam(value="D_IMG_S1",required=false) MultipartFile dImg1,
			@RequestParam(value="D_IMG_S2",required=false) MultipartFile dImg2,
			@RequestParam(value="D_IMG_S3",required=false) MultipartFile dImg3,
			@RequestParam(value="D_IMG_S4",required=false) MultipartFile dImg4,
			@RequestParam(value="D_IMG_S5",required=false) MultipartFile dImg5,
			DefaultMultipartHttpServletRequest serv) throws Exception{
		CurrentDateTime dt = new CurrentDateTime();
		String reg_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		MultipartFile[] files = new MultipartFile[6];
		files[0] = mImg;
		files[1] = dImg1;
		files[2] = dImg2;
		files[3] = dImg3;
		files[4] = dImg4;
		files[5] = dImg5;
		String[] filenames = new String[6];
		String strTmp = serv.getParameter("DETAIL_IMAGE").toString();
		
		String[] dFileName = strTmp.split("\\|");
		for(int i = 0; i < 6; i++){
			if (null != files[i] && !files[i].isEmpty()) {
				CurrentDateTime ms = new CurrentDateTime();
				String filename = dt.getTotalDate("") + dt.getTotalTime("") + ms.getMilliSecond();
				String filePath = PathUtil.getClasspath() + Const.TOUR_IMAGE_FILE_PATH;
				filenames[i] =  FileUpload.fileUp(files[i], filePath, filename);
				new ThumbService().thumb(filePath + filenames[i], 100);
				
			}
			else
			{
				if( i == 0)
					filenames[i] = serv.getParameter("MAIN_IMAGE");
				else
					filenames[i] = dFileName[i-1];
			}	
		}
		
		String ATICLE_ID = this.get32UUID();
		
		pd.put("ID", ATICLE_ID);
		pd.put("NAME", serv.getParameter("NAME"));
		pd.put("DESCRIPTION", serv.getParameter("DESCRIPTION"));
		pd.put("ADULT_EXPENSE", serv.getParameter("ADULT_EXPENSE"));
		pd.put("CHILD_EXPENSE", serv.getParameter("CHILD_EXPENSE"));
		pd.put("MAIN_IMAGE", filenames[0]);
		pd.put("DETAIL_IMAGE", filenames[1] + "|" + filenames[2] + "|" + filenames[3] + "|" + filenames[4] + "|" + filenames[5]);
		pd.put("SERVICE_FEATURE", serv.getParameter("SERVICE_FEATURE"));
		pd.put("TREATMENT_INFORMATION", serv.getParameter("TREATMENT_INFORMATION"));
		pd.put("ROUTE_INTRODUCTION", serv.getParameter("ROUTE_INTRODUCTION"));
		pd.put("EXPENSE_INCLUDED", serv.getParameter("EXPENSE_INCLUDED"));
		pd.put("BOOKING_NOTE", serv.getParameter("BOOKING_NOTE"));
		pd.put("PURCHASE_URL", serv.getParameter("PURCHASE_URL"));
		pd.put("VIEW_STATE", serv.getParameter("VIEW_STATE"));
		pd.put("SALE_STATE", serv.getParameter("SALE_STATE"));
		pd.put("REGISTER", this.getUserLGID());
		pd.put("REGIST_TIME", reg_time);
		
		
		//OPTIONS
		String[] options = serv.getParameter("OPTIONS").split(",");
		
		for(int i = 0; i < options.length; i++){
			
			if(options[i].equals("")) continue;
			
			PageData taOption = new PageData();
			String[] opt = options[i].toString().split("\\|");
			
			taOption.put("ARTICLE_ID", ATICLE_ID);
			taOption.put("OPTION", opt[0].toString());
			taOption.put("VAL", opt[1].toString());
			try{
				tAOptionService.saveTAOption(taOption);
			}
			catch(Exception e){
				logger.error(e.toString(), e);
				errInfo = "error";
			}
		}
		try{
			tourArticleService.saveArt(pd);
			
			LogService.insertLog(this.get32UUID(), this.getUserLGID(), menuUrl, Const.OPERATION_ADD, pd.getString("NAME"), AT_SORT, ATICLE_ID);
			
			if(pd.getString("VIEW_STATE").equals("1")){
				PageData fpTour = new PageData();
				fpTour.put("ID", this.get32UUID());
				fpTour.put("TOUR_ID", ATICLE_ID);
				
				FPTourService.saveFPTour(fpTour);
			}
		}
		catch(Exception e){ 
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}	
	
	@RequestMapping(value="/hasArt")
	@ResponseBody
	public Object hasArt(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(tourArticleService.findByName(pd) != null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/deleteArt")
	@ResponseBody
	public Object deleteArt() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		try{
			tourArticleService.deleteArt(pd);
			PageData fpTour = new PageData();
			fpTour.put("TOUR_ID", pd.getString("ID"));
			FPTourService.deleteFPTour(fpTour);
		}
		catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}

		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/updateArt")
	@ResponseBody
	public Object updateArt(@RequestParam(value="M_IMG_S",required=false) MultipartFile mImg,
			@RequestParam(value="D_IMG_S1",required=false) MultipartFile dImg1,
			@RequestParam(value="D_IMG_S2",required=false) MultipartFile dImg2,
			@RequestParam(value="D_IMG_S3",required=false) MultipartFile dImg3,
			@RequestParam(value="D_IMG_S4",required=false) MultipartFile dImg4,
			@RequestParam(value="D_IMG_S5",required=false) MultipartFile dImg5,
			DefaultMultipartHttpServletRequest serv) throws Exception{
		CurrentDateTime dt = new CurrentDateTime();
		String reg_time = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();
		pd = this.getPageData();
		
		MultipartFile[] files = new MultipartFile[6];
		files[0] = mImg;
		files[1] = dImg1;
		files[2] = dImg2;
		files[3] = dImg3;
		files[4] = dImg4;
		files[5] = dImg5;
		String[] filenames = new String[6];
		String strTmp = serv.getParameter("DETAIL_IMAGE").toString();
		
		String[] dFileName = strTmp.split("\\|");
		for(int i = 0; i < 6; i++){
			if (null != files[i] && !files[i].isEmpty()) {
				CurrentDateTime ms = new CurrentDateTime();
				String filename = dt.getTotalDate("") + dt.getTotalTime("") + ms.getMilliSecond();
				String filePath = PathUtil.getClasspath() + Const.TOUR_IMAGE_FILE_PATH;
				filenames[i] =  FileUpload.fileUp(files[i], filePath, filename);
				new ThumbService().thumb(filePath + filenames[i], 100*100);
			}
			else
			{
				if( i == 0)
					filenames[i] = serv.getParameter("MAIN_IMAGE");
				else
					filenames[i] = dFileName[i-1];
			}	
		}
		
		String ATICLE_ID = serv.getParameter("ID");
		
		pd.put("ID", ATICLE_ID);
		pd.put("NAME", serv.getParameter("NAME"));
		pd.put("DESCRIPTION", serv.getParameter("DESCRIPTION"));
		pd.put("ADULT_EXPENSE", serv.getParameter("ADULT_EXPENSE"));
		pd.put("CHILD_EXPENSE", serv.getParameter("CHILD_EXPENSE"));
		pd.put("MAIN_IMAGE", filenames[0]);
		pd.put("DETAIL_IMAGE", filenames[1] + "|" + filenames[2] + "|" + filenames[3] + "|" + filenames[4] + "|" + filenames[5]);
		pd.put("SERVICE_FEATURE", serv.getParameter("SERVICE_FEATURE"));
		pd.put("TREATMENT_INFORMATION", serv.getParameter("TREATMENT_INFORMATION"));
		pd.put("ROUTE_INTRODUCTION", serv.getParameter("ROUTE_INTRODUCTION"));
		pd.put("EXPENSE_INCLUDED", serv.getParameter("EXPENSE_INCLUDED"));
		pd.put("BOOKING_NOTE", serv.getParameter("BOOKING_NOTE"));
		pd.put("PURCHASE_URL", serv.getParameter("PURCHASE_URL"));
		pd.put("VIEW_STATE", serv.getParameter("VIEW_STATE"));
		pd.put("SALE_STATE", serv.getParameter("SALE_STATE"));
		pd.put("REGISTER", this.getUserLGID());
		pd.put("REGIST_TIME", reg_time);
		
		
		//OPTIONS
		PageData taOption = new PageData();
		taOption.put("ARTICLE_ID", ATICLE_ID);
		taOption.put("OPTION", "");
		taOption.put("VAL", "");
		taOption.put("ID", "");
		try{
			tAOptionService.deleTAOption(taOption);
		}catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		String[] options = serv.getParameter("OPTIONS").split(",");
		
		for(int i = 0; i < options.length; i++){
			
			if(options[i].equals("")) continue;
			
			taOption = new PageData();
			String[] opt = options[i].toString().split("\\|");
			
			taOption.put("ARTICLE_ID", ATICLE_ID);
			taOption.put("OPTION", opt[0].toString());
			taOption.put("VAL", opt[1].toString());
			try{
				tAOptionService.saveTAOption(taOption);
			}
			catch(Exception e){ 
				logger.error(e.toString(), e);
				errInfo = "error";
			}
		}
		
		try{
			tourArticleService.updateArt(pd);
			
			LogService.insertLog(this.get32UUID(), this.getUserLGID(), menuUrl, Const.OPERATION_EDIT, pd.getString("NAME"), AT_SORT, ATICLE_ID);
			
			if(pd.getString("VIEW_STATE").equals("1")){
				PageData fpTour = new PageData();
				fpTour.put("ID", this.get32UUID());
				fpTour.put("TOUR_ID", ATICLE_ID);
				
				List<PageData> fpSpecialList = new ArrayList<PageData>();
				fpSpecialList = FPTourService.hasFPTour(fpTour);
				if(fpSpecialList.size() == 0){
					FPTourService.saveFPTour(fpTour);
				}	
			}
		}
		catch(Exception e){ 
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);		
	}
}
