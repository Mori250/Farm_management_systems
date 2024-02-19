package sales;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class sales_entity extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField pname;
	private JTextField quantity;
	private JTextField unit_price;
	private JTable table_1;
	private JButton btnNewButton_1;
	private JTextField prison_id;
	private JTable table;
	private JTextField purchase_date;
	
	
	public void table_load() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmer_management_system","root","");
			Statement st = con.createStatement();
			String query= "select * from sales";
			ResultSet rs= st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();	
			
			model.setRowCount(0);
			
			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i = 0; i < cols; i++)
				colName[i] = rsmd.getColumnName(i + 1);
			model.setColumnIdentifiers(colName);
			String purchase_id,product_name,quantity,unit_price,total_price,purchase_date;
		
			while(rs.next()) {
				purchase_id = rs.getString(1);
				product_name = rs.getString(2);
				quantity = rs.getString(3);
				unit_price = rs.getString(4);
				total_price = rs.getString(5);
				purchase_date = rs.getString(6);

	            
				String[] row = {purchase_id,product_name,quantity,unit_price,total_price,purchase_date};
				model.addRow(row);
			}
			
		} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sales_entity frame = new sales_entity();
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
	

	

	
	public sales_entity() {
		table_1 = new JTable();
		table_1.setBounds(0, 0, 0, 0);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product name :");
		lblNewLabel.setBounds(111, 60, 128, 14);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		pname = new JTextField();
		pname.setBounds(228, 47, 400, 38);
		contentPane.add(pname);
		pname.setColumns(10);
		
		JLabel lblPrisonDistrict = new JLabel("Quantity :");
		lblPrisonDistrict.setBounds(112, 120, 161, 14);
		lblPrisonDistrict.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblPrisonDistrict);
		
		quantity = new JTextField();
		quantity.setBounds(228, 107, 400, 38);
		quantity.setColumns(10);
		contentPane.add(quantity);
		
		JLabel lblPrisonDistrict_1 = new JLabel("Unit price :");
		lblPrisonDistrict_1.setBounds(112, 182, 161, 14);
		lblPrisonDistrict_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		contentPane.add(lblPrisonDistrict_1);
		
		unit_price = new JTextField();
		unit_price.setBounds(228, 171, 400, 38);
		unit_price.setColumns(10);
		contentPane.add(unit_price);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(111, 324, 121, 38);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sales pur = new sales();
				if(e.getSource()==btnNewButton) {
					pur.setProduct_name(pname.getText());
					pur.setQuantity(Double.parseDouble(quantity.getText()));
					pur.setUnit_price(Double.parseDouble(unit_price.getText()));
					pur.setSales_date(purchase_date.getText());
//					pur.setUnit_price(Double.parseDouble(purchase_date.getText()));
					pur.insertData();
					
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton);
		
		contentPane.add(table_1);

		btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			sales pur = new sales();
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==btnNewButton_1) {
				int id=Integer.parseInt(prison_id.getText());
				pur.delete(id);
				}
			}
		});
		btnNewButton_1.setBounds(246, 322, 121, 38);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("UPDATE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sales pur = new sales();
				if(e.getSource()==btnNewButton_1_1) {
					int id=Integer.parseInt(prison_id.getText());
					pur.setProduct_name(pname.getText());
					pur.setQuantity(Double.parseDouble(quantity.getText()));
					pur.setUnit_price(Double.parseDouble(unit_price.getText()));
					pur.setSales_date(purchase_date.getText());
					pur.update(id);
				}
			}
		});
		btnNewButton_1_1.setBounds(389, 318, 108, 38);
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(674, 47, 517, 224);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton_1_1_1 = new JButton("VIEW");
		btnNewButton_1_1_1.setBounds(525, 315, 101, 38);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton_1_1_1) {
					table_load();
				}
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		contentPane.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("SALES");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setBounds(541, 0, 148, 26);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(678, 302, 515, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Employee ID :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(4, 1, 115, 38);
		panel.add(lblNewLabel_2);
		
		prison_id = new JTextField();
		prison_id.setBounds(110, 4, 395, 38);
		panel.add(prison_id);
		prison_id.setColumns(10);
		
		table = new JTable();
		table.setBounds(147, 367, 1, 1);
		contentPane.add(table);
		
		purchase_date = new JTextField();
		purchase_date.setColumns(10);
		purchase_date.setBounds(228, 233, 400, 38);
		contentPane.add(purchase_date);
		
		JLabel lblPrisonDistrict_1_1_1 = new JLabel("Sales date :");
		lblPrisonDistrict_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrisonDistrict_1_1_1.setBounds(112, 245, 161, 14);
		contentPane.add(lblPrisonDistrict_1_1_1);
	}
}
