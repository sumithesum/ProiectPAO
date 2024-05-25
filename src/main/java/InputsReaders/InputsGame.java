package InputsReaders;

import Clase.Game;
import Clase.Review;
import Search.Search;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class InputsGame extends Inputs {
    @Override
    public void CSVInput(String path) {
        List<String[]> l ;
        try {
            l = ReadCSV.readCSV(path);
            String nume , price ,tags ,developer, description,rating , ageRating;
            for (String[] s : l) {
                nume = s[0];
                price = s[1];
                tags = s[2];
                developer = s[3];
                description = s[4];
                rating = s[5];
                ageRating = s[6];
                GameInput(nume,price,tags,developer,description,rating,ageRating);
            }
        }catch (Exception e){
            System.out.println("Error reading CSV file");

        }

    }

    @Override
    public void JsonInput(String path) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Map<String, Object>> reviews = mapper.readValue(new File(path), new TypeReference<>() {});

            for (Map<String, Object> review : reviews) {
                String nume = (String) review.get("name");
                String price = (String) review.get("price");
                String tags = (String) review.get("tags");
                String developer = (String) review.get("developer");
                String description = (String) review.get("description");
                String rating = (String) review.get("rating");
                String ageRating = (String) review.get("ageRating");
                GameInput(nume,price,tags,developer,description,rating,ageRating);


            }
        } catch (IOException e) {
            System.out.println("File not found or unable to read file");
        }
    }

    @Override
    public void MyInput(String path) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                if (parts.length == 7) {
                    String nume = parts[0];
                    String price = parts[1];
                    String tags = parts[2];
                    String developer = parts[3];
                    String description = parts[4];
                    String rating = parts[5];
                    String ageRating = parts[6];
                    GameInput(nume,price,tags,developer,description,rating,ageRating);


                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file myInput");
        }

    }

    public void GameInput(String nume , String price ,String tags ,String developer, String description,String rating , String ageRating) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            Statement statement = connection.createStatement();
            String comanda = "INSERT INTO login.game (name,price,tags,developer,description,rating,agerating) VALUES ('"+nume+"',"+price+",'"+tags+"','"+developer+"','"+description+"',"+rating+","+ageRating+");";
            statement.executeUpdate(comanda);
            System.out.println("Game "+ nume + " added");
            Search search = new Search();
            Game game = search.searchGame(nume);
            List<Review> reviews = search.searchReview(game.getName());
            if (reviews.isEmpty())
                return;
            float ratingSum = 0;
            for (Review review1 : reviews) {
                ratingSum += Float.parseFloat(review1.getRating());
            }
            float newRating = ratingSum / reviews.size();
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
