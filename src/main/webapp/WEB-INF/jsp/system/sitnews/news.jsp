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
<link rel="stylesheet" type="text/css" href="static/global/css/mycomponents.css">
<link rel="stylesheet" type="text/css" href="static/global/plugins/jstree/dist/themes/default/style.min.css">
<link rel="stylesheet" type="text/css" href="static/global/css/plugins.css">
<style type="text/css">
.input-controls .col-md-12{
	margin-bottom:5px;
}
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>动态新闻管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sn/listNews">新闻管理</a><span> ≫ </span></li>
			<c:if test="${not empty news.ID}">
				<li><a target="mainFrame" href="sn/editNews?ID=${news.ID}">编辑</a></li>
			</c:if>
			<c:if test="${empty news.ID}">
				<li><a target="mainFrame" href="sn/editNews">新增</a></li>
			</c:if>
		</ul>
	</div>
	

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<form  action="sn/saveNews" name="newsForm" id="newsForm" method="post" enctype="multipart/form-data">
				<div class="row input-controls">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">标题<span class="required-item">*</span></label>
							<div class="col-sm-8">
								<input class="form-control" id="S_TITLE" type="text" value="${news.SN_TITLE}">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">图片1<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<input type="file" id="IMAGE_SRC" name="IMAGE_SRC"
											style="display: none;" onchange="uploadFile(this)" />
								<a onclick="fileType('IMAGE_SRC')" id="IAMGE_SRC_BTN"
									class="btn btn-mini btn-success"
									style="margin-bottom:5px; height: 30px; align: center;">选择文件</a>
								<c:if test="${not empty news.SN_IMAGE}">
									<img id="IMAGE_PREVIEW" alt="" src="uploadFiles/uploadImgs/sn/${news.SN_IMAGE}" style="height: 45px;">
								</c:if>
								<c:if test="${empty news.SN_IMAGE}">
									<img id="IMAGE_PREVIEW" alt="" src="" style="height: 45px; width:45px;">
								</c:if>
								<input type="text"
									name="IMAGE_NAME" id="IMAGE_NAME" accept="image/*"
									maxlength="32" placeholder="来选择任何文件" title="图片"
									style="display: none;" value="${news.SN_IMAGE}" readonly/>
								<p class="help-block">图片大小：1200 * 300</p>
							</div>
						</div>
					</div>        
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">动态内容<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<span id="s_org_content" style="display:none;">${news.SN_CONTENT}</span>							
								<div id="s_content_edit_area">
									<INPUT id="editor_data" type="hidden" name="S_CONTENT" value="">
									<iframe id="Web_Edit" name="Web_Edit" style="border:0px;"
										src="static/standard_editor.jsp" width="100%" height="300"></iframe>
									<span style="display: none;">
										<a href="static/standard_editor.jsp" target="Web_Edit">W3Schools.com</a>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>	
	            <div class="row">
	            	<div class="col-md-2">
	            	</div>
	            	<div class="col-md-10 form-group">
	            		<button onclick="saveNews();" class="btn btn-primary">发布</button>
	            		<button onclick="goListPage();" class="btn btn-primary">返回</button>
	            	</div>
	            </div>
		</div>
	</div>

	
	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

	<div></div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<%@ include file="../index/foot.jsp"%>
	<script src="static/ace/js/bootbox.js" type="text/javascript"></script>
	<script src="static/ace/js/ace/ace.js" type="text/javascript"></script>
	<script src="static/ace/js/date-time/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>

</body>

<script>
$(top.hangge());

function fileType(file) {
	document.getElementById(file).click();
}
	
function uploadFile(obj) {
	$("#IMAGE_NAME").val(obj.files[0].name);
	var fileType = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	if (fileType != '.jpg' && fileType != '.png' && fileType != '.bmp') {
		$("#IMAGE_NAME").tips({
			side : 3,
			msg : '请上传图片格式的文件',
			bg : '#AE81FF',
			time : 3
		});
		$("#IMAGE_NAME").val('');
		document.getElementById("IMAGE_SRC").files[0] = '请选择图片格式的文件';
		return;
	}
	$.each(obj.files, function(_, file) {
        var reader = new FileReader();
        reader.onload = function (ev) {
        	$("#IMAGE_PREVIEW").attr('src', ev.target.result);
        }
        reader.readAsDataURL(file);
    });
}

var s_content = $('#s_org_content').html();
$('#editor_data').val(s_content);

function checkValidity(){
	if ($("#S_TITLE").val() == "") {

		$("#S_TITLE").tips({
			side : 2,
			msg : '请输入标题',
			bg : '#AE81FF',
			time : 3
		});

		$("#S_TITLE").focus();
		return false;
	}
	else{
		$("#S_TITLE").val(jQuery.trim($('#S_TITLE').val()));
	}
	
	if ($("#editor_data").val() == "") {

		$("#editor_data").tips({
			side : 2,
			msg : '请输入动态内容',
			bg : '#AE81FF',
			time : 3
		});

		$("#editor_data").focus();
		return false;
	}
	else{
		$("#editor_data").val(jQuery.trim($('#editor_data').val()));
	}
	
	if ($("#IMAGE_NAME").val() == "") {

		$("#IAMGE_SRC_BTN").tips({
			side : 2,
			msg : '请选择图片1',
			bg : '#AE81FF',
			time : 3
		});

		$("#IAMGE_SRC_BTN").focus();
		return false;
	}
	else{
		$("#IMAGE_NAME").val(jQuery.trim($('#IMAGE_NAME').val()));
	}
	
	return true;
}
function saveNews(){
	/* $("#placeSelect option:selected").text(); */
	if(checkValidity()){
		
		var fd = new FormData();
	    var imageFile = $('#IMAGE_SRC')[0].files;
	    
	    var _ID = '${news.ID}';
	    
	    fd.append("ID",_ID);
   		fd.append("SN_TITLE", $("#S_TITLE").val());
	 	fd.append("SN_CONTENT", $("#editor_data").val());
		fd.append("IMG_S", imageFile[0]);
	 	fd.append("SN_IMAGE", $("#IMAGE_NAME").val());
		
		var _url = "";
		_url = "sn/saveNews";
		$.ajax({
			type: "POST",
			url: _url,
			data: fd,
			contentType: false,
       	 	processData: false,
			success: function (data) {
				if ("success" == data.result) {
					$("#newsForm").attr( "action", "sn/listNews");
					$("#newsForm").submit();
				} else {
					alert("FAILED");
				}
			}
		});
	}
}
function goListPage(){
	$("#newsForm").attr( "action", "sn/listNews");
	$("#newsForm").submit();
}

</script>
