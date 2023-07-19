package xucxac.database.entites;


public class RoomUser {
    private String idPhong;
    private int idcustomer;
    private int soNguoi;

    public String getIdPhong() {return idPhong; }

    public int getIdcustomer() {
        return idcustomer;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public RoomUser(String idPhong,int idcustomer, int soNguoi) {
        this.idPhong = idPhong;
        this.idcustomer=idcustomer;
        this.soNguoi = soNguoi;
    }

    @Override
    public String toString() {
        return "RoomUser{" +
                "idPhong='" + idPhong + '\'' +
                ", idcustomer=" + idcustomer +
                ", soNguoi=" + soNguoi +
                '}';
    }
}
