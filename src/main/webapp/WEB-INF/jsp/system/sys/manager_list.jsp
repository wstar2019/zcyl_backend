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
.info-value{
	text-align:left;
	padding-top:7px;
	padding-left:10px;
}
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>系统管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sys/listManager">管理员管理</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<table>
				<tr>
					<td class="ipt-td">登录名</td>
					<td class="ipt-td">
						<input class="form-control" id="M_LGID" type="text"></td>
					<td class="ipt-td">姓名</td>
					<td class="ipt-td">
						<input class="form-control" id="M_NAME" type="text"></td>
					<td class="ipt-td"><button onclick="initCtrl();" class="btn btn-primary">重置</button></td>
				</tr>
				<tr>
					<td class="ipt-td">手机号</td>
					<td class="ipt-td">
						<input class="form-control" id="M_TEL" type="text"></td>
					<td class="ipt-td">状态</td>
					<td class="ipt-td">
						<select class="form-control" id="M_STATE">
							<option value="">全部</option>
							<option value="0">禁用</option>
							<option value="1">启用</option>
						</select>
					</td>
					<td class="ipt-td"><button onclick="search();" class="btn btn-primary">查询</button></td>
					<td class="ipt-td"><button onclick="add();" class="btn btn-primary">新增</button></td>
				</tr>
			</table>
			
			<form action="sys/listManager" method="post" name="sysManagerForm" id="sysManagerForm">
				
				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 80px;">序号</th>
							<th class="center" style="width: 150px;">登录名</th>
							<th class="center" style="width: 100px;">姓名</th>
							<th class="center" style="width: 100px;">手机号</th>
							<th class="center" style="width: 100px;">状态</th>
							<th class="center" style="width: 100px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty managerList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${managerList}" var="m" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr tr_id="${m.id}" ><td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<td class="center b-user-name" onclick="showManager('${m.name}','${m.lg_id}','${m.tel}','${m.email}','${m.role_name}')">${m.lg_id}</td>
										<td class="center b-req-date" >${m.name}</td>
										<td class="center b-sta-date">${m.tel}</td>
										<c:if test="${m.state == 1 }">
											<td class="center b-state">启用</td>
										</c:if>
										<c:if test="${m.state == 0 }">
											<td class="center b-state">禁用</td>
										</c:if>
										<td class="center">
											<div class="btn-group">
												<c:if test="${m.state == 1 }">
												<a class="btn btn-minier btn-white btn_m_enable"
													onclick="changeManagerState('${m.id}','${m.lg_id}','${m.name}','${m.state}');">禁用</a>
												</c:if>
												<c:if test="${m.state == 0 }">
												<a class="btn btn-minier btn-white btn_m_enable" style="color:red"
													onclick="changeManagerState('${m.id}','${m.lg_id}','${m.name}','${m.state}');">启用</a>
												</c:if>
												<a class="btn btn-minier btn-white btn_m_edit"
													onclick="updateManager('${m.id}','${m.name}','${m.lg_id}','${m.role_id}',
														'${m.tel}', '${m.state}', '${m.pass}');">编辑</a>
												<c:if test="${pd.CUR_LGID != m.lg_id }">
													<a class="btn btn-minier btn-white btn_m_delete"
														onclick="delManager('${m.id}','${m.name}','${m.lg_id}');">删除</a>
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
			<form action="" style="display: none;" method="post" name="managerEditForm" id="managerEditForm">
				<input type="text" id="NAME" name="NAME" value="" />
				<input type="text" id="LG_ID" name="LG_ID" value="" />
				<input type="text" id="TEL" name="TEL" value="" />
				<input type="text" id="STATE" name="STATE" value="" />
			</form>
		</div>
	</div>
	
	<div class="modal fade" id="managerModal" tabindex="-1" role="dialog" aria-labelledby="managerModalLabel">						
  		<div class="modal-dialog" role="document" style="width:500px;">
    		<div class="modal-content">
    			<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span>
       				</button>
    				<h4 id="managerModalTitle" class="modal-title">新增管理员</h4>
   				</div>
   				<div class="modal-body">
   					<div class="bootbox-body">
   						<form class="bootbox-form form-horizontal">
   							<input id="MD_ID" style="display:none;" type="text">
   							<div class="form-group">
   								<label class="col-sm-2 control-label">登录名</label>
   								<div class="col-sm-10 ipt-container">
   									<input id="MD_LGID" class="bootbox-input bootbox-input-text form-control" autocomplete="off" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">姓名</label>
								<div class="col-sm-10 ipt-container">
									<input id="MD_NAME" class="bootbox-input bootbox-input-text form-control" autocomplete="off" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">密码</label>
								<div class="col-sm-10 ipt-container">
									<input id="MD_PASS" class="bootbox-input bootbox-input-text form-control" autocomplete="off" type="password">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">确认密码</label>
								<div class="col-sm-10 ipt-container">
									<input id="MD_PASS1" class="bootbox-input bootbox-input-text form-control" autocomplete="off" type="password">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">手机号</label>
								<div class="col-sm-10 ipt-container">
									<input id="MD_TEL" class="bootbox-input bootbox-input-text form-control" autocomplete="off" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">角色</label>
								<div class="col-sm-10 ipt-container">
									<select id="MD_ROLE" class="bootbox-input bootbox-input-select form-control">
									</select>
								</div>
							</div>
						
							<div class="checkbox">
								<label>
									<input id="MD_STATE" class="bootbox-input bootbox-input-checkbox" type="checkbox">是否允许登入</label>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="saveConfirm();">确认</button>
					<button data-dismiss="modal" type="button" class="btn btn-default">取消</button>
				</div>
			</div>
    	</div>
  	</div>
	
	<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel">						
	  		<div class="modal-dialog" role="document" style="width:450px">
	    		<div class="modal-content" style="border-width:0px;">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        				<span aria-hidden="true">&times;</span></button>
	        			<h4 class="modal-title" id="myModalLabel" style="font-weight:bold">管理员</h4>
	      			</div>
	      			<div class="modal-body">
						<form class="form-horizontal">
							<div class="row">
								<label class="col-sm-5 control-label">登录名:</label>
								<div class="col-sm-7 info-value" id='info_lgid'></div>
							</div>
							<div class="row">
								<label class="col-sm-5 control-label">姓名:</label>
								<div class="col-sm-7 info-value" id="info_name"></div>
							</div>
							<div class="row">
								<label class="col-sm-5 control-label">手机号 :</label>
								<div class="col-sm-7 info-value" id='info_tel'></div>
							</div>
							<div class="row">
								<label class="col-sm-5 control-label">邮件地址:</label>
								<div class="col-sm-7 info-value" id="info_email"></div>
							</div>
							<div class="row">
								<label class="col-sm-5 control-label">角色 :</label>
								<div class="col-sm-7 info-value" id='info_role'></div>
							</div>
						</form>
      				</div>
	      		</div>
	      		<div class="modal-footer" style="padding: 5px 0px 10px 0px;">
	      			<button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>		        
	      		</div>
	    	</div>
	  	</div>

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

	

	<!-- basic scripts -->
	<%@ include file="../index/foot.jsp"%>
	<script src="static/ace/js/bootbox.js" type="text/javascript"></script>
	<script src="static/ace/js/ace/ace.js" type="text/javascript"></script>
	<script src="static/ace/js/date-time/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="static/js/myjs/toolPhoneNumber.js"></script>
