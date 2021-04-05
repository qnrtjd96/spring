<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/js_css/style.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js_css/script.js"></script>
</head>
<body>
<div>
	<a href="/myapp/aLink?name=홍길동&age=25">클릭하면 다른컨트롤러로넘어가기11111</a>
	<a href="/myapp/aLink2?name=이순신&age=30">클릭하면 다른컨트롤러로넘어가기22222</a>
	<a href="/myapp/aLink3?username=세종대왕&age=50">클릭하면 다른컨트롤러로넘어가기33333</a>
	<a href="/myapp/aLink3?username=장영실&age=20">클릭하면 다른컨트롤러로넘어가기4444444</a>
	<a href="/myapp/formData">클릭하면 다른폼으로이동</a>
</div>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<!-- <img src="/myapp" 도 가능-->
<img src="<%=request.getContextPath()%>/img/001.png"/>



</body>
</html>
