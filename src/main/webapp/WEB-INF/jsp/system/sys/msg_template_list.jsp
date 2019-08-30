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
			<li><a>消息管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sys/listMsgTemplate">通知模板管理</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<table>
				<tr>
					<td class="ipt-td">类型</td>
					<td class="ipt-td">
						<select class="form-control" id="T_KIND">
							<option value="0">全部</option>
							<option value="1">短信</option>
							<option value="2">邮件</option>
						</select>
					</td>
					<td class="ipt-td">模板名称</td>
					<td class="ipt-td">
						<input class="form-control" id="T_NAME">
					</td>
					<td class="ipt-td">
						<button type="button" class="btn btn-primary" onclick="initCtrl()">重置</button>
					</td>
				</tr>
				<tr>
					<td class="ipt-td">更新日期</td>
					<td class="ipt-td">
						<input id="TS_TIME" class="m-ctrl-medium date-picker" type="text"/></td>
					<td class="ipt-td">---</td>
					<td class="ipt-td">
						<input id="TE_TIME" class="m-ctrl-medium date-picker" type="text"/>
					</td>
					<td class="ipt-td">
						<button type="button" class="btn btn-primary" onclick="search()">查询</button>
					</td>
					<td class="ipt-td">
						<button type="button" class="btn btn-primary" onclick="addTemplate()">新增模板</button>
					</td>
				</tr>
			</table>
			<form action="sys/listMsgTemplate" method="post" name="sysMsgTempForm" id="sysMsgTempForm">
				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 80px;">序号</th>
							<th class="center" style="width: 150px;">类型</th>
							<th class="center" style="width: 150px;">模板名称</th>
							<th class="center" style="width: 150px;">更新日期</th>
							<th class="center" style="width: 100px;">管理员</th>
							<th class="center" style="width: 50px;">状态</th>
							<th class="center" style="width: 150px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty tempList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${tempList}" var="t" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr tr_id="${t.ID}">
										<td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<c:if test="${t.KIND == '0' }">
											<td class="center">短信&邮件</td>
										</c:if>
										<c:if test="${t.KIND == 1 }">
											<td class="center">短信</td>
										</c:if>
										<c:if test="${t.KIND == 2 }">
											<td class="center">邮件</td>
										</c:if>
										<td class="center">${t.NAME}</td>
										<td class="center">${t.REG_TIME}</td>
										<td class="center">${t.REG_MAN}</td>
										<td class="center">${t.STATE == 1 ? '启用' : '禁用'}</td>
										<td class="center">
											<div class="btn-group">
												<a class="btn btn-minier btn-white btn_m_state"
													onclick="changeState('${t.ID}', '${t.KIND}', '${t.NAME}', '${t.STATE}');">
													${t.STATE == 1 ? '禁用' : '启用'}
												</a>
												<a class="btn btn-minier btn-white btn_m_view"
													onclick="viewTemplate('${t.ID}');">查看</a>
												<a class="btn btn-minier btn-white btn_m_edit"
													target="mainFrame" href="sys/editMsgTemplate/?ID=${t.ID}">编辑</a>
												<a class="btn btn-minier btn-white btn_m_delete"
													onclick="deleteTemplate('${t.ID}', '${t.KIND}', '${t.NAME}', '${t.STATE}');">删除</a>
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
			<form action="" style="display: none;" method="post" name="tempEditForm" id="tempEditForm">
				<input type="text" id="KIND" name="KIND" value="" />
				<input type="text" id="NAME" name="NAME" value="" />
				<input type="text" id="S_TIME" name="S_TIME" value="" />
				<input type="text" id="E_TIME" name="E_TIME" value="" />
			</form>
		</div>
	</div>
	
	<div class="modal fade" id="tempModal" tabindex="-1" role="dialog" aria-labelledby="tempModalLabel">						
  		<div class="modal-dialog" role="document" style="width:800px">
    		<div class="modal-content" style="border-width:0px;">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel" style="font-weight:bold">查看通知模板</h4>
      			</div>
      			<div class="modal-body">
      				<div class="row">
      					<div class="col-sm-6">
      						<div class="row" style="margin-bottom: 5px;">
		   						<div class="col-sm-3" style="text-align:right;">模板名称:</div>
		   						<div class="col-sm-9" id="temp_name" style="text-align:left;"></div>
		   					</div>
		   					<div class="row" style="margin-bottom: 5px;">
		   						<div class="col-sm-3" style="text-align:right;">类型:</div>
		   						<div class="col-sm-9" id="temp_kind" style="text-align:left;"></div>
		   					</div>
		   					<div class="row" style="margin-bottom: 5px;">
		   						<div class="col-sm-3" style="text-align:right;">更新日期:</div>
		   						<div class="col-sm-9" id="temp_date" style="text-align:left;"></div>
		   					</div>
      					</div>
      					<div class="col-sm-6">
      						<div class="row" style="margin-bottom: 5px;">
		   					</div>
		   					<div class="row" style="margin-bottom: 5px;">
		   						<div class="col-sm-3" style="text-align:right;">管理员:</div>
		   						<div class="col-sm-9" id="temp_man" style="text-align:left;"></div>
		   					</div>
		   					<div class="row" style="margin-bottom: 5px;">
		   						<div class="col-sm-3" style="text-align:right;">状态:</div>
		   						<div class="col-sm-9" id="temp_state" style="text-align:left;"></div>
		   					</div>
      					</div>
      				</div>
      				<hr>
      				
      				<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-12" style="text-align:left;">短信内容:</div>
   						<div class="col-sm-12">
   							<textarea id="temp_content" rows="" cols="" class="form-control"></textarea>
   						</div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-12" style="text-align:left;">邮件内容:</div>
   						<div class="col-sm-12">
   							<div id="temp_temp" style="max-height:200px;overflow:auto;border:solid 1px #D2D2D2;"></div>
   						</div>
   					</div>
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

	<div></div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<%@ include file="../index/foot.jsp"%>
	<script src="static/ace/js/bootbox.js" type="text/javascript"></script>
	<script src="static/ace/js/ace/ace.js" type="text/javascript"></script>
	<script src="static/ace/js/date-time/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>

