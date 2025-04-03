package gm.events;

import javax.swing.*;
import java.awt.*;

/**
 * The MainWindow class represents the main application window containing a DrawingPanel
 * and a JTextField to display coordinates of mouse clicks.
 */
public class MainWindow extends JFrame {

    private DrawingPanel drawingPanel;
    private JTextField textField;

    /**
     * The main method to launch the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    /**
     * Constructs a MainWindow with a default title.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless() returns true
     */
    public MainWindow() throws HeadlessException {
        this("Events");
    }

    /**
     * Constructs a MainWindow with the specified title.
     *
     * @param title the title of the window
     * @throws HeadlessException if GraphicsEnvironment.isHeadless() returns true
     */
    public MainWindow(String title) throws HeadlessException {
        super(title);
        buildFrame();
    }

    /**
     * Builds the frame by setting its properties and adding components.
     */
    protected void buildFrame() {
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField();
        textField.setEditable(false);
        getContentPane().add(textField, BorderLayout.SOUTH);

        drawingPanel = new DrawingPanel(this);
        getContentPane().add(drawingPanel, BorderLayout.CENTER);
    }

    /**
     * Returns the JTextField used to display coordinates of mouse clicks.
     *
     * @return the JTextField
     */
    public JTextField getTextField() {
        return textField;
    }
}