package com.bjzcyl.controller.system.art;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.art.SAOptionManager;
import com.bjzcyl.service.system.art.SAOptionValueManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.DateUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

@Controller
@RequestMapping(value="/sa")
public class SAOptionValueController extends BaseController {
	
	String menuUrl = "sa/listOptVal";
	
	@Resource(name="saOptionService")
	private SAOptionManager saOptionService;
	
	@Resource(name="saOptionValueService")
	private SAOptionValueManager saOptionValueService;	
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listOptVal")
	public ModelAndView listOptVal(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData option = new PageData();
		option.put("ID", pd.getString("MID"));
		
		option = saOptionService.findById(option);
		List<PageData>	optionValueList = null;
		page.setPd(option);
		if(option != null){
			
			try{
				optionValueList = saOptionValueService.listOptVal(page);		
			}
			catch(Exception ex){ }
			
		}
		mv.setViewName("system/art/option_value");
		mv.addObject("optionValueList", optionValueList);
		mv.addObject("pd", option);
		mv.addObject("QX",Jurisdiction.getHC());
		
		return mv;
		
	}
	
	@RequestMapping(value="/saveOptVal")
	public ModelAndView saveOptVal(Page page) throws Exception{
		PageData pd = new PageData();		
		pd = this.getPageData();
		boolean bUpdate = true;
		if(pd.get("ID") == null || pd.getString("ID").isEmpty() == true)
		{
			pd.put("ID", this.get32UUID());	//ID
			bUpdate = false;
		}
		
		pd.put("NAME", pd.getString("NAME"));								
		pd.put("COMMENT", pd.get("COMMENT"));						
		pd.put("MID", pd.get("MID"));						
		
		if(bUpdate == false)
		{			
			pd.put("CREATE_DATE", DateUtil.getTime().toString());
			saOptionValueService.saveOptVal(pd);
		}
		else
		{
			pd.put("CREATE_DATE", pd.getString("CREATE_DATE_E"));
			saOptionValueService.editOptVal(pd);
		}		
				
		return listOptVal(page);

	}
	
	@RequestMapping(value="/findOptValByMid")
	@ResponseBody
	public Object findOptValByMid(){
		Map<String,Object> map = new HashMap<String,Object>();
		String errInfo = "success";
		PageData pd = new PageData();
		List<PageData> values = null;
		try{
			pd = this.getPageData();
			values = saOptionValueService.getValuesOfCbxByMid(pd);
			
			if(values == null){
				errInfo = "error";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		map.put("data", values);
		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/hasOptVal")
	@ResponseBody
	public Object hasOptVal(){
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(saOptionValueService.findByName(pd) != null){
				errInfo = "exist";
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/deleteOptVal")
	public ModelAndView deleteOptVal(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"delete_option_value");
		PageData pd = new PageData();
		pd = this.getPageData();
		saOptionValueService.deleteOptVal(pd);
		return listOptVal(page);
	}
	
	@RequestMapping(value="/editOptVal")
	@ResponseBody
	public Object editOptVal() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"eidt_option_value");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(saOptionValueService.findByName(pd) != null){
				errInfo = "error";
			} else {
				saOptionValueService.editOptVal(pd);
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
	
	@RequestMapping(value="/deleteAllOptVal")
	@ResponseBody
	public Object deleteAllOptVal() throws Exception {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;}
		logBefore(logger, Jurisdiction.getUsername()+"delete_all_option_value");
		PageData pd = new PageData();
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String IDS = pd.getString("IDS");
		if(null != IDS && !"".equals(IDS)){
			String ArrayIDS[] = IDS.split(",");
			saOptionValueService.deleteAllOptVal(ArrayIDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

}
