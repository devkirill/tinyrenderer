package utils

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Texture(val texture: BufferedImage) {
    constructor(file: File) : this(ImageIO.read(file))
    constructor(path: String) : this(File(path))

    val width: Int = texture.width
    val height: Int = texture.height

    operator fun get(x: Int, y: Int): Color = Color(texture.getRGB(x, height - 1 - y))
    operator fun get(x: Double, y: Double): Color = get((x * width + 0.5).toInt(), (y * height + 0.5).toInt())
}