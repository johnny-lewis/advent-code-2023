package day03

import java.io.File

class QuestionTwo {
    operator fun invoke(strings: List<String>): Int =
        strings
            .flatMapIndexed { index: Int, str: String ->
                getGear(index, str, strings)
            }
            .sum()

    private fun getGear(lineIndex: Int, str: String, strings: List<String>): List<Int> =
        "[*]".toRegex().findAll(str)
            .map {
                getAdjacentNumbers(lineIndex, it.range.first, strings)
            }
            .filter { it.count() == 2 }
            .map { it.first() * it.last() }
            .toList()

    private fun getAdjacentNumbers(lineIndex: Int, charIndex: Int, strings: List<String>): List<Int> {
        val adjacent = mutableListOf<Int>()
        for (i in lineIndex - 1 .. lineIndex + 1) {
            if (lineIndex < 0 || lineIndex >= strings.count()) {
                continue
            }

            var j = charIndex - 1
            do {
                if (j < 0 || j >= strings[i].length) {
                    continue
                }
                if (strings[i][j].isDigit()) {
                    getNumberRange(strings[i], j).also { match ->
                        adjacent.add(match.number)
                        j = match.endIndex
                    }
                }
            } while (j++ < charIndex + 1)
        }

        return adjacent
    }

    private fun getNumberRange(str: String, index: Int): NumberMatch {
        var digits = str[index].toString()
        var end = index

        if (index != 0) {
            for (i in index - 1 downTo 0) {
                if (!str[i].isDigit()) {
                    break
                }
                digits = str[i].toString() + digits
            }
        }
        if (index != str.length - 1) {
            for (i in index + 1 ..< str.length) {
                if (!str[i].isDigit()) {
                    break
                }
                end += 1
                digits += str[i].toString()
            }
        }

        return NumberMatch(
            number = digits.toInt(),
            endIndex = end
        )
    }

    private data class NumberMatch(
        val number: Int,
        val endIndex: Int
    )
}

fun main(args: Array<String>) {
    val question = QuestionTwo()

    val test = question(
        File(args[0]).readLines()
    )
    println("Test: $test")

    val final = question(
        File(args[1]).readLines()
    )
    println("Final: $final")
}
