package io.github.rusticflare.aoc.intcode

@JvmInline
value class Memory(private val memory: List<Int>) {

    operator fun get(address: UInt) = memory[address.toInt()]

    fun write(address: UInt, value: Int) = memory.toMutableList().apply {
        set(index = address.toInt(), element = value)
    }.asMemory()
}

fun List<Int>.asMemory() = Memory(this)
