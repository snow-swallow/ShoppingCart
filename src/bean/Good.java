package bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;

public class Good {

	public String id;
	public String shopId;
	public String name;
	public String logo;
	public int price;// cent
	public int sold;
	public int left;

	public static List<Good> fetchGoods(String shopId) {
		String URL = "http://localhost:9000/CartDB/showGoods";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("GET").setUrl(URL);
		rb.addQueryParameter("shopId", shopId);
		rb.setHeader("Content-Type", "application/json");
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			String body = response.getResponseBody("UTF-8");
			System.out.println(body);
			if (response.getStatusCode() == 200) {
				List<Good> goodList;
				if ("-1".equals(body)) {
					goodList = new ArrayList<Good>();
				} else {
					goodList = new Gson().fromJson(body,
							new TypeToken<ArrayList<Good>>() {
							}.getType());
				}
				return goodList;
			} else {
				System.out.println("Good fetch request failed, statusCode="
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
		return null;
	}

	public static boolean removeGood(String goodId) {
		String URL = "http://localhost:9000/CartDB/removeGood";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("POST").setUrl(URL);
		rb.addQueryParameter("goodId", goodId);
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			if (response.getStatusCode() == 200) {
				return true;
			} else {
				System.out.println("Good removing failed, statusCode="
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
		return false;
	}

	public static boolean createGood(Good newGood) {
		String URL = "http://localhost:9000/CartDB/createGood";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("POST").setUrl(URL);
		rb.addQueryParameter("newGood", new Gson().toJson(newGood));
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			if (response.getStatusCode() == 200) {
				return true;
			} else {
				System.out.println("Good removing failed, statusCode="
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
		return false;
	}
}
