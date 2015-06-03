<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理首頁</title>
		
		<!-- Bootstrap -->
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
<%-- 		<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>  --%>
		
		<!-- social icon 使用 -->
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
		
		<!-- Custom styles -->
		<link href="<c:url value='/css/carousel.css'/>" rel="stylesheet">
		<link rel="icon" href="favicon.ico">
		
		<!-- jQuery (Bootstrap 所有外掛均需要使用) -->
		<script src="<c:url value='/js/jquery.min.js'/>"></script>
		<script src="<c:url value='/js/jquery.form.js'/>"></script>

		<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
		<link href="https://cdn.datatables.net/1.10.7/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/lightbox.css'/>" rel="stylesheet" type="text/css" />
		<script src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
		<script src="<c:url value='/js/lightbox.min.js'/>"></script> 
		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.min.js'/>"></script>

		<!-- Custom Javascript -->
		<script src="<c:url value='/js/holder.js'/>"></script>

		<!-- HTML5 shim and Respond.js 讓 IE8 支援 HTML5 元素與媒體查詢 -->
		<!-- 警告：Respond.js 無法在 file:// 協定下運作 -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
	</head>
	<body>
		<!-- Fixed navbar -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="<c:url value="/admin/intoh.jsp"/>">管理首頁</a></li>
						<li><a href="<c:url value="/admin/checkad.jsp"/>">審核廣告</a></li>
						<li><a href="<c:url value="/admin/AllowShop.jsp"/>">審核店鋪</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<!-- 觸發模態窗 -->
						<li><a>${admin.administratorAcc}</a></li>
						<li><a href='<c:url value="/adminlogout.action"/>'>
							<span class="glyphicon glyphicon-log-out"></span> 登出 </a>
						</li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</nav>

		<div id="jumb" class="jumbotron">
			<div class="container">
				<div class="row">
					<div class="col-md-10">
		
						<!-- 功能放這邊！！！ -->
						<div>
						<h3>登入者:${admin.administratorAcc}</h3>
						</div>
						<!-- 功能放這邊！！！ -->
		
					</div>
				</div>
				<!-- 互動視窗（Modal） -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   					<div class="modal-dialog modal-sm">
      					<div class="modal-content">
        					<div class="modal-header">
            					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">登入</h4>
							</div>
							<div class="modal-body">
								<div id="test">
							
								</div>
         					</div>
      					</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
			</div>
		</div>

		<div class="container marketing">
			<footer>
				<p class="pull-left">&copy; 2015 Taipei Breakfast. All rights reserved. &middot; </p>
				<p class="pull-left"><a href="#">隱私權政策</a> &middot; <a href="#">常見問題</a></p>
				<p class="pull-right">
				
				</p>
				
			</footer>
		</div><!-- /.container -->	

</body>
</html>
