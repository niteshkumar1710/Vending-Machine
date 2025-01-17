package Vending_Machine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class temp extends JFrame implements ActionListener {
    private JLabel displayLabel;
    private JButton[] selectionButtons;
    private double[] prices = {1.0, 1.5, 2.0}; // Prices for items

    public temp() {
        setTitle("Vending Machine");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayLabel = new JLabel("Select an item:");
        add(displayLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        selectionButtons = new JButton[3];

        for (int i = 0; i < selectionButtons.length; i++) {
            selectionButtons[i] = new JButton("drink " + (i + 1) + " (RS" + prices[i] + ")");
            selectionButtons[i].addActionListener(this);
            buttonPanel.add(selectionButtons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }


    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String buttonText = source.getText();

        // Extract the item number from the button text
        int itemNumber = Integer.parseInt(buttonText.substring(6, 7));

        // Display message
        JOptionPane.showMessageDialog(this, "You have selected Drink " + itemNumber + ". Please insert RS" + prices[itemNumber - 1]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new temp().setVisible(true);
        });
    }
}