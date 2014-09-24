package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Good;

public class ShopGoodAddService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String shopId = req.getParameter("shopId");
		String name = req.getParameter("name");
		String logo = req.getParameter("logo");
		int price = Integer.parseInt(req.getParameter("price"));
		int left = Integer.parseInt(req.getParameter("left"));
		int sold = Integer.parseInt(req.getParameter("sold"));

		Good newGood = new Good();
		newGood.name = name;
		newGood.logo = logo;
		newGood.price = price;
		newGood.left = left;
		newGood.sold = sold;
		newGood.shopId = shopId;

		boolean result = Good.createGood(newGood);

		resp.sendRedirect("jsp/" + (result ? "success" : "fail") + ".jsp");

	}

}
