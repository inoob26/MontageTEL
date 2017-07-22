package com.tel.inoob.montagtel.Deserialize

import com.google.gson.*
import com.tel.inoob.montagtel.Model.ConsumableByTask
import com.tel.inoob.montagtel.Model.ConsumableByTaskList
import java.lang.reflect.Type

/**
 * {@code ConsumableByTaskListDeserializer} class deserialize object from
 * http://10.192.25.4:9190/mobile/ConsumablesByTask?id=830
 *
 * @author inoob
 */
class ConsumableByTaskListDeserializer : JsonDeserializer<ConsumableByTaskList> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ConsumableByTaskList {
        val list : ConsumableByTaskList = ConsumableByTaskList()

        if(json!!.isJsonArray){
            val jsonArray = json!!.asJsonArray

            var temp: JsonObject = JsonObject()

            for (element: JsonElement in jsonArray){
                temp = element.asJsonObject
                list.addConsumableByTask(ConsumableByTask(
                        temp.get("ID").asInt,
                        temp.get("Name").asString,
                        temp.get("Si").asString,
                        temp.get("Quantity").asInt,
                        temp.get("Select").asBoolean
                ))
            }
        }

        return list
    }
}