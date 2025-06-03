package com.example.tdahelper

import com.example.tdahelper.model.FileD
import com.example.tdahelper.model.FileRow
import com.example.tdahelper.model.Logger
import java.io.File

val logger = object : Logger {
    override fun print(value: String) {
        println(value)
    }
}

fun FileD.new(
    name: String,
    newRows: List<FileRow>
): FileD {
    return object: FileD {
        override val name: String
            get() = name
        override val rows: List<FileRow>
            get() = newRows
    }
}

fun FileD.pair(boolean: Boolean): Pair<FileD, Boolean> {
    return Pair(this, boolean)
}

fun List<String>.println() {
    this.forEach {
        println(it)
    }
}


fun listAllFilesWithKotlinWalk(rootDir: String): List<File> {
    return File(rootDir)
        .walkTopDown()
        .filter { it.isFile }
        .filter { it.extension == "pdf" }
        .toList()
}