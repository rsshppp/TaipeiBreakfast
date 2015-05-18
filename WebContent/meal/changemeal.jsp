<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>

<link href="bootstrap.css" rel="stylesheet">
<link href="bootstrap-switch.css" rel="stylesheet">
<script src="bootstrap-switch.js"></script>
</head>
<body>
	<div id="dv01">
		<form id="form1" action="<c:url value='/meal/mealadd!mealadd'/>"
			method="POST" enctype=multipart/form-data>
			<div>
				店家 <input type="text" id="select1" name="bean.shopID" readonly/>
			</div>
			<div>
				餐點名稱 <input type="text" id="mealname" name="bean.mealName" readonly/>
			</div>
			<div>
				餐點種類 <select id="select2" name="bean.mealKindID"></select>
			</div>
			<div>
				價格 <input type="text" id="price" value="60" name="bean.price" />
			</div>
			<div id="imagediv">
				圖片:<div id="result" style="width: 50px; height: 50px"></div>
				<input type="radio" value="1" name="imageselect" checked/>不修改圖片 
				<input type="radio" value="2" name="imageselect"/>修改圖片
				<br>
				<input type="checkbox" name="my-checkbox" checked>
				<br> 
				
			</div>
			<input type="submit" value="修改">
		</form>
	</div>
	<script>
	var shopID=3;
	var mealID=${param.mealID};
	var kindlist;
    $.ajax({
        url: "<c:url value='/meal/mealadd!mealKind'/>",
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

    $("input[name=imageselect]:radio").change(function(){
		var select=$("input[name=imageselect]:radio:checked").val();
		if(select==2){
			$("#imagediv").append('<input type="file" accept="image/*" id="file" name="bean.mealImage" />')
			}
		else if(select==1){
			console.log(select);
			$("#file").remove();
			}
        })
	</script>
</body>
</html>