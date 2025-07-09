package com.example.tdahelper.core.actions.shoulds

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.logTime
import com.example.tdahelper.core.states.Hungry
import com.example.tdahelper.core.states.Starving

class ShouldStarving(val state: Starving) : Action {
    override fun execute(
        monster: Monster,
        days: Int,
        counter: Int
    ): Monster {
        val get = monster.withOutEat.get()
        return when {
            get >= state.timeToBeStarving
                -> monster.changeState(state, "essa fome é de matar - ${logTime.invoke(counter, days)}"){}
            else -> monster
        }
    }
}