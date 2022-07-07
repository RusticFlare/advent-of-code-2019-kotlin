import arrow.core.Some
import arrow.core.getOrElse
import arrow.core.traverse

fun main() {

    fun massOfFuel(mass: Int) = Some(mass / 3 - 2).filter { it > 0 }

    fun massOfFuelForFuel(mass: Int) : Int= massOfFuel(mass)
        .fold(ifEmpty = { 0 }, ifSome = { it + massOfFuelForFuel(it) })

    fun part1(input: List<String>) = input.sumOf { massOfFuel(it.toInt()).getOrElse { 0 } }

    fun part2(input: List<String>) = input.sumOf { massOfFuelForFuel(it.toInt()) }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
