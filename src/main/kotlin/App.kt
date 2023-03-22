import core.domain.Pipeline
import insfrastructure.fileManager.FileManager
import insfrastructure.io.SystemIO
import insfrastructure.serializer.Serializer

class App(private val systemIO: SystemIO, private val fileManager: FileManager, private val serializer: Serializer) {
    private val VALID_MESSAGE = "VALID"
    private val INVALID_MESSAGE = "INVALID"

    fun start(filePath: String) {
        val serialized = fileManager.read(filePath)
        try {
            serializer.deserialize(serialized, Pipeline::class.java)
            systemIO.print(VALID_MESSAGE)
        } catch (e: Exception) {
            systemIO.print(INVALID_MESSAGE)
        }
    }
}
