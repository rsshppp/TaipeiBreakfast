<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<!-- jQuery & jQueryUI -->
	<link rel="stylesheet" href="../css/jquery-ui.css">
	<script src="../js/jquery-1.11.2.js"></script>
	<script src="../js/jquery-ui.js"></script>
	<!-- Bootstrap -->
	<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="../bootstrap/js/bootstrap.min.js"></script>
	<!-- timepick -->
	<link rel='stylesheet' href='../css/timepicki.css'/>
	<script type='text/javascript'src='../js/timepicki.js'></script>
	
</head>
<script>
//讀取memberID與店家ID
var memberID = 2;
var shopID = 3;
//優惠券使用與否
var specialpriceStatus;


//購物車相關參數
var shoppingcartObject;
var shoppingcartJSONString;
var shoppingcartJSON;

//測試用購物車
// shoppingcartObject = {}
// shoppingcartObject["memberID"] = memberID;
// shoppingcartObject["shopID"] = shopID;
// shoppingcartObject["items"] = {"2": "鮪魚漢堡, 40, 1", "3":"鮪魚蛋餅, 35, 2"};
// shoppingcartJSONString = JSON.stringify(shoppingcartObject); 
// sessionStorage.setItem("shoppingcart", shoppingcartJSONString);
// console.log(sessionStorage.shoppingcart);



