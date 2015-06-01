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
<link rel="icon" href="../favicon.ico">
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/css/table.css'/>" /> --%>
<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../js/holder.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!-- <script src="http://malsup.github.com/jquery.form.js"></script> -->
<!-- <script src="../js/jquery.min.js"></script> -->
<!-- <script src="../js/jquery.form.js"></script> -->

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
		
	<script>
		var shopID;
		var Area;
		function createarea(){
			Area=$('#sel1').val();
    		console.log(Area);
			while(document.getElementById("sel2").hasChildNodes()){
				document.getElementById("sel2").removeChild(document.getElementById("sel2").childNodes[0]);
			}
			$('#sel2').append("<option value='0' >店家</option>");
	    	$.get("<c:url value='/pe/duAction!selectShopArea.action'/>?sf.shopArea="+Area+"",
	        	function(data){
					var list=JSON.parse(data.redata);
	         		console.log(list);
					$.each(list,function(i,v){
						$('#sel2').append("<option value='" + v.shopID + "'>" + v.shopName + "</option>");	
					})
	        	});
		}
	</script>
		
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
				<a class="navbar-brand" href="../pages/turn.jsp"> <img alt="Brand"
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
				<form class="navbar-form navbar-left" role="search" 
 					action="<c:url value='/pe/duAction!selectShop.action'/>" 
					method="post">
				<div class="form-group">
					<select class="form-control" id="sel1" name="sf.shopArea" onchange="createarea()" >
						<option value="">地區</option>
						<option value="中正">中正區</option>
						<option value="大同">大同區</option>
						<option value="中山">中山區</option>
						<option value="松山">松山區</option>
						<option value="大安">大安區</option>
						<option value="萬華">萬華區</option>
						<option value="信義">信義區</option>
						<option value="士林">士林區</option>
						<option value="北投">北投區</option>
						<option value="內湖">內湖區</option>
						<option value="南港">南港區</option>
						<option value="文山">文山區</option>
					</select> 
					<select class="form-control" id="sel2" name="sf.shopID">
						<option value='0' >店家</option>
						<option value='0' >早餐吃到飽</option>
						<option value='0' >早餐吃不飽</option>
						<option value='0' >早餐吃很少</option>
					</select> 
					<input type="text" class="form-control" placeholder="關鍵字" name="keyword">
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
					<a class="list-group-item text-center" href="#">會員功能</a> 
					<a class="list-group-item text-center" href="#">會員功能</a> 
					<a class="list-group-item text-center" href="#">會員功能</a>
					<a class="list-group-item text-center" href="<c:url value='../member/MemberUpdate.jsp' />">更新資料</a> 
					<a class="list-group-item text-center" href="<c:url value='../member/MemberChangePass.jsp' />">更改密碼</a> 
					<a class="list-group-item text-center" href="<c:url value='../member/MemberDelete.jsp' />">刪除帳號</a> 
					<a class="list-group-item text-center" href="#">會員功能</a> 
				</div>
				<div class="col-md-10" style="text-align: left; top: 30px;">

						<table>
							<tr>
								<th>						
									<c:if test="${not empty user.memberFirstName }">
										<h3>Welecome ${user.memberFirstName}</h3>
									</c:if>
										<h3>Welecome </h3>
								</th>
							</tr>
						</table>

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
			<p class="pull-left">&copy; 2015 Taipei Breakfast. All rights reserved. &middot;</p>
			<p class="pull-left">
				<a href="#">隱私權政策</a> &middot; 
				<a href="#">常見問題</a> &middot; 
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#myModalforContact">聯絡我們</button>

			<!-- Modal -->
		<div class="modal fade" id="myModalforContact" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
<%-- 						<form action="${pageContext.request.contextPath}/myNamespace/myAction.controller" method="post" > --%>
<!-- 							<h4 class="modal-title">信箱</h4> -->
							<input type="text" placeholder="請填入你的信箱" name="SendGmailForm.gmail" required="required">
							<h4 class="modal-title">主旨</h4>
							<input type="text" placeholder="請填入主旨" name="SendGmailForm.subject" required="required">
							<h4 class="modal-title">內容</h4>
							<input type="text" placeholder="請填入內容" name="SendGmailForm.text" required="required">
							<div class="modal-footer">
							<button type="submit">送出</button>
							</div>
<!-- 						</form> -->
					</div>

				</div>
			</div>
		</div>

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
