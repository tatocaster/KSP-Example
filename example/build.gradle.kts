plugins {
    id("com.google.devtools.ksp")
    kotlin("jvm")
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":processor"))
    ksp(project(":processor"))
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

ksp {
    arg("enabled", "true")
}
