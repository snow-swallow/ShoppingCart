<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.List,bean.Shop"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Shops</title>
</head>
<body>
	<%
		List<Shop> shopList = (List<Shop>)request.getAttribute("shopList");
	%>
	<div style="float:left;width:50%;">
		<p>Hello, <%=session.getAttribute("username")%>!</p>
		<p><a href="logout">Logout</a></p>
		<p>Shop's total amount is <%=shopList.size()%></p>
		<%
		if(!shopList.isEmpty()){
		%>
		<table border="1">
			<tr>
				<th>Shop name</th>
				<th>Shop logo</th>
				<th>Shop description</th>
			</tr>
			<%
				for(Shop shop : shopList){
			%>
				<tr>
					<td><a href="buyer_good?shopId=<%=shop.id %>"><%=shop.name %>22</a></td>
					<td><%=shop.logo %></td>
					<td><%=shop.description %></td>
				</tr>
			<%
				}
			%>
		</table>
		<%} %>
	</div>
</body>
</html>