<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- 確保適當的呈現和觸控縮放效果，加入可視區域(Viewport) -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<title>廣告申請</title>
		<!-- Bootstrap -->
		
		<!-- social icon 使用 -->
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
		<!-- Custom styles -->
		<link href="<c:url value='/css/carousel.css'/>" rel="stylesheet">
		<link rel="icon" href="favicon.ico">
		
		<!-- jQuery (Bootstrap 所有外掛均需要使用) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
		
		<!-- Custom Javascript -->
		<script src="<c:url value='/js/holder.js'/>"></script>

		<!-- HTML5 shim and Respond.js 讓 IE8 支援 HTML5 元素與媒體查詢 -->
		<!-- 警告：Respond.js 無法在 file:// 協定下運作 -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		<script src="<c:url value='/js/jquery.min.js'/>"></script>
		<script src="<c:url value='/js/jquery.form.js'/>"></script>
		<link href="<c:url value='/bootstrap/css/bootstrap.css'/>"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="<c:url value='/bootstrap/js/bootstrap.js'/>"></script>
		<script src="<c:url value='/js/lightbox.min.js'/>"></script>
		<link href="<c:url value='/css/lightbox.css'/>" rel="stylesheet"
			type="text/css" />
		<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.min.css"
			rel="stylesheet" type="text/css" />
		<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
		<style>
		#image1 {
			width: 500px;
			height: 300px;
		}
		
		</style>
	</head>
	<body>
		<!-- Fixed navbar -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">
						<img alt="Brand" src="<c:url value='/image/proj_icon_2.png'/>" style="max-width:100px; margin-top: -12px;" >
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
				</div><!--/.nav-collapse -->
			</div>
		</nav>

		<div id="jumb" class="jumbotron">
			<div class="container">
				<div class="row">
					<div class="col-md-2">
						<div class="list-group" id="menu"></div>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
					</div>
					<div class="col-md-10">
		
						<!-- 功能放這邊！！！ -->
						<div style="margin-bottom: 20px;margin-top: 20px">
		<button type="button" class="btn btn-primary btn" data-toggle="modal"
			data-target="#insertAD">申請廣告</button>
	</div>
	<div id="adtable"></div>
<script>
		createtable();
		function createtable() {
			while (document.getElementById("adtable").hasChildNodes()) {
				document.getElementById("adtable").removeChild(
						document.getElementById("adtable").childNodes[0]);
			}
			$('#adtable')
					.append(
							'<table id="admenu" class="display"><thead><tr><th>店家</th><th>title</th><th>內文</th><th>天數</th><th>審核狀態</th></tr></thead><tbody id="tby"></tbody></table>');
			$.get("<c:url value='/owner/adAction!aDTable'/>", "",
					function(data) {
						var list = JSON.parse(data.redata);
						console.log(list);
						$.each(list, function(i, v) {
							$('#tby').append(
									"<tr><td>" + v.shopName + "</td><td>"
											+ v.title + "</td><td>" + v.context
											+ "</td><td>" + v.days
											+ "</td><td>" + v.adStatus
											+ "</td></tr>");
						})
						$('#admenu').DataTable({
						    "oLanguage":{
						    "sProcessing": "處理中...",
						      "sLengthMenu": "一頁顯示 _MENU_ 筆記錄",
						      "sZeroRecords": "無符合資料",
						      "sInfo": "目前顯示：_START_ 至 _END_, 總筆數：_TOTAL_",
						      "sInfoEmpty": "無任何資料",
						      "sInfoFiltered": "(關鍵字總筆數 _MAX_)",
						      "sInfoPostFix": "",
						      "sSearch": "關鍵字",
						      "sUrl": "",
						      "oPaginate": {
						          "sFirst":    "首頁",
						          "sPrevious": "上頁",
						          "sNext":     "下頁",
						          "sLast":     "末頁"
						      }
						    }
						  });
					})
		}
	</script>
					</div>
				</div>
				<!-- 互動視窗（Modal） -->
	<div class="modal fade" id="insertAD" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">申請廣告</h4>
				</div>
				<div class="modal-body">
					<!-- 	     	 新增廣告 -->
					<div class="container-fluid">
						<div class="col-sm-4">

							<form action="<c:url value='/owner/adAction!addAD'/>" id="form1"
								method="POST" enctype=multipart/form-data>
								<div class="row" style="margin-top: 5px">
									<div class="col-sm-3">廣告店家:</div>
									<div class="col-sm-7">
										<select id="shopsel" name="readform.shopID">

										</select>
									</div>
								</div>
								<div class="row" style="margin-top: 5px">
									<div class="col-sm-3">廣告天數:</div>
									<div class="col-sm-7">
										<input type="number" max="30" min="1" name="readform.days"
											value="1">
									</div>
								</div>
								<div class="row" style="margin-top: 5px">
									<div class="col-sm-3">廣告title:</div>
									<div class="col-sm-7">
										<input type="text" name="readform.title">
									</div>
								</div>
								<div class="row" style="margin-top: 5px">
									<div class="col-sm-3">廣告短文:</div>
									<div class="col-sm-7">
										<textarea name="readform.context" rows="4" cols="20"
											id="txtarea"></textarea>
									</div>
								</div>
								<div class="row" style="margin-top: 5px">
									<div class="col-sm-3">廣告圖片:</div>
									<div class="col-sm-7">
										<input name="readform.adImage" type="file" accept="image/*"
											id="file" name="bean.mealImage" />
									</div>
								</div>
								<div class="row" style="margin-top: 5px">
									<div class="col-sm-1" style="margin: 5px">
										<input type="submit" class='btn btn-default' tabindex="0" value="新增">
									</div>
								</div>
							</form>
						</div>
						<div class="col-sm-7">
							<div>
								預覽:<br> <a href="img/image-1.jpg" data-lightbox="image-1"
									data-title="" id="imga"></a>
							</div>
						</div>
					</div>
					<!-- 	     	 新增廣告 -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="over" data-dismiss="modal">Close</button>
