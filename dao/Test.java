package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/gestion_restaurant";
        String utilisateur = "root";
        String motDePasse = "NourRejeb1*";

        try {
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            if (connexion != null) {
                System.out.println("✅ Connexion réussie à la base de données !");
                connexion.close(); 
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur de connexion !");
            e.printStackTrace();
        }
    }
}
