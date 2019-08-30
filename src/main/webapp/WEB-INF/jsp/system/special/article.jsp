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

<link rel="stylesheet" type="text/css" href="static/global/plugins/jstree/dist/themes/default/style.min.css">
<link rel="stylesheet" type="text/css" href="static/global/css/plugins.css">
<link rel="stylesheet" href="static/ryn/jqw/jqx.base.css" type="text/css" />

<style type="text/css">
	.input-controls .col-md-12{
		margin-bottom:5px;
	}
	.jqx-tree-item-li{
		border-color:inherit !important; 
	}
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>特产商品管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="sp/listArticle">商品管理</a><span> ≫ </span></li>
			<c:if test="${empty art}">
				<li><a target="mainFrame" href="sp/editArticle/?ID=${art.ID}">上架新商品</a></li>
			</c:if>
			<c:if test="${not empty art}">
				<li><a target="mainFrame" href="sp/editArticle/?ID=${art.ID}">编辑</a></li>
			</c:if>
			
		</ul>
	</div>
	
	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<form  action="sp/saveArt" name="articleForm" id="articleForm" method="post" enctype="multipart/form-data">
				<div class="row input-controls">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">商品名称<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<input class="form-control" id="A_NAME" type="text" value="${art.NAME}">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">商品分类<span class="required-item">*</span></label>
							<div class="col-sm-2">
								<input class="form-control" id="A_CLASS_ID" type="text" value="${art.CLASS_ID}" style="display:none;">
								<input class="form-control" id="A_CLASS_NAME" type="text" value="${art.CLASS_NAME}" readonly>
							</div>
							<div class="col-sm-1">
								<button type="button" class="form-control btn btn-primary" onclick="selectClass();">选择商品分类</button>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">商品简介<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<textarea class="form-control" id="A_COMMENT" >${art.COMMENT}</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">商品价格<span class="required-item">*</span></label>
							<div class="col-sm-4">
								<div class="input-group">
	                                <input class="form-control" id="A_PRICE" type="number" value="${art.PRICE}">
	                                <span class="input-group-addon">￥</span>
	                            </div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						
						<div class="form-group" id="spOption">
							<label class="col-sm-2 control-label">选项</label>
							<!-- TEMP -->
							<div class="col-sm-10">
								<div class="row" id="OPT_VALS">
									<c:choose>										
										<c:when test="${not empty artOpts}">
											<%
									            int optNum = 0;								
									            pageContext.setAttribute("optNum", optNum);
									        %>
											<c:forEach items="${artOpts}" var="artOpt" varStatus="vs">
												<%
													optNum++;								
										            pageContext.setAttribute("optNum", optNum);
										        %>
												<div class="opt_val" id="opt_val${optNum}"> 
													<div class="col-sm-4" style="margin-bottom:5px;">
														<input id="opt_${optNum}" class="form-control"value="${artOpt.option}" readonly>
													</div>
													<div class='col-sm-7' style="margin-bottom:5px;">
														<input id="val_${optNum}" class="form-control" value="${artOpt.val}" readonly>
													</div>
													<div class="col-sm-1" style="margin-bottom:5px;">
														<button type="button" class="btn" style="padding:1px 8.75px;" onclick="delOption(${optNum});">-</button> 
													</div>
												</div>
											</c:forEach>
										</c:when>
									</c:choose>
								</div>
								<div class="row">
									<div class="col-sm-4">
										<select class="form-control" name="SP_OPTION" id="SP_OPTION">
											<option value="">请选择</option>
											<c:choose>										
												<c:when test="${not empty opts}">
													<c:forEach items="${opts}" var="opts" varStatus="vs">
														<option value="${opts.ID}">${opts.NAME}</option>
													</c:forEach>
												</c:when>
											</c:choose>
										</select>
									</div>
									<div class="col-sm-7">
										<select class="form-control" name="SP_OPTION_VALUE" id="SP_OPTION_VALUE">
											<option value="">请选择</option>
										</select>
									</div>
									<div class="col-sm-1">
										<button type="button" class="btn" style="padding:1px 7px;" onclick="addOption();">+</button>
									</div>
								</div>
							</div>
							
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">商品图片<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<input type="file" id="MAIN_IMAGE" name="MAIN_IMAGE"
											style="display: none;" onchange="uploadFile(this)" />
								<a onclick="fileType('MAIN_IMAGE')"
									class="btn btn-mini btn-success"
									style="margin-bottom:5px; height: 30px; align: center;">选择文件</a>
								<img id="MAIN_IMAGE_PREVIEW" alt="" src="" style="height:45px; max-width:230px;">
								<input type="text"
									name="MAIN_IMAGE_NAME" id="MAIN_IMAGE_NAME" accept="image/*"
									maxlength="32" placeholder="来选择任何文件" title="图片"
									style="display:none;" value="${art.MAIN_IMAGE}" readonly/>
								<p class="help-block">图片大小：1200 * 300</p>
							</div>                        
						</div>
					</div>        
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">图片<span class="required-item">*</span></label>
							<div class="col-sm-2">
								<p class="remove-image" onclick="removePicture('1')"><span>X</span></p>
								<img class="img preview-image" id="preImage1" src="static/assets/images/preview.png">
								<input class="dImageInput" type="file" id="dImage1" style="display: none;">
								<p class="change-image" onclick="changePicture('1')"><span>选择图片</span></p>
							</div>
							<div class="col-sm-2">
								<p class="remove-image" onclick="removePicture('2')"><span>X</span></p>
								<img class="img preview-image" id="preImage2" src="static/assets/images/preview.png">
								<input class="dImageInput" type="file" id="dImage2" style="display: none;">
								<p class="change-image" onclick="changePicture('2')"><span>选择图片</span></p>
							</div>
							<div class="col-sm-2">
								<p class="remove-image" onclick="removePicture('3')"><span>X</span></p>
								<img class="img preview-image" id="preImage3" src="static/assets/images/preview.png">
								<input class="dImageInput" type="file" id="dImage3" style="display: none;">
								<p class="change-image" onclick="changePicture('3')"><span>选择图片</span></p>
							</div>
							<div class="col-sm-2">
								<p class="remove-image" onclick="removePicture('4')"><span>X</span></p>
								<img class="img preview-image" id="preImage4" src="static/assets/images/preview.png">
								<input class="dImageInput" type="file" id="dImage4" style="display: none;">
								<p class="change-image" onclick="changePicture('4')"><span>选择图片</span></p>
							</div>
							<div class="col-sm-2">
								<p class="remove-image" onclick="removePicture('5')"><span>X</span></p>
								<img class="img preview-image" id="preImage5" src="static/assets/images/preview.png">
								<input class="dImageInput" type="file" id="dImage5" style="display: none;">
								<p class="change-image" onclick="changePicture('5')"><span>选择图片</span></p>
							</div>                        
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">服务特点<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<textarea class="form-control" id="A_SERVICE_FEATURE">${art.SERVICE_FEATURE}</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" data-toggle="collapse" data-target="#A_DESCRIPTION_EDIT_AREA">商品介绍<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<span id="A_ORG_DESCRIPTION" style="display:none;">${art.DESCRIPTION}</span>							
								<div id="A_DESCRIPTION_EDIT_AREA" class="collapse">
									<INPUT id="editor1" type="hidden" name="A_DESCRIPTION" value="">
									<iframe id="Web_Edit" name="Web_Edit" style="border:0px;" width="100%" height="300"
										src="static/editor1.jsp"></iframe>
									<span style="display: none;">
										<a href="static/editor1.jsp" target="Web_Edit">W3Schools.com</a>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" data-toggle="collapse" data-target="#A_SIZE_PACKING_EDIT_AREA">规格与包装<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<span id="A_ORG_SIZE_PACKING" style="display:none;">${art.SIZE_PACKING}</span>							
								<div id="A_SIZE_PACKING_EDIT_AREA" class="collapse">
									<INPUT id="editor2" type="hidden" name="A_SIZE_PACKING" value="">
									<iframe id="Web_Edit" name="Web_Edit" style="border:0px;" width="100%" height="300"
										src="static/editor2.jsp"></iframe>
									<span style="display: none;">
										<a href="static/editor2.jsp" target="Web_Edit">W3Schools.com</a>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">购买URL<span class="required-item">*</span></label>
							<div class="col-sm-10">
								<input class="form-control" id="A_PURCHASE_URL" type="text" value="${art.PURCHASE_URL}">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">显示状态</label>
							<div class="col-sm-10">
								<label class="radio-inline">
				                    <input
				                    ${art.VIEW_STATE != 0 ? 'checked' : ''} type="radio" name="viewOptions" value="1">显示
				                </label>
				              	<label class="radio-inline">
				                    <input 
				                    	 ${art.VIEW_STATE == 0 ? 'checked' : ''} type="radio" name="viewOptions" value="0">不显示
				                </label>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">销售状态</label>
							<div class="col-sm-10">
								<label class="radio-inline">
				                    <input
				                    ${art.SALE_STATE != 0 ? 'checked' : ''} type="radio" name="saleOptions" value="1">卖
				                </label>
				              	<label class="radio-inline">
				                    <input 
				                    	 ${art.SALE_STATE == 0 ? 'checked' : ''} type="radio" name="saleOptions" value="0">不卖
				                </label>										
							</div>
						</div>
					</div>
				</div>
			</form>	
	            <div class="row">
	            	<div class="col-md-2">
	            	</div>
	            	<div class="col-md-10 form-group">
	            		<button onclick="preSaveArticle();" class="btn btn-primary">发布</button>
	            		<button onclick="goListPage();" class="btn btn-primary">返回</button>
	            	</div>
	            </div>
		</div>
	</div>

	<div class="modal fade" id="classModal" tabindex="-1" role="dialog" aria-labelledby="classModalLabel">						
  		<div class="modal-dialog" role="document" style="width:500px">
    		<div class="modal-content" style="border-width:0px;">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel" style="font-weight:bold">选择商品分类</h4>
      			</div>
      			<div class="modal-body" style="text-align:left; height: 200px; overflow: auto;">
      				<input id="selClassLabel" value="" style="display:none;">					      				
					<div id="jqxTree" class="tree-demo"></div>
				</div>
      		</div>
      		<div class="modal-footer" style="padding: 5px 0px 10px 0px;">
      			<button type="button" class="btn btn-primary btn_user_modal_confirm" onclick="confirmClass();">确定</button>
       			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					        
      		</div>
    	</div>
  	</div>
	
	<div class="modal fade" id="artModal" tabindex="-1" role="dialog" aria-labelledby="artModalLabel">						
  		<div class="modal-dialog" role="document" style="width:500px">
    		<div class="modal-content" style="border-width:0px;">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
        				<span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel" style="font-weight:bold">保存商品</h4>
      			</div>
      			<div class="modal-body">
      				<input id="edit_art_id" value="" style="display:none;">
      				<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-3" style="text-align:right;">商品名称:</div>
   						<div class="col-sm-9" id="edit_art_name" style="text-align:left;"></div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-3" style="text-align:right;">该商品状态:</div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-offset-1 col-sm-10">
   							<table class="table table-hover">
		      					<thead>      					
			      					<tr>
			      						<th class="ipt-td">用户名</th>
			      						<th class="ipt-td">状态</th>
			      					</tr>
		      					</thead>
		      					<tbody>
		      					
		      					</tbody>
		      				</table>
   						</div>
      				</div>
      				<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-3" style="text-align:right;">
   							<span><input type="checkbox" id="msg_enable">通知用户</span>
						</div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-3" style="text-align:right;">
   							通知模板：
   						</div>
   						<div class="col-sm-4">
   							<select class="form-control">
   								<option>1</option>
   								<option>2</option>
   								<option>3</option>
   								<option>4</option>
   							</select>
   						</div>
   					</div>
   					<div class="row" style="margin-bottom: 5px;">
   						<div class="col-sm-offset-1 col-sm-10">
   							<h3>您确定保存吗？</h3>
   						</div>
   					</div>
				</div>
      		</div>
      		<div class="modal-footer" style="padding: 5px 0px 10px 0px;">
      			<button type="button" class="btn btn-primary btn_user_modal_confirm" onclick="confirmSave();">确定</button>
       			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>					        
      		</div>
    	</div>
  	</div>
  	
	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>
	
	<!-- basic scripts -->
	<%@ include file="../index/foot.jsp"%>
	<script src="static/ace/js/bootbox.js" type="text/javascript"></script>
	<script src="static/ace/js/ace/ace.js" type="text/javascript"></script>
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"
		type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	
	<script type="text/javascript" src="static/ryn/jqw/jqxcore.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxbuttons.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxscrollbar.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxpanel.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxtree.js"></script>
    <script type="text/javascript" src="static/ryn/jqw/jqxexpander.js"></script>
    
