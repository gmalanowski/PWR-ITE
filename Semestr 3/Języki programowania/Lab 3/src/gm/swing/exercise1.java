package gm.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class exercise1 extends JFrame {

    // Main panel where the color will be changed
    private final JPanel panel = new JPanel();
    // Panel for input components
    private final JPanel inputPanel = new JPanel();
    // Label to display messages
    private final JLabel message = new JLabel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    exercise1 window = new exercise1();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to set the title of the JFrame
    public exercise1() throws HeadlessException {
        this("JPanel color change");
    }

    // Constructor with title parameter
    public exercise1(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    // Method to change the color of the panel based on the input string
    private void changePanelColor(String colorName) {
        colorName = colorName.trim().toLowerCase();
        switch (colorName) {
            case "red", "czerwony":
                panel.setBackground(Color.RED);
                message.setText("");
                break;
            case "green", "zielony":
                panel.setBackground(Color.GREEN);
                message.setText("");
                break;
            case "blue", "niebieski":
                panel.setBackground(Color.BLUE);
                message.setText("");
                break;
            default:
                message.setText("Unknown color!");
                message.setForeground(Color.RED);
                break;
        }
    }

    // Method to build the JFrame
    protected void buildFrame() {
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set initial background color of the panel
        panel.setBackground(Color.WHITE);

        // Text field for color input
        JTextField colorInput = new JTextField(15);
        // Button to trigger color change
        JButton changeColorButton = new JButton("Change color");

        // Add action listener to the button
        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanelColor(colorInput.getText());
            }
        });

        // Add the main panel to the center of the frame
        add(panel, BorderLayout.CENTER);

        // Add input components to the input panel
        inputPanel.add(colorInput);
        inputPanel.add(changeColorButton);
        inputPanel.add(message);

        // Add the input panel to the bottom of the frame
        add(inputPanel, BorderLayout.SOUTH);
    }
}