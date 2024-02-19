package Home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Report.report;
import employees.employees;
import production.productions;
import purchase.purchases;
import sales.sales_entity;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
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
	public home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("PURCHASES");
		btnNewButton.setBounds(35, 11, 91, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchases ad = new purchases();
				if(e.getSource()==btnNewButton) {
					ad.main(new String[0]);
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JButton btnProduction = new JButton("PRODUCTION");
		btnProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productions ad = new productions();
				if(e.getSource()==btnProduction) {
					ad.main(new String[0]);
				}
			}
		});
		btnProduction.setBounds(148, 11, 108, 23);
		contentPane.add(btnProduction);
		
		JButton btnSales = new JButton("SALES");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sales_entity ad = new sales_entity();
				if(e.getSource()==btnSales) {
					ad.main(new String[0]);
				}
			}
		});
		btnSales.setBounds(276, 11, 108, 23);
		contentPane.add(btnSales);
		
		JButton btnProduction_1_1_1 = new JButton("REPORT");
		btnProduction_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report ad = new report();
				if(e.getSource()==btnProduction_1_1_1) {
					ad.main(new String[0]);
				}
			}
		});
		btnProduction_1_1_1.setBounds(406, 11, 108, 23);
		contentPane.add(btnProduction_1_1_1);
	}
}
