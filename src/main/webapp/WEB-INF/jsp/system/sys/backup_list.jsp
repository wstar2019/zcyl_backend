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
			<li><a target="mainFrame" href="sys/listBackup">数据库备份</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<table>
				<tr>
					<td class="ipt-td">管理员</td>
					<td class="ipt-td"><input class="form-control" id="C_MAN" type="text"></td>
					<td></td>
					<td></td>
					<td class="ipt-td"><button onclick="initCtrl();" class="btn btn-primary">重置</button></td>
					<td></td>
				</tr>
				<tr>
					<td class="ipt-td">备份日期</td>
					<td class="ipt-td"><input id="CS_TIME" class=" m-ctrl-medium date-picker" type="text"/></td>
					<td class="ipt-td">---</td>
					<td class="ipt-td"><input id="CE_TIME" class="m-ctrl-medium date-picker" type="text"/></td>
					<td class="ipt-td"><button onclick="search();" class="btn btn-primary">查询</button></td>
					<td class="ipt-td"><button onclick="backup();" class="btn btn-primary">立即备份</button></td>
				</tr>
			</table>
			
			<form action="sys/listBackup" method="post" name="sysBackupForm" id="sysBackupForm">
				
				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 80px;">序号</th>
							<th class="center" style="width: 150px;">备份日期</th>
							<th class="center" style="width: 100px;">管理员</th>
							<th class="center" style="width: 100px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty backupList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${backupList}" var="backup" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr tr_id="${backup.id}">
										<td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<td class="center">${backup.create_time}</td>
										<td class="center">${backup.operator}</td>
										<td class="center">
											<div class="btn-group">
												<a class="btn btn-minier btn-white btn_m_delete"
													onclick="delBackup('${backup.id}','${backup.create_time}','${backup.operator}');">删除</a>
												<a class="btn btn-minier btn-white btn_m_edit"
													onclick="restore('${backup.id}','${backup.create_time}','${backup.operator}');">还原</a>
												
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
			<form action="" style="display: none;" method="post" name="backupEditForm" id="backupEditForm">
				<input type="text" id="OPERATOR" name="OPERATOR" value="" />
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
	$("#C_MAN").val('${pd.OPERATOR}');
	$("#CS_TIME").val('${pd.S_TIME}');
	$("#CE_TIME").val('${pd.E_TIME}');
});

function initCtrl(){
	$("#C_MAN").val("");
	$("#CS_TIME").val("");
	$("#CE_TIME").val("");
	search();
}
function search(){
	$("#OPERATOR").val($("#C_MAN").val());
	$("#S_TIME").val($("#CS_TIME").val());
	$("#E_TIME").val($("#CE_TIME").val());
	
	$("#backupEditForm").attr("action", "sys/listBackup");
	$("#backupEditForm").submit();
}

function delBackup(_id, _ct, _op){
	var options = {
			message: "<div class='item-row'><div class='item-label'>备份日期 :</div>" + 
					 "<div class='item-value'>" + _ct + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>管理员 :</div>" + 
					 "<div class='item-value'>" + _op + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除备份"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sys/deleteBackup?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
				
				top.hangge();
			});
		}
	});
}
function backup(){
	top.jzts();
	$.ajax({
		type: "POST",
		url: '<%=basePath%>sys/saveBackup',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if("success" == data.result){
				search();
			}else{
				alert('FAILED!');				
			}
			top.hangge();
		}
	});
}
function restore(_id, _ct, _op){
	var options = {
			message: "<div class='item-row'><div class='item-label'>备份日期 :</div>" + 
					 "<div class='item-value'>" + _ct + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>管理员 :</div>" + 
					 "<div class='item-value'>" + _op + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定还原吗?</div></div>",
			title: "还原数据库"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			$.ajax({
				type: "POST",
				url: '<%=basePath%>sys/restoreBackup',
		    	data: {ID: _id},
				dataType:'json',
				cache: false,
				success: function(data){
					if("success" == data.result){
						nextPage('${page.currentPage}');
					}else{
						alert('FAILED!');
					}
					top.hangge();
				}
			});
		}
	});
}
$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});
</script>
