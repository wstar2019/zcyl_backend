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
<link rel="stylesheet" type="text/css" href="static/global/css/mycomponents.css">
<link rel="stylesheet" type="text/css" href="static/global/plugins/jstree/dist/themes/default/style.min.css">
<link rel="stylesheet" type="text/css" href="static/global/css/plugins.css">

<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>艺术商品管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sa/listArticleUpdate">商品变更记录</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<div class="row form-horizontal">
				<div class="col-md-8">
					<div class="form-group">
					    <label class="col-sm-1 control-label">商品名称</label>
					    <div class="col-sm-11">
					      <input class="form-control" id="AT_NAME" type="text">
					    </div>
				  	</div>
				  	<div class="form-group">
					    <label class="col-sm-1 control-label">变更日期</label>
					    <div class="col-sm-6">
					    	<div class="form-inline">
								<div class="form-group" style="margin:0px;">
								  	<input id="S_TIME" class=" m-ctrl-medium date-picker" type="text" style="width: 150px;"/>
								</div>
								<div class="form-group" style="margin:0px;">
								  	<span>---</span>
								</div>
								<div class="form-group" style="margin:0px;">
								  	<input id="E_TIME" class="m-ctrl-medium date-picker" type="text" style="width: 150px;"/>
								</div> 
					    	</div>
				  		</div>
				  		<div class="col-sm-5">
				  			<div class="form-group">
					  			<label class="col-sm-4 control-label">变更类型</label>
					  			<div class="col-sm-8">
					  				<select id="OP_KIND" class="form-control" style="margin-bottom:5px;">
			    						<option value="">全部</option>
									    <option value="新增">新增</option>
									    <option value="编辑">编辑</option>
									    <option value="删除">删除</option>
									</select>
					  			</div>					  			
							</div>
				  		</div>
				  	</div>
				</div>
				<div class="col-md-4">
	            	<div class="form-group">
	            		<button onclick="initCtrl();" class="btn btn-primary">重置</button>
	            	</div>
	            	<div class="form-group">
	            		<button onclick="search();" class="btn btn-primary">查询</button>	            		
	            	</div>
	            </div> 
			</div>
			
			<form action="sa/listArticleUpdate" method="post" name="articleForm" id="articleForm">
				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 40px;">序号</th>
							<th class="center" style="width: 300px;">商品名称</th>
							<th class="center" style="width: 150px;">变更日期</th>
							<th class="center" style="width: 130px;">变更类型</th>
							<th class="center" style="width: 150px;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>										
							<c:when test="${not empty logList}">
								<c:forEach items="${logList}" var="log" varStatus="vs">
									<tr id="${log.ID}">
										<td class="center">${vs.index+1}</td>
										<td class="center article-name">${log.NAME }</td>
										<td class="center article-comment">${log.OP_DT }</td>
										<td class="center article-time">${log.OP_KIND }</td>
										<td class="center">
											<div class="btn-group">
												<a class="btn btn-minier btn-white btn_m_list"
													onclick="viewLog('${log.NAME}','${log.OP_DT}','${log.OP_KIND}');">查看</a>												
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
			<form action="" style="display: none;" method="post" name="searchForm" id="searchForm">
				<input type="text" id="SEARCH_NAME" name="AT_NAME"/>
				<input type="text" id="SEARCH_S_TIME" name="S_TIME"/>
				<input type="text" id="SEARCH_E_TIME" name="E_TIME"/>
				<input type="text" id="SEARCH_KIND" name="OP_KIND"/>
			</form>
		</div>
	</div>
	
	<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel">						
  		<div class="modal-dialog" role="document" style="width:500px">
    		<div class="modal-content">
    			<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span>
       				</button>
    				<h4 id="viewTitle" class="modal-title">查看商品变更记录</h4>
   				</div>
   				<div class="modal-body">
   					<div class="bootbox-body">
   						<form class="bootbox-form form-horizontal">
   							<div class="form-group">
   								<label class="col-sm-2 control-label">商品名称</label>
   								<div class="col-sm-10 ipt-container">
   									<label id="MD_NAME"></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">变更日期</label>
								<div class="col-sm-10 ipt-container">
									<label id="MD_DATE"></label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">变更类型</label>
								<div class="col-sm-10 ipt-container">
									<label id="MD_KIND"></label>
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

	<!-- /.main-content -->

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

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
	
	function viewLog(_name, _date, _kind){		
		$('#MD_NAME').text(_name);
		$('#MD_DATE').text(_date);
		$('#MD_KIND').text(_kind);
		$('#viewModal').modal("show");		
	}
	
	function initCtrl(){
		$("#AT_NAME").val("");
		$("#S_TIME").val("");
		$("#E_TIME").val("");
		$("#OP_KIND").val("");
		search();
	}
	
	function search(){
		$("#SEARCH_NAME").val($("#AT_NAME").val());
		$("#SEARCH_S_TIME").val($("#S_TIME").val());
		$("#SEARCH_E_TIME").val($("#E_TIME").val());
		$("#SEARCH_KIND").val($("#OP_KIND").val());
		$("#searchForm").attr("action", "sa/listArticleUpdate");
		$("#searchForm").submit();
	}

</script>
