<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.List,java.text.DecimalFormat,bean.Good,bean.CartItem"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Goods List</title>
</head>
<body>
	<div style="float:left;width:50%;">
		<p>Hello, <%=session.getAttribute("username")%>! Here you can add goods to your cart.</p>
		<%
			List<Good> goodList = (List<Good>)request.getAttribute("goodList");
		%>
		<p>goodList's size is <%=goodList.size()%></p>
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
				<th></th>
			</tr>
			<%
				for(Good good : goodList){
			%>
				<tr>
					<td><a href="#"><%=good.name %></a></td>
					<td><%=good.logo %></td>
					<td><%=good.price/100.0 %></td>
					<td><%=good.sold %></td>
					<td><%=good.left %></td>
					<td><a href="javascript:;" class="jsForAddItem" data="<%=good.id+"_"+good.name+"_"+good.price+"_"+good.shopId%>">Add</a></td>
				</tr>
			<%
				}
			%>
		</table>
		<div style="margin-top:20px;"><button id="add2CartBtn">Add to Shopping Cart</button></div>
		<%} %>
	</div>
	<div style="float: right; width: 40%; background-color: #eee; padding: 20px; color: #555;">
		<strong><span style="font-size: 20px;">My Shopping Cart</span></strong>
		<div style="max-height:500px; overflow-y:scroll;">
			<table id="myCartTbl" border="1" style="margin-top:10px;">
				<tr>
					<th>Good Name</th>
					<th>Good price</th>
					<th></th>
				</tr>
				<%
				List<CartItem> myItems = (List<CartItem>)request.getAttribute("cartItemList");
					double sum = 0.0;
					if(null != myItems && !myItems.isEmpty()){
						for(int i = 0; i < myItems.size(); i ++){
							CartItem item = myItems.get(i);
							Good good = item.good;
							sum += good.price / 100.0;
				%>
						<tr id="item_<%=item.id %>">
							<td><%=good.name %></td>
							<td><%=good.price/100.0 %></td>
							<td><a href="javascript:;" class="jsForItemRM" data="<%=item.id %>">delete <%=myItems.get(i).id %></a></td>
						</tr>
					<%
						}
					}else{
					%>
							You have no cartitem now.
					<%
					}
					%>
			</table>
		</div>
		<div style="margin-top:10px">
		Total Cost: <span id="totalCost"><%=new DecimalFormat("0.00").format(sum) %></span><br/>
		</div>
	</div>
	
	<script type="text/javascript" src="js/shopping.js"></script>
</body>
</html>