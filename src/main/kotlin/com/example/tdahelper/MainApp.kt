package com.example.tdahelper

import com.example.tdahelper.actions.CategorySummaryActionFile
import com.example.tdahelper.actions.DebugChainActionFile
import com.example.tdahelper.actions.FirstCategoryActionFile
import com.example.tdahelper.actions.FormatAmountActionFile
import com.example.tdahelper.model.Category
import java.io.File


fun main(args: Array<String>) {
    val path = "/home/aianwhat/googledrive-remote/gastos/2025_maio"
    val classifier = CategoryClassifier(
        bias = mapOf(
            Category.PET to listOf("pet", "veterinario", "ANIMAL"),
            Category.IFOOD to listOf("ifood", "ifd"),
            Category.FARMACIA to listOf("farmacia", "drogaria", "RDSAUDE"),
            Category.STREAM to listOf("apple", "NETFLIX", "disney", "MICROSOFT", "AWS", "SPOTIFY", "sonyplaystatn", "amazon", "Prime"),
            Category.UBER to listOf("uber", "trip"),
            Category.VESTUARIO to listOf("renner", "foxton"),
            Category.ACADEMIA to listOf("smartfit", "smart", "totalpass"),
            Category.RESTAURANTE to listOf("restaurante"),
            Category.AUTOMOVEL to listOf("POSTO"),
            Category.VIAGEM to listOf("AZUL", "linhas"),
        )
    )
    val debugChainActionFile = DebugChainActionFile()
    val categorySummaryActionFile = CategorySummaryActionFile(debugChainActionFile)
    val formatAmountActionFile = FormatAmountActionFile(categorySummaryActionFile)
    val firstCategoryActionFile = FirstCategoryActionFile(formatAmountActionFile, classifier)
    val tdaHelper = TDAHelper(firstCategoryActionFile, Writer(path))
//    args.firstOrNull()?.let { path ->
        listAllFilesWithKotlinWalk(path)
            .forEach { file ->
            val fileRead = file.absolutePath
            tdaHelper.input(File(fileRead))
        }
//    }
    tdaHelper.process()
    tdaHelper.write("output.csv")
}
