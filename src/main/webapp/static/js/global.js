/**
 * 通用顶部JS
 */

//ID 要校验值ID isRequired是否必填 1 是  minSize 最小长度  maxSize 最大长度，title提示内容
function checkForm(id,isRequired,title,reg,minSize,maxSize){
	var message = "";
	//获取ID值
	var idVal = $("#"+id).val();
	idVal = idVal.trim();
	var length = idVal.length;
	//如果必填 先校验
	if("1" == isRequired){
		if(idVal == "" || length==0){
			message = title+"不能为空";
			$("#"+id).focus();
			$("#"+id+"Span").html(message);
			return false;
		}
	}
	if(reg != undefined && reg != null){
		if(!reg.test(idVal)){
			message = title + "格式不正确";
			$("#"+id).focus();
			$("#"+id+"Span").html(message);
			return false;
		}
	}
	//校验最小长度
	if(minSize>0 && minSize != undefined && minSize != null){
		if(length - minSize<0){
			message = title + "不能小于"+minSize;
			$("#"+id).focus();
			$("#"+id+"Span").html(message);
			return false;
		}
	}
	
	//校验最大长度
	if(maxSize>0 && maxSize != undefined && maxSize != null){
		if(length - maxSize>0){
			message = title + "不能大于"+maxSize;
			$("#"+id).focus();
			$("#"+id+"Span").html(message);
			return false;
		}
	}
	$("#"+id+"Span").html("");
	return true;
}