<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE></TITLE></HEAD>
<META http-equiv=content-type content="text/html; charset=utf-8">
<BODY leftMargin=0 topMargin=0 style="margin:0px;">
<SCRIPT language=JavaScript>
<!--
var bokecc_id='';
//-->
 </SCRIPT>
<SCRIPT src="ewebeditor/KindEditor.js" type="text/javascript"></SCRIPT>
<INPUT id=content type=hidden name=content>
<SCRIPT type=text/javascript>

var editor = new KindEditor("editor");
	editor.hiddenName = "content";
	editor.skinPath = "ewebeditor/skins/default/";
	editor.iconPath = "ewebeditor/skins/faceicon/";
	editor.imageAttachPath = "upload_files";
	editor.imageUploadCgi = "upfile_eweb.jsp";
	editor.cssPath = "ewebeditor/common.css";
	editor.editorWidth = "100%";
	editor.formBorderColor = "#416cc7";
	editor.parentID = "editor_data";

function get_height(){
	var h = 0;
	if(parent.document.getElementById("Web_Edit") != null){
		h = (parent.document.getElementById("Web_Edit").height - 60);
	}
	if(h<10){
		h = 100;
	}
	return (h + 'px');
}
function show_edit(){
	editor.editorHeight = get_height();
	editor.show();
}

setcode();
show_edit();

function setcode(){	
	document.getElementById('content').value=parent.document.getElementById(editor.parentID).value;
}

document.onmousemove=editor.code;


function set_input(){
	editor.code();	
	setTimeout("set_input()",30);
}
set_input();

</SCRIPT>
</BODY></HTML>
