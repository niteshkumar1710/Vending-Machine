package project.vending2db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JPanel;
import java.sql.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class project_vending_machine extends JFrame {
    //String[] product = {"coco cola", "sevenup", "acti vit","red bull","fanta","coca cola no sugar","coustom apple","dr_peper"};
    //int[] price = {60, 50, 70,120,50,90,60,50};
    //String[] quantity = {"600 ml", "500 ml", "450 ml","500 ml","200 ml","360 ml","100 ml","300 ml"};
    // String[] manufacturer = {"cola company", "pepsei and co", "pepsi and co"};
    int[] stock = {25, 25, 25,25,25,25,25,25};
    String[] images = {
            "C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\coke.png",
            "C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\sevenup.png",
            "C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\vitimg.png",
            "C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\redbull.png",
            "C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\fanta.png",
            "C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\coke_no_sugar.png",
            "C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\cawston_apple.png",
            "C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\dr_peper.png"
    };




    JPanel main_panel;


    JMenuBar menuBar;
    JMenu access;
    JMenuItem user;
    JMenuItem admin;
    JMenuItem sales_person;
    ImageIcon menuicon;
    ImageIcon person_icon;
    JPanel topPanel;
    int animationX=-200;
    JLabel movingLabel;
    JPanel productPanel;
    JPanel paymentPanel;
    JPanel dispensePanel;
    JLabel dispenseLabel;
    JPanel cashPanel;
    JPanel cardPanel;
    JPanel cashContentPanel;
    JPanel cardContentPanel;
    JPanel paymentContentPanel;






    public project_vending_machine() {
        this.setTitle("Vending Machine");
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main_panel = new JPanel();
        main_panel.setLayout(null); // Use null layout
        main_panel.setBounds(300, 0, 850, 780);
        Border border = BorderFactory.createLineBorder(new Color(0xedbe65), 2);
        main_panel.setBorder(border);
        main_panel.setOpaque(true);
        menuicon = new ImageIcon("C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\menu11.png");
        person_icon = new ImageIcon("C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\person.png");


        menuBar = new JMenuBar();
        access = new JMenu("");
        access.setIcon(menuicon);

        user=new JMenuItem("USER");
        user.setIcon(person_icon);
        admin=new JMenuItem("ADMIN");
        admin.setIcon(person_icon);
        sales_person=new JMenuItem("SALES PERSON");
        sales_person.setIcon(person_icon);



        access.add(user);
        access.add(admin);
        access.add(sales_person);
        menuBar.add(access);

        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBounds(0, 0, 37, 30);
        menuPanel.setBorder(BorderFactory.createLineBorder(new Color(0xedbe65), 2));
        menuPanel.add(menuBar, BorderLayout.CENTER); // Add menu bar to the menu panel


        main_panel.add(menuPanel); // Add the menu panel to the main panel

        JPanel contactPanel=new JPanel();
        contactPanel = new JPanel();
        contactPanel.setLayout(null); // Use null layout
        contactPanel.setBounds(38, 0, 812, 30);
        Border cborder = BorderFactory.createLineBorder(new Color(0xedbe65), 2);
        contactPanel.setBorder(cborder);
        contactPanel.setOpaque(true);
        contactPanel.setBackground(new Color(0x3c6b60));


        JLabel contactLabel = new JLabel("Contact no: 1234574656657");
// Set font and color if needed
        contactLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contactLabel.setForeground(new Color(0xedbe65));  // Set the foreground color

// Set bounds to position the label on the top right corner
        contactLabel.setBounds(500, 5, 270, 20);
        contactLabel.setOpaque(true);  // Set the label as opaque
        // Set the background color
        contactLabel.setBackground(new Color(0x3c6b60));

// Add the contact label to the contact panel
        contactPanel.add(contactLabel);


        // fixed top panel
        topPanel = new JPanel();
        topPanel.setLayout(null); // Use null layout
        topPanel.setBounds(0, 30, 850, 190); // Adjust position and size as needed
        topPanel.setBorder(border);
        topPanel.setOpaque(true);
        topPanel.setBackground(new Color(0x3c6b60));



        //----------------------contents in fixed panel-------------------\\
        // Create a JLabel to hold the logo image
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("C:\\nitesh college\\program files\\SECOND YEAR\\JAVA\\IntelliJ Java\\src\\swing\\vending logo1.png");
        logoLabel.setIcon(logoIcon);
        // Set the position and size of the logo label
//        int logoX = 0; // Position from the left
//        int logoY = (topPanel.getHeight() - logoIcon.getIconHeight()) / 2; // Center vertically
        int logoWidth = logoIcon.getIconWidth();
        int logoHeight = logoIcon.getIconHeight();
        logoLabel.setBounds(2,9 , logoWidth, logoHeight);
        // Add the logo label to the topPanel
        topPanel.add(logoLabel);

        JLabel vendingMachineLabel = new JLabel(" REFRESHING RIGHT SODA");
        vendingMachineLabel.setBounds(170, 0, 850, 80);
        vendingMachineLabel.setForeground(new Color(0xedbe65));
        vendingMachineLabel.setFont(new Font("times new roman", Font.BOLD, 45)); // Font, style, size
        topPanel.add(vendingMachineLabel);

        JLabel vendingMachineslogan = new JLabel(" Taste the Bubbles, Feel the Refreshment!");
        vendingMachineslogan.setBounds(200, 50, 850, 80);
        vendingMachineslogan.setForeground(new Color(0xffffff));
        vendingMachineslogan.setFont(new Font("times new roman", Font.BOLD, 25)); // Font, style, size
        topPanel.add(vendingMachineslogan);

        movingLabel = new JLabel("Quench Your Thirst in a Sip!");
        movingLabel.setForeground(Color.WHITE);
        movingLabel.setFont(new Font("Arial", Font.BOLD, 25));
        movingLabel.setBounds(animationX, 150, 800, 50);
        topPanel.add(movingLabel);

        // Create a Timer to animate the text
        Timer timer = new Timer(10, new ActionListener() { // Decreased delay for faster animation
            @Override
            public void actionPerformed(ActionEvent e) {
                // Move the text from left to right
                animationX+=3;
                if (animationX > topPanel.getWidth()) {
                    animationX = -movingLabel.getWidth();
                }
                movingLabel.setLocation(animationX, 150);
            }
        });
        timer.start(); // Start the timer



        //----------end of contents of fixed panel----------------------\\
        main_panel.add(topPanel);
        main_panel.add(contactPanel);

        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(2, 4, 10, 10)); // GridLayout to display products in rows and columns
        productPanel.setBounds(0, 220, 850, 520);
        productPanel.setBackground(new Color(0x9eb6b0));
        Border pborder = BorderFactory.createLineBorder(new Color(0xedbe65), 2);
        productPanel.setBorder(pborder);
        productPanel.setOpaque(true);

        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "nitesh97903";

        // Arrays to store product data
        int[] productID;
        String[] productNames;
        int[] productPrices;
        String[] productQuantity;
        //byte[][] productImages; // Change the data type to byte[][]

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            // Execute query to fetch product data
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT Product_ID, Product_Name, Product_Price, Product_Quantity, Product_Image FROM Product");

            // Determine the number of rows in the result set
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // Initialize arrays based on the number of rows
            productID = new int[rowCount];
            productNames = new String[rowCount];
            productPrices = new int[rowCount];
            productQuantity = new String[rowCount];
            //productImages = new byte[rowCount][]; // Initialize as a 2D array

            // Populate arrays with data from result set
            int index = 0;
            while (resultSet.next()) {
                productID[index] = resultSet.getInt("Product_Id");
                productNames[index] = resultSet.getString("Product_Name");
                productPrices[index] = resultSet.getInt("Product_Price"); // Get Product_Price from the result set
                productQuantity[index] = resultSet.getString("Product_Quantity");
                //productImages[index] = resultSet.getBytes("Product_Image"); // Store binary data in byte[]
                index++;
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            // Display products
            for (int i = 0; i < productNames.length; i++) {
                //ImageIcon productImage = new ImageIcon(productImages[i]);// Create ImageIcon from byte[]
                JPanel singleProductPanel = createProductPanel(productID[i],productNames[i], productPrices[i], productQuantity[i] ,images[i] );
                productPanel.add(singleProductPanel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        productPanel.revalidate();
        productPanel.repaint();



        paymentPanel = new JPanel();

        paymentPanel.setBounds(0, 220, 850, 520);
        paymentPanel.setBackground(new Color(0xffffff));
        Border payborder = BorderFactory.createLineBorder(new Color(0xedbe65), 2);
        paymentPanel.setBorder(payborder);
        // paymentPanel.setOpaque(true);
        paymentPanel.setVisible(true);
        //main_panel.add(paymentPanel);


        dispensePanel=new JPanel();
        dispensePanel.setBounds(0,740,850,40);
        Border dispenceborder = BorderFactory.createLineBorder(new Color(0xedbe65), 2);

        dispensePanel.setBorder(dispenceborder);
        dispensePanel.setBackground(Color.BLACK);
        dispensePanel.setVisible(true);
        dispenseLabel=new JLabel("YOUR DRINK WILL BE DISPENSED HERE");
        dispenseLabel.setBounds(100, 0, 850, 20);
        dispenseLabel.setForeground(new Color(0xedbe65));
        dispenseLabel.setFont(new Font("times new roman", Font.BOLD, 20)); // Font, style, size
        dispensePanel.add(dispenseLabel);







        cashPanel = new JPanel();

        cashPanel.setBounds(0, 540, 850, 200);
        cashPanel.setBackground(new Color(0xffffff));
        Border cashborder = BorderFactory.createLineBorder(new Color(0xedbe65), 2);
        cashPanel.setBorder(cashborder);
        // cashPanel.setOpaque(true);
        cashPanel.setVisible(true);
        //main_panel.add(cashPanel);

        cardPanel = new JPanel();

        cardPanel.setBounds(0, 540, 850, 200);
        cardPanel.setBackground(new Color(0xffffff));
        Border cardborder = BorderFactory.createLineBorder(new Color(0xedbe65), 2);
        cardPanel.setBorder(cashborder);
        // cardPanel.setOpaque(true);
        cardPanel.setVisible(true);
        //main_panel.add(cardPanel);





        main_panel.add(dispensePanel);
        main_panel.add(productPanel);
        this.add(main_panel);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
    private JPanel createProductPanel(int productID , String productName, int productPrice, String productQuantity, String imagePath) {
        JPanel singleProductPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw grey rounded rectangle on half of the panel's width
                g.setColor(new Color(0x9eb6b0)); // Half color in image
                int width = getWidth() / 2;
                Graphics2D g2d = (Graphics2D) g;
                int arcWidth = 0; // Adjust the arc width as needed
                int arcHeight =0; // Adjust the arc height as needed
                g2d.fillRoundRect(0, 0, 205, 100, arcWidth, arcHeight);
            }
        };

        singleProductPanel.setLayout(new BorderLayout()); // Use BorderLayout for each product panel
        singleProductPanel.setBorder(BorderFactory.createLineBorder(new Color(0x9eb6b0)));
        singleProductPanel.setBackground(new Color(0xffffff));

        // Add product image
        ImageIcon productImage = new ImageIcon(imagePath);
        JLabel productImageLabel = new JLabel(productImage);
        singleProductPanel.add(productImageLabel, BorderLayout.CENTER);

        // Add product details
        JPanel productDetailsPanel = new JPanel(new GridLayout(3, 1));
        productDetailsPanel.setBackground(new Color(0xedbe65)); // Set background color for product details
        productDetailsPanel.setForeground(Color.WHITE); // Set foreground color for product details
        productDetailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding
        JLabel productNameLabel = new JLabel(productName);
        JLabel productPriceLabel = new JLabel("Price: INR " + productPrice);
        JLabel productQuantityLabel = new JLabel("Quantity: " + productQuantity);
        productDetailsPanel.add(productNameLabel);
        productDetailsPanel.add(productPriceLabel);
        productDetailsPanel.add(productQuantityLabel);
        singleProductPanel.add(productDetailsPanel, BorderLayout.SOUTH);

        // Add mouse hover effect
        singleProductPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                productImageLabel.setIcon(new ImageIcon(productImage.getImage().getScaledInstance(productImage.getIconWidth() + 20, productImage.getIconHeight() + 20, Image.SCALE_DEFAULT)));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                productImageLabel.setIcon(productImage); // Reset image size
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Move the product panel to the payment panel
                main_panel.remove(productPanel); // Remove product panel from the main panel
                paymentContentPanel = paymentPanelContents(productID,productName, productPrice, productQuantity, imagePath);
                paymentPanel.add(paymentContentPanel); // Add payment content panel to the payment panel
                main_panel.add(paymentPanel); // Add payment panel to the main panel
                main_panel.revalidate(); // Refresh layout// Show the payment panel
            }
        });

        return singleProductPanel;
    }

    private JPanel paymentPanelContents(int productID,String productName, int productPrice, String productQuantity, String imagePath){
        int price=productPrice;
        //String name=productName;
        JPanel paymentContentPanel = new JPanel();
        paymentContentPanel.setLayout(null); // Set layout to null for absolute positioning
        //on seting the opaque to true ,if we setlayout to null the screen will not be displayed

        paymentContentPanel.setPreferredSize(new Dimension(850, 320));

        paymentContentPanel.setBackground(new Color(0xffffff));

        // Add product image
        ImageIcon productImage = new ImageIcon(imagePath);
        JLabel productImageLabel = new JLabel(productImage);
        productImageLabel.setBounds(-20, 10, 300, 200);
        paymentContentPanel.add(productImageLabel);

        // Add product details
        JLabel productNameLabel = new JLabel("<html>Product Name: " + productName + " <br></html>");
        productNameLabel.setBounds(250, 30, 250, 30);
        paymentContentPanel.add(productNameLabel);

        JLabel productPriceLabel = new JLabel("<html>Price: INR " + productPrice + "<br></html>");
        productPriceLabel.setBounds(250, 70, 250, 30); // Adjusted X position
        paymentContentPanel.add(productPriceLabel);

        JLabel productQuantityLabel = new JLabel("<html>Quantity: " + productQuantity + "<br></html>");
        productQuantityLabel.setBounds(250, 110, 250, 30); // Adjusted Y position
        paymentContentPanel.add(productQuantityLabel);

       /* Timer waiting = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                main_panel.remove(productPanel);

                main_panel.add(productPanel);

                main_panel.revalidate();
                main_panel.repaint();
                main_panel.remove(paymentPanel);
                paymentPanel.remove(paymentContentPanel);
                cashPanel.remove(cashContentPanel);
                main_panel.remove(cashPanel);


                cashPanel.repaint();
                cashPanel.revalidate();

                main_panel.repaint();
                main_panel.revalidate();

                main_panel.remove(cardPanel);
                cardPanel.remove(cardContentPanel);
                cardPanel.revalidate();
                cardPanel.repaint();
                main_panel.repaint();
                main_panel.revalidate();

                paymentPanel.revalidate();


            }
        });

        waiting.setRepeats(false); // Run the timer only once
        waiting.start();*/


        // Add buttons
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(210, 180, 100, 30); // Adjusted position
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font style
        cancelButton.setForeground(Color.RED); // Set text color
        cancelButton.setBackground(Color.WHITE); // Set background color
        cancelButton.setFocusPainted(false); // Remove focus border
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.RED));// Adjusted position
        paymentContentPanel.add(cancelButton);

        JButton cashButton = new JButton("Cash");
        cashButton.setBounds(350, 180, 100, 30); // Adjusted position
        cashButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font style
        cashButton.setForeground(new Color(0x8adfac)); // Set text color
        cashButton.setBackground(Color.WHITE); // Set background color
        cashButton.setFocusPainted(false); // Remove focus border
        cashButton.setBorder(BorderFactory.createLineBorder(new Color(0x37c871)));
        paymentContentPanel.add(cashButton);

        JButton cardButton = new JButton("Card");
        cardButton.setBounds(480, 180, 100, 30); // Adjusted position
        cardButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font style
        cardButton.setForeground(new Color(0x00d6ff)); // Set text color
        cardButton.setBackground(Color.WHITE); // Set background color
        cardButton.setFocusPainted(false); // Remove focus border
        cardButton.setBorder(BorderFactory.createLineBorder(new Color(0x00d99ff)));
        paymentContentPanel.add(cardButton);
        // Add action listeners to buttons
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                paymentPanel.setVisible(false);
//
//
//                paymentPanel.repaint();
//                productPanel.setVisible(true);
                main_panel.remove(productPanel);

                main_panel.add(productPanel);

                main_panel.revalidate();
                main_panel.repaint();
                main_panel.remove(paymentPanel);
                paymentPanel.remove(paymentContentPanel);
                cashPanel.remove(cashContentPanel);
                main_panel.remove(cashPanel);


                cashPanel.repaint();
                cashPanel.revalidate();

                main_panel.repaint();
                main_panel.revalidate();

                main_panel.remove(cardPanel);
                cardPanel.remove(cardContentPanel);
                cardPanel.revalidate();
                cardPanel.repaint();
                main_panel.repaint();
                main_panel.revalidate();

                paymentPanel.revalidate();


            }
        });

        cashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_panel.remove(productPanel); // Remove product panel from the main panel
                main_panel.remove(cardPanel);
                cashContentPanel = cashPanelContents(productID,price,productName);
                cashPanel.add(cashContentPanel); // Add payment content panel to the payment panel
                main_panel.add(cashPanel); // Add payment panel to the main panel
                main_panel.revalidate();            }
        });

        cardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_panel.remove(productPanel); // Remove product panel from the main panel
                main_panel.remove(cashPanel);
                cardContentPanel=cardPanelContents(productID,price,productName);
                cardPanel.add(cardContentPanel);
                main_panel.add(cardPanel);
                main_panel.revalidate();
            }
        });

        return paymentContentPanel;
    }

    private JPanel cashPanelContents(int productID,int price,String ProductName)
    {
        JPanel cashContentPanel = new JPanel();
        cashContentPanel.setLayout(null); // Set layout to null for absolute positioning
        //on seting the opaque to true ,if we setlayout to null the screen will not be displayed

        cashContentPanel.setPreferredSize(new Dimension(850, 200));

        cashContentPanel.setBackground(new Color(0xffffff));

        JLabel cash = new JLabel("  CASH");
        cash.setBounds(20, 10, 50, 30);
        cashContentPanel.add(cash);

        JLabel productPriceLabel = new JLabel("<html>Product price: " + price + " <br></html>");
        productPriceLabel.setBounds(250, 30, 250, 30);
        cashContentPanel.add(productPriceLabel);

        JLabel amnt = new JLabel("ENTER THE AMOUNT :");
        amnt.setBounds(10, 82, 250, 20);
        cashContentPanel.add(amnt);


        JTextField amountField = new JTextField();

        amountField.setBounds(150, 80, 200, 30);
        cashContentPanel.add(amountField);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(380, 80, 100, 30);
        cashContentPanel.add(payButton);

        JLabel successLabel = new JLabel("Payment successful!");
        successLabel.setForeground(Color.GREEN); // Set color to green for success
        successLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font for success message
        successLabel.setBounds(200, 120, 500, 30);
        cashContentPanel.add(successLabel);
        successLabel.setVisible(false); // Initially hide the success label

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int amount= Integer.parseInt(amountField.getText());
                    final int[] tickAnimationCounter = {0};

                    // Check if the card ID is valid

                    if (amount >= price) {
                        int balance = amount - price;

                        // Create a custom panel with animation
                        JPanel customPanel = new JPanel() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                // Draw the white circle
                                g.setColor(Color.WHITE);
                                int circleDiameter = 100; // Diameter of the circle
                                int circleX = (getWidth() - circleDiameter) / 2; // X-coordinate to center the circle
                                int circleY = (getHeight() - circleDiameter) / 2; // Y-coordinate to center the circle
                                g.fillOval(circleX, circleY, circleDiameter, circleDiameter);

                                // Draw the tick mark
                                if (tickAnimationCounter[0] > 0) {
                                    g.setColor(Color.GREEN);
                                    Graphics2D g2d = (Graphics2D) g;
                                    g2d.setStroke(new BasicStroke(3));
                                    int[] xPoints = {circleX + 20, circleX + 40, circleX + 80};
                                    int[] yPoints = {circleY + 50, circleY + 70, circleY + 10};
                                    g2d.drawPolyline(xPoints, yPoints, xPoints.length);
                                }
                            }
                        };
                        // Set layout manager for customPanel as BoxLayout with vertical alignment
                        // customPanel.setLayout(new BoxLayout(customPanel, BoxLayout.Y_AXIS));

