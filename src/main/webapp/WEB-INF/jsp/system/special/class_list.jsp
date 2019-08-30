<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<link rel="stylesheet" type="text/css" href="static/global/css/plugins.css">
<link rel="stylesheet" type="text/css" href="static/global/css/mycomponents.css">

<%@ include file="../index/top.jsp"%>

<style>
	.td-padding {
		padding-left: 10px;
		padding-top: 10px;
	}
	
	.table > tbody > tr > td {
		vertical-align: middle;
	}
	.redMark {
		float: left;
		color: red;
		padding-top: 10px;
		margin-left: -15px;
	}
	#NewClassForm .row{
		margin-bottom:5px;
	}
</style>
</head>
<body class="no-skin">
	<div class="page-header">
		<ul class="navi">
			<li><a>特产商品管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sp/listKind">分类管理</a></li>
		</ul>
	</div>
	<div class="page-content">
		<form action="sp/listKind" method="post" name="ClassListForm" id="ClassListForm">	
			<table style="margin-top: 5px; margin-left: 20px;">
				<tr>										
					<td>
						<button type="button" class="btn btn-primary" title="新增" onclick="initalizeModal();">新增 </button>
					</td>
				</tr>
			</table>
			
			<table id="ClassListTable" class="table table-striped table-bordered table-hover" style="margin-top: 10px;">
				<thead>
					<tr>
						<th class="center" style="width: 120px;">分类名称</th>
						<th class="center" style="width: 200px;">分类说明</th>
						<th class="center" style="width: 80px;">是否显示</th>
						<th class="center" style="width: 80px;">是否一级显示</th>
						<th class="center" style="width: 80px;">展示格式</th>
						<th class="center" style="width: 200px;">图片</th>
						<th class="center" style="width: 180px;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty ClassList}">
							<c:forEach items="${ClassList}" var="cla" varStatus="vs">
								<tr tr_id='${cla.CLASS_ID }' expanded="false"
									level=${cla.LEVEL}
									eco_tmp_name='${cla.NAME}' class="class_row row_${cla.LEVEL}">
									<td class="vcol1">
										<i class="fa fa-caret-right btn_cursor" id='LEVEL${cla.CLASS_PARENT}' style="cursor: pointer; padding:5px 10px;"></i>${cla.CLASS_NAME }
									</td>	
									<td class="center vcol2">
										<c:if test="${cla.CLASS_CONTENT.length() > 35}">
											${cla.CLASS_CONTENT.substring(0, 35)}...
										</c:if> 
										<c:if test="${cla.CLASS_CONTENT.length() <= 35}">
											${cla.CLASS_CONTENT}
										</c:if>
									</td>	
									<td class="center">
										<c:if test="${cla.CLASS_VISIBILITY == 1}">
											是
										</c:if> 
										<c:if test="${cla.CLASS_VISIBILITY == 0}">
											否
										</c:if>
									</td>
									<td class="center">
										<c:if test="${cla.CLASS_LAYER == 1}">
											是
										</c:if> 
										<c:if test="${cla.CLASS_LAYER == 0}">
											否
										</c:if>
									</td>
									<td class="center">
										<c:if test="${cla.CLASS_SHOW_TYPE == 1}">
											添加
										</c:if> 
										<c:if test="${cla.CLASS_SHOW_TYPE == 2}">
											销量
										</c:if>
										<c:if test="${cla.CLASS_SHOW_TYPE == 3}">
											库存
										</c:if>
									</td>	
									<td class="center">
										<img alt="" src="<%=basePath%>uploadFiles/uploadImgs/sp/${cla.CLASS_IMG_URL}" style="height: 45px;">																
									</td>	
									<td class="center">
										<div class="btn-group">
											<%-- <c:if test="${QX.add == 1 }"> --%>
												<a class="btn_m_add" data-toggle="modal" style="cursor:pointer; margin-right: 20px;"
														onclick="addChildClass('${cla.CLASS_ID}','${cla.CLASS_NAME}')">添加下级分类</a>
											<%-- </c:if>
											<c:if test="${QX.edit == 1 }"> --%>
												<a class="btn_m_edit" style="cursor:pointer; margin-right: 20px;"
													onclick="editClass('${cla.CLASS_ID}', '${cla.CLASS_NAME}',
														'${cla.CLASS_PARENT}','${cla.CLASS_VISIBILITY}',
														'${cla.CLASS_LAYER}','${cla.CLASS_SHOW_TYPE}',
														'${cla.CLASS_IMG_URL}', '${cla.CLASS_CONTENT}')">编辑</a>
											<%-- </c:if>
											<c:if test="${QX.del == 1 }"> --%>
												<a class="btn_m_delete" onclick="deleteClass('${cla.CLASS_ID}', '${cla.CLASS_NAME}')">删除</a>
											<%-- </c:if>			 --%>														
										</div>
									</td>
									<%-- <td class="center vcol3" style="display: none;">${cla.CLASS_PARENT}	 --%>		
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
		<div class="modal fade" id="NewClassModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">	
			<div class="modal-dialog" role="document" style="width:509px">
				<div class="modal-content">
					<div class="modal-header mymodal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        			<h3 class="modal-title" id="myModalLabel">新增一级分类</h3>
	      			</div>
	      			<div class="modal-body">
	      				<form action="sp/saveClass" method="post" name="NewClassForm"
							id="NewClassForm" enctype="multipart/form-data">
	      					<div class="row">
      							<input type="hidden" name="CLASS_ID" id="class_id" value="">
								<input type="hidden" name="CLASS_PARENT" id="class_parent" value="0">
      						</div>
      						<div class="row" id="div-parent">
      							<div class="col-sm-3">
							        <span class="">父级分类</span>
							    </div>
							    <div class=" col-sm-7">
							        <span id="parent_class_name" style="float:left;"></span>
							    </div>
      						</div>
      						<div class="row">
      							<div class="col-sm-3">
							        <span class="">分类名称</span>
							    </div>
							    <div class=" col-sm-7">
							        <input id="class_name" name="CLASS_NAME" class="form-control" type="text" value="" style="width: 100%" />
							    </div>
							    <div class=" col-sm-2">
							        <span class="redMark">*</span>
							    </div>
      						</div>
      						<div class="row">
							    <div class="col-sm-offset-3 col-sm-9">
							    	<input id="class_visibility_value" name="CLASS_VISIBILITY" type="text" style="display:none;" value=""/>
							    	<input id="class_layer_value" name="CLASS_LAYER" type="text" style="display:none;" value=""/>
							        <span style="float:left;"><input id="class_visibility" type="checkbox"/>是否显示</span>
							        <span><input id="class_layer" type="checkbox"/>是否一级显示</span>
							    </div>
      						</div>
      						<div class="row">
      							<div class="col-sm-3">
							        <span class="">展示格式</span>
							    </div>
							    <div class=" col-sm-7" style="padding-top: 5px;">
							        <select class="form-control" name="CLASS_SHOW_TYPE" id="short_show_type" data-placeholder="选择季度" style="width: 100%">
							            <option value="">请选择</option>
							            <option value="1">添加排序</option>
							            <option value="2">销售排序</option>
							            <option value="3">库存排序</option>
							        </select>
							    </div>
							    <div class=" col-sm-2">
							        <span class="redMark">*</span>
							    </div>
      						</div>
      						<div class="row">
      							<div class="col-sm-3">
							        <span class="">图片</span>
							    </div>
							    <div class="col-sm-7" style="text-align: left;">
							        <input type="file" id="class_photo" name="CLASS_PHOTO"
										style="display: none;" onchange="uploadFile(this)" />
							        <a onclick="fileType('class_photo')" class="btn btn-mini btn-success"
										style="height: 32px; align: center;">选择文件</a>
									<img id="class_photo_preview" alt="" src="" style="height: 45px;">
							        <input type="text" name="CLASS_PHOTO_NAME" id="class_photo_name"
										accept="image/*" maxlength="32" placeholder="来选择任何文件"
										title="头像图片" style="display: none;" readonly/>
							    </div>
      						</div>
      						<div class="row">
      							<div class="col-sm-3">
							        <span class="">分类说明</span>
							    </div>
							    <div class=" col-sm-7">
							        <textarea id="class_content" name="CLASS_CONTENT" class="input-xlarge" style="height: 115px;"></textarea>
							    </div>
      						</div>
	      				</form>
	      			</div>
      			
	      			<div class="modal-footer">					      			
		        		<button type="button" class="btn btn-white btn_cancel" data-dismiss="modal">取消</button>	
		        		<button type="button" class="btn btn-app btn-primary btn-xs" onclick="saveClass();">确定</button>				        
		      		</div>					      			
				</div>
			</div>
		</div>
		<div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">						
	  		<div class="modal-dialog" role="document" style="width:400px">
	  			<div class="modal-content">
	  				<div class="modal-header mymodal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        			<h3 class="modal-title mymodal_label" id="myModalLabel" style="text-align: center;">删除分类</h3>
	      			</div>
	      			<div class="modal-body">
	      				<form action="sp/deleteClass" name="DeleteForm" id="DeleteForm" method="post" style="font-size: 19px;">
	      					<input type="hidden" id="del_class_id" name="CLASS_ID" value=""/>
	      					<div class="row" style="padding-top: 30px;">
								<span style="font-weight: bold;">分类名称：</span><span id="del_class_name"></span>
							</div>
	      					<div class="row" style="padding-top: 10px;">
								<span style="font-weight: bold;">您确定删除吗？</span>
							</div>
	      				</form>
	      			</div>
	  				<div class="modal-footer">					      			
		        		<button type="button" class="btn btn-white btn_cancel" data-dismiss="modal">取消</button>	
		        		<button type="button" class="btn btn-app btn-primary btn-xs" onclick="deleteConfirm();">确定</button>				        
		      		</div>
	  			</div>
	  		</div>
	  	</div>
	</div>

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

	<!-- basic scripts -->

	<%@ include file="../index/foot.jsp"%>

	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<script src="static/ace/js/chosen.jquery.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script src="static/global/plugins/jstree/dist/jstree.min.js"></script>
	<script src="static/global/scripts/ui-tree.js"></script>
