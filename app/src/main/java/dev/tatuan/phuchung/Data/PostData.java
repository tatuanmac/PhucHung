package dev.tatuan.phuchung.Data;

public class PostData {
    public String id, gia, hinhanh, tendouong, soluong;

    public PostData() {
    }

    public PostData(String id, String gia, String hinhanh, String tendouong, String soluong) {
        this.id = id;
        this.gia = gia;
        this.hinhanh = hinhanh;
        this.tendouong = tendouong;
        this.soluong = soluong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

}
