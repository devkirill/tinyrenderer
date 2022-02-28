import utils.Image

class Render {
    companion object {
        operator fun invoke(width: Int, height: Int, fill: RenderContext.() -> Unit): Image {
            val renderContext = RenderContext(width, height)
            renderContext.fill()
            return renderContext.image
        }
    }
}

