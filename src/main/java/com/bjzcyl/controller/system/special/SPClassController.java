package com.bjzcyl.controller.system.special;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.special.SPClassManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.thumb.ThumbService;
import com.bjzcyl.util.Const;
import com.bjzcyl.util.FileUpload;
import com.bjzcyl.util.FileUtil;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.PathUtil;

import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/sp")
public class SPClassController extends BaseController {
	
	String menuUrl = "sp/listKind";
	
	@Resource(name="spClassService")
	private SPClassManager spClassService;
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	private List<PageData> classList;
	private String tree = "";
	
	@RequestMapping(value="/listKind")
	public ModelAndView listClass(Page page)throws Exception{			
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("UPPERID", -1);
		page.setPd(pd);
		try{
			classList = spClassService.listClassByUpperId(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		tree = "";
		makeClassList("0", 0);
		
		mv.addObject("ClassList", classList);	
		mv.addObject("strTree", tree);
		
		mv.setViewName("system/special/class_list");
		mv.addObject("pd", pd);
		return mv;		
	}
	
	public void makeClassList(String id, int level) throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("UPPERID", id);
		List<PageData> lst = new ArrayList<PageData>();
		try{
			lst = spClassService.listClassByUpperId(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		if(lst.size() == 0) {
			return;
		}
		else {
			tree += "<ul>";
		}
		for(int i = 0; i < lst.size(); i++) {
			PageData pageData = lst.get(i);
			String cid = pageData.get("CLASS_ID").toString();
			pageData.put("LEVEL", level);
			tree += "<li tr_id='" + pageData.get("CLASS_ID") + "' class='tree" +
					pageData.get("CLASS_ID")+ "'>" + pageData.get("CLASS_NAME");
			classList.add(pageData);
			makeClassList(cid, level + 1);
			tree += "</li>";
		}
		tree += "</ul>";
	}
	
	@RequestMapping(value="/saveClass")
	public ModelAndView saveClass(Page page,
			@RequestParam(value="CLASS_PHOTO",required=false) MultipartFile file,
			DefaultMultipartHttpServletRequest serv) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String filename = "";
		if (null != file && !file.isEmpty()) {
			CurrentDateTime dt = new CurrentDateTime();
			filename = dt.getTotalDate("") + dt.getTotalTime("") + dt.getMilliSecond();	
			String filePath = PathUtil.getClasspath() + Const.SP_IMAGE_FILE_PATH;
			filename = FileUpload.fileUp(file, filePath, filename);			
			new ThumbService().thumb(filePath + filename, 100*100);
		}
		else{
			filename = serv.getParameter("CLASS_PHOTO_NAME");
		}
		
		pd.put("CLASS_IMG_URL", filename);
		pd.put("CLASS_NAME", serv.getParameter("CLASS_NAME"));
		pd.put("CLASS_CONTENT", serv.getParameter("CLASS_CONTENT"));		
		pd.put("CLASS_SHOW_TYPE", serv.getParameter("CLASS_SHOW_TYPE"));
		pd.put("CLASS_PARENT", serv.getParameter("CLASS_PARENT"));
		pd.put("CLASS_VISIBILITY", serv.getParameter("CLASS_VISIBILITY"));
		pd.put("CLASS_LAYER", serv.getParameter("CLASS_LAYER"));
		
		page.setPd(pd);
		
		try{
			if(serv.getParameter("CLASS_ID").equals("") || serv.getParameter("CLASS_ID").isEmpty()){
				pd.put("CLASS_ID", this.get32UUID());				
				spClassService.saveClass(pd);
			}else{
				pd.put("CLASS_ID", serv.getParameter("CLASS_ID"));
				spClassService.updateClass(pd);
			}
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.addObject("msg","saveClass");		
		return listClass(page);
	}
	
	@RequestMapping(value="/deleteClass")
	public ModelAndView deleteClass(Page page) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();				
		
		PageData classData = new PageData();
		try{
			classData = spClassService.findClassById(pd);
			String filename = classData.getString("CLASS_IMG_URL");
			String toDelFilePath = PathUtil.getClasspath() + Const.SP_IMAGE_FILE_PATH + filename;
			toDelFilePath = toDelFilePath.replace("/", "\\");		
			FileUtil.delFile(toDelFilePath);
			page.setPd(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		try{
			spClassService.deleteClass(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.addObject("msg", "deleteClass");						
		
		return listClass(page);
	}
}
