package com.tel.inoob.montagtel.Tools;

import android.os.AsyncTask;
import android.util.Log;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * {@code NewWebClient class} work with server using okHttp Library.
 *
 * @author inoob
 * @since 0.1
 */
public class NewWebClient {
    /**
     * TAG for logging.
     */
    private static final String TAG = NewWebClient.class.getSimpleName();

    //http://10.192.25.4:9190/mobile/CloseTask(int taskId, [ {  Id, IsBreak, IsCompleted} ])
    private static final String CLOSE_TASK_PATH = "http://10.192.25.4:9190/mobile/CloseTask";
    /**
     * {"model":{"TaskId":26647,
     * "Services":[
     * {
     *  "ServiceTemplateId": int(число),
     *  "TarifId":int(число),
     *  "Quantity":int(число)}
     * ]}}

     */
    private static final String ADD_ADDITIONAL_TASK = "http://10.192.25.4:9190/mobile/AddServiceToTask";


    /**
     * get data for ConsumablesByTask
     */
    private static final String GET_CONSUMABLES_BY_TASK = "http://10.192.25.4:9190/mobile/ConsumablesByTask?id=";
    //http://10.192.25.4:9190/mobile/ConsumablesByTask?id=830


    /**
     * send data to server for AddConsumablesToTask
     */
    private static final String ADD_CONSUMABLES_TO_TASK_PATH = "http://10.192.25.4:9190/mobile/AddConsumablesToTask";

    /**
     * media type.
     */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Send request to server using url.
     *
     * @param url url for request.
     * @return json from server.
     */
    public String getDataFromUrl(final String url){
        String result = "";
        OkHttpGetJsonFromServer okHttpGetJsonFromServer = new OkHttpGetJsonFromServer();

        try {
            result = okHttpGetJsonFromServer.execute(url).get();
        } catch (InterruptedException e) {
            Log.e(TAG, "getDataFromUrl InterruptedException " + e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, "getDataFromUrl ExecutionException " + e.getMessage());
        } finally {
            okHttpGetJsonFromServer.cancel(true);
        }

        return result;
    }

    /**
     * Closing task on DetailTicketActivity.
     *
     * @param json get json object for close task.
     */
    public void closeTask(final String json){
        String result = "";
        OkHttpSendHandler handler = new OkHttpSendHandler();
        try {
            //result = handler.execute(CLOSE_TASK_PATH + "(" + task_id + "," + json + ")",json).get();
            result = handler.execute(CLOSE_TASK_PATH,json).get();
            Log.i(TAG, "closeTask Result " + result);
        } catch (InterruptedException e) {
            Log.e(TAG, "closeTask InterruptedException " + e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, "closeTask ExecutionException " + e.getMessage());
        }finally {
            handler.cancel(true);
        }
    }

    public void addServiceToTask(final String json){
        String result = "";
        OkHttpSendHandler handler = new OkHttpSendHandler();
        try {
            result = handler.execute(ADD_ADDITIONAL_TASK,json).get();
            Log.i(TAG, "addServiceToTask Result " + result);
        } catch (InterruptedException e) {
            Log.e(TAG, "addServiceToTask InterruptedException " + e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, "addServiceToTask ExecutionException " + e.getMessage());
        } finally {
            handler.cancel(true);
        }
    }

    public void addConsumablesToTask(final String json){
        String result = "";
        OkHttpSendHandler handler = new OkHttpSendHandler();
        try {
            result = handler.execute(ADD_CONSUMABLES_TO_TASK_PATH,json).get();
            Log.i(TAG, "addConsumablesToTask Result " + result);
        } catch (InterruptedException e) {
            Log.e(TAG, "addConsumablesToTask InterruptedException " + e.getMessage());
        } catch (ExecutionException e) {
            Log.e(TAG, "addServiceToTask ExecutionException " + e.getMessage());
        } finally {
            handler.cancel(true);
        }
    }

    class OkHttpSendHandler extends AsyncTask<String,String,String> {

        /**
         * Singleton client
         */
        private final OkHttpClient client = new OkHttpClient();

        private final String TAG = OkHttpSendHandler.class.getSimpleName();

        private final String BAD_REQUEST = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"\"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                "<HTML><HEAD><TITLE>Bad Request</TITLE>\n" +
                "<META HTTP-EQUIV=\"Content-Type\" Content=\"text/html; charset=us-ascii\"></HEAD>\n" +
                "<BODY><h2>Bad Request - Invalid URL</h2>\n" +
                "<hr><p>HTTP Error 400. The request URL is invalid.</p>\n" +
                "</BODY></HTML>";

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            //case 1
            RequestBody body = RequestBody.create(JSON,strings[1]);
            Log.i(TAG,"json " + strings[1]);
            Log.i(TAG,"url " + strings[0]);

            Request request = new Request.Builder()
                    .url(strings[0])
                    .post(body)
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                result = response.body().string();
                Log.i(TAG,"execute");
                /*
                if (result.compareTo(BAD_REQUEST)){
                    Log.i(TAG,"equals");
                    return "HTTP Error 400. The request URL is invalid.";
                }*/
            } catch (IOException e) {
                Log.e(TAG,"OkHttpHandler IOException: "+ e.getMessage());
            }

            if (result.equals(BAD_REQUEST)){
                Log.i(TAG,"equals");
                return "HTTP Error 400. The request URL is invalid.";
            } else {
                return result;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    class OkHttpGetJsonFromServer extends AsyncTask<String,String,String> {

        private final String TAG = OkHttpGetJsonFromServer.class.getSimpleName();

        private final OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... strings) {
            String result = "";

            RequestBody body = RequestBody.create(JSON,"");

            Request request = new Request.Builder()
                    .url(strings[0])
                    .post(body)
                    .build();

            Response response = null;

            try {
                response = client.newCall(request).execute();
                result = response.body().string();
                Log.i(TAG,"Result " + result);
            } catch (IOException e) {
                //e.printStackTrace();
                Log.e(TAG, "IOException " + e.getMessage());
            }

            return result;
        }
    }
}
