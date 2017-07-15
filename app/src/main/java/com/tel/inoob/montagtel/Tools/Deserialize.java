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

    /**
     * The Constant PATH's.
     */
    private final static String D_LOGIN_PASSWORD_PATH = "http://10.192.25.4:9190/mobile/login?login=";
    public final static String D_TASK_PATH = "http://10.192.25.4:9190/mobile/task?id=";
    private final static String D_TASK_SERVICE_PATH = "http://10.192.25.4:9190/mobile/ServiceByTask?id=";
    private final static String D_SERVICE_ADVANS_PATH = "http://10.192.25.4:9190/mobile/ServiceAdvans?userid=";

    public int deserializeLoginPasswod(final String login, final  String password) {
        String json = getJson(D_LOGIN_PASSWORD_PATH + login + "&password=" + password);

        int result;

        Error error = deserializeError(json);

        if(error.getErrorCode() == -1){
            result = deserializeUser(json);
            return result;
        } else {
            return error.getErrorCode();
        }
    }

    /**
     * Deserialize Error Object.
     *
     * @param json path.
     * @return error object.
     */
    public Error deserializeError(final String json){
        Error error = new Error();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Error.class, new ErrorDeserializer())
                .create();

        error = gson.fromJson(json,Error.class);

        return error;
    }

    /**
     * Deserialize User object from webService.
     *
     * @param json path.
     * @return user id.
     */
    private int deserializeUser(final String json) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleDeserializer())
                .registerTypeAdapter(User.class, new UserDeserializer())
                .create();

        System.out.println(json);

        User user = gson.fromJson(json, User.class);

        return user.getId();
    }

    /**
     * Deserialize List of Task from webService.
     *
     * @param user_id user id.
     * @param date date and time.
     * @return List of Task
     */
    public List<Task> deserializeTask(final int user_id, final String date) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskDeserializer())
                .registerTypeAdapter(TicketList.class, new TicketListDeserializer())
                .create();
        TicketList ticketList = gson.fromJson(getJson(D_TASK_PATH + user_id + "&date=" + date), TicketList.class);

        return ticketList.getTaskList();
    }

    /**
     * Deserialize list of TaskService from webService.
     *
     * @param task_id task_id.
     * @return list of taskService.
     */
    public  List<TaskService> deserializeTaskService(final int task_id){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TaskService.class, new TaskServiceDeserializer())
                .registerTypeAdapter(TaskServiceList.class, new TaskServiceListDeserialize())
                .create();

        TaskServiceList taskServiceList = gson.fromJson(getJson(D_TASK_SERVICE_PATH + task_id), TaskServiceList.class);

        return taskServiceList.getList();
    }

    public List<ServiceAdvans> deserializeServiceAdvans(final int user_id){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ServiceAdvans.class, new ServiceAdvansDeserializer())
                .registerTypeAdapter(ServiceAdvansList.class, new ServiceAdvansListDeserializer())
                .create();

        String json = getJson(D_SERVICE_ADVANS_PATH + user_id);

        ServiceAdvansList serviceAdvansList = gson.fromJson(json, ServiceAdvansList.class);

        return serviceAdvansList.getList();
    }

    public String getJson(final String path){
        WebClient webClient = new WebClient(path);

        return webClient.getJSON();
    }
}
