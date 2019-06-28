package com.Console;

import com.CRUD.CrudHibernate;

public class ActionDeleteElement implements IAction {

    private final Object OBJECT;
    private final CrudHibernate CRUD = new CrudHibernate();

    public ActionDeleteElement(Object object) {
        this.OBJECT = object;

        action();
    }

    @Override
    public String getName() {
        return "Delete";
    }

    @Override
    public void action() {

        getCRUD().delete(getOBJECT());
        ObjectConsole.ConsoleUpdate = true;

    }


    public CrudHibernate getCRUD() {
        return CRUD;
    }

    public Object getOBJECT() {
        return OBJECT;
    }
}
