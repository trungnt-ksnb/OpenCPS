package org.opencps.post.utils;

import java.util.ArrayList;
import java.util.List;

import org.opencps.post.vnpost.model.VNPOST;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.util.StringPool;

public class PostGenerator {
	
	public void sendVNPOST(){
		
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
		
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		
		APIRequestUtils apiRequest = new APIRequestUtils();
		jsonObject = apiRequest.callAPI("", "", "", jsonSerializer.serialize(data));
		
		
	}
}