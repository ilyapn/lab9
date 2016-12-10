package PIP;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.List;

public class GraphicImage {
    public static BufferedImage genImage(List<Data> data, int width, int height, double r) {
        int r_px = (int)(r * (double)height / 8.0);
        
        if(r<1 || r>4)
            r_px =0;
        BufferedImage result = new BufferedImage(width, height, 1);
        Graphics2D g = result.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        GraphicImage.paintArea(g, width, height, r_px);
        GraphicImage.paintAxes(g, width, height, r_px);
        GraphicImage.paintPoints(g, data, width, height, r);
        return result;
    }

    public static void paintArea(Graphics g, int width, int height, int r) {
        int xc = width / 2;
        int yc = height / 2;
        g.setColor(new Color(88, 130, 250));
        g.fillArc(xc - r, yc - r, 2 * r, 2 * r, 180, 90);
        g.fillRect(xc, yc , r, r);
        g.fillPolygon(GraphicImage.getTrianglePolygon(width, height, r));
    }

    static Polygon getTrianglePolygon(int width, int height, int R) {
        Polygon result = null;
        int num = 3;
        int[] x_points = new int[num];
        int[] y_points = new int[num];
        x_points[0] = width / 2;
        y_points[0] = height / 2;
        x_points[1] = width / 2 - R;
        y_points[1] = height / 2;
        x_points[2] = width / 2;
        y_points[2] = height / 2 - R / 2;
        result = new Polygon(x_points, y_points, num);
        return result;
    }

    public static void paintAxes(Graphics g, int width, int height, int r) {
        int xc = width / 2;
        int yc = height / 2;
        Double temp = (double)r / 50.0;
        g.setColor(Color.BLACK);
        g.drawLine(0, yc, 2 * xc, yc);
        g.drawLine(xc, 0, xc, 2 * yc);
        g.drawLine(xc - r, yc + 3, xc - r, yc - 3);
        g.drawLine(xc + r, yc - 5, xc + r, yc + 5);
        g.drawLine(xc + 3, yc + r, xc - 3, yc + r);
        g.drawLine(xc + 3, yc - r, xc - 3, yc - r);
        g.drawLine(xc - r / 2, yc + 3, xc - r / 2, yc - 3);
        g.drawLine(xc + r / 2, yc - 3, xc + r / 2, yc + 3);
        g.drawLine(xc + 3, yc + r / 2, xc - 3, yc + r / 2);
        g.drawLine(xc + 3, yc - r / 2, xc - 3, yc - r / 2);
        g.drawLine(xc - 3, 5, xc, 0);
        g.drawLine(xc + 3, 5, xc, 0);
        g.drawLine(xc * 2 - 5, yc - 3, xc * 2, yc);
        g.drawLine(xc * 2 - 5, yc + 3, xc * 2, yc);
        g.setFont(new Font("TimesRoman", 2, 11));
        g.drawString(temp.toString(), xc + r - 10, yc + 10);
        g.drawString(temp.toString(), xc - r - 10, yc + 10);
        g.drawString(temp.toString(), xc - 10, yc - r - 10);
        g.drawString(temp.toString(), xc - 10, yc + r - 10);
        temp = (double)r / 100.0;
        g.drawString(temp.toString(), xc + r / 2 - 10, yc + 10);
        g.drawString(temp.toString(), xc - r / 2 - 10, yc + 10);
        g.drawString(temp.toString(), xc - 10, yc - r / 2 - 10);
        g.drawString(temp.toString(), xc - 10, yc + r / 2 - 10);
    }

    public static void paintPoints(Graphics g, List<Data> data, int width, int height, double r) {
        int pointRadius = 8;
        Color color = Color.BLACK;
        
        if (data.isEmpty()==false)
        {
            double y=data.get(data.size()-1).getValueY();
            double x=data.get(data.size()-1).getValueX();
            if (UserBean.checkResult((double)x, (double)(y ), (double)r).equals("IN")) {
                color = new Color(22, 228, 65);
            }
            if (UserBean.checkResult((double)x, (double)y, (double)r).equals("OUT")) {
                color = Color.RED;
            }
            int item_x = (int)(x * (double)width / 8.0) + width / 2 - 4;
            int item_y = height / 2 - (int)(y * (double)height / 8.0) - 4;
            g.setColor(color);
            g.fillOval(item_x, item_y, pointRadius, pointRadius);
        }
    }
}