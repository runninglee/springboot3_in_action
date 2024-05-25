package com.sp3.chapter5.app.config;

import com.sp3.chapter5.config.TeacherPropertyConfig;
import com.sp3.chapter5.config.TeacherYmlConfig;
import com.sp3.chapter5.util.api.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Resource
    private TeacherPropertyConfig teacherPropertyConfig;

    @Resource
    private TeacherYmlConfig teacherYmlConfig;

    @Value("${app.name}")
    private String name;

    @Value("${app.author:magura}")
    private String author;

    @Value("${HOME}")
    private String home;

    @GetMapping("app-param")
    public ResultJson<Object> app() {
        return ResultJson.success(name);
    }

    @GetMapping("system-param")
    public ResultJson<Object> system() {
        return ResultJson.success(home);
    }

    @GetMapping("default-param")
    public ResultJson<Object> author() {
        return ResultJson.success(author);
    }

    @GetMapping("custom-property")
    public ResultJson<Object> property() {
        return ResultJson.success(teacherPropertyConfig.getStudents());
    }

    @GetMapping("custom-yml")
    public ResultJson<Object> yml() {
        return ResultJson.success(teacherYmlConfig.getStudents());
    }
}
