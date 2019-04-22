import static java.lang.Math.*;

public class Main
{
    public static void line(int x0, int y0, int x1, int y1, Image image, Color color)
    {
        if (abs(x1 - x0) > abs(y1 - y0))
        {
            double s = (double) (y1 - y0) / (double) (x1 - x0);
            if (x0 == x1)
            {
                s = Double.MAX_VALUE;
            }
            for (int x = min(x0, x1), to = max(x0, x1); x <= to; x++)
            {
                double y = s * (x - x0) + y0;
                image.setPixel(x, (int) y, color);
            }
        }
        else
        {
            double s = (double) (x1 - x0) / (double) (y1 - y0);
            if (y0 == y1)
            {
                s = Double.MAX_VALUE;
            }
            for (int y = min(y0, y1), to = max(y0, y1); y <= to; y++)
            {
                double x = s * (y - y0) + x0;
                image.setPixel((int) x, y, color);
            }
        }
    }

    public static void triangle(Point t0, Point t1, Point t2, Image image, Color color)
    {
        if (t0.getY() > t1.getY())
        {
            Point t = t0;
            t0 = t1;
            t1 = t;
        }
        if (t0.getY() > t2.getY())
        {
            Point t = t0;
            t0 = t2;
            t2 = t;
        }
        if (t1.getY() > t2.getY())
        {
            Point t = t1;
            t1 = t2;
            t2 = t;
        }

        int total_height = t2.getY() - t0.getY();
        for (int y = t0.getY(); y <= t1.getY(); y++)
        {
            int segment_height = t1.getY() - t0.getY() + 1;
            double alpha = (double) (y - t0.getY()) / total_height;
            double beta = (double) (y - t0.getY()) / segment_height;
            Point A = t0.plus(t2.minus(t0).times(alpha));
            Point B = t0.plus(t1.minus(t0).times(beta));
            for (int x = min(A.getX(), B.getX()), to = max(A.getX(), B.getX()); x <= to; x++)
            {
                image.setPixel(x, y, color);
            }
        }
        for (int y = t1.getY(); y <= t2.getY(); y++)
        {
            int segment_height = t2.getY() - t1.getY() + 1;
            double alpha = (double) (y - t0.getY()) / total_height;
            double beta = (double) (y - t1.getY()) / segment_height;
            Point A = t0.plus(t2.minus(t0).times(alpha));
            Point B = t1.plus(t2.minus(t1).times(beta));
            for (int x = min(A.getX(), B.getX()), to = max(A.getX(), B.getX()); x <= to; x++)
            {
                image.setPixel(x, y, color);
            }
        }
    }

    public static Point toPoint(Vertex vertex, Image image)
    {
        int x = (int) ((vertex.getX() + 1.) * image.getWidth() / 2.);
        int y = (int) ((vertex.getY() + 1.) * image.getHeight() / 2.);
        return new Point(x, y);
    }

    public static void main(String args[])
    {
        Image image = new Image(1000, 1000);

        WavefrontObj obj = WavefrontObj.parse("src/main/resources/african_head/african_head.obj");
//        WavefrontObj obj = WavefrontObj.parse("src/main/resources/diablo3_pose/diablo3_pose.obj");

        Vertex light = new Vertex(0, 0, -1).normalize();

        obj.getPolygons()
                .forEach(p -> {
                    Vertex v0 = obj.getVertices().get(p.get(0));
                    Vertex v1 = obj.getVertices().get(p.get(1));
                    Vertex v2 = obj.getVertices().get(p.get(2));

                    Vertex b1 = v2.minus(v0);
                    Vertex b2 = v1.minus(v0);
                    Vertex norm = b1.times(b2).normalize();
                    double intensity = norm.scalar(light);
                    if (intensity > 0)
                    {
                        Point p0 = toPoint(v0, image);
                        Point p1 = toPoint(v1, image);
                        Point p2 = toPoint(v2, image);
                        triangle(p0, p1, p2, image, new Color(intensity, intensity, intensity));
                    }
                });

        image.saveImage("output");
    }
}
