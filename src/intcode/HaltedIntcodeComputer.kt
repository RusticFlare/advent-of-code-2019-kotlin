package io.github.rusticflare.aoc.intcode

data class HaltedIntcodeComputer(val memory: Memory, val instructionPointer: UInt)

val HaltedIntcodeComputer.result
    get() = memory[0u]
