package com.blesk.authorizationserver.Handler;

import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Exception.OAuth2Exception;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

public class OAuth2Handler implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<org.springframework.security.oauth2.common.exceptions.OAuth2Exception> translate(Exception exception) {
        if (exception.getCause() instanceof AuthorizationException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new OAuth2Exception(exception.getMessage()));
        } else if (exception instanceof InternalAuthenticationServiceException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new OAuth2Exception(Messages.INTERNAL_AUTH_EXCEPTION));
        } else if (exception instanceof InvalidGrantException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OAuth2Exception(Messages.INVALID_GRANT_EXCEPTION));
        } else if (exception instanceof UnsupportedGrantTypeException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OAuth2Exception(Messages.UNSUPPORTED_GRANT_EXPCEPTION));
        } else if (exception instanceof InvalidRequestException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OAuth2Exception(Messages.INVALID_REQUEST_EXCEPTION));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new OAuth2Exception(Messages.OAUTH_EXPCETION));
    }
}