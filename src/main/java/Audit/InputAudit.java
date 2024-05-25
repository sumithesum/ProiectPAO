package Audit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * The InputAudit class provides methods for auditing user commands by
 * inserting audit records into a database and reading them.
 */
public class InputAudit {

    /**
     * Inserts an audit record into the database.
     *
     * @param command the command to be audited
     * @param username the username of the user executing the command
     */
    public static void inputAudit(String command, String username) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = "INSERT INTO audit (comanda, username) VALUES ('" + command + "', '" + username + "');";
            connection.createStatement().executeUpdate(comanda);
        } catch (Exception e) {
            System.out.println("Error in input Audit ");
        }
    }

    /**
     * Reads audit records for a specific user from the database.
     *
     * @param username the username of the user whose audit records are to be retrieved
     * @return a list of Audit objects representing the audit records of the user
     */
    public static List<Audit> readAudit(String username) {
        try {
            List<Audit> auditList = new ArrayList<>();
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = "SELECT LOWER(USERNAME), LOWER(COMANDA) " +
                    "FROM audit " +
                    "WHERE LOWER(username) = LOWER('" + username + "') " +
                    "ORDER BY idaudit DESC;";

            var result = connection.createStatement().executeQuery(comanda);
            while (result.next()) {
                String comanda1 = result.getString(2);
                String username1 = result.getString(1);
                Audit audit = new Audit(comanda1, username1);
                auditList.add(audit);
            }
            return auditList;

        } catch (Exception e) {
            System.out.println("Error in read Audit ");
            return null;
        }
    }
}

