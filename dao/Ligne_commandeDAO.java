package dao;

import java.sql.*;
import java.util.*;

import Model.Ligne_commande;
import Model.Plat;
import Model.Commande;

public  class Ligne_commandeDAO {

     PlatDAO platDAO = new PlatDAO();
     CommandeDAO commandeDAO = new CommandeDAO();

     
     
    public Ligne_commandeDAO(PlatDAO platDAO, CommandeDAO commandeDAO) {
		super();
		this.platDAO = platDAO;
		this.commandeDAO = commandeDAO;
	}

	public void ajouterLigneCommande(Ligne_commande ligne) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "INSERT INTO ligne_commande (id_ligne,quantite,num_table,prix_uni,remarques)VALUES(?,?,?,?,?)";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, ligne.getQuantite());
        ps.setInt(2, ligne.getNum_table());
        ps.setDouble(3, ligne.getPrix_uni());
        ps.setString(4, ligne.getRemarques());
        ps.setInt(5, ligne.getNum_c());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            int id_ligne = keys.getInt(1);
            for (Plat p : ligne.getPlt()) {
                ajouterPlatALigne(id_ligne, p.getId());
            }
        }
        ps.close();
    }

    public Ligne_commande getLigneCommandeById(int id_ligne) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT * FROM ligne_commande WHERE id_ligne = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, id_ligne);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int quantite = rs.getInt("quantite");
            int num_table = rs.getInt("num_table");
            double prix_uni = rs.getDouble("prix_uni");
            String remarques = rs.getString("remarques");
            int num_c = rs.getInt("num_c");

            List<Plat> plats = getPlatsParLigne(id_ligne);
            List<Commande> commandes = new ArrayList<>();
            commandes.add(commandeDAO.getCommandeParnum_c(num_c));

            return new Ligne_commande(id_ligne, quantite, num_table, prix_uni, remarques, num_c, plats, commandes);
        }

        return null;
    }

    public List<Ligne_commande> getLignesCommandeByNumC(int num_c) throws SQLException {
        List<Ligne_commande> lignes = new ArrayList<>();
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT * FROM ligne_commande WHERE num_c = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, num_c);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id_ligne = rs.getInt("id_ligne");
            int quantite = rs.getInt("quantite");
            int num_table = rs.getInt("num_table");
            double prix_uni = rs.getDouble("prix_uni");
            String remarques = rs.getString("remarques");

            List<Plat> plats = getPlatsParLigne(id_ligne);
            List<Commande> commandes = new ArrayList<>();
            commandes.add(commandeDAO.getCommandeParnum_c(num_c));

            lignes.add(new Ligne_commande(id_ligne, quantite, num_table, prix_uni, remarques, num_c, plats, commandes));
        }

        ps.close();
        rs.close();
        return lignes;
    }

    public void modifierLigneCommande(Ligne_commande ligne) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "UPDATE ligne_commande SET quantite = ?, num_table = ?, prix_uni = ?, remarques = ?, num_c = ? WHERE id_ligne = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, ligne.getQuantite());
        ps.setInt(2, ligne.getNum_table());
        ps.setDouble(3, ligne.getPrix_uni());
        ps.setString(4, ligne.getRemarques());
        ps.setInt(5, ligne.getNum_c());
        ps.setInt(6, ligne.getId_ligne());
        ps.executeUpdate();
        ps.close();
    }

    public void supprimerLigneCommande(int id_ligne) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req1 = "DELETE FROM ligne_commande_plat WHERE id_ligne = ?";
        PreparedStatement ps1 = cx.prepareStatement(req1);
        ps1.setInt(1, id_ligne);
        ps1.executeUpdate();
        ps1.close();

        String req2 = "DELETE FROM ligne_commande WHERE id_ligne = ?";
        PreparedStatement ps2 = cx.prepareStatement(req2);
        ps2.setInt(1, id_ligne);
        ps2.executeUpdate();
        ps2.close();
    }

    public void ajouterPlatALigne(int id_ligne, int id_plat) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "INSERT INTO ligne_commande_plat (id_ligne, id_plat) VALUES (?, ?)";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, id_ligne);
        ps.setInt(2, id_plat);
        ps.executeUpdate();
        ps.close();
    }

    public void supprimerPlatDeLigne(int id_ligne, int id_plat) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "DELETE FROM ligne_commande_plat WHERE id_ligne = ? AND id_plat = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, id_ligne);
        ps.setInt(2, id_plat);
        ps.executeUpdate();
        ps.close();
    }

    @SuppressWarnings("static-access")
	public List<Plat> getPlatsParLigne(int id_ligne) throws SQLException {
        return platDAO.getPlatsParLigne(id_ligne);
    }

    public double calculerTotalParCommande(int num_c) throws SQLException {
        double total = 0;
        List<Ligne_commande> lignes = getLignesCommandeByNumC(num_c);
        for (Ligne_commande ligne : lignes) {
            total += ligne.getPrix_uni() * ligne.getQuantite();
        }
        return total;
    }

    public boolean ligneCommandeExiste(int id_ligne) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT 1 FROM ligne_commande WHERE id_ligne = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, id_ligne);
        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();
        ps.close();
        rs.close();
        return exists;
    }

    public void viderLignesCommande(int num_c) throws SQLException {
        List<Ligne_commande> lignes = getLignesCommandeByNumC(num_c);
        for (Ligne_commande ligne : lignes) {
            supprimerLigneCommande(ligne.getId_ligne());
        }
    }

	public List<Ligne_commande> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
} 

