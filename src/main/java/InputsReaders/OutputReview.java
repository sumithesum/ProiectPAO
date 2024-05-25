package InputsReaders;

import Momentan.Review;
import Search.Search;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class OutputReview extends Outputs {
    public OutputReview(String path) {
        super(path);
    }

    List<Review> reviews;

    /**
     *
     */
    @Override
    public void CSVOutput() {
        try {
            Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(path);
            boolean append = true;
            while (append) {

                System.out.println("Input the name of the game");
                String gameName = scanner.nextLine();
                Search search = new Search();
                reviews = search.searchReview(gameName);

                if (reviews == null || reviews.isEmpty()) {
                    System.out.println("No reviews found for this game");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                }
                for (Review review : reviews) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(review.getGameName());
                    sb.append(',');
                    sb.append(review.getUserName());
                    sb.append(',');
                    sb.append(review.getComment());
                    sb.append(',');
                    sb.append(review.getRating());
                    sb.append('\n');
                    pw.write(sb.toString());
                }

                System.out.println("Would you like to add another game reviews? y/n");
                String option = scanner.nextLine();
                if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                    append = false;
                    pw.close();
                }
            }
            pw.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while creating the CSV file");

        }

    }

    /**
     *
     */
    @Override
    public void JsonOutput() {

        JsonFactory jsonFactory = new JsonFactory();

        try {
            FileWriter fileWriter = new FileWriter(path);
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(fileWriter);

            Scanner scanner = new Scanner(System.in);
            jsonGenerator.writeStartArray();
            Search search = new Search();
            boolean append = true;
            while (append) {
                System.out.println("Input the name of the game:");
                String gameName = scanner.nextLine();

                reviews = search.searchReview(gameName);

                if (reviews == null || reviews.isEmpty()) {
                    System.out.println("No reviews found for this game");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                } else {
                    for (Review review : reviews) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("Game Name", review.getGameName());
                        jsonGenerator.writeStringField("User Name", review.getUserName());
                        jsonGenerator.writeStringField("Comment", review.getComment());
                        jsonGenerator.writeStringField("Rating", review.getRating());
                        jsonGenerator.writeEndObject();
                    }
                }
                System.out.println("Would you like to add another games reviews? y/n");
                String option = scanner.nextLine();
                if (option.toLowerCase().equals("n") || option.toLowerCase().equals("no")) {
                    append = false;
                }
            }

            jsonGenerator.writeEndArray();

            jsonGenerator.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating the JSON file");
        }
    }

    /**
     *
     */
    @Override
    public void MyOutput() {
        Search search = new Search();
        Scanner scanner = new Scanner(System.in);
        try {
            PrintWriter pw = new PrintWriter(path);
            boolean append = true;
            while (append) {

                System.out.println("Input the name of the game:");
                String gameName = scanner.nextLine();

                reviews = search.searchReview(gameName);

                if (reviews == null || reviews.isEmpty()) {
                    System.out.println("No reviews found for this game");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n"))
                        append = false;
                } else {
                    for (Review review : reviews) {
                        String sb = review.getGameName() +
                                "->" +
                                review.getUserName() +
                                "->" +
                                review.getComment() +
                                "->" +
                                review.getRating() +
                                '\n';

                        pw.write(sb);
                    }
                }


                System.out.println("Would you like to add another games reviews? y/n");
                String option = scanner.nextLine();
                if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                    append = false;
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while creating the MyOutput file");

        }


    }
}
