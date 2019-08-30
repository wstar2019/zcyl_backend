<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.bjzcyl.util.*,java.io.File"%>
<%@ page import="com.jspsmart.upload.*" %>
<%@ page import="java.util.*" %> 
<%@ page import="java.io.*" %> 
<%!
	public String ManageFolder(String path,String folder,String osName)
	{
		boolean isFolderID=false;
		java.io.File file=new java.io.File(path+folder);
		if(file.exists()){	
			if(osName.equals("W")){  
				path+=folder+"\\";               //  사용자개인등록부에로 이동
			}else if(osName.equals("L")){
				path+=folder+"/";
			}
			isFolderID=true;
			//out.print("van");
		}else{
				
			if(osName.equals("W")){
				FileUtil.createDir(path+"\\"+folder);
				path+=folder+"\\";               //  사용자개인등록부에로 이동
			} else if(osName.equals("L")){
				FileUtil.createDir(path+"/"+folder);
				path+=folder+"/";               //  사용자개인등록부에로 이동
			}
		//out.print("nincs");
		}
		
		return path;
	}

%>
<%
	/* index.jsp에서 설정한 session값 얻기 */
	//String tid = (String)session.getAttribute("tid");
	//String eid = (String)session.getAttribute("eid");
	String tid = "tid";
	String eid = "eid";
	
	/*---------- 조작체계정보얻기 ---------*/
	String sysInfo = "";
	String os_name = System.getProperty("os.name");
	if(os_name.indexOf("Window")>-1){   //Windows체계일때
		sysInfo="W";
	} else if(os_name.indexOf("Linux")>-1){
		sysInfo="L";
	}
	/*-------- 파일경로얻기-------*/
	ServletContext context = getServletContext();
	String realFolder = context.getRealPath("uploadFiles"); 
	
	if(sysInfo.equals("W")){   //Windows체계일때
		realFolder += "\\";  //"F:\\media_upload\\upload\\";
	} else if(sysInfo.equals("L")){
		realFolder += "/";
	}
	realFolder = ManageFolder(realFolder,tid,sysInfo);
	realFolder = ManageFolder(realFolder,eid,sysInfo);

	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/uploadFiles";
	//  basePath=http://150.30.22.108:8080/education_one/
	String imgWidth=request.getParameter("imgWidth");
	String imgHeight=request.getParameter("imgHeight");
	//out.print(realFolder);

	String ctype=request.getParameter("Ctype");
	com.jspsmart.upload.SmartUpload mySmartUpload=new com.jspsmart.upload.SmartUpload();
	mySmartUpload.initialize(pageContext);
	mySmartUpload.setMaxFileSize(200 * 1024*1024);
	mySmartUpload.upload();
	String fileName = "";
	long size = 0;
	
	for (int i = 0; i < mySmartUpload.getFiles().getCount(); i++)
	{
		com.jspsmart.upload.File file = mySmartUpload.getFiles().getFile(i);
		size = file.getSize();	
		
		if(size != 0){
			if (!file.isMissing())
			{
				fileName=file.getFileName();
			    file.saveAs(realFolder+fileName,SmartUpload.SAVE_PHYSICAL);
		    
				out.print("<html>");
				out.print("<head>");;
				out.print("<title>Insert Image</title>");;
				out.print("<meta http-equiv='content-type' content='text/html; charset=utf-8'>");
				out.print("</head>");
				out.print("<body>");
				if(ctype==null)
				{
					out.print("<SCRIPT LANGUAGE='JavaScript'>parent.KindInsertImage('"+basePath+"/"+tid+"/"+eid+"/"+fileName+"','imgWidth','$imgHeight','$imgBorder','$imgTitle','$imgAlign','$imgHspace','$imgVspace')</SCRIPT>");
				}
				else
				{
					out.print("<SCRIPT LANGUAGE='JavaScript'>parent.KindInsertImageP8FLV('"+basePath+"/"+tid+"/"+eid+"/"+fileName+"','"+imgWidth+"','"+imgHeight+"','"+ctype+"')</SCRIPT>");
				}
				out.print("</body>");
				out.print("</html>");
				out.close();
%>
<%
		}
	   	else     
	   	{
			out.println("<script>alert('&#54028;&#51068;&#51204;&#49569;&#49892;&#54056;??????');</script>");
			return;
	   	}
	  }
	}

%>