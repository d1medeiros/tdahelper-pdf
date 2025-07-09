package com.example.tdahelper.core.actions.goes

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.logTime
import com.example.tdahelper.core.states.Idle

class GoEat(val state: Idle) : Action {
    override fun execute(
        monster: Monster,
        days: Int,
        counter: Int
    ): Monster {
        monster.withOutEat.set(0)
        println("hummm matei a fome - ${logTime.invoke(counter, days)}")
       return monster.changeState(
           state,
           ""
       ) {}
    }
}