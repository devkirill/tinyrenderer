public class Ver3d
{
    private final double x;
    private final double y;
    private final double z;

    public Ver3d(double x, double y, double z)
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

    public Ver3d plus(Ver3d v)
    {
        return new Ver3d(getX() + v.getX(), getY() + v.getY(), getZ() + v.getZ());
    }

    public Ver3d minus(Ver3d v)
    {
        return new Ver3d(getX() - v.getX(), getY() - v.getY(), getZ() - v.getZ());
    }

    public Ver3d times(Ver3d v)
    {
        double i = getY() * v.getZ() - getZ() * v.getY();
        double j = getZ() * v.getX() - getX() * v.getZ();
        double k = getX() * v.getY() - getY() * v.getX();
        return new Ver3d(i, j, k);
    }

    public double scalar(Ver3d v)
    {
        return getX() * v.getX() + getY() * v.getY() + getZ() * v.getZ();
    }

    public Double length()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Ver3d normalize()
    {
        Double length = length();
        return new Ver3d(x / length, y / length, z / length);
    }

    @Override
    public String toString()
    {
        return "Ver3d{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
