package gm.soft.animations;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel for drawing and applying effects to an image.
 */
public class DrawingPanel extends JPanel {
    private BufferedImage image;
    private final Object imageLock = new Object();
    private int currentColorIndex = 0;
    private final Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA};
    private Timer timer;

    /**
     * Constructs a new DrawingPanel with the specified width and height.
     *
     * @param width  the width of the panel
     * @param height the height of the panel
     */
    public DrawingPanel(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        clearImage();
        setPreferredSize(new Dimension(width, height));
        setDoubleBuffered(true);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawOnImage(e.getX(), e.getY());
            }
        });

        timer = new Timer(16, e -> repaint());
        timer.start();
    }

    /**
     * Draws on the image at the specified coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void drawOnImage(int x, int y) {
        synchronized (imageLock) {
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(colors[currentColorIndex]);
            g2d.fillOval(x - 5, y - 5, 10, 10);
            g2d.dispose();
        }
        currentColorIndex = (currentColorIndex + 1) % colors.length;
    }

    /**
     * Clears the image by filling it with white color.
     */
    public void clearImage() {
        synchronized (imageLock) {
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
            g2d.dispose();
        }
    }

    /**
     * Applies the specified effect to the image.
     *
     * @param effect the effect to apply
     */
    public void applyEffect(Effect effect) {
        new Thread(() -> {
            effect.apply(image, imageLock);
        }).start();
    }

    /**
     * Loads an image from a file chosen by the user.
     */
    public void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "png", "jpg", "jpeg", "bmp"));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage loadedImage = ImageIO.read(file);

                synchronized (imageLock) {
                    image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = image.createGraphics();
                    g2d.drawImage(loadedImage, 0, 0, image.getWidth(), image.getHeight(), null);
                    g2d.dispose();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to load image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (imageLock) {
            g.drawImage(image, 0, 0, null);
        }
    }
}