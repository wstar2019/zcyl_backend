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
<link rel="stylesheet" type="text/css" href="static/global/css/mycomponents.css">
<link rel="stylesheet" type="text/css" href="static/global/plugins/jstree/dist/themes/default/style.min.css">
<link rel="stylesheet" type="text/css" href="static/global/css/plugins.css">

<style type="text/css">
	.btn.btn-app.btn-xs {
		width: 64px;
		font-size: 12px;
		border-radius: 6px;
		padding-bottom: 2px;
		padding-top: 2px;
		line-height: 1.45;
	}
	.input-controls .col-md-12{
		margin-bottom:5px;
	}
</style>
<head></head>
<body>
	<div class="page-header">
		<ul class="navi">
			<li><a>旅游管理</a><span> ≫ </span></li>
			<li><a target="mainFrame" href="tour/listTourArticle">旅游商品管理</a><span> ≫ </span></li>
			<c:if test="${not empty art.ID}">
				<li><a target="mainFrame" href="tour/editTourArticle/?ID=${art.ID}">编辑</a></li>
			</c:if>
			<c:if test="${empty art.ID}">
				<li><a target="mainFrame" href="tour/editTourArticle">上架新商品</a></li>
			</c:if>
		</ul>
	</div>
	<!-- /section:basics/navbar.layout -->

	<div class="page-content" style="padding: 10px;">
		<div class="container-fluid">
			<form  action="tour/saveArt.do" name="tourArticleForm" id="tourArticleForm" method="post" enctype="multipart/form-data">
				<div class="row input-controls">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" id="lbl_name">商品名称<span class="required-item">*</span></label>
							<div class="col-sm-8">
								<input class="form-control" id="A_NAME" type="text" autocomplete="off" value="${art.NAME}">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">商品简介<span class="required-item">*</span></label>
							<div class="col-sm-8">
								<textarea class="form-control" id="A_DESCRIPTION" >${art.DESCRIPTION}</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">价格<span class="required-item">*</span></label>
							<div class="col-sm-2">
								<div class="input-group">
	                                <input class="form-control" id="A_ADULT_EXPENSE" type="number"
										min="0"	value="${art.ADULT_EXPENSE}">
	                                <span class="input-group-addon">
	                                    (成人)
	                                </span>
	                            </div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
	                                <input class="form-control" id="A_CHILD_EXPENSE" type="number" 
	                                	min="0" value="${art.CHILD_EXPENSE}">
	                                <span class="input-group-addon">
	                                    (儿童)
	                                </span>
	                            </div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						
						<div class="form-group" id="tourOption">
							<label class="col-sm-2 control-label">选项</label>
							<!-- TEMP -->
							<div class="col-sm-8">
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
														<input class="form-control" id="opt_${optNum}" value="${artOpt.OPTION}" readonly>
													</div>
													<div class='col-sm-7' style="margin-bottom:5px;">
														<input class="form-control" id="val_${optNum}" value="${artOpt.VAL}" readonly>
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
										<select class="form-control" name="TOUR_OPTION" id="TOUR_OPTION">
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
										<select class="form-control" name="TOUR_OPTION_VALUE" id="TOUR_OPTION_VALUE">
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
							<div class="col-sm-8">
								<input type="file" id="MAIN_IMAGE" name="MAIN_IMAGE"
											style="display: none;" onchange="uploadFile(this)" />
								<a onclick="fileType('MAIN_IMAGE')" id="MAIN_IMAGE_BTN" class="btn btn-mini btn-success"
									style="margin-bottom:5px; height: 30px; align: center;">选择文件</a>
								<img id="MAIN_IMAGE_PREVIEW" alt="" src="" style="height: 45px; max-width:230px;">
								<input type="text" name="MAIN_IMAGE_NAME" id="MAIN_IMAGE_NAME"
									accept="image/*" maxlength="32" placeholder="来选择任何文件" title="图片"
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
							<div class="col-sm-8">
								<textarea class="form-control" id="A_SERVICE_FEATURE">${art.SERVICE_FEATURE}</textarea>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" data-toggle="collapse" data-target="#A_TREATMENT_INFO_EDIT_AREA">优惠信息</label>
							<div class="col-sm-8">
								<span id="A_ORG_TREATMENT_INFO" style="display:none;">${art.TREATMENT_INFORMATION}</span>							
								<div id="A_TREATMENT_INFO_EDIT_AREA" class="collapse">
									<INPUT id="editor1" type="hidden" name="A_TREATMENT_INFO" value="">
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
							<label class="col-sm-2 control-label" data-toggle="collapse" data-target="#A_ROUTE_INTRO_EDIT_AREA">行程介绍</label>
							<div class="col-sm-8">
								<span id="A_ORG_ROUTE_INTRO" style="display:none;">${art.ROUTE_INTRODUCTION}</span>							
								<div id="A_ROUTE_INTRO_EDIT_AREA" class="collapse">
									<INPUT id="editor2" type="hidden" name="A_ROUTE_INTRO" value="">
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
							<label class="col-sm-2 control-label" data-toggle="collapse" data-target="#A_EXPENSE_INCLU_EDIT_AREA">费用包含</label>
							<div class="col-sm-8">
								<span id="A_ORG_EXPENSE_INCLU" style="display:none;">${art.EXPENSE_INCLUDED}</span>							
								<div id="A_EXPENSE_INCLU_EDIT_AREA" class="collapse">
									<INPUT id="editor3" type="hidden" name="A_EXPENSE_INCLU" value="">
									<iframe id="Web_Edit" name="Web_Edit" style="border:0px;" width="100%" height="300"
										src="static/editor3.jsp"></iframe>
									<span style="display: none;">
										<a href="static/editor3.jsp" target="Web_Edit">W3Schools.com</a>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" data-toggle="collapse" data-target="#A_BOOKING_NOTE_EDIT_AREA">预订须知</label>
							<div class="col-sm-8">
								<span id="A_ORG_BOOKING_NOTE" style="display:none;">${art.BOOKING_NOTE}</span>							
								<div id="A_BOOKING_NOTE_EDIT_AREA" class="collapse">
									<INPUT id="editor4" type="hidden" name="A_BOOKING_NOTE" value="">
									<iframe id="Web_Edit" name="Web_Edit" style="border:0px;" width="100%" height="300"
										src="static/editor4.jsp"></iframe>
									<span style="display: none;">
										<a href="static/editor4.jsp" target="Web_Edit">W3Schools.com</a>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">购买URL<span class="required-item">*</span></label>
							<div class="col-sm-8">
								<input class="form-control" id="A_PURCHASE_URL" type="text" value="${art.PURCHASE_URL}">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label">显示状态</label>
							<div class="col-sm-8">
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
							<div class="col-sm-8">
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
	            	<div class="col-md-8 form-group">
	            		<button type="button" onclick="saveArticle();" class="btn btn-primary">发布</button>
	            		<button type="button" onclick="goListPage();" class="btn btn-primary">返回</button>
	            	</div>
	            </div>
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
	<script src="static/ace/js/date-time/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="static/ace/js/chosen.jquery.js" type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>

