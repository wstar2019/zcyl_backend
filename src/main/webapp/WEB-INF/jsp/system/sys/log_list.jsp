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
			<li><a>系统管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sys/listOperation">操作日志</a></li>
		</ul>
	</div>

				<div class="page-content" style="padding: 10px;">
					<div class="container-fluid">
						<table>
							<tr>
								<td class="ipt-td">操作类型</td>
								<td class="ipt-td">
									<select class="form-control" id="C_KIND">
										<option value="">全部</option>
										<option value="新增">新增</option>
										<option value="编辑">编辑</option>
										<option value="删除">删除</option>
										<option value="备份">备份</option>
										<option value="还原">还原</option>
									</select>
								</td>
								<td class="ipt-td">管理员</td>
								<td class="ipt-td"><input class="form-control" id="C_MAN" type="text"></td>
								<td class="ipt-td"><button onclick="initCtrl();" class="btn btn-primary">重置</button></td>
							</tr>
							<tr>
								<td class="ipt-td">操作日期</td>
								<td class="ipt-td"><input id="OS_TIME" class=" m-ctrl-medium date-picker" type="text"/></td>
								<td class="ipt-td">---</td>
								<td class="ipt-td"><input id="OE_TIME" class="m-ctrl-medium date-picker" type="text"/></td>
								<td class="ipt-td"><button onclick="search();" class="btn btn-primary">查询</button></td>
							</tr>
						</table>
						
						<form action="sys/listOperation" method="post" name="sysLogForm" id="sysLogForm">
							
							<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
								<thead>
									<tr>
										<th class="center" style="width: 50px;">序号</th>
										<th class="center" style="width: 150px;">操作日期</th>
										<th class="center" style="width: 80px;">操作人</th>
										<th class="center" style="width: 150px;">对象</th>
										<th class="center" style="width: 50px;">类型</th>
										<th class="center" style="width: 400px;">内容</th>
										<th class="center" style="width: 50px;">操作</th>
									</tr>
								</thead>
								<tbody>
									
									<c:choose>										
										<c:when test="${not empty logList}">
											<%
									            int numStds = 0;								
									            pageContext.setAttribute("numStds", numStds);
									        %>
											<c:forEach items="${logList}" var="log" varStatus="vs">
												<%
										            numStds++;								
										            pageContext.setAttribute("numStds", numStds);
										        %>
												<tr tr_id="${log.id}">
													<td class="center" onclick="viewLog('${log.id}','${log.op_dt}','${log.op_man}','${log.op_menu}','${log.op_kind}','${log.op_content}');">${(page.currentPage -1) * page.showCount + numStds}</td>
													<td class="center" onclick="viewLog('${log.id}','${log.op_dt}','${log.op_man}','${log.op_menu}','${log.op_kind}','${log.op_content}');">${log.op_dt}</td>
													<td class="center" onclick="viewLog('${log.id}','${log.op_dt}','${log.op_man}','${log.op_menu}','${log.op_kind}','${log.op_content}');">${log.op_man}</td>
													<td class="center" onclick="viewLog('${log.id}','${log.op_dt}','${log.op_man}','${log.op_menu}','${log.op_kind}','${log.op_content}');">${log.op_menu}</td>
													<td class="center" onclick="viewLog('${log.id}','${log.op_dt}','${log.op_man}','${log.op_menu}','${log.op_kind}','${log.op_content}');">${log.op_kind}</td>
													<td class="center" onclick="viewLog('${log.id}','${log.op_dt}','${log.op_man}','${log.op_menu}','${log.op_kind}','${log.op_content}');">${log.op_content}</td>
													<td class="center">
														<div class="btn-group">
															<a class="btn btn-minier btn-white btn_m_delete"
																onclick="delLog('${log.id}','${log.op_dt}','${log.op_man}','${log.op_menu}','${log.op_kind}');">删除</a>
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
						<form action="" style="display: none;" method="post" name="logEditForm" id="logEditForm">
							<input type="text" id="KIND" name="KIND" value="" />
							<input type="text" id="MAN" name="MAN" value="" />
							<input type="text" id="S_TIME" name="S_TIME" value="" />
							<input type="text" id="E_TIME" name="E_TIME" value="" />
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
	$("#C_KIND").val('${pd.KIND}');
	$("#C_MAN").val('${pd.MAN}');
	$("#OS_TIME").val('${pd.S_TIME}');
	$("#OE_TIME").val('${pd.E_TIME}');
});

function initCtrl(){
	$("#C_KIND").val("");
	$("#C_MAN").val("");
	$("#OS_TIME").val("");
	$("#OE_TIME").val("");
	search();
}
function search(){
	$("#KIND").val($("#C_KIND").val());
	$("#MAN").val($("#C_MAN").val());
	$("#S_TIME").val($("#OS_TIME").val());
	$("#E_TIME").val($("#OE_TIME").val());
	
	$("#logEditForm").attr("action", "sys/listOperation");
	$("#logEditForm").submit();
}

function delLog(_id, _dt, _man, _menu, _kind){
	var options = {
			message: "<div class='row'><div class='col-sm-5 item-label'>操作日期:</div>" + 
					 "<div class='col-sm-7 item-value'>" + _dt + "</div></div>" +
					 "<div class='row'><div class='col-sm-5 item-label'>操作人:</div>" + 
					 "<div class='col-sm-7 item-value'>" + _man + "</div></div>" +
					 "<div class='row'><div class='col-sm-5 item-label'>对象:</div>" + 
					 "<div class='col-sm-7 item-value'>" + _menu + "</div></div>" +
					 "<div class='row'><div class='col-sm-5 item-label'>类型:</div>" + 
					 "<div class='col-sm-7 item-value'>" + _kind + "</div></div>" +
					 "<div class='row'><h3>您确定删除吗?</h3></div>",
			title: "删除操作"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sys/deleteLog?ID=" + _id;
			$.get(url, function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
			});
		}
	});
}

function viewLog(_id, _dt, _man, _menu, _kind, _content){
	var options = {
			message: "<p class='row'><span class='item-label col-md-2' style='padding:0px;'>操作日期:</span>" + 
					 "<span class='item-value col-md-10' style='padding:0px 10px;text-align:left;'>" + _dt + "</span></p>" +
					 "<p class='row'><span class='item-label col-md-2' style='padding:0px;'>操作人:</span>" + 
					 "<span class='item-value col-md-10' style='padding:0px 10px;text-align:left;'>" + _man + "</span></p>" +
					 "<p class='row'><span class='item-label col-md-2' style='padding:0px;'>对象:</span>" + 
					 "<span class='item-value col-md-10' style='padding:0px 10px;text-align:left;'>" + _menu + "</span></p>" +
					 "<p class='row'><span class='item-label col-md-2' style='padding:0px;'>类型:</span>" + 
					 "<span class='item-value col-md-10' style='padding:0px 10px;text-align:left;'>" + _kind + "</span></p>" +
					 "<p class='row'><span class='item-label col-md-2' style='padding:0px;'>内容 :</span>" + 
					 "<span class='item-value col-md-10' style='padding:0px 10px;text-align:left;'>" + _content + "</span></p>",
			title: "查看操作日志"
	}
	bootbox.confirm(options, function(result) {});
}
$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

</script>
