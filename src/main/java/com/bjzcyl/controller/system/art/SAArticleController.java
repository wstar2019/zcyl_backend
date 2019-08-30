package com.bjzcyl.controller.system.art;

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
import com.bjzcyl.entity.system.SAClass;
import com.bjzcyl.service.system.art.SAArticleManager;
import com.bjzcyl.service.system.art.SAArticleOptionManager;
import com.bjzcyl.service.system.art.SAClassManager;
import com.bjzcyl.service.system.art.SAOptionManager;
import com.bjzcyl.service.system.landingpage.FPArtManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.thumb.ThumbService;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.FileUpload;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.PathUtil;

import net.sf.json.JSONArray;
import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/sa")
public class SAArticleController extends BaseController {
	
	String menuUrl = "sa/listArticle";
	
	@Resource(name="saArticleService")
	private SAArticleManager saArticleService;	
	
	@Resource(name="saOptionService")
	private SAOptionManager saOptionService;
	
	@Resource(name="saArticleOptionService")
	private SAArticleOptionManager saArticleOptionService;
	
	@Resource(name="FPArtService")
	private FPArtManager FPArtService;
	@Resource(name="LogService")
	private LogManager LogService;
	@Resource(name="saClassService")
	private SAClassManager saClassService;
	
	private String AT_SORT = "sa_article";
	
	@RequestMapping(value="/listArticle")
	public ModelAndView listArticle(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData> articleList = new ArrayList<PageData>();
		try{
			articleList = saArticleService.listArticle(page);		
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.setViewName("system/art/article_list");
		mv.addObject("articleList", articleList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/editArticle")
	public ModelAndView editArticle()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData art = new PageData();		
		List<PageData> options = new ArrayList<PageData>();
		List<PageData> artOpts = new ArrayList<PageData>();
		
		try{
			options = saOptionService.listAllOption(pd);
			if(pd.containsKey("ID") && !pd.getString("ID").equals("")){
				art = saArticleService.findById(pd);				
				PageData saOptpd = new PageData();
				saOptpd.put("ARTICLE_ID", pd.getString("ID"));
				artOpts = saArticleOptionService.listArticleOption(saOptpd);
				for (PageData aOpt : artOpts) {
					for (PageData opt : options) {
						if(aOpt.getString("option").equals(opt.getString("NAME"))){
							options.remove(opt);
							break;
						}
					}
				}
			}
			
			List<SAClass> classList = saClassService.listAllClass("0");
			JSONArray arr = JSONArray.fromObject(classList);
			String json = arr.toString();		
			json = json.replaceAll("CLASS_ID", "id")
					.replaceAll("ICON", "icon")
					.replaceAll("CLASS_NAME", "name")
					.replaceAll("subClass", "nodes")
					.replaceAll("hasClass", "checked");	
			mv.addObject("zTreeNodes", json);
			
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/art/article");
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
				String filePath = PathUtil.getClasspath() + Const.SA_IMAGE_FILE_PATH;
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
		
		String ATICLE_ID = this.get32UUID();
		
		pd.put("ID", ATICLE_ID);
		pd.put("NAME", serv.getParameter("NAME"));
		pd.put("SA_CLASS", serv.getParameter("SA_CLASS"));
		pd.put("COMMENT", serv.getParameter("COMMENT"));
		pd.put("PRICE", serv.getParameter("PRICE"));
		pd.put("MAIN_IMAGE", filenames[0]);
		pd.put("DETAIL_IMAGE", filenames[1] + "|" + filenames[2] + "|" + filenames[3] + "|" + filenames[4] + "|" + filenames[5]);
		pd.put("SERVICE_FEATURE", serv.getParameter("SERVICE_FEATURE"));
		pd.put("DESCRIPTION", serv.getParameter("DESCRIPTION"));
		pd.put("SIZE_PACKING", serv.getParameter("SIZE_PACKING"));
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
				saArticleOptionService.saveSAOption(taOption);
			}
			catch(Exception e){ 
				errInfo = "error";
				logger.error(e.toString(), e);
			}
		}
		try{
			saArticleService.saveArt(pd);
			
			LogService.insertLog(this.get32UUID(), this.getUserLGID(), menuUrl, Const.OPERATION_ADD, pd.getString("NAME"), AT_SORT, ATICLE_ID);
			
			if(pd.getString("VIEW_STATE").equals("1")){
				PageData fpSA = new PageData();
				fpSA.put("ID", this.get32UUID());
				fpSA.put("ART_ID", ATICLE_ID);
				
				FPArtService.saveFPArt(fpSA);
			}
		}
		catch(Exception e){ 
			errInfo = "error";
			logger.error(e.toString(), e);
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
			if(saArticleService.findByName(pd) != null){
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
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		
		pd = this.getPageData();
		try{
			saArticleService.deleteArt(pd);
			PageData fpSA = new PageData();
			fpSA.put("ART_ID", pd.getString("ID"));
			FPArtService.deleteFPArt(fpSA);
			
		}
		catch(Exception e){
			errInfo = "error";
			logger.error(e.toString(), e);
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
				String filePath = PathUtil.getClasspath() + Const.SA_IMAGE_FILE_PATH;
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
		pd.put("SA_CLASS", serv.getParameter("SA_CLASS"));
		pd.put("COMMENT", serv.getParameter("COMMENT"));
		pd.put("PRICE", serv.getParameter("PRICE"));
		pd.put("MAIN_IMAGE", filenames[0]);
		pd.put("DETAIL_IMAGE", filenames[1] + "|" + filenames[2] + "|" + filenames[3] + "|" + filenames[4] + "|" + filenames[5]);
		pd.put("SERVICE_FEATURE", serv.getParameter("SERVICE_FEATURE"));
		pd.put("DESCRIPTION", serv.getParameter("DESCRIPTION"));
		pd.put("SIZE_PACKING", serv.getParameter("SIZE_PACKING"));
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
			saArticleOptionService.deleSAOption(taOption);
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
				saArticleOptionService.saveSAOption(taOption);
			}
			catch(Exception e){ 
				logger.error(e.toString(), e);
				errInfo = "error";
			}
		}
		
		try{
			saArticleService.updateArt(pd);
			LogService.insertLog(this.get32UUID(), this.getUserLGID(), menuUrl, Const.OPERATION_EDIT, pd.getString("NAME"), AT_SORT, ATICLE_ID);
			if(pd.getString("VIEW_STATE").equals("1")){
				PageData fpSA = new PageData();
				fpSA.put("ID", this.get32UUID());
				fpSA.put("ART_ID", ATICLE_ID);
				
				List<PageData> fpArtList = new ArrayList<PageData>();
				fpArtList = FPArtService.hasFPArt(fpSA);
				if(fpArtList.size() == 0){
					FPArtService.saveFPArt(fpSA);
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
