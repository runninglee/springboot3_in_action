package com.sp3.chapter15.console.system;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

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
}
