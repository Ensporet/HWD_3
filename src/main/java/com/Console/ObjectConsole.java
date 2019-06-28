package com.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class ObjectConsole<T> implements IAction {

    public static final int ITERATION_CLOSE = 0;
    public static final int ITERATION_RUN = 1;
    public static final int ITERATION_EXIT = -1;
    public static int ConsoleIteration = 0;
    public static boolean ConsoleUpdate = false;
    private final HashMap<String, T> hashMap = new HashMap();
    private final List<IAction> DEFAULT_ACTIONS = new ArrayList<>();
    private final List<IAction> ACTIONS = new ArrayList<>();
    private final String NAME;
    private String startMessage;
    private String endMessage;
    private String notHaveCommandMessage;
    private String info;
    private Scanner scanner;


    public ObjectConsole(String startMessage, String endMessage, String notHaveCommandMessage, String NAME, Scanner scanner) {
        this.startMessage = startMessage;
        this.endMessage = endMessage;
        this.notHaveCommandMessage = notHaveCommandMessage;
        this.scanner = scanner;
        this.NAME = NAME;
    }

    public ObjectConsole(Scanner scanner) {
        this.scanner = scanner;
        this.NAME = null;
    }

    public void action() {

        printlnString(getStartMessage());

        ObjectConsole.ConsoleUpdate = true;
        if (ObjectConsole.ConsoleIteration != ObjectConsole.ITERATION_EXIT) {
            ObjectConsole.ConsoleIteration = ObjectConsole.ITERATION_RUN;
        }
        while (ObjectConsole.ConsoleIteration > ObjectConsole.ITERATION_CLOSE) {
            if (ObjectConsole.ConsoleUpdate) {
                update();
            }

            System.out.println(getInfo());

            Object obj = getHashMap().get(scanner.nextLine());
            if (obj == null) {
                printlnString(getNotHaveCommandMessage());
            } else {
                if (obj instanceof IAction) {
                    ((IAction) obj).action();
                }
            }


        }
        if (ObjectConsole.ConsoleIteration != ObjectConsole.ITERATION_EXIT) {
            ObjectConsole.ConsoleIteration = ObjectConsole.ITERATION_RUN;
        }
        printlnString(getEndMessage());

    }

    //--------------------------------

    @Override
    public String getName() {
        return this.NAME;
    }


    //------------------------------------------

    protected void update() {
        ObjectConsole.ConsoleUpdate = false;
        getHashMap().clear();
        int iteration = 0;
        StringBuilder stringBuilder =
                new StringBuilder((getName() == null) ? this.getClass().getTypeName() + "\n" : getName() + " : \n");


        for (IAction action : getACTIONS()) {
            String key = String.valueOf(iteration++);
            getHashMap().put(key, action);
            stringBuilder.append("\n").append(createRowInfo(key, action));
        }

        stringBuilder.append("\n").append(getSeparator());

        for (IAction action : getDEFAULT_ACTIONS()) {
            String key = String.valueOf(iteration++);
            getHashMap().put(key, action);
            stringBuilder.append("\n" + createRowInfo(key, action));
        }

        setInfo(stringBuilder.toString());

    }

    protected String getSeparator() {
        return "|----------------------------------------------------------------------------------|";
    }

    private String createRowInfo(String key, IAction action) {

        return "[" + ((key == null) ? "NULL" : key) + "] : " + ((action.getName() == null) ? "NULL" : action.getName());

    }

    //--------------------------------------------
    private void printlnString(String s) {
        if (s != null) {
            System.out.println(s);
        }
    }

    public HashMap getHashMap() {
        return hashMap;
    }


    public String getStartMessage() {
        return startMessage;
    }

    public void setStartMessage(String startMessage) {
        this.startMessage = startMessage;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public void setEndMessage(String endMessage) {
        this.endMessage = endMessage;
    }

    public List<IAction> getDEFAULT_ACTIONS() {
        return DEFAULT_ACTIONS;
    }

    public List<IAction> getACTIONS() {
        return ACTIONS;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getNotHaveCommandMessage() {
        return notHaveCommandMessage;
    }

    public void setNotHaveCommandMessage(String notHaveCommandMessage) {
        this.notHaveCommandMessage = notHaveCommandMessage;
    }
}
