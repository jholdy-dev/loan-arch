import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.0"
}

group = "project"
version = "0.1"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories { mavenCentral() }

    tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "21" }

    java { targetCompatibility = JavaVersion.VERSION_21 }

    kotlin {
        jvmToolchain(21)
    }
}
