package com.example.tdahelper.core.actions.shoulds

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.states.Dead
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.dayWithHours
import com.example.tdahelper.core.daysToWeeks
import com.example.tdahelper.core.hoursToDays
import com.example.tdahelper.core.logTime
import com.example.tdahelper.core.weeksToMonths

class ShouldDie(val state: Dead) : Action {
    override fun execute(
        monster: Monster,
        days: Int,
        counter: Int
    ): Monster {
        val withOutEat = monster.withOutEat.get()
        return when {
            dayWithHours.invoke(counter, days) > monster.maxAge()
                -> monster.changeState(state, "morreu de velhice - ${logTime.invoke(counter, days)}") { m->
                    m.clean()
            }
            withOutEat.hoursToDays() > 2
                -> monster.changeState(state, "morreu de fome - ${logTime.invoke(counter, days)}") { m->
                m.clean()
            }
            else -> monster
        }
    }

    override val order: Int
        get() = 1
}