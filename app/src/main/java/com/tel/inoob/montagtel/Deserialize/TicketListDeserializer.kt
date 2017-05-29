package com.tel.inoob.montagtel.Deserialize

import com.google.gson.*
import com.tel.inoob.montagtel.Model.Task
import com.tel.inoob.montagtel.Model.TicketList
import java.lang.reflect.Type

/**
 * {@code TicketListDeserializer} class deserialize TicketList form json.
 *
 * @author inoob
 * @since 0.1
 */
class TicketListDeserializer : JsonDeserializer<TicketList> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): TicketList {
        val array : JsonArray = json.asJsonArray;

        var ticketList : TicketList = TicketList();
        var jsonObject : JsonObject;
        for (element : JsonElement in array) {
            jsonObject = element.asJsonObject;
            if(jsonObject.get("Id").isJsonPrimitive){
                if (jsonObject.get("FirstName").isJsonPrimitive){
                    if(jsonObject.get("SecondName").isJsonPrimitive){
                        if(jsonObject.get("MiddleName").isJsonPrimitive){
                            if(jsonObject.get("ClientId").isJsonPrimitive){
                                if(jsonObject.get("FlatNumber").isJsonPrimitive){
                                    if (jsonObject.get("ObjectName").isJsonPrimitive){
                                        if(jsonObject.get("ClientPhone").isJsonPrimitive){
                                            if(jsonObject.get("ServiceInfo").isJsonPrimitive){
                                                ticketList.addTask(Task(
                                                        jsonObject.get("Id").asInt,
                                                        jsonObject.get("ClientId").asInt,
                                                        jsonObject.get("FirstName").asString,
                                                        jsonObject.get("SecondName").asString,
                                                        jsonObject.get("MiddleName").asString,
                                                        jsonObject.get("ServiceInfo").asString,
                                                        jsonObject.get("ClientPhone").asString,
                                                        jsonObject.get("FlatNumber").asString,
                                                        jsonObject.get("ObjectName").asString));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return ticketList;
    }
}