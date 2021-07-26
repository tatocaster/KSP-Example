KSP example
---
Exploring Kotlin Symbol Processing - KSP. This is just an experiment.

Project contains 2 modules
- Processing
- Example

Processing module is the one which is responsible for code generation. 
There are two samples: `PrintProcessor` and `SimpleClassGeneratorProcessor`.

- `SimpleClassGeneratorProcessor` generates the class with the arguments passed to processor from Gradle
- `PrintProcessor` generates class which prints all properties with the names and types from the class annotated with "@Print".
Also it shows how annotation parsing is done in KSP and utilizes visitor pattern.

Project uses Zac Sweers's [`AutoService`](https://github.com/ZacSweers/auto-service-ksp) which is using
KSP already instead of KAPT and KotlinPoet.

Keep in mind : *"The reduction of build times is only applicable if there are no other processors that use KAPT."* - 
Source: https://developer.android.com/jetpack/androidx/releases/room#2.3.0-beta02

## Processor Options
Processor options in `SymbolProcessorEnvironment.options` are specified in gradle build scripts:
```
  ksp {
    arg("enabled", "true")
  }
```

## IDEs and Generated Code
Mark directory as a "generated source root" to make it resolvable
```
build/generated/ksp/main/kotlin/
```

and also "resource directory":
```
build/generated/ksp/main/resources
```
