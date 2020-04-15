package com.blesk.authorizationserver.Exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.sql.Timestamp;

@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class OAuth2Exception extends org.springframework.security.oauth2.common.exceptions.OAuth2Exception {
    public OAuth2Exception(String msg) {
        super(msg);
    }
}

class OAuth2ExceptionSerializer extends StdSerializer<OAuth2Exception> {

    public OAuth2ExceptionSerializer() {
        super(OAuth2Exception.class);
    }

    @Override
    public void serialize(OAuth2Exception value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        jsonGenerator.writeStringField("message", value.getMessage());
        jsonGenerator.writeBooleanField("error", true);
        jsonGenerator.writeEndObject();
    }
}