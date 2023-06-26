package xucxac.database.entites;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private int id;
    private String name;
    private String gender;
    private int cardMoney;
    private int moneyTotal;
    private int idAccount;
}