</body>

<script>
$(top.hangge());

$('#editor1').val($('#A_ORG_DESCRIPTION').html());
$('#editor2').val($('#A_ORG_SIZE_PACKING').html());

function preSaveArticle(){
	if(checkValidity()){
		var _id = '${art.ID}';
		var _name = '${art.NAME}';
		
		if(_id == ''){
			saveArticle(_id);
		}else{
			$('#edit_art_id').val(_id);
			$('#edit_art_name').html(_name);
			$('#artModal').modal("show");
		}
	}
}

function confirmSave(){
	var _id = $('#edit_art_id').val();
	saveArticle(_id);
	$('#artModal').modal("hide");
}

function selectClass(){
	var cid = $('#A_CLASS_ID').val();
	if(cid != ''){
		$('input:radio[name="spcGroup"][value="' + cid + '"]').prop('checked', true);
	}
	$('#classModal').modal("show");
}
function confirmClass(){
	var selCID = $('input:radio[name="spcGroup"]:checked').val();
	var selCLB = $('#selClassLabel').val();
	$('#A_CLASS_NAME').val(selCLB);
	$('#A_CLASS_ID').val(selCID);
	
	$('#classModal').modal("hide");
}

function fileType(file) {
	document.getElementById(file).click();
}
	
function uploadFile(obj) {
	$("#MAIN_IMAGE_NAME").val(obj.files[0].name);
	var fileType = obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	if (fileType != '.jpg' && fileType != '.png' && fileType != '.bmp') {
		$("#MAIN_IMAGE_NAME").tips({
			side : 3,
			msg : '请上传图片格式的文件',
			bg : '#AE81FF',
			time : 3
		});
		$("#MAIN_IMAGE_NAME").val('');
		document.getElementById("MAIN_IMAGE").files[0] = '请选择图片格式的文件';
		return;
	}
	
	$.each(obj.files, function(_, file) {
        var reader = new FileReader();
        reader.onload = function (ev) {
        	$("#MAIN_IMAGE_PREVIEW").attr('src', ev.target.result);
        }
        reader.readAsDataURL(file);
    });
}

