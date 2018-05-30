package tower


class Solver(val n: Int) {
    val steps: ArrayList<Pair<Int, Int>> = ArrayList()

    fun solve(): List<Pair<Int, Int>> {
        solve(n, 0, 2)
        return steps
    }

    private fun solve(n: Int, start: Int, end: Int) {
        if (n == 1) {
            steps.add(Pair(start, end))
            return
        }
        val other = if (start != 0 && end != 0) 0 else if (start != 1 && end != 1) 1 else 2
        solve(n - 1, start, other)
        solve(1, start, end)
        solve(n - 1, other, end)
    }
}

fun main(args: Array<String>) {
    val solver = Solver(3)
    solver.solve().forEach { println(it) }
}
