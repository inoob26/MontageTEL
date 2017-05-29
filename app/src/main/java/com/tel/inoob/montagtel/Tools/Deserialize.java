package com.tel.inoob.montagtel.Tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tel.inoob.montagtel.Deserialize.RoleDeserializer;
import com.tel.inoob.montagtel.Deserialize.TaskDeserializer;
import com.tel.inoob.montagtel.Deserialize.TicketListDeserializer;
import com.tel.inoob.montagtel.Deserialize.UserDeserializer;
import com.tel.inoob.montagtel.Model.Role;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.Model.TicketList;
import com.tel.inoob.montagtel.Model.User;

import java.util.List;

/**
 * {@code Deserialize class} work with webservice.
 * It will deserialize objects.
 *
 * @author inoob
 * @since 0.1
 */
public class Deserialize {
    public int deserializeUser(final String login,final String password) {

        WebClient webClient = new WebClient("http://10.192.25.4:9190/mobile/login?login="
                + login + "&password=" + password);
        String json = webClient.getJSON();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleDeserializer())
                .registerTypeAdapter(User.class, new UserDeserializer())
                .create();

        User user = gson.fromJson(json, User.class);

        return user.getId();
    }

    public void deserializeTask(final int idMontag, final String date) {
        WebClient webClient = new WebClient("http://10.192.25.4:9190/mobile/task?id="
                + idMontag + "&date=" + date );
        String json = webClient.getJSON();

        System.out.println(json);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskDeserializer())
                .registerTypeAdapter(TicketList.class, new TicketListDeserializer())
                .setPrettyPrinting()
                .create();

        TicketList ticketList = gson.fromJson(json, TicketList.class);
        List<Task> list = ticketList.getTaskList();
        for (Task task : list){
            System.out.println(task.getId());
        }
    }
}
