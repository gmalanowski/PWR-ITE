package gm.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The AnimationPanel class is a JPanel that supports simple animations
 * by moving figures based on mouse clicks.
 */
public class AnimationPanel extends JPanel {

    private final List<Rectangle> shapes;
    private long lastClickTime = 0;
    private Rectangle selectedShape = null;

    /**
     * Constructs an AnimationPanel with initial shapes.
     */
    public AnimationPanel() {
        shapes = new ArrayList<>();
        shapes.add(new Rectangle(100, 100, 50, 50));
        shapes.add(new Rectangle(200, 200, 60, 40));
        shapes.add(new Rectangle(300, 150, 40, 70));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Rectangle shape : shapes) {
            g.setColor(Color.BLUE);
            g.fillRect(shape.x, shape.y, shape.width, shape.height);
        }

        if (selectedShape != null) {
            g.setColor(Color.RED);
            g.drawRect(selectedShape.x, selectedShape.y, selectedShape.width, selectedShape.height);
        }
    }

    /**
     * Handles mouse click events to animate shapes.
     *
     * @param e the mouse event
     */
    private void handleMouseClick(MouseEvent e) {
        long currentTime = System.currentTimeMillis();

        for (Rectangle shape : shapes) {
            if (shape.contains(e.getPoint())) {
                if (selectedShape != null && selectedShape.equals(shape)) {
                    long elapsedTime = currentTime - lastClickTime;
                    int dx = (int) (elapsedTime / 10.0); // Move distance depends on elapsed time
                    shape.setLocation(shape.x + dx, shape.y + dx);
                }
                selectedShape = shape;
                lastClickTime = currentTime;
                repaint();
                return;
            }
        }

        selectedShape = null;
        repaint();
    }
}
