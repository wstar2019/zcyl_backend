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
			<li><a target="mainFrame" href="sa/listFeedback">评价管理</a></li>
		</ul>
	</div>

				<div class="page-content" style="padding: 10px;">
					<div class="container-fluid">
						<table>
							<tr>
								<td class="ipt-td">用户名</td>
								<td class="ipt-td"><input class="form-control" id="USER_NAME" type="text"></td>
								<td class="ipt-td">商品名称</td>
								<td class="ipt-td"><input class="form-control" id="ART_NAME" type="text"></td>
								<td class="ipt-td"><button onclick="initCtrl();" class="btn btn-primary">重置</button></td>
							</tr>
							<tr>
								<td class="ipt-td">更新日期</td>
								<td class="ipt-td"><input id="S_TIME"class=" m-ctrl-medium date-picker" type="text"/></td>
								<td class="ipt-td">---</td>
								<td class="ipt-td"><input id="E_TIME" class="m-ctrl-medium date-picker" type="text"/></td>
								<td class="ipt-td"><button onclick="search();" class="btn btn-primary">查询</button></td>
							</tr>
						</table>
						<form action="sa/listFeedback" method="post" name="feedbackForm" id="feedbackForm">
							
							<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
								<thead>
									<tr>
										<th class="center" style="width: 80px;">序号</th>
										<th class="center" style="width: 150px;">用户名</th>
										<th class="center" style="width: 100px;">商品名称</th>
										<th class="center" style="width: 100px;">更新日期</th>
										<th class="center" style="width: 100px;">操作</th>
									</tr>
								</thead>
								<tbody>
									
									<c:choose>										
										<c:when test="${not empty feedbackList}">
											<%
									            int numStds = 0;								
									            pageContext.setAttribute("numStds", numStds);
									        %>
											<c:forEach items="${feedbackList}" var="feedback" varStatus="vs">
												<%
										            numStds++;								
										            pageContext.setAttribute("numStds", numStds);
										        %>
												<tr id="${feedback.id}">
													<td class="center" onclick="viewFeedback('${feedback.customer}','${feedback.article}','${feedback.content}');">${(page.currentPage -1) * page.showCount + numStds}</td>
													<td class="center f-user-name" onclick="viewFeedback('${feedback.customer}','${feedback.article}','${feedback.content}');">${feedback.customer}</td>
													<td class="center f-art-name" onclick="viewFeedback('${feedback.customer}','${feedback.article}','${feedback.content}');">${feedback.article}</td>
													<td class="center f-update-date" onclick="viewFeedback('${feedback.customer}','${feedback.article}','${feedback.content}');">${feedback.create_time}</td>
													<td class="center">
														<div class="btn-group">
															<a class="btn btn-minier btn-white btn_m_delete" 
																onclick="delFeedback('${feedback.id}','${feedback.customer}','${feedback.article}');">删除</a>
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
						<form action="" style="display: none;" method="post" name="feedbackEditForm" id="feedbackEditForm">
							<input type="text" id="FU_NAME" name="USER_NAME" value="" />
							<input type="text" id="FA_NAME" name="ART_NAME" value="" />
							<input type="text" id="FC_STIME" name="S_TIME" value="" />
							<input type="text" id="FC_ETIME" name="E_TIME" value="" />
						</form>
					</div>
				</div>

	<!-- /.main-content -->

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

jQuery(function() {
	$("#USER_NAME").val('${pd.USER_NAME}');
	$("#ART_NAME").val('${pd.ART_NAME}');
	$("#S_TIME").val('${pd.S_TIME}');
	$("#E_TIME").val('${pd.E_TIME}');
});

function initCtrl(){
	$("#USER_NAME").val("");
	$("#ART_NAME").val("");
	$("#S_TIME").val("");
	$("#E_TIME").val("");
	search();
}
function search(){
	$("#FU_NAME").val($("#USER_NAME").val());
	$("#FA_NAME").val($("#ART_NAME").val());
	$("#FC_STIME").val($("#S_TIME").val());
	$("#FC_ETIME").val($("#E_TIME").val());
	
	$("#feedbackEditForm").attr("action", "sa/listFeedback");
	$("#feedbackEditForm").submit();
}
function delFeedback(_id, _user, _art){
	var options = {
			message: "<div class='item-row'><div class='item-label'>用户名 :</div>" + 
					 "<div class='item-value'>" + _user + "</div></div>" +
					 "<div class='item-row'><div class='item-label'>商品名称 :</div>" + 
					 "<div class='item-value'>" + _art + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗？</div></div>",
			title: "删除点评"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>sa/deleteFeedback?ID=" + _id;
			$.get(url,function(data){
				if(data.result == "success")
					search();
			});
		}
	});
}

function viewFeedback(_user, _art, _content){
	var options = {
		title: "编辑选项",
		inputs:[
			{"type": "text", "label": "用户名", "value": _user, "idx": "user" },
			{"type": "text", "label": "商品名称", "value": _art, "idx": "art" },
			{"type": "textarea", "label": "点评内容", "value": _content, "idx": "feedback" }
		],
		btnType: 1
	};
	bootbox.prompt(options, function(result) {
		if(result) {}
	});
	
}

$('.date-picker').datepicker({ format: 'yyyy-mm-dd', date: new Date()});

</script>
