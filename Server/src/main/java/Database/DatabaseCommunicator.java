package Database;

import Utilities.Logging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;

public class DatabaseCommunicator {
    // on helios
   // private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";

    // on local PC
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";

    private static Connection connection;
    private static ProductBase productBase;
    private static SecretBase users;

    public void start(){

        Logging.log(Level.INFO, "Connecting to database ....... !");
        try {
            Scanner scanner = new Scanner(System.in);
            Logging.log(Level.INFO, "Enter your account:");
            String user = scanner.nextLine();
            Logging.log(Level.INFO, "Enter your password:");
            String pass = scanner.nextLine();
            connection = DriverManager.getConnection(DB_URL, user, pass);
            users = new SecretBase(connection);
            productBase = new ProductBase(connection);

        } catch (SQLException e) {
            Logging.log(Level.INFO, "Errors occur!");

        }

        if (connection != null){
            Logging.log(Level.INFO,"Successfully connect to DataBase!");
        }
        else{
            Logging.log(Level.INFO, "Unsuccessfully connect to DataBase!");
            System.exit(0);
        }
    }

    public static ProductBase getProductBase(){
        return productBase;
    }

    public static SecretBase getUsers(){
        return users;
    }

}
