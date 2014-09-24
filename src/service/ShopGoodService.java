package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Good;

public class ShopGoodService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String shopId = req.getParameter("shopId");
		req.setAttribute("goodList", Good.fetchGoods(shopId));
		req.getRequestDispatcher("jsp/seller_goodmanage.jsp")
				.forward(req, resp);
	}

}
