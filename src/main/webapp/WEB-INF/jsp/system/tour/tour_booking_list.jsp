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
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>旅游管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="tour/listTourBooking">游客管理</a></li>
		</ul>
	</div>
	<!-- /section:basics/navbar.layout -->

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<table>
				<tr>
					<td class="ipt-td">用户名</td>
					<td class="ipt-td"><input class="form-control" id="USER_NAME" type="text"></td>
					<td class="ipt-td">预订日期</td>
					<td class="ipt-td"><input id="RS_TIME" class="form-control date-picker" type="text"/></td>
					<td class="ipt-td">---</td>
					<td class="ipt-td"><input id="RE_TIME" class="form-control date-picker" type="text"/></td>
					<td class="ipt-td"><button onclick="initCtrl();" class="btn btn-primary">重置</button></td>
				</tr>
				<tr>
					<td class="ipt-td">状态</td>
					<td class="ipt-td">
						<select class="form-control" id="BOOKING_STATE">
							<option value="">全部</option>
							<c:choose>										
								<c:when test="${not empty booking_state}">
									<c:forEach items="${booking_state}" var="state" varStatus="vs">
										<option value="${state.CONST_VALUE}">${state.CONST_NAME}</option>
									</c:forEach>
								</c:when>
							</c:choose>
						</select>
					</td>
					<td class="ipt-td">出发日期</td>
					<td class="ipt-td">
						<input id="SS_TIME"class="form-control date-picker" type="text"/></td>
					<td class="ipt-td">---</td>
					<td class="ipt-td">
						<input id="SE_TIME" class="form-control date-picker" type="text"/></td>
					<td class="ipt-td">
						<button onclick="search();" class="btn btn-primary">查询</button></td>
				</tr>
			</table>
			
			<form action="tour/listTourBooking" method="post" name="tourBookingForm" id="tourBookingForm">
				
				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 80px;">序号</th>
							<th class="center" style="width: 150px;">用户名</th>
							<th class="center" style="width: 100px;">预订时间</th>
							<th class="center" style="width: 100px;">出发日期</th>
							<th class="center" style="width: 100px;">状态</th>
							<th class="center" style="width: 100px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty tourBookingList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${tourBookingList}" var="tourBooking" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr tr_id="${tourBooking.id}"><td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<td class="center b-user-name">${tourBooking.customer}</td>
										<td class="center b-req-date">${tourBooking.request_date}</td>
										<td class="center b-sta-date">${tourBooking.start_date}</td>
										<td class="center b-state">${tourBooking.booking_state}</td>
										<td class="center">
											<div class="btn-group">
												<a class="btn btn-minier btn-white btn_m_edit" target="mainFrame" href="tour/editTourBooking/?ID=${tourBooking.id}">编辑</a>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
				<div class="position-relative">
					<table style="width: 100%;">
						<tr>
							<td style="vertical-align: top;">
								<div class="pagination"
									style="float: left; padding-top: 0px; margin-top: 0px;">${page.pageStr}
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
			<form action="" style="display: none;" method="post" name="tourBookingEditForm" id="tourBookingEditForm">
				<input type="text" id="BU_NAME" name="USER_NAME" value="" />
				<input type="text" id="BR_STIME" name="RS_TIME" value="" />
				<input type="text" id="BR_ETIME" name="RE_TIME" value="" />
				<input type="text" id="BS_STIME" name="SS_TIME" value="" />
				<input type="text" id="BS_ETIME" name="SE_TIME" value="" />
				<input type="text" id="B_STATE" name="B_STATE" value="" />
			</form>
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

<script>

$(top.hangge());

function initCtrl(){
	$("#USER_NAME").val("");
	$("#RS_TIME").val("");
	$("#RE_TIME").val("");
	$("#SS_TIME").val("");
	$("#SE_TIME").val("");
	$("#BOOKING_STATE").val("");
	search();
}
function search(){
	$("#BU_NAME").val($("#USER_NAME").val());
	$("#BR_STIME").val($("#RS_TIME").val());
	$("#BR_ETIME").val($("#RE_TIME").val());
	$("#BS_STIME").val($("#SS_TIME").val());
	$("#BS_ETIME").val($("#SE_TIME").val());
	$("#B_STATE").val($("#BOOKING_STATE").val());
	
	$("#tourBookingEditForm").attr("action", "tour/listTourBooking");
	$("#tourBookingEditForm").submit();
}

$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

jQuery(function() {
	
	$("#USER_NAME").val('${pd.USER_NAME}');
	$("#RS_TIME").val('${pd.RS_TIME}');
	$("#RE_TIME").val('${pd.RE_TIME}');
	$("#SS_TIME").val('${pd.SS_TIME}');
	$("#SE_TIME").val('${pd.SE_TIME}');
	$("#BOOKING_STATE").val('${pd.B_STATE}');
});

</script>
