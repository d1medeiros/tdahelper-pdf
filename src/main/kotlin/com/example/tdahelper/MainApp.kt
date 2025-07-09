package com.example.tdahelper

import com.example.tdahelper.core.Duration
import com.example.tdahelper.core.DurationType.DAY
import com.example.tdahelper.core.DurationType.HOUR
import com.example.tdahelper.core.StateManager
import com.example.tdahelper.core.TimeCapsule
import com.example.tdahelper.core.actions.goes.GoEat
import com.example.tdahelper.core.actions.shoulds.ShouldDie
import com.example.tdahelper.core.actions.shoulds.ShouldHungry
import com.example.tdahelper.core.actions.shoulds.ShouldSleep
import com.example.tdahelper.core.actions.shoulds.ShouldStarving
import com.example.tdahelper.core.actions.shoulds.ShouldWakeUp
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.states.Dead
import com.example.tdahelper.core.states.Grumpy
import com.example.tdahelper.core.states.Hungry
import com.example.tdahelper.core.states.Idle
import com.example.tdahelper.core.states.Sleeping
import com.example.tdahelper.core.states.Starving
import com.example.tdahelper.core.states.State


fun main() {
    //STATES
    val sleeping = Sleeping()
    val idle = Idle()
    val dead = Dead()
    val hungry = Hungry()
    val grumpy = Grumpy()
    val starving = Starving()
    sleeping.possibles = arrayOf(idle, dead, hungry)
    idle.possibles = arrayOf(sleeping, dead, hungry)
    dead.possibles = emptyArray<State>()
    hungry.possibles = arrayOf(idle, dead)
    grumpy.possibles = arrayOf(sleeping, idle, dead, hungry)
    starving.possibles = arrayOf(idle, dead)

    //ACTIONS
    val shouldSleep = ShouldSleep(sleeping)
    val shouldWakeUp = ShouldWakeUp(idle)
    val shouldDie = ShouldDie(dead)
    val shouldHungry = ShouldHungry(hungry)
    val shouldStarving = ShouldStarving(starving)


    val exoMonster = object : Monster(idle) {
        override fun maxAge(): Int = Duration(7, DAY).parse()
    }

    val timeCapsule = TimeCapsule(
        stateManager = StateManager(
            list = listOf(
                shouldSleep,
                shouldWakeUp,
                shouldDie,
                shouldHungry,
                shouldStarving,
            )
        )
    )

    timeCapsule.nextWeek(
        exoMonster,
        mapOf(
            Duration(
                11,
                HOUR
            ).parse() to listOf(GoEat(idle)),
            Duration(
                1,
                DAY,
                Duration(8, HOUR)
            ).parse() to listOf(GoEat(idle)),
            Duration(
                2,
                DAY,
                Duration(8, HOUR)
            ).parse() to listOf(GoEat(idle)),
            Duration(
                3,
                DAY,
                Duration(8, HOUR)
            ).parse() to listOf(GoEat(idle)),
            Duration(
                4,
                DAY,
                Duration(8, HOUR)
            ).parse() to listOf(GoEat(idle)),
        )
    )
}

