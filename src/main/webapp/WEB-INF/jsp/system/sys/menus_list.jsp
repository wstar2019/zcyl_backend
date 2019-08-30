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

<style>
.btn.btn-app.btn-xs {
    width: 64px;
    font-size: 12px;
    border-radius: 6px;
    padding-bottom: 2px;
    padding-top: 2px;
    line-height: 1.45;
}

.jstree-node > a > i {
	background-color : yellow;
}

#LEVEL1 {
	margin-left : 20px;
}

#LEVEL2 {
	margin-left : 40px;
}

#LEVEL3 {
	margin-left : 60px;
}

#LEVEL4 {
	margin-left : 80px;
}

#LEVEL5 {
	margin-left : 100px;
}

#LEVEL6 {
	margin-left : 120px;
}

</style>
</head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>系统管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sys/listMenus">菜单管理</a></li>
		</ul>
	</div>
	<div class="page-content" style="padding-left: 13px;">
		<div class="container-fluid">						
			<div class="tabbable">
				<ul class="nav nav-tabs" id="myTab">						
					<li class="active" id="tab_name">
						<a data-toggle="tab" href="#nameJ">
							<i class="green icon-home bigger-110"></i>
							菜单列表
						</a>
					</li>
					<li class="" id="tab_add">
						<a data-toggle="tab" href="#addJ">
							菜单添加
						</a>
					</li>								
				</ul>
				<div class="tab-content">
					<div id="nameJ" class="tab-pane active">
						<table id="simple-table" class="table table-striped table-bordered table-hover"  style="margin-top:5px;">
							<thead>
								<tr>
									<th class="center">菜单名称</th>									
									<th class="center" style="width:250px;">链接</th>
									<th class="center" style="width:100px;">排序</th>
									<th class="center" style="width:100px;">可见</th>
									<th class="center" style="width:100px;">权限表示</th>
									<th class="center" style="width:230px;">操作</th>
								</tr>
							</thead>
							<tbody>								
								<c:choose>								
								<c:when test="${not empty menusList}">									
									<c:forEach items="${menusList}" var="menusList" varStatus="vs">										
										<tr disp="true" last="false" expanded="true" level=${menusList.LEVEL}
											tr_id='${menusList.MENU_ID}' menu_tmp_name='${menusList.MENU_NAME}' class="menu_row">
											<td class="vcol1 td_cursor" style="text-align:left; color:#37a7e8;">
												<i
												<c:if test="${menusList.MENU_URL == '#' }">
													class="fa fa-caret-down btn_cursor"
												</c:if> 
												 id='LEVEL${menusList.LEVEL}'></i>${menusList.MENU_NAME }
											</td>
											<td class="center vcol2">${menusList.MENU_URL }</td>
											<td class="center vcol4">${menusList.MENU_ORDER }</td>
											<td class="center vcol6_name">
												<c:if test="${menusList.MENU_STATE == 1 }">
													显示
												</c:if>
												<c:if test="${menusList.MENU_STATE == 0 }">
													隐藏
												</c:if>
											</td>
											<td class="center vcol8">${menusList.PERMISION }</td>
											<td class="center vcol6" style="display:none">${menusList.MENU_STATE }</td>
											<td class="center vcol3" style="display:none">${menusList.PARENT_ID }</td>
											<td class="center vcol3_name" style="display:none">${menusList.PARENT_NAME }</td>
											<td class="center vcol5" style="display:none">${menusList.MENU_ICON }</td>
											<td class="center vcol7" style="display:none">${menusList.MENU_TARGET }</td>
											<td class="center vcol9" style="display:none">${menusList.MENU_COMMENT }</td>
											<td class="center">												
												<div class="btn-group">
													<a class="btn btn-minier btn-white btn_m_edit" style="color:#2283c5 !important;">修改</a>
													<a class="btn btn-minier btn-white btn_m_delete" onclick="delMenus('${menusList.MENU_ID }','${menusList.MENU_NAME }');" style="color:#2283c5 !important;">删除</a>	
													<a class="btn btn-minier btn-white btn_m_add" style="color:#2283c5 !important;">添加下级菜单</a>
												</div>												
											</td>
										</tr>
									</c:forEach>
								</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
					<div id="addJ" class="tab-pane">
						<form action="sys/saveMenus" name="saveForm" id="saveForm" method="post">			
							<input type="hidden" name="MENU_ID" id="r_add_id" value=""/>
							<div id="zhongxin" style="padding-top: 13px;">
								<table id="table_add" class="table table-striped table-bordered table-hover">				
									<tr>								
										<td style="width:120px;text-align: right;padding-top: 22px;">上级菜单:</td>
										<td>
											<div class="input-group input-xlarge margin-top-10">
												<input id="r_add_col3_id" style="display:none;" 
													name = "PARENT_ID" type="text" class="form-control" placeholder="">
												<input id="r_add_col3" type="text" class="form-control" placeholder="没有输入表示最顶级菜单" 
													style="border-radius:0px!important;" readonly>
												
												<span class="input-group-addon" style="border-top-right-radius: 8px !important;border-bottom-right-radius: 8px !important;">
													<i style="color:#37a7e8;" class="fa fa-search btn_search_region" data-toggle="modal" data-target="#myModal"></i>
												</span>
											</div>
										</td>
									</tr>
									<tr>
										<td style="width:120px;text-align: right;padding-top: 13px;">名称:</td>
										<td style="color:red;">
											<input id="r_add_col1" class="input-xlarge"
												type="text" name = "MENU_NAME" value=""/> *</td>
									</tr>
									<tr>
										<td style="width:120px;text-align: right;padding-top: 13px;">链接:</td>
										<td style="color:red;">
											<input id="r_add_col2" class="input-xlarge" type="text"
												name="MENU_URL" placeholder="没有链接, 请输入#" value=""/> *
										</td>
	
									</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">目标:</td>
									<td><input id="r_add_col7" class="input-xlarge" type="text" name = "MENU_TARGET" value=""/>      链接地址打开的目标窗口</td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">图标:</td>
									<td>
										<input id="r_add_col5" style="display:none;" name="MENU_ICON" type="text" 
											class="form-control" placeholder="" value="">
									</td>
									<td>
										<a class="btn btn-mini btn-danger" style="height:32px;align:center;" data-toggle="modal" data-target="#myIconModal">选择</a>
									</td>									
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">排序:</td>
									<td><input id="r_add_col4" class="input-large" type="text" name = "MENU_ORDER" value=""/>排序顺序：升序</td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">可见:</td>
									<td style="color:red">
										<select class="chosen-select form-control" name="MENU_STATE" id="r_add_col6"
											data-placeholder="" style="vertical-align:top;width: 50px;">			
											<option value="1">显示</option>
											<option value="0">隐藏</option>
										</select>
									</td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 13px;">权限标识:</td>
									<td><input id="r_add_col8" class="input-large" type="text" name = "MENU_PERMISION" value=""/>      控制器中定义的权限标识，如：@RequestPermission</td>
								</tr>
								<tr>
									<td style="width:120px;text-align: right;padding-top: 35px;">备注:</td>
									<td>
									<div class="form-group">
										<textarea id="r_add_col9" name = "MENU_COMMENT" class="input-xlarge" rows="3"></textarea>
									</div>
									</td>
								</tr>
								<tr>
									<td style="text-align: center;" colspan="10">
										<a class="btn btn-mini btn-primary" id="r_btn_save" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" id="r_btn_cancel" onclick="top.Dialog.close();">取消</a>
									</td>
								</tr>
							</table>
							</div>
							<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green"></h4></div>
						</form>
					</div>
					</div>
					
					
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document" style="width:350px">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h3 class="modal-title" id="myModalLabel" style="font-weight:bold">选择菜单</h3>
					      </div>
					      <div class="modal-body">
							<table style="margin-top:5px;">
								<tr>
									<td>
										<div class="nav-search">
										关键字: 
										<span class="input-icon">										
											<input class="nav-search-input" autocomplete="off" id="nav-search-input" type="text" name="keywords" value="${pd.keywords }" placeholder="输入关键字" />										
											<i class="ace-icon fa fa-search nav-search-icon"></i>								
										</span>
										<input id="strTree" value="${strTree }" style="display:none;"/>
										</div>
									</td>
									
									<c:if test="${QX.cha == 1 }">
									<td style="vertical-align:top;padding-left:2px;"><a class="btn btn-app btn-primary btn-xs" onclick="searchs();"  title="??"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon"> 查询</i></a></td>																	
									</c:if>
								</tr>
							</table>
							<div id="tree_1" class="tree-demo">
							</div>	      						      
					      </div>
					      <div class="modal-footer">
					      	<button type="button" class="btn btn-primary btn_modal_confirm" data-dismiss="modal">确定</button>
					        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>					        
					      </div>
					    </div>
					  </div>
					</div>

					<div class="modal fade" id="myIconModal" tabindex="-1" role="dialog" aria-labelledby="myIconModalLabel">
					  <div class="modal-dialog" role="document" style="width:800px">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h3 class="modal-title" id="myModalLabel" style="font-weight:bold">Web Application Icons</h3>
					      </div>
					      <div class="modal-body">
							<div class="row margin-bottom-20">
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-arrow-circle-o-right"></i> fa-arrow-circle-o-right
								</div>								
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-adjust"></i> fa-adjust
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-anchor"></i> fa-anchor
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-archive"></i> fa-archive
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-arrows"></i> fa-arrows
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-arrows-h"></i> fa-arrows-h
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-arrows-v"></i> fa-arrows-v
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-asterisk"></i> fa-asterisk
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-automobile"></i> fa-automobile <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-ban"></i> fa-ban
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bank"></i> fa-bank <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bar-chart-o"></i> fa-bar-chart-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-barcode"></i> fa-barcode
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bars"></i> fa-bars
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-beer"></i> fa-beer
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bell"></i> fa-bell
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bell-o"></i> fa-bell-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bolt"></i> fa-bolt
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bomb"></i> fa-bomb
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-book"></i> fa-book
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bookmark"></i> fa-bookmark
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bookmark-o"></i> fa-bookmark-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-briefcase"></i> fa-briefcase
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bug"></i> fa-bug
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-building"></i> fa-building
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-building-o"></i> fa-building-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bullhorn"></i> fa-bullhorn
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-bullseye"></i> fa-bullseye
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cab"></i> fa-cab <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-calendar"></i> fa-calendar
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-calendar-o"></i> fa-calendar-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-camera"></i> fa-camera
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-camera-retro"></i> fa-camera-retro
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-car"></i> fa-car
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-caret-square-o-down"></i> fa-caret-square-o-down
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-caret-square-o-left"></i> fa-caret-square-o-left
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-caret-square-o-right"></i> fa-caret-square-o-right
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-caret-square-o-up"></i> fa-caret-square-o-up
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-certificate"></i> fa-certificate
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-check"></i> fa-check
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-check-circle"></i> fa-check-circle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-check-circle-o"></i> fa-check-circle-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-check-square"></i> fa-check-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-check-square-o"></i> fa-check-square-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-child"></i> fa-child
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-circle"></i> fa-circle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-circle-o"></i> fa-circle-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-circle-o-notch"></i> fa-circle-o-notch
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-circle-thin"></i> fa-circle-thin
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-clock-o"></i> fa-clock-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cloud"></i> fa-cloud
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cloud-download"></i> fa-cloud-download
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cloud-upload"></i> fa-cloud-upload
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-code"></i> fa-code
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-code-fork"></i> fa-code-fork
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-coffee"></i> fa-coffee
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cog"></i> fa-cog
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cogs"></i> fa-cogs
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-comment"></i> fa-comment
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-comment-o"></i> fa-comment-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-comments"></i> fa-comments
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-comments-o"></i> fa-comments-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-compass"></i> fa-compass
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-credit-card"></i> fa-credit-card
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-crop"></i> fa-crop
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-crosshairs"></i> fa-crosshairs
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cube"></i> fa-cube
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cubes"></i> fa-cubes
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-cutlery"></i> fa-cutlery
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-dashboard"></i> fa-dashboard <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-database"></i> fa-database
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-desktop"></i> fa-desktop
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-dot-circle-o"></i> fa-dot-circle-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-download"></i> fa-download
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-edit"></i> fa-edit <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-ellipsis-h"></i> fa-ellipsis-h
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-ellipsis-v"></i> fa-ellipsis-v
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-envelope"></i> fa-envelope
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-envelope-o"></i> fa-envelope-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-envelope-square"></i> fa-envelope-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-eraser"></i> fa-eraser
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-exchange"></i> fa-exchange
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-exclamation"></i> fa-exclamation
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-exclamation-circle"></i> fa-exclamation-circle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-exclamation-triangle"></i> fa-exclamation-triangle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-external-link"></i> fa-external-link
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-external-link-square"></i> fa-external-link-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-eye"></i> fa-eye
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-eye-slash"></i> fa-eye-slash
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-fax"></i> fa-fax
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-female"></i> fa-female
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-fighter-jet"></i> fa-fighter-jet
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-archive-o"></i> fa-file-archive-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-audio-o"></i> fa-file-audio-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-code-o"></i> fa-file-code-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-excel-o"></i> fa-file-excel-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-image-o"></i> fa-file-image-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-movie-o"></i> fa-file-movie-o <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-pdf-o"></i> fa-file-pdf-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-photo-o"></i> fa-file-photo-o <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-picture-o"></i> fa-file-picture-o <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-powerpoint-o"></i> fa-file-powerpoint-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-sound-o"></i> fa-file-sound-o <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-video-o"></i> fa-file-video-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-word-o"></i> fa-file-word-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-file-zip-o"></i> fa-file-zip-o <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-film"></i> fa-film
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-filter"></i> fa-filter
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-fire"></i> fa-fire
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-fire-extinguisher"></i> fa-fire-extinguisher
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-flag"></i> fa-flag
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-flag-checkered"></i> fa-flag-checkered
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-flag-o"></i> fa-flag-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-flash"></i> fa-flash <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-flask"></i> fa-flask
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-folder"></i> fa-folder
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-folder-o"></i> fa-folder-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-folder-open"></i> fa-folder-open
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-folder-open-o"></i> fa-folder-open-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-frown-o"></i> fa-frown-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-gamepad"></i> fa-gamepad
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-gavel"></i> fa-gavel
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-gear"></i> fa-gear <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-gears"></i> fa-gears <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-gift"></i> fa-gift
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-glass"></i> fa-glass
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-globe"></i> fa-globe
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-graduation-cap"></i> fa-graduation-cap
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-group"></i> fa-group <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-hdd-o"></i> fa-hdd-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-headphones"></i> fa-headphones
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-heart"></i> fa-heart
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-heart-o"></i> fa-heart-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-history"></i> fa-history
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-home"></i> fa-home
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-image"></i> fa-image <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-inbox"></i> fa-inbox
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-info"></i> fa-info
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-info-circle"></i> fa-info-circle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-institution"></i> fa-institution <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-key"></i> fa-key
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-keyboard-o"></i> fa-keyboard-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-language"></i> fa-language
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-laptop"></i> fa-laptop
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-leaf"></i> fa-leaf
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-legal"></i> fa-legal <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-lemon-o"></i> fa-lemon-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-level-down"></i> fa-level-down
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-level-up"></i> fa-level-up
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-life-bouy"></i> fa-life-bouy <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-life-ring"></i> fa-life-ring
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-life-saver"></i> fa-life-saver <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-lightbulb-o"></i> fa-lightbulb-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-location-arrow"></i> fa-location-arrow
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-lock"></i> fa-lock
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-magic"></i> fa-magic
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-magnet"></i> fa-magnet
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-mail-forward"></i> fa-mail-forward <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-mail-reply"></i> fa-mail-reply <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-mail-reply-all"></i> fa-mail-reply-all <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-male"></i> fa-male
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-map-marker"></i> fa-map-marker
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-meh-o"></i> fa-meh-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-microphone"></i> fa-microphone
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-microphone-slash"></i> fa-microphone-slash
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-minus"></i> fa-minus
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-minus-circle"></i> fa-minus-circle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-minus-square"></i> fa-minus-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-minus-square-o"></i> fa-minus-square-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-mobile"></i> fa-mobile
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-mobile-phone"></i> fa-mobile-phone <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-money"></i> fa-money
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-moon-o"></i> fa-moon-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-mortar-board"></i> fa-mortar-board <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-music"></i> fa-music
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-navicon"></i> fa-navicon <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-paper-plane"></i> fa-paper-plane
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-paper-plane-o"></i> fa-paper-plane-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-paw"></i> fa-paw
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-pencil"></i> fa-pencil
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-pencil-square"></i> fa-pencil-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-pencil-square-o"></i> fa-pencil-square-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-phone"></i> fa-phone
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-phone-square"></i> fa-phone-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-photo"></i> fa-photo <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-picture-o"></i> fa-picture-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-plane"></i> fa-plane
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-plus"></i> fa-plus
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-plus-circle"></i> fa-plus-circle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-plus-square"></i> fa-plus-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-plus-square-o"></i> fa-plus-square-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-power-off"></i> fa-power-off
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-print"></i> fa-print
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-puzzle-piece"></i> fa-puzzle-piece
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-qrcode"></i> fa-qrcode
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-question"></i> fa-question
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-question-circle"></i> fa-question-circle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-quote-left"></i> fa-quote-left
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-quote-right"></i> fa-quote-right
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-random"></i> fa-random
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-recycle"></i> fa-recycle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-refresh"></i> fa-refresh
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-reorder"></i> fa-reorder <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-reply"></i> fa-reply
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-reply-all"></i> fa-reply-all
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-retweet"></i> fa-retweet
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-road"></i> fa-road
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-rocket"></i> fa-rocket
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-rss"></i> fa-rss
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-rss-square"></i> fa-rss-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-search"></i> fa-search
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-search-minus"></i> fa-search-minus
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-search-plus"></i> fa-search-plus
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-send"></i> fa-send <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-send-o"></i> fa-send-o <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-share"></i> fa-share
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-share-alt"></i> fa-share-alt
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-share-alt-square"></i> fa-share-alt-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-share-square"></i> fa-share-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-share-square-o"></i> fa-share-square-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-shield"></i> fa-shield
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-shopping-cart"></i> fa-shopping-cart
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sign-in"></i> fa-sign-in
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sign-out"></i> fa-sign-out
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-signal"></i> fa-signal
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sitemap"></i> fa-sitemap
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sliders"></i> fa-sliders
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-smile-o"></i> fa-smile-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort"></i> fa-sort
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-alpha-asc"></i> fa-sort-alpha-asc
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-alpha-desc"></i> fa-sort-alpha-desc
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-amount-asc"></i> fa-sort-amount-asc
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-amount-desc"></i> fa-sort-amount-desc
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-asc"></i> fa-sort-asc
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-desc"></i> fa-sort-desc
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-down"></i> fa-sort-down <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-numeric-asc"></i> fa-sort-numeric-asc
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-numeric-desc"></i> fa-sort-numeric-desc
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sort-up"></i> fa-sort-up <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-space-shuttle"></i> fa-space-shuttle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-spinner"></i> fa-spinner
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-spoon"></i> fa-spoon
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-square"></i> fa-square
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-square-o"></i> fa-square-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-star"></i> fa-star
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-star-half"></i> fa-star-half
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-star-half-empty"></i> fa-star-half-empty <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-star-half-full"></i> fa-star-half-full <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-star-half-o"></i> fa-star-half-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-star-o"></i> fa-star-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-suitcase"></i> fa-suitcase
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-sun-o"></i> fa-sun-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-support"></i> fa-support <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-tablet"></i> fa-tablet
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-tachometer"></i> fa-tachometer
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-tag"></i> fa-tag
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-tags"></i> fa-tags
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-tasks"></i> fa-tasks
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-taxi"></i> fa-taxi
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-terminal"></i> fa-terminal
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-thumb-tack"></i> fa-thumb-tack
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-thumbs-down"></i> fa-thumbs-down
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-thumbs-o-down"></i> fa-thumbs-o-down
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-thumbs-o-up"></i> fa-thumbs-o-up
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-thumbs-up"></i> fa-thumbs-up
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-ticket"></i> fa-ticket
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-times"></i> fa-times
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-times-circle"></i> fa-times-circle
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-times-circle-o"></i> fa-times-circle-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-tint"></i> fa-tint
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-toggle-down"></i> fa-toggle-down <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-toggle-left"></i> fa-toggle-left <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-toggle-right"></i> fa-toggle-right <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-toggle-up"></i> fa-toggle-up <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-trash-o"></i> fa-trash-o
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-tree"></i> fa-tree
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-trophy"></i> fa-trophy
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-truck"></i> fa-truck
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-umbrella"></i> fa-umbrella
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-university"></i> fa-university
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-unlock"></i> fa-unlock
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-unlock-alt"></i> fa-unlock-alt
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-unsorted"></i> fa-unsorted <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-upload"></i> fa-upload
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-user"></i> fa-user
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-users"></i> fa-users
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-video-camera"></i> fa-video-camera
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-volume-down"></i> fa-volume-down
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-volume-off"></i> fa-volume-off
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-volume-up"></i> fa-volume-up
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-warning"></i> fa-warning <span class="text-muted">(alias)</span>
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-wheelchair"></i> fa-wheelchair
								</div>
								<div class="fa-item col-md-3 col-sm-4">
									<i class="fa fa-wrench"></i> fa-wrench
								</div>
							</div>
					      </div>
					      <div class="modal-footer">
					      	<button type="button" class="btn btn-primary btn_modal_icon_confirm" data-dismiss="modal">确定</button>
					        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>					        
					      </div>
					    </div>
					  </div>
					</div>

					</div>
					
					</div>
					</div>
						<!-- /.col -->

			
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<%@ include file="../index/foot.jsp"%>
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<script src="static/ace/js/chosen.jquery.js"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	
	<script src="static/global/plugins/jstree/dist/jstree.js"></script>
	</body>

