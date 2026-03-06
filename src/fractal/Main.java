package fractal;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Main extends JPanel {

    private BufferedImage image;
    private RandomKochSnowflake snowflake;

    public Main() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 650));
        generateFractal();
    }

    private void generateFractal() {
        int width = 800;
        int height = 600;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        snowflake = new RandomKochSnowflake(
                new Point2D.Double(width / 2.0, height / 2.0),
                150,
                6,
                4,
                Color.BLUE
        );
        snowflake.draw(g2d);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }

        if (snowflake != null) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("Количество минимальных элементов: " +
                    snowflake.getMinimalElementsCount(), 20, 630);
            g.drawString("Глубина рекурсии: " + snowflake.getDepth(), 20, 650);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Рандомизированная снежинка Коха");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Main());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}