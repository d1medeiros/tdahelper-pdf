package com.example.tdahelper.actions

import com.example.tdahelper.*
import com.example.tdahelper.model.FileD

class FormatAmountActionFile(
    chainActionFile: ChainActionFile? = null
) : ChainActionFile(chainActionFile) {
    override fun apply(fileD: FileD, writer: Writer): Pair<FileD, Boolean> {
        return fileD.new(
            name = fileD.name,
            newRows = fileD.rows.map {
                it.copy(
                    amount = it.amount.replace(",", ".")
                )
            }
        ).pair(true)
    }

    override fun getLogger() = logger

    override fun getName() = "FORMAT-AMOUNT"
}