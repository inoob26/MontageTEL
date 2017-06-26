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
        val array : JsonArray = json.asJsonArray

        //for formating date from yyyy.mm.dd HH:mm to HH:mm
        val sdf1 = SimpleDateFormat("yyyy.mm.dd HH:mm")
        val sdf2 = SimpleDateFormat("HH:mm")

        var ticketList : TicketList = TicketList()
        var jsonObject : JsonObject

        for (element : JsonElement in array) {
            jsonObject = element.asJsonObject
            //Reserve status
            if(jsonObject.get("Status").asInt == 11){
                ticketList.addTask(Task(
                        jsonObject.get("Id").asInt,
                        0,
                        jsonObject.get("Status").asInt,
                        "FirstName",
                        "SecondName",
                        "MiddleName",
                        jsonObject.get("ServiceInfo").asString,
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
                        jsonObject.get("ServiceInfo").asString,
                        jsonObject.get("ClientPhone").asString,
                        jsonObject.get("FlatNumber").asString,
                        jsonObject.get("ObjectName").asString,
                        sdf2.format(sdf1.parse(jsonObject.get("StartDateTime").asString))
                ))
            }
        }

        return ticketList
    }
}