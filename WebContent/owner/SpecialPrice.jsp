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
		<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<!-- social icon 使用 -->
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
		<!-- Custom styles -->
		<link href="../css/carousel.css" rel="stylesheet">
		<link rel="icon" href="favicon.ico">
		<link rel="stylesheet" href="../css/jquery-ui.css">
		<link rel="stylesheet" type="text/css" href="../css/DateTimePicker.css" />
		<!-- DATATABLE -->
		<link href="../css/jquery.dataTables.min.css" rel="stylesheet">
		<!-- HTML5 shim and Respond.js 讓 IE8 支援 HTML5 元素與媒體查詢 -->
		<!-- 警告：Respond.js 無法在 file:// 協定下運作 -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<style>
.seperate{margin:25px 0px}
.floatleft{float:left;margin-right:20px;}
.aligncenter{text-align:center;}
.border{border:1px solid #ddd;background:white;}
</style>
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
					<a class="navbar-brand" href="<c:url value='/index' />">
						<img alt="Brand" src="../image/proj_icon_2.png" style="max-width:100px; margin-top: -12px;" >
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
							<span class="glyphicon glyphicon-log-out"></span> 登出</a>
						</li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</nav>

		<div id="jumb" class="jumbotron">
			<div class="container">
				<div class="row">
					<div class="col-md-2">
						<div class="list-group" id="menu"></div>
							<a class="list-group-item text-center" href="#">店鋪管理</a>
							<a class="list-group-item text-center" href="<c:url value="/owner/selectmeal"/>">菜單管理</a>
							<a class="list-group-item text-center" href="<c:url value="/owner/selectad"/>">廣告申請</a>
							<a class="list-group-item text-center" href="<c:url value="/owner/SpecialPrice"/>">優惠卷管理</a>
							<a class="list-group-item text-center" href="#">查詢銷售報表</a>
							<a class="list-group-item text-center" href="#">查詢交易紀錄</a>
							<a class="list-group-item text-center" href="#">賣家基本資料</a>
					</div>
					<div class="col-md-10" style="margin-top: 20px;">
						<div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">新增優惠券</a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">查詢優惠券</a></li>
 </ul>
  <!-- Tab panes -->
	<div class="tab-content col-md-8 border">
    	<div role="tabpanel" class="tab-pane active heightclass" id="home">
    		<div>
				<h2>新增優惠券</h2>
				<div class="seperate"></div>
			</div>
			<div>
				<div>
					<div class="separate"></div>
					<label hidden="true">標題：<input type="text" /></label>
					<div class="separate"></div>
					<select id="selectShop"></select>
					<div class="seperate"></div>
				</div>
			<div id="specialPriceTime">
			<div class="floatleft"><span>開始優惠日期:</span></div>
			<div>
				<input id="startDate" class="startDate1" type="text" data-field="date" data-startend="start" data-startendelem=".endDate1" required readonly>
			</div>
	
			<div class="seperate"></div>
			<div class="floatleft"><span>結束優惠日期:</span></div>
			<div>
				<input id="endDate" class="endDate1" type="text" data-field="date" data-startend="end" data-startendelem=".startDate1" required readonly>
			</div>
			<div class="seperate"></div>
			</div>
			<div>
				<div>
					<div>
						<div class="floatleft"><span>餐點種類:</span></div>
						<select id="selectMealKind"></select>
						<div class="seperate"></div>
						<div class="floatleft"><span>餐點:</span>
						</div>
						<select id="selectMeal" class="floatleft"></select>
						<div><span>餐點原價:<span id="originprice"></span></span></div>
					</div>
				</div>
			<div>
				<div class="seperate"></div>
				<div class="floatleft"><span>優惠價格:</span></div>
				<div><input id="specailprice" type="number" min='0' required /></div>
			</div>
			<div>
				<div class="seperate"></div>
				<input id="submitbutton" type="button" value="新增"/>
				<div class="seperate"></div>
			</div>
		</div>
	</div>
		<div id="dtBox"></div>
    </div>
    	<div role="tabpanel" class="tab-pane" id="profile">
			<div>
				<div>
					<h2>優惠券內容</h2>
				</div>
				<div class="seperate"></div>
			</div>
			<div id="specialPriceTable" >
				
			</div>
		</div>
    </div>
   </div>
</div>
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

		<!-- jQuery (Bootstrap 所有外掛均需要使用) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		
		<script src="../js/jquery-ui.js"></script>
		<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
		<script src="../bootstrap/js/bootstrap.min.js"></script>
		<!-- Custom Javascript -->
		<script src="../js/holder.js"></script>
		<!-- datetimepicker -->

<script type="text/javascript" src="../js/DateTimePicker.js"></script>
<script src="../js/jquery.dataTables.min.js"></script>
<script>
	//取得店家ID
	var ownID = ${sessionScope.user.ownID};
	//給jQuery使用參數
	var mealKindID;
	var shopID;
	var d =0;
	//Tab初始化
	$('#myTab a').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		})
	
	jQuery(function($){
		//初始化DateTimePicker
		var d = new Date();
		var minDate = d.getFullYear()+"-"+(parseInt(d.getMonth())+1)+"-"+d.getDate();
		console.log(minDate)
		$("#dtBox").DateTimePicker({
			dateFormat:"yyyy-MM-dd",
			titleContentDate:"選擇後修改日期",
			buttonsToDisplay:["HeaderCloseButton", "SetButton"]
		});
		$('#startDate').attr("data-min", minDate);
		console.log(ownID);
		//第一次讀取某個賣方的所有店鋪
		$.getJSON("<c:url value='/owner/ownerSpecialPrice!queryShops?ownID="+ownID+"' />", function(data){
			console.log(data.list);
			var l = data.list;
			shopID = data.list[0].shopID;
			$.each(l, function(index, value){
				var shopName = value.shopName;
				var shopID = value.shopID;
				$('#selectShop').append('<option value="'+shopID+'">'+shopName+'</option>');
			})
			//取得"賣方店舖ID為最小"的所有餐點種類和第一項餐點
			getMealKind(shopID);
			//如果賣方選擇了店家
			$('#selectShop').bind('change', function(){
				shopID = $(this).val();
// 				alert(shopID);
				$.getJSON("<c:url value='/owner/ownerSpecialPrice!selectShopMeal?shopID="+shopID+"' />", function(data){
					console.log(document.getElementById("selectMealKind").hasChildNodes());
					getMealKind(shopID);
				});
			})
			//選擇餐點種類後，改變餐點
			$('#selectMealKind').bind('change', function(){
				mealKindID = $(this).val();
				getMeal(shopID, mealKindID);
			})
			//選擇餐點後，改變顯示價錢
			$('#selectMeal').bind('change', function(){
				var price = $(this).val();
				console.log(price);
				$('#originprice').text(price);
			})
		});
			$('#submitbutton').bind('click', function(){
				
				console.log($('#startDate').val());
				console.log($('#endDate').val());
				console.log($('#selectMeal :selected').attr("id"));
				console.log($('#specailprice').val());
				var mealID = $('#selectMeal :selected').attr("id");
				var specialPrice = $('#specailprice').val();
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				if(startDate!="" && endDate!="" && specialPrice!=""){
					$.post("<c:url value='/owner/ownerSpecialPrice!insertSpecialPrice?mealID="
							+mealID+"&specialPrice="+specialPrice+"&startDate="+startDate+
							"&endDate="+endDate+"' />", function(data){
							console.log("97")
							console.log(shopID);
							if(data == "true"){
								alert("成功");
								$('#startDate').val("");
								$('#endDate').val("");
								$('#specailprice').val("");
								getMealKind(shopID);
							}else{
								alert("失敗");
							}
						});
				}else{
					console.log($('#startDate').val());
					console.log($('#endDate').val());
					console.log($('#selectMeal :selected').attr("id"));
					console.log($('#specailprice').val());
					alert("未輸入日期或價格");
				}
				
			});
			//查詢優惠券
			$('#specialPriceTable').append("<table id='specialTable' class='table table-hover table-bordered'><thead><tr><td>優惠店家名</td><td>起始日期</td>"+
					"<td>結束日期</td><td>餐點</td><td>價格</td><td>刪除</td></tr></thead>"+
					"<tbody id='specialPriceTbody'><tbody></table>");
			//將查詢優惠結合上事件
			$('a[href*="#profile"]').bind('click', function(){
				if(document.getElementById("specialPriceTbody").hasChildNodes()){
					$('#specialPriceTbody').empty();
				}
				querySpecial();
			})
			
	})
	
	//取得特定店家、特定種類的餐點
	function getMeal(ShopID, mealKindID){
		console.log(mealKindID);
		$.getJSON("<c:url value='/owner/neworder!queryMealBean?shopID="+ShopID+"&mealKindID="+mealKindID+"' />", function(data){
				console.log("getMeal");
				console.log(data);
			if(document.getElementById("selectMeal").hasChildNodes()){
				$('#selectMeal').empty();
			}
			$.each(data, function(index, value){
				$('#selectMeal').append('<option id="'+value.mealID+'" value="'+value.price+'">'+value.mealName+'</option>');
			});
			$('#originprice').text(data[0].price);
		});
	}
	//取得特定店家的餐點及種類
	function getMealKind(ShopID){
		$.getJSON("<c:url value='/owner/neworder!queryMealKindList?shopID="+ShopID+"' />", function(data){
			console.log("getMealKind");
			console.log(data);
			if(data.length!=0){
				mealKindID = data[0].mealKindID;
			
				if(document.getElementById("selectMealKind").hasChildNodes()){
					$('#selectMealKind').empty();
					$('#selectMeal').empty();
				}
				$.each(data, function(index, value){
					$('#selectMealKind').append('<option value="'+value.mealKindID+'">'+value.mealKindName+'</option>');
				})
				//取得"賣方店舖ID"的所有餐點
				getMeal(ShopID, mealKindID);
			}else{
				if(document.getElementById("selectMealKind").hasChildNodes()){
					$('#selectMealKind').empty();
					$('#selectMeal').empty();
				}
			}
		});
	}
	
	function querySpecial(){
		$.getJSON("<c:url value='/owner/ownerSpecialPrice!querySpecialPrice?ownID="+ownID+"' />", function(data){
// 			console.log(data);
			$.each(data, function(index, value){
				//添加查詢specailPrice資料
				$('#specialPriceTbody').append("<tr><td>"+value.mealBeanInfo.shopName+"</td><td>"
						+value.startDate.substring(0, 10)+"</td><td>"+value.endDate.substring(0, 10)+"</td><td>"
						+value.mealBeanInfo.mealName+"</td><td>"+value.specialPrice+
						"</td><td><input name='delete' id='"+value.specialPriceID+"' type='button' value='刪除'/></td></tr>");
// 				console.log('but'+value.specialPriceID);
			})
			table.destroy();
			//初始化DataTable
					$('#specialTable').DataTable({"iDisplayLength": 8,
						"lengthChange": false,
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
			//刪除	
			deletebutton();
		})
	}
	
	
	function deletebutton(){
		$('input[name="delete"]').bind('click', function(){
// 			alert($(this).attr("id"));
		var specialPriceID = $(this).attr("id");
		if(confirm("是否刪除")){
		$.post("<c:url value='/owner/ownerSpecialPrice!deleteSpecialPrice?specialPriceID="+specialPriceID+"' />", function(data){
			console.log(data);
			if(data =="true"){
				alert("刪除成功");
				//刪除成功後重載資訊頁面
				$.getJSON("<c:url  value='/owner/ownerSpecialPrice!querySpecialPrice?ownID="+ownID+"' />", function(data){
// 					console.log(data);
					if(document.getElementById("specialPriceTbody").hasChildNodes()){
						$('#specialPriceTbody').empty();
					}
					$.each(data, function(index, value){
						$('#specialPriceTbody').append("<tr><td>"+value.mealBeanInfo.shopName+"</td><td>"
								+value.startDate.substring(0, 10)+"</td><td>"+value.endDate.substring(0, 10)+"</td><td>"
								+value.mealBeanInfo.mealName+"</td><td>"+value.specialPrice+
								"</td><td><input name='delete' id='"+value.specialPriceID+"' type='button' value='刪除'/></td></tr>");
					})
// 					alert("重新載入");
					deletebutton();
				})
			}else{
				alert("刪除失敗");
			}
		})
			}						
		})
	}
	
</script>
</body>
</html>
