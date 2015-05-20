<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page language="java" pageEncoding="UTF-8"%>
<title>Taipei Breakfast - 台北早餐通</title>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="../css/carousel.css" rel="stylesheet">
<link rel="icon" href="../favicon.ico">

<style>
#footer { width:100%; height:100px; position:fixed; bottom:0px; z-index:1; }
tr{
 height:30px;
} 
</style>

</head>
<body>

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
						</select> 
						<select class="form-control" id="sel1">
							<option>店家</option>
							<option>早餐吃到飽</option>
							<option>早餐吃不飽</option>
							<option>早餐吃很少</option>
						</select> 
						<input type="text" class="form-control" placeholder="關鍵字">
					</div>
					<button type="submit" class="btn btn-default">搜尋早餐</button>
				</form>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div style="text-align: left; margin: 80px 20px 50px 30%;">
	
	<form action="MemberInsertServlet.controller" method="post">
		<table>
			<tr><th><h3>加入會員</h3></th></tr>
			<tr>
				<td>Email :</td>
				<td><input type="text" name="email" id="idEmail" onchange="checkmail()"></td>
				<td>${erro.acc}</td>
			</tr>
			<tr>
				<td>密碼 :</td>
				<td><input type="password" name="pass01"></td>
			</tr>
			<tr>
				<td>確認密碼 :</td>
				<td><input type="password" name="pass02"></td>
				<td>${erro.pwd}</td>
			</tr>
			<tr>
				<td> </td>
			</tr>
			<tr>
				<td>姓氏 :</td>
				<td><input type="text" name="first"></td>
			</tr>
			<tr>
				<td>名字 :</td>
				<td><input type="text" name="last"></td>
			</tr>
			
			<tr>
				<td> </td>
			</tr>
			<!-- 只須手機即可 修改by Steven  
			<tr>
				<td>聯絡電話 :</td>
				<td><input type="text" name="tel"></td>
				<td>${erro.tel}</td>
			</tr>
			<tr>
				<td></td>
				<td>例:(0212345678)</td>
			</tr>
			-->
			<tr>
				<td>手機 :</td>
				<td><input type="text" name="pho"></td>
				<td>${erro.pho}</td>
			</tr>
			<tr>
				<td></td>
				<td>例:(0999123456)</td>
			</tr>
			<tr>
				<td> </td>
			</tr>
			
			<tr>
				<td>地址 :</td>
				<td><input type="text" name="addr"></td>
			</tr>
			
			<tr>
				<td>圖片 :</td>
				<td><input type="file" name="img" accept="image/*"></td>
			</tr>
			
			<tr>
				<td></td>
				<td>${action}</td>
			</tr>
			
		</table>
		<script>
// 		function checkmail(){
// 	 		var email = $("#vote_idEmail").attr("value");
// 	 		if(email==""){
// 	 			alert("Please enter Email");
// 	 			$("#vote_idEmail").focus();
// 	 			return false;
// 	 		}else{
// 	 			var emailRegxp = /[\w-]+@([\w-]+\.)+[\w-]+/; 
// 	 			if (emailRegxp.test(email) != true){
// 	 				alert("電子信箱格式錯誤");
// 	 				$("#vote_idEmail").focus();
// 	 				$("#vote_idEmail").select();
// 	 				return false;
// 	 			}
// 	 		}
// 		}
		</script>
	<div style="text-align: center; margin: 50px auto;">
		<span style="text-align: center; position: relative; left:-200px;">
			<input type="submit" name="insertMemberActive" value="註冊">
		</span>
		<span style="text-align: center; color:red; position: relative; left:-100px;">
			<input type="reset" value="清空重填">
		</span>
	</div>
		
		</form>
	</div>

	<div class="container inner">
		<div class="row">
			<div class="col-md-4 col-sm-6 inner"></div>
			<div class="col-md-4 col-sm-6 inner">

			</div>

			<div class="col-md-4 col-sm-6 inner"></div>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		</div>
	</div>

	<div class="container marketing">
		<footer>
			<p class="pull-left">&copy; 2015 Taipei Breakfast. All rights
				reserved. &middot;</p>
			<p class="pull-left">
				<a href="#">隱私權政策</a> &middot; 
				<a href="#">常見問題</a> &middot; 
				<a href="#">聯絡我們</a>
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


</body>
</html>