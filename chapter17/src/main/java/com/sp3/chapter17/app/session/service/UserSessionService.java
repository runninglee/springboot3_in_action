package com.sp3.chapter17.app.session.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Map;


@Service
public class UserSessionService {

    public String login(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        if ("18812345678".equals(mobile) && "123456".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", Map.of("id", 1, "username", "Spring Boot3"));
            return "redirect:/session/user/welcome";
        } else {
            return "login";
        }
    }

    public String info(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "info";
    }

    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/session/user/login";
    }
}
