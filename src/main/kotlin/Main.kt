import geom.vec.*
import utils.Image
import utils.VectorImage
import utils.WavefrontObj
import java.lang.Math.max
import java.lang.Math.min

fun barycentric(vararg pts: Vec3i, P: Vec2i): Vec3d {
    val u = cross(Vec3d(pts[2].x - pts[0].x, pts[1].x - pts[0].x, pts[0].x - P.x), Vec3d(pts[2].y - pts[0].y, pts[1].y - pts[0].y, pts[0].y - P.y))
    if (Math.abs(u.z) < 1) return Vec3d(-1, 1, 1)
    return Vec3d(1.0 - (u.x + u.y) / u.z, u.y / u.z, u.x / u.z)
}

fun triangle(vararg pts: Vec3i, image: Image, brightness: Double = 1.0, textureCoord: List<Vec2d>, texture: VectorImage) {
    var boxmin = Vec2i(image.width - 1, image.height - 1)
    var boxmax = Vec2i(0, 0)
    for (i in 0..2) {
        boxmin = Vec2i(min(boxmin.x, pts[i].x), min(boxmin.y, pts[i].y))
        boxmax = Vec2i(max(boxmax.x, pts[i].x), max(boxmax.y, pts[i].y))
    }

    for (x in boxmin.x..boxmax.x) {
        for (y in boxmin.y..boxmax.y) {
            val coord = barycentric(*pts, P = Vec2i(x, y))
            if (coord.x >= 0 && coord.y >= 0 && coord.z >= 0) {
                val z = pts[0].z * coord.x + pts[1].z * coord.y + pts[2].z * coord.z
                val v1 = Vec3d(textureCoord[0].x, textureCoord[1].x, textureCoord[2].x)
                val v2 = Vec3d(textureCoord[0].y, textureCoord[1].y, textureCoord[2].y)
                val p = Vec2d(scalar(coord, v1), scalar(coord, v2))
                image[x, y, z] = texture[p] * brightness
            }
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
//    val obj = WavefrontObj.parse("src/main/resources/diablo3_pose/diablo3_pose.obj")
    val texture = VectorImage("src/main/resources/african_head/african_head_diffuse.png")
//    val texture = VectorImage("src/main/resources/diablo3_pose/diablo3_pose_diffuse.png")

    val light = Vec3d(0.0, 0.0, 1.0).normalize()

    obj.polygons
            .forEach { p ->
                val v = (0..2).map { obj.vertices[p[it].vertice] }

                val norm = cross(v[1] - v[0], v[2] - v[0]).normalize()
                val intensity = scalar(norm, light)
                if (intensity > 0) {
                    val pi = v.map { toVer3i(it, image) }
                    triangle(*pi.toTypedArray(), image = image, brightness = intensity, textureCoord = (0..2).map { obj.textureCoords[p[it].texture] }, texture = texture)
                }
            }

    image.saveImage("output")
}