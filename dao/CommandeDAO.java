package dao;


import Model.Ligne_commande;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Commande;


public  class CommandeDAO {
    public void ajouterCommande(Commande commande) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "INSERT INTO commandes (num_c, date_c, statut) VALUES (?, NOW(), ?)";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, commande.getNum_c());
        ps.setString(2, commande.getStatut());
        ps.executeUpdate();
    }
    public List<Commande> getToutesLesCommandes() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        @SuppressWarnings("unused")
		List<Ligne_commande>ligne_com=new ArrayList<>();
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT * FROM commandes";

        Statement st = cx.createStatement();
        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            commandes.add(new Commande(rs.getInt("num_c"),rs.getTimestamp("date_c"),rs.getInt("total_c"),rs.getString("statut"),((Commande) rs).getLigne_com()));
        }
        return commandes;
    }
    public List<Commande> getCommandesParStatut(String statut) throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT * FROM commandes WHERE statut = ?";

        PreparedStatement ps = cx.prepareStatement(req);
        ps.setString(1, statut);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            commandes.add(new Commande(rs.getInt("num_c"),rs.getTimestamp("date_c"),rs.getInt("total_c"),rs.getString("statut"),((Commande) rs).getLigne_com()));
        }
        return commandes;
    }
    public void changerStatutCommande(int idCommande, String nouveauStatut) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "UPDATE commandes SET statut = ? WHERE id = ?";

        PreparedStatement ps = cx.prepareStatement(req);
        ps.setString(1, nouveauStatut);
        ps.setInt(2, idCommande);
        ps.executeUpdate();
    }
    public void supprimerCommande(int num) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "DELETE FROM commandes WHERE id = ?";
        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, num);
        ps.executeUpdate();
    }
    public Commande getCommandeParnum_c(int num_c) throws SQLException {
        Connection cx = SingletonConnection.getInstance();
        String req = "SELECT * FROM commandes WHERE num_c = ?";

        PreparedStatement ps = cx.prepareStatement(req);
        ps.setInt(1, num_c);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Commande(rs.getInt("num_c"),rs.getTimestamp("date_c"),rs.getInt("total_c"),rs.getString("statut"),((Commande) rs).getLigne_com());
        }
        return null;
    }
    public void modifier_commande(Commande c) {
        String sql = "UPDATE commandes SET date_c = ?, total_c = ?, statut = ? WHERE num_c = ?";
        try (Connection con = SingletonConnection.getInstance();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(c.getDate_c().getTime()));
            ps.setInt(2, c.getTotal_c());
            ps.setString(3, c.getStatut());
            ps.setInt(4, c.getNum_c());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Aucune commande modifiée, commande non trouvée !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la mise à jour de la commande : " + e.getMessage());
        }
    }

}