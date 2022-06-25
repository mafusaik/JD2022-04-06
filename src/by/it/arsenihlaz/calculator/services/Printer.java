package by.it.arsenihlaz.calculator.services;

import by.it.arsenihlaz.calculator.constants.Exception;
import by.it.arsenihlaz.calculator.entity.Var;
import by.it.arsenihlaz.calculator.exception.CalcException;
import by.it.arsenihlaz.jd02_05.ResourceManager;

import java.io.PrintStream;
import java.util.Objects;


public class Printer {
    private final ResourceManager resourceManager = ResourceManager.INSTANSE;
    private final PrintStream out;

    public Printer(PrintStream out) {
        this.out = out;
    }

    public void print(Var result) {
        if (Objects.nonNull(result)) {
            out.println(result);
        }
    }

    public void print(CalcException e) {
        out.println(resourceManager.getValue(Exception.ERROR) + e.getMessage());
    }
}