</body>

<script type="text/javascript">
	$(top.hangge());
	
	$('#editor1').val($('#A_ORG_TREATMENT_INFO').html());
	$('#editor2').val($('#A_ORG_ROUTE_INTRO').html());
	$('#editor3').val($('#A_ORG_EXPENSE_INCLU').html());
	$('#editor4').val($('#A_ORG_BOOKING_NOTE').html());

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
		var opt_idx = $("#TOUR_OPTION")[0].selectedIndex;
		var opt_label =$("#TOUR_OPTION")[0].options[opt_idx].label;	
		var opt_id = $("#TOUR_OPTION").val().toString();
		var val_idx = $("#TOUR_OPTION_VALUE")[0].selectedIndex;
		var val_label =$("#TOUR_OPTION_VALUE")[0].options[val_idx].label;
		var val_id = $("#TOUR_OPTION_VALUE").val();
		
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
			
			$("#TOUR_OPTION").val("");
			$("#TOUR_OPTION_VALUE").val("");
			
			$('#TOUR_OPTION option:eq(' + opt_idx + ')').remove();
			$('#TOUR_OPTION_VALUE option:eq(' + val_idx + ')').remove();
			
		}
		else{
			if(opt_id == ""){
				$("#TOUR_OPTION").tips({
					side : 2,
					msg : '请选择选项',
					bg : '#AE81FF',
					time : 3
				});
			}
	
			if(val_id == ""){
				$("#TOUR_OPTION_VALUE").tips({
					side : 2,
					msg : '请选择选项',
					bg : '#AE81FF',
					time : 3
				});
			}
		}	
	}
	
	$('#TOUR_OPTION').change(function() {
	   	var option_id = this.value;
		if(option_id == ""){
			
		}
		else
		{
			$.ajax({
				type: "POST",
				url: 'tour/findOptValByMid',
				data: { MID : option_id },
				dataType:'json',
				success: function (res) {
					$("#TOUR_OPTION_VALUE>option").remove();
					$('#TOUR_OPTION_VALUE').append( "<option value=''>请选择</option>" );
					if ("success" == res.result) {
						var _v = res.data;
						for(var i = 0; i < _v.length; i++){
							$('#TOUR_OPTION_VALUE').append( "<option value='" + _v[i].ID + "'>" + _v[i].NAME + "</option>" );
						}
					} else {
						alert("FAILED");
					}
				}
			});
		}
	});

	$('#MAIN_IMAGE_PREVIEW').attr('src', "uploadFiles/uploadImgs/tour/" + '${art.MAIN_IMAGE}');
	var detail_image = '${art.DETAIL_IMAGE}';
	var detail_images = detail_image.split("|");
	
	for(var i = 0; i < detail_images.length; i++ ){
		if(detail_images[i] == ""){
			detail_images[i] = " ";
		}
		else
		{
			if(detail_images[i].trim() != "")
				$('#preImage' +(i + 1)).attr('src',"uploadFiles/uploadImgs/tour/" + detail_images[i]);
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
	}

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
	});

	/* function checkValidity(){
		
		
		return true;
	} */
	
	function saveArticle() {		
		if ($("#A_NAME").val() == '') {
			$("#A_NAME").tips({
				side : 3,
				msg : '请输入商品名称',
				bg : '#AE81FF',
				time : 3
			});			
			$("#A_NAME").focus();			
			return;
		}
		
		if ($("#A_DESCRIPTION").val() == "") {	
			$("#A_DESCRIPTION").tips({
				side : 2,
				msg : '请输入商品简介',
				bg : '#AE81FF',
				time : 3
			});	
			$("#A_DESCRIPTION").focus();
			return;
		}		
		
		if ($("#A_ADULT_EXPENSE").val() == "") {	
			$("#A_ADULT_EXPENSE").tips({
				side : 2,
				msg : '请输入价格(成人)',
				bg : '#AE81FF',
				time : 3
			});	
			$("#A_ADULT_EXPENSE").focus();
			return;
		}		
		
		if ($("#MAIN_IMAGE_NAME").val() == "") {
			//$(".page-content").scrollTop();
			/* $('.page-content').animate({ scrollTop: 0 },100);  */
			$("#MAIN_IMAGE_BTN").tips({
				side : 2,
				msg : '请选择商品图片',
				bg : '#AE81FF',
				time : 3
			});
			return;
		}
	
		if ($("#A_CHILD_EXPENSE").val() == "") {	
			$("#A_CHILD_EXPENSE").tips({
				side : 2,
				msg : '请输入价格(儿童)',
				bg : '#AE81FF',
				time : 3
			});
	
			$("#A_CHILD_EXPENSE").focus();
			return;
		}
				
		if ($("#A_SERVICE_FEATURE").val() == "") {	
			$("#A_SERVICE_FEATURE").tips({
				side : 2,
				msg : '请输入服务特点',
				bg : '#AE81FF',
				time : 3
			});
			$("#A_SERVICE_FEATURE").focus();
			return;
		}
				
		
		if ($("#A_PURCHASE_URL").val() == "") {	
			$("#A_PURCHASE_URL").tips({
				side : 2,
				msg : '请输入购买URL',
				bg : '#AE81FF',
				time : 3
			});
	
			$("#A_PURCHASE_URL").focus();
			return;
		}		
		
		$("#A_NAME").val(jQuery.trim($('#A_NAME').val()));
		$("#A_DESCRIPTION").val(jQuery.trim($('#A_DESCRIPTION').val()));
		$("#A_ADULT_EXPENSE").val(jQuery.trim($('#A_ADULT_EXPENSE').val()));
		$("#A_CHILD_EXPENSE").val(jQuery.trim($('#A_CHILD_EXPENSE').val()));
		$("#A_SERVICE_FEATURE").val(jQuery.trim($('#A_SERVICE_FEATURE').val()));
		$("#A_PURCHASE_URL").val(jQuery.trim($('#A_PURCHASE_URL').val()));		
		
		var _REGISTER = "";
		var _REGIST_TIME = "";
		
		var fd = new FormData();
	    var mainImage = $('#MAIN_IMAGE')[0].files;
	    var dImage1 = $('#dImage1')[0].files;
	    var dImage2 = $('#dImage2')[0].files;
	    var dImage3 = $('#dImage3')[0].files;
	    var dImage4 = $('#dImage4')[0].files;
	    var dImage5 = $('#dImage5')[0].files;
	    
	    var _ID = '${art.ID}';
	    
	    fd.append("ID",_ID);
   		fd.append("NAME", $("#A_NAME").val());
	 	fd.append("DESCRIPTION", $("#A_DESCRIPTION").val());
		fd.append("ADULT_EXPENSE", $("#A_ADULT_EXPENSE").val());
		fd.append("CHILD_EXPENSE", $("#A_CHILD_EXPENSE").val());
		fd.append("M_IMG_S", mainImage[0]);
		fd.append("D_IMG_S1",dImage1[0]);
		fd.append("D_IMG_S2",dImage2[0]);
		fd.append("D_IMG_S3",dImage3[0]);
		fd.append("D_IMG_S4",dImage4[0]);
		fd.append("D_IMG_S5",dImage5[0]);		
	 	fd.append("MAIN_IMAGE", $("#MAIN_IMAGE_NAME").val());
	 	fd.append("DETAIL_IMAGE", detail_images.join("|"));		
		fd.append("SERVICE_FEATURE", $("#A_SERVICE_FEATURE").val());
		fd.append("TREATMENT_INFORMATION", $("#editor1").val());
		fd.append("ROUTE_INTRODUCTION", $("#editor2").val());
		fd.append("EXPENSE_INCLUDED", $("#editor3").val());
		fd.append("BOOKING_NOTE", $("#editor4").val());
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
	    	_url = "tour/saveArt";
	    }
	    else
    	{
	    	_url = "tour/updateArt";
    	}
		$.ajax({
			type: "POST",
			url: _url,
			data: fd,
			contentType: false,
       	 	processData: false,
			success: function (data) {
				if ("success" == data.result) {
					$("#tourArticleForm").attr( "action", "tour/listTourArticle");
					$("#tourArticleForm").submit();
				} else {
					alert("FAILED");
				}
			}
		});		
	}
	
	function goListPage(){
		$("#tourArticleForm").attr( "action", "tour/listTourArticle" + "?" + $("#tourArticleForm").serialize());
		$("#tourArticleForm").submit();
	}

</script>