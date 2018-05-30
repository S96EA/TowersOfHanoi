package tower

import processing.core.PApplet

/**
 *
 * position:
 *
 *       '
 * ------'------
 * -     '     -
 * ------*------
 *       '
 *       '
 * **/


class Chunk(val context: PApplet) {
    val height = 25
    var positionX = 0
    var positionY = 0
    var width = 100
    var isPressed: Boolean = false
    var isMove: Boolean = false
    var pole: Pole? = null

    constructor(ctx: PApplet, width: Int) : this(ctx) {
        this.width = width
    }

    fun draw() {
        if (!isPressed) {
            context.rect(positionX - width / 2, positionY - height, width, height)
        } else {
            context.pushStyle()
            context.fill(0F, 0F, 255F)
            context.rect(positionX - width / 2, positionY - height, width, height)
            context.popStyle()
        }
    }

    fun contains(mouseX: Int, mouseY: Int): Boolean {
        return mouseY < positionY && mouseY > positionY - height
                && mouseX > positionX - width / 2 && mouseX < positionX + width / 2
    }

    fun move(mouseX: Int, mouseY: Int) {
        if (pole!!.positionY - pole!!.length < positionY && positionX == pole!!.positionX) {
        } else {
            positionX = mouseX
        }

        if (positionX == pole!!.positionX && pole!!.positionY - pole!!.chunks.sumBy { it.height } + height <= mouseY) {
        } else {
            positionY = mouseY
        }

    }
}