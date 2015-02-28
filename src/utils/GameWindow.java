package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow {

    public GameWindow(Game game, int targetFrameRate) {

        int desiredFramePerSecondRate = targetFrameRate;
        int width = game.getWidth();
        int height = game.getHeight();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graphics frameGraphics = frame.getGraphics();


        int millis = 1000 / desiredFramePerSecondRate;
        game.loadContent();
        long startTime = System.nanoTime();

        while (true) {
            long beginTime = System.nanoTime();
            BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics bufferGraphics = buffer.getGraphics();

            if (frame.getWidth() != width || frame.getHeight() != height) {
                width = frame.getWidth();
                height = frame.getHeight();
                game.resize(width, height);
            }

            game.update(System.nanoTime() - startTime);
            game.draw(bufferGraphics);

            frameGraphics.clearRect(0, 0, width, height);
            frameGraphics.drawImage(buffer, 0, 0, null);
            long endTime = System.nanoTime();
            long elapsedTime = Math.round((endTime - beginTime) / 1e6);

            try {
                long sleepTime = millis - elapsedTime;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                } else {
                    System.err.println("Running Late!: " + Math.abs(sleepTime));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }// In Game Loop

    }
}
