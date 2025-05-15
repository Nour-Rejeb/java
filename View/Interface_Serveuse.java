package View;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
public class Interface_Serveuse extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Interface_Serveuse() {
		setTitle("Espace_Serveurs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Profil");
		btnNewButton.setBounds(232, 148, 537, 88);
		btnNewButton.setIcon(new ImageIcon(Interface_Serveuse.class.getResource("/images/user (4).png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					new Profil1().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Commandes");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Gestion_Commandes().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(232, 254, 537, 88);
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Lignes_commandes");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Gestion_Lignes_Commandes().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(232, 362, 537, 88);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_31 = new JButton("Menus");
		btnNewButton_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Gestion_Menus().setVisible(true);
			}
		});
		btnNewButton_31.setBounds(232, 362, 537, 88);
		btnNewButton_31.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(btnNewButton_31);
		JButton btnNewButton_5 = new JButton("Déconnexion");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Voulez vous vraiment Déconnecter ?","Déconnecter",JOptionPane.YES_NO_OPTION)
						==0) {
		        setVisible(false);
		        new Login_restaurant().setVisible(true);
		    }
			}
		});
		btnNewButton_5.setBounds(232, 473, 537, 88);
		btnNewButton_5.setIcon(new ImageIcon(Interface_Serveuse.class.getResource("/images/logout.png")));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("Espace_serveurs");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(85, 107, 47));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(32, 31, 800, 71);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Confirmez si vous voulez fermer la fenetre","fermer",JOptionPane.YES_NO_OPTION)
						==0) {
					System.exit(0);
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Interface_Serveuse.class.getResource("/images/button.png")));
		btnNewButton_1.setBounds(843, 577, 72, 70);
		contentPane.add(btnNewButton_1);
	}
}
