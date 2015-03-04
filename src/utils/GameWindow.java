package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class GameWindow {

    public GameWindow(final Game game, int targetFrameRate) {

        int desiredFramePerSecondRate = targetFrameRate;
        int width = game.getWidth();
        int height = game.getHeight();
        final boolean[] keystatus = new boolean[257];
        final double[] lastMouseLocation = new double[2];


        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.add(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                if (g != null) {
                    super.paintComponent(g);
                    g.clearRect(0, 0, getWidth(), getHeight());
                    game.draw(g);
                }

            }
        });
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keystatus[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keystatus[e.getKeyCode()] = false;
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lastMouseLocation[0] = e.getX();
                lastMouseLocation[1] = e.getY();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                keystatus[256] = true;
                lastMouseLocation[0] = e.getX();
                lastMouseLocation[1] = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                keystatus[256] = false;
                lastMouseLocation[0] = e.getX();
                lastMouseLocation[1] = e.getY();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        Graphics frameGraphics = frame.getGraphics();
        game.giveKeys(keystatus);
        game.giveMouse(lastMouseLocation);

        int millis = 1000 / desiredFramePerSecondRate;
        game.loadContent();
        long startTime = System.nanoTime();

        while (true) {
            long beginTime = System.nanoTime();

            if (frame.getWidth() != width || frame.getHeight() != height) {
                width = frame.getWidth();
                height = frame.getHeight();
                game.resize(width, height);
            }
            if (game.getTargetFrameRate() != targetFrameRate) {
                targetFrameRate = game.getTargetFrameRate();
                millis = 1000 / targetFrameRate;
            }

            game.update(System.nanoTime() - startTime);
            frame.repaint();

            long endTime = System.nanoTime();
            long elapsedTime = Math.round((endTime - beginTime) / 1e6);

            try {
                long sleepTime = millis - elapsedTime;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                } else {
                    //System.err.println("Running Late!: " + Math.abs(sleepTime));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }// In Game Loop

    }
}
