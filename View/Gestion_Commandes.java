package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import dao.CommandeDAO;
import Model.Commande;

import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Gestion_Commandes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField num_cField;
    private JTextField date_cField;
    private JTextField total_cField;
    private JTextField statutField;
    private JTable table;
    private CommandeDAO commandeDAO;
    private Table_Commandes model_Commande;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressWarnings("unused")
	public Gestion_Commandes() {
        setTitle("Gestion Commandes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1057, 677);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        commandeDAO = new CommandeDAO(); 

        JLabel lblTitle = new JLabel("Commandes");
        lblTitle.setForeground(new Color(135, 206, 235));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(404, 51, 337, 64);
        contentPane.add(lblTitle);

        JLabel lblNum = new JLabel("num_c");
        lblNum.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNum.setBounds(74, 158, 110, 39);
        contentPane.add(lblNum);

        num_cField = new JTextField();
        num_cField.setBounds(74, 207, 96, 32);
        contentPane.add(num_cField);

        JLabel lblDate = new JLabel("date_c");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDate.setBounds(230, 158, 130, 39);
        contentPane.add(lblDate);

        date_cField = new JTextField();
        date_cField.setBounds(230, 207, 120, 32);
        contentPane.add(date_cField);

        JLabel lblTotal = new JLabel("total_c");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTotal.setBounds(400, 158, 130, 39);
        contentPane.add(lblTotal);

        total_cField = new JTextField();
        total_cField.setBounds(400, 207, 120, 32);
        contentPane.add(total_cField);

        JLabel lblStatut = new JLabel("statut");
        lblStatut.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblStatut.setBounds(570, 158, 130, 39);
        contentPane.add(lblStatut);

        statutField = new JTextField();
        statutField.setBounds(570, 207, 120, 32);
        contentPane.add(statutField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(57, 260, 684, 303);
        contentPane.add(scrollPane);

        model_Commande = new Table_Commandes();
        try {
            model_Commande.loadData(commandeDAO.getToutesLesCommandes());
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, "Erreur chargement des données : " + e1.getMessage());
        }

        table = new JTable(model_Commande);
        scrollPane.setViewportView(table);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Commande commande = model_Commande.getCommandeAt(selectedRow);
                num_cField.setText(String.valueOf(commande.getNum_c()));
                date_cField.setText(dateFormat.format(commande.getDate_c()));
                total_cField.setText(String.valueOf(commande.getTotal_c()));
                statutField.setText(commande.getStatut());
            }
        });

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setBounds(800, 260, 145, 39);
        btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnAjouter.setForeground(Color.BLUE);
        btnAjouter.addActionListener(e -> {
            try {
                int num = Integer.parseInt(num_cField.getText());
                Date date = dateFormat.parse(date_cField.getText());
                int total = Integer.parseInt(total_cField.getText());
                String statut = statutField.getText();
                Commande commande = new Commande(num, date, total, statut, new ArrayList<>());
                commandeDAO.ajouterCommande(commande);
                reloadCommandeData();
                JOptionPane.showMessageDialog(null, "Commande ajoutée !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        contentPane.add(btnAjouter);

        JButton btnModifier = new JButton("Modifier");
        btnModifier.setBounds(800, 320, 145, 39);
        btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnModifier.setForeground(Color.ORANGE);
        btnModifier.addActionListener(e -> {
            try {
                int num = Integer.parseInt(num_cField.getText());
                Date date = dateFormat.parse(date_cField.getText());
                int total = Integer.parseInt(total_cField.getText());
                String statut = statutField.getText();
                Commande c = new Commande(num, date, total, statut, new ArrayList<>());
                commandeDAO.modifier_commande(c);
                reloadCommandeData();
                JOptionPane.showMessageDialog(null, "Commande modifiée !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        contentPane.add(btnModifier);

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setBounds(800, 380, 145, 39);
        btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSupprimer.setForeground(Color.RED);
        btnSupprimer.addActionListener(e -> {
            try {
                int num = Integer.parseInt(num_cField.getText());
                Date date = dateFormat.parse(date_cField.getText());
                int total = Integer.parseInt(total_cField.getText());
                String statut = statutField.getText();
                int num1 = 0 ;
                commandeDAO.supprimerCommande(num1);
                reloadCommandeData();
                JOptionPane.showMessageDialog(null, "Commande supprimée !");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        contentPane.add(btnSupprimer);

        JButton btnRetour = new JButton("Retour");
        btnRetour.setBounds(39, 581, 131, 39);
        btnRetour.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnRetour.addActionListener(e -> {
            setVisible(false);
            new Interface_Client().setVisible(true);
        });
        contentPane.add(btnRetour);

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(867, 581, 117, 39);
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnExit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Confirmer la fermeture ?", "Quitter", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        });
        contentPane.add(btnExit);
    }

    private void reloadCommandeData() throws SQLException {
        model_Commande.loadData(commandeDAO.getToutesLesCommandes());
        table.setModel((TableModel) model_Commande);
    }

}