//判斷購物車是否存在
if( (shoppingcartJSONString = sessionStorage.getItem("shoppingcart")) !=null){
	shoppingcartJSON = JSON.parse(shoppingcartJSONString);	
	//檢查購物車裡的會員ID與店家ID是否與現在的一致
	if(shoppingcartJSON.memberID == memberID && shoppingcartJSON.shopID ==shopID){
// 		console.log(shoppingcartJSON.items);
	//檢查一致開始讀入購物車內容
		if(Object.keys(shoppingcartJSON.items).length == 0){
			alert("您沒有購買任何物品");
			history.back();
			window.location.replace("http://localhost:8080/TaipeiBreakfast20150524/Home.jsp");
		}
	  $(function(){
		  //將下訂單與放棄結合事件
		  $('#order').bind('click',function(){
			  if($('#timepicker').attr("data-timepicki-tim")!=undefined||$('#timepicker').attr("data-timepicki-mini")!=undefined){
			  if (confirm("確認送出訂單!") == true) {
					var order = {};
					order["memberID"]=memberID;
					order["shopID"]=shopID;
					var hour = $('#timepicker').attr("data-timepicki-tim");
					var minute = $('#timepicker').attr("data-timepicki-mini");
					order["expectTime"]= hour+"-"+minute+"-00";
					var items =[];
					//讀取Table
					$('table > tbody > tr').each(function () {
						 count = $.trim($(this).find("td:eq(2)").text());
						 price = $.trim($(this).find("td:eq(3)").text());
						 mealid = $.trim($(this).find("td:eq(3)").attr("id"));
						 iteminfo = mealid+","+count+","+price;
						 items.push(iteminfo);
					});
					order["items"]=items;
					order["memo"]=$('#memo').val();
// 					console.log(order);
					var orderString = JSON.stringify(order);
// 					console.log(JSON.stringify(order));
					sessionStorage.clear();
					$.post("<s:url namespace='member' action='neworder!newOrder?orderString="+orderString+"'/>" , function(){});
					window.location.replace("<c:url value='/Home.jsp' />");
			  } else {
			    	alert("取消送出");
			    }
			  }else{
				  alert("為選取取餐時間");
			  }
			  
		  })
		  $('#abandon').bind('click',function(){
			  if (confirm("是否放棄訂單!") == true) {
			        alert("已放棄");
			        sessionStorage.clear();
			        window.location.replace("http://localhost:8080/TaipeiBreakfast20150524/Home.jsp");
			    } else {
			    	alert("取消放棄");
			    }
		  })
		  	//讀取購物車內容
			var items = shoppingcartJSON.items;
			$('#check').append("<table class='table table-hover' id='checktable'><thead><tr><td>餐點圖</td><td>餐點名</td>"+
			"<td>數量</td><td>價格</td></tr></thead><tbody></tbody><tfoot></tfoot></table>")
			var totalprice =0;
			var mealIDarray =[];
			$.getJSON("<s:url namespace='/member' action='neworder!queryShopMeals?shopID=3'/>", function(data){
				console.log(data);
				
				for(var i in items){
					for(var j in data){
						//如果sessionStorage餐點符合店家有販售的
						if(i==data[j].mealID){
						mealIDarray.push(i);
						var mealinfo = items[i].split(",");
						$('#check tbody').append("<tr><td><img class='mealimage' src='<s:url namespace='owner' action='neworder!queryMealImage?mealID="
							+data[j].mealID+"' />' /></td><td>"+data[j].mealName+"</td><td>"
							+mealinfo[2]+"</td><td id='"+i+"'>"+data[j].price+"</td></tr>");
						totalprice += (parseInt(mealinfo[2])*parseInt(data[j].price));
						}
					}
				}
			$('#check tfoot').append("<tr><td></td><td></td><td>總價格</td><td>"+totalprice+"</td></tr>");
			
// 			var d = new Date().getTime();
			
			$.getJSON("<s:url namespace='/member' action='neworder!queryMemberSpecialPriceBean?memberID="+memberID+"'/>", function(data){
// 				var d1 = new Date().getTime();
// 				console.log(d1-d);
// 				console.log(data);
// 				console.log(data[0].mealID);
// 				console.log(mealIDarray);
				//判斷會員是否有優惠券
				if(data[0] !=null){
					for(var i in mealIDarray){
						//如果餐點中有餐點可使用優惠券折價
						if(mealIDarray[i] ==data[0].mealID){
							$('#aftercheck').append('<div id="fortime"><input id="use" type="checkbox" /><label for="checkbox">'
									+data[0].mealBean.mealName+'優惠價為'+data[0].specialPrice+'元'+' (優惠券限用一次)</label><label><span class="glyphicon glyphicon-time" aria-hidden="true"></span>取餐時間</label></div>');
							$('#aftercheck label').css({'margin-left':'50px'});
							//如果打勾優惠券則動態計算總價和顯示折扣
							$('#use').bind('change', function(){
// 								alert("change")
								var checked = $(this).prop("checked");
								var value=0;
								if(checked){
									$('#'+data[0].mealID+'').text(data[0].specialPrice);
									$('table > tbody > tr').each(function () {
// 								        console.log(parseInt($.trim($(this).find("td:eq(3)").text()))*parseInt($.trim($(this).find("td:eq(2)").text())))
								        value += parseInt($.trim($(this).find("td:eq(3)").text()))*parseInt($.trim($(this).find("td:eq(2)").text())); 
								    });
// 									console.log(value);
									$('table > tfoot > tr').find('td:eq(3)').text(value);
									specialpriceStatus =checked;
								}else{
									$('#'+data[0].mealID+'').text(data[0].mealBean.price);
									$('table > tbody > tr').each(function () {
// 								        console.log(parseInt($.trim($(this).find("td:eq(3)").text()))*parseInt($.trim($(this).find("td:eq(2)").text())))
								        value += parseInt($.trim($(this).find("td:eq(3)").text()))*parseInt($.trim($(this).find("td:eq(2)").text())); 
								    });
									$('table > tfoot > tr').find('td:eq(3)').text(value);
									specialpriceStatus =checked;
								}
							})
							break;
						}
						//如果沒有相符合餐點則顯示沒有可用的
						if(i == mealIDarray[mealIDarray.length]){
							$('#aftercheck').append('<div id="fortime"><label style="padding-left:70px;">沒有可用優惠券</label><label style="margin-left:205px;"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>取餐時間</label></div>')
						}
					}
				}else{
					$('#aftercheck').append('<div id="fortime"><label style="padding-left:70px;">沒有可用優惠券</label><label style="padding-left:205px;"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>取餐時間</label></div>')
				}
			//加上時間
			$('#fortime').append("<input id='timepicker' type='text' name='timepicker' />");   
			$('#timepicker').timepicki({show_meridian:false});
			//調整版型
			
			$('#aftercheck>div').css({'margin-left':'900px'});
			$('#aftercheck span').css({'margin-right':'10px'});
			$('#timepicker').parent().css({'margin-left':'380px'});
			})
		})
			$('#finaldiv').css({'float':'right','margin-right':'145px','margin-top':'10px'})
	  })
	}else{
		alert("錯誤的會員身分或在錯誤店家購物");
		history.back();
		window.location.replace("http://localhost:8080/TaipeiBreakfast20150524/Home.jsp");
	}
}else{
	  alert("您還沒有購物");
	  history.back();
	  window.location.replace("http://localhost:8080/TaipeiBreakfast20150524/Home.jsp");
	}
	

</script>
<style>
.mealimage {width:100px;height:75px;}
td {text-align:center;}
#smallmodal {width:870px;}
</style>
<body>
<div id="check"></div>
<!-- Small modal -->
<div id="smallmodal">
<button style="margin-left:100px;" type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-sm">訂單意見欄</button>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content text-center" >
      <div><h3>請輸入文字</h3></div><hr>
      <textarea id="memo" rows="5" cols="25"></textarea>
    </div>
  </div>
</div>
</div>
<div id="aftercheck">
</div>
<div id="finaldiv">
<a id="order" href="#" class="btn btn-default btn-lg " role="button"> 下訂單 </a>
<a id="abandon" class="btn btn-default" href="#" role="button">放棄訂單</a>
</div>
</body>
</html>