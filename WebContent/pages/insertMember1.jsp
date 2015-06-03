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
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/css/table.css'/>" /> --%>
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
 height:30px;
} 
</style>

<style type="text/css">
div img {
max-width:100px;
width:100px;
width:expression(document.body.clientWidth>100?"100px":"auto");
overflow:hidden;
}
</style>

<script>
    function ProcessFile(e){   
        var file = document.getElementById('imgin').files[0];
        console.log(file);
        if (file) {
            if (document.getElementById("imgout").hasChildNodes()) {
                document.getElementById("imgout").removeChild(document.getElementById("imgout").childNodes[0]);
            }
            var reader = new FileReader();
            reader.onload = function (event) {
                var txt = event.target.result;
                var img = document.createElement("img");
                img.id="image1";
                img.src = txt;
                document.getElementById("imgout").appendChild(img);
            };
        }else{
        	if (document.getElementById("imgout").hasChildNodes()) {
                document.getElementById("imgout").removeChild(document.getElementById("imgout").childNodes[0]);
            }
        }
        reader.readAsDataURL(file);
    }
    function contentLoaded(){
        document.getElementById('imgin').addEventListener('change',ProcessFile,false);
    }
    window.addEventListener("DOMContentLoaded",contentLoaded,false);
    
    function clearimg(){
        if (document.getElementById("imgout").hasChildNodes()) {
            document.getElementById("imgout").removeChild(document.getElementById("imgout").childNodes[0]);
        }
    }
</script>
    
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
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
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
<!-- 					登入後由會員中心的li取代登入的li -->
<!-- 				<li><a data-toggle="tab" href="member/MemberCenter">  -->
<!-- 					<span class="glyphicon glyphicon-user"></span> 會員中心 </a> -->
<!-- 				</li>  -->
					<!-- 觸發模態窗 -->
				<li><a data-toggle="modal" data-target="#myModal" href="#">
					<span class="glyphicon glyphicon-log-in"></span> 登入</a>
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
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div id="jumb" class="jumbotron">
		<div class="container">
	<div style="text-align: left; margin-left: 30%; padding: 8px">
	
<!-- 	<form action="MemberInsertServlet.controller" method="post"> -->
	<form action="<c:url value='/pe/duAction!memberInsert.action'/>" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
		<table>
			<tr><th><h3>加入會員</h3></th></tr>
			<tr>
				<td>Email :</td>
<!-- 				<td><input type="text" name="email" id="idEmail" onchange="checkmail()"></td> -->
				<td><input type="text" name="mf.memberEmail" id="idEmail" onchange="checkmail()" class="form-control" ></td>
				<td>${errors.acc}</td>
			</tr>
			<tr>
				<td>密碼 :</td>
<!-- 				<td><input type="password" name="pass"></td> -->
				<td><input type="password" name="mf.memberPwd" class="form-control" ></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td> </td> -->
<!-- 			</tr> -->
			<tr>
				<td>姓氏 :</td>
<!-- 				<td><input type="text" name="first"></td> -->
				<td><input type="text" name="mf.memberFirstName" class="form-control" ></td>
			</tr>
			<tr>
				<td>名字 :</td>
<!-- 				<td><input type="text" name="last"></td> -->
				<td><input type="text" name="mf.memberLastName" class="form-control" ></td>
			</tr>
			
<!-- 			<tr> -->
<!-- 				<td> </td> -->
<!-- 			</tr> -->
			<!-- 只須手機即可 修改by Steven  
			<tr>
				<td>聯絡電話 :</td>
				<td><input type="text" name="tel"></td>
				<td><input type="text" name="mf.memberTel" class="form-control" ></td>
				<td>${erro.tel}</td>
			</tr>
			<tr>
				<td></td>
				<td>例:(0212345678)</td>
			</tr>
			-->
			<tr>
				<td>手機 :</td>
<!-- 				<td><input type="text" name="pho"></td> -->
				<td><input type="text" name="mf.memberPhone" class="form-control" ></td>
				<td><c:if test="${empty errors.pho }">例:(0999123456)</c:if>${errors.pho}</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td></td> -->
