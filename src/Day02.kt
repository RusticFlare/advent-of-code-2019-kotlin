package io.github.rusticflare.aoc

import io.github.rusticflare.aoc.intcode.IntcodeComputer
import io.github.rusticflare.aoc.intcode.asMemory
import io.github.rusticflare.aoc.intcode.execute

fun main() {

    fun part1(input: String, noun: Int? = null, verb: Int? = null): Int {
        val memory = input.split(',').map { it.toInt() }.asMemory()
            .run { write(address = 1u, value = noun ?: get(address = 1u)) }
            .run { write(address = 2u, value = verb ?: get(address = 2u)) }
        val intcodeComputer = IntcodeComputer(
            memory = memory,
            instructionPointer = 0u,
        )
        return execute(intcodeComputer)
    }

    fun part2(input: String): Int {
        (0..99).forEach { noun ->
            (0..99).forEach { verb ->
                if (part1(input, noun = noun, verb = verb) == 19690720) {
                    return 100 * noun + verb
                }
            }
        }
        error("Result not found")
    }

    val testInput = readInputText("Day02Test")
    check(part1(testInput) == 3500)

    val input = readInputText("Day02")
    println(part1(input, noun = 12, verb = 2)) // 4462686
    println(part2(input))
}
