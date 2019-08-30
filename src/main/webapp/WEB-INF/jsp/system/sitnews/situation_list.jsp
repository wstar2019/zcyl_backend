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
			<li><a>动态新闻管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sn/listSituation">动态管理</a></li>
		</ul>
	</div>
	<!-- /section:basics/navbar.layout -->

				<div class="page-content" style="padding: 10px;">
					<div class="container-fluid">
						<table>
							<tr>
								<td class="ipt-td">标题</td>
								<td class="ipt-td" colspan="3">
									<input class="form-control" id="C_TITLE" type="text">
								</td>
								<td class="ipt-td">
									<button onclick="initCtrl();" class="btn btn-primary">重置</button>
								</td>
							</tr>
							<tr>
								<td class="ipt-td">更新时间</td>
								<td class="ipt-td">
									<input id="CS_TIME" class=" m-ctrl-medium date-picker" type="text"/>
								</td>
								<td>---</td>
								<td class="ipt-td">
									<input id="CE_TIME" class="m-ctrl-medium date-picker" type="text"/>
								</td>
								<td class="ipt-td">
									<button onclick="search();" class="btn btn-primary">查询</button>
								</td>
								<td>
									<button onclick="add();" class="btn btn-primary">新增</button>
								</td>
							</tr>
						</table>
						
						<form action="sn/listSituation" method="post" name="situationForm" id="situationForm">
							
							<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
								<thead>
									<tr>
										<th class="center" style="width: 80px;">序号</th>
										<th class="center" style="width: 150px;">标题</th>
										<th class="center" style="width: 100px;">写作者</th>
										<th class="center" style="width: 100px;">更新时间</th>
										<th class="center" style="width: 100px;">操作</th>
									</tr>
								</thead>
								<tbody>
									
									<c:choose>										
										<c:when test="${not empty situationList}">
											<%
									            int numStds = 0;								
									            pageContext.setAttribute("numStds", numStds);
									        %>
											<c:forEach items="${situationList}" var="sit" varStatus="vs">
												<%
										            numStds++;								
										            pageContext.setAttribute("numStds", numStds);
										        %>
												<tr id="${sit.id}">
													<td class="center" onclick="viewDetail('${sit.id}');">${(page.currentPage -1) * page.showCount + numStds}</td>
													<td class="center" onclick="viewDetail('${sit.id}');">${sit.sn_title}</td>
													<td class="center" onclick="viewDetail('${sit.id}');">${sit.sn_man}</td>
													<td class="center" onclick="viewDetail('${sit.id}');">${sit.sn_datetime}</td>
													<td class="center">
														<div class="btn-group">
															<a class="btn btn-minier btn-white btn_m_edit"
																target="mainFrame" href="sn/editSituation?ID=${sit.id}">编辑</a>
															<a class="btn btn-minier btn-white btn_m_delete"
																onclick="delSituation('${sit.id}','${sit.sn_title}');">删除</a>
															
															
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
						<form action="" style="display: none;" method="post" name="situationEditForm" id="situationEditForm">
							<input type="text" id="SN_TITLE" name="SN_TITLE" value="" />
							<input type="text" id="SN_KIND" name="SN_KIND" value="0" />
							<input type="text" id="S_TIME" name="S_TIME" value="" />
							<input type="text" id="E_TIME" name="E_TIME" value="" />
						</form>
					</div>
				</div>

			<!-- /.page-content -->

	<!-- /.main-content -->

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

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

function viewDetail(_id){
	$("#situationEditForm").attr("action", "sn/viewSituation" + "?ID=" + _id + "&" + $("#situationEditForm").serialize());
	$("#situationEditForm").submit();
}

jQuery(function() {
	$("#C_TITLE").val('${pd.SN_TITLE}');
	$("#CS_TIME").val('${pd.S_TIME}');
	$("#CE_TIME").val('${pd.E_TIME}');
});

function initCtrl(){
	$("#C_TITLE").val("");
	$("#CS_TIME").val("");
	$("#CE_TIME").val("");
	search();
}
function search(){
	$("#SN_TITLE").val($("#C_TITLE").val());
	$("#S_TIME").val($("#CS_TIME").val());
	$("#E_TIME").val($("#CE_TIME").val());
	
	$("#situationEditForm").attr("action", "sn/listSituation" + "?" + $("#situationEditForm").serialize());
	$("#situationEditForm").submit();
}

function delSituation(_id, _ti){
	var options = {
			message: "<div class='item-row'><p><bold>标题 : </bold>" + _ti + "</p></div>" + 
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除动态"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sn/deleteSituation?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					nextPage('${page.currentPage}');
			});
		}
	});
}
function add(){
	$("#situationEditForm").attr("action", "sn/editSituation" + "?" + $("#situationEditForm").serialize());
	$("#situationEditForm").submit();
}
$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

</script>
