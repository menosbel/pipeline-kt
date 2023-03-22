package insfrastructure.serializer

import com.google.gson.GsonBuilder
import core.domain.Pipeline

class GsonSerializer: Serializer {
    private val gson = GsonBuilder()

    override fun deserialize(serialized: String?, type: Class<Pipeline>): Pipeline = gson.create().fromJson(serialized, type)
}
