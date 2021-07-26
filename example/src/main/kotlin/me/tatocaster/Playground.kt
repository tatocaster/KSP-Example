package me.tatocaster

import me.tatocaster.generated.SimpleGeneratedClass

fun main() {
    println("start")

    val generatedClass = SimpleGeneratedClass()
    println("option with the name {enabled} passed to KSP via Gradle: ${generatedClass.enabled}")

    val testClassPrint = TestClassPrint()
    testClassPrint.printAllProperties()
}