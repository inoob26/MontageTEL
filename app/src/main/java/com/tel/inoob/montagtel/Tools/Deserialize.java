package com.tel.inoob.montagtel.Tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tel.inoob.montagtel.Deserialize.*;
import com.tel.inoob.montagtel.Model.*;
import com.tel.inoob.montagtel.Model.Error;

import java.util.List;

/**
 * {@code Deserialize class} work with webservice.
 * It will deserialize objects.
 *
 * @author inoob
 * @since 0.1
 */
public class Deserialize {

    public int deserializeLoginPasswod(final String login, final  String password) {
        WebClient webClient = new WebClient("http://10.192.25.4:9190/mobile/login?login="
                + login + "&password=" + password);
        String json = webClient.getJSON();

        int result = 0;

        Error error = deserializeError(json);

        if(error.getErrorCode() == -1){
            result = deserializeUser(json);
            return result;
        } else {
            return error.getErrorCode();
        }
    }

    private Error deserializeError(final String json){
        Error error = new Error();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Error.class, new ErrorDeserializer())
                .create();

        error = gson.fromJson(json,Error.class);

        return error;
    }

    private int deserializeUser(final String json) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleDeserializer())
                .registerTypeAdapter(User.class, new UserDeserializer())
                .create();

        User user = gson.fromJson(json, User.class);

        return user.getId();
    }

    //was void
    public List<Task> deserializeTask(final int idMontag, final String date) {
        WebClient webClient = new WebClient("http://10.192.25.4:9190/mobile/task?id="
                + idMontag + "&date=" + date );
        String json = webClient.getJSON();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskDeserializer())
                .registerTypeAdapter(TicketList.class, new TicketListDeserializer())
                .create();

        TicketList ticketList = gson.fromJson(json, TicketList.class);
        List<Task> list = ticketList.getTaskList();

        return list;
    }
}
