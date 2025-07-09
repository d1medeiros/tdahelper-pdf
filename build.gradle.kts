plugins {
    kotlin("jvm") version "2.0.21"
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"
application {
    // Set your fully qualified main class (if you wrote `fun main` in a file named Main.kt, it’ll be MainKt)
    mainClass.set("com.example.tdahelper.MainAppKt")
}


repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("io.github.stefanbratanov:jvm-openai:0.11.0")
    implementation(kotlin("stdlib"))
   
    implementation("com.jsoizo:kotlin-csv-jvm:1.10.0")
    implementation("com.github.tabulapdf:tabula-java:1.0.5")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("tdahelper-all")
        archiveVersion.set("1.0")
        // By default, shadowJar merges all runtimeClasspath entries into one JAR.
    }
}