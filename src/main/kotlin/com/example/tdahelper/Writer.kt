package com.example.tdahelper

import com.github.doyaaaaaken.kotlincsv.client.CsvWriter
import com.github.doyaaaaaken.kotlincsv.dsl.context.WriteQuoteMode
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

class Writer(
    val path: String,
) {
    val writer: CsvWriter = csvWriter {
        charset = "ISO_8859_1"
        delimiter = ','
        nullCode = ""
        lineTerminator = "\n"
        outputLastLineTerminator = true
        quote {
            mode = WriteQuoteMode.ALL
            char = '\''
        }
    }

    fun writeAll(rows: List<List<String>>, file: String) {
        val targetFileName = "$path/$file"
        writer.writeAll(rows, targetFileName)
    }
}