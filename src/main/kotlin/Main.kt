import geom.Vec3d
import geom.Vec3i
import utils.Color
import utils.Image
import utils.WavefrontObj
import java.lang.Math.max

fun triangle(t0: Vec3i, t1: Vec3i, t2: Vec3i, image: Image, color: Color) {
    var t0 = t0
    var t1 = t1
    var t2 = t2
    if (t0.y > t1.y) t0 = t1.also { t1 = t0 }
    if (t0.y > t2.y) t0 = t2.also { t2 = t0 }
    if (t1.y > t2.y) t1 = t2.also { t2 = t1 }

    val totalHeight = t2.y - t0.y
    for (y in 0 until totalHeight) {
        val secondHalf = y > t1.y - t0.y || t1.y == t0.y
        val segmentHeight = if (secondHalf) t2.y - t1.y else t1.y - t0.y
        val alpha = y.toDouble() / totalHeight
        val beta = (y - if (secondHalf) t1.y - t0.y else 0).toDouble() / segmentHeight
        var a = t0 + (t2 - t0) * alpha
        var b = if (secondHalf) t1 + (t2 - t1) * beta else t0 + (t1 - t0) * beta
        if (a.x > b.x) a = b.also { b = a }
        for (x in a.x..b.x) {
            val phi = if (a.x == b.x) 1.0 else (x - a.x).toDouble() / (b.x - a.x).toDouble()
            val p = a + (b - a) * phi
            image[p.x, p.y, p.z.toDouble()] = color
        }
    }
}

fun toVer3i(ver3d: Vec3d, image: Image): Vec3i {
    val x = (ver3d.x + 1.0) * image.width / 2.0
    val y = (ver3d.y + 1.0) * image.height / 2.0
    val z = (ver3d.z + 1.0) * max(image.width, image.height) / 2.0
    return Vec3i(x, y, z)
}

fun main(args: Array<String>) {
    val image = Image(1000, 1000)

    val obj = WavefrontObj.parse("src/main/resources/african_head/african_head.obj")
//    val obj = utils.WavefrontObj.parse("src/main/resources/boggie/body.obj")
//    val obj = utils.WavefrontObj.parse("src/main/resources/diablo3_pose/diablo3_pose.obj")

    val light = Vec3d(0.0, 0.0, -1.0).normalize()

    obj.polygons
            .forEach { p ->
                val v0 = obj.vertices[p[0]]
                val v1 = obj.vertices[p[1]]
                val v2 = obj.vertices[p[2]]

                val b1 = v2 - v0
                val b2 = v1 - v0
                val norm = b1.cross(b2).normalize()
                val intensity = norm.scalar(light)
                if (intensity > 0) {
                    val p0 = toVer3i(v0, image)
                    val p1 = toVer3i(v1, image)
                    val p2 = toVer3i(v2, image)
                    triangle(p0, p1, p2, image, Color(intensity, intensity, intensity))
                }
            }

    image.saveImage("output")
}