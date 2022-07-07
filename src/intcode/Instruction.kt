package io.github.rusticflare.aoc.intcode

import arrow.core.Either
import arrow.core.left
import arrow.core.right

sealed class Instruction(val parameterCount: UInt) {

    protected val IntcodeComputer.nextInstructionPointer
        get() = instructionPointer + 1u + parameterCount

    context(IntcodeComputer)
    abstract fun execute(): Either<HaltedIntcodeComputer, IntcodeComputer>

    sealed class NoParameters : Instruction(parameterCount = 0u)

    object Halt : NoParameters() {

        context(IntcodeComputer)
        override fun execute() = HaltedIntcodeComputer(
            memory = memory,
            instructionPointer = nextInstructionPointer
        ).left()
    }

    sealed class ThreeParameters : Instruction(parameterCount = 3u) {

        context(IntcodeComputer)
        final override fun execute(): Either<Nothing, IntcodeComputer> {
            val (a, b, c) = getParameters()
            return IntcodeComputer(
                memory = with(memory) { execute(a, b, c,) },
                instructionPointer = nextInstructionPointer
            ).right()
        }

        context(Memory)
        abstract fun execute(a: Int, b: Int, c: Int): Memory
    }

    object Add : ThreeParameters() {

        context(Memory)
        override fun execute(a: Int, b: Int, c: Int) = write(
            address = c.toUInt(),
            value = get(address = a.toUInt()) + get(address = b.toUInt())
        )
    }

    object Multiply : ThreeParameters() {

        context(Memory)
        override fun execute(a: Int, b: Int, c: Int) = write(
            address = c.toUInt(),
            value = get(address = a.toUInt()) * get(address = b.toUInt())
        )
    }
}