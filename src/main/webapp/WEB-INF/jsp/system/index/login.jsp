<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
				+ request.getServerPort() + path + "/";
	application.setAttribute("basePath", path);
%>
<html lang="en"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>${pd.SYSNAME}</title>
    <meta charset="utf-8">

    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="stylesheet" href="static/login/bootstrap.css">
    <link href="static/ryn/auth.css" type="text/css" rel="stylesheet" />


</head>
<body class="auth">
    <div class="container">
        <form class="form-signin" action="" method="post" name="loginForm" id="loginForm">
            <img class="img logo-img" src="static/assets/images/logo.png">
            <div class="title">
                <p>
                    <span>中朝友联旅游</span>
                </p>
            </div>
            <div style="margin-bottom:5px;">
                <input name="loginname" type="text" class="form-control" id="loginname" placeholder="用户名" maxlength="20">
            </div>
            <div style="margin-bottom:5px;">
                <input id="password" type="password" class="form-control" name="password" placeholder="密码">
            </div>
            <div class="input-group">
            	<input id="code" type="text" class="form-control" name="code" placeholder="验证码">
                <span class="input-group-addon"><img style="height:22px;" id="codeImg" alt="点击更换"
						title="点击更换" src="" />
                </span>
            </div>
                
            <a id="loginBtn" class="btn btn-large btn-primary" onclick="severCheck();" >登&nbsp; &nbsp; &nbsp;&nbsp; 录</a>
        </form>

    </div>
<!-- javscript js -->
<script src="static/login/jquery.js"></script>
<script src="static/login/bootstrap.js"></script>

<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript" src="static/js/jquery.cookie.js"></script>

<script type="text/javascript">
	//服务器校验
	function severCheck(){
		if(check()){				
			var loginname = $("#loginname").val();
			var password = $("#password").val();
			var code = "RC03030514"+loginname+",R3C5,"+password+"19881994NH" + ",R3C5," + $("#code").val();
			$.ajax({
				type: "POST",
				url: 'login_login',
		    	data: {KEYDATA:code,tm:new Date().getTime()},
				dataType:'json',
				cache: false,
				success: function(data){
					if("success" == data.result){							
						saveCookie();							
						window.location.href="main/index";
					}else if("usererror" == data.result){
						$("#loginname").tips({
							side : 1,
							msg : "用户名或密码有误",
							bg : '#FF5080',
							time : 5
						});
						$("#loginname").focus();
					}
					else if("codeerror" == data.result)
					{
						$("#code").tips({
							side : 1,
							msg : "验证码输入有误",
							bg : '#FF5080',
							time : 5
						});
						$("#code").focus();
					}
					else if("stateerror" == data.result){
						alert("该用户被禁止了!");
					} else{
						$("#loginname").tips({
							side : 1,
							msg : "缺少参数",
							bg : '#FF5080',
							time : 5
						});
						$("#loginname").focus();
					}
				}
			});
		}
	}
	
	$(document).ready(function() {
		changeCode();
		$("#codeImg").bind("click", changeCode);
	});
	
	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			$("#loginBtn").trigger("click");
		}
	});
	function genTimestamp() {
		var time = new Date();
		return time.getTime();
	}
	function changeCode() {
		$("#codeImg").attr("src", "code?t=" + genTimestamp());
	}

	//客户端校验
	function check() {

		if ($("#loginname").val() == "") {

			$("#loginname").tips({
				side : 2,
				msg : '用户名不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#loginname").focus();
			return false;
		} else {
			$("#loginname").val(jQuery.trim($('#loginname').val()));
		}

		if ($("#password").val() == "") {

			$("#password").tips({
				side : 2,
				msg : '密码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#password").focus();
			return false;
		}
		
		if ($("#code").val() == "") {

			$("#code").tips({
				side : 3,
				msg : '验证码不得为空',
				bg : '#AE81FF',
				time : 3
			});

			$("#code").focus();
			return false;
		}

		$("#loginbox").tips({
			side : 1,
			msg : '正在登录 , 请稍后 ...',
			bg : '#68B500',
			time : 10
		});

		return true;
	}

	function savePaw() {
		if (!$("#saveid").attr("checked")) {
			$.cookie('loginname', '', {
				expires : -1
			});
			
			$.cookie('password', '', {
				expires : -1
			});
			
			$("#loginname").val('');
			$("#password").val('');
		}
	}

	function saveCookie() {
		if ($("#saveid").attr("checked")) {
			$.cookie('loginname', $("#loginname").val(), {
				expires : 7
			});
			$.cookie('password', $("#password").val(), {
				expires : 7
			});
		}
	}
	function quxiao() {
		$("#loginname").val('');
		$("#password").val('');
	}
	
	jQuery(function() {
		var loginname = $.cookie('loginname');
		var password = $.cookie('password');
		if (typeof(loginname) != "undefined" && typeof(password) != "undefined") {
			$("#loginname").val(loginname);
			$("#password").val(password);
			$("#saveid").attr("checked", true);
			$("#code").focus();
		}
	});
</script>
<script>
	if (window != top) {
           top.location.href = "<%=basePath%>login";
	}
</script>


</body></html>