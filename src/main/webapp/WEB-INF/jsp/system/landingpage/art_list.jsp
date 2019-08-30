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
			<li><a>首页管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="fp/listDPRKArt">朝鲜艺术</a></li>
		</ul>
	</div>

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<form action="fp/listFPArt" method="post" name="fpArtForm" id="fpArtForm">

				<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
					<thead>
						<tr>
							<th class="center" style="width: 10px;"></th>
							<th class="center" style="width: 50px;">序号</th>
							<th class="center" style="width: 80px;">商品名称</th>
							<th class="center" style="width: 120px;">销售数量</th>
							<th class="center" style="width: 50px;">销售金额</th>
							<th class="center" style="width: 100px;">浏览数</th>
						</tr>
					</thead>
					<tbody>
						
						<c:choose>										
							<c:when test="${not empty artList}">
								<%
						            int numStds = 0;								
						            pageContext.setAttribute("numStds", numStds);
						        %>
								<c:forEach items="${artList}" var="art" varStatus="vs">
									<%
							            numStds++;								
							            pageContext.setAttribute("numStds", numStds);
							        %>
									<tr>
										<td class="center art-check">
											<c:if test="${art.VIEW_STATE == 1 }">
												<input type="checkbox" id="${art.ID}" checked>
											</c:if>
											<c:if test="${art.VIEW_STATE == 0 }">
												<input type="checkbox" id="${art.ID}">
											</c:if>
											
										</td>
										<td class="center">${(page.currentPage -1) * page.showCount + numStds}</td>
										<td class="center art-name">${art.ART_NAME}</td>
										<td class="center art-desc">${art.SALE_QUANTITY}</td>
										<td class="center art-desc">${art.SALE_AMOUNT}</td>
										<td class="center art-desc">${art.VIEW_NUM}</td>
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
	<%@ include file="../index/foot.jsp"%>
	<script src="static/ace/js/bootbox.js" type="text/javascript"></script>
	<script src="static/ace/js/ace/ace.js" type="text/javascript"></script>
	<script src="static/ace/js/date-time/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	
<script>
	$('input[type="checkbox"]').on('change', function(event){
	  	var myId = $(this).attr('id');
	  	var _state = "";
	  	var _msg = "";
	  	if(event.target.checked){
  			_state = "1";
	  		_msg = "你 显示 商品吗?";
	  	}else{
  			_state = "0";
  			_msg = "你 不显示 商品吗?";
	  	} 
	  	updateState(myId, _state, _msg);
	});


	function updateState(_id, _state, _msg){
		var options = {
				message: "<p><span class='item-info'>" + _msg + "</span></p>",
				title: "选择商品通知"
		}
		bootbox.confirm(options, function(result) {
			if(result) {
				top.jzts();
				var url = "<%=basePath%>fp/update_art_vs?ID=" + _id + "&VIEW_STATE=" + _state;
				$.get(url,function(data){
					if(data.result == "success")
						$(top.hangge());
				});
			}
		});
	}
</script>
</body>

