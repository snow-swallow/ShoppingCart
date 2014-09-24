package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;

public class HttpUtil {

	public static String get(String method, String URL,
			Map<String, String> params, String postData, String contentType) {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestBuilder rb = new RequestBuilder(method).setUrl(URL);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			rb.addQueryParameter(entry.getKey(), entry.getValue());
		}
		if (StringUtils.isNotBlank(contentType)) {
			rb.setHeader("Content-Type", contentType);
		}
		if (StringUtils.isNotBlank(postData)) {
			rb.setBody(postData);
		}
		rb.setBodyEncoding("UTF-8");
		Request request = rb.build();
		try {
			Response response = asyncHttpClient.executeRequest(request).get();
			String body = response.getResponseBody("UTF-8");
			if (response.getStatusCode() == 200) {
				return body;
			} else {
				return "";
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

	public static String reqRemote(String method, String url) {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Request req = new RequestBuilder(method).setUrl(url).build();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Response res = asyncHttpClient.executeRequest(req).get();
			result.put("status", res.getStatusCode());
			result.put("body", res.getResponseBody());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			asyncHttpClient.close();
		}
		return new Gson().toJson(result);
	}

}
