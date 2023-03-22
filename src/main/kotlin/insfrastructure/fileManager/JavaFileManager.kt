package insfrastructure.fileManager

import java.io.File

class JavaFileManager: FileManager {
    override fun read(path: String) = File(path).readText()
}