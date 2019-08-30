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
					<html lang="en">

					<head>
						<base href="<%=basePath%>">
						<link rel="stylesheet" href="static/ace/css/chosen.css" />
						<%@ include file="../index/top.jsp"%>
							<link rel="stylesheet" href="static/ace/css/datepicker.css" />
							<link rel="stylesheet" type="text/css" href="static/global/css/mycomponents.css">
					</head>

					<body class="no-skin">
						<div class="page-header">
							<ul class="navi">
								<li><a>旅游管理</a><span> ≫ </span></li>
								<li><a target="mainFrame" href="tour/listTourOption">旅游选项管理</a><span> ≫ </span></li>
								<li><a target="mainFrame" href="tour/listOptVal/?MID=${pd.ID}">选项值</a></li>
							</ul>
						</div>
						
						<div class="page-content">
							<div class="container-fluid">
								<div class="row">
									<p>
										<span style="padding: 5px 60px; text-align: left;">选项： ${pd.NAME} </span>
									</p>
								</div>
								<form action="tour/listOptVal" method="post" name="tourOptValForm" id="tourOptValForm">
									<table id="simple-table" class="table table-hover" style="margin-top: 5px;">
										<thead>
											<tr>
												<th class="center" style="width: 80px;">序号</th>
												<th class="center" style="width: 100px;">选项值</th>
												<th class="center" style="width: 400px;">描述</th>
												<th class="center" style="width: 100px;">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${not empty optionValueList}">
													<c:forEach items="${optionValueList}" var="ov" varStatus="vs">													
														<tr tr_id='${ov.ID }'>
															<td class="center ov_no">${vs.index+1}</td>
															<td value='${ov.NAME }' class="center ov_name">${ov.NAME }</td>
															<td value='${ov.COMMENT }' class="center ov_comment">${ov.COMMENT }</td>
															<td class="center" style="width: 100px !important;">
																<div class="btn-group">
																	<a class="btn btn-minier btn-white btn_m_edit" style="color: #2283c5 !important;">编辑</a>
																	<a class="btn btn-minier btn-white btn_m_delete" style="color: #2283c5 !important;">删除</a>
																</div>
															</td>
														</tr>
													</c:forEach>
												</c:when>
											</c:choose>
											<tr id="tr_add">
												<td colspan="12" class="center">
													<a class="btn btn-primary" onclick="addItem();">
														<span style="padding-right: 8px">+</span>新增</a>
												</td>
											</tr>
										</tbody>
									</table>
									<div class="position-relative">
										<table style="width: 100%;">
											<tr>
												<td style="vertical-align: middle;">
													<div class="pagination" style="float: left; padding-top: 0px; margin-top: 0px;">${page.pageStr}</div>
												</td>
											</tr>
										</table>
									</div>
								</form>
								<form action="tour/saveOptVal" style="display: none;" method="post" name="tourOptValEditForm" id="tourOptValEditForm">
									<input type="text" id="OV_ID" name="ID" value="" />
									<input type="text" id="OV_MID" name="MID" value="" />
									<input type="text" id="OV_NAME" name="NAME" value="" />
									<input type="text" id="OV_COMMENT" name="COMMENT" value="" />
									<input type="text" id="OV_DISP" name="DISP" value="" />
								</form>
							</div>
							<!-- /.row -->
						</div>
						
						<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
							<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
						</a>

						<!-- basic scripts -->
						<%@ include file="../index/foot.jsp"%>
							<script src="static/ace/js/bootbox.js"></script>
							<!-- ace scripts -->
							<script src="static/ace/js/ace/ace.js"></script>
							<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
							<script src="static/ace/js/chosen.jquery.js"></script>
							<script type="text/javascript" src="static/js/jquery.tips.js"></script>
					</body>

					<script type="text/javascript">
						$(top.hangge());

						$(function () {
							$("#simple-table").on('click', ".btn_m_edit", function () {
								var ind = 0;
								$(this).parent().parent().parent().find('td').each(
									function () {
										if (ind == 1) {
											$(this).attr("class", "center padding0 ov_name");
											$(this) .html("<input type='text' class='form-control' value='" + $(this).attr("value") + "'/>");
										} else if (ind == 2) {
											$(this).attr("class", "center padding0 ov_comment");
											$(this).html("<input type='text' class='form-control' value='" + $(this).attr("value")+ "'/>");
										}
										ind++;
									});
								var str = "<a class='btn btn-xs btn-success btn_m_edit' title='编辑' >"
									+ "<i class='ace-icon fa fa-pencil-square-o bigger-120' title='编辑'></i></a>"
									+ "<a class='btn btn-xs btn-danger' onclick=''>"
									+ "<i class='ace-icon fa fa-trash-o bigger-120' title='删除'></i></a>";
								$(this).html("保存");
								$(this).removeClass("btn_m_edit");
								$(this).addClass("btn_m_save");
								$(this).next().html("取消");
								$(this).next().attr("onclick", "");
								$(this).next().removeClass("btn_m_delete");
								$(this).next().addClass("btn_m_cancel");
							});

							$("#simple-table").on('click', ".btn_m_delete", function () {
									var id = $(this).parent().parent().parent().attr("tr_id");
									var mid = '${pd.ID}';
									
									var options = {
											message: "<p><span class='item-info'>您确定删除吗?</span></p>",
											title: "删除选项值"
									}
									bootbox.confirm(options, function(result) {
										if(result) {
											top.jzts();
											$("#OV_ID").val(id);
											$("#OV_MID").val(mid);
											$("#tourOptValEditForm").attr("action", "tour/deleteOptVal" + "?" + $("#tourOptValForm").serialize());
											$("#tourOptValEditForm").submit();
										}
									});
								});

							$("#simple-table").on('click', ".btn_m_save", function () {
									var id = $(this).parent().parent().parent().attr("tr_id");
									var values = [];
									var ind = 0;
									$(this).parent().parent().parent().find('td').each(
										function () {
											values[ind] = $(this).find('input').val();
											ind++;
										});

									var name = values[1];
									var comment = values[2];
									var mid = '${pd.ID}';
									var tt = $(this);
									$.ajax({
										type: "POST",
										url: 'tour/hasOptVal',
										data: { NAME: name, COMMENT: comment, ID: id },
										dataType: 'json',
										cache: false,
										success: function (data) {
											if ("success" == data.result) {
												$(this).html("编辑");
												$(this).removeClass("btn_m_save");
												$(this).addClass("btn_m_edit");
												$(this).next().html("删除");
												$(this).next().removeClass("btn_m_cancel");
												$(this).next().addClass("btn_m_delete");

												$("#OV_ID").val(id);
												$("#OV_MID").val(mid);
												$("#OV_NAME").val(name);
												$("#OV_COMMENT").val(comment);

												$("#tourOptValEditForm").attr( "action", "tour/saveOptVal" + "?" + $("#tourOptValForm").serialize());
												$("#tourOptValEditForm").submit();
											} else {
												var f = tt.parent().parent().parent().find( ".ov_name");
												f.tips({
													side: 3,
													msg: 'Already Exists Item',
													bg: '#AE81FF',
													time: 2
												});
												f.focus();
												f.css("background-color", "white");
											}
										}
									});

								});

							$("#simple-table").on('click', ".btn_m_cancel", function () {
								var ind = 0;
								$(this).parent().parent().parent().find('td').each(function () {
									$(this).attr("class", "center");
									$(this).html($(this).attr("value"));
									ind++;
								});
								$(this).html("删除");
								$(this).removeClass("btn_m_cancel");
								$(this).addClass("btn_m_delete");
								$(this).prev().html("编辑");
								$(this).prev().removeClass("btn_m_save");
								$(this).prev().addClass("btn_m_edit");
							});

							if (!ace.vars['touch']) {
								$('.chosen-select').chosen({
									allow_single_deselect: true
								});
								$(window).off('resize.chosen').on('resize.chosen', function () {
									$('.chosen-select').each(function () {
										var $this = $(this);
										if ($this.parent().width() > 0) {
											$this.next().css({
												'width': $this.parent().width()
											});
										} else {
											$this.next().css({
												'width': '200px'
											});
										}
									});
								}).trigger('resize.chosen');
								$(document).on('settings.ace.chosen',
									function (e, event_name, event_val) {
										if (event_name != 'sidebar_collapsed')
											return;
										$('.chosen-select').each(function () {
											var $this = $(this);
											$this.next().css({
												'width': $this.parent().width()
											});
										});
									});
								$('#chosen-multiple-style .btn').on('click', function (e) {
									var target = $(this).find('input[type=radio]');
									var which = parseInt(target.val());
									if (which == 2)
										$('#form-field-select-4').addClass('tag-input-style');
									else
										$('#form-field-select-4').removeClass('tag-input-style');
								});
							}
							var active_class = 'active';
						});

						function save() {
							$("#tourOptValEditForm").submit();
						}

						function addData() {
							var values = [];
							var ind = 0;
							$("#tr_add").find('td').each(function () {
								values[ind] = $(this).find('input').val();
								ind++;
							});
							var name = values[1];
							var comment = values[2];
							var id = '';
							var mid = '${pd.ID}';
							var disp = ${(page.currentPage -1) * page.showCount + numStds} +  1;
							var tt = $(this);
							$.ajax({
								type: "POST",
								url: 'tour/hasOptVal',
								data: { NAME: name, COMMENT: comment, ID: id },
								dataType: 'json',
								cache: false,
								success: function (data) {
									if ("success" == data.result) {
										$("#OV_ID").val(id);
										$("#OV_NAME").val(name);
										$("#OV_MID").val(mid);
										$("#OV_COMMENT").val(comment);
										$("#OV_DISP").val(disp);
										
										$("#tourOptValEditForm").attr( "action", "tour/saveOptVal" + "?" + $("#tourOptValForm").serialize());
										$("#tourOptValEditForm").submit();
									} else {
										var f = tt.parent().parent().parent()
											.find(".ov_name");
										f.tips({
											side: 3,
											msg: 'Already Exists Item',
											bg: '#AE81FF',
											time: 2
										});
										f.focus();
										f.css("background-color", "white");
									}
								}
							});
						}

						function addItem() {
							var strHtml = "<td class='center padding0 ov_no'></td>"
								+ "<td class='center padding0 ov_name'><input type='text' class='form-control'/></td>"
								+ "<td class='center padding0 ov_comment'><input type='text' class='form-control'/></td>"
								+ "<td class='center' style='width:100px !important;'>"
								+ "<a class='btn btn-minier btn-white' onclick='addData()'  style='color:#2283c5 !important;'>保存</a>"
								+ "<a class='btn btn-minier btn-white' onclick='addCancel()' style='color:#2283c5 !important;'>取消</a></td>";
							$("#tr_add").html(strHtml);
						}

						function addCancel() {
							var strHtml = "<td colspan=\"12\" class=\"center\"><a class=\"btn btn-primary\" onclick=\"addItem();\"><span style=\"padding-right: 8px\">+</span>新增</a></td>";
							$("#tr_add").html(strHtml);
						}
					</script>

					</html>