<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Shops</title>
</head>
<body>
	<div style="float:left;width:50%;">
		<p>Hello, <%=session.getAttribute("username")%>!</p>
		<p><a href="../shopSetting?shopId=<%=session.getAttribute("shopId") %>">Shop Settings</a></p>
		<p><a href="../shopGood?shopId=<%=session.getAttribute("shopId") %>">Good Management</a></p>
	</div>
</body>
</html>