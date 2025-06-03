package com.example.tdahelper

import com.example.tdahelper.model.Category

class CategoryClassifier(
    val bias: Map<Category, List<String>>
) {
    private var map: MutableMap<Category, Int> = mutableMapOf()

    fun inputBias(text: String): String {
       map = (Category.entries
            .associateWith { 0 } + mapOf(Category.OUTROS to 1)).toMutableMap()
        for (mutableEntry in map) {
            bias[mutableEntry.key]?.forEach { b->
                if (text.contains(b, true)) {
                    map.compute(mutableEntry.key){ _, p-> (p?:0)+10 }
                }
            }
        }
       return resolveWinner(map).name
    }

    private fun resolveWinner(map: MutableMap<Category, Int>): Category {
        return map.maxBy { it.value }.key
    }

    fun println() {
        this.map.forEach { (t, u) -> println("${t.name} --> $u")}
    }

}