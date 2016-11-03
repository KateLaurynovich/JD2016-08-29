package by.it.akhmelev.project.java.controller;

import by.it.akhmelev.project.java.beans.User;
import by.it.akhmelev.project.java.dao.DAO;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class CmdLogin extends Action {
    @Override
    Action execute(HttpServletRequest req) {
        if (Form.isPost(req)) {
            User user = new User();
            try {
                user.setLogin(Form.getParameter(req, "Login", Patterns.LOGIN));
                user.setPassword(Form.getParameter(req, "Password", Patterns.PASSWORD));
                DAO dao = DAO.getDAO();
                List<User> users = dao.user.getAll(
                        String.format("WHERE Login='%s' and Password='%s' LIMIT 0,1",
                                user.getLogin(),
                                user.getPassword()
                        ));
                if (users.size() == 1) {
                    return Actions.LOGOUT.action;
                } else {
                    req.setAttribute(Messages.MESSAGE_ERROR, "USER NOT FOUND");
                }
                ;
            } catch (ParseException e) {
                req.setAttribute(Messages.MESSAGE_ERROR, "Incorrect data");
                return null;
            }
        }
        return null;
    }
}