package Vending_Machine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RestockInterface extends JFrame {

    private Connection connection;

    public RestockInterface() {
        setTitle("Restock Products");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 columns, with gaps
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Create labels and input fields
        JLabel lblProductId = new JLabel("Product ID:");
        JComboBox<String> cmbProductId = new JComboBox<>(new String[]{"111", "112", "113", "114", "115"}); // Product IDs dropdown

        JLabel lblAvailableQuantity = new JLabel("Available Quantity:");
        JTextField txtAvailableQuantity = new JTextField();
        txtAvailableQuantity.setEditable(false); // Make it read-only

        JLabel lblQuantity = new JLabel("Quantity to restock:");
        JTextField txtQuantity = new JTextField();

        JButton btnRestock = new JButton("Restock");

        // Add components to the panel
        panel.add(lblProductId);
        panel.add(cmbProductId);
        panel.add(lblAvailableQuantity);
        panel.add(txtAvailableQuantity);
        panel.add(lblQuantity);
        panel.add(txtQuantity);
        panel.add(new JLabel()); // Empty cell for spacing
        panel.add(btnRestock);

        // Add action listener for the product ID dropdown
        cmbProductId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update available quantity text field based on selected product ID
                String productId = (String) cmbProductId.getSelectedItem();
                int availableQuantity = updateAvailableQuantity(productId);
                txtAvailableQuantity.setText(String.valueOf(availableQuantity));

            }
        });

        // Add action listener for the restock button
        btnRestock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values
                String productId = (String) cmbProductId.getSelectedItem(); // Get selected product ID from dropdown
                String quantityStr = txtQuantity.getText();

                try {
                    int quantity = Integer.parseInt(quantityStr);

                    if (quantity > 25) {
                        System.out.println("Quantity exceeds maximum limit (25)");
                        JOptionPane.showMessageDialog(null, "Quantity exceeds maximum limit (25)", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        // Get the current available quantity
                        int currentQuantity = updateAvailableQuantity(productId);
                        // Restock the product only if the quantity is within the limit
                        int availableQuantity = updateProductQuantity(productId, currentQuantity, quantity);
                        if (availableQuantity != -1) {
                            txtAvailableQuantity.setText(String.valueOf(availableQuantity));
                            JOptionPane.showMessageDialog(null, "Product ID: " + productId + ", Quantity Restocked: " + quantity, "Restocked Successfully", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add the panel to the frame
        add(panel);
    }

    private int updateAvailableQuantity(String productId) {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";
        int availableQuantity = 0;

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Available_Quantity FROM Product WHERE Product_Id = '" + productId + "'");
            if (resultSet.next()) {
                availableQuantity = resultSet.getInt("Available_Quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching available quantity: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return availableQuantity;
    }

    private int updateProductQuantity(String productId, int currentQuantity, int restockQuantity) {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "anugraha@2005";
        int updatedQuantity = -1;

        try {
            connection = DriverManager.getConnection(url, username, password);

            // Calculate the new available quantity after restocking
            int newQuantity = currentQuantity + restockQuantity;
            if (newQuantity > 25) {
                // If the new quantity exceeds 25, set it to 25
                newQuantity = 25;
            }

            // Update the product quantity in the database
            Statement statement = connection.createStatement();
            String updateQuery = "UPDATE Product SET Available_Quantity = " + newQuantity + " WHERE Product_Id = '" + productId + "'";
            int rowsAffected = statement.executeUpdate(updateQuery);
            if (rowsAffected > 0) {
                updatedQuantity = newQuantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating product quantity: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return updatedQuantity;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestockInterface restockInterface = new RestockInterface();
            restockInterface.setVisible(true);
        });
    }
}