function delOption(_id){
	$('#opt_val' + _id).remove();
}
function addOption(){
	var opt_idx = $("#SP_OPTION")[0].selectedIndex;
	var opt_label =$("#SP_OPTION")[0].options[opt_idx].label;	
	var opt_id = $("#SP_OPTION").val().toString();
	var val_idx = $("#SP_OPTION_VALUE")[0].selectedIndex;
	var val_label =$("#SP_OPTION_VALUE")[0].options[val_idx].label;
	var val_id = $("#SP_OPTION_VALUE").val();
	
	var ele = $(".opt_val").length + 1;
	
	if(opt_id != "" && val_id != ""){
		var html = "<div class='opt_val' id='opt_val" + ele + "'>" + 
				"<div class='col-sm-4'>" + 
					"<input class='form-control' id='opt_" + ele + "' value='" + opt_label + "' readonly>" + 
				"</div>" + 
				"<div class='col-sm-7'>" + 
					"<input class='form-control' id='val_" + ele + "' value='" + val_label + "' readonly>" + 
				"</div>" + 
				"<div class='col-sm-1'>" + 
					"<button type='button' class='btn' style='padding:0px 7px;' onclick='delOption("+ ele +");'>-</button>" + 
				"</div>";
	
		$("#OPT_VALS").append(html);
		
		$("#SP_OPTION").val("");
		$("#SP_OPTION_VALUE").val("");
		
		$('#SP_OPTION option:eq(' + opt_idx + ')').remove();
		$('#SP_OPTION_VALUE option:eq(' + val_idx + ')').remove();
	}
	else{
		if(opt_id == ""){
			$("#SP_OPTION").tips({
				side : 2,
				msg : '请选择',
				bg : '#AE81FF',
				time : 3
			});
		}

		if(val_id == ""){
			$("#SP_OPTION_VALUE").tips({
				side : 2,
				msg : '请选择',
				bg : '#AE81FF',
				time : 3
			});
		}
	}

	
}
$('#SP_OPTION').change(function() {
   	var option_id = this.value;
	if(option_id == ""){
		
	}
	else
	{
		$.ajax({
			type: "POST",
			url: 'sp/findOptValByMid',
			data: { MID : option_id },
			dataType:'json',
			success: function (res) {
				$("#SP_OPTION_VALUE>option").remove();
				$('#SP_OPTION_VALUE').append( "<option value=''>请选择</option>" );
				if ("success" == res.result) {
					var _v = res.data;
					for(var i = 0; i < _v.length; i++){
						$('#SP_OPTION_VALUE').append( "<option value='" + _v[i].ID + "'>" + _v[i].NAME + "</option>" );
					}
				} else {
					alert("FAILED");
				}
			}
		});
	}
	
	

})

