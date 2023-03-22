package builders

import TestExamples.jobA
import TestExamples.jobB
import TestExamples.jobC
import TestExamples.validPipeline
import core.domain.Job
import core.domain.Pipeline

class PipelineBuilder {
    companion object {
        fun with(name: String) = Pipeline(name, jobs = listOf(JobBuilder.someJob()))
        fun withNoJobs() = Pipeline("Some pipeline", listOf())
        fun withDependencyCycle() = Pipeline(validPipeline.name, jobs = listOf(
            Job(jobA.name, jobA.commands, dependsOn = listOf(jobC.name)),
            Job(jobB.name, jobB.commands, dependsOn = listOf(jobA.name)),
            Job(jobC.name, jobC.commands, dependsOn = listOf(jobB.name)),
        ))
    }
}