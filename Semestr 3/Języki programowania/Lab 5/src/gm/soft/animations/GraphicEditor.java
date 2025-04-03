package gm.soft.animations;

import javax.swing.*;
import java.awt.*;

/**
 * Main class for the Graphic Editor application.
 */
public class GraphicEditor extends JFrame {

    /**
     * Main method to launch the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    GraphicEditor window = new GraphicEditor();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    /**
     * Constructs a new GraphicEditor with the default title.
     */
    public GraphicEditor() {
        this("Graphic Editor");
    }

    /**
     * Constructs a new GraphicEditor with the specified title.
     *
     * @param title the title of the window
     */
    public GraphicEditor(String title) {
        super(title);
        buildFrame();
    }

    /**
     * Builds the main frame of the application.
     */
    protected void buildFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingPanel drawingPanel = new DrawingPanel(800, 600);
        add(drawingPanel, BorderLayout.CENTER);

        JPanel controls = new JPanel();

        JButton loadButton = new JButton("Load Image");
        loadButton.addActionListener(e -> drawingPanel.loadImage());

        JButton blurButton = new JButton("Blur");
        blurButton.addActionListener(e -> drawingPanel.applyEffect(new BlurEffect()));

        JButton colorShiftButton = new JButton("Color Shift");
        colorShiftButton.addActionListener(e -> drawingPanel.applyEffect(new ColorShiftEffect()));

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            drawingPanel.clearImage();
            drawingPanel.repaint();
        });

        controls.add(loadButton);
        controls.add(blurButton);
        controls.add(colorShiftButton);
        controls.add(clearButton);
        add(controls, BorderLayout.SOUTH);

        pack();
    }
}