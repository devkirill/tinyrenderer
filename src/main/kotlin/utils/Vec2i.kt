package utils

data class Vec2i(val x: Int, val y: Int) {
    constructor(v: Vec2d) : this(v.x.toInt(), v.y.toInt())
    constructor(x: Double, y: Double) : this(x.toInt(), y.toInt())

    operator fun plus(p: Vec2i) = Vec2i(x + p.x, y + p.y)
    operator fun minus(p: Vec2i) = Vec2i(x - p.x, y - p.y)
    operator fun times(n: Int) = Vec2i(x * n, y * n)
    operator fun div(n: Int) = Vec2i(x / n, y / n)
}

data class Vec2d(val x: Double, val y: Double) {
    constructor(v: Vec2i) : this(v.x.toDouble(), v.y.toDouble())

    operator fun plus(p: Vec2d) = Vec2d(x + p.x, y + p.y)
    operator fun minus(p: Vec2d) = Vec2d(x - p.x, y - p.y)
    operator fun times(n: Double) = Vec2d(x * n, y * n)
    operator fun times(n: Int) = Vec2d(x * n, y * n)
    operator fun div(n: Double) = Vec2d(x / n, y / n)
    operator fun div(n: Int) = Vec2d(x / n, y / n)
}