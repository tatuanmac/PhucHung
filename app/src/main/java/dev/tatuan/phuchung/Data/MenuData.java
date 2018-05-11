package dev.tatuan.phuchung.Data;

public class MenuData {

    public String gia, hinhanh, tendouong;

    public MenuData() {
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getTendouong() {
        return tendouong;
    }

    public void setTendouong(String tendouong) {
        this.tendouong = tendouong;
    }

    public MenuData(String gia, String hinhanh, String tendouong) {
        this.gia = gia;
        this.hinhanh = hinhanh;
        this.tendouong = tendouong;
    }
}
