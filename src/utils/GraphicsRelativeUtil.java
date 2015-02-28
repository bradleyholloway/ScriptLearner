package utils;

/**
 * 2/28/2015
 */
public class GraphicsRelativeUtil {
    private static int width;
    private static int height;

    protected static void updateSize(int twidth, int theight) {
        width = twidth;
        height = theight;
    }

    public static int x(double proportion) {
        return (int) (proportion * width);
    }

    public static int y(double proportion) {
        return (int) (proportion * height);
    }

}
