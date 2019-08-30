package com.bjzcyl.controller.system.sys;

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
import com.bjzcyl.service.system.sys.BackupManager;
import com.bjzcyl.service.system.sys.LogManager;
import com.bjzcyl.util.AppUtil;
import com.bjzcyl.util.Jurisdiction;
import com.bjzcyl.util.PageData;
import com.bjzcyl.util.databackup.MySQLBackup;

import cn.gh.util.FileUtil;
import utils.CurrentDateTime;

@Controller
@RequestMapping(value="/sys")
public class BackupController extends BaseController {
	
	String menuUrl = "sys/listBackup";
	
	@Resource(name="BackupService")
	private BackupManager BackupService;	

	@Resource(name="LogService")
	private LogManager LogService;
	
	@RequestMapping(value="/listBackup")
	public ModelAndView listBackup(Page page)throws Exception{				
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		page.setPd(pd);
		
		List<PageData>	backupList = new ArrayList<PageData>();
		try{
			backupList = BackupService.backuplistPage(page);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
				
		
		mv.setViewName("system/sys/backup_list");
		mv.addObject("backupList", backupList);		
		
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
		
	}
	
	@RequestMapping(value="/saveBackup")
	@ResponseBody
	public Object saveBackup() throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;}
		logBefore(logger, Jurisdiction.getUsername()+"save_backup");
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		
		PageData pd = new PageData();

		try{
			String _file = MySQLBackup.backupAndSave("mysqlbackup.properties");
			
			pd = this.getPageData();
			
			CurrentDateTime dt = new CurrentDateTime();
			String dTime = dt.getTotalDate("-") + " " + dt.getTotalTime(":");
			
			pd.put("ID", this.get32UUID());
			pd.put("FILE", _file);
			pd.put("CREATE_TIME", dTime);
			pd.put("OPERATOR", Jurisdiction.getUsername());
			
			BackupService.saveBackup(pd);
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
		
	}
	
	@RequestMapping(value="/deleteBackup")
	@ResponseBody
	public Object deleteBackup() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"delete_backup");
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			PageData delete = BackupService.getBackupById(pd);
			BackupService.deleteBackup(pd);
			FileUtil.deleteFile(MySQLBackup.getBackupForlderPath() + delete.getString("FILE"));
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@RequestMapping(value="/restoreBackup")
	@ResponseBody
	public Object restoreBackup() throws Exception{
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;}
		logBefore(logger, Jurisdiction.getUsername()+"save_backup");
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";		
		PageData pd = new PageData();

		try{
			pd = this.getPageData();			
			PageData backup = BackupService.getBackupById(pd);
			if(backup != null){
				boolean result = MySQLBackup.restore("mysqlbackup.properties", backup.getString("FILE"));
				if(!result)
					errInfo = "error";
			}
			
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "error";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
}
