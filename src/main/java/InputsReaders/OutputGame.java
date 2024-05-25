package InputsReaders;

import Momentan.Game;
import Search.Search;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class OutputGame extends Outputs{
    public OutputGame(String path) {
        super(path);
    }
    Game game;

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
                game = search.searchGame(gameName);

                if (game == null) {
                    System.out.println("No game found with this name");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                }

                StringBuilder sb = new StringBuilder();
                sb.append(game.getName());
                sb.append(',');
                sb.append(game.getDescription());
                sb.append(',');
                sb.append(game.getTags());
                sb.append(',');
                sb.append(game.getPrice());
                sb.append(',');
                sb.append(game.getRating());
                sb.append(',');
                sb.append(game.getDeveloper());
                sb.append(',');
                sb.append(game.getAgeRating());
                sb.append('\n');
                pw.write(sb.toString());

                System.out.println("Would you like to add another game ? y/n");
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

                game = search.searchGame(gameName);


                if (game == null) {
                    System.out.println("No game found with this name");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                } else {

                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("Name", game.getName());
                    jsonGenerator.writeStringField("Description", game.getDescription());
                    jsonGenerator.writeStringField("Tags", game.getTags());
                    jsonGenerator.writeStringField("Price", game.getPrice());
                    jsonGenerator.writeStringField("Rating", game.getRating());
                    jsonGenerator.writeStringField("Developer", game.getDeveloper());
                    jsonGenerator.writeStringField("AgeRating", game.getAgeRating());
                    jsonGenerator.writeEndObject();
                }
                System.out.println("Would you like to add another game? y/n");
                String option = scanner.nextLine();
                if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
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

                game = search.searchGame(gameName);

                if (game == null) {
                    System.out.println("No game found with this name");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                } else {
                    StringBuilder sb = new StringBuilder();

                    sb.append(game.getName());
                    sb.append("->");

                    sb.append(game.getDescription());
                    sb.append("->");

                    sb.append(game.getTags());
                    sb.append("->");

                    sb.append(game.getPrice());
                    sb.append("->");

                    sb.append(game.getRating());
                    sb.append("->");

                    sb.append(game.getDeveloper());
                    sb.append("->");

                    sb.append(game.getAgeRating());
                    sb.append('\n');
                    pw.write(sb.toString());
                }


                System.out.println("Would you like to add another game ? y/n");
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
