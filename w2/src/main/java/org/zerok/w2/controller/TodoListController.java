package org.zerok.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerok.w2.dto.TodoDTO;
import org.zerok.w2.service.TodoService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name="todoListController" , urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();

        Object object = servletContext.getAttribute("appName");

        log.info(object);

        try {
            List<TodoDTO> list = todoService.listAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req,resp);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new ServletException("list error");
        }

    }
}
