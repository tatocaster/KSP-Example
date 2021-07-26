val kspVersion: String by project

plugins {
    id("com.google.devtools.ksp")
    kotlin("jvm")
}

group = "me.tatocaster"
version = "1.0"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup:kotlinpoet:1.9.0")
    implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")
    implementation("com.google.auto.service:auto-service-annotations:1.0")
    ksp("dev.zacsweers.autoservice:auto-service-ksp:0.5.2")
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}