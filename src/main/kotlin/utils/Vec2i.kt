package utils

data class Vec2i(val x: Int, val y: Int) {
    operator fun plus(p: Vec2i) = Vec2i(x + p.x, y + p.y)
    operator fun minus(p: Vec2i) = Vec2i(x - p.x, y - p.y)
    operator fun times(n: Int) = Vec2i(x * n, y * n)
    operator fun div(n: Int) = Vec2i(x / n, y / n)
}

data class Vec2v(val x: Double, val y: Double) {
    constructor(v: Vec2i) : this(v.x.toDouble(), v.y.toDouble())

    operator fun plus(p: Vec2v) = Vec2v(x + p.x, y + p.y)
    operator fun minus(p: Vec2v) = Vec2v(x - p.x, y - p.y)
    operator fun times(n: Double) = Vec2v(x * n, y * n)
    operator fun div(n: Double) = Vec2v(x / n, y / n)
}