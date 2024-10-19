/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.8/userguide/building_java_projects.html in the Gradle documentation.
 * This project uses @Incubating APIs which are subject to change.
 */

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    alias(libs.plugins.jvm)
    id("com.gradleup.shadow") version "8.3.3"
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // This dependency is used by the application.
    implementation(libs.guava)

    implementation("io.temporal:temporal-sdk:1.25.1")
    implementation("org.jline:jline:3.25.0")
    implementation("com.google.inject:guice:7.0.0")
    implementation("org.apache.sshd:sshd-core:2.12.1")
    implementation("com.beust:jcommander:1.82")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("1.9.22")
        }
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    // Define the main class for the application.
    mainClass = "com.rustyrazorblade.temporaldemo.AppKt"
}

// add a gradle task that runs Worker.kt
tasks {
    register<JavaExec>("runWorker") {
        group = "application"
        description = "Runs the Kotlin application in the submodule"
        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("com.rustyrazorblade.temporaldemo.WorkerKt")
    }
}

// this is needed to make grpc happy when ECL is run via the shadowJar
tasks.named("shadowJar", ShadowJar::class) {
    mergeServiceFiles()
}