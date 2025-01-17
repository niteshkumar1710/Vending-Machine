package Vending_Machine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.util.Calendar;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


public class User_Authentication extends JFrame implements ActionListener {
    private JTextField userIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Connection connection;
    private OptionPage optionPage;

    public User_Authentication() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userIDLabel = new JLabel("User ID:");
        panel.add(userIDLabel, gbc);

        gbc.gridy++;
        userIDField = new JTextField(20);
        panel.add(userIDField, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        gbc.gridy++;
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton, gbc);

        getContentPane().add(panel);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            if (isValidUser(userID, password)) {
                this.setVisible(false);
                optionPage = new OptionPage();
                optionPage.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid user ID or password. Please try again.");
            }
        }
    }

    private boolean isValidUser(String userID, String password) {
        String hashedInputPassword = hashPassword(password);
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String dbPassword = "anugraha@2005";

        try {
            connection = DriverManager.getConnection(url, username, dbPassword);
            String sql = "SELECT Password FROM Users WHERE UserID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("Password");
                return hashedPassword.equals(hashedInputPassword);
            } else {
                return false; // User not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error validating user: " + e.getMessage());
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] byteData = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User_Authentication frame = new User_Authentication();
            frame.setVisible(true);
        });
    }

    class OptionPage extends JFrame implements ActionListener {
        public OptionPage() {
            setTitle("Options");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel label = new JLabel("Choose an Option:");
            panel.add(label, gbc);

            gbc.gridy++;
            JButton backButton = new JButton("Back to Login");
            backButton.addActionListener(this);
            panel.add(backButton, gbc);

            gbc.gridy++;
            JButton productDetailsButton = new JButton("View Product Details");
            productDetailsButton.addActionListener(this);
            panel.add(productDetailsButton, gbc);

            gbc.gridy++;
            JButton salesLogButton = new JButton("View Sales Log");
            salesLogButton.addActionListener(this);
            panel.add(salesLogButton, gbc);

            gbc.gridy++;
            JButton addSalespersonButton = new JButton("Add Salesperson");
            addSalespersonButton.addActionListener(this);
            panel.add(addSalespersonButton, gbc);

            gbc.gridy++;
            JButton ViewSalespersonDetailsButton = new JButton("View Salesperson Details");
            ViewSalespersonDetailsButton.addActionListener(this);
            panel.add(ViewSalespersonDetailsButton, gbc);


            gbc.gridy++;
            JButton salesAnalysisButton = new JButton("Sales Analysis");
            salesAnalysisButton.addActionListener(this);
            panel.add(salesAnalysisButton, gbc);

            getContentPane().add(panel);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Back to Login")) {
                User_Authentication loginFrame = new User_Authentication();
                loginFrame.setVisible(true);
                this.dispose();
            } else if (e.getActionCommand().equals("View Product Details")) {
                connectToDatabaseAndFetchProductDetails();
            } else if (e.getActionCommand().equals("View Sales Log")) {
                connectToDatabaseAndFetchSalesLog();
            } else if (e.getActionCommand().equals("Add Salesperson")) {
                addSalesperson();
            }
            else if(e.getActionCommand().equals("View Salesperson Details")){
                connectToDatabaseAndFetchSalespersonDetails();
            }
            else if (e.getActionCommand().equals("Sales Analysis")) {
                showSalesAnalysis();
            }
        }
    }

    private void showSalesAnalysis() {
        JFrame analysisFrame = new JFrame("Sales Analysis");
        analysisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        analysisFrame.setSize(800, 600);
        analysisFrame.setLocationRelativeTo(null);

        JFreeChart barChart = createBarChart();

        if (barChart != null) {
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
            analysisFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to create the bar chart.");
        }

        analysisFrame.pack();
        analysisFrame.setVisible(true);
    }


    private JFreeChart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Fetch sales data for the last 24 hours
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -24);
        Date twentyFourHoursAgo = cal.getTime();
        String query = "SELECT ProductId, SUM(Sales_Quantity) AS TotalSales FROM Sales_Log WHERE Sales_TimeStamp >= ? GROUP BY ProductId";
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, new Timestamp(twentyFourHoursAgo.getTime()));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductId");
                int totalSales = resultSet.getInt("TotalSales");
                dataset.addValue(totalSales, "Products", String.valueOf(productId)); // Add Product ID as category
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart barChart = ChartFactory.createBarChart("Sales Analysis", "Product ID", "Quantity Sold", dataset);
        return barChart;
    }





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
            JOptionPane.showMessageDialog(this, scrollPane, "Product Details", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching product details: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void connectToDatabaseAndFetchSalesLog() {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";

        try {
            connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Sales_Log");

            StringBuilder salesLog = new StringBuilder();
            salesLog.append("Sales Log:\n");
            while (resultSet.next()) {
                int userId = resultSet.getInt("User_Id");
                int productId = resultSet.getInt("ProductId");
                Date salesDate = resultSet.getDate("Sales_Date");
                int salesQuantity = resultSet.getInt("Sales_Quantity");
                Timestamp salesTimestamp = resultSet.getTimestamp("Sales_TimeStamp");

                salesLog.append("User ID: ").append(userId).append(", ");
                salesLog.append("Product ID: ").append(productId).append(", ");
                salesLog.append("Sales Date: ").append(salesDate).append(", ");
                salesLog.append("Sales Quantity: ").append(salesQuantity).append(", ");
                salesLog.append("Sales Timestamp: ").append(salesTimestamp).append("\n");
            }
            JOptionPane.showMessageDialog(this, salesLog.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching sales log: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addSalesperson() {
        String salespersonName = JOptionPane.showInputDialog(this, "Enter Salesperson Name:");
        String salespersonEmail = JOptionPane.showInputDialog(this, "Enter Salesperson Email:");
        String salespersonPhone = JOptionPane.showInputDialog(this, "Enter Salesperson Phone:");
        String salespersonId = JOptionPane.showInputDialog(this, "Enter Salesperson ID:");
        String salespersonPassword = JOptionPane.showInputDialog(this, "Enter Salesperson Password:");

        if (salespersonName != null && !salespersonName.isEmpty() && salespersonId != null && !salespersonId.isEmpty() && salespersonPassword != null && !salespersonPassword.isEmpty()) {
            String url = "jdbc:mysql://localhost:3306/vending_machine";
            String username = "root";
            String password = "anugraha@2005";

            try {
                connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                // Insert Salesperson data into the Salesperson table
                String sql = String.format("INSERT INTO Salesperson (Salesperson_Id, Salesperson_Name, Salesperson_Email, Salesperson_Phone, Password) VALUES (%s, '%s', '%s', '%s', '%s')",
                        salespersonId, salespersonName, salespersonEmail, salespersonPhone, hashPassword(salespersonPassword));
                int rowsInserted = statement.executeUpdate(sql);

                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Salesperson added successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add salesperson.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding salesperson: " + e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Salesperson name, ID, and password cannot be empty.");
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

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching salesperson details: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
