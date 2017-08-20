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
    /* Local IP
    private final static String D_LOGIN_PASSWORD_PATH = "http://10.192.25.4:9190/mobile/login?login=";
    public final static String D_TASK_PATH = "http://10.192.25.4:9190/mobile/task?id=";
    private final static String D_TASK_SERVICE_PATH = "http://10.192.25.4:9190/mobile/ServiceByTask?id=";
    private final static String D_SERVICE_ADVANS_PATH = "http://10.192.25.4:9190/mobile/ServiceAdvans?userid=";

    private static final String GET_CONSUMABLES_BY_TASK = "http://10.192.25.4:9190/mobile/ConsumablesByTask?id=";
    */

    //Global IP
    private final static String D_LOGIN_PASSWORD_PATH = "http://87.249.19.150:9190/mobile/login?login=";
    private final static String D_TASK_PATH = "http://87.249.19.150:9190/mobile/task?id=";
    private final static String D_TASK_SERVICE_PATH = "http://87.249.19.150:9190/mobile/ServiceByTask?id=";
    private final static String D_SERVICE_ADVANS_PATH = "http://87.249.19.150:9190/mobile/ServiceAdvans?userid=";
    private static final String GET_CONSUMABLES_BY_TASK = "http://87.249.19.150:9190/mobile/ConsumablesByTask?id=";

    public Error deserializeLoginPassword(final String login, final  String password) {
        String json = getJson(D_LOGIN_PASSWORD_PATH + login + "&password=" + password);

        //Error error = deserializeError(json);

        return deserializeError(json);
    }

    public Error deserializeError(final String json){

        //Error error = new Error();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Error.class, new ErrorDeserializer())
                .create();

        //error = gson.fromJson(json,Error.class);

        return gson.fromJson(json,Error.class);
    }

    /**
     * Deserialize Error Object.
     *
     * @return error object.
     */
    public Error deserializeErrorForTask(final int user_id, final String date){
        String json = getJson(D_TASK_PATH + user_id + "&date=" + date);

        //Error error = new Error();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Error.class, new ErrorDeserializer())
                .create();

        //error = gson.fromJson(json,Error.class);

        return gson.fromJson(json,Error.class);
    }

    /**
     * Deserialize User object from webService.
     *
     * @return user.
     */
    public User deserializeUser(final String login, final  String password) {

        String json = getJson(D_LOGIN_PASSWORD_PATH + login + "&password=" + password);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleDeserializer())
                .registerTypeAdapter(User.class, new UserDeserializer())
                .create();

        return gson.fromJson(json, User.class);
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

    public Error deserializeErrorTaskService(final int task_id){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Error.class, new ErrorDeserializer())
                .create();

        //Error error = gson.fromJson(getJson(D_TASK_SERVICE_PATH + task_id),Error.class);

        return gson.fromJson(getJson(D_TASK_SERVICE_PATH + task_id),Error.class);
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

    public List<ConsumableByTask> deserializeConsumableByTask(final int task_id){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ConsumableByTask.class, new ConsumableByTaskDeserializer())
                .registerTypeAdapter(ConsumableByTaskList.class,new ConsumableByTaskListDeserializer())
                .create();

        String json = getJson(GET_CONSUMABLES_BY_TASK + task_id);

        ConsumableByTaskList list = gson.fromJson(json,ConsumableByTaskList.class);

        return list.getList();
    }

    private String getJson(final String path){
        NewWebClient webClient = new NewWebClient();
        return webClient.getDataFromUrl(path);
    }
}
