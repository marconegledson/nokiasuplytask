package com.nokia.suply.task.suplytask.command;

import com.nokia.suply.task.suplytask.service.ManufacturerService;
import com.nokia.suply.task.suplytask.service.PartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.io.IOException;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class SecondaryMenuCommand  {

    private final PartService partService;
    private final ManufacturerService manufacturerService;

    /**
     * Exemplo de chamada no console:
     * shell:> 1.2 ou 2.2 ou 3.2 ou 4.2
     * */
    @ShellMethod(key = "1", value = "1")
    public static void menu() {
        log.info("1.2: Add part");
        log.info("2.2: Add manufacturer");
        log.info("3.2: Remove manufacturer");
        log.info("4.2: Back");
    }

    /**
     * Exemplo de chamada no console:
     * shell:> 1.3 --name part-1 ou simplesmente 1.3 part-1
     * */
    @ShellMethod(key = "1.2", value = "1.2")
    public void addPart(String name) {
        partService.addPart(name);
    }

    /**
     * Exemplo de chamada no console:
     * shell:> 2.2 --name manufaturer-1 ou simplesmente 2.2 manufaturer-1
     * */
    @ShellMethod(key = "2.2", value = "2.2")
    public void addManuFacturer(String name) {
        manufacturerService.addManufacturer(name);
    }

    @ShellMethod(key = "3.2", value = "3.2")
    public void deleteManufacturer(String name) {
        manufacturerService.deleteManufacturer(name);
    }

    @ShellMethod(key = "4.2", value = "4.2")
    public void back() throws IOException {
        PrimaryMenuCommand.menu();
    }

}
