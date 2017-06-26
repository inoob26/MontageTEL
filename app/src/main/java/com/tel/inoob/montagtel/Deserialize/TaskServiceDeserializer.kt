package com.tel.inoob.montagtel.Deserialize

import com.google.gson.*
import com.tel.inoob.montagtel.Model.TaskService

import java.lang.reflect.Type

/**
 * `TaskServiceDeserializer class` deserialize TaskService object form json.

 * @author inoob
 * *
 * @since 0.1
 */
class TaskServiceDeserializer : JsonDeserializer<TaskService> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): TaskService {
        val taskService = TaskService()

        val `object` = json.asJsonObject

        taskService.id = `object`.get("Id").asInt
        taskService.taskId = `object`.get("TaskId").asInt
        taskService.isBreak = `object`.get("IsBreak").asBoolean
        taskService.isCompleted = `object`.get("IsCompleted").asBoolean
        taskService.isPlan = `object`.get("IsPlan").asBoolean
        taskService.quantity = `object`.get("Quantity").asByte
        taskService.serviceTemplateId = `object`.get("ServiceTemplateId").asInt
        taskService.serviceName = `object`.get("ServiceName").asString
        taskService.scladId = `object`.get("ScladId").asInt
        taskService.tarifName = `object`.get("TarifName").asString
        taskService.price = `object`.get("Price").asDouble
        taskService.tarifType = `object`.get("TarifType").asByte
        taskService.cost = `object`.get("Cost").asDouble

        return taskService
    }
}
