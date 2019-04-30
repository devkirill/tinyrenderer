package geom.vec

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