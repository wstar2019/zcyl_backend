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

<style>
	.padding30{
		padding-left: 30px;
	}
</style>

<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>统计</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sc/listCustomerView">用户浏览统计</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="row">
			<div class="tabbable">
				<ul class="nav nav-tabs" id="myTab">	
					<li class="" id="tab_mainMng">
						<a data-toggle="tab" href="#mainMng">浏览日期统计</a>
					</li>
					<li class="" id="tab_commodityMng">
						<a data-toggle="tab" href="#commodityMng">浏览商品统计 </a>
					</li>
				</ul>
				<div class="tab-content">
					<div id="mainMng" class="tab-pane">
						<form action="sc/listCustomerDateView.do" method="post" name="CustomerDateForm" id="CustomerDateForm">
							<table style="margin-top: 5px; margin-left: 20px;">
								<tr>
									<td class="td_label">
										<div class="nav-search">统计方式</div>
									</td>
									<td class="padding30">
										<label class="radio">
											<input id="optionRadio1" type="radio" name="RADIO_TYPE" checked="checked"/>
											月
										</label>										
									</td>
									<td class="padding30">
										<label class="radio">
											<input id="optionRadio2" type="radio" name="RADIO_TYPE" />
											季
										</label>
									</td>
									<td class="padding30">
										<label class="radio">
											<input id="optionRadio2" type="radio" name="RADIO_TYPE" />
											年
										</label>
									</td>
									<td class="padding30">
										<div class="nav-search">统计对象</div>
									</td>
									<td style="padding-left: 10px;">
										<select class="form-control" style="width: 120px;" name="SC_OBJECT" id="sc_object" data-placeholder="" 
											>			
											<option value="0" selected>全部</option>
											<option value="1">短信</option>
											<option value="2">邮件</option>
										</select> 
									</td>
									<td class="padding30">
										<div class="nav-search">显示方式 </div>
									</td>
									<td style="padding-left: 10px;">
										<select class="form-control" style="width: 100px;" name="SC_OBJECT" id="sc_object" data-placeholder="" 
											>			
											<option value="0" selected>表</option>
											<option value="1">瓶装图</option>
											<option value="2">线型图</option>
										</select> 
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div id="commodityMng" class="tab-pane">
					
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- /.main-content -->

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

<script type="text/javascript">

	$(document).ready(function(){
		$("#tab_mainMng").addClass("active");
		$("#mainMng").addClass("active");
	})
</script>
