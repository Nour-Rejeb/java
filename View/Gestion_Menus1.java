package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.MenuDAO;
import Model.Menu;
import Model.Plat;
import java.awt.Font;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gestion_Menus1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField idMenuField;
    private JTextField nomMenuField;
    private JTable table;
    private MenuDAO menuDAO;
    private Table_Menus modelMenu;

    private void reloadMenuData() throws SQLException {
        modelMenu.loadData(menuDAO.getTousLesMenus());
        table.setModel(modelMenu);
    }

    @SuppressWarnings("unused")
	public Gestion_Menus1() {
        setTitle("Menus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1057, 677);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        menuDAO = new MenuDAO();  // ⚠️ Instanciation nécessaire

        JLabel lblTitle = new JLabel("Menus");
        lblTitle.setIcon(new ImageIcon(Gestion_Menus1.class.getResource("/images/client.png")));
        lblTitle.setForeground(new Color(135, 206, 235));
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTitle.setBounds(404, 51, 337, 64);
        contentPane.add(lblTitle);

        JLabel lblIdMenu = new JLabel("ID Menu");
        lblIdMenu.setForeground(new Color(72, 61, 139));
        lblIdMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblIdMenu.setBounds(74, 158, 110, 39);
        contentPane.add(lblIdMenu);

        idMenuField = new JTextField();
        idMenuField.setBounds(74, 207, 96, 32);
        contentPane.add(idMenuField);
        idMenuField.setColumns(10);

        JLabel lblNomMenu = new JLabel("Nom Menu");
        lblNomMenu.setForeground(new Color(72, 61, 139));
        lblNomMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNomMenu.setBounds(336, 158, 130, 39);
        contentPane.add(lblNomMenu);

        nomMenuField = new JTextField();
        nomMenuField.setBounds(348, 207, 96, 32);
        contentPane.add(nomMenuField);
        nomMenuField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(57, 260, 684, 303);
        contentPane.add(scrollPane);

        modelMenu = new Table_Menus();
        try {
            modelMenu.loadData(menuDAO.getTousLesMenus());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table = new JTable(modelMenu);
        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idMenuField.getText());
                String nom = nomMenuField.getText();
                Menu menu = new Menu(id, nom, new ArrayList<Plat>());
                if (menuDAO.ajouterMenu(menu)) {
                    JOptionPane.showMessageDialog(null, "Menu Ajouté");
                    reloadMenuData();
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du Menu.");
                }
                idMenuField.setText("");
                nomMenuField.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        btnAjouter.setForeground(new Color(139, 0, 0));
        btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnAjouter.setBounds(839, 269, 145, 39);
        contentPane.add(btnAjouter);

        JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idMenuField.getText());
                String nom = nomMenuField.getText();
                Menu menu = new Menu(id, nom, new ArrayList<Plat>());
                if (menuDAO.modifierMenu(menu)) {
                    JOptionPane.showMessageDialog(null, "Menu Modifié");
                    reloadMenuData();
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la modification du Menu.");
                }
                idMenuField.setText("");
                nomMenuField.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        btnModifier.setForeground(new Color(139, 0, 0));
        btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnModifier.setBounds(839, 332, 145, 39);
        contentPane.add(btnModifier);

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idMenuField.getText());
                if (menuDAO.supprimerMenu(id)) {
                    JOptionPane.showMessageDialog(null, "Menu Supprimé");
                    reloadMenuData();
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du Menu.");
                }
                idMenuField.setText("");
                nomMenuField.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
            }
        });
        btnSupprimer.setForeground(new Color(139, 0, 0));
        btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSupprimer.setBounds(839, 400, 145, 39);
        contentPane.add(btnSupprimer);

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            setVisible(false);
            new Interface_Serveuse().setVisible(true);
        });
        btnRetour.setForeground(new Color(34, 139, 34));
        btnRetour.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnRetour.setBounds(39, 581, 131, 39);
        contentPane.add(btnRetour);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Confirmez si vous voulez fermer la fenêtre", "fermer", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        });
        btnExit.setForeground(new Color(255, 0, 0));
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnExit.setBounds(867, 581, 117, 39);
        contentPane.add(btnExit);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        idMenuField.setText(table.getValueAt(selectedRow, 0).toString());
                        nomMenuField.setText(table.getValueAt(selectedRow, 1).toString());
                    }
                }
            }
        });
    }
}