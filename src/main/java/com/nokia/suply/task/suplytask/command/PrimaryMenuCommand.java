package com.nokia.suply.task.suplytask.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;

@Slf4j
@ShellComponent
public class PrimaryMenuCommand {

    public static void menu() {
        log.info("1: Data");
        log.info("2: Manufacturers");
        log.info("3: Company");
        log.info("4: Exit");
    }
}
