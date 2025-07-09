package com.example.tdahelper.core.actions.shoulds

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.logTime
import com.example.tdahelper.core.states.Hungry

class ShouldHungry(val state: Hungry) : Action {
    override fun execute(
        monster: Monster,
        days: Int,
        counter: Int
    ): Monster {
        val get = monster.withOutEat.get()
        return when {
            get >= state.timeToBeHungry-> monster.changeState(
                state,
                "to com fome - ${logTime.invoke(counter, days)}"
            ){}

            else -> monster
        }
    }
}