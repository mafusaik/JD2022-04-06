package by.it.avramchuk.calc.service;

import by.it.avramchuk.calc.entity.Var;
import by.it.avramchuk.calc.exception.CalcException;

import java.io.PrintStream;
import java.util.Objects;

public class Printer {

    private final PrintStream out;

    public Printer(PrintStream out) {
        this.out = out;
    }

    public void print(Var result) {
        if(Objects.nonNull(result)){
            out.println(result);
        }
    }

    public void print(CalcException e) {
        out.println("ERROR: "+e.getMessage());
    }
}
