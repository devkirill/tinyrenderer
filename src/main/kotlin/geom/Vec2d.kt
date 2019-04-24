package geom

data class Vec2d(val x: Double, val y: Double) : Vec2<Double, Vec2d> {
    override fun plus(v: Vec2d): Vec2d = Vec2d(x + v.x, y + v.y)

    override fun minus(v: Vec2d): Vec2d = Vec2d(x - v.x, y - v.y)

    override fun times(t: Int): Vec2d = Vec2d(x * t, y * t)

    override fun times(t: Double): Vec2d = Vec2d(x * t, y * t)

    override fun length(): Double = Math.sqrt(x * x + y * y)

    override fun normalize(): Vec2d {
        val length = length()
        return Vec2d(x / length, y / length)
    }
}