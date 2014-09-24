<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add good</title>
</head>
<body>
	<div style="float:left;width:50%;">
		<p>Hello, <%=session.getAttribute("username")%>!</p>
		<form method="POST" action="../delShopGood">
			<input name="goodId" value="<%=request.getParameter("goodId") %>" type="hidden"/>
			<input name="shopId" value="<%=session.getAttribute("shopId") %>" type="hidden"/>
			Are you sure to delete this good?
			<input type="submit" value="Yes"/>
			<a href="#">No</a>
		</form>
	</div>
</body>
</html>