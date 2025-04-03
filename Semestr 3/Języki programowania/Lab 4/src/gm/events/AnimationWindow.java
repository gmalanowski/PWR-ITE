package gm.events;

import javax.swing.*;
import java.awt.*;

/**
 * The AnimationWindow class represents the main application window for simple animation.
 */
public class AnimationWindow extends JFrame {

    /**
     * The main method to launch the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    AnimationWindow window = new AnimationWindow();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    /**
     * Constructs an AnimationWindow with a default title.
     */
    public AnimationWindow() {
        this("Simple Animation");
    }

    /**
     * Constructs an AnimationWindow with the specified title.
     *
     * @param title the title of the window
     */
    public AnimationWindow(String title) {
        super(title);
        buildFrame();
    }

    /**
     * Builds the frame by setting its properties and adding components.
     */
    protected void buildFrame() {
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnimationPanel animationPanel = new AnimationPanel();
        getContentPane().add(animationPanel, BorderLayout.CENTER);
    }
}
