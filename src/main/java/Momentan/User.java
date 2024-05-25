package Momentan;

import lombok.Getter;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class User {
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private boolean admin;

    public User(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.admin = false;
    }
    public User() {
        this.username = null;
        this.password = null;
        this.admin = false;
    }
    public void deleteAccount() {
        try {
            String comanda = "DELETE FROM login.user WHERE LOWER(username) = LOWER('"+ this.username +"');";
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("User " + this.username + " has been deleted.");
        }catch (Exception e){
            System.out.println("Error deleting account");
        }
    }




}

