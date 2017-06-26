package com.tel.inoob.montagtel.Deserialize;

import com.google.gson.*;
import com.tel.inoob.montagtel.Model.Role;
import com.tel.inoob.montagtel.Model.User;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

/**
 * {@code UserDeserializer} class deserialize User class form json.
 *
 * @author inoob
 * @since 0.1
 */
public class UserDeserializer implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        User user = new User();

        if(jsonObject.get("Id").isJsonPrimitive()){
            user.setId(jsonObject.get("Id").getAsInt());
            user.setName(jsonObject.get("Name").getAsString());

            //if RoleId is null set -1
            if(jsonObject.get("RoleId").isJsonNull()){
                user.setRoleId(-1);
            }

            JsonArray roles = jsonObject.getAsJsonArray("Roles");

            JsonObject temp;

            for (JsonElement role : roles){
                temp = role.getAsJsonObject();
                if (temp.get("Id").isJsonPrimitive()){
                    if (temp.get("Name").isJsonPrimitive()){
                        user.addRole(new Role(temp.get("Id").getAsInt() , temp.get("Name").getAsString()));
                    }
                }
            }
        } else {
            if(jsonObject.get("ErrorCode").isJsonPrimitive()){
                user.setId(jsonObject.get("ErrorCode").getAsInt());
            }
        }

        return user;
    }
}
