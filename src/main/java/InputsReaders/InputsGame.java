package InputsReaders;

import Momentan.Game;
import Momentan.Review;
import Search.Search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;

public class InputsGame extends Inputs {
    @Override
    public void CSVInput(String path) {

    }

    @Override
    public void JsonInput(String path) {

    }

    @Override
    public void MyInput(String path) {

    }

    public void GameInput(String nume , String price ,String tags ,String developer, String description,String rating , String ageRating) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            String comanda = "INSERT INTO login.game (name,price,tags,developer,description,rating,agerating) VALUES ('"+nume+"',"+price+",'"+tags+"','"+developer+"','"+description+"',"+rating+","+ageRating+");";
            System.out.println(comanda);
            statement.executeUpdate(comanda);
            Search search = new Search();
            Game game = search.searchGame(nume);
            List<Review> reviews = search.searchReview(game.getName());
            if (reviews.isEmpty())
                return;
            int ratingSum = 0;
            for (Review review1 : reviews) {
                ratingSum += Integer.parseInt(review1.getRating());
            }
            Integer newRating = ratingSum / reviews.size();
            String comanda2 = String.format("UPDATE `login`.`game` SET `rating` = '%s' WHERE (`name` = '%s');", newRating, game.getName());
            statement.executeUpdate(comanda2);
        }catch (Exception e){
            System.out.println("The game "+ nume + " is already in the database");
        }
    }
    public void GameInput(Game game) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            String comanda = "INSERT INTO login.game (name,price,tags,developer,description,rating,agerating) VALUES ('"+game.getName()+"',"+game.getPrice()+",'"+game.getTags()+"','"+game.getDeveloper()+"','"+game.getDescription()+"',"+game.getRating()+","+game.getAgeRating()+");";
            System.out.println(comanda);
            statement.executeUpdate(comanda);
            System.out.println("Game "+ game.getName() + " added");
            Search search = new Search();
            Game game1 = search.searchGame(game.getName());
            List<Review> reviews = search.searchReview(game1.getName());
            if (reviews.isEmpty())
                return;
            int ratingSum = 0;
            for (Review review1 : reviews) {
                ratingSum += Integer.parseInt(review1.getRating());
            }
            Integer newRating = ratingSum / reviews.size();
            String comanda2 = String.format("UPDATE `login`.`game` SET `rating` = '%s' WHERE (`name` = '%s');", newRating, game1.getName());
            statement.executeUpdate(comanda2);
        }catch (Exception e){
            System.out.println("The game "+ game.getName() + " is already in the database");
        }
    }
}
