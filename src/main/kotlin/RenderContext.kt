import utils.Color
import utils.Color.Companion.RED
import utils.Color.Companion.WHITE
import utils.Image

data class RenderContext(val width: Int, val height: Int) {
    val image = Image(width, height)

    fun importModel(path: String) {

    }

    fun test() {
        image.line(13, 20, 80, 40, WHITE)
        image.line(20, 13, 40, 80, RED)
        image.line(80, 40, 13, 20, RED)
    }
}

fun Image.line(x0: Int, y0: Int, x1: Int, y1: Int, color: Color = WHITE) {
    for (x in x0..x1) {
        val t = (x - x0).toDouble() / (x1 - x0).toDouble()
        val y = (y0 * (1.0 - t) + y1 * t).toInt()
        this[x, y] = color
    }
}