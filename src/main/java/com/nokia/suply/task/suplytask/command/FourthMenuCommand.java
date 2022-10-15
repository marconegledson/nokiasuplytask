package com.nokia.suply.task.suplytask.command;

import com.nokia.suply.task.suplytask.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class FourthMenuCommand {

    private final CompanyService companyService;

    /**
     * Exemplo de chamada no console:
     * shell:> 1.4 ou 2.4 ou 3.4 ou 4.4
     * */
    @ShellMethod(key = "3", value = "3")
    public void menu() {
        companyService.addCompany();
        log.info("1.4: Add money");
        log.info("2.4: Buy parts");
        log.info("3.4: List parts");
        log.info("4.4: Back");
    }


    /**
     * Exemplo de chamada no console:
     * shell:> 1.4 --money 50.5
     * */
    @ShellMethod(key = "1.4", value = "1.4")
    public void addMoney(BigDecimal money) {
        companyService.addCompany();
        companyService.addMoney(money);
    }

    /**
     * Exemplo de chamada no console:
     * shell:> 1.3 --partName part-1 --quantity 8
     * */
    @ShellMethod(key = "2.4", value = "2.4")
    public void buyParts(String partName, Integer quantity) {
        companyService.addCompany();
        AtomicReference<Integer> quant = new AtomicReference<>(quantity);
        companyService.buyParts(partName, quant);
    }

    @ShellMethod(key = "3.4", value = "3.4")
    public void listPart() {
        companyService.addCompany();
        companyService.listPart();
    }

    @ShellMethod(key = "4.4", value = "4.4")
    public void back() {
        PrimaryMenuCommand.menu();
    }

}
