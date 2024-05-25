package Clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * The Admin class extends the User class to provide additional administrative
 * capabilities such as promoting, demoting, and deleting users.
 */
public class Admin extends User {

    /**
     * Constructs an Admin object with the specified username and password.
     *
     * @param username the username of the admin
     * @param password the password of the admin
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Promotes a user to admin.
     *
     * @param username the username of the user to be promoted
     */
    public void promoteUser(String username) {
        try {
            String comanda = "UPDATE login.user SET admin = 1 WHERE LOWER(username) = LOWER('" + username + "');";
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("User " + username + " has been promoted to admin.");
        } catch (Exception e) {
            System.out.println("The username " + username + " is not found.");
        }
    }

    /**
     * Demotes a user from admin to regular user.
     *
     * @param username the username of the user to be demoted
     */
    public void demoteUser(String username) {
        try {
            String comanda = "UPDATE login.user SET admin = 0 WHERE LOWER(username) = LOWER('" + username + "');";
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("User " + username + " has been demoted from admin.");
        } catch (Exception e) {
            System.out.println("The username " + username + " is not found.");
        }
    }

    /**
     * Deletes a user from the system.
     *
     * @param username the username of the user to be deleted
     */
    public void deleteUser(String username) {
        try {
            String comanda = "DELETE FROM login.user WHERE LOWER(username) = LOWER('" + username + "');";
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("User " + username + " has been deleted.");
        } catch (Exception e) {
            System.out.println("The username " + username + " is not found.");
        }
    }
}
