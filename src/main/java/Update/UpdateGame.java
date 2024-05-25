package Update;

import Clase.Game;
import Clase.Review;
import Search.Search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * The UpdateGame class contains methods for updating game information in the database,
 * such as the game rating and deleting a game.
 */
public class UpdateGame {


    /**
     * Updates the rating of a game in the database based on the reviews associated with the game.
     * @param game
     * @return void
     */
    public static void  updateRating(Game game) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Search search = new Search();
            List<Review> reviews = search.searchReview(game.getName());
            float ratingSum =  0;
            for (Review review1 : reviews) {
                ratingSum += Float.parseFloat(review1.getRating());
            }
            Float newRating = ratingSum / reviews.size();
            String comanda = String.format("UPDATE login.game SET rating = '%s' WHERE name = '%s';", newRating, game.getName());
            var statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("Rating updated");


        }catch (Exception e){
            System.out.println("Error updating rating");
        }
    }

    /**
     * Deletes a game from the database.
     * @param name the name of the game to be deleted
     * @return void
     */
    public static void delete(String name) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = String.format("DELETE FROM login.game WHERE name = '%s';", name);
            var statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("Game deleted");
        } catch (Exception e) {
            System.out.println("Error deleting game");
        }
    }
}
