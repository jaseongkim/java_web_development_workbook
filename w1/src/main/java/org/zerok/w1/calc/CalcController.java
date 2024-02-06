package org.zerok.w1.calc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="CalcController" ,urlPatterns = "/calcmakeresult")
public class CalcController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name1 = req.getParameter("name1");
        String name2 = req.getParameter("name2");

        int sum = Integer.parseInt(name1) + Integer.parseInt(name2);

        System.out.println(name1);
        System.out.println(name2);
        System.out.println(sum);

        resp.sendRedirect("/index");


    }
}
