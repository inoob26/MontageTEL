package com.tel.inoob.montagtel.Deserialize

import com.google.gson.*
import com.tel.inoob.montagtel.Model.Task
import com.tel.inoob.montagtel.Model.TicketList
import java.lang.reflect.Type
import java.text.SimpleDateFormat

/**
 * {@code TicketListDeserializer} class deserialize TicketList form json.
 *
 * @author inoob
 * @since 0.1
 */
class TicketListDeserializer : JsonDeserializer<TicketList> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): TicketList {
        var ticketList : TicketList = TicketList()

        if(json.isJsonArray) {
            val array: JsonArray = json.asJsonArray

            //for formating date from yyyy.mm.dd HH:mm to HH:mm
            val sdf1 = SimpleDateFormat("yyyy.mm.dd HH:mm")
            val sdf2 = SimpleDateFormat("HH:mm")


            var jsonObject: JsonObject?

            for (element: JsonElement in array) {
                jsonObject = element.asJsonObject

                //Reserve status
                if (jsonObject.get("Status").asInt == 11) {

                    ticketList.addTask(Task(
                            jsonObject.get("Id").asInt,
                            0,
                            jsonObject.get("Status").asInt,
                            "FirstName",
                            "SecondName",
                            "MiddleName",
                            //joinString(jsonObject.get("ServiceInfo").asString),
                            "ServiceInfo",
                            "ClientPhone",
                            "FlatNumber",
                            jsonObject.get("ObjectName").asString,
                            sdf2.format(sdf1.parse(jsonObject.get("StartDateTime").asString))
                    ))
                } else {
                    ticketList.addTask(Task(
                            jsonObject.get("Id").asInt,
                            jsonObject.get("ClientId").asInt,
                            jsonObject.get("Status").asInt,
                            jsonObject.get("FirstName").asString,
                            jsonObject.get("SecondName").asString,
                            jsonObject.get("MiddleName").asString,
                            //joinString(jsonObject.get("ServiceInfo").asString),
                            "ServiceInfo",
                            jsonObject.get("ClientPhone").asString,
                            jsonObject.get("FlatNumber").asString,
                            jsonObject.get("ObjectName").asString,
                            sdf2.format(sdf1.parse(jsonObject.get("StartDateTime").asString))
                    ))
                }
            }
        } else {
            val jsonObject: JsonObject = json.asJsonObject
            if(jsonObject.get("ErrorCode").isJsonPrimitive()){
                ticketList.addTask(Task(jsonObject.get("ErrorCode").asInt, "Error CODE : " + jsonObject.get("ErrorCode").asInt.toString()+ " "
                        + jsonObject.get("ErrorMessage").asString))
            }
        }

        return ticketList
    }

    private fun joinString(str: String?, separator: String = "") : String {
        return (str + separator)
    }
}