<!-- 				<td>例:(0999123456)</td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td> </td> -->
<!-- 			</tr> -->
			
			<tr>
				<td>地址 :</td>
<!-- 				<td><input type="text" name="addr"></td> -->
				<td><input type="text" name="mf.memberAddr" class="form-control" ></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td> </td> -->
<!-- 			</tr> -->
			<tr>
				<td>圖片 :</td>
<!-- 				<td><input type="file" name="img" accept="image/*"></td> -->
				<td><input type="file" id="imgin" name="mf.memberImage" accept="image/*"></td>
			</tr>
			<tr>
				<td></td>
				<td id="imgout" style="width: 20px; height: 20px;"></td>
			</tr>

			<tr>
				<td></td>
				<td>${errors.action}</td>
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
		<span style="position: relative; left:-100px;">
			<input type="submit" value="註冊" class="btn btn-info btn-sm">
		</span>
		<span style="position: relative; left:-50px;">
			<input type="reset" value="清空重填" class="btn btn-danger btn-sm" onclick="clearimg()" >
		</span>
	</div>
		
		</form>
	</div>
		</div>
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
			<p class="pull-left">&copy; 2015 Taipei Breakfast. All rights reserved. &middot;</p>
			<p class="pull-left">
				<a href="#">隱私權政策</a> &middot; 
				<a href="#">常見問題</a> &middot; 
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#myModalforContact">聯絡我們</button>
			</p>
			
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
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">歡迎光臨</h4>
				</div>
				<div class="modal-body">
					<!-- 登入頁籤 -->
					<div role="tabpanel">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#membersign"
								aria-controls="membersign" role="tab" data-toggle="tab">會員</a></li>
							<li role="presentation"><a href="#ownersign"
								aria-controls="ownersign" role="tab" data-toggle="tab">賣方</a></li>
						</ul>
						<!-- Tab panes -->
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="membersign">
								<form class="form-signin">
									<h2 class="form-signin-heading">會員登入</h2>
									<label for="inputEmail1" class="sr-only">Email 地址</label> 
									<input type="email" id="inputEmail1" class="form-control"
										placeholder="Email 地址" required autofocus> 
									<label for="inputPassword1" class="sr-only">密碼</label> 
									<input type="password" id="inputPassword1" class="form-control"
										placeholder="密碼" required>
									<div class="checkbox">
										<label> <input type="checkbox" value="remember-me">記住密碼</label>
									</div>
									<button class="btn btn-lg btn-info btn-block" type="submit">登入</button>
<%-- <a href="${pageContext.request.contextPath}/pages/insertMember1.jsp" type="button" class="btn btn-lg btn-primary btn-block">新會員註冊</a> --%>
							<button class="btn btn-lg btn-primary btn-block" type="button">新會員註冊</button>
									<br>
									<div class="text-right">
										<h5>
											<a href="http://localhost:8080/TB/pages/losePassMember.jsp">忘記密碼?</a>
										</h5>
									</div>
								</form>
							</div>
							<div role="tabpanel" class="tab-pane" id="ownersign">
								<form class="form-signin" action='<c:url value="/login"/>' method="post">
									<h2 class="form-signin-heading">賣方登入</h2>
									<label for="inputEmail2" class="sr-only">Email 地址</label> 
									<input type="email" id="inputEmail2" name="user" class="form-control"
										placeholder="Email 地址" required autofocus> 
									<label for="inputPassword2" class="sr-only">密碼</label> 
									<input type="password" name="password" id="inputPassword2" class="form-control"
										placeholder="密碼" required>
									<div class="checkbox">
										<label> <input type="checkbox" value="remember-me">記住密碼</label>
									</div>
									<button class="btn btn-lg btn-info btn-block" type="submit">登入</button>
									<button class="btn btn-lg btn-primary btn-block" type="button">合作聯繫</button>
									<br>
									<div class="text-right">
										<h5>
											<a href="">忘記密碼?</a>
										</h5>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

<script src="../bootstrap/js/bootstrap.min.js"></script>
<script src="../js/holder.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

</body>
</html>