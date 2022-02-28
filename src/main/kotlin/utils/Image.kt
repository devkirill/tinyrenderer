package utils

import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class Image(val width: Int, val height: Int) {
    private val image: BufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    private val zBuffer = ArrayList<Double>()

    init {
        for (i in 0 until width * height) {
            this.zBuffer.add(java.lang.Double.MIN_VALUE)
        }
    }

    operator fun set(x: Int, y: Int, color: Color) {
        if (x in 0 until width && y in 0 until height) {
            image.setRGB(x, height - 1 - y, color.value)
        }
    }

    operator fun set(x: Int, y: Int, z: Double, color: Color) {
        if (x in 0 until width && y in 0 until height && zBuffer[x + y * width] < z) {
            zBuffer[x + y * width] = z
            image.setRGB(x, height - 1 - y, color.value)
        }
    }

    fun saveImage(filename: String) {
        try {
            ImageIO.write(image, "PNG", File("$filename.png"))
        } catch (e: IOException) {
            throw IllegalStateException(e)
        }
    }
}