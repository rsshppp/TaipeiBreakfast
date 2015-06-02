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
		
		<!-- social icon 使用 -->
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
		<!-- Custom styles -->
		<link href="<c:url value='/css/carousel.css'/>" rel="stylesheet">
		<link rel="icon" href="favicon.ico">
		
		<!-- jQuery (Bootstrap 所有外掛均需要使用) -->

		<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->

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
		<link href="<c:url value='/bootstrap/css/bootstrap.min.css'/>"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'/>"></script> 
		<script src="<c:url value='/js/lightbox.min.js'/>"></script> 
		<link href="<c:url value='/css/lightbox.css'/>" rel="stylesheet"
			type="text/css" />
		<link href="https://cdn.datatables.net/1.10.7/css/jquery.dataTables.min.css"
			rel="stylesheet" type="text/css" />
		<%-- <script src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script> --%>
		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.min.js'/>">
		</script>
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
						<div style="margin-top: 20px;">
							<div id="adtable"></div>
						</div>
						<script>
							createtable();
							
							function createtable() {
								while (document.getElementById("adtable").hasChildNodes()) {
									document.getElementById("adtable").removeChild(
											document.getElementById("adtable").childNodes[0]);
								}
								$('#adtable')
										.append(
												'<table id="admenu" class="display"><thead><tr><th>廣告編號</th><th>會員ID</th><th>店家</th><th>title</th><th>內文</th><th>天數</th><th>審核狀態</th></tr></thead><tbody id="tby"></tbody></table>');
								$.get("<c:url value='/admin/adAction!checkAD'/>", "",
										function(data) {
											var list = JSON.parse(data.redata);
													$.get("<c:url value='/admin/adAction!allStatus'/>",
													"", function(data) {
														$.each(list, function(r, v) {
															$('#tby').append(
																	"<tr><td>" + v.adID + "</td><td>" + v.owerID + "</td><td>" + v.shopName + "</td><td>"
																			+ v.title + "</td><td>" + v.context
																			+ "</td><td>" + v.days
																			+ "</td><td><select id='sel"+v.adID+"' name='selec' onchange='selchange(event)'></select></td></tr>");
															var list2 = JSON.parse(data.redata);
															$.each(list2, function(u, i) {
																$('#sel'+v.adID).append(
																		"<option value='" + i.advertisementStatusID + "'>"
																				+ i.advertisementStatus
																				+ "</option>");
															})
															$("#sel"+v.adID+" option[value='"+ v.adStatusID
																				+ "']").prop('selected', true);
															console.log(v.adStatusID);
														});
														$('#admenu').DataTable({
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
													});
											
											
										})
							}
							function selchange(event){
									var btid = event.target.id;
									var atatus=$('#'+btid).val();
									var adid = {
										adID : btid.split("sel")[1],
										atatusID : atatus
									};
									console.log(adid);
									$.get('<c:url value="/admin/adAction!changeStatus"/>',adid,function(data){
											
										})
								
								}
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
				
				</p>
				
			</footer>
		</div><!-- /.container -->	

</body>
</html>
