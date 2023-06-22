<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String data = (String)request.getAttribute("data");
%>
<html>
<head>
<meta charset="EUC-KR">
<title>Model</title>
</head>
<body>
	<h1>Model</h1>
	<h2>${data}</h2>
	
</body>
</html>