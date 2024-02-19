package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user.user;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class user_login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					user_login frame = new user_login();
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
	public user_login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 56, 82, 14);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(30, 84, 186, 27);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPassword.setBounds(30, 122, 82, 14);
		contentPane.add(lblPassword);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(30, 145, 186, 27);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton) {
					user ad = new user();
						ad.setUsername(textField_1.getText());
						ad.setPassword(textField_2.getText());
						System.out.print(ad.getUsername() + " " + ad.getPassword());
						ad.login();
					}
				}
			});
		btnNewButton.setBounds(30, 199, 191, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(97, 11, 82, 14);
		contentPane.add(lblNewLabel_1);
	}
}
