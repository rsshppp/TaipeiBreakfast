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
<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"
	rel="stylesheet" type="text/css" />
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
.imgsimple {
	width: 60px;
	height: 60px;
}
</style>

<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.js'/>"></script>
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css" />
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
						class="list-group-item text-center" href="<c:url value="/owner/addmeal.jsp"/>">新增菜單</a> <a
						class="list-group-item text-center" href="#">賣方功能</a> <a
						class="list-group-item text-center" href="#">賣方功能</a> <a
						class="list-group-item text-center" href="#">賣方功能</a> <a
						class="list-group-item text-center" href="#">賣方功能</a> <a
						class="list-group-item text-center" href="#">賣方功能</a>
				</div>
				<div class="col-md-10" style="margin-top: 20px;">

					<!-- 功能放這邊！！！ -->
					<div>
						店家:
						<select id="shopsel">

						</select>
					</div>
					<div id="creattable" style="margin-top: 10px;"></div>

					<script>
						var jsondata = {
							owerID : 1
						};
						var shopID;
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
									createtable();
								});
						$('#shopsel')
								.change(
										function() {
											shopID = {
												shopID : $('#shopsel').val()
											}
											while (document.getElementById(
													"creattable")
													.hasChildNodes()) {
												document
														.getElementById(
																"creattable")
														.removeChild(
																document
																		.getElementById("creattable").childNodes[0]);
											}
											createtable();
										})
						function createtable() {
							$('#creattable')
									.append("<table id='menu1' class='display'></table>");
							$('#menu1')
									.append(
											"<thead><tr><th>圖片</th><th>名稱</th><th>種類</th><th>價格</th><th>狀態</th><th>修改</th></tr></thead>");
							$('#menu1').append("<tbody id='tby'></tbody>");
							console.log(shopID);

							$.get("<c:url value='/owner/mealaction!menulist'/>",
											shopID,function(data) {
												if(data.redata!=null){
												var list = JSON.parse(data.redata);
												$.each(list,function(i, v) {
														$('#tby').append("<tr><td><img class='img-thumbnail imgsimple' src='<s:url action='imageAction!getMealImage?imageId="
																							+ v.mealID
																							+ "' />'></td><td>"
																							+ v.mealName
																							+ "</td><td>"
																							+ v.mealKindName
																							+ "</td><td>"
																							+ v.price
																							+ "</td><td><input type='button' value='"
																							+ v.mealStatus
																							+ "' onclick='changestudes(event)' id='sbtn"
																							+ v.mealID
																							+ "' /></td><td><a class='btn btn-default' role='button' href='<c:url value='/owner/changemeal.jsp'/>?mealID="
																							+ v.mealID
																							+ "&shopID="
																							+ shopID.shopID
																							+ "'>修改</a></td></tr>");
																	var t = "#sbtn"
																			+ v.mealID;
																	if (v.mealStatus == "販售中") {
																		$(t)
																				.addClass(
																						"btn btn-primary");
																	} else if (v.mealStatus == "下架中") {
																		$(t)
																				.addClass(
																						"btn btn-warning");
																	}
																	})
																}
												$('#menu1').DataTable({
												    "iDisplayLength": 7,
												    "aLengthMenu": [[7, 10, 20, -1], [7, 10, 20, "All"]],
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

						function changestudes(event) {
							var btid = event.target.id;
							var mealid = {
								mealID : btid.split("sbtn")[1]
							};
							console.log(mealid);
							$.get("<c:url value='/owner/mealaction!changestatus'/>",
											mealid,
											function() {
												var Old_Class = $("#" + btid)
														.attr('class');
												$("#" + btid).removeClass(
														Old_Class);
												if (Old_Class == "btn btn-primary") {
													$("#" + btid).val("下架中");
													$("#" + btid).addClass(
															"btn btn-warning");
												} else if (Old_Class == "btn btn-warning") {
													$("#" + btid).val("販售中");
													$("#" + btid).addClass(
															"btn btn-primary");
												}
											})
						}
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
