import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image
{
    private final int width;
    private final int height;
    private final BufferedImage image;

    public Image(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public void setPixel(int x, int y, Color color)
    {
        if (x >= 0 && x < width && y >= 0 && y < height)
        {
            image.setRGB(x, height - 1 - y, color.getValue());
        }
    }

    public void saveImage(String filename)
    {
        try
        {
            ImageIO.write(image, "PNG", new File(filename + ".png"));
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }
}
