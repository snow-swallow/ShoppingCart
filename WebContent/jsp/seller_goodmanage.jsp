<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,bean.Good"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Good management</title>
</head>
<body>
	<div style="float:left;width:50%;">
		<p>Hello, <%=session.getAttribute("username")%>! Here you can manage goods in your shop.</p>
		<a href="jsp/seller_addgood.jsp">Add</a>
		
		<%
			List<Good> goodList = (List<Good>)request.getAttribute("goodList");
		%>
		<p>goodList size is <%=goodList.size()%></p>
		<%
		if(!goodList.isEmpty()){
		%>
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Logo</th>
				<th>Price</th>
				<th>Sold</th>
				<th>Left</th>
				<th>Operation</th>
			</tr>
			<%
				for(Good good : goodList){
			%>
				<tr>
					<td><%=good.name %></td>
					<td><%=good.logo %></td>
					<td><%=good.price/100 %></td>
					<td><%=good.sold %></td>
					<td><%=good.left %></td>
					<td><a href="delShopGood?goodId=<%=good.id %>">Delete</a></td>
				</tr>
			<%
				}
			%>
		</table>
		<%} %>
		
		
	</div>
</body>
</html>