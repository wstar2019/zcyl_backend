<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String a = basePath;
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<link rel="stylesheet" href="static/ace/css/chosen.css" />

<link rel="stylesheet" href="static/ace/css/datepicker.css" />

<link rel="stylesheet" type="text/css" href="static/global/plugins/jstree/dist/themes/default/style.min.css">
<link rel="stylesheet" type="text/css" href="static/global/css/components.css">
<link rel="stylesheet" type="text/css" href="static/global/css/plugins.css">

<%@ include file="../index/top.jsp"%>
</head>
<body class="no-skin">
	<div class="page-header">
		<ul class="navi">
			<li><a>系统管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sys/listRole">角色管理</a></li>
		</ul>
	</div>

	<div class="page-content">
		<div class="container-fluid">
			<table style="margin-top:5px;">							
				<tr>																	
					<td>
						<button class="btn btn-primary" onclick="addRole();">新增</button>
					</td>
				</tr>
			</table>
			<form action="sys/listRole" method="post" name="RolesListForm" id="RolesListForm">
				
								
				<table id="simple-table" class="table table-striped table-bordered table-hover"  style="margin-top:5px;">
					<thead>
						<tr>
							<th class="center" style="width:40px;">序号</th>
							<th class="center" style="width:80px;">角色名称</th>				
							<th class="center" style="width:80px;">英文名称</th>					
							<th class="center" style="width:200px;">备注</th>			
							<th class="center" style="width:60px;">更新日期</th>									
							<th class="center" style="width:150px;">操作</th>
						</tr>
					</thead>
											
					<tbody>
													
					<c:choose>
						<c:when test="${not empty RolesList}">
							<%
					            int numStds = 0;								
					            pageContext.setAttribute("numStds", numStds);
					        %>
							<c:forEach items="${RolesList}" var="role" varStatus="vs">
								<%
						            numStds++;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<tr tr_id='${role.id }'>
									<td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>											
									<td class="center">${role.role_name }</td>
									<td class="center">${role.role_eng_name}</td>
									<td class="center">${role.role_comment}</td>
									<td class="center">${role.role_dt}</td>
																					
									<td class="center">
										<div class="btn-group">
											<a class="btn btn-minier btn-white btn_m_add_menus"
												style="color:#2283c5 !important;"
												onclick="addMenu('${role.id}', '${role.role_menu_ids}');">栏目权限</a>
											<a class="btn btn-minier btn-white btn_m_add_permission"
												style="color:#2283c5 !important;"
												onclick="addPermission('${role.id}', '${role.role_menu_permission}');">功能权限</a>
											<a class="btn btn-minier btn-white btn_m_edit"
												style="color:#2283c5 !important;"
												onclick="updateRole('${role.id }','${role.role_name }','${role.role_eng_name }','${role.role_comment }');">编辑</a>
											<a class="btn btn-minier btn-white btn_m_delete"
												onclick="deleteRole('${role.id }','${role.role_name }');"
												style="color:#2283c5 !important;">删除</a>	
										</div>
									</td>
								</tr>
							
							</c:forEach>
							<c:if test="${QX.cha == 0 }">
								<tr>
									<td colspan="7" class="center">您无权查看</td>
								</tr>
							</c:if>
						</c:when>
						<c:otherwise>
							<tr class="main_info">
								<td colspan="7" class="center">没有相关数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
					</tbody>
				</table>
			
				<div class="position-relative">
					<table style="width:100%;">
						<tr>							
							<td style="vertical-align:top;">
								<div class="pagination" style="float: left;padding-top: 0px;margin-top: 0px;">${page.pageStr}
								</div>
							</td>
						</tr>						
					</table>
				</div>
			</form>
			<input type="text" value="" id="ROLE_ID" style="display:none;">
			<div class="modal fade" id="menuModal" tabindex="-1" role="dialog" aria-labelledby="menuModalLabel">						
		  		<div class="modal-dialog" role="document" style="width:500px">
		    		<div class="modal-content" style="border-width:0px;">
		      			<div class="modal-header">
		        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		        				<span aria-hidden="true">&times;</span></button>
		        			<h4 class="modal-title" id="myModalLabel" style="font-weight:bold">栏目权限</h4>
		      			</div>
		      			<div class="modal-body" style="text-align:left;">					      				
							<div id="menu_tree" class="tree-demo" style="height: 200px; overflow: auto;"></div>
						</div>
		      		</div>
		      		<div class="modal-footer" style="padding: 5px 0px 10px 0px;">
		      			<button type="button" class="btn btn-primary btn_user_modal_confirm" onclick="updateMenuForRole();">确定</button>
	        			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					        
		      		</div>
		    	</div>
		  	</div>
		  	<div class="modal fade" id="permissionModal" tabindex="-1" role="dialog" aria-labelledby="permissionModalLabel">						
		  		<div class="modal-dialog" role="document" style="width:500px">
		    		<div class="modal-content" style="border-width:0px;">
		      			<div class="modal-header">
		        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		        				<span aria-hidden="true">&times;</span></button>
		        			<h4 class="modal-title" style="font-weight:bold">功能权限</h4>
		      			</div>
		      			<div class="modal-body" style="text-align:left;">
		      				<div id="permission_tree" class="tree-demo" style="height: 200px; overflow: auto;"></div>
						</div>
		      		</div>
		      		<div class="modal-footer" style="padding: 5px 0px 10px 0px;">
		      			<button type="button" class="btn btn-primary btn_user_modal_confirm" onclick="updatePermissionForRole();">确定</button>
	        			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					        
		      		</div>
		    	</div>
		  	</div>
		</div>
		
	</div>

	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
	<%@ include file="../index/foot.jsp"%>
	
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>		
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>		
	<script src="static/ace/js/chosen.jquery.js"></script>		
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>  		
	<script src="static/global/plugins/jstree/dist/jstree.min.js"></script>
	<script src="static/global/scripts/ui-tree.js"></script>
