import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.squareup.kotlinpoet.FileSpec
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets.UTF_8

fun FileSpec.writeTo(codeGenerator: CodeGenerator, dependencies: Dependencies = Dependencies(false)) {
    val file = codeGenerator.createNewFile(dependencies, packageName, name)
    OutputStreamWriter(file, UTF_8).use(::writeTo)
}