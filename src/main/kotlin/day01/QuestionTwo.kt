package day01

import java.io.File
import java.util.Locale

class QuestionTwo {
    private val numbers = listOf(
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    )

    operator fun invoke(strings: List<String>): Int =
        strings.sumOf {
            extractNumbers(it.lowercase(Locale.getDefault()))
        }

    private fun extractNumbers(str: String): Int {
        var first = 0
        for (i in str.indices) {
            first = findValue(false, i, str)
            if (first > 0) {
                break
            }
        }

        var last = 0
        for (i in str.length - 1 downTo 0) {
            last = findValue(true, i, str)
            if (last > 0) {
                break
            }
        }

        return "$first$last".toInt()
    }

    private fun findValue(isReversed: Boolean, index: Int, str: String): Int {
        if (str[index].isDigit()) {
            return str[index].toString().toInt()
        }
        for (numIndex in numbers.indices) {
            if (findCondition(isReversed, str, index, numbers[numIndex])) {
                return numIndex + 1
            }
        }
        return 0
    }

    private fun findCondition(isReversed: Boolean, str: String, strIndex: Int, number: String): Boolean =
        if (isReversed) {
            strIndex - number.length >= 0 && str.substring(strIndex - number.length + 1, strIndex + 1) == number
        } else {
            strIndex + number.length <= str.length && str.substring(strIndex, strIndex + number.length) == number
        }
}

fun main(args: Array<String>) {
    val question = QuestionTwo()

    val test = question(File(args[0]).readLines())
    println("Test: $test")

    val final = question(File(args[1]).readLines())
    println("Final: $final")
}
