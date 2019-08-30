<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<base href="<%=basePath%>">

<link rel="stylesheet" href="static/ace/css/chosen.css" />

<%@ include file="../index/top.jsp"%>

<link rel="stylesheet" href="static/ace/css/datepicker.css" />
<link rel="stylesheet" type="text/css"
	href="static/global/css/mycomponents.css">

<link rel="stylesheet" type="text/css" href="static/global/plugins/jstree/dist/themes/default/style.min.css">
<link rel="stylesheet" type="text/css" href="static/global/css/plugins.css">
<link rel="stylesheet" href="static/ryn/jqw/jqx.base.css" type="text/css" />

<style type="text/css">
	.input-controls .col-md-12{
		margin-bottom:5px;
	}
	.jqx-tree-item-li{
		border-color:inherit !important; 
	}
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>系统管理</a><span> ≫ </span></li>
			<li><a>消息管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sys/listNotiTemplate">通知模板管理</a><span> ≫ </span></li>
			<c:if test="${empty template}">
				<li><a target="mainFrame" href="sys/editNotiTemplate/?ID=${template.ID}">新增公告</a></li>
			</c:if>
			<c:if test="${not empty template}">
				<li><a target="mainFrame" href="sys/editNotiTemplate/?ID=${template.ID}">编辑公告</a></li>
			</c:if>
		</ul>
	</div>
	
	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<form  action="sys/saveNotiTemplate" name="templateForm" id="templateForm" method="post" enctype="multipart/form-data">
				<div class="row input-controls">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">公告名称<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<input class="form-control" id="T_NAME" type="text" value="${template.NAME}">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">发送方式<span class="required-item">*</span></label>
							<div class="col-sm-4">
								<select class="form-control" id="T_KIND" onchange="selTempKind()">
									<option value="0">全部</option>
									<option value="1">短信</option>
									<option value="2">邮件</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-12" id="t_content_area">
						<div class="form-group">
							<label class="col-sm-2 control-label">短信内容<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<textarea class="form-control" id="T_CONTENT" maxlength="100" >${template.CONTENT}</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-12" id="t_temp_area">
						<div class="form-group">
							<label class="col-sm-2 control-label">邮件内容<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<span id="T_ORG_TEMP" style="display:none;">${template.TEMP}</span>							
								<div id="T_TEMP_EDIT_AREA">
									<INPUT id="editor1" type="hidden" name="T_TEMP" value="">
									<iframe id="Web_Edit" name="Web_Edit" style="border:0px;" width="100%" height="300"
										src="static/editor1.jsp"></iframe>
									<span style="display: none;">
										<a href="static/editor1.jsp" target="Web_Edit">W3Schools.com</a>
									</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group">
							<label class="radio-inline">
			                    <input type="checkbox" name="autoOption" id="T_AUTO_SEND">立即发送
			                </label>
						</div>
					</div>
				</div>
			</form>	
            <div class="row">
            	<div class="col-md-2">
            	</div>
            	<div class="col-md-10 form-group">
            		<button onclick="saveTemplate();" class="btn btn-primary">发布</button>
            		<button onclick="goListPage();" class="btn btn-primary">返回</button>
            	</div>
            </div>
		</div>
	</div>

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
	
	<!-- basic scripts -->
	<%@ include file="../index/foot.jsp"%>
	<script src="static/ace/js/bootbox.js" type="text/javascript"></script>
	<script src="static/ace/js/ace/ace.js" type="text/javascript"></script>
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"
		type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	
	<script type="text/javascript" src="static/ryn/jqw/jqxcore.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxbuttons.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxscrollbar.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxpanel.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxtree.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxexpander.js"></script>
	
</body>

<script>
$(top.hangge());
var _auto = "0";
_auto = '${template.AUTO_SEND}';
_auto == "0" ? $("#T_AUTO_SEND").attr("checked", false) : $("#T_AUTO_SEND").attr("checked", true);

if('${template.ID}' == '')
	$('#T_KIND').val('1');
else
	$('#T_KIND').val('${template.KIND}');

$('#editor1').val($('#T_ORG_TEMP').html());
selTempKind();

function selTempKind(){
	var num = parseInt($("#T_KIND").val());
	if($("#T_KIND").val() == "")
		num = 0;
	switch(num){
	case 0:
		$('#t_content_area').show();
		$('#t_temp_area').show();
		break;
	case 1:
		$('#t_content_area').show();
		$('#t_temp_area').hide();
		break;
	case 2:
		$('#t_content_area').hide();
		$('#t_temp_area').show();
		break;
	}
}

function checkValidity(){
	if ($("#T_NAME").val() == "") {

		$("#T_NAME").tips({
			side : 2,
			msg : '请输入公告名称',
			bg : '#AE81FF',
			time : 3
		});

		$("#T_NAME").focus();
		return false;
	}
	
	
	if ($("#T_KIND").val() == "") {

		$("#T_KIND").tips({
			side : 2,
			msg : '请选择发送方式',
			bg : '#AE81FF',
			time : 3
		});

		$("#T_KIND").focus();
		return false;
	}
	
	if ($("#T_CONTENT").val() == "") {

		$("#T_CONTENT").tips({
			side : 2,
			msg : '请输入短信内容',
			bg : '#AE81FF',
			time : 3
		});

		$("#T_CONTENT").focus();
		return false;
	}
	
	
	if ($("#T_TEMP").val() == "") {

		$("#T_TEMP").tips({
			side : 2,
			msg : '请输入邮件内容',
			bg : '#AE81FF',
			time : 3
		});

		$("#editor1").focus();
		return false;
	}
	
	return true;
}
function saveTemplate(){
	/* $("#placeSelect option:selected").text(); */
	if(checkValidity()){
		
		var _ID = '${template.ID}';
		
		var _v = $("#T_AUTO_SEND").prop('checked') == true ? 1 : 0;
	    
	    var param = {
	    		"ID": _ID,
				"NAME":  $("#T_NAME").val(),
				"AUTO_SEND": _v,
				"KIND": $("#T_KIND").val(),
				"CONTENT": $("#T_CONTENT").val(),
				"TEMP": $("#editor1").val(),
				"STATE": 1
	    };
		
		var _url = "sys/saveNotiTemplate";
		
		$.ajax({
			type: "POST",
			url: _url,
			data: param,
			dataType:'json',
			cache: false,
			success: function (data) {
				if ("success" == data.result) {
					$("#templateForm").attr( "action", "sys/listNotiTemplate/?" + $("#templateForm").serialize());
					$("#templateForm").submit();
				} else {
					alert("FAILED");
				}
			}
		});
	}
}
function goListPage(){
	$("#templateForm").attr( "action", "sys/listNotiTemplate/?" + $("#templateForm").serialize());
	$("#templateForm").submit();
}

</script>
