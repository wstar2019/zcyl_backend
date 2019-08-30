package com.bjzcyl.controller.system.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bjzcyl.controller.base.BaseController;
import com.bjzcyl.entity.Page;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.service.system.sys.ManagerManager;
import com.bjzcyl.service.system.sys.RolesManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/sys")
public class ManagerController extends BaseController {
	
	String menuUrl = "sys/listManager";
	
	@Resource(name="ManagerService")
	private ManagerManager ManagerService;	

	@Resource(name="RolesService")
	private RolesManager RolesService;	
	
	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listManager")
	public ModelAndView listManager(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	managerList = new ArrayList<PageData>();
		List<PageData>	roleList = new ArrayList<PageData>();
		try{
			managerList = ManagerService.managerlistPage(page);
			roleList = RolesService.roleList(pd);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
				
		mv.setViewName("system/sys/manager_list");
		mv.addObject("managerList", managerList);
		List<String> opts = new ArrayList<String>();
		for (PageData pageData : roleList) {
			opts.add("{text:'" + pageData.getString("role_name") + "',value:'" + pageData.getString("id") + "'}");
		}
		JSONArray mArr = JSONArray.fromObject(opts);
		mv.addObject("roleList", mArr);
		
		pd.put("CUR_LGID", this.getUserLGID());
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	@RequestMapping(value="/checkPassword")
	@ResponseBody
	public Object checkPassword() throws Exception{
		JSONObject result = new JSONObject();
		String info = "no";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String USERNAME = pd.getString("USERNAME");
			String PASSWORD = pd.getString("PASSWORD");
			
			String passwd = new SimpleHash("SHA-1", USERNAME, PASSWORD).toString();
			pd.put("PASSWORD", passwd);
			PageData exists = ManagerService.getManagerByNameAndPwd(pd);
			if(!exists.isEmpty()){
				info = "ok";
			}
		}catch(Exception e){
			info = "fail";
			logger.error(e.toString(), e);
		}
		result.put("result", info);
		return result;
	}
	@RequestMapping(value="/hasManager")
	@ResponseBody
	public Object hasManager() throws Exception{
		JSONObject result = new JSONObject();
		String info = "no";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{

			PageData exists = ManagerService.findByLGID(pd);
			if(exists != null){
				if(pd.getString("ID").equals("")){
					info = "yes";
				}
				else if(!pd.getString("ID").equals(exists.getString("ID"))){
					info = "yes";
				}
			}
		}catch(Exception e){
			info = "fail";
			logger.error(e.toString(), e);
		}
		result.put("result", info);
		return result;
	}
	@RequestMapping(value="/saveManager")
	@ResponseBody
	public Object saveManager() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();

		try{
			pd = this.getPageData();			
			pd.put("ID", this.get32UUID());
			
			String passwd = new SimpleHash("SHA-1", pd.getString("LG_ID"), pd.getString("PASS")).toString();
			pd.put("PASS", passwd);
			pd.put("EMAIL", "");			
			ManagerService.saveManager(pd);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/changeManagerState")
	@ResponseBody
	public Object changeManagerState() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"change_manager_state");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
//			String passwd = new SimpleHash("SHA-1", pd.getString("LG_ID"), pd.getString("PASS")).toString();
//			pd.put("PASS", passwd);
			
			ManagerService.updateManagerState(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
	
	@RequestMapping(value="/updatePassword")
	@ResponseBody
	public Object updatePassword() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"check_password");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String passwd = new SimpleHash("SHA-1", pd.getString("USERNAME"), pd.getString("PASS")).toString();	
			pd.put("PASS", passwd);
			ManagerService.updatePass(pd);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
	
	@RequestMapping(value="/updateManager")
	@ResponseBody
	public Object updateManager() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"update_manager");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			if(pd.containsKey("PASS")){
				String passwd = new SimpleHash("SHA-1", pd.getString("LG_ID"), pd.getString("PASS")).toString();	
				pd.put("PASS", passwd);
			}
			ManagerService.updateManager(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		
		return AppUtil.returnObject(new PageData(), map);		
	}
	
	@RequestMapping(value="/deleteManager")
	@ResponseBody
	public Object deleteManager() throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;}
		logBefore(logger, Jurisdiction.getUsername()+"delete_manager");
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";

		try{
			ManagerService.deleteManager(pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
}