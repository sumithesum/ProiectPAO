package Update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * The UpdatePlayed class is responsible for updating the Played table in the database.
 * It includes a method to delete a played game from the table.
 */
public class UpdatePlayed {
    /**
     * Deletes a record of a game played by a user from the Played table in the database.
     *
     * @param gameName the name of the game
     * @param userName the name of the user who played the game
     */
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
