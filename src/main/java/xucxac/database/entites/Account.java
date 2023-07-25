package xucxac.database.entites;

public class Account {
    int id;
    String useName;
    String password;

    public int getId() {
        return id;
    }

    public String getUseName() {
        return useName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(int id, String useName, String password) {
        this.id = id;
        this.useName = useName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", useName='" + useName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
