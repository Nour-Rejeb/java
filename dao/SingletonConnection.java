package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingletonConnection {
    Properties props = new Properties();
    private static String user;
    private static String password;
    private static String url;
    private static Connection connect;

    private SingletonConnection() {
        try {
            props.load(new FileInputStream("conf.properties"));
            url = props.getProperty("jdbc.url");
            user = props.getProperty("jdbc.user");
            password = props.getProperty("jdbc.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie !");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC non trouvé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion !");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier de configuration !");
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (connect == null) {
            new SingletonConnection();
        }
        return connect;
    }
}
