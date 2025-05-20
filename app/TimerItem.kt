package com.example.recyclercountdownapp.model

data class TimerItem(
    val number: Int,
    var isDone: Boolean = false,
    var endTimeMillis: Long = System.currentTimeMillis() + number * 1000L
)
