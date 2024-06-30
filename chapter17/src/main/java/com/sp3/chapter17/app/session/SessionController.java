package com.sp3.chapter17.app.session;

import com.sp3.chapter17.app.session.service.UserSessionService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("session")
public class SessionController {

    @Resource
    private UserSessionService userService;

    @GetMapping("user/welcome{path}")
    public String welcome(HttpServletRequest request, @MatrixVariable(value = "SP3", pathVar = "path") String sp3, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("sp3", sp3);
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
        return userService.login(request);
    }

    @GetMapping("user/info")
    public String info(HttpServletRequest request, Model model) {
        return userService.info(request, model);
    }

    @GetMapping("user/logout")
    public String logout(HttpSession session) {
        return userService.logout(session);
    }
}
