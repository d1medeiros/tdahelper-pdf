package com.example.tdahelper.core

import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.states.Dead
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StateSimulationDeadReasonsTest: StateMachineConfig() {


    @Test
    fun `monster hungry after 4 days and 1 hour and is Dead`() {
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.nextDay(exoMonster)   //1D
        result = timeCapsule.nextDay(result)//2D
        result = timeCapsule.nextDay(result)//3D
        result = timeCapsule.next(result)//1h
        assertTrue { result.state is Dead }
    }

    @Test
    fun `monster Dead after 5 hours as max age`() {
        exoMonster = object : Monster(idle) {
            override fun maxAge(): Int = Duration(4, DurationType.HOUR).parse()
        }
        val timeCapsule = TimeCapsule(stateManager)
        var result = timeCapsule.next(exoMonster)   //1h
        result = timeCapsule.next(result)//2h
        result = timeCapsule.next(result)//3h
        result = timeCapsule.next(result)//4h
        result = timeCapsule.next(result)//5h
        assertTrue { result.state is Dead }
    }


}