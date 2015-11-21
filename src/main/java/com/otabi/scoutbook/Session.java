package com.otabi.scoutbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 11/14/2015.
 * <p>
 * Some thoughts here. This session makes use of thread local instances so that each thread
 * has it's own web request capability. However the cookies are kept statically so each thread is
 * not required to log in separately.
 * <p>
 * It's possible to have simultaneous sessions for multiple accounts, but the login process is
 * synchronized globally.
 */
public class Session {
    protected final static Logger staticLogger = LoggerFactory.getLogger(Session.class.getName());
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String CHARSET = StandardCharsets.UTF_8.name();

    private static ThreadLocal<Map<String, Session>> instance;

    static Map<String, Map<String, String>> cookies = new HashMap<String, Map<String, String>>();
    static boolean loggedIn = false;
    protected String email;

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
            ReentrantLock loginLock = new ReentrantLock();
            loginLock.lock();
            try {
                if (!loggedIn) {
                    session.login(email, password);
                    loggedIn = true;
                }
            } catch (Exception e) {
                staticLogger.error("Login error", e);
                throw new Exception("Unable to login to Scoutbook.", e);
            } finally {
                loginLock.unlock();
            }
            session.setEmail(email);
            instance.get().put(email, session);
        }
        return instance.get().get(email);
    }

    public void login(String email, String password) throws Exception {
        logger.info("Login for {} requested.", email);
        Map<String, String> sessionCookies = cookies.get(this.email);
        URL url;
        HttpURLConnection connection;

        if (sessionCookies == null) {
            sessionCookies = new HashMap<String, String>();
        }
        url = URLFactory.getHomepage();
        connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        String headerName;
        for (int i = 1; (headerName = connection.getHeaderFieldKey(i)) != null; i++) {
            if (headerName.equals("Set-Cookie")) {
                String cookie = connection.getHeaderField(i);
                sessionCookies.put(cookie.substring(0, cookie.indexOf("=")), cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";")));
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

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            logger.info("Unexpected HTML status {} while logging in.", responseCode);
            throw new Exception(connection.getResponseMessage());
        }

        Pattern error = Pattern.compile("alert\\('(.*?)\\\\n'\\)");
        Matcher errorMatcher = error.matcher(content);
        if (errorMatcher.find()) {
            String message = errorMatcher.group(1);
            logger.info("Likely permissions error {} when logging in.", message);
            throw new Exception(message);
        }

        for (int i = 1; (headerName = connection.getHeaderFieldKey(i)) != null; i++) {
            if (headerName.equals("Set-Cookie")) {
                String cookie = connection.getHeaderField(i);
                sessionCookies.put(cookie.substring(0, cookie.indexOf("=")), cookie.substring(cookie.indexOf("=") + 1, cookie.indexOf(";")));
            }
        }

    }

    protected String getCookies() {
        Map<String, String> sessionCookies = cookies.get(this.email);
        if (cookies == null || sessionCookies == null) {
            logger.debug("No cookies set.");
            return "";
        }

        StringBuilder cookieBuilder = new StringBuilder();
        for (String key : sessionCookies.keySet()) {
            if (cookieBuilder.length() > 0) cookieBuilder.append("; ");
            cookieBuilder.append(key);
            cookieBuilder.append("=");
            cookieBuilder.append(sessionCookies.get(key));
        }
        return cookieBuilder.toString();
    }

    public String getContent(URL url) throws Exception {
        logger.info("Making http request for {}", url.toString());
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

    public void setEmail(String email) {
        this.email = email;
    }
}
