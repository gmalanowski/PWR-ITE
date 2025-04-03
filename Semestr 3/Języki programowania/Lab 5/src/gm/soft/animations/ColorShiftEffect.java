package gm.soft.animations;

import java.awt.image.BufferedImage;

/**
 * Class representing the color shift effect to be applied to an image.
 */
public class ColorShiftEffect extends Effect {

    /**
     * Applies the color shift effect to the specified image.
     *
     * @param image the image to apply the effect to
     * @param lock  the lock object for synchronizing access to the image
     */
    @Override
    public void apply(BufferedImage image, Object lock) {
        synchronized (lock) {
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int color = image.getRGB(x, y);

                    int alpha = (color >> 24) & 0xFF; // Alpha
                    int red = (color >> 16) & 0xFF;   // Red
                    int green = (color >> 8) & 0xFF;  // Green
                    int blue = color & 0xFF;         // Blue

                    int newColor = (alpha << 24) | (blue << 16) | (red << 8) | green;

                    image.setRGB(x, y, newColor);
                }
            }
        }
    }
}