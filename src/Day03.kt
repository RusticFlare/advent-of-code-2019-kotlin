package io.github.rusticflare.aoc

import io.github.rusticflare.aoc.intcode.IntcodeComputer
import io.github.rusticflare.aoc.intcode.asMemory
import io.github.rusticflare.aoc.intcode.execute
import kotlin.math.absoluteValue

fun main() {

    fun String.toDirection(): Direction = when(first()){
        'U' -> Direction.Up(drop(1).toInt())
        'D' -> Direction.Down(drop(1).toInt())
        'L' -> Direction.Left(drop(1).toInt())
        'R' -> Direction.Right(drop(1).toInt())
        else -> error("No direction for $this")
    }

    fun String.points(): Set<Pair<Int,Int>> = split(',').asSequence()
        .map {it.toDirection()}
        .fold(initial = listOf(0 to 0)) { acc, direction -> acc + direction.points(acc.last()) }
        .drop(1)
        .toSet()

    fun part1(input: String): Int = input.lines()
        .flatMap { it.points() }
        .groupingBy { it }
        .eachCount()
        .filter { (_, count) -> count > 1 }
        .minOf { (point) -> point.first.absoluteValue + point.second.absoluteValue }

    fun part2(input: String): Int {
        return 0
    }

//    val testInput = readInputText("Day02Test")
//    check(part1(testInput) == 3500)

    val input = readInputText("Day03")
    println(part1(input))
    println(part2(input))
}

private sealed class Direction(protected val distance: Int) {

    abstract fun points(start: Pair<Int,Int>):List<Pair<Int,Int>>

    class Up(distance: Int) : Direction(distance) {

        override fun points(start: Pair<Int, Int>) =
            generateSequence(start.copy(second = start.second + 1)) { it.copy(second = it.second + 1)}
                .take(distance)
                .toList()
    }

    class Down(distance: Int) : Direction(distance) {

        override fun points(start: Pair<Int, Int>) =
            generateSequence(start.copy(second = start.second - 1)) { it.copy(second = it.second - 1)}
                .take(distance)
                .toList()
    }

    class Left(distance: Int) : Direction(distance) {

        override fun points(start: Pair<Int, Int>) =
            generateSequence(start.copy(first = start.first - 1)) { it.copy(first = it.first - 1)}
                .take(distance)
                .toList()
    }

    class Right(distance: Int) : Direction(distance) {

        override fun points(start: Pair<Int, Int>) =
            generateSequence(start.copy(first = start.first + 1)) { it.copy(first = it.first + 1)}
                .take(distance)
                .toList()
    }
}
