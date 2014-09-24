package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageFilter implements Filter {

	public ImageFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String contextPath = req.getContextPath() + "/img/";
		String requestURI = req.getRequestURI();
		String imageName = requestURI.substring(requestURI.indexOf(contextPath)
				+ contextPath.length());

		System.out.println("str1: " + imageName);
		System.out.println("getContextPath: " + req.getContextPath());
		System.out.println("getRequestURI: " + req.getRequestURI());
		System.out.println("getRequestURL: " + req.getRequestURL());
		System.out.println("getPathInfo: " + req.getPathInfo());

		if (imageName != null && !"".equals(imageName)) {
			ServletContext context2 = request.getServletContext().getContext(
					"/web2");

			String newPath = "/xxx.jpg";
			RequestDispatcher requestDispatcher;
			requestDispatcher = context2.getRequestDispatcher(newPath);
			requestDispatcher.forward(req, resp);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
