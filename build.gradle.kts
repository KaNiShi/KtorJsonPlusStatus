import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    kotlin("jvm") version "1.4.0"
    kotlin("plugin.serialization") version "1.4.0"
    application
}

group = "com.github.kanishi.ktor_json_plus_status"
version = "0.1.0"

repositories {
    mavenCentral()
    jcenter()
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://kotlin.bintray.com/ktor")
}

application.mainClassName = "MainKt"

dependencies {
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0")
    implementation("io.ktor:ktor-client-mock:1.4.1")
    implementation("io.ktor:ktor-client-logging-jvm:1.4.1")
    implementation("io.ktor:ktor-client-serialization-jvm:1.4.1")
    implementation("org.slf4j:slf4j-nop:1.7.30")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
