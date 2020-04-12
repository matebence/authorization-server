package com.blesk.authorizationserver.Value;

public class Messages {

    public static final String TYPE_MISMATCH_EXCEPTION = "Nesprávný formát URL adresi";
    public static final String REQUEST_BODY_NOT_FOUND_EXCEPTION = "Prázdna požiadavka";
    public static final String PAGE_NOT_FOUND_EXCEPTION = "Je nám ľúto, ale požadovaná stránka nebola nájdená";
    public static final String NULL_POINTER_EXCEPTION = "Ľutujeme, ale nastala chyba";
    public static final String INTERNAL_AUTH_EXCEPTION = "Neúspešné prihlásenie";
    public static final String INVALID_GRANT_EXCEPTION = "Nesprávne prihlasovacie údaje";
    public static final String UNSUPPORTED_GRANT_EXPCEPTION = "Chyba pri prihlásení";
    public static final String INVALID_REQUEST_EXCEPTION = "Chyba pri prihlásení";
    public static final String AUTH_REQUIRED_EXCEPTION = "Prístup odmietnutý";
    public static final String OAUTH_EXPCETION = "Lutujeme, ale nastala chyba";
    public static final String SQL_EXCEPTION = "Operácia sa neuskutočnila";
    public static final String EXCEPTION = "Nastala neočakávaná chyba";
    public static final String BLOCKED_EXCEPTION = "Účet bol zablokovaný na 24h";
    public static final String LOGOUT_EXCEPTION = "Pri odhlásení nastala chyba";

    public static final String ACCOUNT_VERIFICATION_ERROR = "Ľutujeme, účet neexistuje";
    public static final String ACCOUNT_REGISTRATION_ERROR = "Vytvorenie účtu sa nepodarilo";
    public static final String ACCOUNT_EMAIL_RECOVERY_ERROR = "Ľutujeme, emailová adresa neexistuje";
    public static final String RESET_PASSWORD_TOKEN_ERROR = "Ľutujeme, kľúč pre zabudnuté heslo je neplatný";
    public static final String LOGIN_DETAILS_RECORD_ERROR = "Aktualizovanie prihlasovacieho záznamu sa nepodarilo";

    public static final String SERVER_ERROR = "Server je momentálne nedostupný";
    public static final String SIGNOUT_SUCCESS = "Boli ste úspešne odhlásený";
    public static final String SIGNUP_SUCCESS = "Registrácia prebehla úspešne, na Vami zvolenú emailovú adresu sme poslali aktivačný odkaz";
    public static final String SIGNUP_ERROR = "Registrácia bola neúspešná, skúste znova";
    public static final String FORGETPASSWORD_SUCCESS = "Postup obnovenia hesla Vám bol zaslaný na uvedenú emailovú adresu";
    public static final String FORGETPASSWORD_ERROR = "Pri postupe obnoveniu hesla nastala chyba";
}