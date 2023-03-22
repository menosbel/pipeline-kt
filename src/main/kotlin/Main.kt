import insfrastructure.io.ConsoleSystemIO
import insfrastructure.serializer.GsonSerializer
import insfrastructure.fileManager.JavaFileManager

fun main() {
    val inputOutput = ConsoleSystemIO()
    val fileManager = JavaFileManager()
    val serializer = GsonSerializer()
    App(inputOutput, fileManager, serializer).start("./src/test/resources/pipelineWithNoName.json")
}