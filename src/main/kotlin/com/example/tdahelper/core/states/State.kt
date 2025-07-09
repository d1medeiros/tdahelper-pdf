package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster
import com.example.tdahelper.core.logTime
import java.util.concurrent.atomic.AtomicInteger

abstract class State {
    open val duration: Int = 1
    val timeTracking: AtomicInteger = AtomicInteger(0)
    lateinit var monster: Monster
    var possibles: Array<State> = emptyArray()

    abstract fun time()

    abstract fun shouldChange(monster: Monster, next: State): Boolean

    fun info(days: Int, counter: Int): Unit =
        println("[${javaClass.simpleName}] " +
                logTime.invoke(counter, days) +
            " - withOutEat:${monster.withOutEat.get()} " +
                "sleep:${monster.sleep.get()} " +
                "awake:${monster.awake.get()}"
        )

    fun change(
        monster: Monster,
        next: State,
        message: String,
        function: (Monster) -> Unit,
    ): State {
        if (shouldChange(monster, next)) {
            function.invoke(monster)
            timeTracking.set(0)
            println(message+" - withOutEat:${monster.withOutEat.get()} " +
                    "sleep:${monster.sleep.get()} " +
                    "awake:${monster.awake.get()}")
            return next.apply {
                this.monster = monster
            }
        }
        return this
    }

    fun basicVerify( next: State,): Boolean {
        return this.possibles.contains(next)
                && timeTracking.get() >= duration
                && next != this
    }

    fun run(days: Int, counter: Int) {
        time()
        timeTracking.incrementAndGet()
        info(days, counter)
    }

}

