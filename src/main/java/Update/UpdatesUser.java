package Update;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

public class UpdatesUser extends Update{
    @Override
    public void promoteUser(String username) {
        try {

            String comanda = "UPDATE login.user SET admin = "+1+" WHERE LOWER(username) = LOWER('"+ username +"');";

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("User " + username + " has been promoted to admin.");
        }catch (Exception e){
            System.out.println("The username "+ username + " is not found");
        }
    }

    @Override
    public void demoteUser(String username) {
        try {

            String comanda = "UPDATE login.user SET admin = "+0+" WHERE LOWER(username) = LOWER('"+ username +"');";

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("User " + username + " has been demoted from admin.");
        }catch (Exception e){
            System.out.println("The username "+ username + " is not found");
        }

    }

    @Override
    public void deleteUser(String name) {

    }
}
