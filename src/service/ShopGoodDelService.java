package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Good;

public class ShopGoodDelService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String goodId = req.getParameter("goodId");
		resp.sendRedirect("jsp/seller_delgood.jsp?goodId=" + goodId);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String goodId = req.getParameter("goodId");
		boolean result = Good.removeGood(goodId);
		resp.sendRedirect("jsp/" + (result ? "success" : "fail") + ".jsp");
	}
}
