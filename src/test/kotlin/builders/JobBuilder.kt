package builders

import TestExamples.jobA
import core.domain.Job

class JobBuilder {
    companion object {
        fun someJob() = Job("Some job", listOf("Some command"))
    }
}