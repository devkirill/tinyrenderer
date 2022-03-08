import utils.*
import utils.Color.Companion.GREEN
import utils.Color.Companion.RED
import utils.Color.Companion.WHITE
import kotlin.math.abs
import kotlin.math.min

data class RenderContext(val width: Int, val height: Int) {
    val image = Image(width, height)

    fun test() {
        image.line(13, 20, 80, 40, WHITE)
        image.line(20, 13, 40, 80, RED)
        image.line(80, 40, 13, 20, RED)

        image.triangle(Vec2i(10, 70), Vec2i(50, 160), Vec2i(70, 80), RED)
        image.triangle(Vec2i(180, 50), Vec2i(150, 1), Vec2i(70, 180), WHITE)
        image.triangle(Vec2i(180, 150), Vec2i(120, 160), Vec2i(130, 180), GREEN)
    }

    fun importModel(path: String) {
        val obj = WavefrontObj.parse("$path.obj")
        for (p in obj.polygons) {
            val t = p.polygon.map { it.vertice }.map { obj.vertices[it] }
            image.line(t[0], t[1], WHITE)
            image.line(t[1], t[2], WHITE)
            image.line(t[0], t[2], WHITE)
        }
    }
}

fun Image.line(x0: Int, y0: Int, x1: Int, y1: Int, color: Color = WHITE, steep: Boolean = false) {
    if (abs(x0 - x1) < abs(y0 - y1)) {
        line(y0, x0, y1, x1, color, true)
        return
    }
    if (x0 > x1) {
        line(x1, y1, x0, y0, color, steep)
        return
    }
    val dx = x1 - x0
    val dy = y1 - y0
    val derror2 = abs(dy) * 2
    var error2 = 0
    var y = y0
    for (x in x0..x1) {
        if (steep) {
            this[y, x] = color
        } else {
            this[x, y] = color
        }
        error2 += derror2

        if (error2 > dx) {
            y += if (y1 > y0) 1 else -1
            error2 -= dx * 2;
        }
    }
}

fun Image.line(p0: Vec2i, p1: Vec2i, color: Color = WHITE) {
    line(p0.x, p0.y, p1.x, p1.y, color)
}

fun Image.line(p0: Vec2d, p1: Vec2d, color: Color = WHITE) {
    fun convert(p: Vec2d): Vec2i {
        val m = min(width, height)
        return (Vec2i(p.x * m, p.y * m) + Vec2i(width, height)) / 2
    }
    line(convert(p0), convert(p1), color)
}

fun Image.line(p0: Vec3d, p1: Vec3d, color: Color = WHITE) {
    line(Vec2d(p0.x, p0.y), Vec2d(p1.x, p1.y), color)
}

fun Image.triangle(t0: Vec2i, t1: Vec2i, t2: Vec2i, color: Color = WHITE) {
    val t = listOf(t0, t1, t2).sortedBy { it.y }

    val totalHeight = t[2].y - t[0].y
    for (i in 0..totalHeight) {
        val secondHalf = (i > t[1].y - t[0].y) || (t[1].y == t[0].y)
        val segmentHeight = if (secondHalf) (t[2] - t[1]).y else (t[1] - t[0]).y
        var a = t[0] + (t[2] - t[0]) * i / totalHeight
        val beta = i - (if (secondHalf) (t[1] - t[0]).y else 0)
        var b =
            if (secondHalf) t[1] + (t[2] - t[1]) * beta / segmentHeight else t[0] + (t[1] - t[0]) * beta / segmentHeight
        if (a.x > b.x) {
            val z = a
            a = b
            b = z
        }
        for (j in a.x..b.x) {
            this[j, t[0].y + i] = color
        }
    }
}