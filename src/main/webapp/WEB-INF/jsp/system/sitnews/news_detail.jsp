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

<link rel="stylesheet" type="text/css"
	href="static/global/plugins/jstree/dist/themes/default/style.min.css">
<link rel="stylesheet" type="text/css"
	href="static/global/css/plugins.css">
<style type="text/css">
.btn.btn-app.btn-xs {
	width: 64px;
	font-size: 12px;
	border-radius: 6px;
	padding-bottom: 2px;
	padding-top: 2px;
	line-height: 1.45;
}
.input-controls .col-md-12{
margin-bottom:5px;
}
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>动态新闻管理</a><span>≫ </span></li>
			<li><a target="mainFrame" href="sn/listNews">新闻管理</a><span>≫ </span></li>
			<li><a target="mainFrame" href="sn/viewNews?ID=${news.ID}">查看</a></li>
		</ul>
	</div>
	<div class="page-content" style="padding: 10px;bottom: 50px;">
		<div class="container">
			<span id="s_org_content" style="display:none;">${news.SN_CONTENT}</span>
			<div class="row" id="detail_view"></div>
		</div>
	</div>
	<div class="page-footer" style="position: absolute;bottom: 0px;padding: 5px;width: 100%;">
    	<button onclick="goListPage();" class="btn btn-primary" style="float:right;">返回</button>
    </div>
    <form action="" style="display: none;" method="post" name="newsForm" id="newsForm"></form>
			
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
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"
		type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>

</body>

<script>
$(top.hangge());

var s_content = $('#s_org_content').html();
$('#detail_view').append(s_content);

function goListPage(){
	$("#newsForm").attr( "action", "sn/listNews" + "?" + $("#newsForm").serialize());
	$("#newsForm").submit();
}

</script>
