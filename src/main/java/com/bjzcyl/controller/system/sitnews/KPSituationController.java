	package com.bjzcyl.controller.system.sitnews;

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
import com.bjzcyl.service.system.sitnews.KPSituationManager;
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
public class KPSituationController extends BaseController {
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@Resource(name="KPSituationService")
	private KPSituationManager KPSituationService;	
	
	@RequestMapping(value="/listSituation")
	public ModelAndView listSituation(Page page)throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	situationList = KPSituationService.kpSituationlistPage(page);
		
		mv.setViewName("system/sitnews/situation_list");
		mv.addObject("situationList", situationList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/viewSituation")
	public ModelAndView viewSN()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData situation = new PageData();		
		
		try{
			situation = KPSituationService.findById(pd);
		}catch(Exception ex){}
		
		mv.addObject("situation", situation);
		mv.setViewName("system/sitnews/situation_detail");
		mv.addObject("pd", pd);
		return mv;		
	}
	
	@RequestMapping(value="/deleteSituation")
	@ResponseBody
	public Object deleteSN() throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;}
		logBefore(logger, Jurisdiction.getUsername()+"delete_sn");
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			KPSituationService.deleteSN(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/editSituation")
	public ModelAndView editSN()throws Exception{				
		ModelAndView mv = this.getModelAndView();
		
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData situation = new PageData();		
		
		try{
			situation = KPSituationService.findById(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mv.addObject("situation", situation);
		mv.setViewName("system/sitnews/situation");
		mv.addObject("pd", pd);
		return mv;		
	}
	
	@RequestMapping(value="/saveSituation")
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
				KPSituationService.saveSN(pd);
			}
			else{
				pd.put("ID", serv.getParameter("ID"));
				KPSituationService.updateSN(pd);
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
