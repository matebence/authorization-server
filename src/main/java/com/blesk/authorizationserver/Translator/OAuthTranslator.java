package com.blesk.authorizationserver.Translator;

import com.blesk.authorizationserver.Exception.AuthorizationException;
import com.blesk.authorizationserver.Exception.OAuthException;
import com.blesk.authorizationserver.Value.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

public class OAuthTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception exception) {
        if (exception.getCause() instanceof AuthorizationException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new OAuthException(exception.getMessage()));
        } else if (exception instanceof InternalAuthenticationServiceException) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new OAuthException(Messages.INTERNAL_AUTH_EXCEPTION));
        } else if (exception instanceof InvalidGrantException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OAuthException(Messages.INVALID_GRANT_EXCEPTION));
        } else if (exception instanceof UnsupportedGrantTypeException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OAuthException(Messages.UNSUPPORTED_GRANT_EXPCEPTION));
        } else if (exception instanceof InvalidRequestException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OAuthException(Messages.INVALID_REQUEST_EXCEPTION));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new OAuthException(Messages.OAUTH_EXPCETION));
    }
}