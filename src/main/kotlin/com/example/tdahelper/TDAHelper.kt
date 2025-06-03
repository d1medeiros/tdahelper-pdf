package com.example.tdahelper

import com.example.tdahelper.actions.ChainActionFile
import com.example.tdahelper.model.BillFileD
import com.example.tdahelper.model.FileD
import org.apache.pdfbox.pdmodel.PDDocument
import technology.tabula.ObjectExtractor
import technology.tabula.Page
import technology.tabula.RectangularTextContainer
import technology.tabula.extractors.BasicExtractionAlgorithm
import java.io.File
import java.io.InputStream

class TDAHelper(
    private val chainAction: ChainActionFile,
    private val writer: Writer,
) {
    private lateinit var fileD: FileD
    private var rowsString: MutableList<String> = mutableListOf()
    private lateinit var name: String

    fun input(file: File) {
        val inputStream: InputStream = file.inputStream()
        rowsString += simpleExtract(inputStream)
        name = file.name
    }

    fun process()  {
        fileD = chainAction.execute(
            BillFileD(
                name = this.name,
                rowStrings = this.rowsString
            ),
            writer
        )
    }

    fun write(name: String) {
        val rows = fileD.rows.map {
            listOf(it.date, it.description, it.amount, it.category)
        }
        writer.writeAll(rows, name)
    }

    private fun simpleExtract(inputStream: InputStream): MutableList<String> {
        PDDocument.load(inputStream).use { document ->
            val page = ObjectExtractor(document).extract()
            var count = 1
            val mutableList: MutableList<String> = mutableListOf()
            while (page.hasNext()) {
                val page: Page = page.next()
                val bea = BasicExtractionAlgorithm(
                    page.verticalRulings
                )
                val table = bea.extract(page)[0]
                val m = table.rows.flatMap{ a->
                    a.mapNotNull { b: RectangularTextContainer<*> ->
                        extractBills(b)
                    }
                }
                mutableList += m
                println("page ${count++}")
                m.println()
            }
            println(" ")
            return mutableList
        }
    }

    private fun extractBills(row: RectangularTextContainer<*>): String? {
        val regex = Regex("""^(\d{2}\/\d{2}\s+.+?(?:\d{2}\/\d{2}\s+)?\d+,\d{2})""")
        val result = regex.find(row.text)?.groupValues?.get(1)
        return result
    }

}

