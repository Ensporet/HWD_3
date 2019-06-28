package com.Console.Menu;

import com.ClassFinder.ClassFinder;
import com.Console.Functions.HWFunction;
import com.Console.IAction;
import com.Console.ObjectConsoleDefault;

import java.lang.reflect.InvocationTargetException;

public class MenuFunctions extends ObjectConsoleDefault {

    private final String PATH_FUNCTIONS = "com.Console.Functions";

    public MenuFunctions() {
        super("Functions HWD_2");

        action();
    }

    @Override
    protected void update() {
        updatedActions();
        super.update();
    }

    private void updatedActions() {
        getACTIONS().clear();

        for (Class cl : ClassFinder.find(getPATH_FUNCTIONS())) {

            if (cl.getDeclaredAnnotation(HWFunction.class) != null) {
                try {

                    Object action = cl.getConstructor().newInstance();
                    if (action instanceof IAction) {
                        getACTIONS().add(action);
                    }

                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPATH_FUNCTIONS() {
        return PATH_FUNCTIONS;
    }
}
