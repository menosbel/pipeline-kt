import TestExamples.validPipeline
import core.domain.Job
import core.domain.Pipeline
import insfrastructure.fileManager.FileManager
import insfrastructure.fileManager.JavaFileManager
import insfrastructure.serializer.Serializer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AppTest {
    @Test
    fun `should read pipeline from json file`() {
        app.start(validPipelinePath)

        verify(exactly = 1) { fileManager.read(validPipelinePath) }
    }

    @Test
    fun `should call serializer`() {
        app.start(validPipelinePath)

        verify { serializer.deserialize(serializedValidPipeline, Pipeline::class.java) }
    }

    @Test
    fun `should print VALID if pipeline is valid`() {
        val serialized = JavaFileManager().read(validPipelinePath)
        every { fileManager.read(validPipelinePath) }.returns(serialized)
        every { serializer.deserialize(serialized, Pipeline::class.java) }.returns(buildPipeline())

        app.start(validPipelinePath)

        val output = systemIO.messages
        assertThat(output).containsExactly(VALID_MESSAGE)
    }

    @Test
    fun `should print INVALID if pipeline is invalid`() {
        every { fileManager.read(invalidPipelinePath) }.returns(serializedInvalidPipeline)
        every { serializer.deserialize(serializedInvalidPipeline, Pipeline::class.java) }.throws(Exception())

        app.start(invalidPipelinePath)

        val output = systemIO.messages
        assertThat(output).containsExactly(INVALID_MESSAGE)
    }

    @BeforeEach
    fun beforeEach() {
        every { fileManager.read(validPipelinePath) }.returns(serializedValidPipeline)
        every { serializer.deserialize(validPipelinePath, Pipeline::class.java) }.returns(buildPipeline())
    }

    private fun buildPipeline() = Pipeline(validPipeline.name, listOf(buildJob()))

    private fun buildJob() = Job(validPipeline.jobs.first().name, validPipeline.jobs.first().commands)

    private val VALID_MESSAGE = "VALID"
    private val INVALID_MESSAGE = "INVALID"
    private val validPipelinePath = "./src/test/resources/pipeline.json"
    private val invalidPipelinePath = "./src/test/resources/pipelineWithNoName.json"
    private val serializedValidPipeline = JavaFileManager().read(validPipelinePath)
    private val serializedInvalidPipeline = JavaFileManager().read(invalidPipelinePath)
    private val systemIO = FakeSystemIO()
    private val fileManager = mockk<FileManager>(relaxed = true)
    private val serializer = mockk<Serializer>(relaxed = true)
    private val app = App(systemIO, fileManager, serializer)

}
