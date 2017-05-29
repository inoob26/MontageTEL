package com.tel.inoob.montagtel.Deserialize

import com.google.gson.*
import com.tel.inoob.montagtel.Model.Task
import java.lang.reflect.Type

/**
 * {@code TaskDeserializer} class deserialize Task class form json.
 *
 * @author inoob
 * @since 0.1
 */
class TaskDeserializer : JsonDeserializer<Task> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Task {
        val json : JsonObject = json.asJsonObject;
        var task : Task = Task();

        task.id = json.get("Id").asInt;
        task.firstName = json.get("FirstName").asString;
        task.secondName = json.get("SecondName").asString;
        task.middleName = json.get("MiddleName").asString;
        task.clientId = json.get("ClientId").asInt;
        task.flatNumber = json.get("FlatNumber").asString;
        task.objectName = json.get("ObjectName").asString;
        task.clientPhone = json.get("ClientPhone").asString;
        task.serviceInfo = json.get("ServiceInfo").asString;

        return task;
    }
}