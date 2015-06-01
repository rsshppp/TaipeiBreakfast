<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 確保適當的呈現和觸控縮放效果，加入可視區域(Viewport) -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>菜單管理</title>
<!-- Bootstrap -->
<!-- social icon 使用 -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- Custom styles -->
<link href="<c:url value='/css/carousel.css'/>" rel="stylesheet">
<link rel="icon" href="favicon.ico">
<!-- HTML5 shim and Respond.js 讓 IE8 支援 HTML5 元素與媒體查詢 -->
<!-- 警告：Respond.js 無法在 file:// 協定下運作 -->
<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

<!-- jQuery (Bootstrap 所有外掛均需要使用) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- 依需要參考已編譯外掛版本（如下），或各自獨立的外掛版本 -->
<!-- Custom Javascript -->
<script src="<c:url value='/js/holder.js'/>"></script>
<style>
.imgsimple {
	width: 60px;
	height: 60px;
}
#image1 {
	width: 60px;
	height: 60px;
}
#image2 {
	width: 60px;
	height: 60px;
}
</style>

<script src="<c:url value='/js/jquery.min.js'/>"></script>
<script src="<c:url value='/js/jquery.form.js'/>"></script>
<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
<link href="<c:url value='/bootstrap/css/bootstrap.css'/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value='/bootstrap/js/bootstrap.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui.js'/>"></script>
<link href="//cdn.datatables.net/1.10.7/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css" />
<link href="<c:url value='/bootstrap/css/bootstrap-switch.css'/>"
	rel="stylesheet">
