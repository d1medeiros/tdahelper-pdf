package com.example.tdahelper.core.states

import com.example.tdahelper.core.creatures.Monster


class Dead() : State() {
    override fun time() {
        monster.clean()
    }

    override fun shouldChange(
        monster: Monster,
        next: State
    ): Boolean {
        return basicVerify(next)
    }
}