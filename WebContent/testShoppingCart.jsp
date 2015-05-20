<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>jQuery UI Tabs - Vertical Tabs functionality</title>
<!--   jQuery & jQueryUI -->
<script src="js/jquery-1.11.2.js"></script>
<script src="js/jquery-ui.js"></script>
	<!-- 網路資源 -->
<!-- <script src="//code.jquery.com/jquery-1.10.2.js"></script> -->
<!-- <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>
	$(function() {
		$("#tabs").tabs().addClass("ui-tabs-vertical ui-helper-clearfix");
		$("#tabs li").removeClass("ui-corner-top").addClass("ui-corner-left");
	});
</script>
<style>
/*    .ui-tabs-vertical { width: 55em; }  */
/* 	.ui-tabs-vertical .ui-tabs-nav {padding: .2em .1em .2em .2em; float: left;width: 12em;} */
/*  	.ui-tabs-vertical .ui-tabs-nav li {clear: left; width: 100%; border-bottom-width: 1px !important; border-right-width: 0 !important; margin: 0 -1px .2em 0;}  */
/* 	.ui-tabs-vertical .ui-tabs-nav li a {display: block;} */
     .ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active { padding-bottom: 0; padding-right: .1em; border-right-width: 1px; }    
/*       .ui-tabs-vertical .ui-tabs-panel { padding: 1em; float: right; width: 40em;}     */
	.hidediv {display:none;}
	.showdiv {display:inline;}
	.rowdiv {width:1000px;}
	.floatdiv {float:left;}
</style>
</head>
<body>
	<div id="tabs">
		<div style="float: left;padding-right:10px;">
			<ul id="mealKindList" >
			</ul>
		</div>
		<div class="floatdiv">
			<div id="mealdiv" class="rowdiv"></div>
			<input type="button" value="上一頁">
			<input type="button" value="下一頁">
		</div>
		<div id="shoppingcart" class="floatdiv">
		<table>
			<thead>
				<tr>
					<td colspan="3">訂單列表</td>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		</div>
	</div>
</body>
<script>
//載入mealKindList
jQuery(function($) {
	loadMeal();
})
	//取得某家店餐點種類印在最左側
	function loadMeal(){
		$.getJSON("owner/neworder!queryMealKindList",{"shopID" : 3},function(data) {
			console.log(data);
			// 		var jobject = JSON.parse(data.jSONString);
				$.each(data,function(index, value) {
					// 			console.log(index+":"+value);
					//產生餐點表單
					$('#mealKindList').append("<li><a href=#tabs" + index +" value=\""
							+value.mealKindID+"\">" + value.mealKindName	+ "</a></li>");
					//建立好餐點區塊
					
					$('#mealdiv').append("<div id=\"tabs"+index+"\"><div id=\"firstrow"
							+index+"\" class=\"row\"></div><div id=\"secondrow"
							+index+"\" class=\"row\"></div></div>");
					if(index!=0){
					$("#tabs"+index+"").addClass("hidediv");
					}
				});
				
				loadmeals();
				bindshowdiv();
		})
	}
	//取得某間店的某種餐點種類的餐點
	function loadmeals() {
		$.getJSON("owner/neworder!queryMealBean", {
			"shopID" : 3,
			"mealKindID" : 1,
			"page" : 0
		}, function(data) {
			console.log(data);
			var i = 1;
			$.each(data, function(index, value) {
				// 			console.log(value);
// 				var encode64 = encode(new Uint8Array(value.mealImage));
				;
// 				var src = "data:image/jpeg;base64," + encode64;
				// 			console.log(src);
				if (i < 4) {
					$('#firstrow0').append(
							"<div class=\"col-md-4 drag\" draggable=\"true\"><img width=\"200px\" src=\"owner/neworder!queryMealImage?mealID="+value.mealID+"\" ><p>"
									+ value.mealName + "</p><p>" + value.price
									+ "</p><input type=\"number\" min=\"0\" /></div>");
					i++;
				} else {
					$('#secondrow0').append(
							"<div class=\"col-md-4 drag\" draggable=\"true\"><img width=\"200px\" src=\"owner/neworder!queryMealImage?mealID="+value.mealID+"\" ><p>"
									+ value.mealName + "</p><p>" + value.price
									+ "</p><input type=\"number\" min=\"0\" /></div>");
				}
			})
		})
	}
	//將連結綁上點擊事件觸發切換展現div區塊
	function bindshowdiv(){
		$('a').bind('click', function(){
			var h =$(this).attr("href").substring(1, 6);
			//切換顯示div區塊
			$('#mealdiv>div[id!='+h+']').removeClass().addClass("hidediv");
			$('#'+h+'').addClass("showdiv");
			var rowi =$(this).attr("href").substring(5, 6);
			var mealKindID = parseInt($(this).attr("href").substring(5, 6))+1;
// 			console.log(rowi);
// 			console.log(mealKindID);
			//開始時間
			var date = new Date();
// 			console.log(date.getTime());
			//產生該種類餐點的頁面
			loadmeals();
			$.getJSON("owner/neworder!queryMealBean", {
				"shopID" : 3,
				"mealKindID" : mealKindID,
				"page" : 0
			}, function(data) {
				//接受到資料的時間
				var d = new Date();
				console.log(d.getTime()-date.getTime());
				//先清空firstrow在增加
				$('#firstrow'+rowi+'').empty();
				//清空secondrow在增加
				$('#secondrow'+rowi+'').empty();
				console.log(data);
				var i = 1;
				$.each(data, function(index, value) {
					// 			console.log(value);
// 					var encode64 = encode(new Uint8Array(value.mealImage));
					;
// 					var src = "data:image/jpeg;base64," + encode64;
					// 			console.log(src);
					if (i < 4) {
						$('#firstrow'+rowi+'').append(
								"<div id=\"drag"+index+"\" class=\"col-md-4\" draggable=\"true\"><img width=\"200px\" src=\"owner/neworder!queryMealImage?mealID="+value.mealID+"\" ><p>"
										+ value.mealName + "</p><p>" + value.price
										+ "</p><input type=\"number\" min=\"0\" /></div>");
						i++;
					} else {
						$('#secondrow'+rowi+'').append(
								"<div id=\"drag"+index+"\" class=\"col-md-4\" draggable=\"true\"><img width=\"200px\" src=\"owner/neworder!queryMealImage?mealID="+value.mealID+"\" ><p>"
										+ value.mealName + "</p><p>" + value.price
										+ "</p><input type=\"number\" min=\"0\" /></div>");
					}
				})
			})
			})	
	}
	$('div[id="drag"]').bind(dragstart,dragmeal);
	//拖曳餐點的功能
	function dragmeal(e){
		alert(e);
	}

</script>
</html>