package Update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdatePlayed {
    public static void deletePlayed(String gameName, String userName) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM Played WHERE Lower(gameName) = LOWER('" + gameName + "') AND LOWER( userName) = LOWER('" + userName + "');";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error deleting played");
        }
    }
}
