package com.tel.inoob.montagtel.Deserialize

import com.google.gson.*
import com.tel.inoob.montagtel.Model.Task
import com.tel.inoob.montagtel.Model.TaskService
import com.tel.inoob.montagtel.Model.TaskServiceList
import java.lang.reflect.Type

/**
 * {@code TaskServiceListDeserialize class} deserialize TaskServiceList.
 *
 * @author inoob
 * @since 0.1
 */
class TaskServiceListDeserialize : JsonDeserializer<TaskServiceList> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): TaskServiceList {
        val array : JsonArray = json.asJsonArray
        var taskServiceList: TaskServiceList = TaskServiceList()
        var jsonObject: JsonObject
        if(json.isJsonArray) {


            for (element in array) {

                jsonObject = element.asJsonObject

                taskServiceList.addTaskService(TaskService(
                        jsonObject.get("Id").asInt,
                        jsonObject.get("TaskId").asInt,
                        jsonObject.get("IsBreak").asBoolean,
                        jsonObject.get("IsCompleted").asBoolean,
                        jsonObject.get("IsPlan").asBoolean,
                        jsonObject.get("Quantity").asByte,
                        jsonObject.get("ServiceTemplateId").asInt,
                        jsonObject.get("ServiceName").asString,
                        jsonObject.get("ScladId").asInt,
                        jsonObject.get("TarifName").asString,
                        jsonObject.get("Price").asDouble,
                        jsonObject.get("TarifType").asByte,
                        jsonObject.get("Cost").asDouble
                ))
            }
        } else {
            jsonObject = json.asJsonObject
            taskServiceList.addTaskService(TaskService(jsonObject.get("ErrorCode").asInt,"ErrorCode: "
                    + jsonObject.get("ErrorCode").asInt + " " + jsonObject.get("ErrorMessage").asString ))
        }

        return taskServiceList
    }
}