</body>

<script>

$(top.hangge());

function editTemplate(_id){
	
}

function viewTemplate(_id){
	$.ajax({
		type: "POST",
		url: '<%=basePath%>sys/getMsgTemplateInfo',
    	data: {ID:_id},
		dataType:'json',
		cache: false,
		success: function(res){
			if("success" == res.result){
				var temp = res.data;
				var _k = getKindString(temp.KIND);
				var _s = "";
				temp.STATE == "0" ? _s = "禁用" : _s = "启用";
				
				$('#temp_name').html(temp.NAME);
				$('#temp_kind').html(_k);
				$('#temp_date').html(temp.REG_TIME);
				$('#temp_man').html(temp.REG_MAN);
				$('#temp_state').html(_s);
				$('#temp_content').val(temp.CONTENT);
				$('#temp_temp').html(temp.TEMP);
				
				$('#tempModal').modal("show");
			}else{
				alert('FAILED!');
			}
		}
	});
}
function getKindString(_k){
	var _kk = "";
	if(_k == "0"){
		_kk = "短信&邮件";
	}else if(_k == 1){
		_kk = "短信";
	}else if(_k == 2){
		_kk = "邮件";
	}
	return _kk;
}
function deleteTemplate(_id, _k, _n, _s){
	var _kk = getKindString(_k);
	var _ss = "";
	
	_s == "0" ? _ss = "禁用" : _ss = "启用";
	
	var options = {
			message: "<div class='item-row'><div class='item-label'>模板类型:</div>" + 
					 "<div class='item-value'>" + _kk + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>模板名称 :</div>" + 
					 "<div class='item-value'>" + _n + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>状态 :</div>" + 
					 "<div class='item-value'>" + _ss + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除通知模板"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sys/deleteMsgTemplate?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
			});
		}
	});
}
function changeState(_id, _k, _n, _s){
	var _kk = getKindString(_k);
	var _ss = "";
	
	_s == "0" ? _ss = "禁用" : _ss = "启用";
	
	var options = {
			message: "<div class='item-row'><div class='item-label'>模板类型 :</div>" + 
					 "<div class='item-value'>" + _kk + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>模板名称 :</div>" + 
					 "<div class='item-value'>" + _n + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>当前状态 :</div>" + 
					 "<div class='item-value'>" + _ss + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定禁用吗?</div></div>",
			title: "通知模板状态"
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
				url: '<%=basePath%>sys/changeMsgTemplateState',
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
function addTemplate(){
	$("#KIND").val('');
	$("#NAME").val('');
	$("#S_TIME").val('');
	$("#E_TIME").val('');
	
	$("#tempEditForm").attr("action", "sys/editMsgTemplate/?" + $("#tempEditForm").serialize());
	$("#tempEditForm").submit();
}

function search(){
	$("#KIND").val($("#T_KIND").val());
	$("#NAME").val($("#T_NAME").val());
	$("#S_TIME").val($("#TS_TIME").val());
	$("#E_TIME").val($("#TE_TIME").val());
	
	$("#tempEditForm").attr("action", "sys/listMsgTemplate");
	$("#tempEditForm").submit();
}
function initCtrl(){
	$("#T_KIND").val('');
	$("#T_NAME").val('');
	$("#TS_TIME").val('');
	$("#TE_TIME").val('');
	search();
}
jQuery(function() {
	$("#T_KIND").val('${pd.KIND}');
	$("#T_NAME").val('${pd.NAME}');
	$("#TS_TIME").val('${pd.S_TIME}');
	$("#TE_TIME").val('${pd.E_TIME}');
});

$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});
</script>
