plugins {
    application
    kotlin("jvm") version "1.7.20"
}

group = "eu.vendeli.samples"
version = "0.0.1"
application {
    mainClass.set("eu.vendeli.samples.TgBotApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("eu.vendeli:telegram-bot:2.2.1")
    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation("org.xerial:sqlite-jdbc:3.30.1")
}