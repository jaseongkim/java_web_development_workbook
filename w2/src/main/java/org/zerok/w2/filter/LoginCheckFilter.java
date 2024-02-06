package org.zerok.w2.filter;

import lombok.extern.log4j.Log4j2;
import org.zerok.w2.dto.MemberDTO;
import org.zerok.w2.service.MemberService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginCheckFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("Login check filter");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        HttpSession session = req.getSession();

        if(session.getAttribute("loginInfo") != null) {
            chain.doFilter(request, response);
            return;
        }

        Cookie cookie = findCookie(req.getCookies(),"remember-me");

        if (cookie == null) {
            resp.sendRedirect("/login");
            return;
        }

        String uuid = cookie.getValue();

        log.info(uuid);

        try {

            MemberDTO memberDTO = MemberService.INSTANCE.getByUuid(uuid);

            if(memberDTO == null){
                throw new Exception("Cookie value is not valid");
            }
            session.setAttribute("loginInfo", memberDTO);
            chain.doFilter(request,response);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/login");
        }


    }

    private Cookie findCookie(Cookie[] cookies, String cookieName) {

        if(cookies == null || cookies.length == 0 ){
            return null;
        }
        Optional<Cookie> result = Arrays.stream(cookies).filter(ck->ck.getName().equals(cookieName)).findFirst();

        return result.isPresent() ? result.get():null;
    }
}
