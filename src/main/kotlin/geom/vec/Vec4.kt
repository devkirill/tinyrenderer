package geom.vec

interface Vec4<N, T : Vec4<N, T>> : Vec<N, T> {
    fun cross(v: T): T
    fun scalar(v: T): N
    fun normalize(): Vec3d
}