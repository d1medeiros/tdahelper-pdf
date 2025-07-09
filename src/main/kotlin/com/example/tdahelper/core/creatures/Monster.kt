package com.example.tdahelper.core.creatures

import com.example.tdahelper.core.states.State
import java.util.concurrent.atomic.AtomicInteger

abstract class Monster(firstState: State): Creature {
    var state: State = firstState.let {
        it.monster = this
        it
    }
    val awake: AtomicInteger = AtomicInteger(0)
    val sleep: AtomicInteger = AtomicInteger(0)
    val withOutEat: AtomicInteger = AtomicInteger(0)

    fun changeState(state: State, message: String, function: (Monster) -> Unit): Monster {
        return this.apply {
            this.state = this.state.change(
                this,
                state,
                message,
                function,
            )
        }
    }

    fun run(days: Int, counter: Int) = state.run(days, counter)

    fun clean(){
        awake.set(0)
        sleep.set(0)
        withOutEat.set(0)
    }

    abstract fun maxAge(): Int
}
