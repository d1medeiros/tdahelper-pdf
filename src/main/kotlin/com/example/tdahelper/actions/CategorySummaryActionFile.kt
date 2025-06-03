package com.example.tdahelper.actions

import com.example.tdahelper.Writer
import com.example.tdahelper.logger
import com.example.tdahelper.model.FileD
import com.example.tdahelper.pair
import java.math.RoundingMode

class CategorySummaryActionFile(
    chainActionFile: ChainActionFile? = null,
): ChainActionFile(chainActionFile) {
    override fun apply(fileD: FileD, writer: Writer): Pair<FileD, Boolean> {
        writer.writeAll(
            rows = fileD.rows.groupBy({k-> k.category}, {v-> v.amount})
                .map { e->
                    Pair(e.key, e.value.sumOf { it.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN) })
                }.sortedByDescending { it.second }
                .let { pairList ->
                    pairList + listOf(Pair("total", "${pairList.sumOf { it.second }}"))
                }
                .map {
                    listOf(it.first, "${it.second}")
                },
            file = "summary.csv"
        )
        return fileD.pair(true)
    }

    override fun getLogger() = logger

    override fun getName() = "CATEGORY-SUMMARY"
}