<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="thumb" uri="http://hengye.com"%>
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
	<%@ include file="../index/top.jsp"%>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<%@ include file="../index/foot.jsp"%>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		function closeLoading(){
			try{
				top.hangge();
			}catch (e){
				setTimeout(function(){
					closeLoading();
				},1000);
			}
		}
		$(function(){
			closeLoading();
		});
		now=${now};
		setInterval(function(){
			$("#now").html(new Date(now).toLocaleString());
			now = now + 1000;
		},1000);
	</script>
	<script type="text/javascript" src="static/ace/js/jquery.js"></script>
	<script>
	
	function updatePassword(){
		if(checkUpdatePass()){
			var _c = $("#C_PASS").val();
			var _n = $("#N_PASS").val();
			var _r = $("#R_PASS").val();
		}
		<%--var options = {
				title: "修改密码",
				inputs:[
					{"type": "text", "label": "旧密码: ", "value": "", "idx": "C_PASS", "required": true },
					{"type": "text", "label": "新密码: ", "value": "", "idx": "N_PASS", "required": true },
					{"type": "text", "label": "确认密码: ", "value": "", "idx": "R_PASS", "required": true }
				]
			};
		
			 bootbox.prompt(options, function(result) {
				if(result) {
					var _c, _n, _r;
					for(var i = 0; i < result.length; i++){
						if(result[i].idx == "C_PASS"){
							_c = result[i].val;
						}
						if(result[i].idx == "N_PASS"){
							_n = result[i].val;
						}
						if(result[i].idx == "R_PASS"){
							_r = result[i].val;
						}
					}
					
					if(_n == "" || null == _r){
						alert("请输入确认密码");
					}
					else{
						top.jzts();
						$.ajax({
							type: "POST",
							url: '<%=basePath%>sys/updateManager',
					    	data: {ID: _id, NAME: _n, LG_ID: _l, STATE: _s, TEL: _t, ROLE_ID: _r },
							dataType:'json',
							cache: false,
							success: function(data){
								if("success" == data.result){
									nextPage('${page.currentPage}');
								}else{
									alert('FAILED!');
									$(top.hangge());
								}
							}
						});
					}
				}
			}); --%>
	}
	</script>
</body>
</html>