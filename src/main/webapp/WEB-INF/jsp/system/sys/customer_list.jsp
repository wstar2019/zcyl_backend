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
			<li><a>系统管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sys/listCustomer">用户管理</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<table style="margin-bottom:10px;">
				<tbody>
					<tr>
						<td class="ipt-td">用户名</td>
						<td class="ipt-td"><input class="form-control" id="C_LGID" type="text"></td>
						<td></td>
						<td></td>
						<td class="ipt-td"><button onclick="initCtrl();" class="btn btn-primary">重置</button></td>
					</tr>
					<tr>
						<td class="ipt-td">手机号</td>
						<td class="ipt-td"><input class="form-control" id="C_TEL" type="text"></td>
						<td class="ipt-td">姓名</td>
						<td class="ipt-td"><input class="form-control" id="C_NAME" type="text"></td>
						<td class="ipt-td"><button onclick="search();" class="btn btn-primary">查询</button></td>
					</tr>
				</tbody>
			</table>
			
			
			<form action="sys/listCustomer" method="post" name="sysCustomerForm" id="sysCustomerForm">
				
				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 80px;">序号</th>
							<th class="center" style="width: 150px;">用户名</th>
							<th class="center" style="width: 100px;">姓名</th>
							<th class="center" style="width: 100px;">手机号</th>
							<th class="center" style="width: 100px;">邮箱</th>
							<th class="center" style="width: 100px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty customerList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${customerList}" var="customer" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr tr_id="${customer.id}"><td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<td class="center">${customer.lg_id}</td>
										<td class="center">${customer.name}</td>
										<td class="center">${customer.tel}</td>
										<td class="center">${customer.email}</td>
										<td class="center">
											<div class="btn-group">
												<a class="btn btn-minier btn-white btn_m_edit"
													onclick="viewCustomer('${customer.id}','${customer.name}','${customer.lg_id}','${customer.tel}','${customer.email}', '${customer.address}');">查看</a>
												<a class="btn btn-minier btn-white btn_m_delete"
													onclick="delCustomer('${customer.id}','${customer.name}','${customer.lg_id}');">删除</a>
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
			<form action="" style="display: none;" method="post" name="customerEditForm" id="customerEditForm">
				<input type="text" id="NAME" name="NAME" value="" />
				<input type="text" id="LG_ID" name="LG_ID" value="" />
				<input type="text" id="TEL" name="TEL" value="" />
			</form>
		</div>
	</div>
	<!-- Modal Start-->
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel">						
  		<div class="modal-dialog" role="document" style="width:500px">
    		<div class="modal-content">
    			<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span>
       				</button>
    				<h4 id="viewTitle" class="modal-title">查看用户</h4>
   				</div>
   				<div class="modal-body">
   					<div class="bootbox-body">
   						<form class="bootbox-form form-horizontal">
   							<input id="MD_ID" style="display:none;" type="text">
   							<div class="form-group">
   								<label class="col-sm-2 control-label">用户名</label>
   								<div class="col-sm-10 ipt-container">
   									<label id="MD_LGID"></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">姓名</label>
								<div class="col-sm-10 ipt-container">
									<label id="MD_NAME"></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">手机号</label>
								<div class="col-sm-10 ipt-container">
									<label id="MD_TEL"></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">地址</label>
								<div class="col-sm-10 ipt-container">
									<label id="MD_ADDRESS"></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-10 ipt-container">
									<label id="MD_EMAIL"></label>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
				</div>
			</div>
    	</div>
  	</div>
	<!-- Modal End -->

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
	$("#C_NAME").val("");
	$("#C_LGID").val("");
	$("#C_TEL").val("");
	search();
}
function search(){
	$("#NAME").val($("#C_NAME").val());
	$("#LG_ID").val($("#C_LGID").val());
	$("#TEL").val($("#C_TEL").val());
	
	$("#customerEditForm").attr("action", "sys/listCustomer");
	$("#customerEditForm").submit();
}
function delCustomer(_id, _name, _lg){
	var options = {
			message: "<div class='item-row'><div class='item-label'>登录名 :</div>" + 
					 "<div class='item-value'>" + _lg + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>姓名 :</div>" + 
					 "<div class='item-value'>" + _name + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除用户"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sys/deleteCustomer?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
			});
		}
	});
}
function viewCustomer(_id, _name, _lg, _tel, _email, _address){
	
	$('#MD_LGID').text(_lg);
	$('#MD_NAME').text(_name);
	$('#MD_TEL').text(_tel);
	$('#MD_EMAIL').text(_email);
	$('#MD_ADDRESS').text(_address);
	$('#viewModal').modal("show");
	
}
$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

jQuery(function() {
	$("#C_NAME").val('${pd.NAME}');
	$("#C_LGID").val('${pd.LG_ID}');
	$("#C_TEL").val('${pd.TEL}');
});
</script>