<!-- 					<button type="button" onclick="" class="btn btn-primary">Save changes</button> -->
				</div>
			</div>
		</div>
	</div>
	<script>
						var jsondata = {
							owerID : 1
						};
						$.get("<c:url value='/owner/mealaction!shoplist'/>",
								jsondata, function(data) {
									var list = JSON.parse(data.redata);
									console.log(list);
									$.each(list, function(u, i) {
										$('#shopsel').append(
												"<option value='" + i.shopID + "'>"
														+ i.shopName
														+ "</option>");
									})
									shopID = {
										shopID : $('#shopsel').val()
									};
								});
						$('#form1').ajaxForm({
							complete : function(msg) {
								createtable();
// 								$('#over').click();
							    $(".modal-backdrop").remove();
// 							    $("#insertAD").hide();
							    $("#insertAD").attr("aria-hidden","true");
								$("#insertAD").removeClass("in");
// 								$("#insertAD").modal();
// 								$('#insertAD').modal('toggle');
// 								$('body').removeClass('modal-open');
// 								$('.modal-backdrop').remove();
								//$('.modal-backdrop').hide();
							}
						})
						$('#txtarea').keyup(
								function() {
									$('#imga').attr("data-title",
											$('#txtarea').val())
								})
				
						function ProcessFile(e) {
							var file = document.getElementById('file').files[0];
							console.log(file);
							if (file) {
								if (document.getElementById("imga")
										.hasChildNodes()) {
									document
											.getElementById("imga")
											.removeChild(
													document
															.getElementById("imga").childNodes[0]);
								}
								var reader = new FileReader();
								reader.onload = function(event) {
									var txt = event.target.imga;
									var img = document.createElement("img");
									img.id = "image1";
									img.src = txt;
									document.getElementById("imga")
											.appendChild(img);
									$('#image1').addClass("img-thumbnail");
									$('#imga').attr("href", txt);
								};
							} else {
								if (document.getElementById("imga")
										.hasChildNodes()) {
									document
											.getElementById("imga")
											.removeChild(
													document
															.getElementById("imga").childNodes[0]);
								}
							}
							reader.readAsDataURL(file);
						}
						function contentLoaded() {
							document.getElementById('file').addEventListener(
									'change', ProcessFile, false);
						}
						window.addEventListener("DOMContentLoaded",
								contentLoaded, false);
					</script>
			</div>
		</div>

		<div class="container marketing">
			<footer>
				<p class="pull-left">&copy; 2015 Taipei Breakfast. All rights reserved. &middot; </p>
				<p class="pull-left"><a href="#">隱私權政策</a> &middot; <a href="#">常見問題</a></p>
				<p class="pull-right">
				<a href="https://www.facebook.com/taipeibreakfast"><i id="social" class="fa fa-facebook-square fa-3x social-fb"></i></a>
	            <a href="https://twitter.com/taipeibreakfast"><i id="social" class="fa fa-twitter-square fa-3x social-tw"></i></a>
	            <a href="https://plus.google.com/+TaipeiBreakfast-page"><i id="social" class="fa fa-google-plus-square fa-3x social-gp"></i></a>
	            <a href="mailto:taipeibreakfast@gmail.com"><i id="social" class="fa fa-envelope-square fa-3x social-em"></i></a>
				</p>
				
			</footer>
		</div><!-- /.container -->	

</body>
</html>
