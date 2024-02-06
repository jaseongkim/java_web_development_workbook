package org.zerok.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerok.w2.dto.TodoDTO;
import org.zerok.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name="todoModifyController", urlPatterns = "/todo/modify")
public class TodoModifyController extends HttpServlet {

    private TodoService todoService = TodoService.INTANCE;
    private final DateTimeFormatter DATEFOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        long tno = Long.parseLong(req.getParameter("tno"));

        try {
            TodoDTO todoDTO = todoService.get(tno);
            req.setAttribute("todoDTO", todoDTO);
            req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req,resp);

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new ServletException("modify GET error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String finishedStr = req.getParameter("finished");

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(Long.parseLong(req.getParameter("tno")))
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFOMATTER))
                .finished(finishedStr != null && finishedStr.equals("on"))
                .build();

        try {
            todoService.modify(todoDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException("modify POST error");
        }

        resp.sendRedirect("/todo/list");


    }
}