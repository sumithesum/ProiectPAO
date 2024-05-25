package InputsReaders;

import Momentan.User;
import Search.Search;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;

public class OutputUsers extends Outputs {
    private User user;

    public OutputUsers(String path) {
        super(path);
    }

    @Override
    public void CSVOutput() {
        try {
            PrintWriter pw = new PrintWriter(path);
            boolean append = true;
            while (append) {

                System.out.println("Input the name of the user:");
                Scanner scanner = new Scanner(System.in);
                String username = scanner.nextLine();
                Search search = new Search();
                user = search.searchUser(username);

                StringBuilder sb = new StringBuilder();

                sb.append(user.getUsername());
                sb.append(',');
                sb.append(user.getPassword());
                sb.append(',');
                sb.append(user.isAdmin() ? "Admin" : "User");
                sb.append('\n');

                pw.write(sb.toString());

                if (user == null) {
                    System.out.println("User not found");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.toLowerCase().equals("n")) {
                        append = false;
                    }
                }
                System.out.println("Would you like to add another user? y/n");
                String option = scanner.nextLine();
                if (option.toLowerCase().equals("n")) {
                    append = false;
                }


            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while creating the CSV file");

        }

    }

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
                System.out.println("Input the name of the user:");
                String username = scanner.nextLine();

                user = search.searchUser(username);

                if (user == null) {
                    System.out.println("User not found");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.toLowerCase().equals("n") || option.toLowerCase().equals("no")) {
                        append = false;
                    }
                } else {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("username", username);
                    jsonGenerator.writeStringField("password", user.getPassword());
                    jsonGenerator.writeEndObject();
                }
                System.out.println("Would you like to add another user? y/n");
                String option = scanner.nextLine();
                if (option.toLowerCase().equals("n") || option.toLowerCase().equals("no")) {
                    append = false;
                }
            }

            jsonGenerator.writeEndArray();

            jsonGenerator.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void MyOutput() {
        Search search = new Search();
        Scanner scanner = new Scanner(System.in);
        try {
            PrintWriter pw = new PrintWriter(path);
            boolean append = true;
            while (append) {

                System.out.println("Input the name of the user:");
                String username = scanner.nextLine();

                user = search.searchUser(username);

                if (user == null) {
                    System.out.println("User not found");
                    System.out.println("Would you like to try again? y/n");
                    String option = scanner.nextLine();
                    if (option.equalsIgnoreCase("n"))
                        append = false;

                }
                else {


                    String sb = user.getUsername() +
                            "->" +
                            user.getPassword() +
                            "->" +
                            (user.isAdmin() ? "Admin" : "User") +
                            '\n';

                    pw.write(sb);
                }

                System.out.println("Would you like to add another user? y/n");
                String option = scanner.nextLine();
                if (option.toLowerCase().equals("n")) {
                    append = false;
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while creating the MyOutput file");

        }


    }
}
