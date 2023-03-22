object TestExamples {
    object jobA {
        val name = "A"
        val commands = listOf("echo hello")
    }

    object jobB {
        val name = "B"
        val commands = listOf("echo hello")
    }

    object jobC {
        val name = "C"
        val commands = listOf("echo hello")
    }

    object validPipeline {
        val name = "First Pipeline"
        val jobs = listOf(jobA)
    }
}