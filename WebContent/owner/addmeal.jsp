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
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
		<!-- social icon 使用 -->
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
		<!-- Custom styles -->
		<link href="<c:url value='/css/carousel.css'/>" rel="stylesheet">
		<link rel="icon" href="favicon.ico">
		
		<!-- jQuery (Bootstrap 所有外掛均需要使用) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
		<script src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
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
		<script src="<c:url value='/js/jquery.min.js'/>"></script>
		<script src="<c:url value='/js/jquery.form.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script>
		<link href="<c:url value='/bootstrap/css/bootstrap.css'/>" rel="stylesheet">
			<style>
			#image1 {
				width: 50px;
				height: 50px;
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
							<a class="list-group-item text-center" href="<c:url value="/owner/selectmeal.jsp"/>">修改菜單</a>
							<a class="list-group-item text-center" href="<c:url value="/owner/addmeal.jsp"/>">新增菜單</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
							<a class="list-group-item text-center" href="#">賣方功能</a>
					</div>
					<div class="col-md-10">
		
						<!-- 功能放這邊！！！ -->
						<h2>新增菜單</h2>
						<hr>
	<div id="dv01">
		<form id="form1" action="<c:url value='/owner/mealaction!mealadd'/>"
			method="POST" enctype=multipart/form-data>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">店家:</div>
				<div class="col-sm-5">
					<select id="select1" name="bean.shopID"></select>
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">餐點名稱:</div>
				<div class="col-sm-5">
					<input type="text" id="mealname" name="bean.mealName" value="" />
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">餐點種類:</div>
				<div class="col-sm-5">
					<select id="select2" name="bean.mealKindID"></select>
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">價格:</div>
				<div class="col-sm-5">
					<input type="number" min="0" id="price" value="60" name="bean.price" />
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">圖片:</div>
				<div class="col-sm-5">
					<div id="result" ></div>
					<input type="file" accept="image/*" id="file"
					name="bean.mealImage" />
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2" style="margin: 5px">
					<input type="submit" class='btn btn-default' value="新增">
				</div>
			</div>
		</form>
	</div>

	<script>
	var jsondata = {owerID:1};
	$.get("/TaipeiBreakFast/owner/mealaction!shoplist", jsondata, 
		function (data) {
			var list=JSON.parse(data.redata);

    		$.each(list,function(u,i){
				$('#select1').append("<option value='" + i.shopID + "'>" + i.shopName + "</option>");
        	})
		});
       
        var kindlist;
        $.ajax({
            url: "/TaipeiBreakFast/owner/mealaction!mealKind",
            data: "",
            type:"POST",
            dataType:'text',

            success: function(msg){
                kindlist=JSON.parse(msg);
                    var ss=JSON.parse(kindlist.redata);
                    console.log(ss);
                    $.each(ss,function(a,b){
                    	$('#select2').append("<option value='" + b.mealKindID + "'>" + b.mealKindName + "</option>");
                    });
            },

             error:function(xhr, ajaxOptions, thrownError){ 
                alert(xhr.status); 
                alert(thrownError); 
             }
        });
        
    </script>
	<script>

    $('#form1').ajaxForm({
        beforeSend:function(){
        	while (document.getElementById("dv01").hasChildNodes()) {
                document.getElementById("dv01").removeChild(document.getElementById("dv01").childNodes[0]);
            }
        },
		complete:function(msg){               
                var txt = JSON.parse(msg.responseJSON.redata);
                var message=txt.Message;
                $('#dv01').append("<h2>"+message+"</h2>");
                if(txt.image!=null){
                	var gg=JSON.parse(txt.image);
                	var data = encode(new Uint8Array(gg));;
                    var img = document.createElement("img");
                    img.id="image2";
                    img.src = 'data:image/jpg;base64,'+data;
                    document.getElementById("dv01").appendChild( img );
                    } 
			}
        })
	function encode (input) {
    var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var output = "";
    var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
    var i = 0;

    while (i < input.length) {
        chr1 = input[i++];
        chr2 = i < input.length ? input[i++] : Number.NaN; // Not sure if the index 
        chr3 = i < input.length ? input[i++] : Number.NaN; // checks are needed here

        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;

        if (isNaN(chr2)) {
            enc3 = enc4 = 64;
        } else if (isNaN(chr3)) {
            enc4 = 64;
        }
        output += keyStr.charAt(enc1) + keyStr.charAt(enc2) +
                  keyStr.charAt(enc3) + keyStr.charAt(enc4);
    }
    return output;
}
    </script>
    <script>
    function ProcessFile( e ) {   
        var file = document.getElementById('file').files[0];
        console.log(file);
        if (file) {
            if (document.getElementById("result").hasChildNodes()) {
                document.getElementById("result").removeChild(document.getElementById("result").childNodes[0]);
            }
            var reader = new FileReader();
            reader.onload = function ( event ) {                 
                var txt = event.target.result;
                var img = document.createElement("img");
                img.id="image1";
                img.src = txt;
                document.getElementById("result").appendChild( img );
            };
        }else{
        	if (document.getElementById("result").hasChildNodes()) {
                document.getElementById("result").removeChild(document.getElementById("result").childNodes[0]);
            }
            }
        reader.readAsDataURL( file );
    }
    function contentLoaded() {
        document.getElementById('file').addEventListener( 'change' ,ProcessFile , false );
    }
    window.addEventListener( "DOMContentLoaded" , contentLoaded , false );
    </script>
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
				<a href="https://www.facebook.com/taipeibreakfast"><i id="social" class="fa fa-facebook-square fa-3x social-fb"></i></a>
	            <a href="https://twitter.com/taipeibreakfast"><i id="social" class="fa fa-twitter-square fa-3x social-tw"></i></a>
	            <a href="https://plus.google.com/+TaipeiBreakfast-page"><i id="social" class="fa fa-google-plus-square fa-3x social-gp"></i></a>
	            <a href="mailto:taipeibreakfast@gmail.com"><i id="social" class="fa fa-envelope-square fa-3x social-em"></i></a>
				</p>
				
			</footer>
		</div><!-- /.container -->	

</body>
</html>
