package day01

import java.io.File

class QuestionOne {
    operator fun invoke(strings: List<String>): Int =
        strings.sumOf(::extractNumber)

    private fun extractNumber(str: String): Int =
        "[0-9]".toRegex().findAll(str)
            .map { it.value }
            .let {
                "${it.first()}${it.last()}".toInt()
            }
}

fun main(args: Array<String>) {
    val questionOne = QuestionOne()

    val test = questionOne(File(args[0]).readLines())
    println("Test: $test")

    val final = questionOne(File(args[1]).readLines())
    println("Final: $final")
}
