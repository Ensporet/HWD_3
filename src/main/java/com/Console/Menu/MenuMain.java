package com.Console.Menu;

import com.Console.IAction;
import com.Console.ObjectConsoleDefault;

public class MenuMain extends ObjectConsoleDefault<IAction> {


    public MenuMain() {
        super("Main menu");

        this.getACTIONS().add(createMenuCRUD());
        this.getACTIONS().add(createMenuFunction());
        this.action();
    }


    private IAction createMenuFunction() {
        return new IAction() {
            @Override
            public String getName() {
                return "Functions";
            }

            @Override
            public void action() {

                new MenuFunctions();
            }
        };
    }


    private IAction createMenuCRUD() {
        return new IAction() {
            @Override
            public String getName() {
                return "Tables";
            }

            @Override
            public void action() {
                new MenuTables();

            }
        };
    }

}