// Add JLabel for displaying payment success message
                        JLabel paymentLabel = new JLabel("PAYMENT SUCCESSFULL ");
                        paymentLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
                        JLabel paymentLabel1 = new JLabel("TAKE YOUR BALANCE:" + balance);
                        paymentLabel1.setBounds(30,90,90,10); // Center align the text

// Add the paymentLabel and paymentLabel1 to the customPanel
                        customPanel.add(paymentLabel);
                        customPanel.add(paymentLabel1);
                        // Set preferred size for the custom panel
                        customPanel.setPreferredSize(new Dimension(300, 200)); // Set preferred width and height as needed

                        JOptionPane paymentDialog = new JOptionPane(customPanel, JOptionPane.PLAIN_MESSAGE);
                        JDialog dialog = paymentDialog.createDialog(null, "Payment Status");

                        // Counter for animation steps

                        // Create a Timer to animate the tick mark
                        int animationDuration = 4000; // Duration of the animation in milliseconds
                        int animationSteps = 30; // Number of animation steps
                        Timer animationTimer = new Timer(animationDuration / animationSteps, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                tickAnimationCounter[0]++;
                                customPanel.repaint(); // Repaint the panel to update the animation
                                if (tickAnimationCounter[0] >= animationSteps) {
                                    ((Timer) e.getSource()).stop(); // Stop the animation timer
                                    dialog.dispose(); // Close the dialog after animation
                                }
                            }
                        });
                        animationTimer.start();

                        dialog.setVisible(true); // Show the dialog

                        // Update the dispense label to "TAKE YOUR DRINK"
                        dispensePanel.remove(dispenseLabel);
                        dispensePanel.revalidate();
                        dispenseLabel = new JLabel("TAKE YOUR DRINK");
                        dispenseLabel.setBounds(100, 0, 850, 20);
                        dispenseLabel.setForeground(new Color(0xedbe65));
                        dispenseLabel.setFont(new Font("times new roman", Font.BOLD, 20)); // Font, style, size
                        dispensePanel.add(dispenseLabel);
                        dispensePanel.revalidate();

                        // Start a timer to switch back to "Your drink will be dispensed here" after 5 seconds
                        Timer timer0 = new Timer(10000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dispensePanel.remove(dispenseLabel);
                                dispensePanel.revalidate();
                                dispenseLabel = new JLabel("YOUR DRINK WILL BE DISPENSED HERE");
                                dispenseLabel.setBounds(100, 0, 850, 20);
                                dispenseLabel.setForeground(new Color(0xedbe65));
                                dispenseLabel.setFont(new Font("times new roman", Font.BOLD, 20)); // Font, style, size
                                dispensePanel.add(dispenseLabel);
                                dispensePanel.revalidate();

                            }
                        });

                        Timer timer1 = new Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                main_panel.remove(productPanel);
                                main_panel.add(productPanel);
                                main_panel.revalidate();
                                main_panel.repaint();
                                main_panel.remove(paymentPanel);
                                paymentPanel.remove(paymentContentPanel);
                                cashPanel.remove(cashContentPanel);
                                main_panel.remove(cashPanel);
                                cashPanel.repaint();
                                cashPanel.revalidate();
                                cardPanel.remove(cardContentPanel);
                                main_panel.remove(cardPanel);
                                cardPanel.revalidate();
                                cardPanel.repaint();
                                main_panel.repaint();
                                main_panel.revalidate();
                                paymentPanel.revalidate();
                            }
                        });

                        timer1.setRepeats(false); // Run the timer only once
                        timer1.start();
                        timer0.setRepeats(false); // Run the timer only once
                        timer0.start();
                        // ... (existing code for timers and updating dispense label) ...

                        java.util.Date currentDate = new java.util.Date();
                        java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDate.getTime());

                        String url = "jdbc:mysql://localhost:3306/vending_machine";
                        String username = "root";
                        String password = "nitesh97903";
                        Connection connection = null;

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            connection = DriverManager.getConnection(url, username, password);
                            System.out.println("Database connection established.");

                            // Assuming you have the necessary values for userId, productId, and quantity
                            //int userId = 1234; // Replace with the appropriate value
                            //int productId = 1; // Replace with the appropriate value
                            //int quantity = 1; // Replace with the appropriate value

                            // Disable foreign key checks temporarily
                            Statement statement = connection.createStatement();
                            statement.execute("SET FOREIGN_KEY_CHECKS = 0");

                            // Insert into Sales_Log table
                            String insertQuery = "INSERT INTO Sales_Log (ProductId, ProductName,Sales_Date, Sales_TimeStamp, Entered_Amount, Balance_Returned) VALUES (?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                            //preparedStatement.setInt(1, userId); // Set the User_Id
                            preparedStatement.setInt(1, productID); // Set the ProductId
                            preparedStatement.setString(2, ProductName);
                            preparedStatement.setDate(3, new java.sql.Date(currentDate.getTime())); // Set the Sales_Date
                            preparedStatement.setTimestamp(4, timestamp); // Set the Sales_TimeStamp
                            preparedStatement.setInt(5, amount); // Set the Entered_Amount (correct data type)
                            preparedStatement.setInt(6, balance); // Set the Balance_Returened

                            System.out.println("Executing SQL query: " + preparedStatement);

                            try {
                                int rowsInserted = preparedStatement.executeUpdate();
                                System.out.println("Rows inserted: " + rowsInserted);

                                // Update the Available_Quantity in the Product table
                                String updateQuery = "UPDATE Product SET Available_Quantity = Available_Quantity - 1 WHERE Product_Id = ?";
                                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                                updateStatement.setInt(1, productID); // Set the Product_Id

                                int rowsUpdated = updateStatement.executeUpdate();
                                System.out.println("Rows updated in Product table: " + rowsUpdated);
                            } catch (SQLException ex) {
                                System.out.println("SQL Exception: " + ex.getMessage());
                                System.out.println("SQL State: " + ex.getSQLState());
                                ex.printStackTrace();
                            }

                            // Re-enable foreign key checks
                            statement.execute("SET FOREIGN_KEY_CHECKS = 1");

                            // Rest of your code for removing panels
                        } catch (ClassNotFoundException | SQLException ex) {
                            ex.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                    System.out.println("Database connection closed.");
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }
                    else {
                        JPanel customPanel = new JPanel(new GridLayout(2, 1)); // Create custom panel with GridLayout
                        // Add JLabels to display messages
                        JLabel messageLabel1 = new JLabel("AMOUNT IS NOT SUFFICIENT!", SwingConstants.CENTER);
                        JLabel messageLabel2 = new JLabel("Please enter a valid AMOUNT.", SwingConstants.CENTER);
                        // Set font for error message
                        messageLabel1.setFont(new Font("Arial", Font.BOLD, 16));
                        messageLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
                        // Add message labels to the custom panel
                        customPanel.add(messageLabel1);
                        customPanel.add(messageLabel2);
                        // Create a custom JOptionPane dialog
                        JOptionPane optionPane = new JOptionPane(customPanel, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        // Create a JDialog and set the option pane as its content pane
                        JDialog dialog = optionPane.createDialog("Error Message");

                        // Create a Timer to close the dialog after 2 seconds
                        Timer timer = new Timer(3000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                            }
                        });
                        timer.setRepeats(false); // Set to false to run only once
                        timer.start();

                        dialog.setVisible(true);
                        return; // Exit the method if the card ID is invalid
                    }

                } catch (NumberFormatException ex) {
                    JPanel customPanel = new JPanel(new GridLayout(2, 1)); // Create custom panel with GridLayout
                    // Add JLabels to display messages
                    JLabel messageLabel1 = new JLabel("AMOUNT NOT ENTERED", SwingConstants.CENTER);
                    JLabel messageLabel2 = new JLabel("Please enter a AMOUNT.", SwingConstants.CENTER);
                    // Set font for error message
                    messageLabel1.setFont(new Font("Arial", Font.BOLD, 16));
                    messageLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
                    // Add message labels to the custom panel
                    customPanel.add(messageLabel1);
                    customPanel.add(messageLabel2);
                    // Create a custom JOptionPane dialog
                    JOptionPane optionPane = new JOptionPane(customPanel, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    // Create a JDialog and set the option pane as its content pane
                    JDialog dialog = optionPane.createDialog("Error Message");

                    // Create a Timer to close the dialog after 2 seconds
                    Timer timer = new Timer(3000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose();
                        }
                    });
                    timer.setRepeats(false); // Set to false to run only once
                    timer.start();

                    dialog.setVisible(true);
                    return; // Exit the method if the card ID is invalid
                }
                finally {
                    amountField.setText("");
                }
            }
        });
        return cashContentPanel;
    }


    private JPanel cardPanelContents(int productID, int price, String productName) {
        JPanel cardContentPanel = new JPanel();
        cardContentPanel.setLayout(null);
        cardContentPanel.setPreferredSize(new Dimension(850, 200));
        cardContentPanel.setBackground(new Color(0xffffff));

        JLabel cardLabel = new JLabel("  CARD");
        cardLabel.setBounds(20, 10, 50, 30);
        cardContentPanel.add(cardLabel);

        JLabel productPriceLabel = new JLabel("Product price: " + price);
        productPriceLabel.setBounds(250, 30, 250, 30);
        cardContentPanel.add(productPriceLabel);

        JLabel amntLabel = new JLabel("ENTER THE CARD ID :");
        amntLabel.setBounds(10, 82, 250, 20);
        cardContentPanel.add(amntLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(150, 80, 200, 30);
        cardContentPanel.add(amountField);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(380, 80, 100, 30);
        cardContentPanel.add(payButton);

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "nitesh97903";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int access_cardId = Integer.parseInt(amountField.getText());
                    final int[] tickAnimationCounter = {0};

                    // Check if the card ID is valid
                    int balance = getBalanceFromDatabase(access_cardId);
                    if (balance == 0) {
                        displayInvalidCardIdDialog();
                        return;
                    }

                    // Perform debit operation if the card ID is valid and balance is sufficient
                    if (balance >= price) {
                        balance -= price;

                        // Create a custom panel with animation
                        JPanel customPanel = new JPanel() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                // Draw the white circle
                                g.setColor(Color.WHITE);
                                int circleDiameter = 100; // Diameter of the circle
                                int circleX = (getWidth() - circleDiameter) / 2; // X-coordinate to center the circle
                                int circleY = (getHeight() - circleDiameter) / 2; // Y-coordinate to center the circle
                                g.fillOval(circleX, circleY, circleDiameter, circleDiameter);

                                // Draw the tick mark
                                if (tickAnimationCounter[0] > 0) {
                                    g.setColor(Color.GREEN);
                                    Graphics2D g2d = (Graphics2D) g;
                                    g2d.setStroke(new BasicStroke(3));
                                    int[] xPoints = {circleX + 20, circleX + 40, circleX + 80};
                                    int[] yPoints = {circleY + 50, circleY + 70, circleY + 10};
                                    g2d.drawPolyline(xPoints, yPoints, xPoints.length);
                                }
                            }
                        };

                        // Set layout manager for customPanel as BoxLayout with vertical alignment
                        // customPanel.setLayout(new BoxLayout(customPanel, BoxLayout.Y_AXIS));

// Add JLabel for displaying payment success message
                        JLabel paymentLabel = new JLabel("PAYMENT SUCCESSFULL...... ");
                        paymentLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
                        JLabel paymentLabel1 = new JLabel("YOUR CARD BALANCE:" + balance);
                        paymentLabel1.setBounds(30,90,90,10); // Center align the text

// Add the paymentLabel and paymentLabel1 to the customPanel
                        customPanel.add(paymentLabel);
                        customPanel.add(paymentLabel1);
                        // Set preferred size for the custom panel
                        customPanel.setPreferredSize(new Dimension(300, 200)); // Set preferred width and height as needed

                        JOptionPane paymentDialog = new JOptionPane(customPanel, JOptionPane.PLAIN_MESSAGE);
                        JDialog dialog = paymentDialog.createDialog(null, "Payment Status");

                        // Counter for animation steps

                        // Create a Timer to animate the tick mark
                        int animationDuration = 4000; // Duration of the animation in milliseconds
                        int animationSteps = 30; // Number of animation steps
                        Timer animationTimer = new Timer(animationDuration / animationSteps, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                tickAnimationCounter[0]++;
                                customPanel.repaint(); // Repaint the panel to update the animation
                                if (tickAnimationCounter[0] >= animationSteps) {
                                    ((Timer) e.getSource()).stop(); // Stop the animation timer
                                    dialog.dispose(); // Close the dialog after animation
                                }
                            }
                        });
                        animationTimer.start();

                        dialog.setVisible(true); // Show the dialog

                        // Update the dispense label to "TAKE YOUR DRINK"
                        dispensePanel.remove(dispenseLabel);
                        dispensePanel.revalidate();
                        dispenseLabel = new JLabel("TAKE YOUR DRINK");
                        dispenseLabel.setBounds(100, 0, 850, 20);
                        dispenseLabel.setForeground(new Color(0xedbe65));
                        dispenseLabel.setFont(new Font("times new roman", Font.BOLD, 20)); // Font, style, size
                        dispensePanel.add(dispenseLabel);
                        dispensePanel.revalidate();

                        // Start a timer to switch back to "Your drink will be dispensed here" after 5 seconds
                        Timer timer0 = new Timer(10000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dispensePanel.remove(dispenseLabel);
                                dispensePanel.revalidate();
                                dispenseLabel = new JLabel("YOUR DRINK WILL BE DISPENSED HERE");
                                dispenseLabel.setBounds(100, 0, 850, 20);
                                dispenseLabel.setForeground(new Color(0xedbe65));
                                dispenseLabel.setFont(new Font("times new roman", Font.BOLD, 20)); // Font, style, size
                                dispensePanel.add(dispenseLabel);
                                dispensePanel.revalidate();

                            }
                        });

                        Timer timer1 = new Timer(500, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                main_panel.remove(productPanel);
                                main_panel.add(productPanel);
                                main_panel.revalidate();
                                main_panel.repaint();
                                main_panel.remove(paymentPanel);
                                paymentPanel.remove(paymentContentPanel);
                                cashPanel.remove(cashContentPanel);
                                main_panel.remove(cashPanel);
                                cashPanel.repaint();
                                cashPanel.revalidate();
                                cardPanel.remove(cardContentPanel);
                                main_panel.remove(cardPanel);
                                cardPanel.revalidate();
                                cardPanel.repaint();
                                main_panel.repaint();
                                main_panel.revalidate();
                                paymentPanel.revalidate();
                            }
                        });

                        timer1.setRepeats(false); // Run the timer only once
                        timer1.start();
                        timer0.setRepeats(false); // Run the timer only once
                        timer0.start();
                        // Update the balance in the database
                        updateBalanceInDatabase(access_cardId, balance);

                        java.util.Date currentDate = new java.util.Date();
                        java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDate.getTime());

                        String url = "jdbc:mysql://localhost:3306/vending_machine";
                        String username = "root";
                        String password = "nitesh97903";
                        Connection connection = null;

                        try
                        {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            connection = DriverManager.getConnection(url, username, password);
                            System.out.println("Database connection established.");

                            // Assuming you have the necessary values for userId, productId, and quantity
                            int userId = access_cardId; // Replace with the appropriate value
                            //int productId = 1; // Replace with the appropriate value
                            // int quantity = 1; // Replace with the appropriate value
                            int amount = 0;
                            int baalance = 0;



                            // Start a transaction
                            connection.setAutoCommit(false);

                            // Insert into Sales_Log table
                            String insertQuery = "INSERT INTO Sales_Log (User_Id, ProductId, ProductName, Sales_Date, Sales_TimeStamp, Entered_Amount, Balance_Returned) VALUES (?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                            preparedStatement.setInt(1, userId); // Set the User_Id
                            preparedStatement.setInt(2, productID); // Set the ProductId
                            preparedStatement.setString(3, productName);
                            preparedStatement.setDate(4, new java.sql.Date(currentDate.getTime())); // Set the Sales_Date
                            preparedStatement.setTimestamp(5, timestamp); // Set the Sales_TimeStamp
                            preparedStatement.setInt(6, amount); // Set the Entered_Amount (correct data type)
                            preparedStatement.setInt(7, baalance); // Set the Balance_Returned
                            System.out.println("Executing SQL query: " + preparedStatement);

                            // Execute the insert query and handle SQL exceptions
                            try {
                                int rowsInserted = preparedStatement.executeUpdate();
                                System.out.println("Rows inserted: " + rowsInserted);
                            } catch (SQLException ex) {
                                System.out.println("SQL Exception: " + ex.getMessage());
                                System.out.println("SQL State: " + ex.getSQLState());
                                ex.printStackTrace();
                                // Rollback the transaction if an error occurs
                                connection.rollback();
                            }

                            // Update the Available_Quantity in the Product table
                            // Update the Available_Quantity in the Product table
                            String updateQuery = "UPDATE Product SET Available_Quantity = Available_Quantity - ? WHERE Product_Id = ?";
                            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                            updateStatement.setInt(1, 1); // Set the quantity to be reduced
                            updateStatement.setInt(2, productID); // Set the Product_Id

                            // Execute the update query and handle SQL exceptions
                            try {
                                int rowsUpdated = updateStatement.executeUpdate();
                                System.out.println("Rows updated: " + rowsUpdated);
                                // Commit the transaction if both queries are successful
                                connection.commit();
                            } catch (SQLException ex) {
                                System.out.println("SQL Exception: " + ex.getMessage());
                                System.out.println("SQL State: " + ex.getSQLState());
                                ex.printStackTrace();
                                // Rollback the transaction if an error occurs
                                connection.rollback();
                            }
                        } catch (ClassNotFoundException | SQLException ex) {
                            ex.printStackTrace();
                        } finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                    System.out.println("Database connection closed.");
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    } else {
                        displayInsufficientBalanceDialog();
                    }
                } catch (NumberFormatException ex) {
                    displayEmptyCardIdDialog();
                } finally {
                    amountField.setText("");
                }
            }
        });

        return cardContentPanel;
    }

    private int getBalanceFromDatabase(int cardId) {
        int balance = 0;
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "nitesh97903";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT BalanceAmount FROM CardHolder_Details WHERE Access_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, cardId);

            rs = stmt.executeQuery();

            if (rs.next()) {
                balance = rs.getInt("BalanceAmount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return balance;
    }

    private void updateBalanceInDatabase(int cardId, int newBalance) {
        String url = "jdbc:mysql://localhost:3306/vending_machine";
        String username = "root";
        String password = "nitesh97903";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(url, username, password);

            String query = "UPDATE CardHolder_Details SET BalanceAmount = ? WHERE Access_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, newBalance);
            stmt.setInt(2, cardId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayInvalidCardIdDialog() {
        JPanel customPanel = new JPanel(new GridLayout(2, 1));
        JLabel messageLabel1 = new JLabel("Invalid card ID!", SwingConstants.CENTER);
        JLabel messageLabel2 = new JLabel("Please enter a valid card ID.", SwingConstants.CENTER);
        messageLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        customPanel.add(messageLabel1);
        customPanel.add(messageLabel2);
        JOptionPane optionPane = new JOptionPane(customPanel, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog("Error Message");
        Timer timer = new Timer(3000, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private void displayInsufficientBalanceDialog() {
        JPanel customPanel = new JPanel(new GridLayout(2, 1));
        JLabel messageLabel1 = new JLabel("Insufficient Balance!", SwingConstants.CENTER);
        JLabel messageLabel2 = new JLabel("Please check your balance.", SwingConstants.CENTER);
        messageLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        customPanel.add(messageLabel1);
        customPanel.add(messageLabel2);
        JOptionPane optionPane = new JOptionPane(customPanel, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog("Error Message");
        Timer timer = new Timer(3000, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private void displayEmptyCardIdDialog() {
        JPanel customPanel = new JPanel(new GridLayout(2, 1));
        JLabel messageLabel1 = new JLabel("Card ID Not Entered!", SwingConstants.CENTER);
        JLabel messageLabel2 = new JLabel("Please enter a valid card ID.", SwingConstants.CENTER);
        messageLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        customPanel.add(messageLabel1);
        customPanel.add(messageLabel2);
        JOptionPane optionPane = new JOptionPane(customPanel, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog("Error Message");
        Timer timer = new Timer(3000, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new project_vending_machine();
    }
}
