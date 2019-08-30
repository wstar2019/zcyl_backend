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
			<li><a>艺术商品管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sa/listSlide">横幅管理</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<table>
				<tr>
	                <td class="ipt-td">简介</td>
	                <td class="ipt-td">
				    	<input class="form-control" id="S_DESC" type="text">
				    </td>
				    <td class="ipt-td">
				    	<button onclick="initCtrl();" class="btn btn-primary">重置</button>
			    	</td>
			  	</tr>
			  	<tr>
			  		<td class="ipt-td">状态</td>
	                <td class="ipt-td">
				    	<select id="S_STATE" class="form-control" style="margin-bottom:5px;">
    						<option value="">请选择</option>
						    <option value="1">已显示</option>
						    <option value="0">未显示</option>
						</select>
				    </td>
			    	<td class="ipt-td">
	            		<button type="button" onclick="search();" class="btn btn-primary">查询</button>
	            	</td>
					<td class="ipt-td">
	            		<button type="button" onclick="addSlide();" class="btn btn-primary">新增</button>
	            	</td>
	            </tr>
			</table>
			<form action="sa/listSlide" method="post" name="saSlideForm" id="saSlideForm">

				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 50px;">序号</th>
							<th class="center" style="width: 80px;">缩略图</th>
							<th class="center" style="width: 120px;">简介</th>
							<th class="center" style="width: 50px;">状态</th>
							<th class="center" style="width: 100px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty slideList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${slideList}" var="sl" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr id="row_${sl.id}">
										<td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<!--<td class="center sl-name">${sl.name }</td>-->
										<td class="center sl-name">
											<img style="width: 120px; height: 40px;" alt="" src="uploadFiles/uploadImgs/sa/${sl.name}"></td>
										<td class="center sl-desc">${sl.comment }</td>
										<td class="center sl-state">${sl.state == 1 ? '已显示' : '未显示'}</td>							
										<td class="center">
											<div class="btn-group">
												<a class="btn btn-minier btn-white btn_m_edit"
													onclick="updateSlide('${sl.id}','${sl.name}','${sl.comment}','${sl.view_type}','${sl.state}');">编辑</a>
												<a class="btn btn-minier btn-white btn_m_delete"
													onclick="delSlide('${sl.id}','${sl.comment}','${sl.name}');">删除</a>
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
			
			<div class="modal fade" id="slideModal" tabindex="-1" role="dialog" aria-labelledby="slideModalLabel">						
		  		<div class="modal-dialog" role="document" style="width:500px">
		    		<div class="modal-content" style="border-width:0px;">
		      			<div class="modal-header">
		        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		        				<span aria-hidden="true">&times;</saan></button>
		        			<h4 class="modal-title" id="myModalLabel" style="font-weight:bold">新增横幅</h4>
		      			</div>
		      			<div class="modal-body" style="text-align:left;">
		      				<input id="SLIDE_ID" type="text" style="display:none;" value="">
		      				<table>
		      					<tr>
		      						<td class="ipt-td">图片</td>
		      						<td colspan="2">
		      							<input type="file" id="SLIDE_IMAGE" name="SLIDE_IMAGE"
											style="display: none;" onchange="uploadFile(this)" />
										<a onclick="fileType('SLIDE_IMAGE')" id="SLIDE_IMAGE_BTN"
											class="btn btn-mini btn-success"
											style="margin-bottom:5px; height: 30px; align: center;">选择文件</a>
										<img id="SLIDE_IMAGE_PREVIEW" alt="" src="" style="height: 45px; max-width:230px;">
										<input type="text"
											name="SLIDE_IMAGE_NAME" id="SLIDE_IMAGE_NAME" accept="image/*"
											maxlength="32" placeholder="来选择任何文件" title="图片"
											style="display: none;"/>
		      						</td>
		      					</tr>
		      					<tr>
		      						<td class="ipt-td">简介</td>
		      						<td colspan="2">
		      							<textarea id="SLIDE_DESC" class="form-control" rows="5" style="min-width:400px;max-width:400px;"></textarea>
		      						</td>
		      					</tr>
		      					<tr>
		      						<td>
		      						</td>
		      						<td>
		      							<div class="checkbox">
										  <label><input id="SLIDE_VIEW_STATE" type="checkbox" value="" style="margin-bottom:5px;">是否显示</label>
										</div>
		      						</td>
		      					</tr>
		      					<tr>
		      						<td class="ipt-td">
		      							展示格式
		      						</td>
		      						<td colspan="2">
		      							<select id="SLIDE_VIEW_TYPE" class="form-control" style="margin-bottom:5px;">
		      								<option value="">请选择</option>
										    <option value="1">中心</option>
										    <option value="2">适合宽度</option>
										    <option value="3">适合高度</option>
										</select>
		      						</td>
		      						<td><span style="color:red;">*</span></td>
		      					</tr>
		      					
		      				</table>
						</div>
		      		</div>
		      		<div class="modal-footer" style="padding: 5px 0px 10px 0px;">
		      			<button type="button" class="btn btn-primary btn_user_modal_confirm" onclick="slideConfirm();">确定</button>
	        			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					        
		      		</div>
		    	</div>
		  	</div>
		</div>
	</div>


	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
	<%@ include file="../index/foot.jsp"%>
	<script src="static/ace/js/bootbox.js" type="text/javascript"></script>
	<script src="static/ace/js/ace/ace.js" type="text/javascript"></script>
	<script src="static/ace/js/date-time/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	
