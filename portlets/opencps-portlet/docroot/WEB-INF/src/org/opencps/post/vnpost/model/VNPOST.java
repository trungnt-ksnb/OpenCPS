package org.opencps.post.vnpost.model;

import java.util.Date;

import com.liferay.portal.kernel.util.StringPool;


public class VNPOST {
	
	public VNPOST postDieuTins(int PostIdThuGom,int maTinhGui,int maHuyenGui,String maKhachHang,String soDonHang,String diaChiNguoiGui,
			String tenNguoiGui,String  emailNguoiGui,String dienThoaiNguoiGui,String noiDungHang,Double soTienCOD,
			String ghiChu,Date ngayNhap,int posIdNhanTin,String tenNguoiNhan,String diaChiNguoiNhan,String dienThoaiNguoiNhan,
			String maBuuGui,int maTinhNhan,int maHuyenNhan,String emailNguoiNhan){
		
		VNPOST vnPost = new VNPOST();
		
		vnPost.setPosIdThuGom(PostIdThuGom);
		vnPost.setMaTinhGui(maTinhGui);
		vnPost.setMaHuyenGui(maHuyenGui);
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
//		vnPost.setTrongLuong(trongLuong);
//		vnPost.setCuocChinh(cuocChinh);
//		vnPost.setCuocCOD(cuocCOD);
		vnPost.setDonHangNoiHuyen(false);
//		vnPost.setChieuRong(chieuRong);
//		vnPost.setChieuDai(chieuDai);
		vnPost.setMaTinhNhan(maTinhNhan);
		vnPost.setMaHuyenNhan(maHuyenNhan);
		vnPost.setEmailNguoiNhan(emailNguoiNhan);
		vnPost.setExtendData(StringPool.BLANK);
		vnPost.setFlagConfig(0);
		
		return null;
	};
	
	private int PosIdThuGom;
	private int MaTinhGui;
	private int MaHuyenGui;
	private String SoDonHang;
	private String MaKhachHang;
	private String DiaChiNguoiGui;
	private String TenNguoiGui;
	private String EmailNguoiGui;
	private String DienThoaiNguoiGui;
	private String NoiDungHang;
	private Double SoTienCOD;
	private String GhiChu;
	private Date NgayNhap;
	
