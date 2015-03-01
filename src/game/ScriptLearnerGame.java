package game;

import ai.AiNode;
import ai.ScriptUtils;
import game.bullets.Bullet;
import game.players.Player;
import utils.Game;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;


/**
 * 2/28/2015
 */
public class ScriptLearnerGame extends Game {
    public static ArrayList<Player> players;
    public static ArrayList<Bullet> bullets;

    public ScriptLearnerGame(int width, int height) {
        resize(width, height);
        players = new ArrayList<Player>();
        bullets = new ArrayList<Bullet>();
    }

    @Override
    public void loadContent() {
        players.clear();
        bullets.clear();
        for (int i = 0; i < 12; i++) {
            players.add(new Player(new File("ais/test.ai")));
        }
        Player.setPlayerCount(players.size());


    }

    @Override
    public void update(long elapsedTime) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).run();
            //System.out.println(players.get(i));
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).run()) {
                i--;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, x(1), y(1));

        for (Player player : players) {
            player.draw(g);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }
}
