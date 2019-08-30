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
	.b-info-line{
		border-color: gray;
		margin: 0px 0px 5px;
	}
	.b-info-item{
		margin: 0px;
		padding-left: 20px;
		font-size: 14px;
		font-weight: bold;
		color: black;
	}
	.b-info .control-label{
		text-align:right;
		color: #9C9C9C;
	}
	.b-info-label{
		padding: 5px 10px;
		text-align: right;
	}
	.b-info-value{
		padding: 5px 10px;
	}
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>旅游管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="tour/listTourBooking">游客管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="tour/editTourBooking/?ID=${booking.id}">编辑</a></li>
		</ul>
	</div>
	<!-- /section:basics/navbar.layout -->

				<div class="page-content" style="padding: 10px;">
					<div class="container">
						<form class="b-info" action="tour/saveBooking" name="tourBookingForm" id="tourBookingForm" method="post" enctype="multipart/form-data">
							<p class="b-info-item"><span>用户信息</span></p>
							<hr class="b-info-line">
							<table style="margin: 0px 40px">
								<tr>
									<td class="b-info-label">用户名:</td>
									<td class="b-info-value">${booking.user_name}</td>
									<td class="b-info-label">手机号:</td>
									<td class="b-info-value">${booking.user_phone}</td>
								</tr>
								<tr>
									<td class="b-info-label">邮箱:</td>
									<td class="b-info-value">${booking.user_email}</td>
								</tr>
							</table>
							<p class="b-info-item"><span>预订信息</span></p>
							<hr class="b-info-line">
							<table style="margin: 0px 40px">
								<tr>
									<td class="b-info-label">商品名称:</td>
									<td class="b-info-value">${booking.art_name}</td>
								</tr>
								<tr>
									<td class="b-info-label">商品简介:</td>
									<td class="b-info-value">${booking.art_desc}</td>
								</tr>
								<tr>
									<td class="b-info-label">配送至:</td>
									<td class="b-info-value">北京市 朝阳区 望京明苑222号楼1111</td>
								</tr>
								<tr>
									<td class="b-info-label">价格:</td>
									<td class="b-info-value">
										<span>￥</span>${booking.a_exp}<span>(成人)</span>
										<span style="margin-left:30px;">￥</span>${booking.c_exp}<span>(儿童)</span></td>
								</tr>
								<tr>
									<td class="b-info-label">参团人数:</td>
									<td class="b-info-value">
										<div class="row" style="padding: 0px 12px;">
											<input style="float:left; width: 70px;" class="form-control" id="ADULT_NUM" type="number" value="${booking.adult_num}">
											<span style="float:left; padding: 7px 0px;">(成人)</span>
											<input style="float:left; width: 70px;" class="form-control" id="CHILD_NUM" type="number" value="${booking.child_num}">
											<span style="float:left;padding: 7px 0px;">(儿童)</span>
										</div>
									</td>
								</tr>
								<tr>
									<td class="b-info-label">总计金额:</td>
									<td class="b-info-value">${booking.total_expense}<span>￥</span></td>
								</tr>
								<tr>
									<td class="b-info-label">出发日期:</td>
									<td class="b-info-value">
										<input id="RS_TIME" class=" m-ctrl-medium date-picker" type="text" value="${booking.start_date}"/>
									</td>
								</tr>
							</table>
							
							<p class="b-info-item"><span>订单管理</span></p>
							<hr class="b-info-line">
							<table style="margin: 0px 40px">
								<tr>
									<td class="b-info-label">当前状态:</td>
									<td class="b-info-value">${booking.booking_state}</td>
								</tr>
							</table>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
											<tbody>												
											<c:choose>										
											<c:when test="${not empty logs}">
											<%
									            int numStds = 0;								
									            pageContext.setAttribute("numStds", numStds);
									        %>
											<c:forEach items="${logs}" var="log" varStatus="vs">
												<%
										            numStds++;								
										            pageContext.setAttribute("numStds", numStds);
										        %>
												<tr id="row_${log.id}"><td class="center">${numStds}</td>
													<td class="center option-name">${log.update_time}</td>
													<td class="center option-comment">${log.pre_name} -> ${log.cur_name}</td>
													<td class="center option-comment">${log.comment}</td>
												</tr>
											</c:forEach>
											</c:when>
											</c:choose>
											</tbody>
											<tr id="tr_add">
												<td colspan="4">
													<button style="padding:0px 7px;" type="button" class="btn" onclick="addLogItem();">+</button>
												</td>
											</tr>
										</table>									    
								  	</div>
								</div>
							</div>
						</form>	
			            <div class="row">
			            	<div class="col-md-2">
			            	</div>
			            	<div class="col-md-10 form-group">
			            		<button onclick="saveBooking();" class="btn btn-primary">保存</button>
			            		<button onclick="goListPage();" class="btn btn-primary">返回</button>
			            	</div>
			            </div>
					</div>
				</div>
