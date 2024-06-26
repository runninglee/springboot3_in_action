package com.sp3.chapter17.app.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("session")
public class SessionController {

    @GetMapping("user/welcome")
    public String welcome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));
        return "welcome";
    }

    @GetMapping("user/login")
    public String getLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("uid") != null) {
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
            session.setAttribute("uid", 1);
            session.setAttribute("username", "Hui Lee");
            return "redirect:/session/user/welcome";
        } else {
            return "login";
        }
    }

    @GetMapping("user/info")
    public String info(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));
        return "info";
    }

    @GetMapping("user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/session/user/login";
    }


}
