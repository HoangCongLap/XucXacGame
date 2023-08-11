package xucxac.database.entites;


import java.util.List;

public class RoomUser {
    private int idPhong;
    private int customerOwnerId;
    private int soNguoi;


    public int getIdPhong() {
        return idPhong;
    }

    public int getCustomerOwnerId() {
        return customerOwnerId;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public void setCustomerOwnerId(int customerOwnerId) {
        this.customerOwnerId = customerOwnerId;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }



    public RoomUser(int idPhong, int idcustomer, int soNguoi) {
        this.idPhong = idPhong;
        this.customerOwnerId = idcustomer;
        this.soNguoi = soNguoi;
    }

    @Override
    public String toString() {
        return "RoomUser{" +
                "idPhong='" + idPhong + '\'' +
                ", idcustomer=" + customerOwnerId +
                ", soNguoi=" + soNguoi +
                '}';
    }


}
