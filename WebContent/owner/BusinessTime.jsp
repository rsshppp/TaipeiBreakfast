<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<!-- datetimepicker -->
<link rel="stylesheet" type="text/css" href="../css/DateTimePicker.css" />
<script type="text/javascript" src="../js/DateTimePicker.js"></script>
<!-- AjaxForm -->
<script src="../js/jquery.form.js"></script>
</head>
<script>
//從Session讀取店家ID
var shopID = 3;
//
var beginBusinessTime;
var endBusinessTime;
var businessTimeNote;
var daysoffs;
	$(function(){
		//將shopID訊息加入form
		$('#removedaysoffform').append("<input type='hidden' value='"+shopID+"' name='shopID' />");
		$('#updateMemo').append("<input type='hidden' value='"+shopID+"' name='shopID' />");
		$('#submitusage').bind('click', queryDayoffs());
		
		$('#removedaysoffform').ajaxForm({
			complete :function(data){
				var text = data.responseText;
// 				console.log(data.responseText);
				if(text == "true"){
					alert('刪除成功');
					//重新整理剩下休假日
					$.getJSON("<s:url namespace='owner' action='businessTime!queryShopBusinessTime?shopID="+shopID+"' />", function(data){
						var daysoffBeans = data[0].daysoffBeans;
						daysoffs = [];
						for (var i in daysoffBeans){
// 							console.log("這是重新整理獲得")
							daysoffs.push(daysoffBeans[i].daysoff.substring(0, 10));
						}
						queryDayoffs(daysoffs);
					});
				}else{
					alert('未選擇任何日期');
				}
			}
		})
		$('#updateMemo').ajaxForm({
			complete :function(data){
				var text = data.responseText;
// 				console.log(data.responseText);
				if(text == "true"){
					alert('修改成功');
				}else{
					alert('修改失敗，請稍候在試');
				}
			}
		})
		
		
		$.getJSON("<s:url namespace='owner' action='businessTime!queryShopBusinessTime?shopID="+shopID+"' />", function(data){
			beginBusinessTime = data[0].beginBusinessTime.substring(11, 16).split(":");
			endBusinessTime = data[0].endBusinessTime.substring(11, 16).split(":");
			businessTimeNote = data[0].businessTimeNote;
			var daysoffBeans = data[0].daysoffBeans;
			daysoffs = [];
			for (var i in daysoffBeans){
				daysoffs.push(daysoffBeans[i].daysoff.substring(0, 10));
			}
			console.log(daysoffs);
// 			console.log(beginBusinessTime);
// 			console.log(businessTimeNote);
			//initialize DateTimePicker
			var d = new Date();
			var minDate = d.getFullYear()+"-"+(parseInt(d.getMonth())+1)+"-"+d.getDate();
// 			console.log(minDate);
			$("#dtBox").DateTimePicker({
				dateFormat:"yyyy-MM-dd", 
				minDate: minDate, 
				titleContentTime:"選擇後修改時間", 
				titleContentDate:"選擇後修改日期",
				buttonsToDisplay:["HeaderCloseButton", "SetButton"], 
				addEventHandlers : function(){
					this.setDateTimeStringInInputField($('#startTime'), 
							new Date(0, 0, 0, beginBusinessTime[0], beginBusinessTime[1], 0, 0));
					this.setDateTimeStringInInputField($('#endTime'), 
							new Date(0, 0, 0, endBusinessTime[0], endBusinessTime[1], 0, 0));
				},
			});
			
			queryDayoffs(daysoffs);
			
			//附加起始營業時間、休假日資訊
			$('#businessMemo textarea').text(businessTimeNote);
			})
			//更新營業時間事件
			$('#startTime').bind('change', function(){
				var startTime = $(this).val()+":00";
				var endTime = $('#endTime').val()+":00";
 				$.post('<s:url namespace="owner" action="businessTime!updateBusinessTime?beginBusinessTime='
 						+startTime+'&shopID='+shopID+'&endBusinessTime='+endTime+'"/>', function(data){
					if(data == "true"){
						alert("營業時間修改成功");
					}else{
						alert("營業時間修改失敗");
					}
				});
			})
			$('#endTime').bind('change', function(){
				var startTime = $('#startTime').val()+":00";
				var endTime = $('#endTime').val()+":00";
 				$.post('<s:url namespace="owner" action="businessTime!updateBusinessTime?beginBusinessTime='
 						+startTime+'&shopID='+shopID+'&endBusinessTime='+endTime+'"/>', function(data){
					if(data == "true"){
						alert("營業時間修改成功");
					}else{
						alert("營業時間修改失敗");
					}
				});
			})
			//新增休假日事件
			$('#today').bind('change', function(){
				var days = $(this).val();
				$.post('<s:url namespace="owner" action="businessTime!updateDayoff?daysoff='+
						days+'&shopID='+shopID+'"/>', function(data){
					if(data == "true"){
						alert("休假日增加成功");
						$.getJSON("<s:url namespace='owner' action='businessTime!queryShopBusinessTime?shopID="+shopID+"' />", function(data){
							var daysoffBeans = data[0].daysoffBeans;
							daysoffs = [];
							for (var i in daysoffBeans){
// 								console.log("這是重新整理獲得")
								daysoffs.push(daysoffBeans[i].daysoff.substring(0, 10));
							}
							queryDayoffs(daysoffs);
						});
					}else{
						alert("休假日增加失敗");
					}
				});
			})
	})
	//動態產生查詢休假日
	function queryDayoffs(daysoffs){
		var b = document.getElementById("dayofflist").hasChildNodes();
// 		console.log(b);
		if(b){
			$('#dayofflist').empty();
		}
		for(var i in daysoffs){
			$('#dayofflist').append('<tr><td><input type="checkbox" name="removeday" value='+daysoffs[i]+' /></td><td>'+daysoffs[i]+'</td></tr>')
		}
	}
	
	//
