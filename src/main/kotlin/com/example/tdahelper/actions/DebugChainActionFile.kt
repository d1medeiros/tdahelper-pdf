package com.example.tdahelper.actions

import com.example.tdahelper.model.FileD
import com.example.tdahelper.model.Logger
import com.example.tdahelper.Writer


class DebugChainActionFile(
    chainActionFile: ChainActionFile? = null
) : ChainActionFile(chainActionFile) {
    override fun apply(fileD: FileD, writer: Writer): Pair<FileD, Boolean> {
        fileD.rows.forEach { row ->
            getLogger().print("${row.date} | ${row.description} | ${row.amount} | ${row.category}")
        }
        return Pair(fileD, true)
    }

    override fun getLogger(): Logger {
        return object : Logger {
            override fun print(value: String) {
                println(value)
            }

        }
    }

    override fun getName(): String {
        return "DEBUGACTION"
    }

}

