package InputsReaders;

import Momentan.Game;
import Momentan.Review;
import Momentan.User;
import Search.Search;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import static utils.Utils.isNumeric;

public class InputReview extends Inputs{
    /**
     * @param path
     */
    @Override
    public void CSVInput(String path) {
        List<String[]> l ;
        try {
            l = ReadCSV.readCSV(path);
            String username , gameName , review , rating;
            for (String[] s : l) {
                gameName = s[0];
                username = s[1];
                review = s[2];
                rating = s[3];
                User user = new Search().searchUser(username);
                Game game = new Search().searchGame(gameName);
                if (user == null || game == null) {
                    System.out.println("User or game not found");
                    return;
                }
                inputReview(game,user,review,rating);
            }
        }catch (Exception e){
            System.out.println("File not found");

        }

    }


    /**
     * @param path
     */
    @Override
    public void JsonInput(String path) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Map<String, Object>> reviews = mapper.readValue(new File(path), new TypeReference<>() {});

            for (Map<String, Object> review : reviews) {
                String username = (String) review.get("username");
                String gamename = (String) review.get("gamename");
                String comment = (String) review.get("comment");
                Double rating = (Double) review.get("rating");
                String ratingString = rating.toString();
                if (username == null || gamename == null || comment == null || rating == null) {
                    System.out.println("Invalid review");
                } else {
                    User user = new Search().searchUser(username);
                    Game game = new Search().searchGame(gamename);
                    if (user == null || game == null) {
                        System.out.println("User or game not found");
                        return;
                    }
                    inputReview(game, user, comment, ratingString);
                    System.out.println("Review for " + gamename + " by " + username + " added");
                }
            }
        } catch (IOException e) {
            System.out.println("File not found or unable to read file");
        }
    }

    /**
     * @param path
     */
    @Override
    public void MyInput(String path) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                if (parts.length == 4) {
                    String gameName = parts[0];
                    String username = parts[1];
                    String review = parts[2];
                    String rating = parts[3];
                    User user = new Search().searchUser(username);
                    Game game = new Search().searchGame(gameName);
                    if (user == null || game == null) {
                        System.out.println("User or game not found");
                        return;
                    }
                    inputReview(game, user, review, rating);

                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file myInput");
        }

    }

    public static void inputReview(Game game, User user, String review , String rating) {
        if (!isNumeric(rating)) {
            System.out.println("Rating must be a number");
            return;
        }
        if (Float.parseFloat(rating) > 10 || Float.parseFloat(rating) < 0) {
            System.out.println("Rating must be between 0 and 10");
            return;
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda  = String.format("INSERT INTO login.review (gameName,username,comment,rating) VALUES ('%s','%s','%s','%s');", game.getName(), user.getUsername(), review, rating);
            Search search = new Search();
            var statement = connection.createStatement();
            statement.executeUpdate(comanda);
            System.out.println("Review added");
            List<Review> reviews = search.searchReview(game.getName());
            float ratingSum =  0;
            for (Review review1 : reviews) {
                ratingSum += Float.parseFloat(review1.getRating());
            }
            Float newRating = ratingSum / reviews.size();
            String comanda2 = String.format("UPDATE `login`.`game` SET `rating` = '%s' WHERE (`name` = '%s');", newRating, game.getName());
            statement.executeUpdate(comanda2);
            System.out.println("Rating updated");

        }catch (Exception e){
            System.out.println("Error updating review");
        }

    }

}
