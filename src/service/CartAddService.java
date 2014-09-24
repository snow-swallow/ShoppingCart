package service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartItem;
import bean.Good;

import com.google.gson.Gson;

public class CartAddService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("-----------");
		HttpSession session = req.getSession();
		String ownerId = session.getAttribute("personId").toString();

		String json = req.getParameter("good");
		System.out.println("json=" + json);
		Good good = new Gson().fromJson(json, Good.class);

		CartItem newItem = new CartItem();
		newItem.goodId = good.id;
		newItem.ownerId = ownerId;

		String result = CartItem.createCartItem(newItem);
		System.out.println("add result=" + result);
		if (!result.equals("-1")) {
			CartItem item = new CartItem();
			item.id = result;
			item.goodId = good.id;
			item.good = good;
			item.amount = 1;
			item.ownerId = ownerId;

			result = new Gson().toJson(item);
		}

		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(result);
	}

}
