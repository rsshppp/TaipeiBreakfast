<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Tabs - Vertical Tabs functionality</title>
  <link rel="stylesheet" href="../css/jquery-ui.css">
  <script src="../js/jquery-1.11.2.js"></script>
  <script src="../js/jquery-ui.js"></script>
  <!-- Bootstrap -->
  <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--   分頁 -->
  <script src="../js/jPages.js"></script>
  <link rel="stylesheet" href="../css/jPages.css">
<!--   購物車 -->
  <link rel="stylesheet" href="../css/main.css">
  <style>
  	ul#itemContainer li { display: inline-block;}
  	li p {text-align:center;}
  </style>
  <script>
  //取得memberID與shopID
  var memberID = 2;
  var shopID = ${param.shopID};
  //將切換餐點種類的數字設定在第一頁
  var kinddiv = 1;
  //購物車相關參數
  var shoppingcartObject;
  var shoppingcartJSONString;
  var shoppingcartJSON;
  //測試購物車用的sessionStorage
//   shoppingcartObject = {}
//   shoppingcartObject["memberID"] = memberID;
//   shoppingcartObject["shopID"] = shopID;
//   shoppingcartObject["items"] = {"2": "鮪魚漢堡, 40, 1", "3":"鮪魚蛋餅, 35, 2"};
//   shoppingcartJSONString = JSON.stringify(shoppingcartObject); 
//   sessionStorage.setItem("shoppingcart", shoppingcartJSONString);
//   console.log(sessionStorage.shoppingcart);
  
  //判斷購物車是否存在
  if( (shoppingcartJSONString = sessionStorage.getItem("shoppingcart")) !=null ){
	shoppingcartJSON = JSON.parse(shoppingcartJSONString);	
	//檢查購物車裡的會員ID與店家ID是否與現在的一致
	if(shoppingcartJSON.memberID == memberID && shoppingcartJSON.shopID ==shopID){
	//檢查一致開始讀入上次購物車內容
	  $(function(){
			var items = shoppingcartJSON.items;
			for(var i in items ){
// 				console.log(i);
// 				console.log(items[i].split(",")[0]);
// 				console.log(items[i].split(",")[1]);
				var mealinfo = items[i].split(",");
				$('#items').append('<li data-id="' + i + '">'
		        + '<span id='+mealinfo[1]+' class="name">' + mealinfo[0] + '</span>'
		        + '<input class="count" value="'+ mealinfo[2] +'" type="text">'
		        + '<button class="delete">✕</button>')
		            
			}
		        $("#items li button.delete").on("click", function () {
				    $(this).closest("li").remove();
				    deleteItem($(this).parent("li").attr("data-id"));
				});
				//當數量變更時，同時修改sessionStorage的物品數量
				$('#items li input.count').bind('change', function(){
					var templi = $(this).parent();
				})
	  })
	}else{
		//如果不一致，產生新購物車
		sessionStorage.removeItem("shoppingcart");
		newShoppingCart();
// 		console.log(sessionStorage.shoppingcart);
	}
  }else{
	//如果sessionStorage沒有購物車，則建立購物車
	  newShoppingCart();
// 	  console.log(sessionStorage.shoppingcart);
  	}
  //建立購物車方法
	function newShoppingCart(){
	  shoppingcartObject = {}
	  shoppingcartObject["memberID"] = memberID;
	  shoppingcartObject["shopID"] = shopID;
	  shoppingcartObject["items"] = {};
	  shoppingcartJSONString = JSON.stringify(shoppingcartObject); 
	  sessionStorage.setItem("shoppingcart", shoppingcartJSONString);
  }
  
  //幫購物車加入購物品項
  function addItemtoJSON(mealID, mealName, price, count){
	  console.log(mealID+":"+mealName+":"+price+":"+count);
	  value = mealName+","+price+","+count;
	  if(shoppingcartObject == undefined){
		  shoppingcartObject = JSON.parse(sessionStorage.getItem("shoppingcart"));
		  console.log(sessionStorage.getItem("shoppingcart"));
	  }
	  shoppingcartObject.items[mealID] = value;
	  shoppingcartJSONString = JSON.stringify(shoppingcartObject);
	  sessionStorage.setItem("shoppingcart", shoppingcartJSONString);
// 	  console.log(sessionStorage.shoppingcart);
  }
  //購物車修改產品數量時，也修改SessionStorage內的產品數量
  function updateItemCount(mealID, count){
// 	  console.log(mealID+":"+count);
	  shoppingcartJSONString = sessionStorage.getItem("shoppingcart");
	  shoppingcartJSON = JSON.parse(shoppingcartJSONString);
	  var tempString = shoppingcartJSON.items[mealID];
	  var lastindex = (tempString.lastIndexOf(",")+1);
	  tempString = tempString.substring(0, lastindex);
// 	  console.log(tempString);
	  //刪除原本的key value
	  shoppingcartJSON.items[mealID] = tempString+count;
	  shoppingcartJSONString = JSON.stringify(shoppingcartJSON);
	  sessionStorage.setItem("shoppingcart", shoppingcartJSONString);
// 	  console.log(shoppingcartJSON.items);
  }
  //清空購物車時，也清空sessionStorage的購物品項
  function clearItems(){
	  shoppingcartJSONString = sessionStorage.getItem("shoppingcart");
	  shoppingcartJSON = JSON.parse(shoppingcartJSONString);
// 	  console.log(shoppingcartJSON.items);
	  shoppingcartJSON.items ={};
	  shoppingcartJSONString = JSON.stringify(shoppingcartJSON);
// 	  console.log(shoppingcartJSONString);
	  sessionStorage.setItem("shoppingcart", shoppingcartJSONString);
// 	  console.log(sessionStorage.getItem("shoppingcart"));
  }
  function deleteItem(mealID){
	  shoppingcartJSONString = sessionStorage.getItem("shoppingcart");
	  shoppingcartJSON = JSON.parse(shoppingcartJSONString);
	  delete shoppingcartJSON.items[mealID];
	  shoppingcartJSONString = JSON.stringify(shoppingcartJSON);
	  sessionStorage.setItem("shoppingcart", shoppingcartJSONString);
	  console.log(shoppingcartJSON.items)
  }
