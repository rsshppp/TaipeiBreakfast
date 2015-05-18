<h1>菜單</h1>
<div>
	<div>
		<select id="shopsel">

		</select>
	</div>
	<div>
		<table id="menu">
			
		</table>
	</div>
	<script>
		var jsondata = {owerID:1};
		var shopID;
    	$.get("<c:url value='/meal/mealadd!shoplist'/>", jsondata, 
    		function (data) {
    			var list=JSON.parse(data.redata);
        		console.log(list);
        		$.each(list,function(u,i){
					$('#shopsel').append("<option value='" + i.shopID + "'>" + i.shopName + "</option>");
	            })
	            shopID = {shopID:$('#shopsel').val()};
        		createtable();
    		});
		$('#shopsel').change(function(){
			shopID = {shopID:$('#shopsel').val()}
			while(document.getElementById("menu").hasChildNodes()){
					document.getElementById("menu").removeChild(document.getElementById("menu").childNodes[0]);
				}
			createtable();
			})
		function createtable(){
			if (document.getElementById("menu").hasChildNodes()) {
                document.getElementById("menu").removeChild(document.getElementById("menu").childNodes[0]);
            };
			$('#menu').append("<thead><tr><th>名稱</th><th>種類</th><th>價格</th><th>狀態</th></tr></thead>")	;
			$('#menu').append("<tbody id='body'></thead>");	
            console.log(shopID);
            
            $.get("<c:url value='/meal/mealadd!menulist'/>",shopID,function(data){
					var list=JSON.parse(data.redata);
					$.each(list,function(i,v){
						$('#body').append("<tr><td>"+v.mealName+"</td><td>"+v.mealKindName+"</td><td>"+v.price+"</td><td>"+v.mealStatus+"</td><td><a href=''>修改<a></td></tr>");	
						})
                })
		}
	</script>
</div>