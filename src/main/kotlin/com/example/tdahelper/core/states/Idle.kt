package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster

class Idle() : State() {
    override fun time() {
        monster.awake.incrementAndGet()
        monster.withOutEat.incrementAndGet()
        monster.sleep.set(0)
    }

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
        return basicVerify(next)
    }
}