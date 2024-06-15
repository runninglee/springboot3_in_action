package com.sp3.chapter15.console.system;

import com.sp3.chapter15.util.progress.ProgressBar;
import jakarta.validation.constraints.Positive;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@ShellCommandGroup("system:")
public class SystemCommand {

    @ShellMethod(value = "系统初始化", key = "system:init")
    public void init() {
        System.out.println("系统初始化");
    }

    @ShellMethod(value = "系统初始化权限", key = "system:permission")
    public void permission() {
        System.out.println("系统初始化权限");
    }

    @ShellMethod(value = "统计不同用户状态下的活跃人数", key = "system:user:count")
    public void userCount(@ShellOption(value = {"-u"}, defaultValue = "2", help = "用户ID") Long user_id,
                          @ShellOption(value = {"-s"}, defaultValue = "正式", help = "用户状态") String status) {
        System.out.println("当前传入用户 ID为:" + user_id + ", 当前用户状态为:" + status);
    }

    @ShellMethod(value = "系统对用户进行角色授权", key = "system:user:role")
    public void userRole(@ShellOption(value = {"-u"}) @Positive(message = "用户ID必须为有效数字") Long user_id,
                         @ShellOption(value = {"-r"}) @Positive(message = "用户角色ID必须为有效数字") Long role_id) {
        System.out.println("当前传入用户ID为:" + user_id + ", 当前传入角色ID为:" + role_id);
    }

    @ShellMethod(value = "打印进度条", key = "system:user:progress")
    public void userProgress() throws InterruptedException {
        int totalSteps = 2000;
        ProgressBar progressBar = new ProgressBar(totalSteps);
        for (int i = 0; i <= totalSteps; i++) {
            progressBar.updateProgress(i);
            Thread.sleep(50);
        }
    }


}
