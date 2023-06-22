package xucxac.users;


import lombok.AllArgsConstructor;
import lombok.Data;


public class User {
    private String idPhong, tenPhong,soNguoi;

    public String getIdPhong() {return idPhong; }

    public String getTenPhong() {
        return tenPhong;
    }

    public String getSoNguoi() {
        return soNguoi;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public void setSoNguoi(String soNguoi) {
        this.soNguoi = soNguoi;
    }

    public User(String idPhong, String tenPhong, String soNguoi) {
        this.idPhong = idPhong;
        this.tenPhong = tenPhong;
        this.soNguoi = soNguoi;
    }
}
