package utils

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class WavefrontObj(
    val vertices: List<Vec3d>,
    val textureCoords: List<Vec2d>,
    val polygons: List<Polygon>,
    val normalMap: List<Vec3d>
) {

    class Polygon(val polygon: List<Vertex>) {
        val size: Int get() = polygon.size

        operator fun get(node: Int): Vertex {
            return polygon[node % polygon.size]
        }
    }

    data class Vertex(val vertice: Int, val texture: Int)

    companion object {
        fun parse(fileName: String): WavefrontObj {
            val file: BufferedReader
            try {
                file = BufferedReader(FileReader(fileName))
            } catch (e: IOException) {
                throw IllegalStateException(e)
            }

            val splitFileLines = file.lines()
                .map { line -> line.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() } }
                .filter { it.isNotEmpty() }
                .toList()

            val vertices = splitFileLines
                .filter { l -> l[0] == "v" }
                .map { l -> Vec3d(l[1].toDouble(), l[2].toDouble(), l[3].toDouble()) }
                .toList()

            val textureCoord = splitFileLines
                .filter { l -> l[0] == "vt" }
                .map { l -> Vec2d(l[1].toDouble(), l[2].toDouble()) }
                .toList()

            val normalMap = splitFileLines
                .filter { l -> l[0] == "vn" }
                .map { l -> Vec3d(l[1].toDouble(), l[2].toDouble(), l[3].toDouble()) }
                .toList()

            val polygons = splitFileLines
                .filter { l -> l[0] == "f" }
                .map { l ->
                    l.drop(1)
                        .map { s ->
                            val v = s.split("/".toRegex()).dropLastWhile { it.isEmpty() }
                            Vertex(v[0].toInt() - 1, (v.getOrNull(1)?.toInt() ?: 1) - 1)
                        }
                        .toList()
                }
                .map { Polygon(it) }
                .toList()

            return WavefrontObj(vertices, textureCoord, polygons, normalMap)
        }
    }
}