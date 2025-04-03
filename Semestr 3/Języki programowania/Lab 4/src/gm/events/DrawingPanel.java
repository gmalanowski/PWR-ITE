package gm.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The DrawingPanel class is a custom JPanel that allows users to draw and manipulate shapes
 * using mouse and keyboard events.
 */
public class DrawingPanel extends JPanel {

    private Shape currentShape;
    private Shape previousShape;
    private long lastClickTime = 0;
    private Color defaultColor = Color.RED;
    private Color previousColor = Color.RED;
    private final JTextField textField;
    private boolean isDrawing = false;
    private boolean isDragging = false;
    private Point startPoint = null;
    private Point initialClickPoint;

    /**
     * Constructs a DrawingPanel with a reference to the MainWindow.
     *
     * @param mainWindow the main window containing this panel
     */
    public DrawingPanel(MainWindow mainWindow) {
        textField = mainWindow.getTextField();
        currentShape = new Shape(200, 200, 100, 100);
        previousShape = new Shape(200, 200, 100, 100);
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("Clicked at: (" + e.getX() + ", " + e.getY() + ")");
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastClickTime < 500) {
                    moveShape(e.getX(), e.getY(), currentTime - lastClickTime);
                }
                lastClickTime = currentTime;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (currentShape.getBounds().contains(e.getPoint())) {
                    isDragging = true;
                    initialClickPoint = e.getPoint();
                } else {
                    startPoint = e.getPoint();
                    isDrawing = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDrawing = false;
                isDragging = false;
                System.out.println("Mouse button released at: (" + e.getX() + ", " + e.getY() + ")");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Cursor exited the drawing area");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Cursor entered the drawing area");
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDrawing) {
                    int x = Math.min(startPoint.x, e.getX());
                    int y = Math.min(startPoint.y, e.getY());
                    int width = Math.abs(startPoint.x - e.getX());
                    int height = Math.abs(startPoint.y - e.getY());
                    currentShape = new Shape(x, y, width, height);
                    repaint();
                } else if (isDragging) {
                    int dx = e.getX() - initialClickPoint.x;
                    int dy = e.getY() - initialClickPoint.y;
                    currentShape.setLocation(currentShape.getBounds().x + dx, currentShape.getBounds().y + dy);
                    initialClickPoint = e.getPoint();
                    repaint();
                }
            }
        });

        addMouseWheelListener(e -> resizeShape(e.getWheelRotation()));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyRelease(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(defaultColor);
        g.fillRect(currentShape.getBounds().x, currentShape.getBounds().y,
                currentShape.getBounds().width, currentShape.getBounds().height);
    }

    /**
     * Moves the shape to the specified coordinates if the elapsed time is greater than 0.
     *
     * @param x the x-coordinate to move the shape to
     * @param y the y-coordinate to move the shape to
     * @param elapsedTime the time elapsed since the last click
     */
    private void moveShape(int x, int y, long elapsedTime) {
        if (elapsedTime > 0) {
            currentShape.setLocation(x - currentShape.getBounds().width / 2, y - currentShape.getBounds().height / 2);
            repaint();
        }
    }

    /**
     * Resizes the shape based on the mouse wheel rotation.
     *
     * @param wheelRotation the amount of wheel rotation
     */
    private void resizeShape(int wheelRotation) {
        int newWidth = currentShape.getBounds().width + (wheelRotation * 5);
        int newHeight = currentShape.getBounds().height + (wheelRotation * 5);
        if (newWidth > 0 && newHeight > 0) {
            currentShape.setSize(newWidth, newHeight);
            repaint();
        }
    }

    /**
     * Handles key press events to move the shape or change its color.
     *
     * @param e the key event
     */
    private void handleKeyPress(KeyEvent e) {
        int x = currentShape.getBounds().x;
        int y = currentShape.getBounds().y;

        previousShape = new Shape(currentShape.getBounds().x, currentShape.getBounds().y,
                currentShape.getBounds().width, currentShape.getBounds().height);
        previousColor = defaultColor;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentShape.setLocation(x, y - 10);
                break;
            case KeyEvent.VK_DOWN:
                currentShape.setLocation(x, y + 10);
                break;
            case KeyEvent.VK_LEFT:
                currentShape.setLocation(x - 10, y);
                break;
            case KeyEvent.VK_RIGHT:
                currentShape.setLocation(x + 10, y);
                break;
            case KeyEvent.VK_ENTER:
                textField.setText("");
                break;
            case KeyEvent.VK_SHIFT:
                defaultColor = Color.GREEN;
                break;
            case KeyEvent.VK_CONTROL:
                defaultColor = Color.BLUE;
                break;
        }
        repaint();
    }

    /**
     * Handles key release events to reset the shape's color.
     *
     * @param e the key event
     */
    private void handleKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CONTROL) {
            defaultColor = previousColor;
            currentShape = previousShape;
            repaint();
        }
    }
}