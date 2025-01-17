package Vendiing_Machine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sample extends JFrame implements ActionListener {
    private JButton redButton;
    private JButton greenButton;
    private JButton blueButton;
    private JPanel colorPanel;

    public Sample() {
        setTitle("Color Changer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        redButton = new JButton("Red");
        redButton.addActionListener(this);
        buttonPanel.add(redButton, gbc);

        gbc.gridy++;
        greenButton = new JButton("Green");
        greenButton.addActionListener(this);
        buttonPanel.add(greenButton, gbc);

        gbc.gridy++;
        blueButton = new JButton("Blue");
        blueButton.addActionListener(this);
        buttonPanel.add(blueButton, gbc);

        colorPanel = new JPanel();
        colorPanel.setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(colorPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == redButton) {
            colorPanel.setBackground(Color.RED);
        } else if (e.getSource() == greenButton) {
            colorPanel.setBackground(Color.GREEN);
        } else if (e.getSource() == blueButton) {
            colorPanel.setBackground(Color.BLUE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Sample frame = new Sample();
            frame.setVisible(true);
        });
    }
}