package game.engine;

import game.bullets.Bullet;
import game.players.Player;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static utils.GraphicsRelativeUtil.x;
import static utils.GraphicsRelativeUtil.y;

/**
 * 2/28/2015
 */
public class ScriptLearnerShooterMatchInstance {
    public static ArrayList<Player> players;
    public static ArrayList<Bullet> bullets;
    private boolean[] keys;

    public ScriptLearnerShooterMatchInstance() {
        players = new ArrayList<Player>();
        bullets = new ArrayList<Bullet>();
    }

    public void loadContent() {
        players.clear();
        bullets.clear();
        players.add(new Player(new File("ais/dodgey.ai")));
        players.add(new Player(new File("ais/dodgey.ai")));

        for(int i = 0; i < 4; i ++) {
            //players.add(new Player(new File("ais/test.ai")));

            //players.add(new Player(new File("ais/bradleygenetics/generation2/0.ai")));
        }
        Player.setPlayerCount(players.size());
    }

    public void loadHeadlessContent(ArrayList<File> files, ArrayList<Player> combattants) {
        players.clear();
        bullets.clear();

        for (File f : files) {
            Player p = new Player(f);
            players.add(p);
            combattants.add(p);
        }

        Player.setPlayerCount(players.size());
    }

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
    public void requestControl(double x, double y) {
        for(Player p : players) {
            if (p.contains(x,y)) {
                p.takeControl(keys);
            }
        }
    }
    public void giveKeys(boolean[] keys) {
        this.keys = keys;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, x(1), y(1));

        for (Player player : players) {
            player.draw(g);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }
}
