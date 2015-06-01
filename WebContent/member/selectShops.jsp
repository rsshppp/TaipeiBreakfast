<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<!-- DATATABLE -->
<script src="../js/jquery.dataTables.min.js"></script>
<link href="../css/jquery.dataTables.min.css" rel="stylesheet">
<script>
jQuery(function($){
	$('#queryShops').append("<table id='shopsTable'><thead><tr><td>店家標誌</td><td>店名"+
			"</td><td>電話</td><td>地址</td><td>營業時間</td></tr></thead><tbody id='shopcontent'></tbody></table>")
	$.post("<s:url value='/member/neworder!selectAllShops' />", function(data){
		console.log(data);
		$.each(data, function(index, value){
			$('#shopcontent').append("<tr><td><a href='<c:url value='/member/shoppingcart.jsp?shopID="+value.shopID+"' />'><img src='<c:url value='/member/neworder!queryShopImage?shopID="+value.shopID+
					"' />' /></a></td><td>"+value.shopName+"</td><td>"+value.shopPhone+
					"</td><td>"+value.shopCity+value.shopArea+value.shopAddr+"</td><td>"+value.beginBusinessTime.substring(11)+'~'+value.endBusinessTime.substring(11)+"</td></tr>")
		})
		//初始化DataTable
		$('#shopsTable').DataTable({"iDisplayLength": 5,
			    "aLengthMenu": [[5, 10, 20], [5, 10, 20]],
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
			    },
			    "columnDefs": [{ "orderable": false, "targets": 0 }]
			  });
	})
	
})
</script>
	
<body>
	<div>
		<div id="queryShops">
		
		</div>
	</div>
</body>
</html>