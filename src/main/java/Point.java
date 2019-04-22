public class Point
{
    private final int x;
    private final int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Point plus(Point p)
    {
        return new Point(getX() + p.getX(), getY() + p.getY());
    }

    public Point minus(Point p)
    {
        return new Point(getX() - p.getX(), getY() - p.getY());
    }

    public Point times(double t)
    {
        return new Point((int) (getX() * t), (int) (getY() * t));
    }
}
