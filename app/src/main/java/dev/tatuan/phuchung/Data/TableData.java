package dev.tatuan.phuchung.Data;

public class TableData {
    String stt;
    String hinhanh, soban;
    String trangthai;

    public String getTrangthai() {
        return trangthai;
    }

    public String getStt() {
        return stt;
    }

    public void setTrangthai() {
        this.trangthai = trangthai;
    }

    public TableData() {
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getSoban() {
        return soban;
    }

    public void setSoban(String soban) {
        this.soban = soban;
    }

    public TableData(String stt, String hinhanh, String soban, String trangthai) {
        this.stt = stt;
        this.hinhanh = hinhanh;
        this.soban = soban;
        this.trangthai = trangthai;
    }
}