</script>
<style>
.floatleft{float:left;margin-right:20px;}
.aligncenter{text-align:center;}
.seperate{margin:25px 0px}
#submitusage {margin-left:200px;}
#submitusage1 {margin-left:215px;}
#businessMemo {padding-left:3px;}
form {width:310px;}
table {margin-left:80px;}
</style>
<body>
	<div >
		<div id="businessTime">
			<div class="floatleft"><span>開始營業時間:</span></div>
			<div>
				<input id="startTime" class="startTime1 aligncenter" type="text" data-field="time" data-startend="start"  data-startendelem=".endTime1" readonly>
			</div>
			<div class="seperate"></div>
			<div class="floatleft"><span>結束營業時間:</span></div>
			<div>
				<input id="endTime" class="endTime1 aligncenter" type="text" data-field="time"  data-startend="end" data-startendelem=".startTime1" readonly>
			</div>
			<div class="seperate"></div>
		</div>
		<div>
			<div class="floatleft"><span>新增休假日期:</span></div><div ><input id="today" type="text" data-field="date" class="aligncenter"  readonly></div>
			<div class="seperate"></div>
			<div id="dayoff">
				<form id="removedaysoffform" action="<c:url value="/owner/businessTime!removeDaysoff" />" method="post" name="dayoffsform"><fieldset><legend><h1>休假日</h1></legend><table><thead><tr><td>勾選</td><td>休假日</td></tr></thead>
					<tbody id="dayofflist"></tbody></table><input type="submit" id="submitusage" value="移除" /></fieldset></form>
			</div>
			<div class="seperate"></div>
			<div id="businessMemo">
				<form id="updateMemo" action="<c:url value="/owner/businessTime!updateBusinessTime" />" method="post">
					<span>店家營業備註:</span>
					<div class="seperate"></div>
					<textarea name="businessTimeNote" rows="4" cols="40"></textarea>
					<div class="seperate"></div>
					<button id="submitusage1" type="submit">修改</button>
				</form>
			</div>
		</div>
		<div id="dtBox"></div>
	</div>
</body>
</html>