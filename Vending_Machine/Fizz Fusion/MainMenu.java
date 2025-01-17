import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {
    private JButton flavorButton;
    private JButton adminLoginButton;
    private JButton salespersonLoginButton;

    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in maximized mode
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        flavorButton = new JButton("In a world full of options, choose flavor");
        flavorButton.addActionListener(this);
        buttonPanel.add(flavorButton, gbc);

        gbc.gridy++;
        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.addActionListener(this);
        buttonPanel.add(adminLoginButton, gbc);

        gbc.gridy++;
        salespersonLoginButton = new JButton("Salesperson Login");
        salespersonLoginButton.addActionListener(this);
        buttonPanel.add(salespersonLoginButton, gbc);

        getContentPane().add(buttonPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == flavorButton) {
            // Code to handle "In a world full of options, choose flavor" button
            JOptionPane.showMessageDialog(this, "You chose to explore flavors!");
        } else if (e.getSource() == adminLoginButton) {
            // Code to handle "Admin Login" button
            JOptionPane.showMessageDialog(this, "Admin login functionality goes here.");
        } else if (e.getSource() == salespersonLoginButton) {
            // Code to handle "Salesperson Login" button
            JOptionPane.showMessageDialog(this, "Salesperson login functionality goes here.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu frame = new MainMenu();
            frame.setVisible(true);
        });
    }
}