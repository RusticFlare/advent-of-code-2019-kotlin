package io.github.rusticflare.aoc.intcode

@JvmInline
value class Opcode(private val opcode: Int) {

    val instruction: Instruction
        get() = when(opcode) {
            1 -> Instruction.Add
            2 -> Instruction.Multiply
            99 -> Instruction.Halt
            else -> error("Invalid opcode: $opcode")
        }
}

fun Int.asOpcode() = Opcode(this)
