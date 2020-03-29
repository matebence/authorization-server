package com.blesk.authorizationserver.Values;

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
    public static final String PAGINATION_EXCEPTION = "Stránkovanie nie je nastavené";
    public static final String BLOCKED_EXCEPTION = "Účet bol zablokovaný na 24h";
    public static final String LOGOUT_EXCEPTION = "Pri odhlásení nastala chyba";

    public static final String CREATE_ROLE = "Vytvorenie roli sa nepodarilo";
    public static final String DELETE_GET_ROLE = "Ľutujeme, ale rola neexistuje";
    public static final String DELETE_ROLE = "Odstránenie roli sa nepodarilo";
    public static final String UPDATE_ROLE = "Rolu sa nepodarilo aktualizovať";
    public static final String GET_ROLE = "Ľutujeme, rola neexistuje";
    public static final String GET_ALL_ROLES = "Nenašiel sa žiadna rola";
    public static final String GET_ROLE_BY_NAME = "Ľutujeme, rola neexistuje";
    public static final String GET_ROLE_PRIVILEGES = "Rola %s nemá žiadné práva";

    public static final String CREATE_PREFERENCE = "Vytvorenie preferencie sa nepodarilo";
    public static final String DELETE_GET_PREFERENCE = "Ľutujeme, ale preferencia neexistuje";
    public static final String DELETE_PREFEREBCE = "Odstránenie preferencie sa nepodarilo";
    public static final String UPDATE_PREFEREBCE = "Preferenciu sa nepodarilo aktualizovať";
    public static final String GET_PREFERENCE = "Ľutujeme, preferencia neexistuje";
    public static final String GET_ALL_PREFERENCES = "Nenašiel sa žiadné preferencie";
    public static final String GET_PREFERENCE_BY_NAME = "Ľutujeme, preferencia neexistuje";

    public static final String CREATE_PRIVILEGE = "Vytvorenie práva sa nepodarilo";
    public static final String DELETE_GET_PRIVILEGE = "Ľutujeme, ale právo neexistuje";
    public static final String DELETE_PRIVILEGE = "Odstránenie práva sa nepodarilo";
    public static final String UPDATE_PRIVILEGE = "Právo sa nepodarilo aktualizovať";
    public static final String GET_PRIVILEGE = "Ľutujeme, právo neexistuje";
    public static final String GET_ALL_PRIVILEGES = "Nenašiel sa žiadné práva";
    public static final String GET_PRIVILEGE_BY_NAME = "Ľutujeme, právo neexistuje";

    public static final String CREATE_GET_ACCOUNT = "Požadovaná rola pre vytvorenie nového úctu sa nenašla";
    public static final String CREATE_ACCOUNT = "Nepodarilo sa vytvoriť nový účet";
    public static final String DELETE_GET_ACCOUNT = "Ľutujeme, ale účet nebol nájdení";
    public static final String DELETE_ACCOUNT = "Odstránenie účtu bolo neúspešné";
    public static final String UPDATE_ACCOUNT = "Aktualizovanie účtu bolo neúspešné";
    public static final String GET_ACCOUNT = "Ľutujeme, účet neexistuje";
    public static final String GET_ALL_ACCOUNTS = "Nenašiel sa žiadný účet";
    public static final String GET_ACCOUNT_INFORMATION = "Ľutujeme, účet neexistuje";
    public static final String GET_ROLES_TO_ACCOUNT = "Ľutujeme, chyba účtu";
    public static final String SEARCH_FOR_ACCOUNT = "Kritériám nevyhoveli žiadné záznamy";

    public static final String ENTITY_IDS = "Nesprávny formát identifikačného čísla";
    public static final String ENTITY_CREATOR_ID = "Identifikačné číslo vytvárajúcého používatela nebol nastavení";
    public static final String ACCOUNTS_USER_NAME_NULL = "Nezadali ste používatelské meno";
    public static final String ACCOUNTS_USER_NAME_LENGHT = "Používatelské meno je príliž krátké alebo dlhé";
    public static final String ACCOUNTS_PASSWORD = "Nezadali ste heslo";
    public static final String ACCOUNTS_BALANCE_POSITIVE = "Stav účtu nemože klesnút pod 0";
    public static final String ACCOUNTS_BALANCE_MAX = "Maximalný stav úctu je 10000€";

    public static final String PRIVILEGES_NOT_NULL = "Nezadali ste názov práva";
    public static final String PRIVILEGES_SIZE = "Názov práva je príliž krátké alebo dlhé";

    public static final String ROLES_NOT_NULL = "Nezadali ste meno role";
    public static final String ROLES_SIZE = "Meno roli je príliž krátké alebo dlhé";

    public static final String PREFERENCES_NOT_NULL = "Nezadali ste názov preferencie";
    public static final String PREFERENCES_SIZE = "Názov preferencie je príliž krátké alebo dlhé";
}