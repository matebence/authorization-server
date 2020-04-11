package com.blesk.authorizationserver.Exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.IOException;
import java.sql.Timestamp;

@JsonSerialize(using = OAuthExceptionSerializer.class)
public class OAuthException extends OAuth2Exception {
    public OAuthException(String msg) {
        super(msg);
    }
}

class OAuthExceptionSerializer extends StdSerializer<OAuthException> {

    public OAuthExceptionSerializer() {
        super(OAuthException.class);
    }

    @Override
    public void serialize(OAuthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("timestamp", new Timestamp(System.currentTimeMillis()).toString());
        jsonGenerator.writeStringField("message", value.getMessage());
        jsonGenerator.writeBooleanField("error", true);
        jsonGenerator.writeEndObject();
    }
}