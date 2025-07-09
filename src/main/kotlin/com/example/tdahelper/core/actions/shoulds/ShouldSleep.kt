package com.example.tdahelper.core.actions.shoulds

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.logTime
import com.example.tdahelper.core.states.Sleeping

class ShouldSleep(val state: Sleeping) : Action {
    override fun execute(
        monster: Monster,
        days: Int,
        counter: Int
    ): Monster {
        val get = monster.awake.get()
        val message = "soninho.. - ${logTime.invoke(counter, days)}"
        return when {
            get > state.timeTo -> monster.changeState(state, message) { monster ->
                    monster.awake.set(0)
            }
            else -> monster
        }
    }

}