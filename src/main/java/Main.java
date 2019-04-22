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

    public static void triangle(Ver3i t0, Ver3i t1, Ver3i t2, Image image, Color color)
    {
        if (t0.getY() > t1.getY())
        {
            Ver3i t = t0;
            t0 = t1;
            t1 = t;
        }
        if (t0.getY() > t2.getY())
        {
            Ver3i t = t0;
            t0 = t2;
            t2 = t;
        }
        if (t1.getY() > t2.getY())
        {
            Ver3i t = t1;
            t1 = t2;
            t2 = t;
        }

        int total_height = t2.getY() - t0.getY();
        for (int y = 0; y < total_height; y++)
        {
            boolean second_half = y > t1.getY() - t0.getY() || t1.getY() == t0.getY();
            int segment_height = second_half ? t2.getY() - t1.getY() : t1.getY() - t0.getY();
            double alpha = (double) y / total_height;
            double beta = (double) (y - (second_half ? t1.getY() - t0.getY() : 0)) / segment_height;
            Ver3i A = t0.plus(t2.minus(t0).times(alpha));
            Ver3i B = second_half ? t1.plus(t2.minus(t1).times(beta)) : t0.plus(t1.minus(t0).times(beta));
            if (A.getX() > B.getX())
            {
                Ver3i t = A;
                A = B;
                B = t;
            }
            for (int x = A.getX(); x <= B.getX(); x++)
            {
                double phi = A.getX() == B.getX() ? 1. : (double) (x - A.getX()) / (double) (B.getX() - A.getX());
                Ver3i P = B.minus(A).times(phi).plus(A);
                image.setPixel(P.getX(), P.getY(), P.getZ(), color);
            }
        }
    }

    public static Ver3i toVer3i(Ver3d ver3d, Image image)
    {
        int x = (int) ((ver3d.getX() + 1.) * image.getWidth() / 2.);
        int y = (int) ((ver3d.getY() + 1.) * image.getHeight() / 2.);
        int z = (int) ((ver3d.getZ() + 1.) * max(image.getWidth(), image.getHeight()) / 2.);
        return new Ver3i(x, y, z);
    }

    public static void main(String args[])
    {
        Image image = new Image(1000, 1000);

        WavefrontObj obj = WavefrontObj.parse("src/main/resources/african_head/african_head.obj");
//        WavefrontObj obj = WavefrontObj.parse("src/main/resources/diablo3_pose/diablo3_pose.obj");

        Ver3d light = new Ver3d(0, 0, -1).normalize();

        obj.getPolygons()
                .forEach(p -> {
                    Ver3d v0 = obj.getVertices().get(p.get(0));
                    Ver3d v1 = obj.getVertices().get(p.get(1));
                    Ver3d v2 = obj.getVertices().get(p.get(2));

                    Ver3d b1 = v2.minus(v0);
                    Ver3d b2 = v1.minus(v0);
                    Ver3d norm = b1.times(b2).normalize();
                    double intensity = norm.scalar(light);
                    if (intensity > 0)
                    {
                        Ver3i p0 = toVer3i(v0, image);
                        Ver3i p1 = toVer3i(v1, image);
                        Ver3i p2 = toVer3i(v2, image);
                        triangle(p0, p1, p2, image, new Color(intensity, intensity, intensity));
                    }
                });

        image.saveImage("output");
    }
}
