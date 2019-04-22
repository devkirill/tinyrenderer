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

    public static void main(String args[])
    {
        Image image = new Image(1000, 1000);

        WavefrontObj obj = WavefrontObj.parse("src/main/resources/african_head/african_head.obj");

        obj.getPolygons()
                .forEach(p -> {
                    for (int i = 0; i < p.size(); i++)
                    {
                        Vertex v0 = obj.getVertices().get(p.get(i));
                        Vertex v1 = obj.getVertices().get(p.get(i + 1));

                        int x0 = (int) ((v0.getX() + 1.) * image.getWidth() / 2.);
                        int y0 = (int) ((v0.getY() + 1.) * image.getHeight() / 2.);
                        int x1 = (int) ((v1.getX() + 1.) * image.getWidth() / 2.);
                        int y1 = (int) ((v1.getY() + 1.) * image.getHeight() / 2.);
                        line(x0, y0, x1, y1, image, Color.white);
                    }
                });

        image.saveImage("output");
    }
}
