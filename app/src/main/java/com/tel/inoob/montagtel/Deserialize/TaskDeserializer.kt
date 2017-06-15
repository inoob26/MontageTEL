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
        var jsonObj : JsonObject = json.asJsonObject
        var task : Task = Task()

        if (!checkElement(jsonObj.get("Id"),"Task Id is null")){
            task.id = jsonObj.get("Id").asInt
        }
        if (!checkElement(jsonObj.get("FirstName"),"Task FirstName is null")) {
            task.firstName = jsonObj.get("FirstName").asString
        }
        if(!checkElement(jsonObj.get("SecondName"),"Task SecondName is null")){
            task.secondName = jsonObj.get("SecondName").asString
        }
        if (!checkElement(jsonObj.get("MiddleName"),"Task MiddleName is null")){
            task.middleName = jsonObj.get("MiddleName").asString
        }
        if(!checkElement(jsonObj.get("Status"),"Task Status is null")){
            task.status = jsonObj.get("Status").asInt
        }
        if(!checkElement(jsonObj.get("ClientId"),"Task ClientId is null")){
            task.clientId = jsonObj.get("ClientId").asInt
        }
        if(!checkElement(jsonObj.get("FlatNumber"),"Task FlatNumber is null")){
            task.flatNumber = jsonObj.get("FlatNumber").asString
        }
        if(!checkElement(jsonObj.get("ObjectName"),"Task ObjectName is null")){
            task.objectName = jsonObj.get("ObjectName").asString
        }
        if(!checkElement(jsonObj.get("ClientPhone"),"Task ClientPhone is null")){
            task.clientPhone = jsonObj.get("ClientPhone").asString
        }
        if(!checkElement(jsonObj.get("ServiceInfo"),"Task ServiceInfo is null")){
            task.serviceInfo = jsonObj.get("ServiceInfo").asString
        }

        return task
    }

    private fun checkElement(element: JsonElement,message: String) : Boolean {
        if( element == null) {
            println(message)
            return true
        } else {
            return false
        }
    }
}