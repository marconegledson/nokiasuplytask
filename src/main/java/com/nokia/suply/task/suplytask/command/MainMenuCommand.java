package com.nokia.suply.task.suplytask.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@Slf4j
@ShellComponent
public class MainMenuCommand extends AbstractShellComponent  {

    @ShellMethod(key = "st", value = "connect")
    public void connect(@ShellOption(value = "-s") String command) throws InterruptedException, IOException {
        log.info(String.format("Loggerd to machine %s", command));
        PrimaryMenuCommand.menu();
    }

}
