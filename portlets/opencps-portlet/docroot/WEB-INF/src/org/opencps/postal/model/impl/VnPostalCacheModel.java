/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.opencps.postal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.opencps.postal.model.VnPostal;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VnPostal in entity cache.
 *
 * @author nhanhlt
 * @see VnPostal
 * @generated
 */
public class VnPostalCacheModel implements CacheModel<VnPostal>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{vnPostalId=");
		sb.append(vnPostalId);
		sb.append(", posIdThuGom=");
		sb.append(posIdThuGom);
		sb.append(", soDonHang=");
		sb.append(soDonHang);
		sb.append(", maKhachHang=");
		sb.append(maKhachHang);
		sb.append(", diaChiNguoiGui=");
		sb.append(diaChiNguoiGui);
		sb.append(", tenNguoiGui=");
		sb.append(tenNguoiGui);
		sb.append(", emailNguoiGui=");
		sb.append(emailNguoiGui);
		sb.append(", dienThoaiNguoiGui=");
		sb.append(dienThoaiNguoiGui);
		sb.append(", noiDungHang=");
		sb.append(noiDungHang);
		sb.append(", soTienCOD=");
		sb.append(soTienCOD);
		sb.append(", ghiChu=");
		sb.append(ghiChu);
		sb.append(", ngayNhap=");
		sb.append(ngayNhap);
		sb.append(", posIdNhanTin=");
		sb.append(posIdNhanTin);
		sb.append(", tenNguoiNhan=");
		sb.append(tenNguoiNhan);
		sb.append(", diaChiNguoiNhan=");
		sb.append(diaChiNguoiNhan);
		sb.append(", dienThoaiNguoiNhan=");
		sb.append(dienThoaiNguoiNhan);
		sb.append(", maBuuGui=");
		sb.append(maBuuGui);
		sb.append(", donHangNoiHuyen=");
		sb.append(donHangNoiHuyen);
		sb.append(", maTinhNhan=");
		sb.append(maTinhNhan);
		sb.append(", emailNguoiNhan=");
		sb.append(emailNguoiNhan);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public VnPostal toEntityModel() {
		VnPostalImpl vnPostalImpl = new VnPostalImpl();

		vnPostalImpl.setVnPostalId(vnPostalId);
		vnPostalImpl.setPosIdThuGom(posIdThuGom);

		if (soDonHang == null) {
			vnPostalImpl.setSoDonHang(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setSoDonHang(soDonHang);
		}

		if (maKhachHang == null) {
			vnPostalImpl.setMaKhachHang(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setMaKhachHang(maKhachHang);
		}

		if (diaChiNguoiGui == null) {
			vnPostalImpl.setDiaChiNguoiGui(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setDiaChiNguoiGui(diaChiNguoiGui);
		}

		if (tenNguoiGui == null) {
			vnPostalImpl.setTenNguoiGui(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setTenNguoiGui(tenNguoiGui);
		}

		if (emailNguoiGui == null) {
			vnPostalImpl.setEmailNguoiGui(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setEmailNguoiGui(emailNguoiGui);
		}

		if (dienThoaiNguoiGui == null) {
			vnPostalImpl.setDienThoaiNguoiGui(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setDienThoaiNguoiGui(dienThoaiNguoiGui);
		}

		if (noiDungHang == null) {
			vnPostalImpl.setNoiDungHang(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setNoiDungHang(noiDungHang);
		}

		vnPostalImpl.setSoTienCOD(soTienCOD);

		if (ghiChu == null) {
			vnPostalImpl.setGhiChu(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setGhiChu(ghiChu);
		}

		if (ngayNhap == null) {
			vnPostalImpl.setNgayNhap(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setNgayNhap(ngayNhap);
		}

		vnPostalImpl.setPosIdNhanTin(posIdNhanTin);

		if (tenNguoiNhan == null) {
			vnPostalImpl.setTenNguoiNhan(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setTenNguoiNhan(tenNguoiNhan);
		}

		if (diaChiNguoiNhan == null) {
			vnPostalImpl.setDiaChiNguoiNhan(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setDiaChiNguoiNhan(diaChiNguoiNhan);
		}

		if (dienThoaiNguoiNhan == null) {
			vnPostalImpl.setDienThoaiNguoiNhan(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setDienThoaiNguoiNhan(dienThoaiNguoiNhan);
		}

		if (maBuuGui == null) {
			vnPostalImpl.setMaBuuGui(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setMaBuuGui(maBuuGui);
		}

		vnPostalImpl.setDonHangNoiHuyen(donHangNoiHuyen);
		vnPostalImpl.setMaTinhNhan(maTinhNhan);

		if (emailNguoiNhan == null) {
			vnPostalImpl.setEmailNguoiNhan(StringPool.BLANK);
		}
		else {
			vnPostalImpl.setEmailNguoiNhan(emailNguoiNhan);
		}

		vnPostalImpl.resetOriginalValues();

		return vnPostalImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		vnPostalId = objectInput.readLong();
		posIdThuGom = objectInput.readInt();
		soDonHang = objectInput.readUTF();
		maKhachHang = objectInput.readUTF();
		diaChiNguoiGui = objectInput.readUTF();
		tenNguoiGui = objectInput.readUTF();
		emailNguoiGui = objectInput.readUTF();
		dienThoaiNguoiGui = objectInput.readUTF();
		noiDungHang = objectInput.readUTF();
		soTienCOD = objectInput.readDouble();
		ghiChu = objectInput.readUTF();
		ngayNhap = objectInput.readUTF();
		posIdNhanTin = objectInput.readInt();
		tenNguoiNhan = objectInput.readUTF();
		diaChiNguoiNhan = objectInput.readUTF();
		dienThoaiNguoiNhan = objectInput.readUTF();
		maBuuGui = objectInput.readUTF();
		donHangNoiHuyen = objectInput.readBoolean();
		maTinhNhan = objectInput.readInt();
		emailNguoiNhan = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(vnPostalId);
		objectOutput.writeInt(posIdThuGom);

		if (soDonHang == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(soDonHang);
		}

		if (maKhachHang == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(maKhachHang);
		}

		if (diaChiNguoiGui == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(diaChiNguoiGui);
		}

		if (tenNguoiGui == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tenNguoiGui);
		}

		if (emailNguoiGui == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(emailNguoiGui);
		}

		if (dienThoaiNguoiGui == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dienThoaiNguoiGui);
		}

		if (noiDungHang == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(noiDungHang);
		}

		objectOutput.writeDouble(soTienCOD);

		if (ghiChu == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ghiChu);
		}

		if (ngayNhap == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(ngayNhap);
		}

		objectOutput.writeInt(posIdNhanTin);

		if (tenNguoiNhan == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(tenNguoiNhan);
		}

		if (diaChiNguoiNhan == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(diaChiNguoiNhan);
		}

		if (dienThoaiNguoiNhan == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dienThoaiNguoiNhan);
		}

		if (maBuuGui == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(maBuuGui);
		}

		objectOutput.writeBoolean(donHangNoiHuyen);
		objectOutput.writeInt(maTinhNhan);

		if (emailNguoiNhan == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(emailNguoiNhan);
		}
	}

	public long vnPostalId;
	public int posIdThuGom;
	public String soDonHang;
	public String maKhachHang;
	public String diaChiNguoiGui;
	public String tenNguoiGui;
	public String emailNguoiGui;
	public String dienThoaiNguoiGui;
	public String noiDungHang;
	public double soTienCOD;
	public String ghiChu;
	public String ngayNhap;
	public int posIdNhanTin;
	public String tenNguoiNhan;
	public String diaChiNguoiNhan;
	public String dienThoaiNguoiNhan;
	public String maBuuGui;
	public boolean donHangNoiHuyen;
	public int maTinhNhan;
	public String emailNguoiNhan;
}