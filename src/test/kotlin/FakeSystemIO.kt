import insfrastructure.io.SystemIO

class FakeSystemIO: SystemIO {
    val messages: MutableList<String> = mutableListOf()

    override fun print(message: String) {
        messages.add(message)
    }
}