package Vending_Machine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import javax.swing.ImageIcon;

public class Salesperson_Login extends JFrame  {
    private JTextField salespersonIDField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Connection connection;
    private RestockPage restockPage;

    public Salesperson_Login() {
        initComponents();
        txtusername.setBackground(new java.awt.Color(0, 0, 0, 1));
        txtpassword.setBackground(new java.awt.Color(0, 0, 0, 1));
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        txtusername.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

        // Add underline border to passwordField
        txtpassword.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));


        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 51));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("x");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LOGIN");

        jLabel4.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Welcome back, Salesperson !");

        jLabel5.setFont(new java.awt.Font("Malgun Gothic", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password");

        jLabel6.setFont(new java.awt.Font("Malgun Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Username ");

        txtpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtpassword.setBorder(null);
        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("");

        txtusername.setFont(txtusername.getFont());
        txtusername.setForeground(new java.awt.Color(255, 255, 255));
        txtusername.setText("");
        txtusername.setBorder(null);
        txtusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("");

        jLabel11.setText(" ");

        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("LOGIN");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String salespersonID = txtusername.getText();
                char[] passwordChars = txtpassword.getPassword();
                String password = new String(passwordChars);

                if (isValidSalesperson(salespersonID, password)) {
                    restockPage = new RestockPage();
                    restockPage.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(Salesperson_Login.this, "Invalid salesperson ID or password. Please try again.");
                }
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\ANUGRAHA.VS\\Downloads\\user (1).png")); // NOI18N
        jLabel9.setText("jLabel9");
        jLabel9.setToolTipText(" ");

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\ANUGRAHA.VS\\Downloads\\padlock.png")); // NOI18N
        jLabel10.setText("jLabel9");

        jLabel10.setToolTipText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(313, 313, 313)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel8))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel6)
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(jLabel8))
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addComponent(jLabel5)
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(112, 112, 112))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\ANUGRAHA.VS\\Downloads\\cokebg.jpg")); // NOI18N
        jLabel1.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

        setSize(new java.awt.Dimension(758, 493));
        setLocationRelativeTo(null);
    }


    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    private void txtusernameActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private boolean isValidSalesperson(String salespersonID, String password) {
        String hashedInputPassword = hashPassword(password);
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String dbPassword = "anugraha@2005";

        try {
            connection = DriverManager.getConnection(url, username, dbPassword);
            String sql = "SELECT Password FROM Salesperson WHERE Salesperson_Id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, salespersonID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("Password");
                return hashedPassword.equals(hashedInputPassword);
            } else {
                return false; // Salesperson not found
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error validating salesperson: " + ex.getMessage());
            return false;
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

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salesperson_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salesperson_Login().setVisible(true);
            }
        });
    }

    class RestockPage extends JFrame implements ActionListener {
        private JTable productTable;
        private DefaultTableModel tableModel;
        private JButton restockButton;
        private RestockInterface restockInterface;

        public RestockPage() {
            setTitle("Restock Page");
            setSize(500, 200);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            // setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel messageLabel = new JLabel("Please click the button below to restock products:");
            panel.add(messageLabel, gbc);

            gbc.gridy++;
            JButton restockButton = new JButton("Restock Products");
            restockButton.addActionListener(this);
            panel.add(restockButton, gbc);

            getContentPane().add(panel);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Restock Products")) {
                restockInterface = new RestockInterface();
                restockInterface.setVisible(true);
            }
        }
    }

    class RestockInterface extends JFrame {
        private Connection connection;
        private JComboBox<String> cmbProductId;
        private JTextField txtAvailableQuantity;
        private JTextField txtQuantity;
        private JButton btnRestock;
        private JTextField txtProductName;
        private JLabel lblProductImage;

        public RestockInterface() {
            setTitle("Restock Products");
            setSize(700, 550);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Create a panel to hold components
            JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10)); // 7 rows, 2 columns, horizontal and vertical gaps
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Create labels and input fields
            JLabel lblProductId = new JLabel("Product ID:");
            JComboBox<String> cmbProductId = new JComboBox<>(new String[]{"111", "112", "113", "114", "115"});


            JLabel lblProductName = new JLabel("Product Name:");
            txtProductName = new JTextField();
            txtProductName.setEditable(false);


            JLabel lblAvailableQuantity = new JLabel("Available Quantity:");
            txtAvailableQuantity = new JTextField();
            txtAvailableQuantity.setEditable(false); // Make it read-only


            JLabel lblQuantity = new JLabel("Quantity to restock:");
            txtQuantity = new JTextField();

            JPanel imagePanel = new JPanel(new BorderLayout());
            lblProductImage = new JLabel();
            imagePanel.add(lblProductImage, BorderLayout.CENTER);
            panel.add(imagePanel);


            JButton btnRestock = new JButton("Restock");


            // Add components to the panel
            panel.add(lblProductId);
            panel.add(cmbProductId);
            panel.add(lblProductName);
            panel.add(txtProductName);
            panel.add(lblAvailableQuantity);
            panel.add(txtAvailableQuantity);
            lblProductImage = new JLabel();

            // Add the JLabel to the panel
            panel.add(lblProductImage);


            panel.add(lblQuantity);
            panel.add(txtQuantity);
            panel.add(new JLabel()); // Empty cell for spacing
            panel.add(btnRestock);

            // Add action listener for the product ID dropdown
            cmbProductId.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Update available quantity and product name text fields based on selected product ID
                    String productId = (String) cmbProductId.getSelectedItem();
                    int availableQuantity = updateAvailableQuantity(productId);
                    txtAvailableQuantity.setText(String.valueOf(availableQuantity));
                    String productName = getProductName(productId);
                    txtProductName.setText(productName);
                    //String productId = (String) cmbProductId.getSelectedItem();
                    ImageIcon productImage = getProductImage(productId);
                    lblProductImage.setIcon(productImage);
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

                        if (quantity < 0) {
                            JOptionPane.showMessageDialog(null, "Invalid quantity. Restock quantity cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method if quantity is negative
                        }

                        if (quantity > 25) {
                            System.out.println("Quantity exceeds maximum limit (25)");
                            JOptionPane.showMessageDialog(null, "Quantity exceeds maximum limit (25)", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Get the current available quantity
                            int currentQuantity = updateAvailableQuantity(productId);
                            // Restock the product only if the quantity is within the limit
                            int availableQuantity = updateProductQuantity(productId, currentQuantity, quantity);
                            if (availableQuantity < 25) {
                                txtAvailableQuantity.setText(String.valueOf(availableQuantity));
                                JOptionPane.showMessageDialog(null, "Product ID: " + productId + ", Quantity Restocked: " + quantity, "Restocked Successfully", JOptionPane.INFORMATION_MESSAGE);
                                JOptionPane.showMessageDialog(null, "Available Quantity: " + availableQuantity);

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
        private ImageIcon getProductImage(String productId) {
            ImageIcon productImage = null;
            String imagePath;

            switch (productId) {
                case "111":
                    imagePath = "C:\\Users\\ANUGRAHA.VS\\Downloads\\coke.png";
                    productImage = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 500, Image.SCALE_DEFAULT));
                    break;
                case "112":
                    imagePath = "C:\\Users\\ANUGRAHA.VS\\Downloads\\md.png";
                    break;
                case "113":
                    imagePath = "C:\\Users\\ANUGRAHA.VS\\Downloads\\redbull.png";
                    break;
                case "114":
                    imagePath = "C:\\Users\\ANUGRAHA.VS\\Downloads\\sprite.png";
                    break;
                case "115":
                    imagePath = "C:\\Users\\ANUGRAHA.VS\\Downloads\\wb.png";
                    break;
                default:
                    imagePath = "path/to/default/image.png";
                    break;
            }

            try {
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    productImage = new ImageIcon(imagePath);
                } else {
                    // Handle the case when the product image file is not found
                    productImage = new ImageIcon("path/to/default/image.png");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading product image: " + e.getMessage());
                productImage = new ImageIcon("path/to/default/image.png");
            }

            return productImage;
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
        private String getProductName(String productId) {
            String url = "jdbc:mysql://localhost:3306/vending_machine";
            String username = "root";
            String password = "anugraha@2005";
            String productName = "";

            try {
                connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT Product_Name FROM Product WHERE Product_Id = '" + productId + "'");
                if (resultSet.next()) {
                    productName = resultSet.getString("Product_Name");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching product name: " + e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return productName;
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
                    System.out.println("Quantity exceeds maximum limit (25)");
                    JOptionPane.showMessageDialog(null, "Quantity exceeds maximum limit (25)", "Error", JOptionPane.ERROR_MESSAGE);
                    // If the new quantity exceeds 25, set it to 25
                    newQuantity = 25;
                }

                // Update the product quantity in the database
                Statement statement = connection.createStatement();
                String updateQuery = "UPDATE Product SET Available_Quantity = " + newQuantity + " WHERE Product_Id = '" + productId + "'";
                int rowsAffected = statement.executeUpdate(updateQuery);
                if (rowsAffected > 0) {
                    updatedQuantity = newQuantity;

                    // Retrieve the salesperson name and product name
                    String salespersonId = ""; // Replace with the actual salesperson ID
                    String getSalespersonNameQuery = "SELECT Salesperson_Name FROM Salesperson WHERE Salesperson_Id = ?";
                    PreparedStatement getSalespersonNameStatement = connection.prepareStatement(getSalespersonNameQuery);
                    getSalespersonNameStatement.setString(1, salespersonId);
                    ResultSet salespersonNameResultSet = getSalespersonNameStatement.executeQuery();
                    String salespersonName = salespersonNameResultSet.next() ? salespersonNameResultSet.getString("Salesperson_Name") : "";

                    String getProductNameQuery = "SELECT Product_Name FROM Product WHERE Product_Id = ?";
                    PreparedStatement getProductNameStatement = connection.prepareStatement(getProductNameQuery);
                    getProductNameStatement.setInt(1, Integer.parseInt(productId));
                    ResultSet productNameResultSet = getProductNameStatement.executeQuery();
                    String productName = productNameResultSet.next() ? productNameResultSet.getString("Product_Name") : "";

                    // Insert a new record into the Restock_Log table
                    String insertRestockLogQuery = "INSERT INTO Restock_Log (Salesperson_Id, Salesperson_Name, Product_Id, Product_Name, Restocked_Quantity) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertStatement = connection.prepareStatement(insertRestockLogQuery);
                    insertStatement.setString(1, salespersonId);
                    insertStatement.setString(2, salespersonName);
                    insertStatement.setInt(3, Integer.parseInt(productId));
                    insertStatement.setString(4, productName);
                    insertStatement.setInt(5, restockQuantity);
                    insertStatement.executeUpdate();
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
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
}