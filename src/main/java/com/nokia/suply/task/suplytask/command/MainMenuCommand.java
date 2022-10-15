package com.nokia.suply.task.suplytask.command;

import com.nokia.suply.task.suplytask.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@Slf4j
@ShellComponent
public class MainMenuCommand extends AbstractShellComponent  {

    @Autowired
    private CompanyService companyService;

    public MainMenuCommand(){

    }

    @ShellMethod(key = "st", value = "connect")
    public void connect(@ShellOption(value = "-s") String command) throws InterruptedException, IOException {
        companyService.addCompany();
        log.info(String.format("Loggerd to machine %s", command));
        PrimaryMenuCommand.menu();
    }

}
