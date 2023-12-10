package day02

import java.io.File
import java.util.Locale

class QuestionTwo : DayTwo() {
    operator fun invoke(strings: List<String>): Int =
        strings
            .sumOf {
                extractGame(it.lowercase(Locale.getDefault())).gameColors.cube()
            }
}

fun main(args: Array<String>) {
    val question = QuestionTwo()

    val test = question(
        strings = File(args[0]).readLines()
    )
    println("Test: $test")

    val final = question(
        strings = File(args[1]).readLines()
    )
    println("Final: $final")
}
