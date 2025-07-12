package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster
import java.util.concurrent.atomic.AtomicInteger


class Hungry() : State() {
    override val timeTo = 6

    override fun awake(): (AtomicInteger) -> Unit =
        { awake: AtomicInteger -> awake.andIncrement }

    override fun sleep(): (AtomicInteger) -> Unit =
        { sleep: AtomicInteger -> sleep.andIncrement }

    override fun hungry(): (AtomicInteger) -> Unit =
        { hungry: AtomicInteger -> hungry.andIncrement }

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
        return basicVerify(next)
                && (monster.hungry.get() < timeTo
                || next is Dead
                || next is Starving)
    }
}