package tower

import processing.core.PApplet

fun PApplet.line(x1: Int, y1: Int, x2: Int, y2: Int) {
    line(x1.toFloat(), y1.toFloat(), x2.toFloat(), y2.toFloat())
}

fun PApplet.strokeWeight(w: Int) {
    this.strokeWeight(w.toFloat())
}

fun PApplet.rect(lux: Int, luy: Int, w: Int, h: Int) {
    rect(lux.toFloat(), luy.toFloat(), w.toFloat(), h.toFloat())
}

fun PApplet.text(s: String, x: Int, y: Int) {
    this.text(s, x.toFloat(), y.toFloat())
}