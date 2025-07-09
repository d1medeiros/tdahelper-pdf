package com.example.tdahelper.core

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
import org.junit.jupiter.api.BeforeEach

open class StateMachineConfig {
    lateinit var stateManager: StateManager
    lateinit var shouldSleep: ShouldSleep
    lateinit var shouldWakeUp: ShouldWakeUp
    lateinit var shouldDie: ShouldDie
    lateinit var shouldHungry: ShouldHungry
    lateinit var shouldStarving: ShouldStarving
    lateinit var sleeping: Sleeping
    lateinit var idle: Idle
    lateinit var dead: Dead
    lateinit var hungry: Hungry
    lateinit var grumpy: Grumpy
    lateinit var starving: Starving
    lateinit var exoMonster: Monster

    @BeforeEach
    fun setUp() {
        sleeping = Sleeping()
        idle = Idle()
        dead = Dead()
        hungry = Hungry()
        grumpy = Grumpy()
        starving = Starving()

        sleeping.possibles = arrayOf(idle, dead, hungry)
        idle.possibles = arrayOf(sleeping, dead, hungry)
        dead.possibles = emptyArray<State>()
        hungry.possibles = arrayOf(idle, dead, starving)
        grumpy.possibles = arrayOf(sleeping, idle, dead, hungry, starving)
        starving.possibles = arrayOf(idle, dead)

        shouldSleep = ShouldSleep(sleeping)
        shouldWakeUp = ShouldWakeUp(idle)
        shouldDie = ShouldDie(dead)
        shouldHungry = ShouldHungry(hungry)
        shouldStarving = ShouldStarving(starving)

        stateManager = StateManager(
            list = listOf(
                shouldSleep,
                shouldWakeUp,
                shouldDie,
                shouldHungry,
                shouldStarving,
            )
        )
        exoMonster = object : Monster(idle) {
            override fun maxAge(): Int = Duration(7, DurationType.DAY).parse()
        }
    }
}