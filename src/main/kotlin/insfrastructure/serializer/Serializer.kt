package insfrastructure.serializer

import core.domain.Pipeline

interface Serializer {
    fun deserialize(serialized: String?, type: Class<Pipeline>): Pipeline
}
