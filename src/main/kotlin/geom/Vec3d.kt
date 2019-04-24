package geom

data class Vec3d(val x: Double, val y: Double, val z: Double) : Vec3<Double, Vec3d> {
    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    override fun cross(v: Vec3d): Vec3d = Vec3d(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)

    override fun scalar(v: Vec3d): Double = x * v.x + y * v.y + z * v.z

    override fun plus(v: Vec3d): Vec3d = Vec3d(x + v.x, y + v.y, z + v.z)

    override fun minus(v: Vec3d): Vec3d = Vec3d(x - v.x, y - v.y, z - v.z)

    override fun times(t: Int): Vec3d = Vec3d(x * t, y * t, z * t)

    override fun times(t: Double): Vec3d = Vec3d(x * t, y * t, z * t)

    override fun length(): Double = Math.sqrt(x * x + y * y + z * z)

    override fun normalize(): Vec3d {
        val length = length()
        return Vec3d(x / length, y / length, z / length)
    }
}