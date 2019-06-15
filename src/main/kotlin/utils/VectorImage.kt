package utils

import geom.vec.Vec2d
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class VectorImage(private val image: BufferedImage) {
    constructor(width: Int, height: Int) : this(BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB))
    constructor(file: File) : this(ImageIO.read(file))
    constructor(path: String) : this(File(path))

    operator fun get(x: Double, y: Double): Int = image.getRGB(toInt(x, image.width), toInt(y, image.height))

    operator fun set(x: Double, y: Double, c: Int) = image.setRGB(toInt(x, image.width), toInt(y, image.height), c)

    operator fun get(point: Vec2d): Color = Color(get(point.x, 1 - point.y))

    companion object {
        private fun toInt(d: Double, s: Int): Int = maxOf(0, minOf(s - 1, (d * s).toInt()))
    }
}