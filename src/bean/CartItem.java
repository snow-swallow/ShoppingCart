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

public class CartItem {

	public String id;
	public String ownerId;
	public String goodId;
	public int amount;
	public Good good;

	public static List<CartItem> fetchByOwner(String ownerId) {
		List<CartItem> itemList = new ArrayList<CartItem>();
		String URL = "http://localhost:9000/CartDB/fetchCartItems";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("GET").setUrl(URL);
		rb.addQueryParameter("ownerId", ownerId);
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			String body = response.getResponseBody("UTF-8");
			if (response.getStatusCode() == 200) {
				if (!"-1".equals(body)) {
					itemList = new Gson().fromJson(body,
							new TypeToken<ArrayList<CartItem>>() {
							}.getType());
				}
			} else {
				System.out.println("CartItem fetch request failed, statusCode="
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
		return itemList;
	}

	public static String createCartItem(CartItem newItem) {
		String result = "-1";

		String URL = "http://localhost:9000/CartDB/createCartItem";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("POST").setUrl(URL);
		rb.addQueryParameter("newItem", new Gson().toJson(newItem));
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			if (response.getStatusCode() == 200) {
				result = response.getResponseBody();
			} else {
				System.out
						.println("Failed in creating cartitem request, statusCode="
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

		return result;
	}

	public static boolean remove(String cartItemId) {
		boolean result = false;

		String URL = "http://localhost:9000/CartDB/delCartItem";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("POST").setUrl(URL);
		rb.addQueryParameter("itemId", cartItemId);
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			if (response.getStatusCode() == 200) {
				result = true;
			} else {
				System.out
						.println("Failed in deleting cartitem request, statusCode="
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

		return result;
	}
}
