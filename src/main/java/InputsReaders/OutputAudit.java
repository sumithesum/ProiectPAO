package InputsReaders;

import Audit.Audit;
import Clase.Game;
import Clase.User;
import Search.Search;
import Audit.InputAudit;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class OutputAudit extends Outputs {
    public OutputAudit(String path) {
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
            Search search = new Search();
            boolean append = true;
            while (append) {

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

                List<Audit> audit = InputAudit.readAudit(userName);
                if (audit == null) {
                    System.out.println("No audit found with this name");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                        append = false;
                    }
                    continue;
                }
                for (Audit audit1 : audit) {
                    pw.println(audit1.getCommand() + "," + audit1.getUsername());
                }

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

                    List<Audit> audit = InputAudit.readAudit(userName);
                    if (audit == null) {
                        System.out.println("No audit found with this name");
                        System.out.println("Would you like to try again? y/n");
                        String option = scanner.nextLine();
                        if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                            append = false;
                        }
                        continue;
                    }
                    for (Audit audit1 : audit) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("Command", audit1.getCommand());
                        jsonGenerator.writeStringField("Username", audit1.getUsername());
                        jsonGenerator.writeEndObject();
                    }
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

                    List<Audit> audit = InputAudit.readAudit(userName);
                    if (audit == null) {
                        System.out.println("No audit found with this name");
                        System.out.println("Would you like to try again? y/n");
                        String option = scanner.nextLine();
                        if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                            append = false;
                        }
                        continue;
                    }
                    for (Audit audit1 : audit) {
                        pw.println(audit1.getCommand() + "," + audit1.getUsername());
                    }

                }


                System.out.println("Would you like to add another ? y/n");
                String option = scanner.nextLine();
                if (option.equalsIgnoreCase("n") || option.equalsIgnoreCase("no")) {
                    append = false;
                }
            }
            pw.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("An error occurred while creating the MyOutput file");

        }


    }
}
