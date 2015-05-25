<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<!-- 自動跳轉 -->
<form name=loading>
<p align=center>
<font color="#0066ff" size="2">請稍等</font>
<font color="#0066ff" size="2" face="Arial">...</font>
<script>
window.location = "../index.jsp";
</script>
</p>
</form>
<p align="center"> 如果無法跳轉,
<a style="text-decoration: none" href="../index.jsp"><font color="red">請點這裡</font></a>.
</p>
</body>
</html>