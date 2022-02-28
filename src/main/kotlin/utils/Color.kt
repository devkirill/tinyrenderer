package utils

class Color(val value: Int) {
    val r: Int get() = value shr 16 and 0xFF
    val g: Int get() = value shr 8 and 0xFF
    val b: Int get() = value shr 0 and 0xFF

    constructor(r: Int, g: Int, b: Int, a: Int = 0) : this(
        a and 0xFF shl 24 or
                (r and 0xFF shl 16) or
                (g and 0xFF shl 8) or
                (b and 0xFF shl 0)
    )

    constructor(r: Double, g: Double, b: Double) : this((r * 255).toInt(), (g * 255).toInt(), (b * 255).toInt()) {}

    companion object {
        val WHITE = Color(255, 255, 255)
        val BLACK = Color(0, 0, 0)
        val RED = Color(255, 0, 0)
        val GREEN = Color(0, 255, 0)
        val BLUE = Color(0, 0, 255)
    }

    operator fun times(d: Double): Color = Color((r * d).toInt(), (g * d).toInt(), (b * d).toInt())
}