</body>

<script>

$(top.hangge());

var roles = '${roleList}';
roles = JSON.parse(roles);
$('#MD_ROLE').append( "<option value=''>请选择</option>" );
for(var i = 0; i < roles.length; i++){
	$('#MD_ROLE').append( "<option value='" + roles[i].value + "'>" + roles[i].text + "</option>" );
}

function initCtrl(){
	$("#M_NAME").val("");
	$("#M_LGID").val("");
	$("#M_STATE").val("");
	$("#M_TEL").val("");
	search();
}
function search(){
	$("#NAME").val($("#M_NAME").val());
	$("#LG_ID").val($("#M_LGID").val());
	$("#STATE").val($("#M_STATE").val());
	$("#TEL").val($("#M_TEL").val());
	
	$("#managerEditForm").attr("action", "sys/listManager");
	$("#managerEditForm").submit();
}
function showManager(_n, _l, _t, _e, _r){
	$('#info_lgid').html(_l);
	$('#info_name').html(_n);
	$('#info_tel').html(_t);
	$('#info_email').html(_e);
	$('#info_role').html(_r);
	$('#infoModal').modal('show')
}
function delManager(_id, _name, _lg){
	var options = {
			message: "<div class='item-row'><div class='item-label'>登录名 :</div>" + 
					 "<div class='item-value'>" + _lg + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>姓名 :</div>" + 
					 "<div class='item-value'>" + _name + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除管理员"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sys/deleteManager?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
			});
		}
	});
}
function updateManager(_id, _name, _lg, _role, _tel, _state, _pass){
	$('#managerModalTitle').text("编辑管理员");
	$('#MD_ID').val(_id);
	$("#MD_NAME").val(_name);
	$("#MD_LGID").val(_lg);
	$("#MD_ROLE").val(_role);
	_state == '1' ? $("#MD_STATE").attr("checked", true) : $("#MD_STATE").attr("checked", false);
	$("#MD_TEL").val(_tel);
	$("#MD_PASS").val("ryncph");
	$("#MD_PASS1").val("ryncph");
	$('#managerModal').modal("show");
}
function add(){
	$('#managerModalTitle').text("新增管理员");
	$('#MD_ID').val("");
	$("#MD_NAME").val("");
	$("#MD_LGID").val("");
	$("#MD_ROLE").val("");
	$("#MD_STATE").attr("checked", true);
	$("#MD_TEL").val("");
	$("#MD_PASS").val("");
	$("#MD_PASS1").val("");
	$('#managerModal').modal("show");
}
function saveConfirm(){
	var _id = $("#MD_ID").val();
	var _l = $("#MD_LGID").val();
	var _n = $("#MD_NAME").val();
	var _t = $("#MD_TEL").val();	
	var _r = $("#MD_ROLE").val();
	var _s = $("#MD_STATE").prop('checked') == true ? 1 : 0;;
	var _p = $("#MD_PASS").val();
	var _pp = $("#MD_PASS1").val();
	
	if (_l == '') {
		$("#MD_LGID").tips({
			side : 3,
			msg : '请输入登录名',
			bg : '#AE81FF',
			time : 2
		});
		$("#MD_LGID").focus();
		return;
	}
	
	if (_n == '') {
		$("#MD_NAME").tips({
			side : 3,
			msg : '请输入姓名',
			bg : '#AE81FF',
			time : 2
		});
		$("#MD_NAME").focus();
		return;
	}
	if(!formatPhoneNumber(_t)){
		$("#MD_TEL").tips({
			side : 3,
			msg : '请输入有效的联系人电话',
			bg : '#AE81FF',
			time : 2
		});
        $("#MD_TEL").focus();
        return false;
    }
	if (_r == '') {
		$("#MD_ROLE").tips({
			side : 3,
			msg : '请输入角色',
			bg : '#AE81FF',
			time : 2
		});
		$("#MD_ROLE").focus();
		return;
	}
	if (_p == '') {
		$("#MD_PASS").tips({
			side : 3,
			msg : '请输入密码',
			bg : '#AE81FF',
			time : 2
		});
		$("#MD_PASS").focus();
		return;
	}
	if (_p != _pp) {
		$("#MD_PASS1").tips({
			side : 3,
			msg : '密码不一致',
			bg : '#AE81FF',
			time : 2
		});
		$("#MD_PASS1").focus();
		return;
	}
	
	top.jzts();
	$.ajax({
		type: "POST",
		url: '<%=basePath%>sys/hasManager',
    	data: {LG_ID: _l, ID: _id },
		dataType:'json',
		cache: false,
		success: function(data){
			if("yes" == data.result){
				alert("该用户名已经存在!");
				$(top.hangge());
			}else{
				var _url = "";
				var _data;
				if(_id == ""){
					_url = '<%=basePath%>sys/saveManager';
					_data = {NAME: _n, LG_ID: _l, STATE: _s, TEL: _t, ROLE_ID: _r, PASS: _p };
				}else{
					_url = '<%=basePath%>sys/updateManager';
					if(_p != 'ryncph'){
						_data = {ID: _id, NAME: _n, LG_ID: _l, STATE: _s, TEL: _t, ROLE_ID: _r, PASS: _p };	
					}else{
						_data = {ID: _id, NAME: _n, LG_ID: _l, STATE: _s, TEL: _t, ROLE_ID: _r}
					}
					
				}
				
				$.ajax({
					type: "POST",
					url: _url,
			    	data: _data,
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
							search();
						}else{
							alert('FAILED!');
							$(top.hangge());
						}
					}
				});	
			}
		}
	});
	
	
}

