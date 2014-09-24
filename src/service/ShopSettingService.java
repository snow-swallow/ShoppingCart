package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Shop;

public class ShopSettingService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		String shopId = req.getParameter("shopId");
		Shop shop = Shop.findById(shopId);

		req.setAttribute("shop", shop);
		req.getRequestDispatcher("jsp/seller_shopsettings.jsp").forward(req,
				res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String shopId = req.getParameter("shopId");
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String logo = req.getParameter("logo");
		boolean isOpen = new Boolean(req.getParameter("isOpen").equals("on"));

		Shop newShop = new Shop();
		newShop.id = shopId;
		newShop.name = name;
		newShop.description = description;
		newShop.logo = logo;
		newShop.isOpen = isOpen;

		boolean result = Shop.updateShop(newShop);
		res.sendRedirect("jsp/" + (result ? "success" : "fail") + ".jsp");
	}

}
