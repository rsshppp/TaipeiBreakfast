<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Taipei Breakfast - 台北早餐通</title>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
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
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="../index.html"> 
					<img alt="Brand" src="../image/proj_icon_2.png"
						style="max-width: 100px; margin-top: -12px;">
				</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">最新消息</a></li>
					<li><a href="#">關於早餐通</a></li>
				</ul>
					<ul class="nav navbar-nav navbar-right">
<!-- 					登入後由會員中心的li取代登入的li -->
<!-- 						<li><a data-toggle="tab" href="member/MemberCenter"> -->
<!-- 							<span class="glyphicon glyphicon-user"></span> 會員中心 </a> -->
<!-- 						</li> -->
						<!-- 觸發模態窗 -->
						<li><a data-toggle="modal" data-target="#myModal" href="#">
							<span class="glyphicon glyphicon-log-in"></span> 登入 </a>
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
	
<!-- 	<form action="MemberLosePassServlet.controller" method="post"> -->
	<form action="<c:url value='/pe/duAction!losePassword.action'/>" method="post">
		<table>
			<tr><th><h3>忘記密碼</h3></th></tr>
			<tr>
				<td>Email :</td>
<!-- 				<td><input type="text" name="email"></td> -->
				<td><input type="text" name="mf.memberEmail"></td>
				<td>${errors.acc}</td>
			</tr>  
<!-- 			<tr> -->
<!-- 				<td>姓氏 :</td> -->
<!-- 				<td><input type="text" name="first"></td> -->
<%-- 				<td>${errors.first}</td> --%>
<!-- 			</tr>   -->
			
			<tr>
				<td></td>
				<td>${errors.action}</td>
			</tr>
			
		</table>
		
	<div style="text-align: center; margin: 50px auto;">
		<span style="text-align: center; position: relative; left:-200px;">
			<input type="submit" value="確認">
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

		
		
		
		<!-- 互動視窗（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   			<div class="modal-dialog modal-sm">
      		<div class="modal-content">
      		
        	<div class="modal-header">
            	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
			<h4 class="modal-title" id="myModalLabel">
			歡迎光臨
            </h4>
		</div>
		<div class="modal-body">
		<!-- 登入頁籤 -->
			<div role="tabpanel">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#membersign" aria-controls="membersign" role="tab" data-toggle="tab">會員</a></li>
					<li role="presentation"><a href="#ownersign" aria-controls="ownersign" role="tab" data-toggle="tab">賣方</a></li>
				</ul>
				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="membersign">	
						<form class="form-signin">
							<h2 class="form-signin-heading">會員登入</h2>
							<label for="inputEmail1" class="sr-only">Email 地址</label>
							<input type="email" id="inputEmail1" class="form-control" placeholder="Email 地址" required autofocus>
							<label for="inputPassword1" class="sr-only">密碼</label>
							<input type="password" id="inputPassword1" class="form-control" placeholder="密碼" required>
							<div class="checkbox">
								<label>
									<input type="checkbox" value="remember-me"> 記住密碼
								</label>
							</div>
							<button class="btn btn-lg btn-info btn-block" type="submit">登入</button>
							<!-- 新會員註冊buttion連結至 insertMember1.jsp by Steven -->
							<a href="http://localhost:8080/TB/pages/insertMember1.jsp" type="button" class="btn btn-lg btn-primary btn-block">新會員註冊</a>
							<!--  
							<button class="btn btn-lg btn-primary btn-block" type="button">新會員註冊</button>
							-->
							<br>
							<div class="text-right"><h5><a href="http://localhost:8080/TB/pages/losePassMember.jsp">忘記密碼?</a></h5></div>
						</form>
					</div>
    				<div role="tabpanel" class="tab-pane" id="ownersign">
    					<form class="form-signin">
        					<h2 class="form-signin-heading">賣方登入</h2>
							<label for="inputEmail2" class="sr-only">Email 地址</label>
							<input type="email" id="inputEmail2" class="form-control" placeholder="Email 地址" required autofocus>
							<label for="inputPassword2" class="sr-only">密碼</label>
							<input type="password" id="inputPassword2" class="form-control" placeholder="密碼" required>
							<div class="checkbox">
								<label>
									<input type="checkbox" value="remember-me"> 記住密碼
								</label>
							</div>
							<button class="btn btn-lg btn-info btn-block" type="submit">登入</button>
							<button class="btn btn-lg btn-primary btn-block" type="button">合作聯繫</button>
							<br>
							<div class="text-right"><h5><a href="">忘記密碼?</a></h5></div>
						</form>
    				</div>
 				</div>
			</div>
         </div>
         
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
		
		<!-- jQuery (Bootstrap 所有外掛均需要使用) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
		<script src="../bootstrap/js/bootstrap.min.js"></script>
		<!-- Custom Javascript -->
		<script src="../js/holder.js"></script>

</body>
</html>