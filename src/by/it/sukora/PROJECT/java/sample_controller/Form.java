package by.it.sukora.PROJECT.java.sample_controller;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * Created by Sukora Stas.
 */
public class Form {
    static String getParameter(HttpServletRequest req, String name, String pattern)
            throws ParseException {
        String value = req.getParameter(name);
        if ((value != null) && value.matches(pattern)) {
            return value;
        } else {
            throw new ParseException("Incorecct String " + name, 0);
        }
    }

    static boolean isPost(HttpServletRequest req) {
        return req.getMethod().equalsIgnoreCase("post");
    }

    static void showMessage(HttpServletRequest req, String message) {
        req.setAttribute(Message.MESSAGE, message);
    }

    static void showError(HttpServletRequest req, String error) {
        req.setAttribute(Message.MESSAGE_ERROR, error);
    }
}
