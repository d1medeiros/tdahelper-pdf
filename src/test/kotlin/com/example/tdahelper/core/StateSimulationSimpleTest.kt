package com.example.tdahelper.core

import com.example.tdahelper.core.DurationType.DAY
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StateSimulationSimpleTest {

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
            override fun maxAge(): Int = Duration(7, DAY).parse()
        }
    }

    @Test
    fun `feeding monster after 4 hours without eat`() {
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.next(exoMonster)   //1h
        result = timeCapsule.next(result)//2h
        result = timeCapsule.next(result)//3h
        result = timeCapsule.next(
            result,
            listOf(GoEat(idle))
        )//4h
        result = timeCapsule.next(result)//5h
        assertTrue { result.state is Idle }
        assertEquals(1, result.withOutEat.get())
    }

    @Test
    fun `monster hungry after 7 hours without eat`() {
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.next(exoMonster)   //1h
        result = timeCapsule.next(result)//2h
        result = timeCapsule.next(result)//3h
        result = timeCapsule.next(result)//4h
        result = timeCapsule.next(result)//5h
        result = timeCapsule.next(result)//6h
        result = timeCapsule.next(result)//7h
        assertTrue { result.state is Hungry }
        assertEquals(7, result.withOutEat.get())
    }

    @Test
    fun `monster hungry after 3 days`() {
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.nextDay(exoMonster)   //1D
        result = timeCapsule.nextDay(result)//2D
        result = timeCapsule.next(result)//1h
        assertTrue { result.state is Starving }
        assertEquals(25, result.withOutEat.get())
    }

    @Test
    fun `monster hungry after 4 days and 1 hour`() {
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.nextDay(exoMonster)   //1D
        result = timeCapsule.nextDay(result)//2D
        result = timeCapsule.nextDay(result)//3D
        result = timeCapsule.next(result)//1h
        assertTrue { result.state is Dead }
    }

    @Test
    fun `monster sleep after 11 hours`() {
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.next(exoMonster)   //1h
        result = timeCapsule.next(result)//2h
        result = timeCapsule.next(result)//3h
        result = timeCapsule.next(
            result,
            listOf(GoEat(idle))
        )//4h
        result = timeCapsule.next(result)//5h
        result = timeCapsule.next(result)//6h
        result = timeCapsule.next(result)//7h
        result = timeCapsule.next(
            result,
            listOf(GoEat(idle))
        )//8h
        result = timeCapsule.next(result)//9h
        result = timeCapsule.next(result)//10h
        result = timeCapsule.next(result)//11h
        assertTrue { result.state is Sleeping }
        assertEquals(0, result.awake.get())
    }
}