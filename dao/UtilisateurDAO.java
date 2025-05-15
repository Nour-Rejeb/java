package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Utilisateur;

public class UtilisateurDAO {

    public Utilisateur se_connecter(String login, String mdp) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT * FROM utilisateurs WHERE login = ? AND mdp = ?";
        
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setString(1, login);
        ps.setString(2, mdp);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String type = rs.getString("type");
            int cin = rs.getInt("cin");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prénom");
            String loginDb = rs.getString("login");
            String mdpDb = rs.getString("mdp");
            	return new Utilisateur(cin, nom, prenom, loginDb, mdpDb, type);
    }
		return null;
            }

    public void ajouterUtilisateur(Utilisateur u) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "INSERT INTO utilisateurs (cin, nom, prénom, login, mot_de_passe, type) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, u.getCin());
        ps.setString(2, u.getNom());
        ps.setString(3, u.getPrénom());
        ps.setString(4, u.getLogin());
        ps.setString(5, u.getMdp());
        ps.setString(6, u.getType());
        ps.executeUpdate();
    }

    public void modifierUtilisateur(Utilisateur u) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
                    String reqClient = "UPDATE client SET nom=?, prénom=?, login=?, mdp=? WHERE cin=?";
                    PreparedStatement ps = cx.prepareStatement(reqClient);
                    ps.setString(1, u.getNom());
                    ps.setString(2, u.getPrénom());
                    ps.setString(3, u.getLogin());
                    ps.setString(4, u.getMdp());
                    ps.setInt(5, u.getCin());
                    ps.executeUpdate();
                }

    public void supprimerUtilisateur(int cin) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "DELETE FROM utilisateurs WHERE cin = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, cin);
        ps.executeUpdate();
    }

    public Utilisateur getUtilisateurParCin(Utilisateur u) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT * FROM utilisateurs WHERE cin = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, u.getCin());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Utilisateur(rs.getInt("cin"), rs.getString("nom"), rs.getString("prénom"), rs.getString("login"), rs.getString("mdp"), rs.getString("type"));
        }
        return null;
    }

    public List<Utilisateur> getTousLesUtilisateurs() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT * FROM utilisateurs";
        Statement s = cx.createStatement();
        ResultSet rs = s.executeQuery(req);

        while (rs.next()) {
            @SuppressWarnings("unused")
			int cin = rs.getInt("cin");
            @SuppressWarnings("unused")
			String nom = rs.getString("nom");
            @SuppressWarnings("unused")
			String prenom = rs.getString("prénom");
            @SuppressWarnings("unused")
			String login = rs.getString("login");
            @SuppressWarnings("unused")
			String mdp = rs.getString("mdp");
            @SuppressWarnings("unused")
			String type = rs.getString("type");
        return utilisateurs;
    }
		return utilisateurs;
        }

    }