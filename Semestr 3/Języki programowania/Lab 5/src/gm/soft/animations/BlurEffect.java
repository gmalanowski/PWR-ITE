package gm.soft.animations;

import java.awt.image.BufferedImage;

/**
 * Class representing the blur effect to be applied to an image.
 */
public class BlurEffect extends Effect {

    /**
     * Applies the blur effect to the specified image.
     *
     * @param image the image to apply the effect to
     * @param lock  the lock object for synchronizing access to the image
     */
    @Override
    public void apply(BufferedImage image, Object lock) {
        synchronized (lock) {
            for (int x = 1; x < image.getWidth() - 1; x++) {
                for (int y = 1; y < image.getHeight() - 1; y++) {
                    int rgb = getAverageColor(image, x, y);
                    image.setRGB(x, y, rgb);
                }
            }
        }
    }

    /**
     * Calculates the average color of the 3x3 pixel neighborhood around the specified coordinates.
     *
     * @param image the image to sample the colors from
     * @param x     the x-coordinate of the center pixel
     * @param y     the y-coordinate of the center pixel
     * @return the average color of the 3x3 neighborhood
     */
    private int getAverageColor(BufferedImage image, int x, int y) {
        int[] rgb = new int[3];
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int color = image.getRGB(x + dx, y + dy);
                rgb[0] += (color >> 16) & 0xFF; // Red
                rgb[1] += (color >> 8) & 0xFF;  // Green
                rgb[2] += color & 0xFF;         // Blue
            }
        }
        for (int i = 0; i < 3; i++) {
            rgb[i] /= 9;
        }
        return (0xFF << 24) | (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
    }
}