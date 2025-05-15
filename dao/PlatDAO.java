package dao;
import java.sql.*;
import java.util.*;

import Model.Plat;

public  class PlatDAO {
	public static List<Plat> getPlatsParMenu(int id_menu) throws SQLException {
	    List<Plat> plats = new ArrayList<>();
	    Connection cx = SingletonConnection.getInstance();
	    String query = "SELECT * FROM plats WHERE id_menu = ?";
	    PreparedStatement px = cx.prepareStatement(query);
	    px.setInt(1, id_menu);
	    ResultSet rs = px.executeQuery();

	    while (rs.next()) {
	        plats.add(new Plat(rs.getInt("id"), rs.getString("nom_p"), rs.getString("description"), rs.getString("disponibilite"), rs.getDouble("prix")));
	    }
	    return plats;
	}

    public boolean ajouterPlat(Plat plat) throws SQLException {
    	Connection cx = SingletonConnection.getInstance();
        String req = "INSERT INTO plats ( id,nom_p, prix,description,disponibilite) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, plat.getId());
        ps.setString(2, plat.getNom_p());
        ps.setDouble(3, plat.getPrix());
        ps.setString(4, plat.getDescription());
        ps.setString(5, plat.getDisponibilite());
        ps.executeUpdate();
		return false;
    }

    public boolean modifierPlat(Plat plat) throws SQLException {
    	Connection cx = SingletonConnection.getInstance();
        String req = "UPDATE plats SET nom_p = ?, prix = ?, description= ?,disponibilite= ? WHERE id = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setString(2, plat.getNom_p());
        ps.setDouble(3, plat.getPrix());
        ps.setString(4, plat.getDescription());
        ps.setString(5, plat.getDisponibilite());
        ps.executeUpdate();
		return false;
    }

    public boolean supprimerPlat(int id) throws SQLException {
    	Connection cx = SingletonConnection.getInstance();
        String req= "DELETE FROM plats WHERE id = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
		return false;
    }
    public static List<Plat> getPlatsParLigne(int id_ligne) throws SQLException {
        List<Plat> plats = new ArrayList<>();
        Connection cx = SingletonConnection.getInstance();
        
        String query = "SELECT p.* FROM plats p " +
                       "JOIN ligne_plat lp ON p.id = lp.id_plat " +
                       "WHERE lp.id_ligne = ?";
        
        PreparedStatement ps = cx.prepareStatement(query);
        ps.setInt(1, id_ligne);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Plat plat = new Plat(
                rs.getInt("id"),
                rs.getString("nom_p"),
                rs.getString("description"),
                rs.getString("disponibilite"),
                rs.getDouble("prix")
            );
            plats.add(plat);
        }
        return plats;
    }
}
