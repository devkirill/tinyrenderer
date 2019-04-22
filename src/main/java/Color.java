public class Color
{
    public final static Color white = new Color(255, 255, 255);
    public final static Color black = new Color(0, 0, 0);

    private final int value;

    public Color(int value)
    {
        this.value = value;
    }

    public Color(int r, int g, int b, int a)
    {
        this(((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8) |
                ((b & 0xFF) << 0));
    }

    public Color(int r, int g, int b)
    {
        this(r, g, b, 0);
    }

    public int getValue()
    {
        return value;
    }
}
