package com.example.tdahelper.core.foods

import com.example.tdahelper.core.creatures.Monster

interface Food {
    var quality: Int
    fun run(monster: Monster) {
        monster.apply {
            hungry.updateAndGet { it - quality}
        }
    }
}


