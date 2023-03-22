package insfrastructure.io

class ConsoleSystemIO: SystemIO {
    override fun print(message: String) = println(message)
}
