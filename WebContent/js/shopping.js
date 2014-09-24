var goods = document.getElementsByClassName('jsForAddItem');
for(var i = 0; i < goods.length; i ++){
	goods[i].addEventListener('click', function(){
		add2Cart(this);
	});
}

var rmBtns = document.getElementsByClassName('jsForItemRM');
for(var i = 0; i <rmBtns.length; i ++){
	rmBtns[i].addEventListener('click', function(){
		rmFromCart(this);
	});
}

var add2Cart = function(ele){
	var s = ele.getAttribute('data'), datas = s.split('_');
	var g = {
				id: datas[0],
				name: datas[1],
				price: datas[2],
				shopId: datas[3]
			};
			
	ajaxFun('POST', 'add2Cart', true, "good="+JSON.stringify(g), function(xmlhttp){
		var json = xmlhttp.responseText;
		if(json === 'false'){
			alert('Failed in adding cart item.');
			return;
		}
		var cartItem = JSON.parse(json);
		var good = cartItem.good;
		var myCartTbl = document.getElementById('myCartTbl');
		var tr = document.createElement('tr');
		var td = document.createElement('td');
		td.innerHTML = good.name;
		tr.appendChild(td);
		
		td = document.createElement('td');
		td.innerHTML = good.price/100.0;
		tr.appendChild(td);
				
		td = document.createElement('td');
		var a = document.createElement('a');
		a.href = 'javascript:;';
		a.className = 'jsForItemRM';
		a.setAttribute('data', cartItem.id);
		a.innerHTML = 'delete '+cartItem.id;
		td.appendChild(a);
		tr.appendChild(td);
		a.addEventListener('click', function(){
			rmFromCart(this);
		});
		
		myCartTbl.appendChild(tr);
		
		var sum = parseFloat(document.getElementById('totalCost').innerHTML);
		sum += parseFloat(good.price)/100.0;
		document.getElementById('totalCost').innerHTML = sum.toFixed(2);
	});
	
};

var rmFromCart = function(ele){
	var itemId = ele.getAttribute('data');
	
	ajaxFun('POST', 'delFromCart', true, "cartItemId="+itemId, function(xmlhttp){
		var json = xmlhttp.responseText;
		if(json === 'false'){
			alert('failed in deleting');
		}else{
			var e = document.getElementById('item_'+itemId);
			e.remove();
		}
	});
};

var ajaxFun = function(method, url, async, datas, callback){
	var xmlhttp;
	if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}else{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	//xmlhttp.open("POST","add2Cart",true);
	xmlhttp.open(method, url, async);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
			if(typeof callback === 'function'){
				callback(xmlhttp);
			}
		}
	};
	xmlhttp.send(datas);
};