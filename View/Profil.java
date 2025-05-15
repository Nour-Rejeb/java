package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import Model.Utilisateur;
import dao.UtilisateurDAO;

public class Profil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cin;
	private JTextField nom;
	private JTextField prénom;
	private JTextField type;
	private UtilisateurDAO userDAO;


	public Profil() throws SQLException {
		setVisible(true);
		setTitle("Profil Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblProfile = new JLabel("Profil");
		lblProfile.setIcon(new ImageIcon(Profil.class.getResource("/images/user (4).png")));
		lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfile.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblProfile.setBounds(398, 35, 282, 70);
		contentPane.add(lblProfile);

		JLabel lblCin = new JLabel("CIN");
		lblCin.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCin.setForeground(new Color(128, 0, 128));
		lblCin.setBounds(202, 135, 182, 44);
		contentPane.add(lblCin);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNom.setForeground(new Color(128, 0, 128));
		lblNom.setBounds(202, 211, 182, 44);
		contentPane.add(lblNom);

		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPrenom.setForeground(new Color(128, 0, 128));
		lblPrenom.setBounds(202, 291, 182, 44);
		contentPane.add(lblPrenom);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblType.setForeground(new Color(128, 0, 128));
		lblType.setBounds(202, 369, 182, 44);
		contentPane.add(lblType);

		cin = new JTextField();
		cin.setEditable(false);
		cin.setFont(new Font("Tahoma", Font.BOLD, 17));
		cin.setBounds(669, 135, 336, 44);
		contentPane.add(cin);
		cin.setColumns(10);

		nom = new JTextField();
		nom.setEditable(false);
		nom.setFont(new Font("Tahoma", Font.BOLD, 17));
		nom.setBounds(669, 211, 336, 44);
		contentPane.add(nom);
		nom.setColumns(10);

		prénom = new JTextField();
		prénom.setEditable(false);
		prénom.setFont(new Font("Tahoma", Font.BOLD, 17));
		prénom.setBounds(669, 291, 336, 44);
		contentPane.add(prénom);
		prénom.setColumns(10);

		type = new JTextField();
		type.setEditable(false);
		type.setFont(new Font("Tahoma", Font.BOLD, 17));
		type.setBounds(669, 369, 336, 44);
		contentPane.add(type);
		type.setColumns(10);

		userDAO = new UtilisateurDAO();
		String login = Login_restaurant.getLog();
		String mdp = Login_restaurant.getPassword();
		Utilisateur u = userDAO.se_connecter(login, mdp);

		if (u != null) {
			cin.setText(String.valueOf(u.getCin()));
			nom.setText(u.getNom());
			prénom.setText(u.getPrénom()); 
			type.setText(u.getType());
		} else {
			JOptionPane.showMessageDialog(this, "Erreur : utilisateur non trouvé", "Erreur", JOptionPane.ERROR_MESSAGE);
		}

		JButton btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnRetour.setForeground(new Color(64, 128, 128));
		btnRetour.setBounds(98, 645, 207, 44);
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Interface_Client().setVisible(true);
			}
		});
		contentPane.add(btnRetour);

		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnExit.setForeground(new Color(255, 0, 0));
		btnExit.setBounds(716, 645, 207, 44);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Confirmez si vous voulez fermer la fenêtre", "fermer", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		contentPane.add(btnExit);
	}
}
