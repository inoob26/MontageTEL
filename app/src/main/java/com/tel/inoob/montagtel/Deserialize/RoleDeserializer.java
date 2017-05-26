package com.tel.inoob.montagtel.Deserialize;

import com.google.gson.*;
import com.tel.inoob.montagtel.Model.Role;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * {@code RoleDeserializer } deserialize Role class from json.
 *
 * @author inoob
 * @since 0.1
 */
public class RoleDeserializer implements JsonDeserializer<Role> {
    @Override
    public Role deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Role role = new Role();

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        if(jsonObject.get("Id").isJsonPrimitive()){
            role.setId(jsonObject.get("Id").getAsInt());
        }
        if (jsonObject.get("Name").isJsonPrimitive()){
            role.setName(jsonObject.get("Name").getAsString());
        }

        return role;
    }
}
