package Update;

import Clase.Review;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The UpdateReview class contains methods for updating review information in the database.
 */
public class UpdateReview {
    /**
     * Deletes a review from the database.
     *
     * @param review the review to be deleted
     */
    public void deleteReview(Review review) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = String.format("DELETE FROM login.review WHERE (`gameName` = '%s' AND `username` = '%s');", review.getGameName(), review.getUserName());
            var statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("Review deleted");

        } catch (Exception e) {
            System.out.println("Error deleting review");
        }

    }


}
