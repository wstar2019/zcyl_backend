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
	<!-- /section:basics/navbar.layout -->

				<div class="page-content" style="padding: 10px;">
					<div class="container-fluid">
						<div class="row form-horizontal">
							<div class="col-md-6">
				                <div class="form-group">
								    <label class="col-sm-2 control-label">商品名称</label>
								    <div class="col-sm-10">
								      <input class="form-control" id="A_NAME" type="text">
								    </div>
							  	</div>
							  	<div class="form-group">
								    <label class="col-sm-2 control-label">更新日期</label>
								    <div class="col-sm-10">
								    	<div class="form-inline">
											<div class="form-group" style="margin:0px;">
											  	<input id="S_TIME"class=" m-ctrl-medium date-picker" type="text"/>
											</div>
											<div class="form-group" style="margin:0px;">
											  	<span>---</span>
											</div>
											<div class="form-group" style="margin:0px;">
											  	<input id="E_TIME" class="m-ctrl-medium date-picker" type="text"/>
											</div> 
								    	</div>
							  		</div>
							  	</div>
				            </div>
				            <div class="col-md-6">
				            	<div class="form-group">
				            		<button onclick="initCtrl();" class="btn btn-primary">重置</button>
				            	</div>
				            	<div class="form-group">
				            		<button onclick="search();" class="btn btn-primary">查询</button>
				            		<button onclick="add();" class="btn btn-primary">上架新商品</button>
				            	</div>
				            </div> 
						</div>
						<form action="tour/listTourTourist" method="post" name="tourTouristForm" id="tourTouristForm">
							
							<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
								<thead>
									<tr>
										<th class="center" style="width: 80px;">序号</th>
										<th class="center" style="width: 100px;">用户名</th>
										<th class="center" style="width: 100px;">预订时间</th>
										<th class="center" style="width: 100px;">出发日期</th>
										<th class="center" style="width: 100px;">状态</th>
										<th class="center" style="width: 100px;">操作</th>
									</tr>
								</thead>
								<tbody>
									
									<c:choose>										
										<c:when test="${not empty tourTouristList}">
											<%
									            int numStds = 0;								
									            pageContext.setAttribute("numStds", numStds);
									        %>
											<c:forEach items="${tourTouristList}" var="tourTourist" varStatus="vs">
												<%
										            numStds++;								
										            pageContext.setAttribute("numStds", numStds);
										        %>
												<tr tr_id="${tourTourist.ID}"><td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
													<td class="center tourist-name">${tourTourist.NAME }</td>
													<td class="center booking-time">${tourTourist.BOOKING_TIME }</td>
													<td class="center start-time">${tourTourist.START_TIME }</td>
													<td class="center tourist-state">${tourTourist.STATE }</td>
													<td class="center">
														<div class="btn-group">
															<a class="btn btn-minier btn-white btn_m_edit">编辑</a>
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
						<form action="tour/saveOptArt" style="display: none;" method="post" name="tourOptArtEditForm" id="tourOptArtEditForm">
							<input type="text" id="OT_NAME" name="NAME" value="" />
							<input type="text" id="OT_STIME" name="S_TIME" value="" />
							<input type="text" id="OT_ETIME" name="E_TIME" value="" />
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
function initCtrl(){
	$("#A_NAME").val("");
	$("#S_TIME").val("");
	$("#E_TIME").val("");
	search();
}
function search(){
	$("#OT_NAME").val($("#A_NAME").val());
	$("#OT_NAME").val($("#S_TIME").val());
	$("#OT_NAME").val($("#E_TIME").val());
	$("#tourOptArtEditForm").attr("action", "tour/listTourTourist" + "?" + $("#tourTouristForm").serialize());
	$("#tourOptArtEditForm").submit();
}

$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

$(top.hangge());
function addTourOption(){
	var options = {
			title: "新增选项",
			inputs:[
				{"type": "text", "label": "选项名称", "value": "", "idx": "NAME", "required": true },
				{"type": "textarea", "label": "描述", "value": "", "idx": "COMMENT", "required": true }
			]
		};
		bootbox.prompt(options, function(result) {
			if(result) {
				var _n, _c;
				for(var i = 0; i < result.length; i++){
					if(result[i].idx == "NAME"){
						_n = result[i].val;
					}
					if(result[i].idx == "COMMENT"){
						_c = result[i].val;
					}
				}
				
				if(_n == "" || null == _n){
					alert("请输入选项名称");
				}
				else{
					top.jzts();
					$.ajax({
						type: "POST",
						url: '<%=basePath%>tour/saveOpt',
				    	data: {NAME: _n, COMMENT: _c, STATE: 0},
						dataType:'json',
						cache: false,
						success: function(data){
							if("success" == data.result){
								 nextPage('${page.currentPage}');
							}else{
								alert('FAILED!');
							}
						}
					});
				}
			}
		});
}
function changeStateTourOption(_id, _n, _c, _s){
	var options = {
			message: "<div class='item-row'><div class='item-label'>选项名称 :</div>" + 
					 "<div class='item-value'>" + _n + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>当前状态 :</div>" + 
					 "<div class='item-value'>" + _c + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定切换吗?</div></div>",
			title: "选项状态"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			if(_s == "0" ){
				_s = 1
			}
			else{
				_s = 0;
			}
			$.ajax({
				type: "POST",
				url: '<%=basePath%>tour/changeStateOpt',
		    	data: {ID:_id, STATE:_s},
				dataType:'json',
				cache: false,
				success: function(data){
					if("success" == data.result){
						nextPage('${page.currentPage}');
					}else{
						alert('FAILED!');
					}
				}
			});
		}
	});
}
function delTourOption(_id, _name, _comment){
	var options = {
			message: "<div class='item-row'><div class='item-label'>选项名称 :</div>" + 
					 "<div class='item-value'>" + _name + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>当前状态 :</div>" + 
					 "<div class='item-value'>" + _comment + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除选项"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>tour/deleteOpt?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
			});
		}
	});
}
function updateTourOption(_id, _name, _comment){
	var options = {
		title: "编辑选项",
		inputs:[
			{"type": "text", "label": "选项名称", "value": _name, "idx": "NAME" },
			{"type": "textarea", "label": "描述", "value": _comment, "idx": "COMMENT" }
		]
	};
	bootbox.prompt(options, function(result) {
		if(result) {
			top.jzts();
			var _n, _c;
			for(var i = 0; i < result.length; i++){
				if(result[i].idx == "NAME"){
					_n = result[i].val;
				}
				if(result[i].idx == "COMMENT"){
					_c = result[i].val;
				}
			}
			$.ajax({
				type: "POST",
				url: '<%=basePath%>tour/editOpt',
		    	data: {ID:_id, NAME:_n, COMMENT: _c},
				dataType:'json',
				cache: false,
				success: function(data){
					if("success" == data.result){
						 var _row = $("#row_"+ _id);
						 	var _nCell = $(_row).find(".option-name");
						 	var _cCell = $(_row).find(".option-comment");
						 	$(_nCell)[0].innerHTML = _n;
							 $(_cCell)[0].innerHTML = _c;
							
					 	
					}else{
						alert('FAILED!');
					}
					$(top.hangge());
				}
			});
		}
	});
	
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
