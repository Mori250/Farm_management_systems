package signup;

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
import javax.swing.UIManager;

public class register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField userName;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
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
	public register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(71, 52, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(70, 77, 186, 27);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(70, 121, 82, 14);
		contentPane.add(lblNewLabel);
		
		userName = new JTextField();
		userName.setColumns(10);
		userName.setBounds(72, 148, 186, 27);
		contentPane.add(userName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPassword.setBounds(72, 192, 82, 14);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(71, 217, 186, 27);
		contentPane.add(password);
		
		JButton registerbtn = new JButton("REGISTER");
		registerbtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==registerbtn) {
					if(e.getSource()==registerbtn) {
						user ad = new user();
							ad.setUsername(userName.getText());
							ad.setPassword(password.getText());
							ad.setName(name.getText());
							ad.insertData();
						}
				}
			}
		});
		registerbtn.setBounds(72, 273, 191, 23);
		contentPane.add(registerbtn);
		
		JLabel lblNewLabel_2 = new JLabel("Register");
		lblNewLabel_2.setForeground(UIManager.getColor("MenuItem.selectionBackground"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(72, 19, 113, 20);
		contentPane.add(lblNewLabel_2);
	}
}
