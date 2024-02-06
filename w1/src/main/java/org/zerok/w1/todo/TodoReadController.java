package org.zerok.w1.todo;

import org.zerok.w1.todo.dto.TodoDTO;
import org.zerok.w1.todo.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long tno = Long.parseLong(req.getParameter("tno"));

        TodoDTO dto = TodoService.INSTANCE.getTno(tno);

        req.setAttribute("dto",dto);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/todo/read.jsp");

        dispatcher.forward(req,resp);
    }

}
