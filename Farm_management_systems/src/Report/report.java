package Report;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class report extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table_1;

    public void table_load() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmer_management_system", "root", "");
            Statement st = con.createStatement();
            String query = "SELECT COALESCE(p.product_name, s.product_name, pr.product_name) AS product_name, " +
                "SUM(p.quantity) AS total_quantity_purchase, AVG(p.unit_price) AS average_unit_price_purchase, " +
                "SUM(p.total_price) AS total_price_purchase, SUM(s.quantity) AS total_quantity_sales, " +
                "AVG(s.unit_price) AS average_unit_price_sales, SUM(s.total_price) AS total_price_sales, " +
                "SUM(pr.quantity) AS total_quantity_production, AVG(pr.unit_price) AS average_unit_price_production, " +
                "SUM(pr.total_price) AS total_price_production, (SUM(s.total_price) - SUM(p.total_price)) AS income " +
                "FROM purchase p LEFT JOIN sales s ON p.product_name = s.product_name " +
                "LEFT JOIN production pr ON p.product_name = pr.product_name " +
                "GROUP BY COALESCE(p.product_name, s.product_name, pr.product_name) " +
                "UNION " +
                "SELECT COALESCE(p.product_name, s.product_name, pr.product_name) AS product_name, " +
                "SUM(p.quantity) AS total_quantity_purchase, AVG(p.unit_price) AS average_unit_price_purchase, " +
                "SUM(p.total_price) AS total_price_purchase, SUM(s.quantity) AS total_quantity_sales, " +
                "AVG(s.unit_price) AS average_unit_price_sales, SUM(s.total_price) AS total_price_sales, " +
                "SUM(pr.quantity) AS total_quantity_production, AVG(pr.unit_price) AS average_unit_price_production, " +
                "SUM(pr.total_price) AS total_price_production, (SUM(s.total_price) - SUM(p.total_price)) AS income " +
                "FROM sales s LEFT JOIN purchase p ON s.product_name = p.product_name " +
                "LEFT JOIN production pr ON s.product_name = pr.product_name " +
                "GROUP BY COALESCE(p.product_name, s.product_name, pr.product_name) " +
                "UNION " +
                "SELECT COALESCE(p.product_name, s.product_name, pr.product_name) AS product_name, " +
                "SUM(p.quantity) AS total_quantity_purchase, AVG(p.unit_price) AS average_unit_price_purchase, " +
                "SUM(p.total_price) AS total_price_purchase, SUM(s.quantity) AS total_quantity_sales, " +
                "AVG(s.unit_price) AS average_unit_price_sales, SUM(s.total_price) AS total_price_sales, " +
                "SUM(pr.quantity) AS total_quantity_production, AVG(pr.unit_price) AS average_unit_price_production, " +
                "SUM(pr.total_price) AS total_price_production, (SUM(s.total_price) - SUM(p.total_price)) AS income " +
                "FROM production pr LEFT JOIN purchase p ON pr.product_name = p.product_name " +
                "LEFT JOIN sales s ON pr.product_name = s.product_name " +
                "GROUP BY COALESCE(p.product_name, s.product_name, pr.product_name)";
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table_1.getModel();

            model.setRowCount(0);

            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];
            for (int i = 0; i < cols; i++)
                colName[i] = rsmd.getColumnName(i + 1);
            model.setColumnIdentifiers(colName);
            String product_name, total_quantity_purchase, average_unit_price_purchase, total_price_purchase,
                    total_quantity_sales, average_unit_price_sales, total_price_sales, total_quantity_production,
                    average_unit_price_production, total_price_production, income;

            while (rs.next()) {
                product_name = rs.getString(1);
                total_quantity_purchase = rs.getString(2);
                average_unit_price_purchase = rs.getString(3);
                total_price_purchase = rs.getString(4);
                total_quantity_sales = rs.getString(5);
                average_unit_price_sales = rs.getString(6);
                total_price_sales = rs.getString(7);
                total_quantity_production = rs.getString(8);
                average_unit_price_production = rs.getString(9);
                total_price_production = rs.getString(10);
                income = rs.getString(11);

                String[] row = { product_name, total_quantity_purchase, average_unit_price_purchase,
                        total_price_purchase, total_quantity_sales, average_unit_price_sales, total_price_sales,
                        total_quantity_production, average_unit_price_production, total_price_production, income };
                model.addRow(row);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    report frame = new report();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public report() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1300, 590);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 52, 1264, 449);
        contentPane.add(scrollPane);

        table_1 = new JTable();
        scrollPane.setViewportView(table_1);

        JButton btnNewButton_1_1_1 = new JButton("VIEW THE REPORT");
        btnNewButton_1_1_1.setBounds(584, 11, 141, 38);
        btnNewButton_1_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnNewButton_1_1_1) {
                    table_load();
                }

            }
        });
        btnNewButton_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
        contentPane.add(btnNewButton_1_1_1);
    }
}
