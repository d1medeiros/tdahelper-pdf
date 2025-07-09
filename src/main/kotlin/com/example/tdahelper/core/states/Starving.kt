package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster
import java.util.concurrent.atomic.AtomicInteger


class Starving() : State() {
    override val timeTo = 24

    override fun awake(): (AtomicInteger) -> Unit =
        { awake: AtomicInteger -> awake.andIncrement }

    override fun sleep(): (AtomicInteger) -> Unit =
        { sleep: AtomicInteger -> sleep.andIncrement }

    override fun withOutEat(): (AtomicInteger) -> Unit =
        { withOutEat: AtomicInteger -> withOutEat.updateAndGet { i -> i + 3}}

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
       return basicVerify(next)
               && (monster.withOutEat.get() < timeTo || next is Dead)
    }
}