//   addItemtoJSON(5, "World", 40, 6);
//   console.log(sessionStorage.shoppingcart);
  
  $(function() {
	//取得某家店餐點種類印在最左側 
	  $.getJSON("<s:url namespace='member' action='neworder!queryMealKindList?shopID="+shopID+"'/>" ,function(data) {
		$('#tabs').append('<ul></ul>');
		var firstMealKindID = data[0].mealKindID;
		
		//產生餐點種類表單(左側)
		$.each(data,function(index, value) {
			var tabsi = (index+1);
			//利用迴圈增加每筆"餐點種類"
			$('#tabs>ul').append('<li><a href="#tabs-'+tabsi+'">'+value.mealKindName+'</a></li>');	
			//建立好餐點區塊
			$('#tabs').append('<div id="tabs-'+tabsi+'"></div>')
			
		});
		
		//將a結合上"click"事件
		$('a').bind('click', function(){
			var h =$(this).attr("href").substring(1, 7);
			var rowi =$(this).attr("href").substring(6, 7);
			var mealKindID = parseInt($(this).attr("href").substring(6, 7));
// 				console.log(rowi);
// 				console.log(mealKindID);
			//開始時間
// 			var date = new Date();
// 			console.log(date.getTime());
			//產生該種類餐點的頁面
			$.getJSON("<s:url namespace='member' action='neworder!queryMealBean?shopID="+shopID+"&mealKindID="+mealKindID+"'/>", function(data) {
				//接受到資料的時間
// 				var d = new Date();
// 				console.log(d.getTime()-date.getTime());
				
				//先清空divtabs在增加
				$('#tabs-'+kinddiv+'').empty();
				$('#tabs-'+rowi+'').empty();
				kinddiv =rowi;					
				var i = 1;
				//重新增加分頁功能
				$('#tabs-'+rowi+'').append('<section id="product"><ul id="itemContainer" class="clear"></ul></section>');
				$('#tabs-'+rowi+'').append('<div class="holder"></div>');
// 				console.log("HI");
// 				console.log(data);
				//利用each迴圈增加餐點
				$.each(data, function(index, value) {
					//重新建立餐點表單
 					//利用迴圈增加頁面資料
					$('#itemContainer').append('<li id="'+value.mealID+'" data-id="'+value.mealID+'"><img class="mealimage" src="<s:url namespace="owner" action="neworder!queryMealImage?mealID='+value.mealID+'" />" /><h3>'+value.mealName+
								'</h3><p>價格'+value.price+
								'</p></li>');
				})
					$("div.holder").jPages({
		  				containerID : "itemContainer",
		  				perPage : 6
					});
					shoppingcart();
			})
		})
			
		
			//取得某間店的某種餐點種類的餐點
				$.getJSON("<s:url namespace='member' action='neworder!queryMealBean?shopID="+shopID+"&mealKindID="+firstMealKindID+"'/>", function(data) {
// 					console.log(data);
					var i = 1;
					//建立餐點表單
					$('#tabs-1').append('<section id="product"><ul id="itemContainer" class="clear" ></ul></section>');
					$('#tabs-1').append('<div class="holder"></div>');
 					//利用迴圈增加一開始頁面呈現資料
					$.each(data, function(index, value) {
						$('#itemContainer').append('<li id="'+value.mealID+'" data-id="'+value.mealID+'"><img class="mealimage" src="<s:url namespace="owner" action="neworder!queryMealImage?mealID='+value.mealID+'" />" /><h3>'+value.mealName
								+'</h3><p>價格'+value.price+'</p></li>');
					})
					$("div.holder").jPages({
		  				containerID : "itemContainer",
		  				perPage : 6
					});
					shoppingcart();
				})
	    $( "#tabs" ).tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	    $( "#tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
	  })
	  
	//按鈕一次清除購物車所有內容
	  $('input[name="clearcart"]').bind('click', function(){
		  console.log("click");
		  $('.basket ul').empty();
		  clearItems();
	  });
	
  });
  
  //購物車
  function shoppingcart(){
	  $("#product li").draggable({
			
		    // brings the item back to its place when dragging is over
		    revert:true,
		
		    // once the dragging starts, we decrease the opactiy of other items
		    // Appending a class as we do that with CSS
		    drag:function () {
		        $(this).addClass("active");
		        $(this).closest("#product").addClass("active");
		    },
		
		    // removing the CSS classes once dragging is over.
		    stop:function () {
		        $(this).removeClass("active").closest("#product").removeClass("active");
		    }
		});
		
		// jQuery UI Droppable
		$(".basket").droppable({

		    // The class that will be appended to the to-be-dropped-element (basket)
		    activeClass:"active",

		    // The class that will be appended once we are hovering the to-be-dropped-element (basket)
		    hoverClass:"hover",

		    // The acceptance of the item once it touches the to-be-dropped-element basket
		    // For different values http://api.jqueryui.com/droppable/#option-tolerance
		    tolerance:"touch",
		    drop:function (event, ui) {
		        var basket = $(this),
		                move = ui.draggable,
		                itemId = basket.find("ul li[data-id='" + move.attr("data-id") + "']");
				
		        // To increase the value by +1 if the same item is already in the basket
		        if (itemId.html() != null) {
		            itemId.find("input").val(parseInt(itemId.find("input").val()) + 1);
		            //如果使用圖片增加1也可以更新sessionStorage項目
		            var tempmealID = move.attr("data-id");
		            var tempcount = itemId.find("input").val();
// 		            console.log("tempcount:"+tempcount);
		            updateItemCount(tempmealID, tempcount);
		        }
		        else {
		            // Add the dragged item to the basket
		            addBasket(basket, move);

		            // Updating the quantity by +1" rather than adding it to the basket
		            move.find("input").val(parseInt(move.find("input").val()) + 1);
		        }
		    }
		});
		
		function addBasket(basket, move) {
		    basket.find("ul").append('<li data-id="' + move.attr("data-id") + '">'
		            + '<span class="name" id="'+move.find("p").html().substring(2)+'">' + move.find("h3").html()+'</span>'
		            + '<input class="count" value="1" type="text">'
		            + '<button class="delete">✕</button>');
		    //新增商品後，加入至sessionStorage
		    addItemtoJSON(move.attr("data-id"), move.find("h3").html(), move.find("p").html().substring(2), 1);
		    $(".basket ul li button.delete").on("click", function () {
			    $(this).closest("li").remove();
			    console.log($(this).parent("li").attr("data-id"));
			    deleteItem($(this).parent("li").attr("data-id"));
			});
		  //當數量變更時，同時修改sessionStorage的物品數量
			$('#items li input.count').bind('change', function(){
				var tempcount = $(this).val();
// 				console.log(tempcount);
				var tempmealID = $(this).parent().attr("data-id");
// 				console.log(tempmealID);
				updateItemCount(tempmealID, tempcount);
			})
		}
  }
  </script>
  <style>
  .ui-tabs-vertical { width: 65em; }
  .ui-tabs-vertical .ui-tabs-nav { padding: .2em .1em .2em .2em; float: left; width: 12em; }
  .ui-tabs-vertical .ui-tabs-nav li { clear: left; width: 100%; border-bottom-width: 1px !important; border-right-width: 0 !important; margin: 0 -1px .2em 0; }
  .ui-tabs-vertical .ui-tabs-nav li a { display:block; }
  .ui-tabs-vertical .ui-tabs-nav li.ui-tabs-active { padding-bottom: 0; padding-right: .1em; border-right-width: 1px; }
  .ui-tabs-vertical .ui-tabs-panel { padding: 1em; float: left; width: 35em; margin-right:20px;}
  
  .mealimage {width:200px;height:150px;}
  .mealdiv {width:210px;float:left;margin-bottom:20px;}
  .numbercss {width:200px;}
  .floatt {float:left;}
  .shopcart {float:left; border:thin solid grey ; margin-left:10px;width:350px;height:650px;
  border-radius:20px;
  }
  h3{text-align: center;color:black;}
  </style>
</head>
<body>
<div>
	<div class="col-md-5 floatt" >
		<div id="tabs">
		</div>
	</div>
	<aside id="sidebar" style="float:right; margin-right:1em">
	    <div class="basket">
	        <div class="basket_list">
	            <div class="head">
	                <span class="name">Product name</span>
	                <span class="count">Quantity</span>
	            </div>
	            <ul id="items">
	            </ul>
	        </div>
		</div>
		<div class="row" style="padding-top:10px;">
  			<div class="col-md-6" style="text-align:center;"> <a href="<c:url value='check.jsp' />" class="btn btn-default" role="button">結帳</a></div>
  			<div class="col-md-6" style="text-align:center;"><input name="clearcart" class="btn btn-default" type="button" value="清除" /></div>
		</div>
	</aside>
</div>
</body>
</html>