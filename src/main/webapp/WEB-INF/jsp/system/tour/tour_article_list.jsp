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
			<li><a>旅游管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="tour/listTourArticle">旅游商品管理</a></li>
		</ul>
	</div>
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
	            		<button onclick="addTourArticle();" class="btn btn-primary">上架新商品</button>
	            	</div>
	            </div> 
			</div>
			<form action="tour/listTourArticle" method="post" name="tourArticleForm" id="tourArticleForm">
				
				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 50px;">序号</th>
							<th class="center" style="width: 100px;">商品名称</th>
							<th class="center" style="width: 300px;">商品简介</th>
							<th class="center" style="width: 120px;">更新日期</th>
							<th class="center" style="width: 250px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty tourArticleList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${tourArticleList}" var="ta" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr tr_id="${ta.ID}"><td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<td class="center article-name">${ta.NAME }</td>
										<td class="center article-comment">${ta.DESCRIPTION }</td>
										<td class="center article-time">${ta.REGIST_TIME }</td>
										<td class="center">
											<div class="btn-group">
												<a class="btn btn-minier btn-white btn_m_tourist" target="mainFrame" href="tour/listTourBooking/?ART_ID=${ta.ID}">游客管理</a>
												<a class="btn btn-minier btn-white btn_m_feedback" target="mainFrame" href="tour/listTourFeedback/?ART_ID=${ta.ID}">点评管理</a>
												<a class="btn btn-minier btn-white btn_m_log" target="mainFrame" href="tour/articleChangeLog/?ART_ID=${ta.ID}">变更记录</a>
												<a class="btn btn-minier btn-white btn_m_edit" target="mainFrame" href="tour/editTourArticle/?ID=${ta.ID}">编辑</a>
												<a class="btn btn-minier btn-white btn_m_delete" onclick="delTourArticle('${ta.ID}','${ta.NAME}')">删除</a>
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
	<div class="modal fade" id="artModal" tabindex="-1" role="dialog" aria-labelledby="artModalLabel">						
  		<div class="modal-dialog" role="document" style="width:500px">
    		<div class="modal-content" style="border-width:0px;">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel" style="font-weight:bold">删除商品</h4>
      			</div>
      			<div class="modal-body">
      				<input id="del_art_id" value="" style="display:none;">
      				<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-3" style="text-align:right;">商品名称:</div>
   						<div class="col-sm-9" id="del_art_name" style="text-align:left;"></div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-3" style="text-align:right;">该商品状态:</div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-offset-1 col-sm-10">
   							<table class="table table-hover">
		      					<thead>      					
			      					<tr>
			      						<th class="ipt-td">用户名</th>
			      						<th class="ipt-td">状态</th>
			      					</tr>
		      					</thead>
		      					<tbody>
		      					
		      					</tbody>
		      				</table>
   						</div>
      				</div>
      				<div class="row" style="margin-bottom: 15px;">
   						<div class="col-sm-3" style="text-align:right;">
   							<span><input type="checkbox" id="msg_enable">通知用户</span>
						</div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-3" style="text-align:right;">
   							通知模板：
   						</div>
   						<div class="col-sm-4">
   							<select class="form-control">
   								<option>1</option>
   								<option>2</option>
   								<option>3</option>
   								<option>4</option>
   							</select>
   						</div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-offset-1 col-sm-10">
   							<h3>您确定删除吗？</h3>
   						</div>
   					</div>
				</div>
      		</div>
      		<div class="modal-footer" style="padding: 5px 0px 10px 0px;">
      			<button type="button" class="btn btn-primary btn_user_modal_confirm" onclick="confirmDelete();">确定</button>
       			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					        
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
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"
		type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>

</body>

<script>
	$(top.hangge());
	
	$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});
	
	function initCtrl(){
		$("#A_NAME").val("");
		$("#S_TIME").val("");
		$("#E_TIME").val("");
		search();
	}
	function search(){
		$("#OT_NAME").val($("#A_NAME").val());
		$("#OT_STIME").val($("#S_TIME").val());
		$("#OT_ETIME").val($("#E_TIME").val());
		$("#tourOptArtEditForm").attr("action", "tour/listTourArticle" + "?" + $("#tourArticleForm").serialize());
		$("#tourOptArtEditForm").submit();
	}
	
	function addTourArticle(){
		$("#OT_NAME").val("");
		$("#OT_STIME").val("");
		$("#OT_ETIME").val("");
		$("#tourOptArtEditForm").attr("action", "tour/editTourArticle" + "?" + $("#tourArticleForm").serialize());
		$("#tourOptArtEditForm").submit();
	}
	
	function delTourArticle(_id, _name){
		$('#del_art_id').val(_id);
		$('#del_art_name').html(_name);
		$('#artModal').modal("show");
	}
	function confirmDelete(){
		var _id = $('#del_art_id').val();
		top.jzts();
		var url = "<%=basePath%>tour/deleteArt?ID=" + _id;
		$.get(url,function(data){
			if(data.result == "success")
				search();
			else
				$(top.hangge());
		});
		
	}
	jQuery(function() {
		$("#A_NAME").val('${pd.NAME}');
		$("#S_TIME").val('${pd.S_TIME}');
		$("#E_TIME").val('${pd.E_TIME}');
	});
</script>
