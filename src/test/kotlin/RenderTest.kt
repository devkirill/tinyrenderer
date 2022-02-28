import org.junit.jupiter.api.Test

internal class RenderTest {
    @Test
    fun render() {
        val image = Render(200, 200) {
            test()
        }
        image.saveImage("test")
    }
}