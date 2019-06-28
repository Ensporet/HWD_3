package com.Console;

public class ActionExit implements IAction {


    @Override
    public String getName() {
        return "Exit";
    }


    @Override
    public void action() {
        ObjectConsole.ConsoleIteration = ObjectConsole.ITERATION_EXIT;

    }
}
