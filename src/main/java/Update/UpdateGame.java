package Update;

import Momentan.Game;
import Momentan.Review;
import Momentan.User;
import Search.Search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static utils.Utils.isNumeric;


public class UpdateGame {


    public static void inputReview(Game game, User user, String review , String rating) {
        if (!isNumeric(rating)) {
            System.out.println("Rating must be a number");
            return;
        }
        if (Integer.parseInt(rating) > 10 || Integer.parseInt(rating) < 0) {
            System.out.println("Rating must be between 0 and 10");
            return;
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda  = String.format("INSERT INTO `login`.`review` (`GameName`, `UserName`, `Rating`, `Coment`) VALUES ('%s', '%s', '%s', '%s');", game.getName(), user.getUsername(), rating, review);
            Search search = new Search();
            if (search.searchGame(game.getName()) == null) {
                System.out.println("Game not found");
                return;
            }
            var statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("Review added");
            List<Review> reviews = search.searchReview(game.getName());
            Integer ratingSum = 0;
            for (Review review1 : reviews) {
                ratingSum += Integer.parseInt(review1.getRating());
            }
            Integer newRating = ratingSum / reviews.size();
            String comanda2 = String.format("UPDATE `login`.`game` SET `rating` = '%s' WHERE (`name` = '%s');", newRating, game.getName());
            statement.executeUpdate(comanda2);
            System.out.println("Rating updated");

        }catch (Exception e){
            System.out.println("Error updating review");
        }

    }


    public void delete(String name) {

    }
}
