package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import dao.Ligne_commandeDAO;
import Model.Ligne_commande;
import java.awt.*;
import java.util.List;

public class Gestion_Lignes_Commandes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField id_ligneField;
    private JTextField quantiteField;
    private JTextField num_tableField;
    private JTextField prix_uniField;
    private JTextField remarquesField;
    private JTextField num_cField;
    private JTable table;
    private Ligne_commandeDAO ligne_commandeDAO;
    private Table_ligne_commande modelLigneCommande;

    @SuppressWarnings({ "null", "unused" })
	public Gestion_Lignes_Commandes() {
        setTitle("Gestion des Lignes de Commande");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1057, 677);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Lignes Commande");
        lblTitle.setForeground(new Color(135, 206, 235));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(350, 30, 400, 50);
        contentPane.add(lblTitle);

        id_ligneField = createLabeledField("id_ligne", 50, 100);
        quantiteField = createLabeledField("quantite", 200, 100);
        num_tableField = createLabeledField("num_table", 350, 100);
        prix_uniField = createLabeledField("prix_uni", 500, 100);
        remarquesField = createLabeledField("remarques", 650, 100);
        num_cField = createLabeledField("num_c", 800, 100);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 200, 900, 300);
        contentPane.add(scrollPane);
        Ligne_commandeDAO ligne_commandeDAO = null ;

        modelLigneCommande = new Table_ligne_commande();
        try {
            List<Ligne_commande> lignes = ligne_commandeDAO.getAll();
            modelLigneCommande.loadData(lignes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement : " + e.getMessage());
        }

        table = new JTable(modelLigneCommande);
        scrollPane.setViewportView(table);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Ligne_commande ligne = modelLigneCommande.getLigneCommandeAt(selectedRow);
                    id_ligneField.setText(String.valueOf(ligne.getId_ligne()));
                    quantiteField.setText(String.valueOf(ligne.getQuantite()));
                    num_tableField.setText(String.valueOf(ligne.getNum_table()));
                    prix_uniField.setText(String.valueOf(ligne.getPrix_uni()));
                    remarquesField.setText(ligne.getRemarques());
                    num_cField.setText(String.valueOf(ligne.getNum_c()));
                }
            }
        });

        JButton btnAjouter = createButton("Ajouter", 800, 250, Color.BLUE);
        btnAjouter.addActionListener(e -> {
            try {
                Ligne_commande l = getLigneFromFields();
                ligne_commandeDAO.ajouterLigneCommande(l);
                reloadLigneCommandeData();
                JOptionPane.showMessageDialog(null, "Ligne ajoutée !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        contentPane.add(btnAjouter);

        JButton btnModifier = createButton("Modifier", 800, 310, Color.ORANGE);
        btnModifier.addActionListener(e -> {
            try {
                Ligne_commande l = getLigneFromFields();
                ligne_commandeDAO.modifierLigneCommande(l);
                reloadLigneCommandeData();
                JOptionPane.showMessageDialog(null, "Ligne modifiée !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        contentPane.add(btnModifier);

        JButton btnSupprimer = createButton("Supprimer", 800, 370, Color.RED);
        btnSupprimer.addActionListener(e -> {
            try {
                int id = Integer.parseInt(id_ligneField.getText());
                ligne_commandeDAO.supprimerLigneCommande(id);
                reloadLigneCommandeData();
                JOptionPane.showMessageDialog(null, "Ligne supprimée !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        contentPane.add(btnSupprimer);

        JButton btnRetour = createButton("Retour", 50, 530, Color.BLACK);
        btnRetour.addActionListener(e -> {
            setVisible(false);
            new Interface_Serveuse().setVisible(true);
        });
        contentPane.add(btnRetour);

        JButton btnExit = createButton("Exit", 850, 530, Color.BLACK);
        btnExit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Confirmer la fermeture ?", "Quitter", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        });
        contentPane.add(btnExit);
    }

    private JTextField createLabeledField(String label, int x, int y) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl.setBounds(x, y, 100, 25);
        contentPane.add(lbl);
        JTextField field = new JTextField();
        field.setBounds(x, y + 30, 120, 25);
        contentPane.add(field);
        return field;
    }

    private JButton createButton(String text, int x, int y, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        btn.setBounds(x, y, 140, 40);
        btn.setForeground(color);
        return btn;
    }

    private Ligne_commande getLigneFromFields() {
        int id = Integer.parseInt(id_ligneField.getText());
        int quantite = Integer.parseInt(quantiteField.getText());
        int table = Integer.parseInt(num_tableField.getText());
        double prix = Double.parseDouble(prix_uniField.getText());
        String remarques = remarquesField.getText();
        int numC = Integer.parseInt(num_cField.getText());
        return new Ligne_commande(id, quantite, table, prix, remarques, numC, null, null);
    }

    private void reloadLigneCommandeData() {
        try {
            modelLigneCommande.loadData(ligne_commandeDAO.getAll());
            table.setModel(modelLigneCommande);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de rafraîchissement : " + e.getMessage());
        }
    }
}