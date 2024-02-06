package org.zerok.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerok.w2.dto.MemberDTO;
import org.zerok.w2.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
@Log4j2
public class LoginController extends HttpServlet {

    private MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("login get....");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/login.jsp");

        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String auto = req.getParameter("auto");

        boolean rememberMe = auto != null && auto.equals("on");

        try {
            MemberDTO memberDTO = memberService.Login(mid, mpw);
            if (rememberMe) {
                String uuid = UUID.randomUUID().toString();
                memberDTO.setUuid(uuid);

                memberService.updateUuid(uuid, mid);
                Cookie rememberCookie = new Cookie("remember-me", uuid);
                rememberCookie.setPath("/");
                rememberCookie.setMaxAge(60*60*27*7);
                resp.addCookie(rememberCookie);


            }

            log.info(memberDTO);
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/todo/list");

        } catch (Exception e) {
            resp.sendRedirect("/login?result=error");
        }

    }
}
