package com.example.tdahelper.core.actions.goes

import com.example.tdahelper.core.actions.Action
import com.example.tdahelper.core.foods.Food
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.logTime
import com.example.tdahelper.core.states.Idle

class GoEat(val state: Idle, val food: Food) : Action {
    override fun execute(
        monster: Monster,
        days: Int,
        counter: Int
    ): Monster {
        monster.feed(food)
        println("hummm matei a fome - ${logTime.invoke(counter, days)}")
       return monster.changeState(
           state,
           ""
       ) {}
    }
}
