import org.junit.jupiter.api.Test

internal class RenderTest {
    @Test
    fun render() {
        val image = Render(128, 128) {
            test()
        }
        image.saveImage("test")
    }
}