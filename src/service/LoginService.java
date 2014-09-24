package service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Person;
import bean.Result;
import bean.Shop;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;

public class LoginService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		Result result = null;
		String URL = "http://localhost:9000/CartDB/login";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("GET").setUrl(URL);
		rb.addQueryParameter("username", req.getParameter("username"));
		rb.addQueryParameter("password", req.getParameter("password"));
		rb.setHeader("Content-Type", "application/json");
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			String body = response.getResponseBody("UTF-8");
			if (response.getStatusCode() == 200) {
				result = new Gson().fromJson(body, Result.class);
			} else {
				System.out.println("Login request failed, statusCode="
						+ response.getStatusCode());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			asyncHttpClient.close();
		}

		if (null != result && result.succ) {
			Person person = new Gson().fromJson(result.body, Person.class);
			HttpSession session = req.getSession();
			session.setAttribute("username", person.username);
			session.setAttribute("personId", person.id);
			res.addCookie(new Cookie("username", person.username));
			res.addCookie(new Cookie("isLogin", "true"));
			res.addCookie(new Cookie("type", person.type));

			if (person.type.equalsIgnoreCase("seller")) {
				Shop shop = Shop.findBySellerId(person.id);
				System.out.println("person.id=" + person.id);
				System.out.println("shop.id=" + shop.id);
				session.setAttribute("shopId", shop.id);
				res.sendRedirect("jsp/seller_index.jsp");
			} else {
				req.setAttribute("shopList", Shop.fetchShops());
				req.getRequestDispatcher("buyer_index").forward(req, res);
			}
		} else {
			req.setAttribute("error", "true");
			res.sendRedirect("jsp/login.jsp");
		}

	}

}
