package org.opencps.post.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.opencps.post.vnpost.model.VNPOST;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.util.StringPool;

@SuppressWarnings("deprecation")
public class ApacheURL {

	private static final String USER_AGENT = "Mozilla/5.0";

	public static final String USERNAME = "User10";
	public static final String PASSWORD = "cd@2016";

	public static final String TARGET_DOMAIN = "https://hcconline.vnpost.vn/demo/";
	public static final String API = "serviceApi/v1/postDieuTin?";
	public static final String TOKEN = "token=c45b5eae-23a1-4da2-af66-db834db0e65b";

	public static void callTest() {
		main(null);
	};

	public static void main(String[] args) {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {

			JSONObject dieuTins = JSONFactoryUtil.createJSONObject();
			List<VNPOST> dataList = new ArrayList<VNPOST>();
			VNPOST data = new VNPOST();

			data.setPosIdThuGom(0);
			data.setMaTinhGui(0);
			data.setMaHuyenGui(0);
			data.setSoDonHang("MPDS-375718352-1368");
			data.setMaKhachHang("fds_123");
			data.setDiaChiNguoiGui(StringPool.BLANK);
			data.setTenNguoiGui(StringPool.BLANK);
			data.setEmailNguoiGui(StringPool.BLANK);
			data.setDienThoaiNguoiGui(StringPool.BLANK);
			data.setNoiDungHang(StringPool.BLANK);
			data.setSoTienCOD(0.0);
			data.setGhiChu(StringPool.BLANK);
			data.setPosIdNhanTin(16);
			data.setTenNguoiNhan(StringPool.BLANK);
			data.setNgayNhap(null);

			data.setDiaChiNguoiNhan(StringPool.BLANK);
			data.setDienThoaiNguoiNhan(StringPool.BLANK);
			data.setMaBuuGui(StringPool.BLANK);
			data.setTrongLuong(0.0);
			data.setCuocChinh(0.0);
			data.setCuocCOD(0.0);
			data.setDonHangNoiHuyen(true);
			data.setChieuDai(0.0);
			data.setChieuRong(0.0);
			data.setMaTinhNhan(0);
			data.setMaHuyenNhan(0);
			data.setEmailNguoiNhan(StringPool.BLANK);
			data.setExtendData(StringPool.BLANK);
			data.setFlagConfig(1);

			JSONSerializer jsonSerializer = JSONFactoryUtil
					.createJSONSerializer();

			StringEntity json = new StringEntity(jsonSerializer.serialize(data));

			String urlString = TARGET_DOMAIN + API + TOKEN;

			System.out.println("json.toString():" + json.toString());

			HttpPost url = new HttpPost(urlString);
			json.setContentType("application/json");
			url.setEntity(json);

			System.out.println("url.getAllHeaders():" + url.getAllHeaders());
			System.out.println("url.getMethod():" + url.getMethod());
			System.out.println("url.getEntity():" + url.getEntity());
			System.out.println("url.getParams():" + url.getParams());
			System.out.println("url.getProtocolVersion():"
					+ url.getProtocolVersion());
			System.out.println("url.getRequestLine():" + url.getRequestLine());
			System.out.println("url.getURI():" + url.getURI());
			System.out.println("url.getConfig():" + url.getConfig());

			System.out.println("EntityUtils.toString(url.getEntity()):"
					+ EntityUtils.toString(url.getEntity()));

			HttpResponse httpResponse = httpClient.execute(url);

			int responseCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("Sending get request : " + url);
			System.out.println("responseCode : " + responseCode);

			// Reading response from input Stream
			BufferedReader in = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent()));

			String output;

			StringBuffer response = new StringBuffer();

			while ((output = in.readLine()) != null) {
				response.append(output);
			}

			in.close();

			// printing result from response
			System.out.println("===response.toString():" + response.toString());

			JSONObject fileJSON = JSONFactoryUtil.createJSONObject(response
					.toString());

			System.out.println("===fileJSON:" + fileJSON);

			// fileURL = getURLDownloadFile(groupId, uuid);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

	}
}