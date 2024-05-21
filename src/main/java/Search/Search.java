package Search;

import Momentan.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Search implements SearchI {
    public User searchUser(String username) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = "SELECT * FROM user WHERE LOWER(username) = LOWER('" + username + "');";
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery(comanda);
            resultSet.next();
            String username1 = resultSet.getString("username");
            String password = resultSet.getString("password");
            boolean isAdmin = resultSet.getString("admin").equals("1");
            return new User(username1, password, isAdmin);
        }catch (Exception e){
            System.out.println("The username "+ username + " is not found");
            return null;
        }
    }
}
