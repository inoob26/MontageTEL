package com.tel.inoob.montagtel.Deserialize

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.tel.inoob.montagtel.Model.ConsumableByTask
import java.lang.reflect.Type

/**
 * @author inoob
 * @since 0.1
 */
class ConsumableByTaskDeserializer : JsonDeserializer<ConsumableByTask> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ConsumableByTask {
        val consumableByTask: ConsumableByTask = ConsumableByTask()

        val jsonObj : JsonObject = json!!.asJsonObject

        consumableByTask.ID = jsonObj.get("ID").asInt
        consumableByTask.name = jsonObj.get("Name").asString
        consumableByTask.si = jsonObj.get("Si").asString
        consumableByTask.quantity = jsonObj.get("Quantity").asInt
        consumableByTask.select = jsonObj.get("Select").asBoolean

        return consumableByTask
    }
}