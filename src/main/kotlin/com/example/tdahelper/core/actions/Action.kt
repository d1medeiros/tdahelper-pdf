package com.example.tdahelper.core.actions

import com.example.tdahelper.core.creatures.Monster

interface Action{
    val order: Int
        get() = Int.MAX_VALUE

    fun execute(monster: Monster, days: Int, counter: Int): Monster
}