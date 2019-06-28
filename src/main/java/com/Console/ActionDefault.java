package com.Console;

public class ActionDefault implements IAction {

    @Override
    public String getName() {
        return "DEFAULT";
    }

    @Override
    public void action() {
        System.out.println("...");
    }
}
