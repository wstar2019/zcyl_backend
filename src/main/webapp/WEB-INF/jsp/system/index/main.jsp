<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

	<!-- jsp文件头和头部 -->
	<%@ include file="top.jsp"%>
	<style type="text/css">
		.commitopacity{
			position:absolute;
			width:100%;
			height:100px;
			background:rgba(127, 127, 127, 0.43);;
			filter:alpha(opacity=50);
			-moz-opacity:0.8;
			-khtml-opacity: 0.5;
			opacity: 0.5;
			top:0px; z-index:99999;}
	
		body{
			overflow: hidden;
		}
		.btn-primary, .btn-primary:focus {
		    background-color: #37A7E8 !important;
		    border-color: #37A7E8;
		    margin-top : 1px;
		}

		.btn-primary-selected, .btn-primary-selected:focus, .btn-primary-selected:hover {
		    background-color: #1b6aaa !important;
		    border-color: #1b6aaa;
		    margin-top : 1px;
		}
		
		.btn-primary:hover {
		    background-color: #1b6aaa !important;
		    border-color: #1b6aaa !important;
		}
		
		.top-icon{
			margin-top : 15px;
			margin-right : 5px;
		}
		
		.no-skin .nav-list > li > .submenu li > .submenu li.open > a, .no-skin .nav-list > li > .submenu li > .submenu li.active > a {
		    background-color: #2b7dbc !important;
		    color: white!important;
		}
		.no-skin .nav-list > li > .submenu li > .submenu li.open > a:hover, .no-skin .nav-list > li > .submenu li > .submenu li.active > a:hover {
		    background-color: #2b7dbc !important;
		    color: white!important;
		}
		
		.no-skin .nav-list > li .submenu > li.active:not(.open) > a:hover {
		    background-color: #2b7dbc !important;
		    color: white!important;
		}
		
		.no-skin .nav-list > li .submenu > li.active:not(.open) > a {
		    background-color: #2b7dbc !important;
		    color: white!important;
		}
		
		.no-skin .nav-list > li.active:after {
		    display: block;
		    content: "";
		    position: absolute;
		    right: -2px;
		    top: -1px;
		    bottom: 0;
		    z-index: 1;
		    border: 0px solid;
		    border-width: 0 0px 0 0;
		    border-color: #2b7dbc;
		}
		
		.no-skin .nav-list > li.active > a {
		    font-weight: normal !important;
		    color: #2b7dbc;
		}
		.modal-open{
			padding-right: 0px !important;
		}
		.navi{
			margin:0px;
		}
		.page-header ul li{
			padding:0px;
			cursor:pointer;
			float:left;
		}
		
	</style>
	<link rel="stylesheet" href="static/ace/css/ace.onpage-help.css" />
</head>
	<body class="no-skin">
		<%@ include file="head.jsp"%>
		<%@ include file="left.jsp"%>		
		<div id="jzts" style="display:none; width:100%; position:absolute; z-index:99999999;">
			<div class="commitopacity" id="bkbgjz"></div>
			<div style="padding: 70px; text-align:center;">
				<div style="text-align:center;margin-top: 3px;"><img src="static/images/loadingi.gif" /> </div>
				<div style="margin-top: 6px;"><h4 class="lighter block red">&nbsp;正在处理中 ...</h4></div>
			</div>
		</div>
		<div id="content">
			<iframe name="mainFrame" id="mainFrame" src="landing" style="border:0px; margin:0 auto; width:100%; height:100%; padding-bottom:78px;"></iframe>
		</div>
		
		<div class="modal fade" id="updatePassModal" tabindex="-1" role="dialog" aria-labelledby="updatePassModalLabel">						
	  		<div class="modal-dialog" role="document" style="width:400px;margin-top:200px;">
	    		<div class="modal-content" style="border-width:0px;">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        				<span aria-hidden="true">&times;</span></button>
	        			<h4 class="modal-title" id="myModalLabel" style="font-weight:bold">修改密码</h4>
	      			</div>
	      			<div class="modal-body">
						<form class="form-horizontal">
							<input type="text" style="display:none;" id="U_AME">
							<input type="text" style="display:none;" id="U_ID">
							<div class="form-group">
								<label class="col-sm-2 control-label">旧密码:</label>
								<div class="col-sm-10 ipt-container">
									<input id="C_PASS" class="form-control" autocomplete="off" type="password">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">新密码:</label>
								<div class="col-sm-10 ipt-container">
									<input id="N_PASS" class="form-control" autocomplete="off" type="password">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">确认密码:</label>
								<div class="col-sm-10 ipt-container">
									<input id="R_PASS" class="form-control" autocomplete="off" type="password">
								</div>
							</div>
						</form>
      				</div>
	      		</div>
	      		<div class="modal-footer" style="padding: 5px 0px 10px 0px;">
	      			<button type="button" class="btn btn-primary" onclick="updatePassword()">确认</button>
	      			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>				        
	      		</div>
	    	</div>
	  	</div>
		<!-- basic scripts -->
		<!-- 页面底部js¨ -->
		<%@ include file="foot.jsp"%>
		
		<!-- page specific plugin scripts -->

		<!-- ace scripts -->
		<script src="static/ace/js/ace/elements.scroller.js"></script>
		<script src="static/ace/js/ace/elements.colorpicker.js"></script>
		<script src="static/ace/js/ace/elements.fileinput.js"></script>
		<script src="static/ace/js/ace/elements.typeahead.js"></script>
		<script src="static/ace/js/ace/elements.wysiwyg.js"></script>
		<script src="static/ace/js/ace/elements.spinner.js"></script>
		<script src="static/ace/js/ace/elements.treeview.js"></script>
		<script src="static/ace/js/ace/elements.wizard.js"></script>
		<script src="static/ace/js/ace/elements.aside.js"></script>
		<script src="static/ace/js/ace/ace.js"></script>
		<script src="static/ace/js/ace/ace.ajax-content.js"></script>
		<script src="static/ace/js/ace/ace.touch-drag.js"></script>
		<script src="static/ace/js/ace/ace.sidebar.js"></script>
		<script src="static/ace/js/ace/ace.sidebar-scroll-1.js"></script>
		<script src="static/ace/js/ace/ace.submenu-hover.js"></script>
		<script src="static/ace/js/ace/ace.widget-box.js"></script>
		<script src="static/ace/js/ace/ace.settings.js"></script>
		<script src="static/ace/js/ace/ace.settings-rtl.js"></script>
		<script src="static/ace/js/ace/ace.settings-skin.js"></script>
		<script src="static/ace/js/ace/ace.widget-on-reload.js"></script>
		<script src="static/ace/js/ace/ace.searchbox-autocomplete.js"></script>
		<script type="text/javascript"> ace.vars['base'] = '..'; </script>
		<script src="static/ace/js/ace/elements.onpage-help.js"></script>
		<script src="static/ace/js/ace/ace.onpage-help.js"></script>
	
		<script type="text/javascript" src="static/js/myjs/head.js"></script>
		<script type="text/javascript" src="static/js/myjs/index.js"></script>
		<script type="text/javascript" src="plugins/attention/zDialog/zDrag.js"></script>
		<script type="text/javascript" src="plugins/attention/zDialog/zDialog.js"></script>
		<script src="static/ace/js/bootbox.js" type="text/javascript"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script>
		$(document).ready(function(){
			$(top.hangge());
			
			$("#menu").on('click',".real-menu", function(e){
				$('.real-menu').removeClass("active");
				$(this).addClass("active");
			});
			
		});
		</script>
	</body>
</html>