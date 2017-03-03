/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

/**
 * @author nhanhlt
 * */
package org.opencps.postal.utils;

import org.opencps.postal.model.VnPostal;
import org.opencps.postal.model.impl.VnPostalImpl;
import org.opencps.postal.service.PostalOrderLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;

public class PostalUtils {

	private static Log _log = LogFactoryUtil.getLog(PostalUtils.class);

	public void updatePostalOrder(long dossierId, ServiceContext serviceContext) {

		try {

			int PostIdThuGom = 0;
			int maTinhGui = 0;
			int maHuyenGui = 0;
			String maKhachHang = StringPool.BLANK;
			String soDonHang = StringPool.BLANK;
			String diaChiNguoiGui = StringPool.BLANK;
			String tenNguoiGui = StringPool.BLANK;
			String emailNguoiGui = StringPool.BLANK;
			String dienThoaiNguoiGui = StringPool.BLANK;
			String noiDungHang = StringPool.BLANK;
			Double soTienCOD = 0.0;
			String ghiChu = StringPool.BLANK;
			String ngayNhap = StringPool.BLANK;
			int posIdNhanTin = 0;
			String tenNguoiNhan = StringPool.BLANK;
			String diaChiNguoiNhan = StringPool.BLANK;
			String dienThoaiNguoiNhan = StringPool.BLANK;
			String maBuuGui = StringPool.BLANK;
			int maTinhNhan = 0;
			int maHuyenNhan = 0;
			String emailNguoiNhan = StringPool.BLANK;

			String postalOrderContent = createJsonPostalOrderContent(
					PostIdThuGom, maTinhGui, maHuyenGui, maKhachHang,
					soDonHang, diaChiNguoiGui, tenNguoiGui, emailNguoiGui,
					dienThoaiNguoiGui, noiDungHang, soTienCOD, ghiChu,
					ngayNhap, posIdNhanTin, tenNguoiNhan, diaChiNguoiNhan,
					dienThoaiNguoiNhan, maBuuGui, maTinhNhan, maHuyenNhan,
					emailNguoiNhan);

			PostalOrderLocalServiceUtil.updatePosOrder(0, PostalKeys.NEW,
					postalOrderContent, serviceContext);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	/**
	 * @param PostIdThuGom
	 * @param maTinhGui
	 * @param maHuyenGui
	 * @param maKhachHang
	 * @param soDonHang
	 * @param diaChiNguoiGui
	 * @param tenNguoiGui
	 * @param emailNguoiGui
	 * @param dienThoaiNguoiGui
	 * @param noiDungHang
	 * @param soTienCOD
	 * @param ghiChu
	 * @param ngayNhap
	 * @param posIdNhanTin
	 * @param tenNguoiNhan
	 * @param diaChiNguoiNhan
	 * @param dienThoaiNguoiNhan
	 * @param maBuuGui
	 * @param maTinhNhan
	 * @param maHuyenNhan
	 * @param emailNguoiNhan
	 * @return jsonData
	 */
	public String createJsonPostalOrderContent(int PostIdThuGom, int maTinhGui,
			int maHuyenGui, String maKhachHang, String soDonHang,
			String diaChiNguoiGui, String tenNguoiGui, String emailNguoiGui,
			String dienThoaiNguoiGui, String noiDungHang, Double soTienCOD,
			String ghiChu, String ngayNhap, int posIdNhanTin,
			String tenNguoiNhan, String diaChiNguoiNhan,
			String dienThoaiNguoiNhan, String maBuuGui, int maTinhNhan,
			int maHuyenNhan, String emailNguoiNhan) {

		VnPostal vnPost = new VnPostalImpl();

		vnPost.setPosIdThuGom(PostIdThuGom);
		// vnPost.setMaTinhGui(maTinhGui);
		// vnPost.setMaHuyenGui(maHuyenGui);
		vnPost.setSoDonHang(soDonHang);
		vnPost.setMaKhachHang(maKhachHang);
		vnPost.setDiaChiNguoiGui(diaChiNguoiGui);
		vnPost.setTenNguoiGui(tenNguoiGui);
		vnPost.setEmailNguoiGui(emailNguoiGui);
		vnPost.setDienThoaiNguoiGui(dienThoaiNguoiGui);
		vnPost.setNoiDungHang(noiDungHang);
		vnPost.setSoTienCOD(soTienCOD);
		vnPost.setGhiChu(ghiChu);
		vnPost.setNgayNhap(ngayNhap);

		vnPost.setPosIdNhanTin(posIdNhanTin);
		vnPost.setTenNguoiNhan(tenNguoiNhan);
		vnPost.setDiaChiNguoiNhan(diaChiNguoiNhan);
		vnPost.setDienThoaiNguoiNhan(dienThoaiNguoiNhan);
		vnPost.setMaBuuGui(maBuuGui);
		// vnPost.setTrongLuong(trongLuong);
		// vnPost.setCuocChinh(cuocChinh);
		// vnPost.setCuocCOD(cuocCOD);
		vnPost.setDonHangNoiHuyen(false);
		// vnPost.setChieuRong(chieuRong);
		// vnPost.setChieuDai(chieuDai);
		vnPost.setMaTinhNhan(maTinhNhan);
		// vnPost.setMaHuyenNhan(maHuyenNhan);
		vnPost.setEmailNguoiNhan(emailNguoiNhan);

		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

		String jsonData = jsonSerializer.serialize(vnPost);

		return jsonData;

	}

	/**
	 * @param jsonObject
	 * @return VnPostal
	 */
	public VnPostal convertJsonToVnPostal(JSONObject jsonObject) {

		VnPostal vnPost = new VnPostalImpl();

		vnPost.setPosIdThuGom(jsonObject.getInt("posIdThuGom"));
		// vnPost.setMaTinhGui(maTinhGui);
		// vnPost.setMaHuyenGui(maHuyenGui);
		vnPost.setSoDonHang(jsonObject.getString("soDonHang"));
		vnPost.setMaKhachHang(jsonObject.getString("maKhachHang"));
		vnPost.setDiaChiNguoiGui(jsonObject.getString("diaChiNguoiGui"));
		vnPost.setTenNguoiGui(jsonObject.getString("tenNguoiGui"));
		vnPost.setEmailNguoiGui(jsonObject.getString("emailNguoiGui"));
		vnPost.setDienThoaiNguoiGui(jsonObject.getString("dienThoaiNguoiGui"));
		vnPost.setNoiDungHang(jsonObject.getString("noiDungHang"));
		vnPost.setSoTienCOD(jsonObject.getDouble("soTienCOD"));
		vnPost.setGhiChu(jsonObject.getString("ghiChu"));
		vnPost.setNgayNhap(jsonObject.getString("ngayNhap"));

		vnPost.setPosIdNhanTin(jsonObject.getInt("posIdNhanTin"));
		vnPost.setTenNguoiNhan(jsonObject.getString("tenNguoiNHan"));
		vnPost.setDiaChiNguoiNhan(jsonObject.getString("diaChiNguoiNhan"));
		vnPost.setDienThoaiNguoiNhan(jsonObject.getString("dienThoaiNguoiNhan"));
		vnPost.setMaBuuGui(jsonObject.getString("maBuuGui"));
		vnPost.setDonHangNoiHuyen(false);
		vnPost.setMaTinhNhan(jsonObject.getInt("maTinhNhan"));
		// vnPost.setMaHuyenNhan(maHuyenNhan);
		vnPost.setEmailNguoiNhan(jsonObject.getString("emailNguoiNhan"));
		// vnPost.setExtendData(StringPool.BLANK);
		// vnPost.setFlagConfig(0);

		return vnPost;
	}

}