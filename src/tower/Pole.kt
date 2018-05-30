package tower

import processing.core.PApplet
import java.util.concurrent.ConcurrentLinkedDeque

class Pole(val context: PApplet) {
    var length = 600
    var positionX = 0
    var positionY = context.height
    var strokeWeight = 5
    val chunks = ConcurrentLinkedDeque<Chunk>()

    fun draw() {
        context.pushStyle()
        context.strokeWeight(strokeWeight)
        context.line(positionX, positionY, positionX, positionY - length)
        context.popStyle()
    }

    private fun rearrange() {
        var y = positionY
        chunks.forEach {
            it.positionX = positionX
            it.positionY = y
            y -= it.height
        }
    }

    fun add(chunk: Chunk) {
        chunk.pole = this
        chunks.addLast(chunk)
        rearrange()
    }

    fun pop(): Chunk? {
        return chunks.pollLast()
    }

    fun top(): Chunk? {
        return chunks.peekLast()
    }
}