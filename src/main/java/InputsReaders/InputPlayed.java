package InputsReaders;

import Clase.Game;
import Clase.User;
import Search.Search;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

public class InputPlayed extends Inputs {
    /**
     * @param path
     */
    @Override
    public void CSVInput(String path) {
        List<String[]> l ;
        try {
            l = ReadCSV.readCSV(path);
            String numejoc, numeuser;
            for (String[] s : l) {
                numejoc = s[0];
                numeuser = s[1];
                inputPlayed(numejoc, numeuser);
            }
        }catch (Exception e){
            System.out.println("Error reading CSV file");

        }

    }

    /**
     * @param path
     */
    @Override
    public void JsonInput(String path) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Map<String, Object>> playeds = mapper.readValue(new File(path), new TypeReference<>() {});

            for (Map<String, Object> played : playeds) {
                String numejoc = (String) played.get("gameName");
                String numeuser = (String) played.get("userName");
                inputPlayed(numejoc, numeuser);

            }
        } catch (IOException e) {
            System.out.println("File not found or unable to read file");
        }
    }

    /**
     * @param path
     */
    @Override
    public void MyInput(String path) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                if (parts.length == 2)
                    inputPlayed(parts[0], parts[1]);

            }
        } catch (Exception e) {
            System.out.println("Error reading file myInput");
        }

    }

    public void inputPlayed(String gameName, String userName) {
        try {
            Search search = new Search();
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            User user = search.searchUser(userName);
            Game game = search.searchGame(gameName);
            if (user == null || game == null) {
                System.out.println("User or game not found");
                return;
            }
            String comanda = "INSERT INTO played (gameName, userName) VALUES ('" + gameName + "', '" + userName + "');";
            connection.createStatement().executeUpdate(comanda);
            System.out.println("User " + userName + " has played " + gameName);

        } catch (Exception e) {
            System.out.println("Error in inputing in Played");
        }
    }
}
