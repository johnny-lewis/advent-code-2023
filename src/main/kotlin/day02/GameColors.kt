package day02

data class GameColors(
    val red: Int,
    val green: Int,
    val blue: Int
) {
    fun cube(): Int =
        red * green * blue
}
