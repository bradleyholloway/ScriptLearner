package utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;


/**
 * 10/8/2014
 */
public abstract class Game {
    protected int width;
    protected int height;
    protected int frameRate;
    protected boolean[] keys;
    protected HashMap<String, BufferedImage> images;
    protected HashMap<String, Clip> sounds;
    protected String contentDirectory;

    public abstract void loadContent();

    public abstract void update(long elapsedTime);

    public abstract void draw(Graphics g);

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        GraphicsRelativeUtil.updateSize(width, height);
    }
    public void giveKeys(boolean[] keys) {
        this.keys = keys;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTargetFrameRate() {
        return frameRate;
    }

    protected void enableContent() {
        images = new HashMap<String, BufferedImage>();
        sounds = new HashMap<String, Clip>();
        XboxController.loadControllers();
    }

    protected boolean loadFile(String fileName) throws ContentNotReadyException {
        if (images == null && sounds == null) {
            throw new ContentNotReadyException();
        }
        if (contentDirectory == null) {
            contentDirectory = "";
        }

        File f = new File(contentDirectory + fileName);
        try {
            if (fileName.contains(".png") || fileName.contains(".jpg")) {
                BufferedImage image = ImageIO.read(f);
                images.put(fileName.substring(0, fileName.length() - 4), image);
            } else if (fileName.contains(".wav")) {
                Clip clip = loadClip(fileName);
                sounds.put(fileName.substring(0, fileName.length() - 4), clip);
            } else {
                System.err.println("Unsuported file type: " + fileName);
            }
        } catch (Exception e) {
            System.err.println("Loading Content Exception: " + fileName);
            return false;
        }
        return true;
    }

    protected void setContentDirectory(String path) {
        contentDirectory = path;
    }

    private Clip loadClip(String filename) {
        Clip clip = null;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(filename));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    private class ContentNotReadyException extends Exception {
    }

    protected int x(double proportion) {
        return GraphicsRelativeUtil.x(proportion);
    }

    protected int y(double proportion) {
        return GraphicsRelativeUtil.y(proportion);
    }
}
