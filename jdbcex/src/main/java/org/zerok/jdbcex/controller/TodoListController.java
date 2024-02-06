package org.zerok.jdbcex.controller;

import lombok.extern.log4j.Log4j2;
import org.zerok.jdbcex.dao.TodoDAO;
import org.zerok.jdbcex.domain.TodoVO;
import org.zerok.jdbcex.dto.TodoDTO;
import org.zerok.jdbcex.service.TodoService;

import javax.servlet.RequestDispatcher;
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
