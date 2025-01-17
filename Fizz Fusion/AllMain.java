package Vending_Machine;

import javax.swing.*;
import java.awt.*;

public class AllMain extends javax.swing.JFrame {

    public AllMain() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setUndecorated(true); // Set this after making the window visible
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        background = new javax.swing.JLabel();
        gotovending = new javax.swing.JLabel();
        gotosalesperson = new javax.swing.JLabel();
        gotoadmin1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Set the background image for the background label
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\ANUGRAHA.VS\\Downloads\\Coca Cola SmileWorld City.jpeg");
        background.setIcon(backgroundImage);
        background.setLayout(new BorderLayout());

        gotovending.setBackground(new java.awt.Color(0, 0, 0, 80));
        gotovending.setForeground(new java.awt.Color(51, 51, 51));
        gotovending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gotovending.setText("In a world full of options, choose flavor");
        gotovending.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gotovending.setOpaque(true);
        gotovending.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gotovendingMouseClicked(evt);
            }
        });

        gotosalesperson.setBackground(new java.awt.Color(0, 0, 0, 80));
        gotosalesperson.setText("Salesperson Login");
        gotosalesperson.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gotosalesperson.setOpaque(true);

        gotoadmin1.setBackground(new java.awt.Color(0, 0, 0, 80));
        gotoadmin1.setText("Admin Login");
        gotoadmin1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gotoadmin1.setOpaque(true);
        gotoadmin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gotoadmin1MouseClicked(evt);
            }
        });

        background.add(gotovending, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 10, 0);
        bottomPanel.add(gotoadmin1, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 10, 10);
        bottomPanel.add(gotosalesperson, gbc);

        background.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(background);

        pack();
    }

    private void gotovendingMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void gotoadmin1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new AllMain().setVisible(true));
    }

    private javax.swing.JLabel background;
    private javax.swing.JLabel gotoadmin1;
    private javax.swing.JLabel gotosalesperson;
    private javax.swing.JLabel gotovending;
}