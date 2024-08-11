import java.awt.Color;

public class Button extends Rectangle {
    public String name;

    // Dummy constructor for typing in filename in create & open
    public Button(String name) {
        super(0, 0, 0, 0, false);
        this.name = name;
    }

    public Button(int x, int y, int halfWidth, int halfHeight, String name, boolean filled) {
        super(x, y, halfWidth, halfHeight, filled);
        this.name = name;
    }

    public void draw() {
        super.draw();
        Color tmp = StdDraw.getPenColor();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(x, y, name);
        StdDraw.setPenColor(tmp);
    }
}