<script>
	
	jQuery(function() {
		$("#S_DESC").val('${pd.COMMENT}');
		$("#S_STATE").val('${pd.STATE}');
	});
	function initCtrl(){
		$("#S_DESC").val('');
		$("#S_STATE").val('');
		search();
	}
	function search(){
		var DESC = $("#S_DESC").val();
		var STATE = $("#S_STATE").val();
		$("#saSlideForm").attr("action", "sa/listSlide/?COMMENT=" + DESC + "&STATE=" + STATE);
		$("#saSlideForm").submit();
	}

	function fileType(file) {
		document.getElementById(file).click();
	}
	
	function uploadFile(obj) {
		$("#SLIDE_IMAGE_NAME").val(obj.files[0].name);
		var fileType = obj.value.substr(obj.value.lastIndexOf("."))
				.toLowerCase();//获得文件后缀名
		if (fileType != '.jpg' && fileType != '.png' && fileType != '.bmp') {
			$("#SLIDE_IMAGE_NAME").tips({
				side : 3,
				msg : '请上传图片格式的文件',
				bg : '#AE81FF',
				time : 3
			});
			$("#SLIDE_IMAGE_NAME").val('');
			document.getElementById("SLIDE_IMAGE").files[0] = '请选择图片格式的文件';
			return;
		}
		$.each(obj.files, function(_, file) {
	        var reader = new FileReader();
	        reader.onload = function (ev) {
	        	$("#SLIDE_IMAGE_PREVIEW").attr('src', ev.target.result);
	        }
	        reader.readAsDataURL(file);
	    });
	}
	
	function slideConfirm(){
		var _id = $("#SLIDE_ID").val();
		var _name = $("#SLIDE_IMAGE_NAME").val();
		var _vstate = $("#SLIDE_VIEW_STATE").prop('checked') == true ? 1 : 0;
		var _vtype = $("#SLIDE_VIEW_TYPE").val();
		var _desc = $("#SLIDE_DESC").val();
		
		if(_name == ""){
			$("#SLIDE_IMAGE_BTN").tips({
				side : 2,
				msg : '请选择图片',
				bg : '#AE81FF',
				time : 3
			});

			$("#SLIDE_IMAGE_BTN").focus();
			return;
		}
		if(_vtype == ""){
			$("#SLIDE_VIEW_TYPE").tips({
				side : 2,
				msg : '请选择展示格式',
				bg : '#AE81FF',
				time : 3
			});

			$("#SLIDE_VIEW_TYPE").focus();
			return;
		}
		
		var fd = new FormData();
	    var _img = $('#SLIDE_IMAGE')[0].files;
	    
	    fd.append("ID", _id);
	    fd.append("IMG_NAME", _name);
	 	fd.append("COMMENT", _desc);
		fd.append("VIEW_STATE", _vstate);
		fd.append("VIEW_TYPE", _vtype);
		fd.append("IMG", _img[0]);
		
		top.jzts();
		$.ajax({
			type: "POST",
			url: '<%=basePath%>sa/saveSlide',
	    	data: fd,
	    	contentType: false,
       	 	processData: false,
			success: function(data){
				if("success" == data.result){
					$('#slideModal').modal("hide");
					search();
				}else{
					alert('FAILED!');
				}
				$(top.hangge());
			}
		});
		
	}
	function addSlide(){
		$("#SLIDE_ID").val("");
		$("#SLIDE_IMAGE_NAME").val("");
		$("#SLIDE_IMAGE_PREVIEW").attr('src', '');
		$("#SLIDE_VIEW_STATE").attr("checked", true);
		$("#SLIDE_VIEW_TYPE").val("");
		$("#SLIDE_DESC").val("");
		$('#slideModal').modal("show");
		
		$("#myModalLabel").html("新增横幅");
	}
	
	function updateSlide(_id, _name, _desc, _vType, _vState){
		$("#myModalLabel").html("编辑横幅");
		$("#SLIDE_ID").val(_id);
		$("#SLIDE_IMAGE_NAME").val(_name);
		$("#SLIDE_IMAGE_PREVIEW").attr('src', "uploadFiles/uploadImgs/sa/" + _name);
		_vState == 1 ?  $("#SLIDE_VIEW_STATE").attr("checked", true) : $("#SLIDE_VIEW_STATE").attr("checked", false); 
		$("#SLIDE_VIEW_TYPE").val(_vType);
		$("#SLIDE_DESC").val(_desc);
		$('#slideModal').modal("show");
	}
	function delSlide(_id, _comment, _name){
		var options = {
				message: "<div class='item-row'><div class='item-label'>横幅名称 :</div>" + 
						 "<div class='item-value'>" + _comment + "</div></div>" +
						 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
				title: "删除横幅"
		}
		bootbox.confirm(options, function(result) {
			if(result) {
				top.jzts();
				var url = "<%=basePath%>sa/deleteSlide?ID=" + _id + "&NAME=" + _name;
				$.get(url,function(data){
					if(data.result == "success")
						search();
					
					$(top.hangge());
				});
			}
		});
	}
</script>
</body>

