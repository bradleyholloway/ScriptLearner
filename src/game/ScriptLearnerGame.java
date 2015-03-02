package game;

import game.engine.ScriptLearnerShooterMatchInstance;
import utils.Game;

import java.awt.*;


/**
 * 2/28/2015
 */
public class ScriptLearnerGame extends Game {

    ScriptLearnerShooterMatchInstance instance;

    public ScriptLearnerGame(int width, int height, int framerate) {
        resize(width, height);
        this.frameRate = framerate;
        instance = new ScriptLearnerShooterMatchInstance();

    }

    @Override
    public void loadContent() {
        instance.loadContent();
    }

    @Override
    public void update(long elapsedTime) {
        instance.update(elapsedTime);

        if (keys[0x27]) {
            frameRate++;
        }
        if (keys[0x25]) {
            frameRate--;
        }
        if (keys[256]) {
            instance.giveKeys(keys);
            instance.requestControl(mouseInfo[0]/getWidth(),mouseInfo[1]/getHeight());
        }//mouse event

    }

    @Override
    public void draw(Graphics g) {
        instance.draw(g);
    }
}
