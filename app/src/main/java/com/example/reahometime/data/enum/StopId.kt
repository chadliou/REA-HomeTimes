package com.example.reahometime.data.enum

enum class StopId(val value: Int) {

    NORTH(4055),
    SOUTH(4155);

    companion object {
        fun valueof(value: Int) = StopId.values().find { it.value == value }
    }
}