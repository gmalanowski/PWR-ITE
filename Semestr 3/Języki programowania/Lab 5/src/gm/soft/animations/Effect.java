package gm.soft.animations;

import java.awt.image.BufferedImage;

/**
 * Abstract class representing an effect to be applied to an image.
 */
public abstract class Effect {

    /**
     * Applies the effect to the specified image.
     *
     * @param image the image to apply the effect to
     * @param lock  the lock object for synchronizing access to the image
     */
    public abstract void apply(BufferedImage image, Object lock);
}