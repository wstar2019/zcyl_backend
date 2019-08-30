package com.bjzcyl.service.system.sys;

import java.util.List;

import com.bjzcyl.entity.Page;
import com.bjzcyl.util.PageData;

public interface BackupManager {
	public List<PageData> backuplistPage(Page page)throws Exception;
	public void deleteBackup(PageData pd)throws Exception;
	public void saveBackup(PageData pd)throws Exception;
	public PageData getBackupById(PageData pd) throws Exception;
}
