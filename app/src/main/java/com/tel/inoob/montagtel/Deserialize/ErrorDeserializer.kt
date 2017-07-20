package com.tel.inoob.montagtel.Deserialize

import com.google.gson.*
import com.tel.inoob.montagtel.Model.Error

import java.lang.reflect.Type

/**
 * @author inoob
 * @since 0.1
 */
class ErrorDeserializer : JsonDeserializer<Error> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Error {
        val error = Error()

        if(json.isJsonObject){
            val jsonObject = json.asJsonObject
            val element = jsonObject.get("ErrorCode")
            if (element == null) {
                return error
            } else {
                error.errorCode = jsonObject.get("ErrorCode").asInt
                error.errorMsg = jsonObject.get("ErrorMessage").asString
                error.toLogin = jsonObject.get("ToLogin").asInt
            }
        }

        return error
    }
}
