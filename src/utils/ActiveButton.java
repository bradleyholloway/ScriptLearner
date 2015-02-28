package utils;

/**
 * 10/8/2014
 */
public class ActiveButton {
    private boolean value;
    private boolean previous;

    public ActiveButton() {
        previous = true;
        value = false;
    }

    public void update(boolean value) {
        if (!previous && value) {
            this.value = true;
        } else {
            this.value = false;
        }
        this.previous = value;
    }

    public boolean get() {
        return value;
    }
}
