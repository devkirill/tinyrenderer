package geom.vec

interface Vec2<N, T : Vec2<N, T>> : Vec<N, T> {
    fun normalize(): Vec2d
}