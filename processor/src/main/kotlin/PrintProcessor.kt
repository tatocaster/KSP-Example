import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.*

class PrintProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation("me.tatocaster.annotation.Print")
        val invalidSymbols = symbols.filter { !it.validate() }.toList()
        symbols
            .filter { it is KSClassDeclaration && it.validate() }
            .forEach { it.accept(PrintVisitor(), Unit) }
        return invalidSymbols
    }

    inner class PrintVisitor : KSVisitorVoid() {
        override fun visitClassDeclaration(classDeclaration: KSClassDeclaration, data: Unit) {
            logger.logging("PrintProcessor#visitClassDeclaration", classDeclaration)
            val packageName = classDeclaration.containingFile!!.packageName.asString()
            val className = "${classDeclaration.simpleName.asString()}Print"
            val fileKotlinPoet = FileSpec.builder(packageName, className)

            val functionBuilder = FunSpec.builder("printAllProperties")
            classDeclaration.getAllProperties().forEach {
                functionBuilder.addStatement(
                    "println(%S)",
                    "${it.simpleName.asString()}: ${it.type.resolve()}"
                ).build()
            }

            fileKotlinPoet.addType(
                TypeSpec.classBuilder(className)
                    .addFunction(
                        functionBuilder.build()
                    ).build()
            ).build()
            fileKotlinPoet.build().writeTo(codeGenerator, Dependencies(true, classDeclaration.containingFile!!))
        }
    }
}