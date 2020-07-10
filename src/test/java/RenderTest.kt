import org.junit.Test

internal class RenderTest {
    @Test
    fun renderDiablo() {
        RenderContext(4096, 4096)
                .importModel("src/main/resources/diablo3_pose/diablo3_pose")
                .render("diablo")
    }

    @Test
    fun renderAfricanHead() {
        RenderContext(4096, 4096)
                .importModel("src/main/resources/african_head/african_head")
                .render("african_head_without_eye")
                .importModel("src/main/resources/african_head/african_head_eye_inner")
                .render("african_head")
    }

    @Test
    fun renderBoggie() {
        RenderContext(4096, 4096)
                .importModel("src/main/resources/boggie/eyes")
                .render("boggie_1")
                .importModel("src/main/resources/boggie/head")
                .render("boggie_2")
                .importModel("src/main/resources/boggie/body")
                .render("boggie_3")
    }
}