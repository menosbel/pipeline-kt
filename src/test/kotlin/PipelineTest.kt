import builders.PipelineBuilder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PipelineTest {
    @ParameterizedTest
    @ValueSource(strings = ["", " "])
    fun `fail if has no name`(name: String) {
        assertThrows<Exception> {
            PipelineBuilder.with(name)
        }
    }

    @Test
    fun `fail if has no jobs`() {
        assertThrows<Exception> {
            PipelineBuilder.withNoJobs()
        }
    }

    @Test
    fun `fail if has dependency cycle`() {
        assertThrows<Exception> {
            PipelineBuilder.withDependencyCycle()
        }
    }
}
