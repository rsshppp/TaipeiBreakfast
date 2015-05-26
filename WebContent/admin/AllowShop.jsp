<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<title>Taipei Breakfast - 台北早餐通</title>

<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href="../css/carousel.css" rel="stylesheet">
<link rel="icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/table.css'/>" />
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> -->
<!-- <script src="http://malsup.github.com/jquery.form.js"></script> -->
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery.form.js"></script>

<style>
tr{
 height:30px;
} 
</style>

</head>
<body>

	<div>
	<h3>審核店鋪</h3>
	<form method="post">
		<table>
		
	<thead>
	<tr>
		<th>shopID</th>
		<th>ownID</th>
		<th>logoImage</th>
		<th>shopName</th>
		<th>shopPhone</th>
		<th>shopCity</th>
		<th>shopArea</th>
		<th>shopAddr</th>
		<th>beginBusinessTime</th>
		<th>businessTimeNote</th>
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
    	window.location = "";
    }
    function nota(sid){
    	console.log(2);
    	$.get("<c:url value='/pe/duAction!notallowShop.action'/>?sf.shopID="+sid+"")
    	window.location = "";
    }
    
    $.get("<c:url value='/pe/duAction!shoplist.action'/>",
		function (data) {
			var list=JSON.parse(data.redata);
    		console.log(list);
    		$.each(list,function(u,i){
				$('#t1').append("<tr>"+
					"<td>"+i.shopID+"</td>"+
					"<td>"+i.ownID+"</td>"+
					"<td>"+i.logoImage+"</td>"+
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
        	})
		}
	);
    
    </script>
	
<br><br>
<a href="<c:url value=''/>" >後台首頁</a>
</body>
</html>