package com.Console;

import com.CRUD.CrudHibernate;

public class ActionDeleteAll implements IAction {

    private final CrudHibernate CRUD = new CrudHibernate();
    private final Class ENTITY;

    public ActionDeleteAll(Class entity) {
        ENTITY = entity;
    }


    @Override
    public String getName() {
        return "Delete all";
    }

    @Override
    public void action() {

        getCRUD().deleteAll(getENTITY());

    }

    public CrudHibernate getCRUD() {
        return CRUD;
    }

    public Class getENTITY() {
        return ENTITY;
    }
}
