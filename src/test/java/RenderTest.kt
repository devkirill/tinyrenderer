import geom.vec.Vec3d
import org.junit.Test
import utils.Image

data class Render(var output : String = "output", var resources: MutableList<String> = mutableListOf()) {
    fun addResource(resource: String) = resources.add(resource)

    fun render() {
        val imageRender = ImageRender(
                Image(2048, 2048),
                eye = Vec3d(0, 0, 1),
                center = Vec3d(0, 0, 0),
                light = Vec3d(0.0, 0.0, 1.0).normalize(),
                up = Vec3d(0, 1, 0)
        )

        for (resource in resources) {
            imageRender.render(resource)
        }

        imageRender.image.saveImage(output)
    }

    companion object {
        operator fun invoke(config: Render.() -> Unit) {
            val config = Render()
            config.config()
            config.render()
        }
    }
}

internal class RenderTest {
    @Test
    fun renderDiablo() {
        Render {
            output = "diablo"
            addResource("src/main/resources/diablo3_pose/diablo3_pose")
        }
    }

    @Test
    fun renderAfricanHead() {
        Render {
            output = "african_head"
            addResource("src/main/resources/african_head/african_head")
            addResource("src/main/resources/african_head/african_head_eye_inner")
        }
    }

    @Test
    fun renderBoggie() {
        Render {
            output = "boggie"
            addResource("src/main/resources/boggie/eyes")
            addResource("src/main/resources/boggie/body")
            addResource("src/main/resources/boggie/head")
        }
    }
}