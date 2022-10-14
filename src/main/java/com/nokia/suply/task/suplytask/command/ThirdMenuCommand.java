package com.nokia.suply.task.suplytask.command;

import com.nokia.suply.task.suplytask.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@ShellComponent
@RequiredArgsConstructor
public class ThirdMenuCommand {

    private final PriceService priceService;

    /**
     * Exemplo de chamada no console:
     * shell:> 1.3 ou 2.3 ou 3.3
     * */
    @ShellMethod(key = "2", value = "2")
    public static void menu() {
        log.info("1.3: Add quantity");
        log.info("2.3: List quantity");
        log.info("3.3: Back");
    }

    /**
     * Exemplo de chamada no console:
     * shell:> 1.3 --partName part-1 --manufacturerName manu-1 --price 10.5 --quantity 5
     * */
    @ShellMethod(key = "1.3", value = "1.3")
    public void addQuantity(String partName, String manufacturerName, BigDecimal price, Integer quantity) {
        priceService.addPartPrice(partName, manufacturerName, price, quantity);
    }

    /**
     * Exemplo de chamada no console:
     * shell:> 1.3 --partName part-1 --manufacturerName manu-1
     * */
    @ShellMethod(key = "2.3", value = "2.3")
    public void listQuantity(String partName, @ShellOption(defaultValue = "__NULL__") String manufacturerName) {
        priceService.listQuantity(partName, manufacturerName);
    }

    @ShellMethod(key = "3.3", value = "3.3")
    public void back() throws IOException {
        SecondaryMenuCommand.menu();
    }

}
