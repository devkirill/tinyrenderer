package geom

data class Vec2i(val x: Int, val y: Int) : Vec2<Double, Vec2i> {
    constructor(x: Double, y: Double) : this((x + 1e-9).toInt(), (y + 1e-9).toInt())

    override fun plus(v: Vec2i): Vec2i = Vec2i(x + v.x, y + v.y)

    override fun minus(v: Vec2i): Vec2i = Vec2i(x - v.x, y - v.y)

    override fun times(t: Int): Vec2i = Vec2i(x * t, y * t)

    override fun times(t: Double): Vec2i = Vec2i(x * t, y * t)

    override fun length(): Double = Math.sqrt((x * x + y * y).toDouble())

    override fun normalize(): Vec2d {
        val length = length()
        return Vec2d(x / length, y / length)
    }
}