package day02

import java.io.File
import java.util.Locale

class QuestionOne : DayTwo() {
    operator fun invoke(strings: List<String>, limit: GameColors): Int =
        strings
            .map {
                extractGame(it.lowercase(Locale.getDefault()))
            }
            .filter { isWithinLimit(it.gameColors, limit) }
            .sumOf { it.number.toInt() }

    private fun isWithinLimit(gameMax: GameColors, limit: GameColors): Boolean =
        gameMax.red <= limit.red && gameMax.blue <= limit.blue && gameMax.green <= limit.green
}

fun main(args: Array<String>) {
    val question = QuestionOne()

    val test = question(
        strings = File(args[0]).readLines(),
        limit = GameColors(red = 12, green = 13, blue = 14)
    )
    println("Test: $test")

    val final = question(
        strings = File(args[1]).readLines(),
        limit = GameColors(red = 12, green = 13, blue = 14)
    )
    println("Final: $final")
}
