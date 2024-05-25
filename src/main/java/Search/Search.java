package Search;

import Clase.Game;
import Clase.Played;
import Clase.Review;
import Clase.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 * The Search class provides methods for searching the database for users, games, and reviews,ect.

 */
public class Search  {
    /**
     * Searches the database for a user with the specified username.
     *
     * @param username the username of the user to search for
     * @return the User object with the specified username, or null if the user is not found
     */
    public User searchUser(String username) {
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
        } catch (Exception e) {
            System.out.println("The username " + username + " is not found");
            return null;
        }
    }


    /**
     * Searches the database for a game with the specified name.
     *
     * @param name the name of the game to search for
     * @return the Game object with the specified name, or null if the game is not found
     */
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
            return new Game(name1, description, tags, price, rating, developer, ageRating);

        } catch (Exception e) {
            System.out.println("The game " + name + " is not found");
            return null;
        }
    }


    /**
     * Searches the database for a reviews with the specified game name.
     *
     * @param gameName the name of the game to search for
     * @return a list of Review objects with the specified game name, or null if the game is not found
     */
    public List<Review> searchReview(String gameName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = "SELECT * FROM review WHERE LOWER(GameName) = LOWER('" + gameName + "');";
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery(comanda);
            List<Review> reviews = new java.util.ArrayList<>(List.of());
            while (resultSet.next()) {
                String gameName1 = resultSet.getString("GameName");
                String userName = resultSet.getString("UserName");
                String rating = resultSet.getString("Rating");
                String coment = resultSet.getString("Comment");
                reviews.add(new Review(gameName1, userName, rating, coment));
            }
            return reviews;

        } catch (Exception e) {
            System.out.println("The game " + gameName + " is not found");
            return null;
        }
    }
    /**
     * Searches the database for a reviews with the specified user name.
     *
     * @param userName the name of the user to search for
     * @return a list of Review objects with the specified user name, or null if the user is not found
     */
    public List<Review> searchReviewUser(String userName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");

            String comanda = "SELECT * FROM review WHERE LOWER(UserName) = LOWER('" + userName + "');";
            Statement statement = connection.createStatement();
            var resultSet = statement.executeQuery(comanda);
            List<Review> reviews = new java.util.ArrayList<>(List.of());
            while (resultSet.next()) {
                String gameName1 = resultSet.getString("GameName");
                String userName1 = resultSet.getString("UserName");
                String rating = resultSet.getString("Rating");
                String coment = resultSet.getString("Comment");
                reviews.add(new Review(gameName1, userName1, rating, coment));
            }
            return reviews;
        } catch (Exception e) {
            System.out.println("The user " + userName + " is not found");
            return null;
        }
    }
    /**
     * Searches the database for a review with the specified user name and game name.
     *
     * @param userName the name of the user to search for
     * @param gameName the name of the game to search for
     * @return the Review object with the specified user name and game name, or null if the review is not found
     */
    public Review searchReviewUserGame(String userName, String gameName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            String comanda = "SELECT * FROM review WHERE LOWER(UserName) = LOWER('" + userName + "') AND LOWER(GameName) = LOWER('" + gameName + "');";
            var resultSet = statement.executeQuery(comanda);
            resultSet.next();
            String gameName1 = resultSet.getString("GameName");
            String userName1 = resultSet.getString("UserName");
            String rating = resultSet.getString("Rating");
            String coment = resultSet.getString("Comment");
            return new Review(gameName1, userName1, rating, coment);


        } catch (Exception e) {
            System.out.println("Error");
            return null;
        }
    }

    /**
     * Searches the database for a played with the specified user name and game name.
     *
     * @param gameName the name of the game to search for
     * @param userName the name of the user to search for
     * @return the Played object with the specified user name and game name, or null if the played is not found
     */
    public Played searchPlayed(String gameName, String userName) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            String comanda = "SELECT * FROM played WHERE LOWER(gameName) = LOWER('" + gameName + "') AND LOWER(userName) = LOWER('" + userName + "');";
            var resultSet = statement.executeQuery(comanda);
            resultSet.next();
            String gameName1 = resultSet.getString("gameName");
            String userName1 = resultSet.getString("userName");
            return new Played(gameName1, userName1);
        } catch (Exception e) {
            System.out.println("Error in searching played");
            return null;
        }
    }

    /**
     * Searches the database for a playeds with the specified user name.
     *
     * @param userName the name of the user to search for
     * @return a list of Played objects with the specified user name, or null if the user is not found
     */
    public List<Played> searchPlayedUser(String userName) {
        List<Played> played = new java.util.ArrayList<>(List.of());
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            String comanda = "SELECT * FROM played WHERE LOWER(userName) = LOWER('" + userName + "');";
            var resultSet = statement.executeQuery(comanda);
            while (resultSet.next()) {
                String gameName = resultSet.getString("gameName");
                String userName1 = resultSet.getString("userName");
                played.add(new Played(gameName, userName1));
            }
            return played;
        } catch (Exception e) {
            System.out.println("Error in searching played");
            return null;
        }
    }

    /**
     * Searches the database for a playeds with the specified game name.
     *
     * @param gameName the name of the game to search for
     * @return a list of Played objects with the specified game name, or null if the game is not found
     */
    public List<Played> searchPlayedGame(String gameName) {
        List<Played> played = new java.util.ArrayList<>(List.of());
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            String comanda = "SELECT * FROM played WHERE LOWER(gameName) = LOWER('" + gameName + "');";
            var resultSet = statement.executeQuery(comanda);
            while (resultSet.next()) {
                String gameName1 = resultSet.getString("gameName");
                String userName = resultSet.getString("userName");
                played.add(new Played(gameName1, userName));
            }
            return played;
        } catch (Exception e) {
            System.out.println("Error in searching played");
            return null;
        }
    }
}


