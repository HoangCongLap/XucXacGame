package xucxac.database.entites;

import java.util.ArrayList;
import java.util.List;

public class ListPutMoney {
    private List<PutMoney> moneyPuts = new ArrayList<>();

    public List<PutMoney> getMoneyPuts() {
        return moneyPuts;
    }

    public void setMoneyPuts(List<PutMoney> moneyPuts) {
        this.moneyPuts = moneyPuts;
    }
    public void refresh(List<PutMoney> moneyPuts) {
        this.moneyPuts.clear();
        this.moneyPuts.addAll(moneyPuts);
    }
    public ListPutMoney(List<PutMoney> moneyPuts) {
        this.moneyPuts = moneyPuts;
    }

    @Override
    public String toString() {
        return "ListPutMoney{" +
                "moneyPuts=" + moneyPuts +
                '}';
    }
}
