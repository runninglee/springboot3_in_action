package com.sp3.chapter17.app.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("session")
public class SessionController {

    @GetMapping("user/welcome")
    public String welcome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "welcome";
    }

    @GetMapping("user/login")
    public String getLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/session/user/welcome";
        } else {
            return "login";
        }
    }

    @PostMapping("/user/login")
    public String postLogin(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        if ("18937166730".equals(mobile) && "123456".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", Map.of("id", 1, "username", "HuiLee"));
            return "redirect:/session/user/welcome";
        } else {
            return "login";
        }
    }

    @GetMapping("user/info")
    public String info(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "info";
    }

    @GetMapping("user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/session/user/login";
    }


}
