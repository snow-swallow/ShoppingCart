package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartItem;
import bean.Good;

public class BuyerGoodService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		String shopId = req.getParameter("shopId");
		HttpSession session = req.getSession();
		String ownerId = session.getAttribute("personId").toString();

		req.setAttribute("goodList", Good.fetchGoods(shopId));
		req.setAttribute("cartItemList", CartItem.fetchByOwner(ownerId));
		req.getRequestDispatcher("jsp/buyer_goods.jsp").forward(req, res);
	}

}
