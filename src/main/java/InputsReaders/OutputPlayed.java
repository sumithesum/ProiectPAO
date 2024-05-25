package InputsReaders;

import Clase.Game;
import Clase.Played;
import Clase.User;
import Search.Search;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class OutputPlayed extends Outputs{
    public OutputPlayed(String path) {
        super(path);
    }

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
                Game game;
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
                System.out.println("Input the name of the user");
                String userName = scanner.nextLine();
                User user = search.searchUser(userName);
                if (user == null) {
                    System.out.println("No user found with this name");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                }
                Played played = search.searchPlayed(gameName, userName);

                if (played == null) {
                    System.out.println("No played found with this name");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(gameName);
                sb.append(',');
                sb.append(userName);
                sb.append('\n');
                pw.write(sb.toString());


                System.out.println("Would you like to add another  ? y/n");
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
            Game game;
            User user;
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
                }
                System.out.println("Input the name of the user:");
                String userName = scanner.nextLine();
                user = search.searchUser(userName);
                if (user == null) {
                    System.out.println("No user found with this name");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                }else {

                    Played played = search.searchPlayed(gameName, userName);
                    if (played == null) {
                        System.out.println("No played found with this name");
                        System.out.println("Would you like to try again? y/n");
                        String option = scanner.nextLine();
                        if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                            append = false;
                        }
                        continue;
                    }
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("game", gameName);
                    jsonGenerator.writeStringField("user", userName);
                    jsonGenerator.writeEndObject();
                }
                System.out.println("Would you like to add another ? y/n");
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
            Game game;
            User user;
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
                    System.out.println("Input the name of the user:");
                    String userName = scanner.nextLine();
                    user = search.searchUser(userName);
                    if (user == null) {
                        System.out.println("No user found with this name");
                        System.out.println("Would you like to try again? y/n");
                        String option = scanner.nextLine();
                        if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                            append = false;
                        }
                        continue;
                    } else {
                        Played played = search.searchPlayed(gameName, userName);
                        if (played == null) {
                            System.out.println("No played found with this name");
                            System.out.println("Would you like to try again? y/n");
                            String option = scanner.nextLine();
                            if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                                append = false;
                            }
                            continue;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(gameName);
                        sb.append("->");
                        sb.append(userName);
                        sb.append('\n');
                        pw.write(sb.toString());
                    }

                }


                System.out.println("Would you like to add another ? y/n");
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
