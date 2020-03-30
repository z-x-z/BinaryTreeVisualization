package drawTree;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * @description: A picture drawing interface.
 * @project: Algorithms
 * @author: caged_bird
 * @Create: 2020-03-19 12:01
 **/
abstract class figureWithPoint
{
    double x;
    double y;

    figureWithPoint(double _x, double _y)
    {
        x = _x;
        y = _y;
    }

    double distanceSquare(figureWithPoint f)
    {
        return (x - f.x) * (x - f.x) + (y - f.y) * (y - f.y);
    }
}

class Point extends figureWithPoint
{
    Point(double _x, double _y)
    {
        super(_x, _y);
    }

    void draw()
    {
        DrawBase.drawPoint(x, y);
    }
}

class Circle extends figureWithPoint
{
    double r;

    Circle(double _x, double _y, double _r)
    {
        super(_x, _y);
        r = _r;
    }

    void draw()
    {
        DrawBase.drawCircle(x, y, r);
    }
}

public class DrawBase
{
    static final int DEFAULT_CANVAS_Size = 400;

    /**
     * In this Canvas, the x and y scale is 0 ~ 1
     * Base method: drawLine(), drawCircle(), drawPoint(), drawText()
     *              setFontSize(), setCanvasSize()
     */
    // Draw line link two point (x1, y1) and (x2, y2).
    static void drawLine(double x1, double y1, double x2, double y2)
    {
        StdDraw.line(x1, y1, x2, y2);
    }
    static void drawLine(Point p1, Point p2){drawLine(p1.x, p1.y, p2.x, p2.y);}

    // Draw circle with circleCenter (x, y) and radius r.
    static void drawCircle(double x, double y, double r)
    {
        StdDraw.circle(x, y, r);
    }
    static void drawCircle(Circle circle)
    {
        drawCircle(circle.x, circle.y, circle.r);
    }

    // Draw point (x, y)
    static void drawPoint(double x, double y)
    {
        StdDraw.point(x, y);
    }
    static void drawPoint(Point p)
    {
        drawPoint(p.x, p.y);
    }

    // Draw text
    static void drawText(double x, double y, String text)
    {
        StdDraw.text(x, y, text);
    }

    static void setFontSize(int fontSize)
    {
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
    }

    static void setCanvasSize(int width, int height)
    {
        StdDraw.setCanvasSize(width, height);
    }
    /**
     * Expanded method: drawLineTwoCircle()
     */
    // draw line link two circles
    static void drawLineTwoCircle(Circle c1, Circle c2)
    {
        // Two circles intersect, nothing to do.
        if(c1.distanceSquare(c2) < (c1.r + c2.r) * (c1.r + c2.r)) return;

        //  Two circleCenters are on the same vertical line, so the slope has no meaning.
        if(Math.abs(c1.x-c2.x) < 1e-6)
        {
            if(c2.y > c1.y) DrawBase.drawLine(c1.x, c1.y+c1.r, c2.x, c2.y-c2.r);
            else DrawBase.drawLine(c1.x, c1.y-c1.r, c2.x, c2.y+c2.r);
            return;
        }

        double k = (c2.y - c1.y) / (c2.x - c1.x);
        double p1x = c1.x, p1y = c1.y, p2x = c2.x, p2y = c2.y;
        int b1x, b1y, b2x, b2y;
        if(k > 0)
        {
            b1x = b1y = c1.x < c2.x ? 1 : -1;
            b2x = b2y = -b1x;
        }
        else
        {
            b1x = b2y = c1.x < c2.x ? 1 : -1;
            b1y = b2x = -b1x;
            k = -k;
        }
        double rk = Math.sqrt(k * k + 1);
        p1x += b1x * c1.r / rk;
        p1y += b1y * c1.r * k / rk;
        p2x += b2x * c2.r / rk;
        p2y += b2y * c2.r * k / rk;
        DrawBase.drawLine(p1x, p1y, p2x, p2y);
    }

}