<script type="text/javascript">
$(top.hangge());

function save(){
	if($("#r_add_col1").val() == ""){
		$("#r_add_col1").tips({
			side:3,
            msg:'请输入菜单名称',
            bg:'#AE81FF',
            time:2
        });
		$("#r_add_col1").focus();
		$("#r_add_col1").val('');
		$("#r_add_col1").css("background-color","white");		
		return false;
	}
	if($("#r_add_col2").val() == ""){
		$("#r_add_col2").tips({
			side:3,
            msg:'请输入链接',
            bg:'#AE81FF',
            time:2
        });
		$("#r_add_col2").focus();
		$("#r_add_col2").val('');
		$("#r_add_col2").css("background-color","white");		
		return false;
	}
	
	var menu_name = $("#r_add_col1").val();
	var parent_id = $("#r_add_col3_id").val();
	var this_id = $("#r_add_id").val();

	if($("#r_add_id").val() == ""){		
		$.ajax({
			type: "POST",
			url: '<%=basePath%>sys/hasMenus',
	    	data: {MENU_NAME:menu_name, PKID:this_id, PARENT_ID:parent_id},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){								
					$("#saveForm").attr("action", "sys/saveMenus");
					$("#saveForm").submit();
					$("#zhongxin").hide();
					$("#zhongxin2").show();
				 }else{
					$("#r_add_col1").tips({
						side:3,
			            msg:'此菜单名已存在!',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#r_add_col1").focus();
					$("#r_add_col1").css("background-color","white");	
				 }
			}
		});	
	} 
	else
	{
		$.ajax({
			type: "POST",
			url: '<%=basePath%>sys/hasMenus',
	    	data: {MENU_NAME:menu_name, PKID:this_id, PARENT_ID:parent_id},
			dataType:'json',
			cache: false,
			success: function(data){
				 if("success" == data.result){								
						$("#saveForm").attr("action", "sys/updateMenus");
						$("#saveForm").submit();
						$("#tab_add").find("a").text("菜单添加");
						$("#r_btn_save").val("保存");

						$("#zhongxin").hide();
						$("#zhongxin2").show();
				 }else{
					$("#r_add_col1").tips({
						side:3,
			            msg:'此菜单名已存在!',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#r_add_col1").focus();
					$("#r_add_col1").css("background-color","white");	
				 }
			}
		});	
	}
}

function delMenus(_id, _name){
	var options = {
			message: "<div class='item-row'><div class='item-label'>菜单名称 :</div>" + 
					 "<div class='item-value'>" + _name + "</div></div>" +
					 "<div class='item-row'><div class='item-info'>您确定删除吗?</div></div>",
			title: "删除菜单"
	}
	bootbox.confirm(options, function(result) {
		if(result) {
			top.jzts();
			$("#r_add_id").val(statId);
			$("#saveForm").attr("action", "sys/deleteMenus");
			$("#saveForm").submit();
		}
	});
}

$(function() {	
	
	var tr_id="";	
	
	var _tree = "<div id='tree_1' class='tree-demo'>" + $("#strTree").val() + "</div>" 		
	$("#tree_1").html(_tree);
	
	$(".btn_modal_confirm").on('click', function(){		
		$("#r_add_col3").val($(".jstree-clicked")[0].innerText);
		$("#r_add_col3_id").val($(".jstree-clicked").parent().attr("tr_id"));
	});
	
	$(".fa-item").on('click', function(){
		$("#r_add_col5_name").removeClass($("#r_add_col5_name").attr("class"));
		$("#r_add_col5_name").addClass("fa " + jQuery.trim($(this).text()) + " blue");

		var icon = $("#r_add_col5_name").attr("class");
		$("#r_add_col5").val("menu-icon " + icon);
	});


	$("tr.menu_row").each(function(index)
	{		
		//if($(this).attr("level") != "0")
		//	$(this).css("display", "none");		
		
		if(parseInt($(this).attr("level")) + 1 != parseInt($(this).next().attr("level")))
		{			
			$(this).find("i.btn_cursor").removeClass("fa fa-caret-right btn_cursor");
			$(this).find("td.td_cursor").css("color", "black");

			$(".tree" + $(this).attr("tr_id")).attr("data-jstree", "{ \"type\" : \"file\" , \"icon\" : \"fa fa-file icon-state-success\"}");
		}
	});			
	$("#simple-table").find("tr.menu_row:last-child").attr("last", "true");
	
	
	$("#table_add").on('click', ".btn_search_region", function(){
		
	});
	
	$("#simple-table").on('click', ".td_cursor", function(){
		var node = $(this).parent();

		if(node.attr("last") == "true")
			return;
		var cursor_icon = $(this).find(".btn_cursor");

		var _level = parseInt(node.attr("level"));
		
		if(node.attr("expanded") == "false")
		{
			node.attr("expanded", "true");
			if(cursor_icon.length != "0")
				$(this).css("color", "#37a7e8");
		}
		else
		{
			node.attr("expanded", "false");			
			if(cursor_icon.length != "0")
				$(this).css("color", "black");
		}
		
		var _expanded = node.attr("expanded"); 
		if(_expanded == "true")
		{
			cursor_icon.removeClass("fa fa-caret-right btn_cursor");	
			cursor_icon.addClass("fa fa-caret-down btn_cursor");
		}
		else
		{
			cursor_icon.removeClass("fa fa-caret-down btn_cursor");	
			cursor_icon.addClass("fa fa-caret-right btn_cursor");
		}		
		var i = 0;
				
		while(1)
		{
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
		//console.log(cursor_icon);
	});
	
	var nodeclass;
	var icon_id;
	
	$("#simple-table").on('click',".btn_m_edit", function(){
		$("#tab_name").removeClass("active");
		$("#tab_name").find("a").attr("aria-expanded", "false");
		$("#tab_add").addClass("active");
		$("#tab_add").find("a").text("菜单修改");
		$("#tab_add").find("a").attr("aria-expanded", "true");
		$("#nameJ").removeClass("active");
		$("#addJ").addClass("active");

		$("#r_btn_save").text("修改");

		var node_row = $(this).parent().parent().parent();
		$("#r_add_id").val(node_row.attr("tr_id"));

		$("#r_add_col1").val(node_row.find(".vcol1").text().trim());		
		$("#r_add_col2").val(node_row.find(".vcol2").text().trim());
		
		$("#r_add_col3_id").val(node_row.find(".vcol3").text().trim());
		$("#r_add_col3").val(node_row.find(".vcol3_name").text().trim());
		
		$("#r_add_col4").val(node_row.find(".vcol4").text().trim());
		$("#r_add_col5").val(node_row.find(".vcol5").text().trim());
		$("#r_add_col6").val(node_row.find(".vcol6").text().trim());
		$("#r_add_col7").val(node_row.find(".vcol7").text().trim());
		$("#r_add_col8").val(node_row.find(".vcol8").text().trim());		
		$("#r_add_col9").val(node_row.find(".vcol9").text().trim());

		$("#r_add_col5_name").removeClass($("#r_add_col5_name").attr("class"));
		$("#r_add_col5_name").addClass(node_row.find(".vcol5").text().trim());

		var state = "";
		if(node_row.find(".vcol6").text().trim() == "1") state = "显示";
		else state = "隐藏";

		$("#r_add_col6_chosen").find(".chosen-single").find("span").html(state);				
	});
	
	$("#r_btn_cancel").on('click', function(){
		$("#r_btn_save").text("保存");

		$("#tab_add").removeClass("active");
		$("#tab_add").find("a").attr("aria-expanded", "false");

		$("#tab_name").addClass("active");
		$("#tab_add").find("a").text("菜单添加");
		$("#tab_name").find("a").attr("aria-expanded", "true");
		$("#addJ").removeClass("active");
		$("#nameJ").addClass("active");
		
		$("#r_add_id").val("");
		$("#r_add_col1").val("");		
		$("#r_add_col2").val("");		
		$("#r_add_col3").val("");
		$("#r_add_col3_id").val("");
		$("#r_add_col4").val("");
		$("#r_add_col5").val("");
		$("#r_add_col6").val("");
		$("#r_add_col7").val("");
		$("#r_add_col8").val("");
		$("#r_add_col9").val("");		
	});
	
	$("#simple-table").on('click',".btn_m_add", function(){
		$("#tab_name").removeClass("active");
		$("#tab_name").find("a").attr("aria-expanded", "false");
		$("#tab_add").addClass("active");
		$("#tab_add").find("a").text("下级菜单添加");
		$("#tab_add").find("a").attr("aria-expanded", "true");
		$("#nameJ").removeClass("active");
		$("#addJ").addClass("active");

		$("#r_btn_save").text("添加");

		var node_row = $(this).parent().parent().parent();

		//$("#r_add_id").val(node_row.attr("tr_id"));
		$("#r_add_col3_id").val(node_row.attr("tr_id"));
		$("#r_add_col3").val(node_row.attr("menu_tmp_name"));
	});
		
	$("#simple-table").on('click',".btn_m_cancel", function(){
		var node = $(this).parent().parent().parent();		
		tr_id=$(this).parent().parent().parent().attr("tr_id");
		var name = $("#node1"+tr_id).val();
		
		node.find(".region_name").html("<i style='font-style: normal;' class='" + $("#node1"+tr_id).attr("i_class") + "' id='" + $("#node1"+tr_id).attr("i_id") + "'>" + $("#node1"+tr_id).val() + "</i>");
		node.find(".region_code").html($("#node2"+tr_id).attr("value"));
		node.find(".region_kind").html($("#node3"+tr_id).attr("value"));
		node.find(".region_comment").html($("#node4"+tr_id).attr("value"));
		
		$(this).html("<i class='ace-icon fa fa-trash-o bigger-120' onclick=\"del('" + tr_id + "'," + "'" + name + "');\" title='删除'></i>");		
		$(this).removeClass("btn_m_cancel");
		$(this).addClass("btn_m_delete");
		
		$(this).prev().html("<i class='ace-icon fa fa-pencil-square-o bigger-120' title='修改'></i>");
		$(this).prev().removeClass("btn_m_save");
		$(this).prev().addClass("btn_m_edit");
	});
	
	
	$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
	
	if(!ace.vars['touch']) {
		
		
		$('.chosen-select').chosen({allow_single_deselect:true}); 
		$(window)
		.off('resize.chosen')
		.on('resize.chosen', function() {
			$('.chosen-select').each(function() {				
				 var $this = $(this);
				 if($this.parent().width() > 0)
				 {
					 $this.next().css("width", 100);
				 }
				 else
				 {
					 $this.next().css("width", 100);
				 }				 
			});
		})
		.trigger('resize.chosen');
		$(document).on('settings.ace.chosen', function(e, event_name, event_val) {			
			if(event_name != 'sidebar_collapsed') return;
			$('.chosen-select').each(function() {
				 var $this = $(this);
				 $this.next().css({'width': $this.parent().width()});
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
		
});

function toExcel(){
	var keywords = $("#nav-search-input").val();
	var lastLoginStart = $("#lastLoginStart").val();
	var lastLoginEnd = $("#lastLoginEnd").val();
	var ROLE_ID = $("#role_id").val();
	window.location.href='<%=basePath%>user/excel?keywords='+keywords+'&lastLoginStart='+lastLoginStart+'&lastLoginEnd='+lastLoginEnd+'&ROLE_ID='+ROLE_ID;
}

</script>
</html>
