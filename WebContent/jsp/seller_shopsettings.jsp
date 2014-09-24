<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="bean.Shop"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Shop settings</title>
</head>
<body>
	<div style="float:left;width:50%;">
		<p>Hello, <%=session.getAttribute("username")%>! Here you can set your shop info.</p>
		<%Shop shop = (Shop)request.getAttribute("shop"); %>
		<form method="post" action="shopSetting">
			<table>
				<tr>
					<td>Shop Name</td>
					<td><input name="name" type="text" value="<%=shop.name %>"/></td>
				</tr>
				<tr>
					<td>Shop Logo</td>
					<td><input name="logo" type="text" value="<%=shop.logo %>"/></td>
				</tr>
				<tr>
					<td>Shop Description</td>
					<td><textarea name="description" rows="3" cols="40"><%=shop.description %></textarea></td>
				</tr>
				<tr>
					<td>Is Open</td>
					<td><input name="isOpen" type="checkbox" <%=shop.isOpen ? "checked" : "" %>/></td>
				</tr>
				<tr>
					<input name="shopId" type="hidden" value="<%=shop.id %>" />
					<td colspan="2"><input type="submit" value="Save"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>