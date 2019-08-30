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
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>特产商品管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sp/listBooking">订单管理</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<table>
				<tr>
					<td class="ipt-td">订单编号</td>
					<td class="ipt-td" colspan="3"><input class="form-control" id="BOOKING_NUM" type="text"></td>
					<td class="ipt-td"><button onclick="initCtrl();" class="btn btn-primary">重置</button></td>
				</tr>
				<tr>
					<td class="ipt-td">用户名</td>
					<td class="ipt-td"><input class="form-control" id="USER_NAME" type="text"></td>
					<td class="ipt-td">状态</td>
					<td class="ipt-td">
						<select class="form-control" id="BOOKING_STATE">
							<option value="">全部</option>
							<option value="0">待付款</option>
							<option value="1">待收款</option>
							<option value="3">已完成</option>
						</select>
					</td>
					<td class="ipt-td"><button onclick="search();" class="btn btn-primary">查询</button></td>
				</tr>
			</table>
			
			<form action="sp/listBooking" method="post" name="bookingForm" id="bookingForm">
				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 80px;">序号</th>
							<th class="center" style="width: 150px;">订单编号</th>
							<th class="center" style="width: 150px;">用户名</th>
							<th class="center" style="width: 100px;">状态</th>
							<th class="center" style="width: 100px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty bookingList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${bookingList}" var="booking" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr id="${booking.id}">
										<td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<td class="center b-req-date">${booking.id}</td>
										<td class="center b-user-name">${booking.customer}</td>
										<td class="center b-state">${booking.booking_state}</td>
										<td class="center">
											<div class="btn-group">
												<c:if test="${booking.booking_state == '已完成'}">
													<a class="btn btn-minier btn-white btn_m_edit" target="mainFrame" href="sp/booking/?ID=${booking.id}&EDIT=false">查看</a>
												</c:if>
												<c:if test="${booking.booking_state != '已完成'}">
													<a class="btn btn-minier btn-white btn_m_edit" target="mainFrame" href="sp/booking/?ID=${booking.id}&EDIT=true">编辑</a>
												</c:if>
												
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
			<form action="" style="display: none;" method="post" name="bookingEditForm" id="bookingEditForm">
				<input type="text" id="B_NUM" name="BOOKING_NUM" value="" />
				<input type="text" id="B_USER" name="USER_NAME" value="" />
				<input type="text" id="B_STATE" name="BOOKING_STATE" value="" />
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

jQuery(function() {
	$("#USER_NAME").val('${pd.USER_NAME}');
	$("#BOOKING_NUM").val('${pd.BOOKING_NUM}');
	$("#BOOKING_STATE").val('${pd.BOOKING_STATE}');
});


function initCtrl(){
	$("#USER_NAME").val("");
	$("#BOOKING_NUM").val("");
	$("#BOOKING_STATE").val("");
	search();
}
function search(){
	$("#B_USER").val($("#USER_NAME").val());
	$("#B_NUM").val($("#BOOKING_NUM").val());
	$("#B_STATE").val($("#BOOKING_STATE").val());
	
	$("#bookingEditForm").attr("action", "sp/listBooking");
	$("#bookingEditForm").submit();
}

$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

</script>
