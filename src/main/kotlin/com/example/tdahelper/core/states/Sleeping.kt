package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster
import java.util.concurrent.atomic.AtomicInteger


open class Sleeping() : State() {
    override val duration: Int = 2
    override val timeTo = 10

    override fun awake(): (AtomicInteger) -> Unit =
        { awake: AtomicInteger -> awake.set(0) }

    override fun sleep(): (AtomicInteger) -> Unit =
        { sleep: AtomicInteger -> sleep.andIncrement }

    override fun hungry(): (AtomicInteger) -> Unit =
        { hungry: AtomicInteger -> hungry.andIncrement }

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
        return basicVerify(next)

    }
}

