package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

import dao.UtilisateurDAO;
import Model.*;

public class Login_restaurant extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField login;
    private JPasswordField mdp;
    private JFrame fermer_Login;
    private static String Log;
    private static String password;

    public static String getLog() {
        return Log;
    }

    public static String getPassword() {
        return password;
    }

  

    public Login_restaurant() {
        initialize();
    }

    private void initialize() {
 
    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 781, 500);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Login restaurant");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(150, 20, 480, 50);
        getContentPane().add(lblNewLabel);

        JLabel login_lbl = new JLabel("Login");
        login_lbl.setFont(new Font("Tahoma", Font.BOLD, 28));
        login_lbl.setHorizontalAlignment(SwingConstants.CENTER);
        login_lbl.setBounds(100, 110, 180, 40);
        getContentPane().add(login_lbl);

        JLabel passwrd_lbl = new JLabel("Mot de passe");
        passwrd_lbl.setFont(new Font("Tahoma", Font.BOLD, 28));
        passwrd_lbl.setHorizontalAlignment(SwingConstants.CENTER);
        passwrd_lbl.setBounds(50, 180, 230, 40);
        getContentPane().add(passwrd_lbl);

        login = new JTextField();
        login.setFont(new Font("Tahoma", Font.BOLD, 22));
        login.setBounds(310, 115, 320, 35);
        getContentPane().add(login);
        login.setColumns(10);

        mdp = new JPasswordField();
        mdp.setFont(new Font("Tahoma", Font.BOLD, 22));
        mdp.setBounds(310, 185, 320, 35);
        getContentPane().add(mdp);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnLogin.setForeground(new Color(50, 205, 50));
        btnLogin.setBounds(39, 334, 140, 54);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                password= new String(mdp.getPassword());  
                String loginInput = login.getText();

                UtilisateurDAO u = new UtilisateurDAO();
                Utilisateur utilisateur = null;
                try {
                    utilisateur = u.se_connecter(loginInput, password);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                Log = loginInput;

                if (utilisateur != null) {
                 
                    System.out.println(utilisateur.getType());
                   if (utilisateur.getType()=="client") {
						try {
							Profil n=new Profil();
							n.setVisible(true);
							 System.out.println(utilisateur.getType());	
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                   }
//                   if (utilisateur.getType()=="serveuse") {
//                	   try {
//						new Profil1().setVisible(true);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//                  }
//                   if (utilisateur.getType()=="cuisinier") {
//                	   try {
//						new Profil2().setVisible(true);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//                  }
//                       
//                        
                  
                } else {
                    JOptionPane.showMessageDialog(null, "Identifiants invalides", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
                    mdp.setText(null);
                    login.setText(null);
                }
            }
        });
        getContentPane().add(btnLogin);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnReset.setBounds(311, 334, 140, 54);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login.setText(null);
                mdp.setText(null);
            }
        });
        getContentPane().add(btnReset);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnExit.setForeground(new Color(255, 0, 0));
        btnExit.setBounds(575, 334, 140, 54);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fermer_Login = new JFrame("Exit");
                if (JOptionPane.showConfirmDialog(fermer_Login, "Confirmez si vous voulez fermer la fenetre", "Login", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        getContentPane().add(btnExit);

      
    }
}
