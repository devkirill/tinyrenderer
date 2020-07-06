import geom.vec.Vec2i
import geom.vec.Vec3d
import geom.vec.Vec3i
import geom.vec.cross
import utils.Image
import kotlin.math.max

fun barycentric(vararg pts: Vec3i, P: Vec2i): Vec3d {
    val u = cross(Vec3d(pts[2].x - pts[0].x, pts[1].x - pts[0].x, pts[0].x - P.x), Vec3d(pts[2].y - pts[0].y, pts[1].y - pts[0].y, pts[0].y - P.y))
    if (Math.abs(u.z) < 1) return Vec3d(-1, 1, 1)
    return Vec3d(1.0 - (u.x + u.y) / u.z, u.y / u.z, u.x / u.z)
}

fun toVer3i(ver3d: Vec3d, image: Image): Vec3i {
    val x = (ver3d.x + 1.0) * image.width / 2.0
    val y = (ver3d.y + 1.0) * image.height / 2.0
    val z = (ver3d.z + 1.0) * max(image.width, image.height) / 2.0
    return Vec3i(x, y, z)
}

