package org.opencps.post.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@SuppressWarnings("deprecation")
public class APIRequestUtils {

	private static Log _log = LogFactoryUtil.getLog(APIRequestUtils.class);


	public static final String USERNAME = "User10";
	public static final String PASSWORD = "cd@2016";

	public static final String TARGET_DOMAIN = "https://hcconline.vnpost.vn/demo/";
	public static final String API = "serviceApi/v1/postDieuTin?";
	public static final String TOKEN = "token=c45b5eae-23a1-4da2-af66-db834db0e65b";

	public JSONObject callAPI(String domainName, String APIPath, String token,
			String jsonData) {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {

			StringEntity json = new StringEntity(jsonData);

			StringBuffer urlString = new StringBuffer();
			urlString.append(domainName);
			urlString.append(APIPath);
			urlString.append(token);

			HttpPost url = new HttpPost(urlString.toString());
			json.setContentType("application/json");
			url.setEntity(json);

			HttpResponse httpResponse = httpClient.execute(url);

			int responseCode = httpResponse.getStatusLine().getStatusCode();

			// Reading response from input Stream
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent()));

			String output;

			StringBuffer response = new StringBuffer();

			while ((output = in.readLine()) != null) {
				response.append(output);
			}

			in.close();

			JSONObject fileJSON = JSONFactoryUtil.createJSONObject(response
					.toString());

			return fileJSON;

		} catch (Exception e) {
			_log.error(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return JSONFactoryUtil.createJSONObject();

	}
}