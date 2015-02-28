package game;

import ai.AiNode;
import ai.ScriptUtils;
import utils.Game;

import java.awt.*;
import java.io.File;


/**
 * 2/28/2015
 */
public class ScriptLearnerGame extends Game {
    AiNode node;
    public ScriptLearnerGame(int width, int height) {
        resize(width,height);
    }

    @Override
    public void loadContent() {
        node = ScriptUtils.loadFromFile(new File("ais/test.ai"));
    }

    @Override
    public void update(long elapsedTime) {
        node.run();
        System.out.println(node);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0,0,x(1),y(1));
        g.setColor(Color.blue);
        g.fillRect(0,0,x(.45),y(.45));
    }
}
