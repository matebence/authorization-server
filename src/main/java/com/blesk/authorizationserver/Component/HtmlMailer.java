package com.blesk.authorizationserver.Component;

import java.util.Map;

public interface HtmlMailer {

    String generateMailHtml(String text, String htmlfile,  Map<String, Object> variables);
}