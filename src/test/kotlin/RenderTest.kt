import org.junit.jupiter.api.Test

internal class RenderTest {
    @Test
    fun render() {
        val image = Render(200, 200) {
            test()
        }
        image.saveImage("test")
    }

    @Test
    fun african_head() {
        val image = Render(2048, 2048) {
            importModel("resources/african_head/african_head")
        }
        image.saveImage("african_head")
    }

    @Test
    fun boggie() {
        val image = Render(2048, 2048) {
            importModel("resources/boggie/body")
            importModel("resources/boggie/head")
        }
        image.saveImage("boggie")
    }

    @Test
    fun diablo3_pose() {
        val image = Render(2048, 2048) {
            importModel("resources/diablo3_pose/diablo3_pose")
            importModel("resources/diablo3_pose/diablo3_pose")
        }
        image.saveImage("diablo3_pose")
    }
}