</body>

<script type="text/javascript">
	$(top.hangge());
	function addChildClass(_id, _name){
		$("#div-parent").show();
		$("#parent_class_name").text(_name);
		$("#myModalLabel").text("添加下级分类");
		$("#class_id").val("");
		$("#class_name").val("");
		$("#class_parent").val(_id);
		$("#class_visibility").attr("checked", true); 
		$("#class_layer").attr("checked", false);
		$("#class_visibility_value").val("0");
		$("#class_layer_value").val("0");
		$("#class_show_type").val("");
		$("#class_photo_name").val("");
		$("#class_photo_preview").attr('src', "");
		$("#class_content").val("");
		$('#NewClassModal').modal("show");
	}
	function editClass(_id, _name, _pid, _visi, _lay, _show, _img, _cont){
		$("#myModalLabel").text("编辑分类");
		$("#div-parent").hide();
		$("#class_id").val(_id);
		$("#class_name").val(_name);
		$("#class_parent").val(_pid);
		_visi == "1" ?  $("#class_visibility").attr("checked", true) : $("#class_visibility").attr("checked", false); 
		_lay == "1" ?  $("#class_layer").attr("checked", true) : $("#class_layer").attr("checked", false);
		$("#class_visibility_value").val(_visi);
		$("#class_layer_value").val(_lay);
		$("#class_show_type").val(_show);
		$("#class_photo_name").val(_img);
		$("#class_photo_preview").attr('src', "uploadFiles/uploadImgs/sp/" + _img);
		$("#class_content").val(_cont);
		$('#NewClassModal').modal("show");
	}
	function deleteClass(_id, _name){
		$("#del_class_name").text(_name);
		$("#del_class_id").val(_id);
		$('#DeleteModal').modal("show");
	}
	function deleteConfirm(){
		$("#DeleteForm").attr("action", "sp/deleteClass");
		$("#DeleteForm").submit();
		$('#DeleteModal').modal("hide");
	}
	function saveClass() {
		if ($("#class_name").val() == '') {
			$("#class_name").tips({
				side : 3,
				msg : '请输入分类名称',
				bg : '#AE81FF',
				time : 2
			});
			$("#class_name").focus();
			$("#class_name").val('');
			$("#class_name").css("background-color", "white");
			return;
		}
		if ($("#short_show_type").val() == '') {
			$("#short_show_type").tips({
				side : 3,
				msg : '请选择展示格式',
				bg : '#AE81FF',
				time : 3
			});
			$("#short_show_type").val('');
			$("#short_show_type").focus();
			return;
		}
		
		if ($("#class_photo_name").val() == '') {
			$("#class_photo_name").tips({
				side : 3,
				msg : '请上传图片格式的文件',
				bg : '#AE81FF',
				time : 3
			});
			$("#class_photo_name").val('');
			$("#class_photo").focus();
			document.getElementById("class_photo").files[0] = '请选择图片格式的文件';
			return;
		}
		
		var _v = $("#class_visibility").prop('checked') == true ? 1 : 0;
		var _f = $("#class_layer").prop('checked') == true ? 1 : 0;
		$("#class_visibility_value").val(_v);
		$("#class_layer_value").val(_f);
		
		$("#NewClassForm").attr("action", "sp/saveClass");
		$("#NewClassForm").submit();
	}
	
	function fileType(file) {
		document.getElementById(file).click();
	}

	function uploadFile(obj) {
		$("#class_photo_name").val(obj.files[0].name);
		var fileType = obj.value.substr(obj.value.lastIndexOf("."))
				.toLowerCase();//获得文件后缀名
		if (fileType != '.jpg' && fileType != '.png' && fileType != '.bmp') {
			$("#class_photo_name").tips({
				side : 3,
				msg : '请上传图片格式的文件',
				bg : '#AE81FF',
				time : 3
			});
			$("#class_photo_name").val('');
			document.getElementById("class_photo").files[0] = '请选择图片格式的文件';
			return;
		}
			
		$.each(obj.files, function(_, file) {
	        var reader = new FileReader();
	        reader.onload = function (ev) {
	        	$("#class_photo_preview").attr('src', ev.target.result);
	        }
	        reader.readAsDataURL(file);
	    });
	}
	
	function initalizeModal() {
		$("#div-parent").hide();
		$("#myModalLabel").text("新增一级分类");
		$("#class_id").val("");
		$("#class_name").val("");
		$("#class_parent").val("0");
		$("#class_visibility").attr("checked", true); 
		$("#class_layer").attr("checked", false);
		$("#class_visibility_value").val("0");
		$("#class_layer_value").val("0");
		
		$("#class_show_type").val("");
		$("#class_photo_name").val("");
		$("#class_photo_preview").attr('src', '');
		$("#class_content").val("");
		$('#NewClassModal').modal("show");
	}
	
	$(function() {
		var tr_id="";	
		
		var _tree = "<div id='tree_1' class='tree-demo'>" + $("#strTree").val() + "</div>" 
		$("#tree_1").html(_tree);
		
		$("tr.class_row").each(function(index) {		
			if ($(this).attr("level") != "0")
				$(this).css("display", "none");		
			
			if(parseInt($(this).attr("level")) + 1 != parseInt($(this).next().attr("level"))) {			
				$(this).find("i.btn_cursor").removeClass("fa fa-caret-right btn_cursor");
				$(".tree" + $(this).attr("tr_id")).attr("data-jstree", "{ \"type\" : \"file\" , \"icon\" : \"fa fa-file icon-state-success\"}");			
			}
		});			
		
		$("#ClassListTable").find("tbody>.class_row:last").attr("last", "true");
		
		$("#ClassListTable").on('click',".btn_cursor", function(){
			var node = $(this).parent().parent();
			//console.log(node.attr("level"));
			var _level = parseInt(node.attr("level"));
			//console.log(_level);
			if(node.attr("expanded") == "false") {
				node.attr("expanded", "true");
				node.css("color", "blue");
			}
			else {
				node.attr("expanded", "false");
				node.css("color", "black");
			}
			
			var _expanded = node.attr("expanded"); 
			if(_expanded == "true") {
				$(this).removeClass("fa fa-caret-right btn_cursor");	
				$(this).addClass("fa fa-caret-down btn_cursor");
			}
			else {
				$(this).removeClass("fa fa-caret-down btn_cursor");	
				$(this).addClass("fa fa-caret-right btn_cursor");
			}		
			var i = 0;
					
			while(1) {
				node = node.next();
				//console.log(node.css("display") != "none");
				//console.log(node);
				if(node.attr("level") == _level || node.attr("level") < _level)
					break;
				
				if(_expanded == "true")
				{
					if(node.attr("level") == ("" + (_level + 1)))
					{
						node.css("display", "");
						node.attr("disp", "true");
					}
					if(node.attr("disp") == "true")
						node.css("display", "");
				}
				else
				{
					if(node.attr("level") == ("" + (_level + 1)))
					{					
						node.attr("disp", "false");
					}
					node.css("display", "none");
				}	
				if(node.attr("last") == "true")
					break;
			}
		});
	});
	
</script>
</html>
