package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster


class Hungry() : State() {
    val timeToBeHungry = 6

    override fun time() {
        monster.awake.incrementAndGet()
        monster.withOutEat.incrementAndGet()
        monster.sleep.incrementAndGet()
    }

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
       return basicVerify(next)
               && (next is Dead || monster.withOutEat.get() < timeToBeHungry)
    }
}