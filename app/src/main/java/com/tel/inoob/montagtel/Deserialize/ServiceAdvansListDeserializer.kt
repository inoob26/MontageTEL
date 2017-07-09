package com.tel.inoob.montagtel.Deserialize

import com.google.gson.*
import com.tel.inoob.montagtel.Model.ServiceAdvans
import com.tel.inoob.montagtel.Model.ServiceAdvansList
import java.lang.reflect.Type

/**
 * @author inoob
 * @since 0.1
 */
class ServiceAdvansListDeserializer: JsonDeserializer<ServiceAdvansList> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ServiceAdvansList {
        if (!json!!.isJsonNull) {
            var serviceAdvans: ServiceAdvansList = ServiceAdvansList()
            val array: JsonArray = json!!.asJsonArray

            var temp : JsonObject
            for (element in array){
                temp = element.asJsonObject
                serviceAdvans.addServiceAdvans(
                        ServiceAdvans(
                                temp.get("Id").asInt,
                                temp.get("ServiceName").asString,
                                temp.get("IsCompleted").asBoolean,
                                temp.get("Quantity").asInt,
                                temp.get("Price").asDouble)
                )
            }
            return serviceAdvans
        } else {
            var serviceAdvans: ServiceAdvansList = ServiceAdvansList()

            serviceAdvans.addServiceAdvans(
                    ServiceAdvans(0, "", false, -1, 0.0)
            )

            return serviceAdvans
        }
    }
}