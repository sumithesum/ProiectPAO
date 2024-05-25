package InputsReaders;

import Momentan.Game;
import Momentan.User;
import Search.Search;

import java.sql.Connection;
import java.sql.DriverManager;

public class InputPlayed extends Inputs {
    /**
     * @param path
     */
    @Override
    public void CSVInput(String path) {

    }

    /**
     * @param path
     */
    @Override
    public void JsonInput(String path) {

    }

    /**
     * @param path
     */
    @Override
    public void MyInput(String path) {

    }

    public void inputPlayed(String gameName, String userName) {
        try {
            Search search = new Search();
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            User user = search.searchUser(userName);
            Game game = search.searchGame(gameName);
            if (user == null || game == null) {
                System.out.println("User or game not found");
                return;
            }
            String comanda = "INSERT INTO played (gameName, userName) VALUES ('" + gameName + "', '" + userName + "');";
            connection.createStatement().executeUpdate(comanda);
            System.out.println("User " + userName + " has played " + gameName);

        } catch (Exception e) {
            System.out.println("Error in inputing in Played");
        }
    }
}
