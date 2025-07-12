package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster
import java.util.concurrent.atomic.AtomicInteger


class Dead() : State() {

    override fun awake() =  {awake: AtomicInteger-> awake.set(0)}
    override fun sleep() =  {sleep: AtomicInteger-> sleep.set(0)}
    override fun hungry() =  { hungry: AtomicInteger-> hungry.set(0)}

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
        return basicVerify(next)
    }
}