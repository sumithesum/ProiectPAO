package LoginRegister;

import Clase.User;


import java.sql.*;
import java.text.MessageFormat;
import java.util.Scanner;

public class LoginRegister implements LoginRegisterI {
    @Override
    public User login() {
        try {
            Scanner scanner = new Scanner(System.in);
            User user = null;
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Enter your password: ");
            String pass = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");


            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE LOWER( username) =  LOWER('" + name + "') AND LOWER(password) = LOWER('" + pass + "');");

            resultSet.next();
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            boolean isAdmin = resultSet.getString("admin").equals("1");

            user = new User(username, password, isAdmin);

            if (resultSet.getString("admin").equals("1")) {
                System.out.println("Welcome admin " + username);
                return user;
            } else {
                System.out.println("Welcome user " + username);
                return user;
            }


        } catch (SQLException e) {
            System.out.println("The username or password is incorrect");
            return null;

        }

    }


    @Override
    public User register() {
        try {
            Scanner scanner = new Scanner(System.in);
            User user = null;
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Enter your password: ");
            String pass = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", "root", "+++xela1");
            String comanda = MessageFormat.format("INSERT INTO `login`.`user`('username','password' ) VALUES (''{0}'',''{1}'');", name, pass);
            Statement statement = connection.createStatement();

            statement.executeUpdate(comanda);


            user = new User(name, pass);


            System.out.println("Welcome user " + name);
            return user;


        } catch (SQLException e) {
            System.out.println("The  username is already taken");
            return null;

        }
    }
}

