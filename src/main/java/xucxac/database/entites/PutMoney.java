package xucxac.database.entites;

public class PutMoney {
    private int idPutMoney;
    private int idPhong;
    private int idCustomer;

    public int getIdPutMoney() {
        return idPutMoney;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdPutMoney(int idPutMoney) {
        this.idPutMoney = idPutMoney;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public PutMoney(int idPutMoney, int idPhong, int idCustomer) {
        this.idPutMoney = idPutMoney;
        this.idPhong = idPhong;
        this.idCustomer = idCustomer;
    }

    @Override
    public String toString() {
        return String.valueOf(idCustomer);
    }
}
