package day03

import java.io.File

class QuestionOne {
    private val symbolSet = setOf('*', '@', '=', '+', '!', '#', '$', '%', '^', '&', '-', '/', '+', '=')

    operator fun invoke(strings: List<String>): Int =
        strings
            .flatMapIndexed { index: Int, s: String ->
                findValidNumbers(index, s, strings)
            }
            .sum()


    private fun findValidNumbers(lineIndex: Int, str: String, strings: List<String>): List<Int> =
        "[0-9]+".toRegex().findAll(str)
            .filter { isAdjacent(lineIndex, it.range, strings) }
            .map {
                it.value.toInt()
            }.toList()

    private fun isAdjacent(lineIndex: Int, indexRange: IntRange, strings: List<String>): Boolean {
        for (i in lineIndex - 1 .. lineIndex + 1) {
            if (i < 0 || i >= strings.count()) {
                continue
            }
            for (j in indexRange.first - 1 .. indexRange.last + 1) {
                if (j < 0 || j >= strings[i].length) {
                    continue
                }
                if (symbolSet.contains(strings[i][j])) {
                    return true
                }
            }
        }
        return false
    }
}

fun main(args: Array<String>) {
    val question = QuestionOne()

    val test = question(
        strings = File(args[0]).readLines()
    )
    println("Test: $test")

    val final = question(
        strings = File(args[1]).readLines()
    )
    println("Final: $final")
}
