package com.bjzcyl.util.databackup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MySQLBackup
{
	private static String user = "root";
	private static String password = "";
	private static String database = "zcyl";
	private static String host = "localhost";
	private static String port = "3306";
	private static String charsetName = "utf8";
	private static String mysqlPath = "c:/xampp/mysql/bin/";	
	private static String BAKUP_FOLDER = "/backup/mysql/";	
	private static String EXPORT_BASE_PATH = "c:/home/"+System.getProperty("user.name");	
	private static String exportPath = EXPORT_BASE_PATH + BAKUP_FOLDER;
	private static String IMPORT_BASE_PATH = "c:/home/"+System.getProperty("user.name");
	private static String importPath = IMPORT_BASE_PATH + BAKUP_FOLDER;
	
	private static String osName = System.getProperty("os.name");
	private static String fileName;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
	private static Logger logger = Logger.getLogger(MySQLBackup.class);
	
	public static String getBackupForlderPath(){
		return exportPath;
	}
	public static String backupAndSave(String springResourcePath){
		try {
			initByApplication(springResourcePath);
			return doBackUpAndSave();
		} catch (IOException e) {
			logger.error("MySQL" + e.toString());
			return null;
		}
	}
	public static void backupAndSave(String user, String password, String database, String host,
			String port, String charsetName, String mysqlPath, String exportPath){
		initByCustomer(user, password, database, host, port, charsetName , mysqlPath, exportPath);
		doBackUpAndSave();
	}
	private static void initByApplication(String springResourcePath) throws IOException{
		
		Properties prop = new Properties();
		InputStream resourceAsStream = MySQLBackup.class.getClassLoader().getResourceAsStream(springResourcePath);
		prop.load(resourceAsStream);
		user = prop.getProperty("user");
		password = prop.getProperty("password");
		host = prop.getProperty("host");
		port = prop.getProperty("port");
		database = prop.getProperty("database");
		charsetName = prop.getProperty("charsetName");
		mysqlPath = prop.getProperty("mysqlPath");
		exportPath = prop.getProperty("exportPath");
		importPath = prop.getProperty("importPath");
	}
	private static void initByCustomer(String user , String password , String database , String host , String port , String charsetName , String mysqlPath , String exportPath){
		if(user!=null&&user.length()!=0)
			MySQLBackup.user = user;
		if(password!=null&&password.length()!=0)
			MySQLBackup.password = password;
		if(database!=null&&database.length()!=0)
			MySQLBackup.database = database;
		if(host!=null&&host.length()!=0)
			MySQLBackup.host = host;
		if(port!=null&&port.length()!=0)
			MySQLBackup.port = port;
		if(charsetName!=null&&charsetName.length()!=0)
			MySQLBackup.charsetName = charsetName;
		if(mysqlPath!=null&&mysqlPath.length()!=0)
			MySQLBackup.mysqlPath = mysqlPath + File.separator + "bin" + File.separator;
		if(exportPath!=null&&exportPath.length()!=0){
			MySQLBackup.exportPath = exportPath;
		}
	}
	private static String doBackUpAndSave(){
		fileName = dateFormat.format(new Date())+".sql";
		File file = new File(exportPath);
		if(!file.exists()){
			file.mkdirs();
		}else{
			if(!file.isDirectory()){
				file.delete();
				file.mkdirs();
			}
		}
		File exportFile = new File(exportPath+fileName);
		if(exportFile.exists()){
			exportFile.delete();
		}
		try {
			exportFile.createNewFile();
		} catch (IOException e1) {
			logger.error("MySQL" + e1.toString());
		}

		String mysqldump = "";
		if(osName.startsWith("Windows")){
			mysqldump = "cmd.exe /c \""+mysqlPath + "mysqldump\" ";
		}else{
			mysqldump = mysqlPath + "mysqldump";
		}
		
		String command = "";
		if(password.equals("")){
			command = new StringBuffer(mysqldump)
					.append(" -u").append(user)
					.append(" -h").append(host)
					.append(" -P").append(port)
					.append(" ").append(database)
					.append(" -r").append(exportPath+fileName)
					.toString();
		}
		else {
			command = new StringBuffer(mysqldump)
					.append(" -u").append(user)
					.append(" -p").append(password)
					.append(" -h").append(host)
					.append(" -P").append(port)
					.append(" ").append(database)
					.append(" -r").append(exportPath+fileName)
					.toString();
		}
		
		try {
			if(doBackup(command))
				return fileName;
			else
				return null;
//			doSave();
		} catch (IOException e) {
			logger.error("MySQL "+e.getMessage());
			return null;
		}
	}
	private static boolean doBackup(String command) throws IOException{
		logger.info("MySQL："+command);
		Runtime runtime = Runtime.getRuntime();
		Process exec = runtime.exec(command);
		try {
			exec.waitFor();
			return true;
		} catch (InterruptedException e) {
			logger.error("MySQL: " + e.toString());
			return false;
		}
	}
	private static boolean doSave(){
		boolean result = false;
		try{
			File exportFile = new File(exportPath+fileName);
			FileInputStream inputStream = new FileInputStream(exportFile);
			String fileStr = exportPath+fileName.substring(0,fileName.lastIndexOf("."));
			
			if(osName.startsWith("Windows")){
				fileStr = fileStr + ".zip";
				logger.info("MySQL："+fileStr);
				result = saveAsZip(inputStream,fileStr);
			}else{
				fileStr = fileStr + ".gz";
				logger.info("MySQL："+fileStr);
				result = saveAsGZip(inputStream, fileStr);
			}
			inputStream.close();
			exportFile.delete();
			return result;
		}catch(IOException e){
			logger.info("MySQL Backup：" + e.toString());
			return false;
		}
	}
	private static boolean saveAsZip(FileInputStream inputStream , String fileStr){
		try
		{	FileOutputStream fileOutputStream = new FileOutputStream(fileStr);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
			byte[] b = new byte[1024];
			int length = 0;
			zipOutputStream.putNextEntry(new ZipEntry(fileName));
			while((length = inputStream.read(b))!=-1){
				zipOutputStream.write(b, 0, length);
			}
			zipOutputStream.closeEntry();
			zipOutputStream.finish();
			zipOutputStream.flush();
			zipOutputStream.close();
			fileOutputStream.close();
			return true;
		}catch(IOException e){
			logger.info("MySQL Backup：" + e.toString());
			return false;
		}
		
	}
	private static boolean saveAsGZip(FileInputStream inputStream , String fileStr){
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(fileStr);
			GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
			byte[] b = new byte[1024];
			int length = 0;
			while((length = inputStream.read(b))!=-1){
				gzipOutputStream.write(b, 0, length);
			}
			gzipOutputStream.finish();
			gzipOutputStream.flush();
			gzipOutputStream.close();
			fileOutputStream.close();
			return true;
		}
		catch(IOException e){
			logger.info("MySQL Backup：" + e.toString());
			return false;
		}
	}
	
	public static boolean restore(String springResourcePath, String fileName){
		try {
			initByApplication(springResourcePath);
			return doRestoreDatabase(fileName);
		} catch (IOException e) {
			logger.error("MySQL" + e.toString());
			return false;
		}
	}
	private static boolean doRestoreDatabase(String fileName){
		File exportFile = new File(importPath + fileName);
		if(exportFile.exists()){
			String restoreDB = "";
			if(osName.startsWith("Windows")){
				restoreDB = "cmd.exe /c \""+mysqlPath + "mysql\" ";
			}else{
				restoreDB = mysqlPath + "mysqldump";
			}
			
			String command = "";
			if(password.equals("")){
				command = new StringBuffer(restoreDB)
						.append(" -u").append(user)
						.append(" -h").append(host)
						.append(" -P").append(port)
						.append(" ").append(database)
						.append(" < ").append(importPath + fileName)
						.toString();
			}
			else {
				command = new StringBuffer(restoreDB)
						.append(" -u").append(user)
						.append(" -p").append(password)
						.append(" -h").append(host)
						.append(" -P").append(port)
						.append(" ").append(database)
						.append(" < ").append(importPath + fileName)
						.toString();
			}
			
			try {
				return doRestore(command);
			} catch (IOException e) {
				logger.error("MySQL "+e.getMessage());
				return false;
			}
			
		}
		else{
			return false;
		}		
	}
	private static boolean doRestore(String command) throws IOException{
		logger.info("MySQL："+command);
		Runtime runtime = Runtime.getRuntime();
		Process exec = runtime.exec(command);
		try {
			exec.waitFor();
			return true;
		} catch (InterruptedException e) {
			logger.error("MySQL: " + e.toString());
			return false;
		}
	}
}