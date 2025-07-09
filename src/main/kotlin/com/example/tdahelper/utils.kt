package com.example.tdahelper

import com.example.tdahelper.model.FileD
import com.example.tdahelper.model.FileRow
import com.example.tdahelper.model.Logger
import io.github.stefanbratanov.jvm.openai.CreateImageRequest
import io.github.stefanbratanov.jvm.openai.EditImageRequest
import io.github.stefanbratanov.jvm.openai.OpenAI
import io.github.stefanbratanov.jvm.openai.OpenAIModel
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption

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


private fun createImage(
    image: String,
    getenv: String
): String? {
    val openAI = OpenAI.newBuilder(getenv).build()
    val imagesClient = openAI.imagesClient()
    val response = imagesClient.createImage(
        CreateImageRequest.newBuilder()
            .prompt(image)
            .n(1)
            .model(OpenAIModel.DALL_E_2)
            .size("512x512")
            .quality("standard")
            .build()
    )
    println(response.data[0].b64Json)
    println(response.data[0].url)
    return response.data[0].url
}

private fun editImage(
    image: String,
    prompt: String,
    getenv: String
): String? {
    val openAI = OpenAI.newBuilder(getenv).build()
    val imagesClient = openAI.imagesClient()
    val response = imagesClient.editImage(
        EditImageRequest.newBuilder()
            .n(1)
            .prompt(prompt)
            .image(File(image).toPath())
            .model(OpenAIModel.DALL_E_2)
            .size("512x512")
            .build()
    )
    println(response.data[0].b64Json)
    println(response.data[0].url)
    return response.data[0].url
}


fun downloadImage(url: String, outputPath: String) {
    URL(url).openStream().use { inputStream: InputStream ->
        Files.copy(
            inputStream,
            File(outputPath).toPath(),
            StandardCopyOption.REPLACE_EXISTING
        )
    }
}