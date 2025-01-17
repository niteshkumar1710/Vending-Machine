package Vending_Machine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.NumberFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




/**
 *
 * @author Adhi
 */
public final class chart extends javax.swing.JFrame {

    /**
     * Creates new form chart
     */
    private JPanel displayrestockdetails;
    private static chart instance;
    public static chart getInstance() {
        if (instance == null) {
            instance = new chart();
        }
        return instance;
    }
    public chart() {
        initComponents();
        showLineChart();
        showBarChart();
        connectToDatabaseAndFetchProductDetails();
        displayRestockDetails();
    }
    private Connection connection;
    private void connectToDatabaseAndFetchProductDetails() {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";

        try {
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Product_Id, Product_Name, Product_Price, Available_Quantity FROM Product");

            // Create a table model to hold the product details
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Product ID");
            tableModel.addColumn("Product Name");
            tableModel.addColumn("Product Price");
            tableModel.addColumn("Available Quantity");

            while (resultSet.next()) {
                int productId = resultSet.getInt("Product_Id");
                String productName = resultSet.getString("Product_Name");
                double productPrice = resultSet.getDouble("Product_Price");
                int available_quantity = resultSet.getInt("Available_Quantity");

                tableModel.addRow(new Object[]{productId, productName, productPrice, available_quantity});
            }

            JTable productTable = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(productTable);
            // Add the scroll pane to the display panel
            displayproductdetails.setLayout(new BorderLayout());
            displayproductdetails.add(scrollPane, BorderLayout.CENTER);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching product details: " + ex.getMessage());
        }
    }
    private void displayRestockDetails() {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";

        try {
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Restock_Log");

            // Create a table model to hold the restock details
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Restock ID");
            tableModel.addColumn("Restock Timestamp");
            tableModel.addColumn("Salesperson ID");
            tableModel.addColumn("Salesperson Name");
            tableModel.addColumn("Product ID");
            tableModel.addColumn("Product Name");
            tableModel.addColumn("Restocked Quantity");

            while (resultSet.next()) {
                int restockId = resultSet.getInt("Restock_Id");
                Timestamp restockTimestamp = resultSet.getTimestamp("Restock_Timestamp");
                String salespersonId = resultSet.getString("Salesperson_Id");
                String salespersonName = resultSet.getString("Salesperson_Name");
                int productId = resultSet.getInt("Product_Id");
                String productName = resultSet.getString("Product_Name");
                int restockedQuantity = resultSet.getInt("Restocked_Quantity");

                tableModel.addRow(new Object[]{restockId, restockTimestamp, salespersonId, salespersonName, productId, productName, restockedQuantity});
            }

            JTable restockTable = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(restockTable);
            // Add the scroll pane to the display panel
            displayrestockdetails.setLayout(new BorderLayout());
            displayrestockdetails.add(scrollPane, BorderLayout.CENTER);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching restock details: " + ex.getMessage());
        }
    }
    private void connectToDatabaseAndFetchSalespersonDetails() {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";

        try {
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Salesperson_Id, Salesperson_Name, Salesperson_Email, Salesperson_Phone FROM Salesperson");

            // Create a table model to hold the data
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Salesperson ID");
            model.addColumn("Name");
            model.addColumn("Email");
            model.addColumn("Phone");

            // Populate the table model with data from the result set
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getInt("Salesperson_Id"),
                        resultSet.getString("Salesperson_Name"),
                        resultSet.getString("Salesperson_Email"),
                        resultSet.getString("Salesperson_Phone")
                };
                model.addRow(rowData);
            }

            // Create a JTable with the table model
            JTable table = new JTable(model);

