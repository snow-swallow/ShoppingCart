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

public class Shop {
	public String id;
	public String ownerId;
	public String name;
	public String logo;
	public String description;
	public boolean isOpen;

	public static List<Shop> fetchShops() {
		List<Shop> shopList = new ArrayList<Shop>();
		String URL = "http://localhost:9000/CartDB/showShops";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("GET").setUrl(URL);
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			String body = response.getResponseBody("UTF-8");
			if (response.getStatusCode() == 200) {
				if (!body.trim().equals("-1")) {
					shopList = new Gson().fromJson(body,
							new TypeToken<ArrayList<Shop>>() {
							}.getType());
				}
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
		return shopList;
	}

	public static Shop findById(String shopId) {
		Shop shop = null;
		String URL = "http://localhost:9000/CartDB/shopInfo";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("GET").setUrl(URL);
		rb.addQueryParameter("shopId", shopId);
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			String body = response.getResponseBody("UTF-8");
			if (response.getStatusCode() == 200) {
				if (!body.trim().equals("-1")) {
					shop = new Gson().fromJson(body, Shop.class);
				} else {
					System.out.println("body is -1");
				}
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
		return shop;
	}

	public static Shop findBySellerId(String sellerId) {
		Shop shop = null;
		String URL = "http://localhost:9000/CartDB/queryShopBySeller";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("GET").setUrl(URL);
		rb.addQueryParameter("sellerId", sellerId);
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			String body = response.getResponseBody("UTF-8");
			if (response.getStatusCode() == 200) {
				if (!body.trim().equals("-1")) {
					shop = new Gson().fromJson(body, Shop.class);
				} else {
					System.out.println("body is -1");
				}
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
		return shop;
	}

	public static boolean updateShop(Shop newShop) {
		String URL = "http://localhost:9000/CartDB/updateShop";

		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder("POST").setUrl(URL);
		rb.addQueryParameter("newShop", new Gson().toJson(newShop));
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			if (response.getStatusCode() == 200) {
				return true;
			} else {
				System.out.println("Shop update failed, statusCode="
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
