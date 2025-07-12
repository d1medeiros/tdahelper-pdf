package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster
import java.util.concurrent.atomic.AtomicInteger

class Grumpy() : State() {

    override fun awake(): (AtomicInteger) -> Unit =
        { awake: AtomicInteger -> awake.andIncrement }

    override fun sleep(): (AtomicInteger) -> Unit =
        { sleep: AtomicInteger -> sleep.andIncrement }

    override fun hungry(): (AtomicInteger) -> Unit =
        { hungry: AtomicInteger -> hungry.updateAndGet { i -> i + 2}}

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
        return basicVerify(next)
    }
}