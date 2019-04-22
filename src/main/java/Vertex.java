public class Vertex
{
    private final double x;
    private final double y;
    private final double z;

    public Vertex(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    public Vertex plus(Vertex v)
    {
        return new Vertex(getX() + v.getX(), getY() + v.getY(), getZ() + v.getZ());
    }

    public Vertex minus(Vertex v)
    {
        return new Vertex(getX() - v.getX(), getY() - v.getY(), getZ() - v.getZ());
    }

    public Vertex times(Vertex v)
    {
        double i = getY() * v.getZ() - getZ() * v.getY();
        double j = getZ() * v.getX() - getX() * v.getZ();
        double k = getX() * v.getY() - getY() * v.getX();
        return new Vertex(i, j, k);
    }

    public double scalar(Vertex v)
    {
        return getX() * v.getX() + getY() * v.getY() + getZ() * v.getZ();
    }

    public Double length()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vertex normalize()
    {
        Double length = length();
        return new Vertex(x / length, y / length, z / length);
    }
}
