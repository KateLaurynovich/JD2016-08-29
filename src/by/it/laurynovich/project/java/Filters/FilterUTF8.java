package by.it.laurynovich.project.java.Filters;

import by.it.laurynovich.project.java.controller.Messages;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class FilterUTF8 implements Filter {
    private String code;

    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //пример фильтра ПЕРЕД сервлетом.
        String codeResponse = response.getCharacterEncoding();
        // установка кодировки ответа из параметров фильтра, если не установлена
        if (code != null && !code.equalsIgnoreCase(codeResponse)) {
            response.setCharacterEncoding(code);
        }

        String codeRequest = request.getCharacterEncoding();
        // установка кодировки запроса из параметров фильтра, если не установлена
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
        }

        //пример обращения к сессии
        //Приведение запроса request к http
        HttpServletRequest req=(HttpServletRequest)request;

        //получение сессии
        HttpSession session=req.getSession(true);
        //session.setAttribute(Messages.msgMessage,"Это сообщение из фильтра");
        //обработка прочих фильтров сервлетов и jsp
        chain.doFilter(request, response);

        //тут можно изменить/прочитать выходной поток, но учтите, он уже создан и заполнен.
    }

    public void destroy() {
        code = null;
    }
}