package com.otabi.scoutbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


/**
 * Created by Stephen on 11/15/2015.
 */
public class Authentication {

    protected static final String EMAIL_KEY = "scoutbook.email";
    protected static final String PASSWORD_KEY = "scoutbook.password";

    protected static Properties properties = new Properties();

    protected static final String RESOURCES_AUTHENTICATION_PROPERTIES = "authentication.properties";

    static {
        InputStream in = null;
        try {
            in = new FileInputStream(RESOURCES_AUTHENTICATION_PROPERTIES);
        } catch (FileNotFoundException e) {
        }

        if (in != null) {
            try {
                Authentication.properties.load(in);
                in.close();
            } catch (IOException e) {
                //log error
            }
        }
    }

    protected static void storeProperty(String key, String value) throws Exception {
        properties.setProperty(key, value);

        try {
            OutputStream out = new FileOutputStream(RESOURCES_AUTHENTICATION_PROPERTIES);
            properties.store(out, String.format("Setting %s to %s", key, value));
            out.close();
        } catch (IOException e) {
            throw new Exception("Unable to store properties", e);
        }
    }

    public static String getEmail() {
        return properties.getProperty(EMAIL_KEY);

    }

    public static void setEmail(String email) throws Exception {
        storeProperty(EMAIL_KEY, email);
    }

    public static String getPassword() {
        return properties.getProperty(PASSWORD_KEY);
    }

    public static void setPassword(String password) throws Exception {
        properties.setProperty(PASSWORD_KEY, password);
        // FIXME Optionally store ENCRYPTED password
        //storeProperty(PASSWORD_KEY, password);
    }
}
