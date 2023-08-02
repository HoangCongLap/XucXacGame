package xucxac.database.entites;

import java.util.ArrayList;
import java.util.List;

public class InformationInRoom {
    private int idPhong;
    private List<Integer> customerIds = new ArrayList<Integer>();// customersId


    public int getIdPhong() {
        return idPhong;
    }

    public List<Integer> getCustomerIds() {
        return customerIds;
    }


    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    public void refresh(List<Integer> customerIds) {
        this.customerIds.clear();
        this.customerIds.addAll(customerIds);
    }


    public InformationInRoom(int idPhong, List<Integer> idcustomer) {
        this.idPhong = idPhong;
        this.customerIds = idcustomer;
    }

    @Override
    public String toString() {
        return "InformationInRoom{idPhong=%d, idcustomer=%s}".formatted(idPhong, customerIds);
    }
}
