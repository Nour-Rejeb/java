package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.Menu;
import Model.Plat;

public  class MenuDAO {
	public List<Menu> getTousLesMenus() throws SQLException {
	    List<Menu> menus = new ArrayList<>();
	    Connection cx = SingletonConnection.getInstance();
	    Statement s = cx.createStatement();
	    ResultSet rs = s.executeQuery("SELECT * FROM menus");

	    while (rs.next()) {
	        int id_menu = rs.getInt("id_menu");
	        String nom_m = rs.getString("nom_m");
	        List<Plat> plats = PlatDAO.getPlatsParMenu(id_menu); 
	        menus.add(new Menu(id_menu, nom_m, plats));
	    }
	    return menus;
	}

	public Menu getMenuById(int id_menu) throws SQLException {
	    Connection cx = SingletonConnection.getInstance();
	    String req = "SELECT * FROM menus WHERE id_menu = ?";
	    PreparedStatement ps = cx.prepareStatement(req);
	    ps.setInt(1, id_menu);
	    ResultSet rs = ps.executeQuery();

	    if (rs.next()) {
	        String nom_m = rs.getString("nom_m");
	        List<Plat> plats = PlatDAO.getPlatsParMenu(id_menu); 
	        return new Menu(id_menu, nom_m, plats);
	    }

	    return null;
	}
    public boolean ajouterMenu(Menu menu) throws SQLException {
    	Connection cx = SingletonConnection.getInstance();
        String req = "INSERT INTO menus (nom) VALUES (?)";
        PreparedStatement s = cx.prepareStatement(req);
        s.setString(1, menu.getNom_m());
        s.executeUpdate();
		return false;
    }

    public boolean modifierMenu(Menu menu) throws SQLException {
    	Connection cx = SingletonConnection.getInstance();
        String req = "UPDATE menus SET nom = ? WHERE id = ?";
        PreparedStatement s = cx.prepareStatement(req);
        s.setString(1, menu.getNom_m());
        s.setInt(2, menu.getId_menu());
        s.executeUpdate();
		return false;
    }

    public boolean supprimerMenu(int idMenu) throws SQLException {
    	Connection cx = SingletonConnection.getInstance();
        String req = "DELETE FROM menus WHERE id = ?";
        PreparedStatement s = cx.prepareStatement(req);
        s.setInt(1, idMenu);
        s.executeUpdate();
		return false;
    }
	public List<Menu> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