>
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
$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

var consts = ${cList};
var cSlect = "<select class='form-control'>";
for(var i = 0; i < consts.length; i++){
	cSlect = cSlect + "<option value='" + consts[i].value + "'>" + consts[i].name + "</option>"
}	
cSlect = cSlect + "</select>";

function addLogItem() {
	
	var strHtml = "<td class='center' style='width:200px !important;'>"
		+ "<a class='btn btn-minier btn-white' onclick='saveLogItem()'  style='color:#2283c5 !important;'>保存</a>"
		+ "<a class='btn btn-minier btn-white' onclick='cancelAddLogItem()' style='color:#2283c5 !important;'>取消</a></td>"
		+ "<td class='center padding0 bl_date'><input type='text' class='form-control date-picker'/></td>"
		+ "<td class='center padding0 bl_state'>" + cSlect + "</td>"
		+ "<td class='center padding0 bl_comment'><input type='text' class='form-control'/></td>";
	$("#tr_add").html(strHtml);
	$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});
}

function saveLogItem() {
	var values = [];
	var ind = 0;
	$("#tr_add").find('td').each(function () {
		if(ind == 1 || ind == 3){
			values[ind] = $(this).find('input').val();
		}
		if(ind == 2){
			values[ind] = $(this).find('select').val();
		}
		ind++;
	});
	
	var tt = $(this);
	
	var _data = {
			PRE_STATE: '${booking.cur_state}',
			CUR_STATE: values[2],
			COMMENT: values[3],
			UPDATE_TIME: values[1],
			BOOKING_ID: '${booking.id}'
	};
	
 	$.ajax({
		type: "POST",
		url: 'tour/insert_booking_log',
		data: _data,
		dataType: 'json',
		cache: false,
		success: function (data) {
			if (data.success == "ok") {
				$("#tourBookingForm").attr( "action", "tour/editTourBooking/?ID=${booking.id}");
				$("#tourBookingForm").submit();
			} else {
				alert("Failed");
				/* var f = tt.parent().parent().parent().find( ".ov_name");
				f.tips({
					side: 3,
					msg: 'Already Exists Item',
					bg: '#AE81FF',
					time: 2
				});
				f.focus();
				f.css("background-color", "white"); */
			}
		}
	});
}

function cancelAddLogItem() {
	var strHtml = '<td colspan="4"><button style="padding:0px 7px;" type="button" class="btn" onclick="addLogItem();">+</button></td>';
	$("#tr_add").html(strHtml);
}

function saveBooking(){
	
}
function goListPage(){
	$("#tourBookingForm").attr( "action", "tour/listTourBooking");
	$("#tourBookingForm").submit();
}

$(function() {
	if(!ace.vars['touch']) {
		$('.chosen-select').chosen({allow_single_deselect:true}); 
		$(window)
		.off('resize.chosen')
		.on('resize.chosen', function() {
			$('.chosen-select').each(function() {
				 var $this = $(this);
				 $this.next().css({'width': $this.parent().width()});
			});
		}).trigger('resize.chosen');
		$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
			if(event_name != 'sidebar_collapsed') return;
			$('.chosen-select').each(function() {
				 var $this = $(this);
				 $this.next().css({'width': $this.parent().width()});
			});
		});
		$('#chosen-multiple-style .btn').on('click', function(e){
			var target = $(this).find('input[type=radio]');
			var which = parseInt(target.val());
			if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
			 else $('#form-field-select-4').removeClass('tag-input-style');
		});
	}

	var active_class = 'active';
});
</script>
