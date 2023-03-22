package core.domain

class Job(private val name: String, commands: List<String>, private val dependsOn: List<String>? = listOf()) {
    fun hasCircularDependencyWith(jobs: List<Job>): Boolean {
        jobs.forEach { job ->
            job.dependsOn?.forEach { dependency ->
                val jobItDependsOn = jobs.find { it.name == dependency }
                jobItDependsOn?.dependsOn?.forEach {
                    if (it == job.name) return true
                    else {
                        hasCircularDependencyWith(jobs, job.dependsOn)
                    }
                }
            }
        }
        return false
    }

    private fun hasCircularDependencyWith(jobs: List<Job>, jobsDependencyList: List<String>): Boolean {
        jobsDependencyList.forEach { dependency ->
            val jobItDependsOn = jobs.find { it.name == dependency }
            jobItDependsOn?.dependsOn?.forEach {
                if (it == jobItDependsOn.name) return true
                else {
                    hasCircularDependencyWith(jobs, jobItDependsOn.dependsOn)
                }
            }
        }
        return false
    }

    override fun toString() = "Job(name='$name', dependsOn=$dependsOn)"

}
