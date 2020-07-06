package geom

fun toInt(d: Double): Int = (d + 1e-9).toInt()

fun <N, T : Vec3<N, T>> cross(v1: T, v2: T): T = v1.cross(v2)
fun <N, T : Vec3<N, T>> scalar(v1: T, v2: T): N = v1.scalar(v2)

interface Vec<N, T : Vec<N, T>> {
    operator fun plus(v: T): T
    operator fun plusAssign(v: T)
    operator fun minus(v: T): T
    operator fun minusAssign(v: T)
    operator fun times(t: Int): T
    operator fun times(t: Double): T
    operator fun timesAssign(t: Int)
    operator fun timesAssign(t: Double)
    fun length(): Double
}

interface Vec2<N, T : Vec2<N, T>> : Vec<N, T> {
    fun normalize(): Vec2d
}

data class Vec2d(var x: Double, var y: Double) : Vec2<Double, Vec2d> {
    override fun plus(v: Vec2d): Vec2d = Vec2d(x + v.x, y + v.y)

    override fun plusAssign(v: Vec2d) {
        x += v.x
        y += v.y
    }

    override fun minus(v: Vec2d): Vec2d = Vec2d(x - v.x, y - v.y)

    override fun minusAssign(v: Vec2d) {
        x -= v.x
        y -= v.y
    }

    override fun times(t: Int): Vec2d = Vec2d(x * t, y * t)

    override fun times(t: Double): Vec2d = Vec2d(x * t, y * t)

    override fun timesAssign(t: Int) {
        x *= t
        y *= t
    }

    override fun timesAssign(t: Double) {
        x *= t
        y *= t
    }

    override fun length(): Double = Math.sqrt(x * x + y * y)

    override fun normalize(): Vec2d {
        val length = length()
        return Vec2d(x / length, y / length)
    }
}

data class Vec2i(var x: Int, var y: Int) : Vec2<Int, Vec2i> {
    constructor(x: Double, y: Double) : this(toInt(x), toInt(y))

    override fun plus(v: Vec2i): Vec2i = Vec2i(x + v.x, y + v.y)

    override fun plusAssign(v: Vec2i) {
        x += v.x
        y += v.y
    }

    override fun minus(v: Vec2i): Vec2i = Vec2i(x - v.x, y - v.y)

    override fun minusAssign(v: Vec2i) {
        x -= v.x
        y -= v.y
    }

    override fun times(t: Int): Vec2i = Vec2i(x * t, y * t)

    override fun times(t: Double): Vec2i = Vec2i(x * t, y * t)

    override fun timesAssign(t: Int) {
        x *= t
        y *= t
    }

    override fun timesAssign(t: Double) {
        x = toInt(x * t)
        y = toInt(y * t)
    }

    override fun length(): Double = Math.sqrt((x * x + y * y).toDouble())

    override fun normalize(): Vec2d {
        val length = length()
        return Vec2d(x / length, y / length)
    }
}

interface Vec3<N, T : Vec3<N, T>> : Vec<N, T> {
    fun times(v: T): T
    fun cross(v: T): T
    fun scalar(v: T): N
    fun normalize(): Vec3d
}

data class Vec3d(var x: Double, var y: Double, var z: Double) : Vec3<Double, Vec3d> {
    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    override fun cross(v: Vec3d): Vec3d = Vec3d(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)

    override fun scalar(v: Vec3d): Double = x * v.x + y * v.y + z * v.z

    override fun plus(v: Vec3d): Vec3d = Vec3d(x + v.x, y + v.y, z + v.z)

    override fun plusAssign(v: Vec3d) {
        x += v.x
        y += v.y
        z += v.z
    }

    override fun minus(v: Vec3d): Vec3d = Vec3d(x - v.x, y - v.y, z - v.z)

    override fun minusAssign(v: Vec3d) {
        x -= v.x
        y -= v.y
        z -= v.z
    }

    override fun times(t: Int): Vec3d = Vec3d(x * t, y * t, z * t)

    override fun times(t: Double): Vec3d = Vec3d(x * t, y * t, z * t)

    override fun times(v: Vec3d): Vec3d = cross(v)

    override fun timesAssign(t: Int) {
        x *= t
        y *= t
        z *= t
    }

    override fun timesAssign(t: Double) {
        x *= t
        y *= t
        z *= t
    }

    override fun length(): Double = Math.sqrt(x * x + y * y + z * z)

    override fun normalize(): Vec3d {
        val length = length()
        return Vec3d(x / length, y / length, z / length)
    }
}

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

interface Vec4<N, T : Vec4<N, T>> : Vec<N, T> {
    fun cross(v: T): T
    fun scalar(v: T): N
    fun normalize(): Vec3d
}