package Vending_Machine;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class barchart_example extends JFrame {

    private int[] data;
    private Color[] colors;

    public barchart_example() {
        setTitle("Bar Chart Example");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Generate random data for the bar chart
        Random random = new Random();
        data = new int[10];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100); // Random values between 0 and 100
        }

        // Define colors for the bars
        colors = new Color[]{
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
                Color.CYAN, Color.MAGENTA, Color.PINK, Color.LIGHT_GRAY, Color.DARK_GRAY
        };

        // Create a panel for drawing the bar chart
        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBarChart(g);
            }
        };

        // Add the chart panel to the frame
        add(chartPanel);
    }

    private void drawBarChart(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        int barWidth = width / data.length;

        // Find the maximum value in the data
        int maxValue = Integer.MIN_VALUE;
        for (int value : data) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        // Calculate the scale factor for the bars
        double scaleFactor = (double) height / maxValue;

        // Draw the bars
        for (int i = 0; i < data.length; i++) {
            int barHeight = (int) (data[i] * scaleFactor);
            int x = i * barWidth;
            int y = height - barHeight;
            g.setColor(colors[i % colors.length]); // Use different colors for each bar
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            barchart_example example = new barchart_example();
            example.setVisible(true);
        });
    }
}
