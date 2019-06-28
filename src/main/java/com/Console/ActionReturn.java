package com.Console;

public class ActionReturn implements IAction {


    @Override
    public String getName() {
        return "<<<";
    }


    @Override
    public void action() {
        System.out.println("<<<<Return--------------/");
        ObjectConsole.ConsoleIteration = ObjectConsole.ITERATION_CLOSE;
    }
}
