package Vending_Machine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Cursor;
import java.util.Objects;


public class LoginForm extends JFrame {
    private ImageIcon logoIcon;
    private JLabel logoLabel;

    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Coca-Cola Sales Login");
        setLayout(new GridLayout(1, 2));

        // Left Panel: Logo
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());
        logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("\\video\\coke_2009.png")));
        logoLabel = new JLabel(logoIcon);
        logoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                animateLogo();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetLogo();
            }
        });
        leftPanel.add(logoLabel, BorderLayout.CENTER);
        add(leftPanel);

        // Right Panel: Login Form
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Welcome back salesperson, please login to continue");
        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rightPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(new JLabel()); // Placeholder for icon
        formPanel.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("\\video\\coke_2009.png")))));
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.RED);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        loginButton.setCursor(Cursor.getPredefinedCursor(12));

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(Color.RED);
                loginButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(Color.WHITE);
                loginButton.setForeground(Color.RED);
            }
        });
        rightPanel.add(formPanel, BorderLayout.CENTER);
        rightPanel.add(loginButton, BorderLayout.SOUTH);
        add(rightPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void animateLogo() {
        // Animate the logo by slightly scaling up
        Image scaledImage = logoIcon.getImage().getScaledInstance((int) (logoIcon.getIconWidth() * 1.05),
                (int) (logoIcon.getIconHeight() * 1.05), Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledImage));
    }

    private void resetLogo() {
        // Reset the logo to its original size
        logoLabel.setIcon(logoIcon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}