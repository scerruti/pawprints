package com.otabi.scoutbook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 11/14/2015.
 */
public class Session {

    private static ThreadLocal<Map<String, Session>> instance;

    public static Session getInstance() throws Exception {
        String email = Authentication.getEmail();
        String password = Authentication.getPassword();

        return getInstance(email, password);
    }

    public static Session getInstance(String email, String password) throws Exception {
        if (instance == null) {
            instance = new ThreadLocal<Map<String, Session>>();
        }
        if (instance.get() == null) {
            instance.set(new HashMap<String, Session>());
        }
        if (!instance.get().containsKey(email)) {
            Session session = new Session();
            try {
                session.login(email, password);
            } catch (Exception e) {
                throw new Exception("Unable to login to Scoutbook.", e);
            }
            instance.get().put(email, session);
        }
        return instance.get().get(email);
    }

    public static Session getInstance(String email) throws Exception {
        if (instance == null) {
            instance = new ThreadLocal<Map<String, Session>>();
            instance.set(new HashMap<String, Session>());
        }
        if (!instance.get().containsKey(email)) {
            throw new Exception(String.format("No session for %s found.", email));
        }
        return instance.get().get(email);
    }

    private static final String CHARSET = StandardCharsets.UTF_8.name();
    static Map<String, String> cookies = new HashMap<String, String>();

    public void login(String email, String password) throws Exception {

        URL url = null;
        HttpURLConnection connection;

        url = URLFactory.getHomepage();
        connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        String headerName = null;
        for (int i = 1; (headerName = connection.getHeaderFieldKey(i)) != null; i++) {
            if (headerName.equals("Set-Cookie")) {
                String cookie = connection.getHeaderField(i);
                cookies.put(cookie.substring(0, cookie.indexOf("=")), cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";")));
            }
        }

        connection.disconnect();

        url = URLFactory.getLogin();
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", CHARSET);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);

        String cookieString = getCookies();
        if (!cookieString.isEmpty()) {
            connection.setRequestProperty("Cookie", cookieString);
        }

        String query = String.format("Action=Login&Email=%s&Password=%s",
                URLEncoder.encode(email, CHARSET),
                URLEncoder.encode(password, CHARSET));

        OutputStream output = connection.getOutputStream();
        output.write(query.getBytes(CHARSET));


        connection.connect();

        // wrap the urlconnection in a bufferedreader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;

        // read from the urlconnection via the bufferedreader
        StringBuilder content = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if (content.length() > 0) content.append("\n");
            content.append(line);
        }
        bufferedReader.close();

        if (connection.getResponseCode() != 200) throw new Exception(content.toString());

        Pattern error = Pattern.compile("alert\\('(.*?)\\\\n'\\)");
        Matcher errorMatcher = error.matcher(content);
        if (errorMatcher.find()) {
            throw new Exception(errorMatcher.group(1));
        }

        for (int i = 1; (headerName = connection.getHeaderFieldKey(i)) != null; i++) {
            if (headerName.equals("Set-Cookie")) {
                String cookie = connection.getHeaderField(i);
                cookies.put(cookie.substring(0, cookie.indexOf("=")), cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";")));
            }
        }

    }

    protected static String getCookies() {
        StringBuilder cookieBuilder = new StringBuilder();
        if (cookies != null) {
            for (String key : cookies.keySet()) {
                if (cookieBuilder.length() > 0) cookieBuilder.append("; ");
                cookieBuilder.append(key);
                cookieBuilder.append("=");
                cookieBuilder.append(cookies.get(key));
            }
        }
        return cookieBuilder.toString();
    }

    public static String getContent(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", CHARSET);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);

        String cookieString = getCookies();
        if (!cookieString.isEmpty()) {
            connection.setRequestProperty("Cookie", cookieString);
        }

        // read from the urlconnection via the bufferedreader
        StringBuilder content = new StringBuilder();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((line = bufferedReader.readLine()) != null) {
            if (content.length() > 0) content.append("\n");
            content.append(line);
        }
        bufferedReader.close();

        if (connection.getResponseCode() != 200) throw new Exception(content.toString());

        return content.toString();
    }
}
