package xucxac.database.entites;


public class RoomUser {
    private String idPhong;
    private int soNguoi;

    public String getIdPhong() {return idPhong; }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public RoomUser(String idPhong, int soNguoi) {
        this.idPhong = idPhong;
        this.soNguoi = soNguoi;
    }
}
