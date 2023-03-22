package core.domain

class Pipeline(val name: String, val jobs: List<Job>) {
    init {
        if (name.isBlank()) throw Exception("Name cannot be blank")
        if (jobs.isEmpty()) throw Exception("Jobs cannot be empty")
        jobs.forEach {
            if (it.hasCircularDependencyWith(jobs)) throw Exception("Jobs cannot have circular dependency")
        }
    }
}
