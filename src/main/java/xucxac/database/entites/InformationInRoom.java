package xucxac.database.entites;

import java.util.ArrayList;
import java.util.List;

public class InformationInRoom {
    private int idPhong;
   private List<Integer> idcustomer = new ArrayList<Integer>();


    public int getIdPhong() {
        return idPhong;
    }

    public List<Integer> getIdcustomer() {
        return idcustomer;
    }


    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public void setIdcustomer(List<Integer> idcustomer) {
        this.idcustomer = idcustomer;
    }

    public InformationInRoom( int idPhong, List<Integer> idcustomer) {
        this.idPhong = idPhong;
        this.idcustomer = idcustomer;
    }

    @Override
    public String toString() {
        return "InformationInRoom{idPhong=%d, idcustomer=%s}".formatted(idPhong, idcustomer);
    }
}
