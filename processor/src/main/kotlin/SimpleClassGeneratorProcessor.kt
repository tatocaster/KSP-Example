import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.squareup.kotlinpoet.*

class SimpleClassGeneratorProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>
) : SymbolProcessor {
    // we are not going to use visitor so manually avoid the processing over and over
    private var visited = false

    override fun process(resolver: Resolver): List<KSAnnotated> {
        if (visited) {
            return emptyList()
        }
        val name = "SimpleGeneratedClass"
        val fileKotlinPoet = FileSpec.builder("me.tatocaster.generated", name)
            .addType(
                TypeSpec.classBuilder(name)
                    .addProperty(
                        PropertySpec.builder("enabled", Boolean::class)
                            .initializer(options["enabled"] ?: "false")
                            .build()
                    )
                    .build()
            )
            .build()
        fileKotlinPoet.writeTo(codeGenerator)
        visited = true
        return emptyList()
    }
}