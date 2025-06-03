package com.example.tdahelper.actions

import com.example.tdahelper.*
import com.example.tdahelper.model.FileD
import com.example.tdahelper.model.FileRow

class FirstCategoryActionFile(
    chainActionFile: ChainActionFile? = null,
    val c: CategoryClassifier,
) : ChainActionFile(chainActionFile) {
    override fun apply(fileD: FileD, writer: Writer): Pair<FileD, Boolean> {
        return Pair(object: FileD {
            override val name: String
                get() = fileD.name
            override val rows: List<FileRow>
                get() = fileD.rows.map {
                    val winner = c.inputBias(it.description)
                    println("===========================")
                    println(it.description)
                    println("===========================")
                    c.println()
                    println("===========================")
                    it.copy(category = winner)
                }

        }, true)
    }

    override fun getLogger() = logger

    override fun getName() = "FIRST-CATEGORY"

}