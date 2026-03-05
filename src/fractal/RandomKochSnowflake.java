package fractal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class RandomKochSnowflake extends AbstractFractal {

    private static final Random random = new Random();
    private final int sides;
    private final double radius;

    public RandomKochSnowflake(Point2D center, double radius, int sides, int depth, Color color) {
        super(center, color, depth);
        this.radius = radius;
        this.sides = sides;
    }

    @Override
    public void draw(Graphics2D g) {
        Color oldColor = g.getColor();
        g.setColor(color);

        Point2D[] vertices = new Point2D[sides];
        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            vertices[i] = new Point2D.Double(
                    center.getX() + radius * Math.cos(angle),
                    center.getY() - radius * Math.sin(angle)
            );
        }

        java.awt.Polygon poly = new java.awt.Polygon();
        for (Point2D p : vertices) {
            poly.addPoint((int) Math.round(p.getX()), (int) Math.round(p.getY()));
        }
        g.fillPolygon(poly);

        for (int i = 0; i < sides; i++) {
            drawRandomKochCurve(g, vertices[(i + 1) % sides], vertices[i], depth);
        }

        g.setColor(oldColor);
    }

    private static int randomSgn() {
        return random.nextInt(2) * 2 - 1;
    }

    private void drawRandomKochCurve(Graphics2D g, Point2D p, Point2D q, int n) {
        if (n <= 0) return;

        int w = randomSgn();

        Point2D r = new Point2D.Double(
                (2 * p.getX() + q.getX()) / 3,
                (2 * p.getY() + q.getY()) / 3
        );

        Point2D s = new Point2D.Double(
                (p.getX() + q.getX()) / 2 - w * (p.getY() - q.getY()) * Math.sqrt(3) / 6,
                (p.getY() + q.getY()) / 2 + w * (p.getX() - q.getX()) * Math.sqrt(3) / 6
        );

        Point2D t = new Point2D.Double(
                (p.getX() + 2 * q.getX()) / 3,
                (p.getY() + 2 * q.getY()) / 3
        );

        java.awt.Polygon triangle = new java.awt.Polygon();
        triangle.addPoint((int) Math.round(r.getX()), (int) Math.round(r.getY()));
        triangle.addPoint((int) Math.round(s.getX()), (int) Math.round(s.getY()));
        triangle.addPoint((int) Math.round(t.getX()), (int) Math.round(t.getY()));

        g.setColor(w == 1 ? Color.GREEN : Color.CYAN);
        g.fillPolygon(triangle);

        drawRandomKochCurve(g, p, r, n - 1);
        drawRandomKochCurve(g, r, s, n - 1);
        drawRandomKochCurve(g, s, t, n - 1);
        drawRandomKochCurve(g, t, q, n - 1);
    }
}