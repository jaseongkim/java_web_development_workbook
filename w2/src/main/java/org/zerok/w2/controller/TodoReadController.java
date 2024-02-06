package org.zerok.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerok.w2.dto.TodoDTO;
import org.zerok.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name="todoReadController" , urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long tno = Long.parseLong(req.getParameter("tno"));

        try {
            TodoDTO todoDTO = todoService.get(tno);
            req.setAttribute("todoDTO", todoDTO);

            Cookie viewTodoCookies = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookies.getValue();
            boolean exist = false;

            if(todoListStr != null && todoListStr.indexOf(tno+"-") >= 0) {
                exist = true;
            }

            if(!exist) {
                todoListStr += tno+"-";
                viewTodoCookies.setValue(todoListStr);
                viewTodoCookies.setPath("/");
                viewTodoCookies.setMaxAge(60*60*24);
                resp.addCookie(viewTodoCookies);
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);
        } catch (SQLException e) {
            log.info(e.getMessage());
            throw new ServletException("read error");
        }

    }
    private Cookie findCookie(Cookie[] cookies, String cookieName){

        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0) {
            for(Cookie ck : cookies){
                if(ck.getName().equals(cookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }

        if(targetCookie == null) {
            targetCookie = new Cookie(cookieName, "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }

        return targetCookie;

    }

}
