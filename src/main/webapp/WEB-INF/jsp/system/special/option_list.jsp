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
			<li><a>特产商品管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sp/listOption">选项管理</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<button onclick="addOption();" class="btn btn-primary">新增</button>
			<form action="sp/listOption" method="post"
				name="optionForm" id="optionForm">

				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 80px;">序号</th>
							<th class="center" style="width: 100px;">选项</th>
							<th class="center" style="width: 400px;">描述</th>
							<th class="center" style="width: 150px;">操作</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty optionList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${optionList}" var="option" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr id="row_${option.ID}"><td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<td class="center option-name">${option.NAME }</td>
										<td class="center option-comment">${option.COMMENT }</td>
										<td class="center">
											<div class="btn-group">
												<c:if test="${option.STATE == 1 }">
												<a class="btn btn-minier btn-white btn_m_enable"
													onclick="changeStateOption('${option.ID}','${option.NAME}','${option.COMMENT}','${option.STATE}');">启用</a>
												</c:if>
												<c:if test="${option.STATE == 0 }">
												<a class="btn btn-minier btn-white btn_m_enable" style="color:red"
													onclick="changeStateOption('${option.ID}','${option.NAME}','${option.COMMENT}','${option.STATE}');">禁用</a>
												</c:if>
												<a class="btn btn-minier btn-white btn_m_edit"
													onclick="updateOption('${option.ID}','${option.NAME}','${option.COMMENT}');">编辑</a>
												<a class="btn btn-minier btn-white btn_m_delete"
													onclick="delOption('${option.ID}','${option.NAME}','${option.STATE}');">删除</a>
												<a class="btn btn-minier btn-white btn_m_child" target="mainFrame" href="sp/listOptVal/?MID=${option.ID}">选项值</a>
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

top.hangge();
function addOption(){
	var options = {
			title: "新增选项",
			inputs:[
				{"type": "text", "label": "选项名称", "value": "", "idx": "NAME", "required": true },
				{"type": "textarea", "label": "描述", "value": "", "idx": "COMMENT", "required": true }
			]
		};
		bootbox.prompt(options, function(result) {
			if(result) {
				var _n, _c;
				for(var i = 0; i < result.length; i++){
					if(result[i].idx == "NAME"){
						_n = result[i].val;
					}
					if(result[i].idx == "COMMENT"){
						_c = result[i].val;
					}
				}
				
				if(_n == "" || null == _n){
					alert("请输入选项名称");
				}
				else{
					top.jzts();
					$.ajax({
						type: "POST",
						url: '<%=basePath%>sp/saveOpt',
				    	data: {NAME: _n, COMMENT: _c, STATE: 0},
						dataType:'json',
						cache: false,
						success: function(data){
							if("success" == data.result){
								var pageNum = '${page.currentPage}';
								if(pageNum == '0'){
									$("#optionForm").attr("action", "sp/listOption/?" + $("#optionForm").serialize());
									$("#optionForm").submit();	
								}else{
									nextPage('${page.currentPage}');
								}
							}else{
								alert('FAILED!');
							}
							top.hangge();
						}
					});
				}
			}
		});
}
function changeStateOption(_id, _n, _c, _s){
	var options = {
			message: "<div class='item-row'><div class='item-label'>选项名称 :</div>" + 
					 "<div class='item-value'>" + _n + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>当前状态 :</div>" + 
					 "<div class='item-value'>" + _c + "</div></div>" +
					 "<p><span class='item-info'>您确定切换吗?</span></p>",
			title: "选项状态"
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
				url: '<%=basePath%>sp/changeStateOpt',
		    	data: {ID:_id, STATE:_s},
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
function delOption(_id, _name, _state){
	_state == 1 ? _state = "启用" :  _state = "禁用";
	
	var options = {
			message: "<div class='item-row'><div class='item-label'>选项名称 :</div>" + 
					 "<div class='item-value'>" + _name + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>当前状态 :</div>" + 
					 "<div class='item-value'>" + _state + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除选项"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sp/deleteOpt?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
				
				top.hangge();
			});
		}
	});
}
function updateOption(_id, _name, _comment){
	var options = {
		title: "编辑选项",
		inputs:[
			{"type": "text", "label": "选项名称", "value": _name, "idx": "NAME" },
			{"type": "textarea", "label": "描述", "value": _comment, "idx": "COMMENT" }
		]
	};
	bootbox.prompt(options, function(result) {
		if(result) {
			top.jzts();
			var _n, _c;
			for(var i = 0; i < result.length; i++){
				if(result[i].idx == "NAME"){
					_n = result[i].val;
				}
				if(result[i].idx == "COMMENT"){
					_c = result[i].val;
				}
			}
			$.ajax({
				type: "POST",
				url: '<%=basePath%>sp/editOpt',
		    	data: {ID:_id, NAME:_n, COMMENT: _c},
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

</script>
