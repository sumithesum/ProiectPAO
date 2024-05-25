package utils;

import Clase.Game;

import java.util.Scanner;

public class Utils {
    public static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }
    public static Game readGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the game");
        String name = scanner.nextLine();
        System.out.println("Enter the price of the game");
        String price = scanner.nextLine();
        System.out.println("Enter the tags of the game");
        String tags = scanner.nextLine();
        System.out.println("Enter the developer of the game");
        String developer = scanner.nextLine();
        System.out.println("Enter the description of the game");
        String description = scanner.nextLine();
        System.out.println("Enter the rating of the game");
        String rating = scanner.nextLine();
        System.out.println("Enter the age rating of the game");
        String ageRating = scanner.nextLine();
        System.out.println(name + " " + price + " " + tags + " " + developer + " " + description + " " + rating + " " + ageRating);
        if (price.isEmpty() || !isNumeric(rating) || !isNumeric(ageRating) || !isNumeric(price) || name.isEmpty() || tags.isEmpty() || developer.isEmpty() || description.isEmpty() || rating.isEmpty() || ageRating.isEmpty()){
            System.out.println("Invalid input");
            return null;
        }
        if (Integer.parseInt(ageRating) > 18 || Integer.parseInt(ageRating) < 0){
            System.out.println("Invalid age rating");
            return null;
        }
        if (Integer.parseInt(rating) > 10 || Integer.parseInt(rating) < 0){
            System.out.println("Invalid rating");
            return null;
        }

        return new Game(name,description,tags,price,rating,developer,ageRating);
    }
}
