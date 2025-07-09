package com.example.tdahelper.core

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.creatures.Monster

class StateManager(val list: List<Action>) {
    fun execute(monster: Monster, days: Int, counter: Int, extraAction: List<Action>? = null): Monster {
        monster.run(days, counter) //apply time passed on monster
        return (list + (extraAction?:emptyList())).sortedBy { it.order }.fold(monster){ acc, action ->
            action.execute(acc, days, counter)
        }
    }
}