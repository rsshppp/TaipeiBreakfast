<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<!-- social icon 使用 -->
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
		<!-- Custom styles -->
		<link href="css/carousel.css" rel="stylesheet">
		<link rel="icon" href="favicon.ico">

		<!-- HTML5 shim and Respond.js 讓 IE8 支援 HTML5 元素與媒體查詢 -->
		<!-- 警告：Respond.js 無法在 file:// 協定下運作 -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<div id="jumb" class="jumbotron">
			<div class="container">
				<div class="row">
					<form class="form-signin" action="adminlogin">
						<h2 class="form-signin-heading text-danger text-center"><strong>台北早餐通管理介面</strong></h2>
						<label for="inputAdmin1" class="sr-only">帳號</label>
						<input type="text" name="user" id="inputAdmin1" class="form-control" placeholder="帳號" required autofocus>
						<label for="inputPassword1" class="sr-only">密碼</label>
						<input type="password" name="password" id="inputPassword1" class="form-control" placeholder="密碼" required>
						<div class="checkbox text-right">
							<label>
								<input type="checkbox" value="remember-me"> 記住密碼
							</label>
						</div>
						<button class="btn btn-lg btn-danger btn-block" type="submit">登入</button>
						<br>
						
					</form>
					<h6 class="text-center">1.本頁面僅供網站管理者使用，如造成不便，尚祈見諒。</h6>
					<h6 class="text-center">2.倘若有使用上的問題，煩請於上班時段逕洽資訊部門。</h6>
					<br>
					<div class="text-center">
					<input type="button" value="關閉視窗" onClick="JavaScript:self.close()">
					</div>
				</div>
				
			</div>
		</div>
		<!-- jQuery (Bootstrap 所有外掛均需要使用) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
		<script src="bootstrap/js/bootstrap.min.js"></script>
		<!-- Custom Javascript -->
</body>
</html>
