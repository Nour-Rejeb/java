package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import dao.PlatDAO;
import Model.Plat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Gestion_Plats extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField id_plat;
    private JTextField Nom_plat;
    private JTextField Description;
    private JTextField Disponibilite;
    private JTextField Prix;
    private JTable table;
    private PlatDAO platdao;
    private Table_Plats model_plat;

    @SuppressWarnings("static-access")
	private void reloadPlatData() {
        try {
            int id_menu = 0;
			model_plat.loadData(platdao.getPlatsParMenu(id_menu)); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.setModel((TableModel) model_plat);
    }

    public Gestion_Plats() {
        setTitle("Gestion des Plats");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1057, 677);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Gestion des Plats");
        lblNewLabel.setIcon(new ImageIcon(Gestion_Plats.class.getResource("/images/client.png")));
        lblNewLabel.setForeground(new Color(135, 206, 235));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(404, 51, 337, 64);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("ID Plat");
        lblNewLabel_1.setForeground(new Color(72, 61, 139));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(74, 158, 110, 39);
        contentPane.add(lblNewLabel_1);

        id_plat = new JTextField();
        id_plat.setBounds(74, 207, 96, 32);
        contentPane.add(id_plat);
        id_plat.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Nom Plat");
        lblNewLabel_1_1.setForeground(new Color(72, 61, 139));
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1.setBounds(336, 158, 130, 39);
        contentPane.add(lblNewLabel_1_1);

        Nom_plat = new JTextField();
        Nom_plat.setColumns(10);
        Nom_plat.setBounds(348, 207, 96, 32);
        contentPane.add(Nom_plat);

        JLabel lblNewLabel_1_11 = new JLabel("Description");
        lblNewLabel_1_11.setForeground(new Color(72, 61, 139));
        lblNewLabel_1_11.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_11.setBounds(336, 258, 130, 39);
        contentPane.add(lblNewLabel_1_11);

        Description = new JTextField();
        Description.setColumns(10);
        Description.setBounds(621, 258, 96, 32);
        contentPane.add(Description);

        JLabel lblNewLabel_1_111 = new JLabel("Disponibilité");
        lblNewLabel_1_111.setForeground(new Color(72, 61, 139));
        lblNewLabel_1_111.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_111.setBounds(336, 307, 130, 39);
        contentPane.add(lblNewLabel_1_111);

        Disponibilite = new JTextField();
        Disponibilite.setColumns(10);
        Disponibilite.setBounds(621, 307, 96, 32);
        contentPane.add(Disponibilite);

        JLabel lblNewLabel_1_1111 = new JLabel("Prix");
        lblNewLabel_1_1111.setForeground(new Color(72, 61, 139));
        lblNewLabel_1_1111.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1_1111.setBounds(336, 356, 130, 39);
        contentPane.add(lblNewLabel_1_1111);

        Prix = new JTextField();
        Prix.setColumns(10);
        Prix.setBounds(621, 356, 96, 32);
        contentPane.add(Prix);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(57, 409, 684, 151);
        contentPane.add(scrollPane);

        platdao = new PlatDAO();
        model_plat = new Table_Plats();
        reloadPlatData();

        table = new JTable(model_plat);
        scrollPane.setViewportView(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(id_plat.getText());
                    String nom = Nom_plat.getText();
                    String description = Description.getText();
                    String disponibilite = Disponibilite.getText();
                    double prix = Double.parseDouble(Prix.getText());

                    Plat plat = new Plat(id, nom, description, disponibilite, prix);
                    try {
						if (platdao.ajouterPlat(plat)) {
						    JOptionPane.showMessageDialog(null, "Plat ajouté !");
						    reloadPlatData();
						} else {
						    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du Plat.");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur dans la saisie des données.");
                }
            }
        });
        btnAjouter.setForeground(new Color(139, 0, 0));
        btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnAjouter.setBounds(839, 269, 145, 39);
        contentPane.add(btnAjouter);

        JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(id_plat.getText());
                    String nom = Nom_plat.getText();
                    String description = Description.getText();
                    String disponibilite = Disponibilite.getText();
                    double prix = Double.parseDouble(Prix.getText());

                    Plat plat = new Plat(id, nom, description, disponibilite, prix);
                    try {
						if (platdao.modifierPlat(plat)) {
						    JOptionPane.showMessageDialog(null, "Plat modifié !");
						    reloadPlatData();
						} else {
						    JOptionPane.showMessageDialog(null, "Erreur lors de la modification du Plat.");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur dans la saisie des données.");
                }
            }
        });
        btnModifier.setForeground(new Color(139, 0, 0));
        btnModifier.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnModifier.setBounds(839, 332, 145, 39);
        contentPane.add(btnModifier);

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(id_plat.getText());
                    try {
						if (platdao.supprimerPlat(id)) {
						    JOptionPane.showMessageDialog(null, "Plat supprimé !");
						    reloadPlatData();
						} else {
						    JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du Plat.");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur dans la saisie des données.");
                }
            }
        });
        btnSupprimer.setForeground(new Color(139, 0, 0));
        btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSupprimer.setBounds(839, 395, 145, 39);
        contentPane.add(btnSupprimer);

        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Interface_Cuisinier().setVisible(true);
            }
        });
        btnRetour.setForeground(new Color(34, 139, 34));
        btnRetour.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnRetour.setBounds(39, 581, 131, 39);
        contentPane.add(btnRetour);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Confirmez si vous voulez fermer la fenêtre", "Fermer", JOptionPane.YES_NO_OPTION) == 0) {
                    System.exit(0);
                }
            }
        });
        btnExit.setForeground(new Color(255, 0, 0));
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnExit.setBounds(867, 581, 117, 39);
        contentPane.add(btnExit);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        id_plat.setText(table.getValueAt(selectedRow, 0).toString());
                        Nom_plat.setText(table.getValueAt(selectedRow, 1).toString());
                        Description.setText(table.getValueAt(selectedRow, 2).toString());
                        Disponibilite.setText(table.getValueAt(selectedRow, 3).toString());
                        Prix.setText(table.getValueAt(selectedRow, 4).toString());
                    }
                }
            }
        });
    }
}