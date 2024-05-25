package Clase;

import lombok.Getter;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The User class represents a user in the system, with attributes for username, password,
 * and admin status. It includes methods for managing user accounts, such as deleting an account.
 */
public class User {

    @Getter
    private String username;

    @Getter
    private String password;

    @Getter
    private boolean admin;

    /**
     * Constructs a User object with the specified username, password, and admin status.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param admin whether the user has admin privileges
     */
    public User(String username, String password, boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    /**
     * Constructs a User object with the specified username and password.
     * The user is not an admin by default.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.admin = false;
    }

    /**
     * Constructs a User object with no specified attributes.
     * The username and password are set to null and the user is not an admin by default.
     */
    public User() {
        this.username = null;
        this.password = null;
        this.admin = false;
    }

    /**
     * Deletes the user account from the database.
     */
    public void deleteAccount() {
        try {
            String comanda = "DELETE FROM login.user WHERE LOWER(username) = LOWER('" + this.username + "');";
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("User " + this.username + " has been deleted.");
        } catch (Exception e) {
            System.out.println("Error deleting account");
        }
    }
}
