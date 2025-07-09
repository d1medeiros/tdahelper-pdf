package com.example.tdahelper.core.actions.shoulds

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.states.Idle
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.logTime
import com.example.tdahelper.core.states.Sleeping

class ShouldWakeUp(val state: Idle) : Action {
    override fun execute(
        monster: Monster,
        days: Int,
        counter: Int
    ): Monster {
        val mstate = monster.state
        val message = "acordei - ${logTime.invoke(counter, days)}"
        return when {
            mstate is Sleeping
                    && mstate.timeTracking.get() >= mstate.duration
                -> monster.changeState(state, message) { monster ->
                    monster.sleep.set(0)
            }
            else -> monster
        }
    }
}