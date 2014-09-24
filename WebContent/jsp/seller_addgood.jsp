<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add good</title>
</head>
<body>
	<div style="float:left;width:50%;">
		<p>Hello, <%=session.getAttribute("username")%>! Here you can add your good info.</p>
		<form method="POST" action="../addShopGood">
			<table>
				<tr>
					<td>Good Name</td>
					<td><input name="name" type="text"/></td>
				</tr>
				<tr>
					<td>Good Logo</td>
					<td><input name="logo" type="text"/></td>
				</tr>
				<tr>
					<td>Good Price</td>
					<td><input name="price" type="text"/></td>
				</tr>
				<tr>
					<td>Left amount</td>
					<td><input name="left" type="text"/></td>
				</tr>
				<tr>
					<td>Sold amount</td>
					<td><input name="sold" type="text"/></td>
				</tr>
				<tr>
					<input name="shopId" type="hidden" value="<%=session.getAttribute("shopId") %>"/>
					<td colspan="2"><input type="submit" value="Save"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>