	private int PosIdNhanTin;
	private String TenNguoiNhan;
	private String DiaChiNguoiNhan;
	private String DienThoaiNguoiNhan;
	private String MaBuuGui;
	private Double TrongLuong;
	private Double CuocChinh;
	private Double CuocCOD;
	private boolean DonHangNoiHuyen;
	private Double ChieuRong;
	private Double ChieuDai;
	private int MaTinhNhan;
	private int MaHuyenNhan;
	private String EmailNguoiNhan;
	private String ExtendData;
	private int FlagConfig;
	
	
	public int getPostIdThuGom() {
		return PosIdThuGom;
	}
	public void setPosIdThuGom(int postIdThuGom) {
		PosIdThuGom = postIdThuGom;
	}
	public int getMaTinhGui() {
		return MaTinhGui;
	}
	public void setMaTinhGui(int maTinhGui) {
		MaTinhGui = maTinhGui;
	}
	public int getMaHuyenGui() {
		return MaHuyenGui;
	}
	public void setMaHuyenGui(int maHuyenGui) {
		MaHuyenGui = maHuyenGui;
	}
	public String getSoDonHang() {
		return SoDonHang;
	}
	public void setSoDonHang(String soDonHang) {
		SoDonHang = soDonHang;
	}
	public String getMaKhachHang() {
		return MaKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		MaKhachHang = maKhachHang;
	}
	public String getDiaChiNguoiGui() {
		return DiaChiNguoiGui;
	}
	public void setDiaChiNguoiGui(String diaChiNguoiGui) {
		DiaChiNguoiGui = diaChiNguoiGui;
	}
	public String getTenNguoiGui() {
		return TenNguoiGui;
	}
	public void setTenNguoiGui(String tenNguoiGui) {
		TenNguoiGui = tenNguoiGui;
	}
	public String getEmailNguoiGui() {
		return EmailNguoiGui;
	}
	public void setEmailNguoiGui(String emailNguoiGui) {
		EmailNguoiGui = emailNguoiGui;
	}
	public String getDienThoaiNguoiGui() {
		return DienThoaiNguoiGui;
	}
	public void setDienThoaiNguoiGui(String dienThoaiNguoiGui) {
		DienThoaiNguoiGui = dienThoaiNguoiGui;
	}
	public String getNoiDungHang() {
		return NoiDungHang;
	}
	public void setNoiDungHang(String noiDungHang) {
		NoiDungHang = noiDungHang;
	}
	public Double getSoTienCOD() {
		return SoTienCOD;
	}
	public void setSoTienCOD(Double soTienCOD) {
		SoTienCOD = soTienCOD;
	}
	public String getGhiChu() {
		return GhiChu;
	}
	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}
	public int getPosIdNhanTin() {
		return PosIdNhanTin;
	}
	public void setPosIdNhanTin(int posIdNhanTin) {
		PosIdNhanTin = posIdNhanTin;
	}
	public String getTenNguoiNhan() {
		return TenNguoiNhan;
	}
	public void setTenNguoiNhan(String tenNguoiNhan) {
		TenNguoiNhan = tenNguoiNhan;
	}
	public Date getNgayNhap() {
		return NgayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		NgayNhap = ngayNhap;
	}
	public String getDiaChiNguoiNhan() {
		return DiaChiNguoiNhan;
	}
	public void setDiaChiNguoiNhan(String diaChiNguoiNhan) {
		DiaChiNguoiNhan = diaChiNguoiNhan;
	}
	public String getDienThoaiNguoiNhan() {
		return DienThoaiNguoiNhan;
	}
	public void setDienThoaiNguoiNhan(String dienThoaiNguoiNhan) {
		DienThoaiNguoiNhan = dienThoaiNguoiNhan;
	}
	public String getMaBuuGui() {
		return MaBuuGui;
	}
	public void setMaBuuGui(String maBuuGui) {
		MaBuuGui = maBuuGui;
	}
	public Double getTrongLuong() {
		return TrongLuong;
	}
	public void setTrongLuong(Double trongLuong) {
		TrongLuong = trongLuong;
	}
	public Double getCuocChinh() {
		return CuocChinh;
	}
	public void setCuocChinh(Double cuocChinh) {
		CuocChinh = cuocChinh;
	}
	public Double getCuocCOD() {
		return CuocCOD;
	}
	public void setCuocCOD(Double cuocCOD) {
		CuocCOD = cuocCOD;
	}
	public boolean isDonHangNoiHuyen() {
		return DonHangNoiHuyen;
	}
	public void setDonHangNoiHuyen(boolean donHangNoiHuyen) {
		DonHangNoiHuyen = donHangNoiHuyen;
	}
	public Double getChieuRong() {
		return ChieuRong;
	}
	public void setChieuRong(Double chieuRong) {
		ChieuRong = chieuRong;
	}
	public Double getChieuDai() {
		return ChieuDai;
	}
	public void setChieuDai(Double chieuDai) {
		ChieuDai = chieuDai;
	}
	public int getMaTinhNhan() {
		return MaTinhNhan;
	}
	public void setMaTinhNhan(int maTinhNhan) {
		MaTinhNhan = maTinhNhan;
	}
	public int getMaHuyenNhan() {
		return MaHuyenNhan;
	}
	public void setMaHuyenNhan(int maHuyenNhan) {
		MaHuyenNhan = maHuyenNhan;
	}
	public String getEmailNguoiNhan() {
		return EmailNguoiNhan;
	}
	public void setEmailNguoiNhan(String emailNguoiNhan) {
		EmailNguoiNhan = emailNguoiNhan;
	}
	public String getExtendData() {
		return ExtendData;
	}
	public void setExtendData(String extendData) {
		ExtendData = extendData;
	}
	public int getFlagConfig() {
		return FlagConfig;
	}
	public void setFlagConfig(int flagConfig) {
		FlagConfig = flagConfig;
	}

	
	
	
}