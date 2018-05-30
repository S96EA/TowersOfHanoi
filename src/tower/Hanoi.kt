package tower

import processing.core.PApplet
import processing.event.KeyEvent

class Hanoi : PApplet() {

    val poles = ArrayList<Pole>()
    var steps: Int = 0
    var numChunks = 4
    var isAuto = false
    var moveSteps: List<Pair<Int, Int>>? = null
    var moveStepIdx = 0
    val timeInterval =10

    override fun settings() {
        size(1200, 800)
    }

    override fun setup() {
        for (i in 0 until 3) {
            poles.add(Pole(this))
        }

        val interval: Int = width / poles.size
        var pos = interval / 2

        poles.forEach {
            it.positionX = pos
            pos += interval
        }

        var width = interval * 2 / 3
        for (i in 0 until numChunks) {
            poles[0].add(Chunk(this, width))
            width -= 40
        }

        moveSteps = getMoveSteps0()
    }

    private fun getMoveSteps0(): List<Pair<Int, Int>> {
        return Solver(numChunks).solve()
    }

    fun getStep(): Pair<Int, Int> {
        return moveSteps!![moveStepIdx++]
    }


    override fun draw() {
        pushStyle()
        background(100F, 0F, 0F)
        textSize(20F)
        text("steps:$steps", 10, 30)


        if (isAuto) {
            if (frameCount % timeInterval == 0) {
                if (moveStepIdx < moveSteps!!.size) {
                    val step = getStep()
//                    println(step)
                    poles[step.second].add(poles[step.first].pop()!!)
                    steps++
                }
            }
        }

        poles.forEach { it.draw() }

        poles.forEach { it.chunks.forEach { it.draw() } }
        popStyle()
    }

    override fun mousePressed() {
        poles.forEach { pole ->
            pole.chunks.forEach {
                it.isPressed = it.contains(mouseX, mouseY)
                if (it == pole.chunks.last() && it.isPressed) {
                    it.move(mouseX, mouseY)
                    it.isMove = true
                }
            }
        }
    }

    override fun mouseDragged() {
        poles.forEach {
            it.chunks.forEach {
                if (it.isMove) {
                    it.move(mouseX, mouseY)
                }
            }
        }
    }

    override fun mouseReleased() {
        poles.forEach { pole ->
            pole.chunks.forEach {
                if (it.isMove) {
                    var minX = Int.MAX_VALUE
                    var minP = 0
                    for (i in 0 until poles.size) {
                        val d = Math.abs(it.positionX - poles[i].positionX)
                        if (d < minX) {
                            minX = d
                            minP = i
                        }
                    }
                    if (poles[minP].top() == null || poles[minP].top()?.width!! > it.width) {
                        pole.pop()
                        poles[minP].add(it)
                        steps++
                    } else {
                        pole.pop()
                        pole.add(it)
                    }
                }
                it.isPressed = false
                it.isMove = false
            }
        }
    }

    override fun keyPressed(event: KeyEvent?) {
        if (event!!.key == 's') {
//            poles.clear()
//            setup()
            isAuto = !isAuto
        }
    }
}