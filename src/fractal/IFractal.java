package fractal;

import java.awt.Graphics2D;

public interface IFractal {
    void draw(Graphics2D g);

    int getMinimalElementsCount();
    void resetCounter();
}