var locat = (window.location+'').split('/'); 
$(function(){if('main'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});

var fmid = "fhindex";	//菜单点中状态
var mid = "fhindex";	//菜单点中状态
var fhsmsCount = 0;		//站内信总数
var USER_ID;			//用户ID
var user = "R3";		//用于即时通讯（ 当前登录用户）
var TR3smsSound = '1';	//站内信提示音效
var websocket;			//websocket对象
var wimadress="";		//即时聊天服务器IP和端口
var oladress="";		//在线管理和站内信服务器IP和端口

function siMenu(id,fid,MENU_NAME,MENU_URL){
	if(id != mid){
		$("#"+mid).removeClass();
		mid = id;
	}
	if(fid != fmid){
		$("#"+fmid).removeClass();
		fmid = fid;
	}
	$("#"+fid).attr("class","active open");
	$("#"+id).attr("class","active");
	top.mainFrame.tabAddHandler(id,MENU_NAME,MENU_URL);
	if(MENU_URL != "druid/index.html"){
		jzts();
	}
}

$(function(){
	getHeadMsg();	//初始页面最顶部信息
});

//初始页面信息
function getHeadMsg(){
	
}


//加入在线列表
function online(){
	if (window.WebSocket) {
		websocket = new WebSocket(encodeURI('ws://'+oladress)); //oladress在main.jsp页面定义
		websocket.onopen = function() {
			//连接成功
			websocket.send('[join]'+user);
		};
		websocket.onerror = function() {
			//连接失败
		};
		websocket.onclose = function() {
			//连接断开
		};
		//消息接收
		websocket.onmessage = function(message) {
			var message = JSON.parse(message.data);
			if(message.type == 'goOut'){
				$("body").html("");
				goOut("1");
			}else if(message.type == 'thegoout'){
				$("body").html("");
				goOut("2");
			}else if(message.type == 'senFhsms'){
				fhsmsCount = Number(fhsmsCount)+1;
				$("#fhsmsCount").html(Number(fhsmsCount));
				$("#fhsmsobj").html('<audio style="display: none;" id="fhsmstsy" src="static/sound/'+TR3smsSound+'.mp3" autoplay controls></audio>');
				$("#fhsmstss").tips({
					side:3,
		            msg:'有新消息',
		            bg:'#AE81FF',
		            time:30
		        });
			}
		};
	}
}

//下线
function goOut(msg){
	window.location.href=locat+"/logout.do?msg="+msg;
}


//修改个人资料
function checkUpdatePass(){
	
	if ($("#C_PASS").val() == "") {

		$("#C_PASS").tips({
			side : 2,
			msg : '请输入旧密码',
			bg : '#AE81FF',
			time : 3
		});

		$("#C_PASS").focus();
		return false;
	}
	if ($("#N_PASS").val() == "") {

		$("#N_PASS").tips({
			side : 2,
			msg : '请输入新密码',
			bg : '#AE81FF',
			time : 3
		});

		$("#N_PASS").focus();
		return false;
	}
	if ($("#R_PASS").val() == "") {

		$("#R_PASS").tips({
			side : 2,
			msg : '请输入确认密码',
			bg : '#AE81FF',
			time : 3
		});

		$("#R_PASS").focus();
		return false;
	}
	if ($("#R_PASS").val() != $("#N_PASS").val()) {

		$("#R_PASS").tips({
			side : 2,
			msg : '密码不一致',
			bg : '#AE81FF',
			time : 3
		});

		$("#R_PASS").focus();
		return false;
	}
	
	return true;
}
function editUserH(UAME, UID){
	$('#U_AME').val(UAME);
	$('#U_ID').val(UID);
	$('#updatePassModal').modal("show");
}
function updatePassword(){
	if(checkUpdatePass()){
		var UAME = $('#U_AME').val();
		var UID = $('#U_ID').val();
		var _c = $("#C_PASS").val();
		var _n = $("#N_PASS").val();
		
		jzts();
		$.ajax({
			type: "POST",
			url: locat + '/sys/checkPassword',
	    	data: {USERNAME: UAME, PASSWORD: _c},
			dataType:'json',
			cache: false,
			success: function(data){
				if("ok" == data.result){
					$.ajax({
						type: "POST",
						url: locat + '/sys/updatePassword',
				    	data: {ID: UID, PASS: _n, USERNAME: UAME},
						dataType:'json',
						cache: false,
						success: function(data){
							if("success" == data.result){
								alert('更新密码成功了！');
								$('#updatePassModal').modal("hide");
								$(top.hangge());
							}else{
								alert('FAILED!');
								$(top.hangge());
							}
						}
					});
				}else{
					alert('旧密码不对!');
					$(top.hangge());
				}
			}
		});
	}
}

//系统设置
function editSys(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="系统设置";
	 diag.URL = locat+'/head/goSystem.do';
	 diag.Width = 600;
	 diag.Height = 526;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//站内信
function fhsms(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="站内信";
	 diag.URL = locat+'/fhsms/list.do?STATUS=2';
	 diag.Width = 800;
	 diag.Height = 500;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//切换菜单
function changeMenus(){
	window.location.href=locat+'/main/yes';
}

//清除加载进度
function hangge(){
	$("#jzts").hide();
}

//显示加载进度
function jzts(){
	$("#jzts").show();
}