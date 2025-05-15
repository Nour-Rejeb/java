package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class Interface_Client extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    @SuppressWarnings("unused")
	public Interface_Client() {
        setTitle("Interface Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1002, 694);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("Espace_Client");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(85, 107, 47));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(32, 31, 800, 71);
        contentPane.add(lblNewLabel);

        JButton btnProfil = new JButton("Profil");
        btnProfil.setBounds(232, 120, 537, 60);
        btnProfil.setIcon(new ImageIcon(Interface_Client.class.getResource("/images/user (4).png")));
        btnProfil.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnProfil.addActionListener(e -> {
            setVisible(false);
            try {
				new Profil().setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        contentPane.add(btnProfil);

        
        JButton btnPlat = new JButton("Plats");
        btnPlat.setBounds(232, 190, 537, 60);
        btnPlat.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnPlat.addActionListener(e -> {
            setVisible(false);
            new Gestion_Plats().setVisible(true);
        });
        contentPane.add(btnPlat);

        
        JButton btnCommandes = new JButton("Commandes");
        btnCommandes.setBounds(232, 260, 537, 60);
        btnCommandes.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnCommandes.addActionListener(e -> {
            setVisible(false);
            new Gestion_Commandes().setVisible(true);
        });
        contentPane.add(btnCommandes);

        
        JButton btnMenus = new JButton("Menus");
        btnMenus.setBounds(232, 330, 537, 60);
        btnMenus.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnMenus.addActionListener(e -> {
            setVisible(false);
            new Gestion_Menus().setVisible(true);
        });
        contentPane.add(btnMenus);

        
        JButton btnDeconnexion = new JButton("Déconnexion");
        btnDeconnexion.setBounds(232, 400, 537, 60);
        btnDeconnexion.setIcon(new ImageIcon(Interface_Client.class.getResource("/images/logout.png")));
        btnDeconnexion.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnDeconnexion.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment vous déconnecter ?", "Déconnexion", JOptionPane.YES_NO_OPTION) == 0) {
                setVisible(false);
                new Login_restaurant().setVisible(true);
            }
        });
        contentPane.add(btnDeconnexion);

        
        JButton btnQuitter = new JButton("");
        btnQuitter.setIcon(new ImageIcon(Interface_Client.class.getResource("/images/button.png")));
        btnQuitter.setBounds(843, 577, 72, 70);
        btnQuitter.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, "Confirmez si vous voulez fermer la fenêtre", "Fermer", JOptionPane.YES_NO_OPTION) == 0) {
                System.exit(0);
            }
        });
        contentPane.add(btnQuitter);
    }
	}


