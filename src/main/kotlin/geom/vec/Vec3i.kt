package geom.vec

data class Vec3i(var x: Int, var y: Int, var z: Int) : Vec3<Int, Vec3i> {
    constructor(x: Double, y: Double, z: Double) : this((x + 1e-9).toInt(), (y + 1e-9).toInt(), (z + 1e-9).toInt())

    override fun cross(v: Vec3i): Vec3i = Vec3i(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)

    override fun scalar(v: Vec3i): Int = x * v.x + y * v.y + z * v.z

    override fun plus(v: Vec3i): Vec3i = Vec3i(x + v.x, y + v.y, z + v.z)

    override fun plusAssign(v: Vec3i) {
        x += v.x
        y += v.y
        z += v.z
    }

    override fun minus(v: Vec3i): Vec3i = Vec3i(x - v.x, y - v.y, z - v.z)

    override fun minusAssign(v: Vec3i) {
        x -= v.x
        y -= v.y
        z -= v.z
    }

    override fun times(t: Int): Vec3i = Vec3i(x * t, y * t, z * t)

    override fun times(t: Double): Vec3i = Vec3i(x * t, y * t, z * t)

    override fun times(v: Vec3i): Vec3i = cross(v)

    override fun timesAssign(t: Int) {
        x *= t
        y *= t
        z *= t
    }

    override fun timesAssign(t: Double) {
        x = toInt(x * t)
        y = toInt(y * t)
        z = toInt(z * t)
    }

    override fun length(): Double = Math.sqrt((x * x + y * y + z * z).toDouble())

    override fun normalize(): Vec3d {
        val length = length()
        return Vec3d(x / length, y / length, z / length)
    }
}