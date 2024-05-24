package Search;

import Momentan.Game;
import Momentan.Review;
import Momentan.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class Search implements SearchI {
    public  User searchUser(String username) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = "SELECT * FROM user WHERE LOWER(username) = LOWER('" + username + "');";
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery(comanda);
            resultSet.next();
            String username1 = resultSet.getString("username");
            String password = resultSet.getString("password");
            boolean isAdmin = resultSet.getString("admin").equals("1");
            return new User(username1, password, isAdmin);
        }catch (Exception e){
            System.out.println("The username "+ username + " is not found");
            return null;
        }
    }


    @Override
    public Game searchGame(String name) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = "SELECT * FROM game WHERE LOWER(name) = LOWER('" + name + "');";
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery(comanda);
            resultSet.next();
            String name1 = resultSet.getString("name");
            String price = resultSet.getString("price");
            String tags = resultSet.getString("tags");
            String developer = resultSet.getString("developer");
            String description = resultSet.getString("description");
            String rating = resultSet.getString("rating");
            String ageRating = resultSet.getString("ageRating");
            return new Game(name1,description,tags,price,rating,developer,ageRating);

        }catch (Exception e){
            System.out.println("The game "+ name + " is not found");
            return null;
        }
    }

    /**
     * @param gameName
     * @return
     */
    @Override
    public List<Review> searchReview(String gameName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = "SELECT * FROM review WHERE LOWER(GameName) = LOWER('" + gameName + "');";
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery(comanda);
            List<Review> reviews = List.of();
            while (resultSet.next()) {
                String gameName1 = resultSet.getString("GameName");
                String userName = resultSet.getString("UserName");
                String rating = resultSet.getString("Rating");
                String coment = resultSet.getString("Coment");
                reviews.add(new Review(gameName1,userName,rating,coment));
            }
            return reviews;

        }catch (Exception e){
            System.out.println("The game "+ gameName + " is not found");
            return null;
        }
    }
}
