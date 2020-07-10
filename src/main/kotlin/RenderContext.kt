import geom.vec.*
import utils.Image
import utils.VectorImage
import utils.WavefrontObj
import utils.get
import kotlin.math.max
import kotlin.math.min

class RenderContext(val width: Int = 4096, val height: Int = 4096) {
    var eye = Vec3d(0, 0, 1)
    var center = Vec3d(0, 0, 0)
    var light = Vec3d(0.0, 0.0, 1.0).normalize()
    var up = Vec3d(0, 1, 0)

    var models = mutableListOf<String>()

    var objects = mutableListOf<WavefrontObj>()
}

fun RenderContext.renderTriangle() {

}

fun RenderContext.triangle(vararg pts: Vec3i, norm: Vec3d, light: Vec3d, image: Image, textureCoord: List<Vec2d>, texture: VectorImage, normalize: VectorImage) {
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
                val brightness = normalize[p].g / 255.0
                image[x, y, z] = texture[p] * brightness
            }
        }
    }
}

fun RenderContext.transform(ver3d: Vec3d): Vec3d {
    val z = ver3d.z
    val c = 2.5
    val zc = 1 - z / c
    return ver3d
//    return Vec3d(ver3d.x / zc, ver3d.y / zc, ver3d.z / zc)
}

fun RenderContext.importModel(model: String): RenderContext {
    models.add(model)

    return this
}

fun RenderContext.render(resultPath: String): RenderContext {
    val image = Image(width, height)

    for (model in models) {
        val obj = WavefrontObj.parse("$model.obj")
        val texture = VectorImage("${model}_diffuse.png")
        val normalize = VectorImage("${model}_nm_tangent.png")

        obj.polygons
                .forEach { p ->
                    val v = (0..2).map { obj.vertices[p[it].vertice] }

                    val norm = cross(v[1] - v[0], v[2] - v[0]).normalize()
                    val intensity = scalar(norm, light)
                    if (intensity > 0) {
                        val pi = v
                                .map { transform(it) }
                                .map { toVer3i(it, image) }
                        triangle(*pi.toTypedArray(), norm = norm, light = light, image = image, textureCoord = (0..2).map { obj.textureCoords[p[it].texture] }, texture = texture, normalize = normalize)
                    }
                }
    }

    image.saveImage(resultPath)

    return this
}

fun RenderContext.save() {

}