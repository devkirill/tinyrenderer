package utils

class Color(val value: Int) {
    constructor(r: Int, g: Int, b: Int, a: Int = 0) : this(a and 0xFF shl 24 or
            (r and 0xFF shl 16) or
            (g and 0xFF shl 8) or
            (b and 0xFF shl 0))

    constructor(r: Double, g: Double, b: Double) : this((r * 255).toInt(), (g * 255).toInt(), (b * 255).toInt()) {}

    companion object {
        val white = Color(255, 255, 255)
        val black = Color(0, 0, 0)
        val red = Color(255, 0, 0)
        val green = Color(0, 255, 0)
        val blue = Color(0, 0, 255)
    }
}
