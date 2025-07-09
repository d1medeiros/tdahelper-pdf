package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster


class Sleeping() : State() {
    override val duration: Int = 2
    val timeToBeSleepy = 10

    override fun time() {
        monster.sleep.incrementAndGet()
        monster.withOutEat.incrementAndGet()
        monster.awake.set(0)
    }

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
        return basicVerify(next)

    }
}