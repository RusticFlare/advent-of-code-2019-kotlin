package io.github.rusticflare.aoc.intcode

data class IntcodeComputer(val memory: Memory, val instructionPointer: UInt) {

    private val instruction = memory[instructionPointer].asOpcode().instruction

    fun executeInstruction() = instruction.execute()
}

val execute = DeepRecursiveFunction<IntcodeComputer, _> { intcodeComputer ->
    intcodeComputer.executeInstruction().fold(
        ifLeft = { it.result },
        ifRight = { callRecursive(it) }
    )
}

context(Instruction)
fun IntcodeComputer.getParameters(): List<Int> =
    generateSequence(seed = instructionPointer + 1u) { it + 1u }
        .map { memory[it] }
        .take(parameterCount.toInt())
        .toList()
