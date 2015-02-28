package utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 10/8/2014
 */
public class Menu {
    private Rectangle workArea;
    private List<MenuElement> elements;
    private Font font;
    private int currentIndex;
    private Color iconColor;

    public Menu(Rectangle r) {
        workArea = r;
        font = new Font("Times New Roman", 0, 24);
        currentIndex = 0;
        elements = new ArrayList<MenuElement>();
        iconColor = Color.black;
    }

    public void setFont(Font f) {
        font = f;
    }

    public void updateArea(Rectangle r) {
        workArea = r;
    }

    public void setIconColor(Color c) {
        this.iconColor = c;
    }

    public void addElement(String text, Color color, int returnValue) {
        elements.add(new MenuElement(text, color, returnValue));
    }

    public void draw(Graphics g) {
        if (elements.size() != 0) {
            int deltaY = workArea.height / elements.size();
            Font temp = g.getFont();
            g.setFont(font);
            for (int i = 0; i < elements.size(); i++) {
                elements.get(i).draw(g, workArea.x + workArea.width/24 * 3/2, workArea.y + deltaY * i);
            }
            Color tempColor = g.getColor();
            g.setColor(iconColor);
            g.fillOval(workArea.x, workArea.y * 5/6 + deltaY * currentIndex, workArea.width/24, workArea.height/24);
            g.setColor(tempColor);
            g.setFont(temp);
        }
    }

    public int update(int input) {
        if (input == -1) {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = elements.size() - 1;
            }
        } else if (input == 1) {
            currentIndex++;
            if (currentIndex >= elements.size()) {
                currentIndex = 0;
            }
        } else if (input == Integer.MAX_VALUE) {
            return elements.get(currentIndex).getReturnValue();
        }
        return 0;
    }

    private class MenuElement {
        Color displayColor;
        String text;
        int pointingValue;

        public MenuElement(String text, Color color, int returnValue) {
            displayColor = color;
            this.text = text;
            pointingValue = returnValue;
        }

        public void draw(Graphics g, int x, int y) {
            Color temp = g.getColor();
            g.setColor(displayColor);
            g.drawString(text, x, y);
            g.setColor(temp);
        }

        public int getReturnValue() {
            return pointingValue;
        }
    }
}


