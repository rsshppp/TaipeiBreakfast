<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Taipei Breakfast - 台北早餐通</title>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href="../css/carousel.css" rel="stylesheet">
<link rel="../icon" href="../favicon.ico">

<!-- HTML5 shim and Respond.js 讓 IE8 支援 HTML5 元素與媒體查詢 -->
<!-- 警告：Respond.js 無法在 file:// 協定下運作 -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
<style>
#footer { width:100%; height:100px; position:fixed; bottom:0px; z-index:1; }
tr{
 height:20px;
}
</style>
		
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
						data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="../index.html"> <img alt="Brand"
					src="../image/proj_icon_2.png"
					style="max-width: 100px; margin-top: -12px;">
				</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">最新消息</a></li>
					<li><a href="#">關於早餐通</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a data-toggle="tab" href="/member/MemberCenter.jsp"> 
						<span class="glyphicon glyphicon-user"></span> 會員中心</a>
					</li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<select class="form-control" id="sel1">
								<option>地區</option>
								<option>中正區</option>
								<option>大同區</option>
								<option>中山區</option>
								<option>松山區</option>
								<option>大安區</option>
								<option>萬華區</option>
								<option>信義區</option>
								<option>士林區</option>
								<option>北投區</option>
								<option>內湖區</option>
								<option>南港區</option>
								<option>文山區</option>
							</select> <select class="form-control" id="sel1">
								<option>店家</option>
								<option>早餐吃到飽</option>
								<option>早餐吃不飽</option>
								<option>早餐吃很少</option>
							</select> <input type="text" class="form-control" placeholder="關鍵字">
						</div>
						<button type="submit" class="btn btn-default">搜尋早餐</button>
					</form>
			</div>
		</div>
	</nav>

	<div id="jumb" class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-md-2" style="top: 30px">
					<div class="list-group" id="menu"></div>
					<a class="list-group-item text-center" href="MemberUpdate.jsp">更新資料</a> 
					<a class="list-group-item text-center" href="MemberCenter.jsp">會員資料</a> 
					<a class="list-group-item text-center" href="MemberDelete.jsp">刪除帳號</a> 
					<a class="list-group-item text-center" href="#">會員功能</a> 
					<a class="list-group-item text-center" href="#">會員功能</a> 
					<a class="list-group-item text-center" href="#">會員功能</a> 
					<a class="list-group-item text-center" href="#">會員功能</a>
				</div>
				<div class="col-md-10" style="text-align: left; top: 30px;">

					<form action="MemberChPassServlet.controller" method="post">
						<table>
							<tr>
								<th><h3>修改密碼</h3></th>
							</tr>
							<tr>
								<td>Email :</td>
								<td><input type="text" name="email"></td>
								<td>${errors.acc}</td>
							</tr>
							<tr>
								<td>密碼 :</td>
								<td><input type="password" name="pass"></td>
							</tr>
<!-- 							<tr> -->
<!-- 								<td>確認密碼 :</td> -->
<!-- 								<td><input type="password" name="pass02"></td> -->
<%-- 								<td>${errors.pwd}</td> --%>
<!-- 							</tr> -->

							<tr>
								<td></td>
								<td>${action}</td>
							</tr>

						</table>

						<div style="text-align: center; margin: 50px auto;">
							<span
								style="text-align: center; position: relative; left: -200px;">
								<input type="submit" name="insertMemberActive" value="修改">
							</span> <span
								style="text-align: center; color: red; position: relative; left: -100px;">
								<input type="reset" value="清空重填">
							</span>
						</div>

					</form>
				</div>
			</div>
			
		</div>
	</div>

	<div class="container marketing">
		<footer>
			<p class="pull-left">&copy; 2015 Taipei Breakfast. All rights reserved. &middot;</p>
			<p class="pull-left">
				<a href="#">隱私權政策</a> &middot; 
				<a href="#">常見問題</a> &middot; 
				<a href="#">聯絡我們</a>
			</p>
			<p class="pull-right">
				<a href="https://www.facebook.com/taipeibreakfast">
					<i id="social" class="fa fa-facebook-square fa-3x social-fb"></i>
				</a> 
				<a href="https://twitter.com/taipeibreakfast">
					<i id="social" class="fa fa-twitter-square fa-3x social-tw"></i>
				</a> 
				<a href="https://plus.google.com/+TaipeiBreakfast-page">
					<i id="social" class="fa fa-google-plus-square fa-3x social-gp"></i>
				</a> 
				<a href="mailto:taipeibreakfast@gmail.com">
					<i id="social" class="fa fa-envelope-square fa-3x social-em"></i>
				</a>
			</p>
		</footer>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../bootstrap/js/bootstrap.min.js"></script>
	<script src="../js/holder.js"></script>
</body>
</html>
