package org.zerok.jdbcex.controller;

import lombok.extern.log4j.Log4j2;
import org.zerok.jdbcex.dto.TodoDTO;
import org.zerok.jdbcex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);
        } catch (SQLException e) {
            log.info(e.getMessage());
            throw new ServletException("read error");
        }


    }
}