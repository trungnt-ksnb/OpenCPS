package org.opencps.post.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;


public class JavaNetURL {

	private static final String USER_AGENT = "Mozilla/5.0";

	public static final String USERNAME = "User10";
	public static final String PASSWORD = "cd@2016";

	public static final String TARGET_DOMAIN = "https://hcconline.vnpost.vn/demo/";
	public static final String API = "serviceApi/v1/postDieuTins?";
	public static final String TOKEN = "token=c45b5eae-23a1-4da2-af66-db834db0e65b";
	
	


//	public static void main(String[] args){
//		
//		HttpURLConnection con = null;
//
//		try {
//			
//			String test = "";
//			// JSONObject dieuTins = JSONFactoryUtil.createJSONObject();
//			List<VNPOST> dataList = new ArrayList<VNPOST>();
//			VNPOST data = new VNPOST();
//
//			//data.setPostIdThuGom(0);
//			data.setMaTinhGui(0);
//			data.setMaHuyenGui(0);
//			data.setSoDonHang("MPDS-375718352-1365");
//			data.setMaKhachHang("106");
//			data.setDiaChiNguoiGui("Số 14 ngách 460/55, Thụy Khuê, phường Bưởi, quận Tây Hồ  Hà Nội  Hà Nội");
//			data.setTenNguoiGui("Lazada Hà nội");
//			data.setEmailNguoiGui(StringPool.BLANK);
//			data.setDienThoaiNguoiGui("0906191111");
//			data.setNoiDungHang("TIỀN COD: 57000    Hanoi Post  DS   Cap sau  Nắp body for Nikon (Đen) (1)  Hanoi Post  DS");
//			data.setSoTienCOD(57000.00);
//			data.setGhiChu("ghi chu");
//			data.setPosIdNhanTin(0);
//			data.setTenNguoiNhan("Trần Tuấn Tài");
//			// data.setNgayNhap(ngayNhap);
//
//			data.setDiaChiNguoiNhan("H2 Lô A PMH, Nguyễn Văn Linh Quận 7Phường Tân Phong  TP HCMQuận 7Phường Tân PhongHồ Chí Minh");
//			data.setDienThoaiNguoiNhan("0908207820");
//			data.setMaBuuGui(StringPool.BLANK);
//			data.setTrongLuong(0.0);
//			data.setCuocChinh(0.0);
//			data.setCuocCOD(0.0);
//			data.setDonHangNoiHuyen(true);
//			data.setChieuDai(0.0);
//			data.setChieuRong(0.0);
//			data.setMaTinhNhan(0);
//			data.setMaHuyenNhan(0);
//			data.setEmailNguoiNhan(StringPool.BLANK);
//			data.setExtendData(StringPool.BLANK);
//			data.setFlagConfig(1);
//
////			JSONSerializer jsonSerializer = JSONFactoryUtil
////					.createJSONSerializer();
//			//String json = jsonSerializer.serialize(data);
//			String json = "{\"qty\":100,\"name\":\"iPad 4\"}";
//
//			String urlString = TARGET_DOMAIN + API + TOKEN ;
////
////			String userPass = USERNAME + ":" + PASSWORD;
////			String basicAuth = "Basic "
////					+ new String(new Base64().encode(userPass.getBytes()));
//
//			URL url = new URL(urlString);
//			con = (HttpURLConnection) url.openConnection();
//			
//
//			
//			con.setRequestMethod("POST");
//			con.setRequestProperty("User-Agent", USER_AGENT);
//			con.setRequestProperty("Content-Type", "application/json");
//		    con.setRequestProperty("Charset", "UTF-8");
//		    con.setRequestProperty("Content-Length", "500");
//		    
//		    con.setDoOutput(true);
//		    con.setFixedLengthStreamingMode(json.getBytes("UTF-8").length);
//
//		 
//		    con.connect();
//		    
//		    
//		   
//			
//			
//			//System.out.println("con.getContentLength():"+con.getContentLength());
//			
//			OutputStream os = con.getOutputStream();
//			os.write(json.getBytes("UTF-8"));
//			os.flush();
//			os.close();
//			
//
//			int responseCode = con.getResponseCode();
//			System.out.println("Sending get request : " + url);
//			System.out.println("responseCode : " + responseCode);
//
//
//			
//
//			// Reading response from input Stream
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			
//			
//
//			String output;
//
//			StringBuffer response = new StringBuffer();
//
//			while ((output = in.readLine()) != null) {
//				response.append(output);
//			}
//
//			in.close();
//			
//
//			// printing result from response
//			System.out.println("===response.toString():" + response.toString());
//
//			JSONObject fileJSON = JSONFactoryUtil.createJSONObject(response
//					.toString());
//
//			System.out.println("===fileJSON:" + fileJSON);
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}finally{
//			con.disconnect();
//		}
//
//	}
}