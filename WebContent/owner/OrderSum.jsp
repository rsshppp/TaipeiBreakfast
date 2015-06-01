<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<!-- jQuery & jQueryUI -->
<link rel="stylesheet" href="../css/jquery-ui.css">
<script src="../js/jquery-1.11.2.js"></script>
<script src="../js/jquery-ui.js"></script>
<!-- Bootstrap -->
<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../bootstrap/js/bootstrap.min.js"></script>
<!-- DataTable -->
<script src="../js/jquery.dataTables.min.js"></script>
<link href="../css/jquery.dataTables.min.css" rel="stylesheet">
<body>
<div id="orderSumTable">
</div>
</body>
<script>
	//shopID
	var shopID = 3;

	var cyclei = 1;
	jQuery(function($){
		$('#orderSumTable').append("<table id='SumTable'><thead><tr><td>訂單編號</td><td>訂單內容</td><td>總價</td><td>取餐時間</td><td>訂單狀態</td></tr></thead><tbody id='itbody'></tbody></table>")
		reload();
		//初始化DataTable
		$('#SumTable').DataTable({"iDisplayLength": 5,
		    "aLengthMenu": [[5, 10, 20, -1], [5, 10, 20, "All"]],
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
	})
	function updateCond(){
		$('select').bind('change', function(event){
			var OrderCondID = $(this).val();
			var id = 'orderSumID'+$(this).attr("id").substr(6, 6);
			var OrderSumID = document.getElementById(id).textContent;
// 			alert(OrderSumID);
// 			alert(OrderCondID);
 			$.post("<s:url namespace='owner' action='oneOrderSum!updateOrderCond?orderSumID="+OrderSumID+"&orderCondID="+OrderCondID+"'/>" , function(data){
 				if(data.match("true")){
 					console.log(data);
 					var itbody = document.getElementById("itbody");
 					while(itbody.hasChildNodes()){
 						itbody.removeChild(itbody.childNodes[0]);
 					}
 					
 					reload();
 				}else{
 				reload();
 				alert("變更失敗");
 				}
 			});
		})
	}
	function reload(){
	$.getJSON("<s:url namespace='owner' action='oneOrderSum!queryOrderSum?shopID="+shopID+"'/>", function(data){
		var jobject = JSON.parse(data.jSONString).OrderSums;
// 		console.log(jobject);
		$.each(jobject, function(index, value){
			var expectTime = value.expectTime;
			var orderCondID = value.orderCondID;
			var orderSumID = value.orderSumID;
			var totalPrice = value.totalPrice;
			var orderDetail = value.orderDetail;
			var content ="";
			//讀取訂單明細的值
			$.each(orderDetail, function(index1, value1){
// 				console.log(value1);
				content += value1.mealName+"*"+value1.count+"<br>"; 
			})
// 			console.log(content);
			if(orderCondID==1){
				$('#itbody').append("<tr><td id=\"orderSumID"+cyclei+"\">"+orderSumID+"</td><td>"
						+content+"</td><td>"+totalPrice+"</td><td>"
						+expectTime+"</td><td><select id=\"select"+cyclei+"\"><option id=\"opt\" value=1>處理中</option><option id=\"opt\" value=2>已接受</option><option id=\"opt\" value=3>已拒絕</option><option id=\"opt\" value=4>已完成</option></select></td></tr>");
				}else if (orderCondID==2){
					$('#itbody').append("<tr><td id=\"orderSumID"+cyclei+"\">"+orderSumID+"</td><td>"
							+content+"</td><td>"+totalPrice+"</td><td>"
							+expectTime+"</td><td><select id=\"select"+cyclei+"\"><option id=\"opt\" value=2>已接受</option><option id=\"opt\" value=4>已完成</option></select></td></tr>");
				}else if (orderCondID==3){
					$('#itbody').append("<tr><td id=\"orderSumID"+cyclei+"\">"+orderSumID+"</td><td>"
							+content+"</td><td>"+totalPrice+"</td><td>"
							+expectTime+"</td><td><select id=\"select"+cyclei+"\"><option id=\"opt\" value=3>已拒絕</option></select></td></tr>");
				}
			cyclei++;
		})
		//將select選項結合上事件
		updateCond();
	})
	}
</script>
</html>