            // Display the table in a scrollable pane
            JScrollPane scrollPane = new JScrollPane(table);
            JOptionPane.showMessageDialog(this, scrollPane, "Salesperson Details", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching salesperson details: " + ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void showBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine", "root", "anugraha@2005");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ProductName, CAST(SUM(Sales_Quantity) AS SIGNED INTEGER) AS TotalSales " +
                    "FROM Sales_Log " +
                    "GROUP BY ProductName");

            while (resultSet.next()) {
                String productName = resultSet.getString("ProductName");
                int totalSales = resultSet.getInt("TotalSales");
                dataset.setValue(totalSales, "Total Sales", productName);
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart("Product Sales", "Product Name", "Total Sales", dataset, PlotOrientation.VERTICAL, false, true, false);

        // Set item label generator to show integers
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getIntegerInstance()));
        renderer.setBaseItemLabelsVisible(true);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer barRenderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        barRenderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        panebarchart.removeAll();
        panebarchart.add(barpChartPanel, BorderLayout.CENTER);
        panebarchart.validate();
    }

    public void showLineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vending_machine", "root", "anugraha@2005");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT HOUR(Sales_TimeStamp) AS Hour, CAST(COUNT(*) AS SIGNED INTEGER) AS TotalSales " +
                    "FROM Sales_Log " +
                    "WHERE Sales_Date = '2024-05-05' " +
                    "GROUP BY Hour " +
                    "ORDER BY Hour");

            while (resultSet.next()) {
                int hour = resultSet.getInt("Hour");
                int totalSales = resultSet.getInt("TotalSales");
                dataset.setValue(totalSales, "Total Sales", String.format("%02d:00", hour));
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JFreeChart lineChart = ChartFactory.createLineChart("Number of Products Sold", "Hourly Sales", "Time", dataset, PlotOrientation.VERTICAL, false, true, false);

        // Set item label generator to show integers
        CategoryPlot lineCategoryPlot = lineChart.getCategoryPlot();
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        lineRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", NumberFormat.getIntegerInstance()));
        lineRenderer.setBaseItemLabelsVisible(true);

        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.BLACK);

        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        panelLineChart.removeAll();
        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminmenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        salesdetailclick = new javax.swing.JLabel();
        transactionlogclick = new javax.swing.JLabel();
        restocklogclick = new javax.swing.JLabel();
        salesregclick = new javax.swing.JLabel();
        displayproductdetails = new javax.swing.JPanel();
        panebarchart = new javax.swing.JPanel();
        panelLineChart = new javax.swing.JPanel();
        displayrestockdetails = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        adminmenu.setBackground(new Color(153, 153, 153));

        jLabel1.setBackground(new Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ADMIN PANEL");
        jLabel1.setOpaque(true);

        salesdetailclick.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        salesdetailclick.setText(" Salesperson Detail");
        salesdetailclick.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salesdetailclick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesdetailclickMouseClicked(evt);
            }
        });

        transactionlogclick.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        transactionlogclick.setText(" Transaction log");
        transactionlogclick.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transactionlogclick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionlogclickMouseClicked(evt);
            }
        });

        //restocklogclick = new javax.swing.JLabel();
        restocklogclick.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        restocklogclick.setText(" Restock log");
        restocklogclick.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        restocklogclick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                restocklogclickMouseClicked(evt);
            }
        });

        salesregclick.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        salesregclick.setText(" Salesperson Register ");
        salesregclick.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salesregclick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesregclickMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout adminmenuLayout = new javax.swing.GroupLayout(adminmenu);
        adminmenu.setLayout(adminmenuLayout);
        adminmenuLayout.setHorizontalGroup(
                adminmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salesdetailclick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(transactionlogclick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(restocklogclick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salesregclick, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
        );
        adminmenuLayout.setVerticalGroup(
                adminmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(adminmenuLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(salesregclick, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salesdetailclick, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(transactionlogclick, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(restocklogclick, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 416, Short.MAX_VALUE))
        );

        getContentPane().add(adminmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        displayproductdetails.setBackground(new Color(204, 255, 255));

        javax.swing.GroupLayout displayproductdetailsLayout = new javax.swing.GroupLayout(displayproductdetails);
        displayproductdetails.setLayout(displayproductdetailsLayout);
        displayproductdetailsLayout.setHorizontalGroup(
                displayproductdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 980, Short.MAX_VALUE)
        );
        displayproductdetailsLayout.setVerticalGroup(
                displayproductdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 310, Short.MAX_VALUE)
        );

        getContentPane().add(displayproductdetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));

        panebarchart.setBackground(new Color(255, 255, 204));
        panebarchart.setLayout(new BorderLayout());
        getContentPane().add(panebarchart, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 400, 300));

        panelLineChart.setBackground(new Color(255, 255, 204));
        panelLineChart.setLayout(new BorderLayout());
        getContentPane().add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salesdetailclickMouseClicked(java.awt.event.MouseEvent evt) {
        connectToDatabaseAndFetchSalespersonDetails();
    }

    private void transactionlogclickMouseClicked(java.awt.event.MouseEvent evt) {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";

        try {
            // Connect to the database
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Sales_Log");

            // Create a table model to hold the sales log details
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("User ID");
            tableModel.addColumn("Product ID");
            tableModel.addColumn("Product Name");
            tableModel.addColumn("Sales Date");
            tableModel.addColumn("Sales Quantity");
            tableModel.addColumn("Sales Timestamp");
            tableModel.addColumn("Entered Amount");
            tableModel.addColumn("Balance Returned");

            while (resultSet.next()) {
                String userId = resultSet.getString("User_Id");
                int productId = resultSet.getInt("ProductId");
                String productName = resultSet.getString("ProductName");
                Date salesDate = resultSet.getDate("Sales_Date");
                int salesQuantity = resultSet.getInt("Sales_Quantity");
                Timestamp salesTimestamp = resultSet.getTimestamp("Sales_TimeStamp");
                int enteredAmount = resultSet.getInt("Entered_Amount");
                int balanceReturned = resultSet.getInt("Balance_Returned");

                tableModel.addRow(new Object[]{userId, productId, productName, salesDate, salesQuantity, salesTimestamp, enteredAmount, balanceReturned});
            }

            JTable salesLogTable = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(salesLogTable);
            JOptionPane.showMessageDialog(this, scrollPane, "Transaction Log", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching sales log details: " + ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void restocklogclickMouseClicked(java.awt.event.MouseEvent evt) {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";

        try {
            // Connect to the database
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Restock_Log");

            // Create a table model to hold the restock log details
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Restock ID");
            tableModel.addColumn("Restock Timestamp");
            tableModel.addColumn("Salesperson ID");
            tableModel.addColumn("Salesperson Name");
            tableModel.addColumn("Product ID");
            tableModel.addColumn("Product Name");
            tableModel.addColumn("Restocked Quantity");

            while (resultSet.next()) {
                int restockId = resultSet.getInt("Restock_Id");
                Timestamp restockTimestamp = resultSet.getTimestamp("Restock_Timestamp");
                String salespersonId = resultSet.getString("Salesperson_Id");
                String salespersonName = resultSet.getString("Salesperson_Name");
                int productId = resultSet.getInt("Product_Id");
                String productName = resultSet.getString("Product_Name");
                int restockedQuantity = resultSet.getInt("Restocked_Quantity");

                tableModel.addRow(new Object[]{restockId, restockTimestamp, salespersonId, salespersonName, productId, productName, restockedQuantity});
            }

            JTable restockLogTable = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(restockLogTable);
            JOptionPane.showMessageDialog(this, scrollPane, "Restock Log", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching restock log details: " + ex.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void salesregclickMouseClicked(java.awt.event.MouseEvent evt) {

        this.setVisible(true);
        Salesperson_Register sales = new  Salesperson_Register();
        sales.setVisible(true);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adminmenu;
    private javax.swing.JPanel displayproductdetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panebarchart;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JLabel restocklogclick;
    private javax.swing.JLabel salesdetailclick;
    private javax.swing.JLabel salesregclick;
    private javax.swing.JLabel transactionlogclick;
    // End of variables declaration//GEN-END:variables
}
