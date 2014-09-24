<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<form action="login" method="POST" style="width:200px;margin:20px auto;">
		<div>
			<span>username</span>
			<input name="username" type="text" value="xyz"/>
		</div>
		<div style="margin-top:10px">
			<span>password</span>
			<input name="password" type="password" value="222222"/>
		</div>
		<div style="margin-top:10px">
			<input style="margin-right:10px" type="submit" value="login"/>
			<input type="reset"/>
		</div>
		<div>
			<%
			if(null != request && null != request.getAttribute("error") && StringUtils.isNotBlank(request.getAttribute("error").toString())){
			%>
			Sorry, your username or password is incorrect.	
			<%
			}
			%>
		</div>
	</form>
</body>
</html>