function changeManagerState(_id, _lg, _name, _state){
	var _s = "";
	if(_state == 0)
		_s = '禁用';
	else
		_s = '启用';
	
	var options = {
			message: "<div class='item-row'><div class='item-label'>登录名 :</div>" + 
					 "<div class='item-value'>" + _lg + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>姓名 :</div>" + 
					 "<div class='item-value'>" + _name + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>当前状态 :</div>" + 
					 "<div class='item-value'>" + _s + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定切换吗?</div></div>",
			title: "管理员状态"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			if(_state == "0" ){
				_state = 1
			}
			else{
				_state = 0;
			}
			$.ajax({
				type: "POST",
				url: '<%=basePath%>sys/changeManagerState',
		    	data: {ID:_id, STATE:_state},
				dataType:'json',
				cache: false,
				success: function(data){
					if("success" == data.result){
						if(_lg == '${pd.CUR_LGID}'){
							$("#managerEditForm").attr("action", "<%=basePath%>logout");
							$("#managerEditForm").submit();
						} else {
							nextPage('${page.currentPage}');
						}
					}else{
						alert('FAILED!');
						$(top.hangge());
					}
				}
			});
		}
	});
}
$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

jQuery(function() {
	$("#M_NAME").val('${pd.NAME}');
	$("#M_LGID").val('${pd.LG_ID}');
	$("#M_STATE").val('${pd.STATE}');
	$("#M_TEL").val('${pd.TEL}');
});
</script>
