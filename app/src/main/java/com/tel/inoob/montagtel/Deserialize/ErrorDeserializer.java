package com.tel.inoob.montagtel.Deserialize;

import com.google.gson.*;
import com.tel.inoob.montagtel.Model.Error;

import java.lang.reflect.Type;

/**
 * @author inoob
 * @since 0.1
 */
public class ErrorDeserializer implements JsonDeserializer<Error> {
    @Override
    public Error deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Error error = new Error();

        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement element = jsonObject.get("ErrorCode");
        if(element == null) {
            return error;
        } else {
            error.setErrorCode(jsonObject.get("ErrorCode").getAsInt());
            error.setErrorMsg(jsonObject.get("ErrorMessage").getAsString());
        }

        return error;
    }
}
