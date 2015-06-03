<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>審核店鋪</title>
		
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
		<link href="<c:url value='/css/carousel.css'/>" rel="stylesheet">
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
		<link href="https://cdn.datatables.net/1.10.7/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
		<link rel="icon" href="favicon.ico">
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/table.css'/>" />
		<link href="<c:url value='/css/lightbox.css'/>" rel="stylesheet" type="text/css" />
		<script src="<c:url value='/js/holder.js'/>"></script>
		<script src="<c:url value='/js/jquery.min.js'/>"></script>
		<script src="<c:url value='/js/jquery.form.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script> 
		<script src="<c:url value='/js/lightbox.min.js'/>"></script> 
<!-- 		<script src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script> -->
<%-- 		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.min.js'/>"></script> --%>

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
					<div class="col-md-12">
		
						<!-- 功能放這邊！！！ -->
						<div style="margin-top: 20px;">
							<div>
	<h3>審核店鋪</h3>
	<form method="post">
		<table id="shmenu" style="width: 100%;">
		
	<thead>
	<tr>
		<th><span>店鋪編號</span></th>
		<th>所有者ID</th>
		<th>logo</th>
		<th>店鋪名稱</th>
		<th>連絡電話</th>
		<th>所在城市</th>
		<th>所在區域</th>
		<th>店鋪地址</th>
		<th>開店時間</th>
		<th>備註</th>
	</tr>
	</thead>
	<tbody id="t1">
		
	</tbody>
		</table>
		${errors.action}
		</form>
	</div>
	
	<script>
	var sid;
	
    function change(sid){
    	console.log(1);
    	$.get("<c:url value='/pe/duAction!allowShop.action'/>?sf.shopID="+sid+"")
    	location.reload();
    }
    function nota(sid){
    	console.log(2);
    	$.get("<c:url value='/pe/duAction!notallowShop.action'/>?sf.shopID="+sid+"")
    	location.reload();
    }
    
    $.get("<c:url value='/pe/duAction!shoplist.action'/>",
		function (data) {
			var list=JSON.parse(data.redata);
    		console.log(list);
    		$.each(list,function(u,i){
				$('#t1').append("<tr>"+
					"<td>"+i.shopID+"</td>"+
					"<td>"+i.ownID+"</td>"+
					"<td style='width: 20px; height: 20px;'>"+i.logoImage+"</td>"+
					"<td>"+i.shopName+"</td>"+
					"<td>"+i.shopPhone+"</td>"+
					"<td>"+i.shopCity+"</td>"+
					"<td>"+i.shopArea+"</td>"+
					"<td>"+i.shopAddr+"</td>"+
					"<td>"+i.beginBusinessTime+"</td>"+
					"<td>"+i.businessTimeNote+"</td>"+
					"<td><input type='button' value='允許' onclick='change("+i.shopID+")' /></td>"+
					"<td><input type='button' value='不准' onclick='nota("+i.shopID+")' /></td>"+
					"</tr>");
        	});
//     		$('#shmenu').DataTable({
// 			    "iDisplayLength": 7,
// 			    "aLengthMenu": [[7, 10, 20, -1], [7, 10, 20, "All"]],
// 			    "oLanguage":{
// 			    "sProcessing": "處理中...",
// 			      "sLengthMenu": "一頁顯示 _MENU_ 筆記錄",
// 			      "sZeroRecords": "無符合資料",
// 			      "sInfo": "目前顯示：_START_ 至 _END_, 總筆數：_TOTAL_",
// 			      "sInfoEmpty": "無任何資料",
// 			      "sInfoFiltered": "(關鍵字總筆數 _MAX_)",
// 			      "sInfoPostFix": "",
// 			      "sSearch": "關鍵字",
// 			      "sUrl": "",
// 			      "oPaginate": {
// 			          "sFirst":    "首頁",
// 			          "sPrevious": "上頁",
// 			          "sNext":     "下頁",
// 			          "sLast":     "末頁"
// 			      }
// 			    }
// 			});
		}
	);
    
    </script>
	
<!-- <br><br> -->
<%-- <a href="<c:url value=''/>" >後台首頁</a> --%>
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
