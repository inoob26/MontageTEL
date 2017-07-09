package com.tel.inoob.montagtel.Deserialize

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.tel.inoob.montagtel.Model.ServiceAdvans
import java.lang.reflect.Type

/**
 * Deserialize ServiceAdvans from json.
 *
 * @author inoov
 * @since 0.1
 */
class ServiceAdvansDeserializer : JsonDeserializer<ServiceAdvans> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ServiceAdvans {
        var serviceAdvans: ServiceAdvans = ServiceAdvans()

        if(!json!!.isJsonNull) {
            val jsonObj: JsonObject = json!!.asJsonObject

            serviceAdvans.id = jsonObj.get("Id").asInt
            serviceAdvans.serviceName = jsonObj.get("ServiceName").asString
            serviceAdvans.isCompleted = jsonObj.get("IsCompleted").asBoolean
            serviceAdvans.quantity = jsonObj.get("Quantity").asInt
            serviceAdvans.price = jsonObj.get("Price").asDouble
        } else {
            serviceAdvans.id = 0
            serviceAdvans.serviceName = ""
            serviceAdvans.isCompleted = false
            serviceAdvans.quantity = -1
            serviceAdvans.price = 0.0
        }

        return serviceAdvans
    }
}