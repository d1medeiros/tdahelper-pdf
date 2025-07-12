package com.example.tdahelper.core

import com.example.tdahelper.core.actions.goes.GoEat
import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.states.Hungry
import com.example.tdahelper.core.states.Idle
import com.example.tdahelper.core.states.Sleeping
import com.example.tdahelper.core.states.Starving
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class StateSimulationSimpleTest: StateMachineConfig() {

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
        assertEquals(1, result.hungry.get())
    }

    @Test
    fun `monster Hungry after 7 hours without eat`() {
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.next(exoMonster)   //1h
        result = timeCapsule.next(result)//2h
        result = timeCapsule.next(result)//3h
        result = timeCapsule.next(result)//4h
        result = timeCapsule.next(result)//5h
        result = timeCapsule.next(result)//6h
        result = timeCapsule.next(result)//7h
        assertTrue { result.state is Hungry }
        assertEquals(7, result.hungry.get())
    }

    @Test
    fun `monster Starving after 3 days`() {
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.nextDay(exoMonster)   //1D
        result = timeCapsule.nextDay(result)//2D
        assertTrue { result.state is Starving }
        assertEquals(24, result.hungry.get())
    }

    @Test
    fun `monster Sleeping after 11 hours`() {
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

    @Test
    fun `monster wake up after 13 hours sleeping_`() {
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
        result = timeCapsule.next(result)//12h
        result = timeCapsule.next(result)//13h
        assertTrue { result.state is Idle }
        assertEquals(0, result.sleep.get())
    }

    @Test
    @Disabled
    fun `a a`() {
        exoMonster = object : Monster(idle) {
            override fun maxAge(): Int = Duration(7, DurationType.DAY).parse()
        }
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.next(exoMonster)   //1h

    }
}