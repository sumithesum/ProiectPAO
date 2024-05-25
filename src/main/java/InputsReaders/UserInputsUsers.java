package InputsReaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class UserInputsUsers extends Inputs {
    @Override
    public void CSVInput(String path) {
        List<String[]> l ;
        try {
            l = ReadCSV.readCSV(path);
            String nume , password;
            for (String[] s : l) {
                nume = s[0];
                password = s[1];
                if (UserInput(nume,password))
                    System.out.println("User "+ nume +" added");
                else
                    System.out.println("User "+ nume +" not added");
            }
        }catch (Exception e){
            System.out.println("File not found");

        }

    }


    @Override
    public void JsonInput(String path) {
        ObjectMapper mapper = new ObjectMapper();

        try {

            List<Map<String, String>> users = mapper.readValue(new File(path), new TypeReference<>() {
            });


            for (Map<String, String> user : users) {
                String username = user.get("username");
                String password = user.get("password");


                if (UserInput(username, password)) {
                    System.out.println("User " + username + " added");
                } else {
                    System.out.println("User " + username + " not added");
                }
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    @Override
    public void MyInput(String path) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];

                    if (UserInput(username, password)) {
                        System.out.println("User " + username + " added");
                    } else {
                        System.out.println("User " + username + " not added");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file myInput");
        }
    }

    public boolean UserInput(String name, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = MessageFormat.format("INSERT INTO `login`.`user`('username','password' ) VALUES (''{0}'',''{1}'');", name, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate(comanda);
            return true;
        } catch (Exception e) {
            System.out.println("The  username "+ name + " is already taken");
        }
        return false;
    }
}
