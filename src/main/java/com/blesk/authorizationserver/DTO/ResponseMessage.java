package com.blesk.authorizationserver.DTO;

import java.util.HashMap;

public class ResponseMessage {

    private HashMap<String, String> nav;

    public ResponseMessage(HashMap<String, String> nav) {
        this.nav = nav;
    }

    public ResponseMessage() {
    }

    public HashMap<String, String> getNav() {
        return nav;
    }

    public void setNav(HashMap<String, String> nav) {
        this.nav = nav;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "nav=" + nav +
                '}';
    }
}
