import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField un;
	private JPasswordField pf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("login");
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame f= new JFrame();
		
		setBounds(100, 100, 1929, 1042);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name :");
		lblUserName.setForeground(new Color(240, 255, 240));
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblUserName.setBounds(950, 227, 228, 54);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(new Color(240, 255, 240));
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblPassword.setBounds(980, 317, 215, 54);
		contentPane.add(lblPassword);
		
		un = new JTextField();
		un.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		un.setBounds(1190, 230, 319, 54);
		contentPane.add(un);
		un.setColumns(10);
		
		pf = new JPasswordField();
		pf.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		pf.setBounds(1190, 317, 319, 54);
		contentPane.add(pf);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String u=un.getText();
				@SuppressWarnings("deprecation")
				String p=pf.getText();
				if(u.equals("system") && p.equals("system")) {
					JOptionPane.showMessageDialog(f,"Login Success");
					TransportHome frame = new TransportHome();
					frame.setVisible(true);
					dispose();
				}
				else JOptionPane.showMessageDialog(f, "Invalid Username/Password!");
				un.setText(null);
				pf.setText(null);
			}
		});
		btnSignIn.setFont(new Font("Times New Roman", Font.BOLD, 36));
		btnSignIn.setBounds(1190, 441, 174, 60);
		contentPane.add(btnSignIn);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected()) {
					pf.setEchoChar((char)0);
				}
				else pf.setEchoChar('*');
			}
		});
		chckbxNewCheckBox.setBackground(new Color(245, 245, 220));
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 24));
		chckbxNewCheckBox.setBounds(1461, 393, 193, 31);
		contentPane.add(chckbxNewCheckBox);
		
		/*JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("D:\\Downloads\\6.jpg"));
		label.setBounds(-11, -38, 1948, 1051);
		contentPane.add(label);*/
	}
}