var main_image = '${art.MAIN_IMAGE}';
$('#MAIN_IMAGE_PREVIEW').attr('src',"static/uploadFiles/uploadImgs/sp/" + main_image);

var detail_image = '${art.DETAIL_IMAGE}';
var detail_images = detail_image.split("|");

for(var i = 0; i < detail_images.length; i++ ){
	if(detail_images[i] == ""){
		detail_images[i] = " ";
	}
	else
	{
		if(detail_images[i].trim() != "")
			$('#preImage' +(i + 1)).attr('src',"static/uploadFiles/uploadImgs/sp/" + detail_images[i]);
	}
}
for(var i = detail_images.length; i < 5; i++ ){
	detail_images[i] = " ";
}
function removePicture(_idx){
    $('#preImage' + _idx).attr('src',"static/assets/images/preview.png");
    $('#dImage' + _idx ).val("");
    detail_images[_idx - 1] = " ";
}
function changePicture(_idx) {
    var fileInput = document.getElementById('dImage'+ _idx);
    fileInput.click();
};
function readIMG(input) {
	var _idx = input.id.replace("dImage", "");
	
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#preImage' + _idx).attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
$('.dImageInput').change(function() {
    readIMG(this);
})
function checkValidity(){
	if ($("#A_NAME").val() == "") {

		$("#A_NAME").tips({
			side : 2,
			msg : '请输入商品名称',
			bg : '#AE81FF',
			time : 3
		});

		$("#A_NAME").focus();
		return false;
	}
	else{
		$("#A_NAME").val(jQuery.trim($('#A_NAME').val()));
	}
	if ($("#A_CLASS_ID").val() == "") {

		$("#A_CLASS_NAME").tips({
			side : 2,
			msg : '请选择商品分类',
			bg : '#AE81FF',
			time : 3
		});

		$("#A_CLASS_NAME").focus();
		return false;
	}
	else{
		$("#A_CLASS_ID").val(jQuery.trim($('#A_CLASS_ID').val()));
	}
	
	if ($("#A_COMMENT").val() == "") {

		$("#A_COMMENT").tips({
			side : 2,
			msg : '请输入商品简介',
			bg : '#AE81FF',
			time : 3
		});

		$("#A_COMMENT").focus();
		return false;
	}
	else{
		$("#A_COMMENT").val(jQuery.trim($('#A_COMMENT').val()));
	}
	
	if ($("#A_PRICE").val() == "") {

		$("#A_PRICE").tips({
			side : 2,
			msg : '请输入商品价格',
			bg : '#AE81FF',
			time : 3
		});

		$("#A_PRICE").focus();
		return false;
	}
	else{
		$("#A_PRICE").val(jQuery.trim($('#A_PRICE').val()));
	}
	
	if ($("#MAIN_IMAGE_NAME").val() == "") {

		$("#MAIN_IMAGE_NAME").tips({
			side : 2,
			msg : '请选择图片',
			bg : '#AE81FF',
			time : 3
		});

		$("#MAIN_IMAGE").focus();
		return false;
	}
	else{
		$("#MAIN_IMAGE_NAME").val(jQuery.trim($('#MAIN_IMAGE_NAME').val()));
	}

	if ($("#A_SERVICE_FEATURE").val() == "") {

		$("#A_SERVICE_FEATURE").tips({
			side : 2,
			msg : '请输入服务特点',
			bg : '#AE81FF',
			time : 3
		});

		$("#A_SERVICE_FEATURE").focus();
		return false;
	}
	else{
		$("#A_SERVICE_FEATURE").val(jQuery.trim($('#A_SERVICE_FEATURE').val()));
	}
	
	if ($("#A_DESCRIPTION").val() == "") {

		$("#A_DESCRIPTION").tips({
			side : 2,
			msg : '请输入商品介绍',
			bg : '#AE81FF',
			time : 3
		});

		$("#A_DESCRIPTION").focus();
		return false;
	}
	else{
		$("#A_DESCRIPTION").val(jQuery.trim($('#A_DESCRIPTION').val()));
	}
	
	if ($("#A_SIZE_PACKING").val() == "") {

		$("#A_SIZE_PACKING").tips({
			side : 2,
			msg : '请输入规格与包装',
			bg : '#AE81FF',
			time : 3
		});

		$("#A_SIZE_PACKING").focus();
		return false;
	}
	else{
		$("#A_SIZE_PACKING").val(jQuery.trim($('#A_SIZE_PACKING').val()));
	}
	
	if ($("#A_PURCHASE_URL").val() == "") {

		$("#A_PURCHASE_URL").tips({
			side : 2,
			msg : '请输入购买URL',
			bg : '#AE81FF',
			time : 3
		});

		$("#A_PURCHASE_URL").focus();
		return false;
	}
	else{
		$("#A_PURCHASE_URL").val(jQuery.trim($('#A_PURCHASE_URL').val()));
	}
	
	return true;
}
function saveArticle(_ID){
	/* $("#placeSelect option:selected").text(); */
	//if(checkValidity()){
		var _REGISTER = "";
		var _REGIST_TIME = "";
		
		var fd = new FormData();
	    var mainImage = $('#MAIN_IMAGE')[0].files;
	    var dImage1 = $('#dImage1')[0].files;
	    var dImage2 = $('#dImage2')[0].files;
	    var dImage3 = $('#dImage3')[0].files;
	    var dImage4 = $('#dImage4')[0].files;
	    var dImage5 = $('#dImage5')[0].files;
	    
	    fd.append("ID",_ID);
   		fd.append("NAME", $("#A_NAME").val());
   		fd.append("SP_CLASS", $("#A_CLASS_ID").val());
	 	fd.append("COMMENT", $("#A_COMMENT").val());
		fd.append("PRICE", $("#A_PRICE").val());
		fd.append("M_IMG_S", mainImage[0]);
		fd.append("D_IMG_S1",dImage1[0]);
		fd.append("D_IMG_S2",dImage2[0]);
		fd.append("D_IMG_S3",dImage3[0]);
		fd.append("D_IMG_S4",dImage4[0]);
		fd.append("D_IMG_S5",dImage5[0]);		
	 	fd.append("MAIN_IMAGE", $("#MAIN_IMAGE_NAME").val());
	 	fd.append("DETAIL_IMAGE", detail_images.join("|"));		
		fd.append("SERVICE_FEATURE", $("#A_SERVICE_FEATURE").val());
		fd.append("DESCRIPTION", $("#editor1").val());
		fd.append("SIZE_PACKING", $("#editor2").val());
		fd.append("PURCHASE_URL", $("#A_PURCHASE_URL").val());
		fd.append("VIEW_STATE", $('input[type="radio"][name="viewOptions"]:checked').val());
		fd.append("SALE_STATE", $('input[type="radio"][name="saleOptions"]:checked').val());
		fd.append("REGISTER",_REGISTER);
		fd.append("REGIST_TIME", _REGIST_TIME);
		

		var options = [];
		for(var i = 0; i < $(".opt_val").length; i++ ){
			var opt = $("#opt_" + (i + 1)).val();
			var val = $("#val_" + (i + 1)).val();
			
			options.push(opt + "|" + val);
		}
		
		fd.append("OPTIONS", options );
		
		var _url = "";
	    if(_ID == ""){
	    	_url = "sp/saveArt";
	    }
	    else
    	{
	    	_url = "sp/updateArt";
    	}
		$.ajax({
			type: "POST",
			url: _url,
			data: fd,
			contentType: false,
       	 	processData: false,
			success: function (data) {
				if ("success" == data.result) {
					$("#articleForm").attr( "action", "sp/listArticle/?" + $("#articleForm").serialize());
					$("#articleForm").submit();
				} else {
					alert("FAILED");
				}
			}
		});
	//}
}
function goListPage(){
	$("#articleForm").attr( "action", "sp/listArticle/?" + $("#articleForm").serialize());
	$("#articleForm").submit();
}

$(function() {
	var mn = '${zTreeNodes}';
	var mnObj = jQuery.parseJSON(mn);
	
	function makeTree(nodes){
		var nodeList = [];
		for(var i = 0; i < nodes.length; i++){
			var el = nodes[i];
			var n = {
					value: el.id,
					expanded: true,
					html: "<input type='radio' name='spcGroup' value='" 
							+ el.id + "'><span style='padding: 0px 7px;font-size: 14px;'>" + el.name + "</span>",
  					items: []
          	};
			
			if(el.nodes.length > 0){
				n.items = makeTree(el.nodes);
			}
			
			nodeList.push(n);
		}
		return nodeList;
	}
	
	var source = makeTree(mnObj);

     $('#jqxTree').jqxTree({
			source: source,
			width: '100%', height: '100%',
			allowDrag: false});
     $('#jqxTree').jqxTree('selectItem', null);
     
     $('#jqxTree').on('itemClick', function (event)
	 {
	     var args = event.args;
	     var item = $('#jqxTree').jqxTree('getItem', args.element);
	     var value = item.value;
	     var label = item.label;
	     $('#selClassLabel').val(label);
	     
	     $('input:radio[name=spcGroup]').each(function() {
    	    var d = $(this)[0].value;
    	    if(d == value){
    	    	$(this).prop('checked', true);
    	    }else{
    	    	$(this).prop('checked', false);
    	    }
   		 });
	     
	 });
	
});
</script>
