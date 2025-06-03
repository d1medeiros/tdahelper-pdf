package com.example.tdahelper.actions

import com.example.tdahelper.model.FileD
import com.example.tdahelper.Writer


abstract class ChainActionFile(
    val next: ChainActionFile? = null,
) : Action {
    abstract fun apply(fileD: FileD, writer: Writer): Pair<FileD, Boolean>
    fun execute(fileD: FileD, writer: Writer): FileD {
        getLogger().print("ACTION[${getName()}] -> processing file ${fileD.name}")
        val result = this.apply(fileD, writer)
        getLogger().print("ACTION[${getName()}] -> applied is ${result.second}")
        if (next == null) {
            getLogger().print("ACTION[${getName()}] -> no next action chain")
        }
        return when {
            result.second && next != null -> next.execute(result.first, writer)
            else -> result.first
        }
    }
}