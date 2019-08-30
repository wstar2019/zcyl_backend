var locat = (window.location+'').split('/'); 
$(function(){if('tool'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});

//倒计时
function timer(intDiff){
	window.setInterval(function(){
	$('#second_shows').html('<s></s>'+intDiff+'秒');
	intDiff--;
	}, 1000);
}

function showdiv(){
	$("#zhongxin2").hide();
	$("#zhongxin").show();
}

function setType(value){
	$("#TYPE").val(value);
}

function isAll(){
	if(document.getElementsByName('form-field-checkbox')[0].checked){
		$("#isAll").val('yes');
		$("#EMAIL").attr("disabled",true);
	}else{
		$("#isAll").val('no');
		$("#EMAIL").attr("disabled",false);
	}
}

//ueditor纯文本
function getContentTxt(id) {
    var arr = [];
    var target=id?id:'editor';
    arr.push(UE.getEditor(target).getContentTxt());
    return arr.join("");
}

//ueditor有标签文本
function getContent(id) {
    var arr = [];
    var target=id?id:'editor'
    arr.push(UE.getEditor(target).getContent());
    return arr.join("");
}
function insertHtml() {
	var value = prompt('插入html代码', '');
	UE.getEditor('editor').execCommand('insertHtml', value)
}
setTimeout("ueditor()",500);
function ueditor(){
	var ue = UE.getEditor('editor');
}
