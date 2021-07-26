pluginManagement {
    val kotlinVersion: String by settings
    val kspVersion: String by settings
    repositories {
        gradlePluginPortal()
        google()
    }
    plugins {
        id("com.google.devtools.ksp") version kspVersion
        kotlin("jvm") version kotlinVersion
    }
}

rootProject.name = "KspExample"

include(":processor")
include("example")
