package Audit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class InputAudit {
    public static void inputAudit(String command, String username) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = "INSERT INTO audit (comanda, username) VALUES ('" + command + "', '" + username + "');";
            connection.createStatement().executeUpdate(comanda);
        } catch (Exception e) {
            System.out.println("Error in input Audit ");
        }
    }
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

        }catch (Exception e){
            System.out.println("Error in read Audit ");
            return null;
        }
    }
}
