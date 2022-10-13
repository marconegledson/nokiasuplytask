package com.nokia.suply.task.suplytask.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@Slf4j
@ShellComponent
public class MainMenuCommand {

    @ShellMethod(key = "st", value = "connect")
    public void connect(@ShellOption(value = "-s") String command) {
        log.info(String.format("Loggerd to machine %s", command));

    }
}
