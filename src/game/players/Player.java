package game.players;

import ai.AiNode;
import ai.ScriptUtils;
import game.bullets.Bullet;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.File;

import static game.engine.ScriptLearnerShooterMatchInstance.bullets;
import static game.engine.ScriptLearnerShooterMatchInstance.players;
import static utils.GraphicsRelativeUtil.x;
import static utils.GraphicsRelativeUtil.y;

/**
 * 2/28/2015
 */
public class Player {
    private static final double width = 0.04;
    private static final double height = 0.04;
    private static final double viewLength = 10;
    private static final double fov = Math.PI/8;
    private static final double rotationConstant = .02;
    private static final int fireCooldown = 10;
    private static final int reloadCooldown = 30;
    private static final int maxAmmo = 4;
    private static final double moveSpeed = .006;

    private static int totalPlayers;
    private static int startInc;
    private AiNode node;
    private double locationx, locationy;
    private double rotation;
    private Color color;
    private int fireCool;
    private int reloadCool;
    private int ammo;

    public static final int trackedStats = 6;
    private int kills;
    private int deaths;
    private int cyclesSurvived;
    private int shotsFired;
    private int reloads;
    private int fails;


    private boolean firstRun;

    public Player(File scriptFile) {
        node = ScriptUtils.loadFromFile(scriptFile);
        node.linkPlayer(this);
        firstRun = true;
    }

    public void run() {
        if (firstRun) {
            double[] loc = getStartLocation();
            locationx = loc[0];
            locationy = loc[1];
            rotation = loc[2];
            firstRun = false;
            color = new Color((int) (255 * Math.random()), (int) (255 * Math.random()), (int) (255 * Math.random()));
            fireCool = fireCooldown;
            reloadCool = 0;
            ammo = maxAmmo;
            kills = 0;
            deaths = 0;
            cyclesSurvived = 0;
            shotsFired = 0;
            reloads = 0;
            fails = 0;
        }

        if (reloadCool > 0) {
            reloadCool--;
            if (reloadCool == 0) {
                ammo = maxAmmo;
                reloads++;
            }
        }
        if (fireCool > 0) {
            fireCool--;
        }
        if (ammo == 0 && reloadCool == 0) {
            reloadCool = reloadCooldown;
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).collides(locationx,locationy,width)) {
                players.remove(this);
                deaths++;
                break;
            }
        }
        cyclesSurvived++;


        //todo update node info to script vars

        if (!node.run()) {
            fails++;
        }
    }

    public void rotateRight() {
        rotation-= rotationConstant;
    }

    public void rotateLeft() {
        rotation+= rotationConstant;
    }

    public void moveForward() {
        double tlocationx=locationx+moveSpeed * Math.cos(-rotation);
        double tlocationy=locationy+moveSpeed * Math.sin(-rotation);
        tryMove(tlocationx,tlocationy);
    }

    public void moveBackwards() {
        double tlocationx=locationx - moveSpeed * Math.cos(-rotation);
        double tlocationy=locationy - moveSpeed * Math.sin(-rotation);
        tryMove(tlocationx,tlocationy);
    }

    public void strafeRight() {
        double tlocationx=locationx + moveSpeed * Math.sin(rotation);
        double tlocationy=locationy + moveSpeed * Math.cos(rotation);
        tryMove(tlocationx,tlocationy);
    }

    public void strafeLeft() {
        double tlocationx=locationx - moveSpeed * Math.sin(rotation);
        double tlocationy=locationy - moveSpeed * Math.cos(rotation);
        tryMove(tlocationx,tlocationy);
    }

    private void tryMove(double tlocx, double tlocy) {
        if (tlocx > .03 && tlocx < .98 && tlocy < .97 && tlocy > .075) {
            locationx = tlocx;
            locationy = tlocy;
        }
    }

    public boolean fire() {
        if (ammo != 0 && fireCool == 0 && reloadCool == 0) {
            ammo--;
            fireCool = fireCooldown;
            Bullet b = new Bullet(locationx,locationy,-rotation,color,this);
            bullets.add(b);
            shotsFired++;
            return true;
        } else {
            return false;
        }
    }

    public void reload() {
        if (ammo != maxAmmo && reloadCool == 0) {
            reloadCool = reloadCooldown;
        }
    }

    public void addKill() {
        kills++;
    }

    public void draw(Graphics g) {
        int rotationDeg = (int) (rotation * 180 / Math.PI) % 360;
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 25));
        Polygon view = new Polygon();
        view.addPoint(x(locationx), y(locationy));
        view.addPoint(x(locationx + viewLength * Math.cos(-rotation - fov / 2)), y(locationy + viewLength * Math.sin(-rotation - fov / 2)));
        view.addPoint(x(locationx + viewLength * Math.cos(-rotation + fov / 2)), y(locationy + viewLength * Math.sin(-rotation + fov / 2)));
        g.fillPolygon(view);
        g.setColor(color);
        g.fillOval(x(locationx - width / 2), y(locationy - height / 2), x(width), y(height));

        //draw reload cool meter
        g.setColor(new Color(255, 255, 255));
        if (reloadCool > 0) {
            g.drawArc(x(locationx - width / 2), y(locationy - height / 2), x(width), y(height), 0, (int) ((double) 360 * (reloadCooldown - reloadCool) / reloadCooldown));
        }
    }

    public int[] getStats() {
        return new int[]{kills, deaths, cyclesSurvived, shotsFired, reloads, fails};
    }

    private static double[] getStartLocation() {
        double rad = 2 * Math.PI;
        rad *= (double) startInc / totalPlayers;
        double[] location = new double[3];
        location[0] = (.5 + .4 * Math.cos(rad));
        location[1] = (.5 + .4 * Math.sin(rad));
        location[2] = -rad + Math.PI;
        startInc++;
        return location;
    }

    public static void setPlayerCount(int count) {
        totalPlayers = count;
        startInc = 0;
    }

    public String toString() {
        return node.toString();
    }
}
