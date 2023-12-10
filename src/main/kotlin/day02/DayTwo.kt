package day02

abstract class DayTwo {
    protected fun extractGame(str: String): Game {
        val start = str.indexOf(":") + 1
        val number = "[0-9]+".toRegex().find(str)?.value ?: "0"
        var gameColors = GameColors(red = 0, green = 0, blue = 0)

        var value = ""
        var color = ""
        for (i in start ..< str.length) {
            when {
                str[i] == ';' || str[i] == ',' -> {
                    gameColors = calcMaxColor(value.toInt(), color, gameColors)
                    value = ""
                    color = ""
                }
                str[i].isDigit() ->
                    value += str[i].toString()
                else -> {
                    color += str[i].toString()
                }
            }
        }
        gameColors = calcMaxColor(value.toInt(), color, gameColors)

        return Game(
            number = number,
            gameColors = gameColors
        )
    }

    private fun calcMaxColor(value: Int, color: String, gameColors: GameColors): GameColors {
        when(color.trim()) {
            "red" ->
                if (value > gameColors.red) {
                    return gameColors.copy(red = value)
                }
            "green" ->
                if (value > gameColors.green) {
                    return gameColors.copy(green = value)
                }
            "blue" ->
                if (value > gameColors.blue) {
                    return gameColors.copy(blue = value)
                }
        }
        return gameColors
    }
}
