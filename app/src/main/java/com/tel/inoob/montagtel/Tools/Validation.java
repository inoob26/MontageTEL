package com.tel.inoob.montagtel.Tools;

import android.widget.TextView;

/**
 * Validation_test class.
 * @author inoob
 */
public class Validation {
    private WebClient webClient;

    public String checkLoginForm(final String login,final String password,TextView view){
        webClient = new WebClient("http://10.192.25.4:9190/mobile/login?login=" + login + "&password=" + password);
        return webClient.getJSON();
    }
}
