package com.sp3.chapter17.app.user;

import com.sp3.chapter17.util.api.ResultJson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jwt")
public class JwtController {
    @GetMapping("user/welcome")
    public ResultJson<Object> welcome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));
        return ResultJson.success("welcome");
    }


    @PostMapping("/user/login")
    public ResultJson<Object> login(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        if ("18937166730".equals(mobile) && "123456".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("uid", 1);
            session.setAttribute("username", "Hui Lee");
            return ResultJson.success("welcome");
        } else {
            return ResultJson.failed("账户或密码错误,请检查");
        }
    }

    @GetMapping("user/info")
    public ResultJson<Object> info(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        model.addAttribute("uid", session.getAttribute("uid"));
        model.addAttribute("username", session.getAttribute("username"));
        return ResultJson.success(model.asMap());
    }

    @GetMapping("user/logout")
    public ResultJson<Object> logout(HttpSession session) {
        session.invalidate();
        return ResultJson.unauthorized("您已经退出登录");
    }


}
