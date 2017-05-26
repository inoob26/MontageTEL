package com.tel.inoob.montagtel.Tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tel.inoob.montagtel.Deserialize.RoleDeserializer;
import com.tel.inoob.montagtel.Deserialize.UserDeserializer;
import com.tel.inoob.montagtel.Model.Role;
import com.tel.inoob.montagtel.Model.User;

/**
 * {@code Deserialize class} work with webservice.
 * It will deserialize objects.
 *
 * @author inoob
 * @since 0.1
 */
public class Deserialize {
    private WebClient webClient;

    public int deserializeUser(final String login,final String password){
        int clientId = 0;

        WebClient webClient = new WebClient("http://10.192.25.4:9190/mobile/login?login="
                + login + "&password=" + password);
        webClient.getJSON();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleDeserializer())
                .registerTypeAdapter(User.class, new UserDeserializer())
                .create();

        User user = gson.fromJson(webClient.getJSON(), User.class);

        if(!user.getName().equals(" ")){
            clientId = user.getId();
        }

        return clientId;
    }
}
