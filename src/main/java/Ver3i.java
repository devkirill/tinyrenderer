public class Ver3i
{
    private final int x;
    private final int y;
    private final int z;

    public Ver3i(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    public Ver3i plus(Ver3i v)
    {
        return new Ver3i(getX() + v.getX(), getY() + v.getY(), getZ() + v.getZ());
    }

    public Ver3i minus(Ver3i v)
    {
        return new Ver3i(getX() - v.getX(), getY() - v.getY(), getZ() - v.getZ());
    }

    public Ver3i times(Ver3i v)
    {
        int i = getY() * v.getZ() - getZ() * v.getY();
        int j = getZ() * v.getX() - getX() * v.getZ();
        int k = getX() * v.getY() - getY() * v.getX();
        return new Ver3i(i, j, k);
    }

    public Ver3i times(double t)
    {
        return new Ver3i((int)Math.round(x * t),(int) Math.round(y * t),(int) Math.round(z * t));
    }

    public int scalar(Ver3i v)
    {
        return getX() * v.getX() + getY() * v.getY() + getZ() * v.getZ();
    }

    @Override
    public String toString()
    {
        return "Ver3i{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