<script src="<c:url value='/bootstrap/js/bootstrap-switch.js'/>"></script>
</head>
<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value='/index' />"> <img alt="Brand"
					src="<c:url value='/image/proj_icon_2.png'/>"
					style="max-width: 100px; margin-top: -12px;">
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
							<span class="glyphicon glyphicon-log-out"></span> 登出
					</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div id="jumb" class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<div class="list-group" id="menu"></div>
					
					<a class="list-group-item text-center" href="#">店鋪管理</a> <a
						class="list-group-item text-center" href="<c:url value="/owner/selectmeal"/>">菜單管理</a> <a
						class="list-group-item text-center" href="<c:url value="/owner/selectad"/>">廣告申請</a> <a
						class="list-group-item text-center" href="<c:url value="/owner/SpecialPrice"/>">優惠卷管理</a> <a
						class="list-group-item text-center" href="#">查詢銷售報表</a> <a
						class="list-group-item text-center" href="#">查詢交易紀錄</a> <a
						class="list-group-item text-center" href="#">賣家基本資料</a>
				</div>
				<div class="col-md-10" style="margin-top: 20px;">

					<!-- 功能放這邊！！！ -->
					<div class="row">
						<div class="col-sm-2">
							店家:
							<select id="shopsel">
	
							</select>
						</div>
						
						<div class="col-sm-2 col-md-offset-8 text-right" style="margin-bottom: 20px;margin-top: 20px">
							<button type="button" class="btn btn-primary btn" data-toggle="modal"
								data-target="#insertMeal">新增餐點</button>
						</div>
					</div>
					<div id="creattable" style="margin-top: 10px;"></div>
					
					
					<script>
						var jsondata = {
							owerID : 1
						};
						var shopID;
						$.get("<c:url value='/owner/mealaction!shoplist'/>",
								jsondata, function(data) {
									var list = JSON.parse(data.redata);
									console.log(list);
									$.each(list, function(u, i) {
										$('#shopsel').append(
												"<option value='" + i.shopID + "'>"
														+ i.shopName
														+ "</option>");
									})
									shopID = {
										shopID : $('#shopsel').val()
									};
									createtable();
								});
						$('#shopsel').change(function() {
											shopID = {
												shopID : $('#shopsel').val()
											}
											createtable();
											$("#select1 option[value='"+ $('#shopsel').val()
													+ "']").prop('selected', true);
										})
						function createtable() {
							while (document.getElementById("creattable").hasChildNodes()) {
								document.getElementById("creattable").removeChild(
										document.getElementById("creattable").childNodes[0]);
							}
							$('#creattable')
									.append("<table id='menu1' class='display'></table>");
							$('#menu1')
									.append(
											"<thead><tr><th>圖片</th><th>名稱</th><th>種類</th><th>價格</th><th>狀態</th><th>修改</th></tr></thead>");
							$('#menu1').append("<tbody id='tby'></tbody>");
							console.log(shopID);

							$.get("<c:url value='/owner/mealaction!menulist'/>",
											shopID,function(data) {
												if(data.redata!=null){
												var list = JSON.parse(data.redata);
												$.each(list,function(i, v) {
														$('#tby').prepend("<tr><td><img class='img-thumbnail imgsimple' src='<s:url action='imageAction!getMealImage?imageId="
																							+ v.mealID
																							+ "' />'></td><td>"
																							+ v.mealName
																							+ "</td><td>"
																							+ v.mealKindName
																							+ "</td><td>"
																							+ v.price
																							+ "</td><td><input type='button' value='"
																							+ v.mealStatus
																							+ "' onclick='changestudes(event)' id='sbtn"
																							+ v.mealID
																							+ "' /></td><td><button type='button' class='btn btn-default btn' id='insb"
																							+v.mealID+"' onclick='getmeal(event)' data-toggle='modal'data-target='#updateMeal'>修改</button></td></tr>");
																	var t = "#sbtn"
																			+ v.mealID;
																	if (v.mealStatus == "販售中") {
																		$(t)
																				.addClass(
																						"btn btn-primary");
																	} else if (v.mealStatus == "下架中") {
																		$(t)
																				.addClass(
																						"btn btn-warning");
																	}
																	})
																}
												$('#menu1').DataTable({
												    "iDisplayLength": 5,
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
						}

						function changestudes(event) {
							var btid = event.target.id;
							var mealsid = {
								mealID : btid.split("sbtn")[1]
							};
							$.get("<c:url value='/owner/mealaction!changestatus'/>",
											mealsid,
											function() {
												var Old_Class = $("#" + btid)
														.attr('class');
												$("#" + btid).removeClass(
														Old_Class);
												if (Old_Class == "btn btn-primary") {
													$("#" + btid).val("下架中");
													$("#" + btid).addClass(
															"btn btn-warning");
												} else if (Old_Class == "btn btn-warning") {
													$("#" + btid).val("販售中");
													$("#" + btid).addClass(
															"btn btn-primary");
												}
											})
						}
					</script>
					<!-- 功能放這邊！！！ -->

				</div>
			</div>
			<!-- 互動視窗（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel2" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel2">登入</h4>
						</div>
						<div class="modal-body">
							<div id="test"></div>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
<!-- 							新增餐點 -->
	<div class="modal fade" id="insertMeal" tabindex="-1" role="dialog"
					aria-labelledby="mySmallModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">新增菜單</h4>
							</div>
							<div class="modal-body">
<!-- 							新增餐點動態內碼 -->
								
	<div id="dv01">
		<form id="form1" action="<c:url value='/owner/mealaction!mealadd'/>"
			method="POST" enctype=multipart/form-data>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">店家:</div>
				<div class="col-sm-5">
					<select id="select1" name="bean.shopID"></select>
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">餐點名稱:</div>
				<div class="col-sm-5">
					<input type="text" name="bean.mealName" value="" required/>
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">餐點種類:</div>
				<div class="col-sm-5">
					<select id="select2" name="bean.mealKindID"></select>
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">價格:</div>
				<div class="col-sm-5">
					<input type="number" min="0" value="60" name="bean.price" required/>
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2">圖片:</div>
				<div class="col-sm-5">
					<div id="result" ></div>
					<input type="file" accept="image/*" id="file"
					name="bean.mealImage" />
				</div>
			</div>
			<div class="row" style="margin-top: 5px">
				<div class="col-sm-2" style="margin: 5px">
					<input type="submit" class='btn btn-default' value="新增">
				</div>
			</div>
		</form>
	</div>

	<script>
	var jsondata = {owerID:1};
	$.get("<c:url value='/owner/mealaction!shoplist' />", jsondata, 
		function (data) {
			var list=JSON.parse(data.redata);

    		$.each(list,function(u,i){
				$('#select1').append("<option value='" + i.shopID + "'>" + i.shopName + "</option>");
        	})
		});
       
        var kindlist;
        $.ajax({
            url: "<c:url value='/owner/mealaction!mealKind' />",
            data: "",
            type:"POST",
            dataType:'text',

            success: function(msg){
                kindlist=JSON.parse(msg);
                    var ss=JSON.parse(kindlist.redata);
                    console.log(ss);
                    $.each(ss,function(a,b){
                    	$('#select2').append("<option value='" + b.mealKindID + "'>" + b.mealKindName + "</option>");
                    	$('#select3').append("<option value='" + b.mealKindID + "'>"+ b.mealKindName+ "</option>");
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

        },
		complete:function(msg){               
                var txt = JSON.parse(msg.responseJSON.redata);
                var message=txt.Message;
                alert(message);
                document.forms["form1"].reset();
                createtable();
                $('#over').click();
                if (document.getElementById("result").hasChildNodes()) {
                    document.getElementById("result").removeChild(document.getElementById("result").childNodes[0]);
                }
			}
        })
	
    </script>
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
<!-- 							新增餐點動態內碼 -->
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" id="over" data-dismiss="modal" aria-hidden="true">Close</button>
			<!-- 					<button type="button" onclick="" class="btn btn-primary">Save changes</button> -->
							</div>
						</div>
					</div>
				</div>
<!-- 							新增餐點 -->
				
<!-- 				修改餐點 -->
	<div class="modal fade" id="updateMeal" tabindex="-1" role="dialog"
					aria-labelledby="mySmallModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="upModalLabel">修改</h4>
							</div>
							<div class="modal-body">
<!-- 							修改餐點動態內碼 -->
					<div id="dv02">
						<form id="form2"
							action="<c:url value='/owner/mealaction!mealchange'/>"
							method="POST" enctype=multipart/form-data>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2">
									店家: <input type="hidden" name="bean.shopID" id="sh01"
										value="">
								</div>
								<div class="col-sm-5">
									<!-- 					<input type="text" id="shopname" name="bean.shopName" readonly/> -->
									<label id="shopname"></label>
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2">

									餐點名稱: <input type="hidden" name="bean.mealID" id="nm01"
										value="">
								</div>
								<div class="col-sm-3">
									<label id="mealname"></label>
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2">餐點種類:</div>
								<div class="col-sm-3">
									<select id="select3" name="bean.mealKindID"></select>
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2">價格:</div>
								<div class="col-sm-3">
									<input type="text" id="price" value="60" name="bean.price" required/>
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-md-2">
									修改圖片: <input type="hidden" name="bean.imageChange"
										id="imageChange" value="false">
								</div>
								<div id="imagediv" class="col-sm-3">
									<div id="imgdiv" style="width: 60px; height: 60px">
										<img id="image2" src="">
									</div>
									<input type="checkbox" name="my-checkbox" id="check1">
								</div>
							</div>
							<div class="row" style="margin-top: 5px">
								<div class="col-sm-2 ">

									<a class='btn btn-default' role='button'
										href="<c:url value='/owner/selectmeal.jsp'/>">回菜單</a>
								</div>
								<div class="col-sm-3 ">
									<input type="submit" class='btn btn-default' value="修改">
								</div>
							</div>
						</form>
					</div>
<!-- 							修改餐點動態內碼 -->
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" id="over2" data-dismiss="modal" aria-hidden="true">Close</button>
							</div>
						</div>
					</div>
				</div>
<!-- 				修改餐點 -->
				<script>
					$("[name='my-checkbox']").bootstrapSwitch();
					var mealID;
					var shID;
					function getmeal(event){
						$('#shopname').empty();
						$('#mealname').empty();
						shID = shopID.shopID;
						mealID = event.target.id.split("insb")[1]
						var selone = {shopID : shID,mealID : mealID};
						$.get("<c:url value='/owner/mealaction!onemeal'/>",
								selone, function(data) {
									var list = JSON.parse(data.redata);
									console.log(list);


									$('#shopname').append(list.shopName);
									console.log(list.shopID);
									$('#sh01').val(list.shopID);
									$('#mealname').append(list.mealName);
									$('#nm01').val(list.mealID);
									$('#price').val(list.price);
									$("#select3 option[value='"
													+ list.mealKindID
													+ "']").prop(
											'selected', true);
									if(!$('#check1').is(':checked')){
									$('#image2').attr(
											"src",
											"<s:url action='imageAction!getMealImage?imageId="
													+ mealID + "' />");
									}
									//image=list.mealImage;
									//byteimage(image);
								});
					}
					$('#select3').change(
							function() {
								var file = document.getElementById('file2').files[0];
								if (!file) {
									var ss = $('#select3').val();
									$('#image2').attr("src",
											"<s:url action='imageAction!getMealImage?typeId="+ ss + "' />");
								}
					});
					$('input[name="my-checkbox"]').on(
							'switchChange.bootstrapSwitch',
							function(event, state) {
								$("#file2").remove();
								if (state) {
									$('#imageChange').val('true');
									$("#imagediv")
											.append(
													'<input type="file" accept="image/*" id="file2" name="bean.mealImage" />');
									var ss = $('#select3').val();
									$('#image2').attr(
											"src",
											"<s:url action='imageAction!getMealImage?typeId="
													+ ss + "' />");

								    document.getElementById('file2').addEventListener( 'change' ,ProcessFile2 , false );

								} else {
									$('#imageChange').val('false');
									$("#file2").remove();
									$('#image2').attr(
											"src",
											"<s:url action='imageAction!getMealImage?imageId="
													+ mealID
													+ "' />");
								}
							});
					$('#form2').ajaxForm({
				        beforeSend:function(){

				        },
						complete:function(msg){               
				                var txt = JSON.parse(msg.responseJSON.redata);
				                var message=txt.Message;
				                alert(message);
				                document.forms["form2"].reset();
				                createtable();
				                $('#over2').click();
				                
							}
				        })
				</script>
				<script>
			    function ProcessFile2( e ) {   
			        var file = document.getElementById('file2').files[0];
			        console.log(file);
			        if (file) {
			            var reader = new FileReader();
			            reader.onload = function ( event ) {                 
			                var txt = event.target.result;
// 			                var img = document.createElement("img");
// 			                img.id="image1";
// 			                img.src = txt;
// 			                document.getElementById("result").appendChild( img );
			                $('#image2').attr('src',txt);
			            };
			        }else{
			        	
			            }
			        reader.readAsDataURL( file );
			    }
			    
			    </script>				
				
			</div>
	</div>
	<div class="container marketing">
		<footer>
			<p class="pull-left">&copy; 2015 Taipei Breakfast. All rights
				reserved. &middot;</p>
			<p class="pull-left">
				<a href="#">隱私權政策</a> &middot; <a href="#">常見問題</a>
			</p>
			<p class="pull-right">
				<a href="https://www.facebook.com/taipeibreakfast"><i
					id="social" class="fa fa-facebook-square fa-3x social-fb"></i></a> <a
					href="https://twitter.com/taipeibreakfast"><i id="social"
					class="fa fa-twitter-square fa-3x social-tw"></i></a> <a
					href="https://plus.google.com/+TaipeiBreakfast-page"><i
					id="social" class="fa fa-google-plus-square fa-3x social-gp"></i></a> <a
					href="mailto:taipeibreakfast@gmail.com"><i id="social"
					class="fa fa-envelope-square fa-3x social-em"></i></a>
			</p>

		</footer>
	</div>
	<!-- /.container -->


</body>
</html>
