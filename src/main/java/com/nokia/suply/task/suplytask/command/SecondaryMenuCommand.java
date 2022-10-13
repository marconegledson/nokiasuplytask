package com.nokia.suply.task.suplytask.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@Slf4j
@ShellComponent
public class SecondaryMenuCommand  {

    @ShellMethod(key = "1", value = "1")
    public static void menu() {
        log.info("1: Add part");
        log.info("2: Add manufacturer");
        log.info("3: Remove manufacturer");
        log.info("4: Back");
    }

    @ShellMethod(key = "4", value = "4")
    public void back() throws IOException {
        PrimaryMenuCommand.menu();
    }

    @ShellMethod(key = "2", value = "2")
    public void addManuFacturer() {
        //TODO: Chamar o serviço de cadastrar Fabricante
    }

    public void addPart(String name) {
        log.info("add new part:");
        //TODO: chamar o serviço de cadastrar peça
    }

    public void deleteManufacturer() {
        //TODO: chamar o serviço de deletar fabricante
    }

}
