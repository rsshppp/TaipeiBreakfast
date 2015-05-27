<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 確保適當的呈現和觸控縮放效果，加入可視區域(Viewport) -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>賣方範本</title>
<!-- Bootstrap -->
<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
<!-- social icon 使用 -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Custom styles -->
<link href="<c:url value='/css/carousel.css'/>" rel="stylesheet">
<link rel="icon" href="favicon.ico">

<!-- HTML5 shim and Respond.js 讓 IE8 支援 HTML5 元素與媒體查詢 -->
<!-- 警告：Respond.js 無法在 file:// 協定下運作 -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
			<!-- jQuery (Bootstrap 所有外掛均需要使用) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
	<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
	<!-- Custom Javascript -->
	<script src="<c:url value='/js/holder.js'/>"></script>

<style>
#image1 {
	width: 60px;
	height: 60px;
}
</style>
<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<link href="<c:url value='/bootstrap/css/bootstrap.css'/>"
	rel="stylesheet">
<link href="<c:url value='/bootstrap/css/bootstrap-switch.css'/>"
	rel="stylesheet">
<script src="<c:url value='/bootstrap/js/bootstrap-switch.js'/>"></script>
</head>
<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"> <img alt="Brand"
					src="<c:url value='/image/proj_icon_2.png'/>"
					style="max-width: 100px; margin-top: -12px;">
				</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">最新消息</a></li>
					<li><a href="#">關於早餐通</a></li>
					<li><a href="#">聯絡我們</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<!-- 觸發模態窗 -->
					<li><a  href="<c:url value="/logout"/>">
							<span class="glyphicon glyphicon-log-out"></span> 登出
					</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div id="jumb" class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<div class="list-group" id="menu"></div>
					<a class="list-group-item text-center" href="<c:url value="/owner/selectmeal.jsp"/>">修改菜單</a> <a
						class="list-group-item text-center" href="<c:url value="/owner/addmeal.jsp.jsp"/>">新增菜單</a> <a
						class="list-group-item text-center" href="#">賣方功能</a> <a
						class="list-group-item text-center" href="#">賣方功能</a> <a
						class="list-group-item text-center" href="#">賣方功能</a> <a
						class="list-group-item text-center" href="#">賣方功能</a> <a
						class="list-group-item text-center" href="#">賣方功能</a>
				</div>
				<div class="col-md-10" style="margin-top: 20px;">

					<!-- 功能放這邊！！！ -->
					<div id="dv01">
						<form id="form1"
							action="<c:url value='/owner/mealaction!mealchange'/>"
							method="POST" enctype=multipart/form-data>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2">
									店家: <input type="hidden" name="bean.shopID"
										value="${param.shopID}">
								</div>
								<div class="col-sm-5">
									<!-- 					<input type="text" id="shopname" name="bean.shopName" readonly/> -->
									<label id="shopname"></label>
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2">

									餐點名稱: <input type="hidden" name="bean.mealID"
										value="${param.mealID}">
								</div>
								<div class="col-sm-3">
									<!-- 					<input type="hidden" id="mealname" name="bean.mealName" /> -->
									<label id="mealname"></label>
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2">餐點種類:</div>
								<div class="col-sm-3">
									<select id="select2" name="bean.mealKindID"></select>
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2">價格:</div>
								<div class="col-sm-3">
									<input type="text" id="price" value="60" name="bean.price" />
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-md-2">
									修改圖片: <input type="hidden" name="bean.imageChange"
										id="imageChange" value="false">
								</div>
								<div id="imagediv" class="col-sm-3">
									<div id="result" style="width: 60px; height: 60px">
										<img id="image1" src="">
									</div>
									<input type="checkbox" name="my-checkbox" id="check1">
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2 ">

									<a class='btn btn-default' role='button'
										href="<c:url value='/owner/selectmeal.jsp'/>">回菜單</a>
								</div>
								<div class="col-sm-3 ">
									<input type="submit" class='btn btn-default' value="修改">
								</div>
							</div>
						</form>
					</div>

					<script>
						var shopID = ${param.shopID};
						var mealID = ${param.mealID};
						var selone = {shopID : shopID,mealID : mealID};
						var kindlist;
						var image;
						$("[name='my-checkbox']").bootstrapSwitch();

						$('#select2')
								.change(
										function() {
											var file = document
													.getElementById('file').files[0];
											if (!file) {
												var ss = $('#select2').val();
												$('#image1').attr(
														"src",
														"<s:url action='imageAction!getMealImage?typeId="
																+ ss + "' />");
											}
										})

						$
								.ajax({
									url : "<c:url value='/owner/mealaction!mealKind'/>",
									data : "",
									type : "POST",
									dataType : 'text',

									success : function(msg) {
										kindlist = JSON.parse(msg);
										var ss = JSON.parse(kindlist.redata);
										console.log(ss);
										$.each(ss, function(a, b) {
											$('#select2').append(
													"<option value='" + b.mealKindID + "'>"
															+ b.mealKindName
															+ "</option>");
										});
										meal();
									},

									error : function(xhr, ajaxOptions,
											thrownError) {
										alert(xhr.status);
										alert(thrownError);
									}
								});
						function meal() {
							$.get("<c:url value='/owner/mealaction!onemeal'/>",
									selone, function(data) {
										var list = JSON.parse(data.redata);
										console.log(list);
										console.log(list.shopName);

										$('#shopname').append(list.shopName);
										$('#mealname').append(list.mealName);
										$('#price').val(list.price);
										$(
												"#select2 option[value='"
														+ list.mealKindID
														+ "']").prop(
												'selected', true);
										$('#image1').attr(
												"src",
												"<s:url action='imageAction!getMealImage?imageId="
														+ mealID + "' />");
										//image=list.mealImage;
										//byteimage(image);
									});
						}
						$('input[name="my-checkbox"]')
								.on(
										'switchChange.bootstrapSwitch',
										function(event, state) {
											if (state) {
												$('#imageChange').val('true');
												$("#imagediv")
														.append(
																'<input type="file" accept="image/*" id="file" name="bean.mealImage" />');
												var ss = $('#select2').val();
												$('#image1').attr(
														"src",
														"<s:url action='imageAction!getMealImage?typeId="
																+ ss + "' />");
											} else {
												$('#imageChange').val('false');
												$("#file").remove();
												$('#image1').attr(
														"src",
														"<s:url action='imageAction!getMealImage?imageId="
																+ mealID
																+ "' />");
											}
										});
					</script>
					<script>
						function byteimage(input) {
							if (document.getElementById("result")
									.hasChildNodes()) {
								document
										.getElementById("result")
										.removeChild(
												document
														.getElementById("result").childNodes[0]);
							}
							var data = encode(new Uint8Array(input));
							;
							var img = document.createElement("img");
							img.id = "image2";
							img.src = 'data:image/jpg;base64,' + data;
							document.getElementById("result").appendChild(img);
						}
						function encode(input) {
							var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
							var output = "";
							var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
							var i = 0;

							while (i < input.length) {
								chr1 = input[i++];
								chr2 = i < input.length ? input[i++]
										: Number.NaN; // Not sure if the index 
								chr3 = i < input.length ? input[i++]
										: Number.NaN; // checks are needed here

								enc1 = chr1 >> 2;
								enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
								enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
								enc4 = chr3 & 63;

								if (isNaN(chr2)) {
									enc3 = enc4 = 64;
								} else if (isNaN(chr3)) {
									enc4 = 64;
								}
								output += keyStr.charAt(enc1)
										+ keyStr.charAt(enc2)
										+ keyStr.charAt(enc3)
										+ keyStr.charAt(enc4);
							}
							return output;
						}
						$(document)
								.on(
										'change',
										'#file',
										function() {
											var file = document
													.getElementById('file').files[0];
											console.log(file);
											if (file) {
												var reader = new FileReader();
												reader.onload = function(event) {
													var txt = event.target.result;
													var img = document
															.getElementById("image1");
													img.src = txt;
												};
											} else {
												var ss = $('#select2').val();
												$('#image1').attr(
														"src",
														"<s:url action='imageAction!getMealImage?typeId="
																+ ss + "' />");
											}
											reader.readAsDataURL(file);
										})
					</script>
					<!-- 功能放這邊！！！ -->

				</div>
			</div>
			<!-- 互動視窗（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">登入</h4>
						</div>
						<div class="modal-body">
							<div id="test"></div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		</div>
	</div>

	<div class="container marketing">
		<footer>
			<p class="pull-left">&copy; 2015 Taipei Breakfast. All rights
				reserved. &middot;</p>
			<p class="pull-left">
				<a href="#">隱私權政策</a> &middot; <a href="#">常見問題</a>
			</p>
			<p class="pull-right">
				<a href="https://www.facebook.com/taipeibreakfast"><i
					id="social" class="fa fa-facebook-square fa-3x social-fb"></i></a> <a
					href="https://twitter.com/taipeibreakfast"><i id="social"
					class="fa fa-twitter-square fa-3x social-tw"></i></a> <a
					href="https://plus.google.com/+TaipeiBreakfast-page"><i
					id="social" class="fa fa-google-plus-square fa-3x social-gp"></i></a> <a
					href="mailto:taipeibreakfast@gmail.com"><i id="social"
					class="fa fa-envelope-square fa-3x social-em"></i></a>
			</p>

		</footer>
	</div>
	<!-- /.container -->


</body>
</html>