</body>

<script type="text/javascript">
$(top.hangge());
var menu_tree, permission_tree;

function updatePermissionForRole(){
	var _id = $('#ROLE_ID').val();
	var temp_data_tree = permission_tree.jstree()._model.data;
	var ids = "";
	for(id in temp_data_tree){
		if(temp_data_tree[id]['state']['selected']==true){
			ids = ids + id + "^";
		}
	}	
	$('#permissionModal').modal("hide");
	top.jzts();
	$.ajax({
		type: "POST",
		url: '<%=basePath%>sys/updateRole',
    	data: {ROLE_MENU_PERMISSION: ids, ID: _id },
		dataType:'json',
		cache: false,
		success: function(data){
			if("success" == data.result){
				nextPage('${page.currentPage}');
			}else{
				alert('FAILED!');
				$(top.hangge());
			}
		}
	});
}
function addPermission(_id, _permission){
	$('#ROLE_ID').val(_id);
	$('#permissionModal').modal("show");
	
	var ids = _permission.split("^");
	var objTree = permission_tree.jstree()._model.data;
	for(id in objTree){
		objTree[id]['state']['selected'] = false;
		for(var i = 0; i < ids.length; i++){
			if(ids[i] == id)
				objTree[id]['state']['selected'] = true;
		}
	}
	permission_tree.jstree()._model.data = objTree;
	permission_tree.jstree().refresh();
	
}
function updateMenuForRole(){
	var _id = $('#ROLE_ID').val();
	var temp_data_tree = menu_tree.jstree()._model.data;
	var tmp_str = "^";
	for(id in temp_data_tree){
		if(temp_data_tree[id]['state']['selected']==true){
			tmp_str += id + "-" + temp_data_tree[id]['parent'] + "^";
		}
	}	
	$('#menuModal').modal("hide");
	top.jzts();
	$.ajax({
		type: "POST",
		url: '<%=basePath%>sys/updateRole',
    	data: {ROLE_MENU_IDS: tmp_str, ID: _id },
		dataType:'json',
		cache: false,
		success: function(data){
			if("success" == data.result){
				nextPage('${page.currentPage}');
			}else{
				alert('FAILED!');
				$(top.hangge());
			}
		}
	});
}
function addMenu(_id, _ids){
	$('#ROLE_ID').val(_id);
	$('#menuModal').modal("show");
	
	var ids = _ids.split("^");
	var objTree = menu_tree.jstree()._model.data;
	for(id in objTree){
		objTree[id]['state']['selected'] = false;
		for(var i = 0; i < ids.length; i++){
			var _ip = ids[i].split("-");
			if(_ip[0] == id)
				objTree[id]['state']['selected'] = true;
		}
	}
	menu_tree.jstree()._model.data = objTree;
	menu_tree.jstree().refresh();
	
}
function updateRole(_id, _n, _en, _c){
	var options = {
			title: "编辑角色",
			inputs:[
				{"type": "text", "label": "角色", "value": _n, "idx": "NAME", "required": true },
				{"type": "text", "label": "英文名称", "value": _en, "idx": "ENG_NAME", "required": true },
				{"type": "textarea", "label": "备注", "value": _c, "idx": "COMMENT", }
			]
		};
		bootbox.prompt(options, function(result) {
			if(result) {
				var _un, _uen, _uc;
				for(var i = 0; i < result.length; i++){
					if(result[i].idx == "NAME"){
						_un = result[i].val;
					}
					if(result[i].idx == "ENG_NAME"){
						_uen = result[i].val;
					}
					if(result[i].idx == "COMMENT"){
						_uc = result[i].val;
					}
				}
				
				if(_n == "" || null == _n){
					alert("请输入角色");
				}
				else if(_en == "" || null == _en){
					alert("请输入英文名称");
				}
				else{
					top.jzts();
					$.ajax({
						type: "POST",
						url: '<%=basePath%>sys/updateRole',
				    	data: {ID: _id, ROLE_NAME: _un, ROLE_ENG_NAME: _uen, ROLE_COMMENT: _uc },
						dataType:'json',
						cache: false,
						success: function(data){
							if("success" == data.result){
								nextPage('${page.currentPage}');
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
function addRole(){
	var options = {
			title: "新增角色",
			inputs:[
				{"type": "text", "label": "角色", "value": "", "idx": "NAME", "required": true },
				{"type": "text", "label": "英文名称", "value": "", "idx": "ENG_NAME", "required": true },
				{"type": "textarea", "label": "备注", "value": "", "idx": "COMMENT", }
			]
		};
		bootbox.prompt(options, function(result) {
			if(result) {
				var _n, _en, _c;
				for(var i = 0; i < result.length; i++){
					if(result[i].idx == "NAME"){
						_n = result[i].val;
					}
					if(result[i].idx == "ENG_NAME"){
						_en = result[i].val;
					}
					if(result[i].idx == "COMMENT"){
						_c = result[i].val;
					}
				}
				
				if(_n == "" || null == _n){
					alert("请输入角色");
				}
				else if(_en == "" || null == _en){
					alert("请输入英文名称");
				}
				else{
					top.jzts();
					$.ajax({
						type: "POST",
						url: '<%=basePath%>sys/saveRole',
				    	data: {ROLE_NAME: _n, ROLE_ENG_NAME: _en, ROLE_COMMENT: _c },
						dataType:'json',
						cache: false,
						success: function(data){
							if("success" == data.result){
								nextPage('${page.currentPage}');
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
function deleteRole(_id, _name){
	var options = {
			message: "<div class='item-row'><div class='item-label'>角色 :</div>" + 
					 "<div class='item-value'>" + _name + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除角色"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sys/deleteRole?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
			});
		}
	});
}

$(function() {
	
	var mn = '${mTreeNodes}';
	var mnObj = jQuery.parseJSON(mn);	
	menu_tree=$('#menu_tree').jstree(mnObj);
	
	var pn = '${pTreeNodes}';
	var pnObj = jQuery.parseJSON(pn);	
	permission_tree=$('#permission_tree').jstree(pnObj);
    
	
	$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		
	if(!ace.vars['touch']) {
		$('.chosen-select').chosen({allow_single_deselect:true}); 
		$(window)
		.off('resize.chosen')
		.on('resize.chosen', function() {
			$('.chosen-select').each(function() {
				 var $this = $(this);
				 $this.next().css({'width': 200});
			});
		}).trigger('resize.chosen');
		$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
			if(event_name != 'sidebar_collapsed') return;
			$('.chosen-select').each(function() {
				 var $this = $(this);
				 $this.next().css({'width': 200});
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
	$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
		var th_checked = this.checked;//checkbox inside "TH" table header
		$(this).closest('table').find('tbody > tr').each(function(){
			var row = this;
			if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
			else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
		});
	});
	
	UITree.init();	

});


</script>
</html>
