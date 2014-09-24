<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,bean.Good"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Goods</title>
</head>
<body>
	<%
		List<Good> goodList = (List<Good>)request.getAttribute("goodList");
	%>
	<div style="float:left;width:50%;">
		<p>Hello, <%=session.getAttribute("username")%>!</p> 
		<p>Good's total amount is <%=goodList.size()%></p>
		<table>
			<tr>
				<th></th>
				<th>Good Name</th>
				<th>Good price</th>
			</tr>
			<%
				for(Good good : goodList){
			%>
				<tr>
					<td><input class="jsForGoodItem" type="checkbox" value="<%=good.name+":"+good.price%>"/></td>
					<td><%=good.name%></td>
					<td><%=good.price/100.0%></td>
				</tr>
			<%
				}
			%>
		</table>
		<div style="margin-top:20px;"><button id="add2CartBtn">Add to Shopping Cart</button></div>
	</div>
	<div style="float: right; width: 40%; background-color: #eee; padding: 20px; color: #555;">
		<strong><span style="font-size: 20px;">My Shopping Cart</span></strong>
		<div style="max-height:500px; overflow-y:scroll;">
			<table id="myCartTbl" border="1" style="margin-top:10px;">
				<tr>
					<th>Good Name</th>
					<th>Good price</th>
				</tr>
				<%
					List<Good> myGoods = (List<Good>)session.getAttribute("myGoods");
						double sum = 0.0;
						if(null != myGoods && !myGoods.isEmpty()){
							for(int i = 0; i < myGoods.size(); i ++){
								sum += myGoods.get(i).price / 100.0;
				%>
						<tr>
							<td><%=myGoods.get(i).name %></td>
							<td><%=myGoods.get(i).price/100.0 %></td>
						</tr>
						<%
					}
				}else{
					%>
					You have no goods in your cart.
					<%
				}
				%>
			</table>
		</div>
		<div style="margin-top:10px">
		Total Cost: <span id="totalCost"><%=sum %></span><br/>
		<a href="myCart">Go to My Shopping Cart</a>
		</div>
	</div>
	<script type="text/javascript" src="js/shopping.js"></script>
</body>
</html>