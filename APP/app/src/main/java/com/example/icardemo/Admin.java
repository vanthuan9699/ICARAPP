package com.example.icardemo;

public class Admin {
    private int id;
    private String taikhoan;
    private String matkhau;
    private int SDT;
    private String TenDaiLi;
    private int SoLanSuDung;

    public Admin(int id, String taikhoan, String matkhau, int SDT, String tenDaiLi, int soLanSuDung) {
        this.id = id;
        this.taikhoan = taikhoan;
        this.matkhau = matkhau;
        this.SDT = SDT;
        this.TenDaiLi = tenDaiLi;
        this.SoLanSuDung = soLanSuDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getSDT() {
        return SDT;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public String getTenDaiLi() {
        return TenDaiLi;
    }

    public void setTenDaiLi(String tenDaiLi) {
        this.TenDaiLi = tenDaiLi;
    }

    public int getSoLanSuDung() {
        return SoLanSuDung;
    }

    public void setSoLanSuDung(int soLanSuDung) {
        this.SoLanSuDung = soLanSuDung;
    }
}
