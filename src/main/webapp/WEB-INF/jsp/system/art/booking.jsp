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
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>艺术商品管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sa/listBooking">订单管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sa/editBooking/?ID=${booking.id}">编辑</a></li>
		</ul>
	</div>
	<!-- /section:basics/navbar.layout -->

				<div class="page-content" style="padding: 10px;">
					<div class="container-fluid">
						<form class="b-info" action="sa/saveBookingLog" name="bookingLogForm" id="bookingLogForm" method="post" enctype="multipart/form-data">
							<p class="b-info-item"><span>用户信息</span></p>
							<hr class="b-info-line">
							<div class="row ">
								<div class="col-md-6">
									<div class="form-group">
									    <label class="col-sm-4 control-label">用户名</label>
									    <div class="col-sm-8 control-value">${booking.user_name}</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
									    <label class="col-sm-4 control-label">手机号</label>
									    <div class="col-sm-8 control-value">${booking.user_phone}</div>
								  	</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
									    <label class="col-sm-4 control-label">邮箱</label>
									    <div class="col-sm-8 control-value">${booking.user_email}</div>
								  	</div>	
								</div>
							</div>
							<p class="b-info-item"><span>预订信息</span></p>
							<hr class="b-info-line">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									    <label class="col-sm-2 control-label">商品名称</label>
									    <div class="col-sm-10 control-value"><p>${booking.art_name}<p></div>
								  	</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									    <label class="col-sm-2 control-label">商品简介</label>
									    <div class="col-sm-10 control-value"><p>${booking.art_desc}<p></div>
								  	</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									    <label class="col-sm-2 control-label">配送至</label>
									    <div class="col-sm-10 control-value">北京市 朝阳区 望京明苑222号楼1111</div>
								  	</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									    <label class="col-sm-2 control-label">重量</label>
									    <p class="col-sm-10 control-value">1.5Kg</p>
								  	</div>	
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									    <label class="col-sm-2 control-label">数量</label>
									    <p class="col-sm-2 control-value">1</p>
									    <label class="col-sm-2 control-label">付款</label>
									    <p class="col-sm-2 control-value">1999<span></span>￥</p>
								  	</div>	
								</div>
							</div>
							
							<p class="b-info-item"><span>订单管理</span></p>
							<hr class="b-info-line">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
									    <label class="col-sm-2 control-label">当前状态</label>
									    <div class="col-sm-10 control-value">${booking.booking_name}</div>
								  	</div>
								</div>
							</div>
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
													<td class="center option-comment">${log.pre_state_name} -> ${log.cur_state_name}</td>
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

function addLogItem() {
	var strHtml = "<td class='center' style='width:100px !important;'>"
		+ "<a class='btn btn-minier btn-white' onclick='saveLogItem()'  style='color:#2283c5 !important;'>保存</a>"
		+ "<a class='btn btn-minier btn-white' onclick='cancelAddLogItem()' style='color:#2283c5 !important;'>取消</a></td>"
		+ "<td class='center padding0 bl_date'><input type='text' class='form-control date-picker'/></td>"
		+ "<td class='center padding0 bl_state'>"
		+ "<select>" 
			+ "<option value='0'>待确认</option>"
			+ "<option value='1'>待支付</option>"
			+ "<option value='2'>待收款</option>"
			+ "<option value='3'>已支付</option>"
			+ "<option value='4'>已结束</option>"
		+ "</select>"
		+ "</td>"
		+ "<td class='center padding0 bl_comment'><input type='text' class='form-control'/></td>";
	$("#tr_add").html(strHtml);
	$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});
}

function saveLogItem() {
	var values = [];
	var ind = 0;
	$("#tr_add").find('td').each(function () {
		
		if(ind == 2)
			values[ind] = $(this).find('select').val();
		else
			values[ind] = $(this).find('input').val();
		
		ind++;
	});
	var date = values[1];
	var state = values[2];
	var comment = values[3];
	var bid = '${booking.id}';
	var preState = '${booking.booking_state}';
	
	if(preState == state){
		alert("Re-select Booking State");
		return;
	}
	top.jzts();
	$.ajax({
		type: "POST",
		url: 'sa/saveBookingLog',
		data: { BOOKING_ID: bid, PRE_STATE: preState, CUR_STATE: state,  COMMENT: comment},
		dataType: 'json',
		cache: false,
		success: function (data) {
			if(data.result == "success"){
				$("#bookingLogForm").attr( "action", "sa/editBooking/?ID=" + '${booking.id}');
				$("#bookingLogForm").submit();
			}else{
				alert('fail');
			}
			top.hangge();
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
	$("#bookingLogForm").attr( "action", "sa/listBooking");
	$("#bookingLogForm").submit();
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
