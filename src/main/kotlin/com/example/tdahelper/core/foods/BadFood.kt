package com.example.tdahelper.core.foods

import com.example.tdahelper.core.creatures.Monster

abstract class BadFood() : Food {
    override var quality: Int = 1
    override fun run(monster: Monster) {
        monster.apply {
            hungry.updateAndGet { it - quality}
            sleep.incrementAndGet()
        }
    }
}