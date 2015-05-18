<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#image1 {
	width: 50px;
	height: 50px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script>
    function ProcessFile( e ) {   
        var file = document.getElementById('file').files[0];
        console.log(file);
        if (file) {
            if (document.getElementById("result").hasChildNodes()) {
                document.getElementById("result").removeChild(document.getElementById("result").childNodes[0]);
            }
            var reader = new FileReader();
            reader.onload = function ( event ) {                 
                var txt = event.target.result;
                var img = document.createElement("img");
                img.id="image1";
                img.src = txt;
                document.getElementById("result").appendChild( img );
            };
        }else{
        	if (document.getElementById("result").hasChildNodes()) {
                document.getElementById("result").removeChild(document.getElementById("result").childNodes[0]);
            }
            }
        reader.readAsDataURL( file );
    }
    function contentLoaded() {
        document.getElementById('file').addEventListener( 'change' ,ProcessFile , false );
    }
    window.addEventListener( "DOMContentLoaded" , contentLoaded , false );
    </script>
</head>
<body>
	<h1>新增菜單</h1>
	<div id="dv01">
		<form id="form1" action="<c:url value='/meal/mealadd!mealadd'/>"
			method="POST" enctype=multipart/form-data>
			<div>
				店家 <select id="select1" name="bean.shopID"></select>
			</div>
			<div>
				餐點名稱 <input type="text" id="mealname" name="bean.mealName"
					value="" />
			</div>
			<div>
				餐點種類 <select id="select2" name="bean.mealKindID"></select>
			</div>
			<div>
				價格 <input type="text" id="price" value="60" name="bean.price" />
			</div>
			<div>
				圖片 <input type="file" accept="image/*" id="file"
					name="bean.mealImage" />
				<div id="result" style="width: 50px; height: 50px"></div>
			</div>
			<input type="submit" value="新增">
		</form>
	</div>

	<script>
	var jsondata = {owerID:1};
	$.get("<c:url value='/meal/mealadd!shoplist'/>", jsondata, 
		function (data) {
			var list=JSON.parse(data.redata);

    		$.each(list,function(u,i){
				$('#select1').append("<option value='" + i.shopID + "'>" + i.shopName + "</option>");
        	})
		});
       
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
        
    </script>
	<script>

    $('#form1').ajaxForm({
        beforeSend:function(){
        	while (document.getElementById("dv01").hasChildNodes()) {
                document.getElementById("dv01").removeChild(document.getElementById("dv01").childNodes[0]);
            }
        },
		complete:function(msg){               
                var txt = JSON.parse(msg.responseJSON.redata);
                var gg=JSON.parse(txt.image);
                var message=txt.Message;
                var data = encode(new Uint8Array(gg));;
                var img = document.createElement("img");
                img.id="image2";
                img.src = 'data:image/jpg;base64,'+data;
                $('#dv01').append("<h2>"+message+"</h2>");
                document.getElementById("dv01").appendChild( img );
			}
        })
	function encode (input) {
    var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var output = "";
    var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
    var i = 0;

    while (i < input.length) {
        chr1 = input[i++];
        chr2 = i < input.length ? input[i++] : Number.NaN; // Not sure if the index 
        chr3 = i < input.length ? input[i++] : Number.NaN; // checks are needed here

        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;

        if (isNaN(chr2)) {
            enc3 = enc4 = 64;
        } else if (isNaN(chr3)) {
            enc4 = 64;
        }
        output += keyStr.charAt(enc1) + keyStr.charAt(enc2) +
                  keyStr.charAt(enc3) + keyStr.charAt(enc4);
    }
    return output;
}
    </script>
</body>
</html>