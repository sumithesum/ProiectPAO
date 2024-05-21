package InputsReaders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.List;

public class Inputs implements InputsI {
    @Override
    public void CSVInputUser(String path, String type) {
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
    public void JsonInputUser(String path, String type) {

    }

    @Override
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
