package fractal;

import java.awt.Color;
import java.awt.geom.Point2D;

public abstract class AbstractFractal implements IFractal {

    protected Color color;
    protected Point2D center;
    protected int depth;

    public AbstractFractal(Point2D center, Color color, int depth) {
        this.center = center;
        this.color = color;
        this.depth = depth;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}